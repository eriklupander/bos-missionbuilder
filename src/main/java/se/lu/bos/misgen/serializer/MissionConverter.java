package se.lu.bos.misgen.serializer;

import se.lu.bos.misgen.groups.StaticGroupsFactory;
import se.lu.bos.misgen.helper.ObjectGroup;
import se.lu.bos.misgen.model.*;
import se.lu.bos.misgen.model.WayPoint;
import se.lu.bos.misgen.webmodel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-29
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class MissionConverter {

    public static GeneratedMission convert(ClientMission cm) throws IOException {
        GeneratedMission gm = new GeneratedMission();
        gm.setMissionOptions(buildMissionOptions(cm));

        // TODO determine mission bounds, until then don't load towns at all

        gm.getBridges().addAll(StaticGroupsFactory.getReadBridgeGroupEntities());
        gm.getRailwayStations().addAll(StaticGroupsFactory.getRailwayStationGroupEntities());
        gm.getAirfields().addAll(StaticGroupsFactory.getAirFieldGroupEntities());

        buildObjectGroups(cm, gm);

        return gm;
    }

    private static void buildObjectGroups(ClientMission cm, GeneratedMission gm) {
        Side ussr = cm.getSides().get(101);
        Side germany = cm.getSides().get(201);

        ussr.getUnitGroups().forEach(ug -> ug.setCountryCode(101));
        germany.getUnitGroups().forEach(ug -> ug.setCountryCode(201));

        List<WayPoint> allWaypoints = new ArrayList<>();

        Stream<UnitGroup> all = Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());

        List<ObjectGroup> airGroups = all.filter(ug -> ug.getGroupType().equals("AIR_GROUP"))
                .map(ug -> {

                    ObjectGroup objectGroup = GroupFactory.buildPlaneGroup(ug.getAiLevel() == 0, ug.getSize(), PlaneType.valueOf(ug.getType()), ug.getY() != 0, ug.getX(), ug.getY(), ug.getZ(), ug.getyOri());
                    generateWaypoints(ug, objectGroup, gm);
                    return objectGroup;
                })
                .collect(Collectors.toList());

        // Ugly, rebuild stream from scratch
        all = Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());
        List<ObjectGroup> vehicleGroups = all.filter(ug -> ug.getGroupType().equals("GROUND_GROUP"))
                .map(ug -> {

                    ObjectGroup objectGroup = GroupFactory.buildVehicleGroup(ug.getSize(), VehicleType.valueOf(ug.getType()), ug.getX(), ug.getY(), ug.getZ(), ug.getyOri());
                    generateWaypoints(ug, objectGroup, gm);
                    return objectGroup;
                })
                .collect(Collectors.toList());


        gm.getObjectGroups().addAll(airGroups);
        gm.getObjectGroups().addAll(vehicleGroups);
        gm.getWayPoints().addAll(allWaypoints);
    }

    private static void generateWaypoints(UnitGroup ug, ObjectGroup og, GeneratedMission gm) {
        List<WayPoint> generatedWaypoints = new ArrayList<>();
        for (int a = 0; a < ug.getWaypoints().size(); a++) {
            se.lu.bos.misgen.webmodel.WayPoint wp = ug.getWaypoints().get(a);
            WayPoint eWp = new WayPoint(wp.getX(), wp.getY(), wp.getZ(), wp.getArea(), wp.getSpeed(), wp.getPriority());
            eWp.getObjects().add(og.getLeaderId());

            if (a == 0) {
                Timer waypointTimer = new Timer(wp.getX(), wp.getY(), wp.getZ());
                waypointTimer.getTargets().add(eWp.getId().intValue());
                TranslatorMissionBegin missionBegin = new TranslatorMissionBegin(wp.getX(), wp.getY(), wp.getZ());
                missionBegin.getTargets().add(waypointTimer.getId().intValue());
                gm.getTimers().add(waypointTimer);
                gm.getTranslatorMissionBegins().add(missionBegin);
            }
            generatedWaypoints.add(eWp);
        }
        for (int a = 0; a < generatedWaypoints.size() - 1; a++) {
            WayPoint wayPoint = generatedWaypoints.get(a);
            wayPoint.getTargets().add(generatedWaypoints.get(a+1).getId().intValue());
        }

        gm.getWayPoints().addAll(generatedWaypoints);
    }

    private static MissionOptions buildMissionOptions(ClientMission cm) {
        MissionOptions mo = new MissionOptions();
        mo.setDate(cm.getDate());
        mo.setTime(cm.getTime());

        // TODO fix hard-coded playerconfig, currently only bf109g2 works

        return mo;
    }

}
