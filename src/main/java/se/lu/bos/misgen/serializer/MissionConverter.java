package se.lu.bos.misgen.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lu.bos.misgen.groups.ClientAirfieldParser;
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

    private Map<Integer, String> localization = new HashMap<>();
    private List<ClientAirfield> clientAirfields;
    private Integer lcId = 0;

    public GeneratedMission convert(ClientMission cm) throws IOException {

        clientAirfields = ClientAirfieldParser.build();

        GeneratedMission gm = new GeneratedMission();
        gm.setMissionOptions(buildMissionOptions(cm));

        // TODO determine mission bounds, until then don't load towns at all

        gm.getBridges().addAll(StaticGroupsFactory.getReadBridgeGroupEntities());
        gm.getRailwayStations().addAll(StaticGroupsFactory.getRailwayStationGroupEntities());
        gm.getAirfields().addAll(StaticGroupsFactory.getAirFieldGroupEntities());

        buildObjectGroups(cm, gm);

        gm.setLocalization(localization);

        return gm;
    }

    private void buildObjectGroups(ClientMission cm, GeneratedMission gm) {
        Side ussr = cm.getSides().get(101);
        Side germany = cm.getSides().get(201);

        ussr.getUnitGroups().forEach(ug -> ug.setCountryCode(101));
        germany.getUnitGroups().forEach(ug -> ug.setCountryCode(201));

        List<WayPoint> allWaypoints = new ArrayList<>();

        Stream<UnitGroup> all = rebuildStream(ussr, germany);

        List<ObjectGroup> airGroups = all.filter(ug -> ug.getGroupType().equals("AIR_GROUP"))
                .map(ug -> {

                    ObjectGroup objectGroup = GroupFactory.buildPlaneGroup(ug.getAiLevel() == 0, ug.getSize(), PlaneType.valueOf(ug.getType()), ug.getY() != 0, ug.getX(), ug.getY(), ug.getZ(), ug.getyOri(), ug.getLoadout(), ug.getName());
                    groupMap.put(ug.getClientId(), objectGroup);

                    return objectGroup;
                })
                .collect(Collectors.toList());

        // Ugly, rebuild stream from scratch
        all = rebuildStream(ussr, germany);
        List<ObjectGroup> vehicleGroups = all.filter(ug -> ug.getGroupType().equals("GROUND_GROUP"))
                .map(ug -> {

                    ObjectGroup objectGroup = GroupFactory.buildVehicleGroup(ug.getSize(), VehicleType.valueOf(ug.getType()), ug.getX(), ug.getY(), ug.getZ(), ug.getyOri());
                    groupMap.put(ug.getClientId(), objectGroup);
                    return objectGroup;
                })
                .collect(Collectors.toList());


        // Next, run through the UnitGroups again but this time we want to generate waypoints and commands.
        all = rebuildStream(ussr, germany);
        all.forEach( ug -> {
             // Find the corresponding objectGroup
            ObjectGroup objectGroup = groupMap.get(ug.getClientId());
            if(ug.getWaypoints().size() > 0) {
                generateWaypoints(ug, objectGroup, gm, cm);
            } else {
                // Do we need a mission begin translator if there are no waypoints at all?
            }

        });


        gm.getObjectGroups().addAll(airGroups);
        gm.getObjectGroups().addAll(vehicleGroups);
        gm.getWayPoints().addAll(allWaypoints);

        all = rebuildStream(ussr, germany);

        addIconsForPlayerRoute(gm, all);
    }

    private Stream<UnitGroup> rebuildStream(Side ussr, Side germany) {
        return Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());
    }

    private void addIconsForPlayerRoute(GeneratedMission gm, Stream<UnitGroup> unitGroupStream) {

        List<TranslatorIcon> icons = unitGroupStream
                .filter(ug -> ug.getAiLevel() == 0)
                .flatMap(ug -> ug.getWaypoints().stream())
                .map(wp -> {
                    TranslatorIcon ti = new TranslatorIcon(wp.getX(), wp.getY(), wp.getZ());
                    lcId++;
                    ti.setLCName(lcId);
                    localization.put(lcId, wp.getName());
                    lcId++;
                    ti.setLCDesc(lcId);
                    localization.put(lcId, "Waypoint");
                    return ti;
                })
                .collect(Collectors.toList());

        // Link icons to form route
        for(int a = 0; a < icons.size(); a++) {
            if(a+1 < icons.size()) {
                TranslatorIcon icon = icons.get(a);
                icon.getTargets().add(icons.get(a+1).getId().intValue());
            }
        }

         gm.getTranslatorIcons().addAll(icons);
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

                // For ground groups, set initial formation using a timer or something
                if(ug.getGroupType().equals("GROUP_GROUP")) {
                    CommandFormation cmdFormation = new CommandFormation(wp.getX(), wp.getY(), wp.getZ(), FormationType.ON_ROAD_COLUMN.getFormationCode(), 0);
                    cmdFormation.getObjects().add(og.getLeaderId());
                    waypointTimer.getTargets().add(cmdFormation.getId().intValue());
                }
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
                            eWp.setPriority(2); // MEDIUM priority, will try to engage stuff if possible
                        }
                        generatedWaypoints.add(eWp);
                        break;
                    case ATTACK_AREA:
                        // TODO For at least artillery, a waypoint with attack_area should NOT move to the location, just
                        // make them fire on it...
                        CommandAttackArea attackAreaCmd = new CommandAttackArea(
                                wp.getX(), wp.getY(), wp.getZ(), wp.getArea(),
                                0, 0, 1, og.getLeaderId());
                        gm.getAreaAttackCommands().add(attackAreaCmd);
                        eWp.getTargets().clear();
                        eWp.getTargets().add(attackAreaCmd.getId().intValue());

                        generatedWaypoints.add(eWp);

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


                        generatedWaypoints.add(eWp);
                        break;
                    case ATTACK_TARGET:

                        // We must know the targetId for the group to be covered
                        ObjectGroup targetObjectGroup2 = groupMap.get(wp.getAction().getTargetClientId());
                        if(targetObjectGroup2 != null) {
                            // Next, figure out the generated targetId for its entity.
                            CommandAttackTarget attackCmd = new CommandAttackTarget(
                                    wp.getX(), wp.getY(), wp.getZ(), 1, og.getLeaderId(), targetObjectGroup2.getLeaderId());
                            gm.getAttackTargetCommands().add(attackCmd);

                            // Add attack target to waypoint target list. Or should this be done on the previous waypoint??
                            // TODO How do we resume route after command has completed?
                            eWp.getTargets().clear();
                            eWp.getTargets().add(attackCmd.getId().intValue());
                            generatedWaypoints.add(eWp);
                        } else {
                            log.warn("Got ATTACK_TARGET command, but command had no target set: " + ug.getName());
                        }


                        break;
                    case START:
                        CommandTakeOff cmdStart = new CommandTakeOff(wp.getX(), wp.getY(), wp.getZ(), og.getLeaderId());
                        gm.getTakeOffCommands().add(cmdStart);
                        eWp.getTargets().add(cmdStart.getId().intValue());
                        eWp.setYPos(0.0f);
                        generatedWaypoints.add(eWp);
                        break;
                    case LAND:
                        CommandLand cmdLand = new CommandLand(wp.getX(), wp.getY(), wp.getZ(), og.getLeaderId());
                        gm.getLandCommands().add(cmdLand);
                        eWp.getTargets().add(cmdLand.getId().intValue());
                        eWp.setYPos(0.0f);
                        // TODO A Land command for AI seems to work MUCH better if there is an "Airfield" entity present.
                        // A: Determine how to extract/find the airfield entities with landing vectors etc.
                        final ClientAirfield closest = findClosestAirfield(eWp.getXPos(), eWp.getZPos());
                        // B: Use the waypoint position and find the closest airfield and add it.
                        Airfield airfield = new Airfield(closest.getX(), closest.getY(), closest.getZ(), closest.getName());

                        // Only add once if there are several flights using same land airfield.
                        if(gm.getAirfieldEntities().stream().filter(ae -> ae.getName().equals(closest.getName())).count() == 0) {
                            gm.getAirfieldEntities().add(airfield);
                        }
                        generatedWaypoints.add(eWp);
                        break;
                    default:
                        log.warn("ActionType " + wp.getAction().getActionType() + " not implemented yet...");
                }
            }


        }
        WayPoint lastWaypoint = null;
        int a = 0;
        Iterator<WayPoint> wpIter = generatedWaypoints.iterator();
        while(wpIter.hasNext()) {
        //for (int a = 0; a < generatedWaypoints.size() - 1; a++) {
            WayPoint wayPoint = wpIter.next(); //generatedWaypoints.get(a);

            // If the waypoint does NOT have a command associated at this waypoint, just add the next waypoint if applicable.
            if(wayPoint.getTargets().size() == 0 && a+1 < generatedWaypoints.size()) {
                wayPoint.getTargets().add(generatedWaypoints.get(a+1).getId().intValue());
            } else if(a+1 < generatedWaypoints.size()) {

                GameEntity attackCommand = findAttackCommand(wayPoint.getTargets().get(0).intValue(), gm);
                if(attackCommand != null) {
                    // Attack commands should REPLACE its originating waypoint. The previous waypoint
                    // should retarget from THIS waypoint to the COMMAND.
                    if(lastWaypoint != null) {
                        if(lastWaypoint.getTargets().contains(wayPoint.getId().intValue())) {
                            int idx = lastWaypoint.getTargets().indexOf(wayPoint.getId().intValue());
                            lastWaypoint.getTargets().remove(idx);
                        }

                        lastWaypoint.getTargets().add(attackCommand.getId().intValue());


                        Timer attackCompleteTimer = new Timer(lastWaypoint.getXPos(), lastWaypoint.getYPos(), lastWaypoint.getZPos());
                        attackCompleteTimer.setTime(600);  // 10 minutes, for now.
                        attackCompleteTimer.getTargets().add(generatedWaypoints.get(a+1).getId().intValue());

                        String text = ug.getName() + " (" + ug.getType() + ") attack time period expired, resuming route";
                        lcId++;
                        TranslatorSubtitle subtitle = new TranslatorSubtitle(lastWaypoint.getXPos(), lastWaypoint.getYPos(), lastWaypoint.getZPos(), lcId);
                        localization.put(lcId, text);
                        attackCompleteTimer.getTargets().add(subtitle.getId().intValue());
                        gm.getTranslatorSubtitles().add(subtitle);

                        gm.getTimers().add(attackCompleteTimer);
                        lastWaypoint.getTargets().add(attackCompleteTimer.getId().intValue());

                        wpIter.remove();
                        a++;
                        continue;
                    }
                }
            }

            // Test, add subtitle for planes
            if(ug.getGroupType().equals("AIR_GROUP")) {
                String text = ug.getName() + " (" + ug.getType() + ") has reached waypoint " + (a+1);
                lcId++;
                TranslatorSubtitle subtitle = new TranslatorSubtitle(wayPoint.getXPos(), wayPoint.getYPos(), wayPoint.getZPos(), lcId);
                localization.put(lcId, text);
                wayPoint.getTargets().add(subtitle.getId().intValue());
                gm.getTranslatorSubtitles().add(subtitle);
            }
            lastWaypoint = wayPoint;
            a++;
        }

        gm.getWayPoints().addAll(generatedWaypoints);
    }

    private GameEntity findAttackCommand(Integer id, GeneratedMission gm) {
        return Stream.concat(gm.getAreaAttackCommands().stream(), gm.getAttackTargetCommands().stream()).filter(ac -> ac.getId().intValue() == id.intValue()).findFirst().orElse(null);
    }

    private CommandAttackArea findCommandAttackArea(Integer id, GeneratedMission gm) {

        Optional<CommandAttackArea> first = gm.getAreaAttackCommands().stream().filter(aac -> aac.getId().intValue() == id.intValue()).findFirst();
        if(first.isPresent()) {
            return first.get();
        } else {
            return null;
        }

    }

    private ClientAirfield findClosestAirfield(Float xPos, Float zPos) {
        if(clientAirfields.size() == 0) {
            return null;
        }

        return clientAirfields.stream().min(new Comparator<ClientAirfield>() {
            @Override
            public int compare(ClientAirfield o1, ClientAirfield o2) {
                Double len1 = Math.sqrt(Math.abs(o1.getX() - xPos) * Math.abs(o1.getX() - xPos) +
                        Math.abs(o1.getZ() - zPos) * Math.abs(o1.getZ() - zPos));
                Double len2 = Math.sqrt(Math.abs(o2.getX() - xPos) * Math.abs(o2.getX() - xPos) +
                        Math.abs(o2.getZ() - zPos) * Math.abs(o2.getZ() - zPos));

                return len1.compareTo(len2);
            }
        }).get();
    }

//    private GameEntity findAttackAreaCommand(GeneratedMission gm, Integer id) {
//
//    }

    private MissionOptions buildMissionOptions(ClientMission cm) {
        MissionOptions mo = MissionFactory.buildDefaultMission();
        mo.setDate(cm.getDate());
        mo.setTime(cm.getTime());
        mo.setLCName(lcId); // Mission title, for briefing
        localization.put(lcId, cm.getName());
        lcId++; // 1
        mo.setLCDesc(lcId); // Mission briefing text
        localization.put(lcId, cm.getDescription());

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
