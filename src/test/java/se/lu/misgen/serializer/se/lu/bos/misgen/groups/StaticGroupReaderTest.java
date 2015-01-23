package se.lu.misgen.serializer.se.lu.bos.misgen.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.lu.bos.misgen.groups.GroupEntity;
import se.lu.bos.misgen.groups.GroupEntityType;
import se.lu.bos.misgen.groups.StaticGroupReader;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-05
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
@Test
public class StaticGroupReaderTest {

    StaticGroupReader testee;

    @BeforeMethod
    public void init() {
        testee = new StaticGroupReader();
    }

    public void testStalingrad() throws IOException {
        List<GroupEntity> groupEntityList =  testee.readGroupEntities("Stalingrad_City.Group", GroupEntityType.STALINGRAD_CITY);
        assertNotNull(groupEntityList);
    }

    public void testAirFields() throws IOException {
        List<GroupEntity> groupEntityList =  testee.readGroupEntities("airfields.group", GroupEntityType.AIRFIELD);
        assertNotNull(groupEntityList);
    }

    public void testReadBridges() throws IOException {
        List<GroupEntity> groupEntityList =  testee.readGroupEntities("stalingrad_bridges.Group", GroupEntityType.BRIDGE);
        assertNotNull(groupEntityList);
    }

    public void testReadTowns() throws IOException {
        List<GroupEntity> cities =  testee.readGroupEntities("Stalingrad_ALL_Towns.Group", GroupEntityType.TOWN);
        assertNotNull(cities);
    }

    public void testRailwayStations() throws IOException {
        List<GroupEntity> groupEntityList =  testee.readGroupEntities("Stalingrad_railway_stations.Group", GroupEntityType.TRAIN_STATION);
        assertNotNull(groupEntityList);
    }
}
