package se.lu.bos.misgen.templates;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-03
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class DemoTemplate {

    public static String buildDemoMission() throws IOException {
//        MissionOptions missionOptions = MissionFactory.buildDefaultMissionOptions();
//        ObjectGroup planeGroup1 = GroupFactory.buildPlaneGroup(true, 3, PlaneType.BF109G2, true, 95939f, 500f, 50383f, 45f, 0, "Group A", FormationType.LINE);
//        // ObjectGroup planeGroup1 = GroupFactory.buildPlaneGroup(true, 1, PlaneType.BF109G2, true, 131950, 500f, 253736);        // Above stalingrad
//        //planeGroup1.applyYOrientation(90.0f);
//        ObjectGroup planeGroup2 = GroupFactory.buildPlaneGroup(false, 4, PlaneType.BF109G2, true, 94939f, 700f, 50383f, 45f, 0, "Group B", FormationType.LINE);     // TODO Add skill, -1 for random?
//        ObjectGroup yakGroup3 = GroupFactory.buildPlaneGroup(false, 4, PlaneType.YAK1, true, 96939f, 100f, 50383f, 45f, 0, "Group C", FormationType.LINE);
//        ObjectGroup il2Group3 = GroupFactory.buildPlaneGroup(false, 12, PlaneType.IL2M42, true, 96939f, 500f, 55383f, 45f, 18, "Group D", FormationType.LINE);
//        il2Group3.getObjects().forEach(ge -> ((Plane) ge).setPayloadId(49));
//
//        ObjectGroup tanks = GroupFactory.buildVehicleGroup(6, VehicleType.PZIII, 96939f, 185.608f, 50383f, 90f, FormationType.LINE);
//        ObjectGroup sdkfzGroup = GroupFactory.buildVehicleGroup(4, VehicleType.SDKFZ251, 97939f, 185.608f, 50283f, 90f, FormationType.LINE);
//
//        ObjectGroup flak37_1 = GroupFactory.buildVehicleGroup(8, VehicleType.FLAK37, 95539f, 185.608f, 53483f, 90f, FormationType.LINE);
//        ObjectGroup flak37_2 = GroupFactory.buildVehicleGroup(8, VehicleType.FLAK37, 96139f, 185.608f, 50383f, 90f, FormationType.LINE);
//        ObjectGroup flak38_1 = GroupFactory.buildVehicleGroup(8, VehicleType.FLAK38, 96239f, 185.608f, 51383f, 90f, FormationType.LINE);
//
//        CommandAttackArea attackCmd = new CommandAttackArea(tanks.getXPos(), tanks.getYPos(), tanks.getZPos(), 2000, 0, 0, 1, il2Group3.getObjects().get(0));
//        CommandCover yakCoverIl2 = new CommandCover(yakGroup3.getLeader(), il2Group3.getLeader());
//
//
//        // Group now has initial size, position and consistency. Now add some waypoints..
//        WayPoint wayPoint1 = WayPointFactory.buildWayPoint( 89939f, 500f, 69383f, planeGroup2.getObjects().get(0));
//        WayPoint wayPoint2 = WayPointFactory.buildWayPoint( 89939f, 500f, 65383f, planeGroup2.getObjects().get(0));
//        WayPoint wayPoint3 = WayPointFactory.buildWayPoint( 96939f, 185.608f, 54283f, tanks.getObjects().get(0));
//
//        CommandLand landCmd = new CommandLand(95939.132f, 85.608f, 50383.284f, planeGroup2.getObjects().get(0));
//        landCmd.setYOri(270.0f);
//        wayPoint2.getTargets().add(landCmd.getId().intValue());
//
//        // Point WP1 -> WP2
//        TranslatorSubtitle sub2 = new TranslatorSubtitle(80939f, 500f, 75383f, 8);
//        wayPoint1.getTargets().add(wayPoint2.getId().intValue());
//        Timer waypointTimer = new Timer(wayPoint1.getXPos(), wayPoint1.getYPos(), wayPoint1.getZPos()); // TODO use GameEntity as arg
//        waypointTimer.getTargets().add(wayPoint1.getId().intValue());
//        waypointTimer.getTargets().add(wayPoint3.getId().intValue());
//        waypointTimer.getTargets().add(attackCmd.getId().intValue());
//        waypointTimer.getTargets().add(yakCoverIl2.getId().intValue());
//
//
//        // Cover command      TODO We should integrate the timer within the command??
//        //CommandCover commandCover = new CommandCover(planeGroup2.getObjects().get(0), planeGroup1.getObjects().get(0));
//       // Timer cmdTimer = new Timer(commandCover.getXPos(), commandCover.getYPos(), commandCover.getZPos());
//       // cmdTimer.getTargets().add(commandCover.getId().intValue());
//
//
////        Timer landTimer = new Timer(95939.132f, 85.608f, 50383.284f);
////        landTimer.getTargets().add(landCmd.getId().intValue());
////        landTimer.setTime(5000);
//
//        TranslatorIcon icon1 = new TranslatorIcon(89939f, 500f, 69383f);
//        TranslatorIcon icon2 = new TranslatorIcon(89939f, 500f, 65383f);
//        icon1.getTargets().add(icon2.getId().intValue());
//        icon1.setIconId(12);
//        icon2.setIconId(12);
//        icon2.setLCName(5);
//        icon2.setLCDesc(6);
//
//        TranslatorSubtitle sub1 = new TranslatorSubtitle(80939f, 500f, 75383f, 7);
//        Timer subTimer = new Timer(80939f, 500f, 75383f);
//        subTimer.setTime(20);
//        subTimer.getTargets().add(sub1.getId().intValue());
//        subTimer.getTargets().add(sub2.getId().intValue());
//
//        TranslatorSubtitle yakDestSub = new TranslatorSubtitle(80939f, 500f, 75383f, 9);
//        yakDestSub.setRColor(255);
//        yakDestSub.setGColor(0);
//        yakDestSub.setBColor(0);
//        yakGroup3.getObjects().stream().forEach(ge -> ge.getMCU_TR_Entity().getOnEvents().add(new OnEvent(OnEventId.DESTROYED, yakDestSub.getId().intValue())));
//
//        TranslatorSubtitle il2DestSub = new TranslatorSubtitle(80939f, 500f, 75383f, 10);
//        il2DestSub.setRColor(255);
//        il2DestSub.setGColor(0);
//        il2DestSub.setBColor(0);
//        il2Group3.getObjects().stream().forEach(ge -> ge.getMCU_TR_Entity().getOnEvents().add(new OnEvent(OnEventId.DESTROYED, il2DestSub.getId().intValue())));
//
//
//        TranslatorMissionBegin missionBegin = new TranslatorMissionBegin(80939f, 500f, 75383f);
//        missionBegin.getTargets().add(subTimer.getId().intValue());
//        missionBegin.getTargets().add(waypointTimer.getId().intValue());
//
//        GeneratedMission gm = new GeneratedMission();
//        gm.setMissionOptions(missionOptions);
//
////        gm.getAirfields().addAll(StaticGroupsFactory.getAirFieldGroupEntities());
////        gm.getTowns().addAll(StaticGroupsFactory.getReadTownGroupEntities(40000f, 30000f, 120000f, 140000f));
////        gm.getBridges().addAll(StaticGroupsFactory.getReadBridgeGroupEntities(40000f, 30000f, 120000f, 140000f));
////        gm.getRailwayStations().addAll(StaticGroupsFactory.getRailwayStationGroupEntities(40000f, 30000f, 120000f, 140000f));
//
//        gm.getAreaAttackCommands().add(attackCmd);
//        gm.getObjectGroups().add(planeGroup1);
//        gm.getObjectGroups().add(planeGroup2);
//        gm.getObjectGroups().add(yakGroup3);
//        gm.getObjectGroups().add(il2Group3);
//        gm.getObjectGroups().add(tanks);
//        gm.getObjectGroups().add(sdkfzGroup);
//        gm.getObjectGroups().add(flak37_1);
//        gm.getObjectGroups().add(flak37_2);
//        gm.getObjectGroups().add(flak38_1);
//
//        gm.getTranslatorIcons().add(icon1);
//        gm.getTranslatorIcons().add(icon2);
//        gm.getTranslatorSubtitles().add(sub1);
//        gm.getTranslatorSubtitles().add(sub2);
//        gm.getTranslatorSubtitles().add(yakDestSub);
//        gm.getTranslatorSubtitles().add(il2DestSub);
//        gm.getWayPoints().add(wayPoint1);
//        gm.getWayPoints().add(wayPoint2);
//        gm.getWayPoints().add(wayPoint3);
//
//        gm.getTimers().add(subTimer);
//        gm.getTimers().add(waypointTimer);
//        gm.getTranslatorMissionBegins().add(missionBegin);
//
//        return new MissionWriter().generateMission(gm);

        return null;
    }

}
