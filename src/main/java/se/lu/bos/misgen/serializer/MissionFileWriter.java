package se.lu.bos.misgen.serializer;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Writes the mission directly to disk.
 *
 * See
 */
@Controller
public class MissionFileWriter {

    private static final String TRANSLATION_FILE_ENCODING = "UTF-16LE";
    private static final String MISSION_FILE_ENCODING = "US-ASCII";


    public void write(String name, Map<Integer, String> localization, String missionBody, String targetPath) throws IOException {

        File dir = new File(targetPath);
        if(!dir.exists()) dir.mkdirs();

        name = name.replaceAll(" ", "_");
        name = name.substring(0, name.length() > 15 ? 15 : name.length());

        StringBuilder buf = new StringBuilder();
        localization.entrySet().stream().forEach(entry -> buf.append(entry.getKey()).append(":").append(entry.getValue() != null ? entry.getValue() : "").append("\r\n"));
        FileOutputStream fos1 = new FileOutputStream(
                new File(targetPath + name + ".eng"));
        IOUtils.write(buf.toString(),
                fos1
                , TRANSLATION_FILE_ENCODING
        );
        IOUtils.closeQuietly(fos1);

        FileOutputStream fos2 = new FileOutputStream(
                new File(targetPath + name + ".Mission"));
        IOUtils.write(missionBody,
                fos2
                , MISSION_FILE_ENCODING
        );
        IOUtils.closeQuietly(fos2);

    }
}
