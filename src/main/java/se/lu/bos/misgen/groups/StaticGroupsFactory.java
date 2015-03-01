package se.lu.bos.misgen.groups;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.util.EnvUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-27
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
@Component
public class StaticGroupsFactory {

    @Autowired
    StaticGroupReader staticGroupReader;

    @Autowired
    EnvUtil envUtil;

    public String getAirfields() throws IOException {
        InputStream resourceAsStream = StaticGroupsFactory.class.getClassLoader().getResourceAsStream("airfields.group");              // OWN DERIVED GROUP
        String airfields = IOUtils.toString(resourceAsStream);
        return airfields;
    }

    public String getStalingradCity() throws IOException {
        InputStream resourceAsStream = readFile("Template\\Stalingrad_City.Group"); ///StaticGroupsFactory.class.getClassLoader().getResourceAsStream("Stalingrad_City.Group");     // BOS resource
        return IOUtils.toString(resourceAsStream);
    }



    public String getTowns() throws IOException {
        InputStream resourceAsStream = readFile("Template\\Stalingrad_ALL_Towns.Group");   // BOS resource
        return IOUtils.toString(resourceAsStream);
    }

    public String getBridges() throws IOException {
        InputStream resourceAsStream = readFile("Template\\Stalingrad_bridges.Group");   // BOS resource
        return IOUtils.toString(resourceAsStream);
    }

    public String getRailwayStations() throws IOException {
        InputStream resourceAsStream = readFile("Template\\Stalingrad_railway_stations.Group");    // BOS resource
        return IOUtils.toString(resourceAsStream);
    }

    public List<GroupEntity> getStalingradGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_City.Group", GroupEntityType.STALINGRAD_CITY);    // BOS resource
        return groupEntityList;
    }

    public List<GroupEntity> getAirFieldGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntitiesFromClasspath("airfields.group", GroupEntityType.AIRFIELD);
        return groupEntityList;
    }

    public List<GroupEntity> getReadBridgeGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("stalingrad_bridges.Group", GroupEntityType.BRIDGE);
        return groupEntityList;
    }

    public List<GroupEntity> getReadBridgeGroupEntities(float x1, float z1, float x2, float z2) throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("stalingrad_bridges.Group", GroupEntityType.BRIDGE);
        return filterByPosition(x1, z1, x2, z2, groupEntityList);
    }



    public List<GroupEntity> getReadTownGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_ALL_Towns.Group", GroupEntityType.TOWN);
        return groupEntityList;
    }

    public List<GroupEntity> getReadTownGroupEntities(float x1, float z1, float x2, float z2) throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_ALL_Towns.Group", GroupEntityType.TOWN);
        return filterByPosition(x1, z1, x2, z2, groupEntityList);
    }

    public List<GroupEntity> getRailwayStationGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_railway_stations.Group", GroupEntityType.TRAIN_STATION);
        return groupEntityList;
    }

    public List<GroupEntity> getRailwayStationGroupEntities(float x1, float z1, float x2, float z2) throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_railway_stations.Group", GroupEntityType.TRAIN_STATION);
        return filterByPosition(x1, z1, x2, z2, groupEntityList);
    }

    private static List<GroupEntity> filterByPosition(final float x1, final float z1, final float x2, final float z2, List<GroupEntity> groupEntityList) {
        return groupEntityList.stream()
                .filter(ge -> {
                    boolean x1b = ge.getxPos() > x1;
                    boolean x2b = ge.getxPos() < x2;
                    boolean z1b = ge.getzPos() > z1;
                    boolean z2b = ge.getzPos() < z2;
                    return x1b && x2b && z1b && z2b;
                })
                .collect(Collectors.toList());
    }

    private FileInputStream readFile(String resourceFile) throws IOException {
        return FileUtils.openInputStream(new File(envUtil.getBasePath() + resourceFile));
    }
}
