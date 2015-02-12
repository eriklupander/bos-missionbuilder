package se.lu.bos.misgen.nosql;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

public class ElasticSearchServer {

    // Constants -------------------------------------------------------------------

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchServer.class);

    // Instances -------------------------------------------------------------------

    protected final Map<String, String> _configuration;
    private Node _server;

    // Constructors ----------------------------------------------------------------

    public ElasticSearchServer(Map<String, String> configuration) {
        _configuration = configuration;
    }


    // Publics ---------------------------------------------------------------------

    public void start() {
        if (LOG.isInfoEnabled()) {
            LOG.info("Starting the Elastic Search server node");
        }

        final ImmutableSettings.Builder builder =
                ImmutableSettings.settingsBuilder().put(_configuration);
        _server = nodeBuilder().settings(builder).build();

        if ("true".equalsIgnoreCase(System.getProperty("es.max.files"))) {
            final String workPath = _server.settings().get("path.work");
            final int maxOpen = maxOpenFiles(new File(workPath));
            if (LOG.isInfoEnabled()) {
                LOG.info("The maximum number of open files for user () is {}: ",
                        System.getProperty("user.name"), maxOpen);
            }
        }

        if (LOG.isInfoEnabled()) {
            LOG.info("Starting the Elastic Search server node with these settings:");
            final Map<String, String> map = _server.settings().getAsMap();
            final List<String> keys = new ArrayList<String>(map.keySet());
            Collections.sort(keys);
            for (String key : keys) {
                LOG.info("    " + key + " : " + getValue(map, key));
            }
        }

        _server.start();

        checkServerStatus();

        if (LOG.isInfoEnabled()) {
            LOG.info("Elastic Search server is started.");
        }
    }

    public void stop() {
        _server.close();
    }

    public Client getClient() {
        return _server.client();
    }

    // Protecteds ------------------------------------------------------------------

    @SuppressWarnings({"InfiniteLoopStatement", "ResultOfMethodCallIgnored"})
    protected int maxOpenFiles(File testDir) {
        boolean dirCreated = false;
        if (!testDir.exists()) {
            dirCreated = true;
            testDir.mkdirs();
        }
        List<RandomAccessFile> files = new ArrayList<RandomAccessFile>();
        try {
            while (true) {
                files.add(new RandomAccessFile(new File(testDir, "tmp" + files.size()), "rw"));
            }
        } catch (IOException ioe) {
            int i = 0;
            for (RandomAccessFile raf : files) {
                try {
                    raf.close();
                } catch (IOException e) {
                    // ignore
                }
                new File(testDir, "tmp" + i++).delete();
            }
            if (dirCreated) {
                deleteRecursively(testDir);
            }
        }
        return files.size();
    }

    protected boolean deleteRecursively(File root) {
        return deleteRecursively(root, true);
    }

    /**
     * Delete the supplied {@link java.io.File} - for directories,
     * recursively delete any nested directories or files as well.
     *
     * @param root       the root <code>File</code> to delete
     * @param deleteRoot whether or not to delete the root itself or just the content of the root.
     * @return <code>true</code> if the <code>File</code> was deleted,
     *         otherwise <code>false</code>
     */
    protected boolean deleteRecursively(File root, boolean deleteRoot) {
        if (root != null && root.exists()) {
            if (root.isDirectory()) {
                File[] children = root.listFiles();
                if (children != null) {
                    for (File aChildren : children) {
                        deleteRecursively(aChildren);
                    }
                }
            }

            return !deleteRoot || root.delete();
        }
        return false;
    }


    protected ClusterHealthStatus getHealthStatus() {
        return getClient().admin().cluster().prepareHealth().execute().actionGet().getStatus();
    }


    protected void checkServerStatus() {
        ClusterHealthStatus status = getHealthStatus();

        // Check the current status of the ES cluster.
        if (ClusterHealthStatus.RED.equals(status)) {
            LOG.info("ES cluster status is " + status + ". Waiting for ES recovery.");

            // Waits at most 30 seconds to make sure the cluster health is at least yellow.
            getClient().admin().cluster().prepareHealth()
                    .setWaitForYellowStatus()
                    .setTimeout("30s")
                    .execute().actionGet();
        }

        // Check the cluster health for a final time.
        status = getHealthStatus();
        LOG.info("ES cluster status is " + status);

        // If we are still in red status, then we cannot proceed.
        if (ClusterHealthStatus.RED.equals(status)) {
            throw new RuntimeException("ES cluster health status is RED. Server is not able to start.");
        }

    }

    protected static String getValue(Map<String, String> map, String key) {
        if (key.startsWith("cloud.aws.secret")) return "<HIDDEN>";
        return map.get(key);
    }
}