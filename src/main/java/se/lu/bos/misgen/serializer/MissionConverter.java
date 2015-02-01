package se.lu.bos.misgen.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lu.bos.misgen.groups.StaticGroupsFactory;
import se.lu.bos.misgen.helper.ObjectGroup;
import se.lu.bos.misgen.model.*;
import se.lu.bos.misgen.model.Timer;
import se.lu.bos.misgen.model.WayPoint;
import se.lu.bos.misgen.webmodel.*;

import java.io.IOException;
import java.util.*;
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

    private static final Logger log = LoggerFactory.getLogger(MissionConverter.class);

    private Map<Long, ObjectGroup> groupMap = new HashMap<>();

    public GeneratedMission convert(ClientMission cm) throws IOException {
        GeneratedMission gm = new GeneratedMission();
        gm.setMissionOptions(buildMissionOptions(cm));

        // TODO determine mission bounds, until then don't load towns at all

        gm.getBridges().addAll(StaticGroupsFactory.getReadBridgeGroupEntities());
        gm.getRailwayStations().addAll(StaticGroupsFactory.getRailwayStationGroupEntities());
        gm.getAirfields().addAll(StaticGroupsFactory.getAirFieldGroupEntities());

        buildObjectGroups(cm, gm);

        return gm;
    }

    private void buildObjectGroups(ClientMission cm, GeneratedMission gm) {
        Side ussr = cm.getSides().get(101);
        Side germany = cm.getSides().get(201);

        ussr.getUnitGroups().forEach(ug -> ug.setCountryCode(101));
        germany.getUnitGroups().forEach(ug -> ug.setCountryCode(201));

        List<WayPoint> allWaypoints = new ArrayList<>();

        Stream<UnitGroup> all = Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());

        List<ObjectGroup> airGroups = all.filter(ug -> ug.getGroupType().equals("AIR_GROUP"))
                .map(ug -> {

                    ObjectGroup objectGroup = GroupFactory.buildPlaneGroup(ug.getAiLevel() == 0, ug.getSize(), PlaneType.valueOf(ug.getType()), ug.getY() != 0, ug.getX(), ug.getY(), ug.getZ(), ug.getyOri());
                    groupMap.put(ug.getClientId(), objectGroup);
                    //generateWaypoints(ug, objectGroup, gm, cm);
                    return objectGroup;
                })
                .collect(Collectors.toList());

        // Ugly, rebuild stream from scratch
        all = Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());
        List<ObjectGroup> vehicleGroups = all.filter(ug -> ug.getGroupType().equals("GROUND_GROUP"))
                .map(ug -> {

                    ObjectGroup objectGroup = GroupFactory.buildVehicleGroup(ug.getSize(), VehicleType.valueOf(ug.getType()), ug.getX(), ug.getY(), ug.getZ(), ug.getyOri());
                    groupMap.put(ug.getClientId(), objectGroup);
                    //generateWaypoints(ug, objectGroup, gm, cm);
                    return objectGroup;
                })
                .collect(Collectors.toList());


        // Next, run through the UnitGroups again but this time we want to generate waypoints and commands.
        all = Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());
        all.forEach( ug -> {
             // Find the corresponding objectGroup
            ObjectGroup objectGroup = groupMap.get(ug.getClientId());
            generateWaypoints(ug, objectGroup, gm, cm);
        });


        gm.getObjectGroups().addAll(airGroups);
        gm.getObjectGroups().addAll(vehicleGroups);
        gm.getWayPoints().addAll(allWaypoints);
    }

    private void generateWaypoints(UnitGroup ug, ObjectGroup og, GeneratedMission gm, ClientMission cm) {
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

            // Now, check if there is an interesting command on the waypoint.
            if(wp.getAction() != null) {
                // Generate applicable command
                switch(wp.getAction().getActionType()) {
                    case FLY:
                        // Make sure ground groups don't fly away ;) and keep within speed limits.
                        if(ug.getGroupType().equals("GROUND_GROUP")) {
                            eWp.setYPos(0.0f);
                            if(eWp.getSpeed() > 30) {
                                eWp.setSpeed(30);
                            }
                        }
                        break;
                    case ATTACK_AREA:
                        CommandAttackArea attackAreaCmd = new CommandAttackArea(
                                wp.getX(), wp.getY(), wp.getZ(), wp.getArea(),
                                0, 0, 1, og.getLeaderId());
                        gm.getAreaAttackCommands().add(attackAreaCmd);
                        eWp.getTargets().clear();
                        eWp.getTargets().add(attackAreaCmd.getId().intValue());
                        // TODO How the hell do we get the group to continue after attacking??
                        break;
                    case COVER:

                        // We must know the targetId for the group to be covered
                        ObjectGroup targetObjectGroup = groupMap.get(wp.getAction().getTargetClientId());

                        // Next, figure out the generated targetId for its entity.
                        CommandCover coverCmd = new CommandCover(
                                wp.getX(), wp.getY(), wp.getZ(), og.getLeaderId(), targetObjectGroup.getLeaderId());
                        gm.getCoverCommands().add(coverCmd);
                        // Add cover target to waypoint target list. Or should this be done on the previous waypoint??
                        eWp.getTargets().add(coverCmd.getId().intValue());

                        break;
                    case ATTACK_TARGET:

                        // We must know the targetId for the group to be covered
                        ObjectGroup targetObjectGroup2 = groupMap.get(wp.getAction().getTargetClientId());

                        // Next, figure out the generated targetId for its entity.
                        CommandAttackTarget attackCmd = new CommandAttackTarget(
                                wp.getX(), wp.getY(), wp.getZ(), 1, og.getLeaderId(), targetObjectGroup2.getLeaderId());
                        gm.getAttackTargetCommands().add(attackCmd);

                        // Add attack target to waypoint target list. Or should this be done on the previous waypoint??
                        // TODO How do we resume route after command has completed?
                        eWp.getTargets().clear();
                        eWp.getTargets().add(attackCmd.getId().intValue());


                        break;
                    case START:
                        CommandTakeOff cmdStart = new CommandTakeOff(wp.getX(), wp.getY(), wp.getZ(), og.getLeaderId());
                        gm.getTakeOffCommands().add(cmdStart);
                        eWp.getTargets().add(cmdStart.getId().intValue());
                        eWp.setYPos(0.0f);
                        break;
                    case LAND:
                        CommandLand cmdLand = new CommandLand(wp.getX(), wp.getY(), wp.getZ(), og.getLeaderId());
                        gm.getLandCommands().add(cmdLand);
                        eWp.getTargets().add(cmdLand.getId().intValue());
                        eWp.setYPos(0.0f);
                        break;
                    default:
                        log.warn("ActionType " + wp.getAction().getActionType() + " not implemented yet...");
                }
            }

            generatedWaypoints.add(eWp);
        }
        for (int a = 0; a < generatedWaypoints.size() - 1; a++) {
            WayPoint wayPoint = generatedWaypoints.get(a);
            wayPoint.getTargets().add(generatedWaypoints.get(a+1).getId().intValue());
        }

        gm.getWayPoints().addAll(generatedWaypoints);
    }

    private MissionOptions buildMissionOptions(ClientMission cm) {
        MissionOptions mo = new MissionOptions();
        mo.setDate(cm.getDate());
        mo.setTime(cm.getTime());

        // TODO fix hard-coded playerconfig, currently only bf109g2 works

        return mo;
    }

    private UnitGroup findUnitGroupByClientId(Long clientId, ClientMission cm) {
        List<UnitGroup> collect = cm.getSides().entrySet().stream().flatMap(entry -> entry.getValue().getUnitGroups().stream()).filter(ug -> ug.getClientId().equals(clientId)).collect(Collectors.toList());
        if(collect.size() > 0) {
            return collect.get(0);
        }
        throw new IllegalArgumentException("Could not find unitgroup matching id: " + clientId);
    }

}
