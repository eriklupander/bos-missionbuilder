package se.lu.bos.misgen.groups;

import org.apache.commons.io.IOUtils;

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
public class StaticGroupsFactory {

    private static StaticGroupReader staticGroupReader = new StaticGroupReader();

    public static String getAirfields() throws IOException {
        InputStream resourceAsStream = StaticGroupsFactory.class.getClassLoader().getResourceAsStream("airfields.group");
        String airfields = IOUtils.toString(resourceAsStream);
        return airfields;
    }

    public static String getStalingradCity() throws IOException {
        InputStream resourceAsStream = StaticGroupsFactory.class.getClassLoader().getResourceAsStream("Stalingrad_City.Group");
        return IOUtils.toString(resourceAsStream);
    }

    public static String getTowns() throws IOException {
        InputStream resourceAsStream = StaticGroupsFactory.class.getClassLoader().getResourceAsStream("Stalingrad_ALL_Towns.Group");
        return IOUtils.toString(resourceAsStream);
    }

    public static String getBridges() throws IOException {
        InputStream resourceAsStream = StaticGroupsFactory.class.getClassLoader().getResourceAsStream("Stalingrad_bridges.Group");
        return IOUtils.toString(resourceAsStream);
    }

    public static String getRailwayStations() throws IOException {
        InputStream resourceAsStream = StaticGroupsFactory.class.getClassLoader().getResourceAsStream("Stalingrad_railway_stations.Group");
        return IOUtils.toString(resourceAsStream);
    }

    public static List<GroupEntity> getStalingradGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_City.Group", GroupEntityType.STALINGRAD_CITY);
        return groupEntityList;
    }

    public static List<GroupEntity> getAirFieldGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("airfields.group", GroupEntityType.AIRFIELD);
        return groupEntityList;
    }

    public static List<GroupEntity> getReadBridgeGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("stalingrad_bridges.Group", GroupEntityType.BRIDGE);
        return groupEntityList;
    }

    public static List<GroupEntity> getReadBridgeGroupEntities(float x1, float z1, float x2, float z2) throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("stalingrad_bridges.Group", GroupEntityType.BRIDGE);
        return filterByPosition(x1, z1, x2, z2, groupEntityList);
    }



    public static List<GroupEntity> getReadTownGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_ALL_Towns.Group", GroupEntityType.TOWN);
        return groupEntityList;
    }

    public static List<GroupEntity> getReadTownGroupEntities(float x1, float z1, float x2, float z2) throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_ALL_Towns.Group", GroupEntityType.TOWN);
        return filterByPosition(x1, z1, x2, z2, groupEntityList);
    }

    public static List<GroupEntity> getRailwayStationGroupEntities() throws IOException {
        List<GroupEntity> groupEntityList =  staticGroupReader.readGroupEntities("Stalingrad_railway_stations.Group", GroupEntityType.TRAIN_STATION);
        return groupEntityList;
    }

    public static List<GroupEntity> getRailwayStationGroupEntities(float x1, float z1, float x2, float z2) throws IOException {
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
}
