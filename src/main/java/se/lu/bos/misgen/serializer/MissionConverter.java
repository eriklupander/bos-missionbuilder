package se.lu.bos.misgen.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.groups.ClientAirfieldParser;
import se.lu.bos.misgen.groups.GroupEntity;
import se.lu.bos.misgen.groups.StaticGroupsFactory;
import se.lu.bos.misgen.helper.ObjectGroup;
import se.lu.bos.misgen.model.*;
import se.lu.bos.misgen.model.Timer;
import se.lu.bos.misgen.model.WayPoint;
import se.lu.bos.misgen.factory.GroupFactory;
import se.lu.bos.misgen.factory.MissionFactory;
import se.lu.bos.misgen.factory.VehicleFactory;
import se.lu.bos.misgen.util.Util;
import se.lu.bos.misgen.webmodel.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converts "client" Mission POJO into "bos" Mission POJO.
 *
 * E.g. ClientMission -> GeneratedMission
 *
 * TODO should be refactored a bit into more manageable pieces.
 */
@Component
public class MissionConverter {

    private static final Logger log = LoggerFactory.getLogger(MissionConverter.class);

    private Map<Long, ObjectGroup> groupMap = new HashMap<>();

    private Map<Integer, String> localization = new HashMap<>();
    private List<ClientAirfield> clientAirfields = new ArrayList<>();
    private Integer lcId = 0;

    @Autowired
    StaticGroupsFactory staticGroupsFactory;

    @Autowired
    ClientAirfieldParser clientAirfieldParser;


    public synchronized GeneratedMission convert(ClientMission cm) throws IOException {

        groupMap.clear();
        localization.clear();
        clientAirfields.clear();
        lcId = 0;

        List<UnitGroup> playerGroups = cm.getSides().entrySet().stream().flatMap(e -> e.getValue().getUnitGroups().stream()).filter(ug -> ug.getAiLevel() == 0).collect(Collectors.toList());
        if(playerGroups.size() != 1) {
            throw new RuntimeException("Can't generate mission, mission must have exactly ONE (1) group with Skill Level \"Player\". You have " + playerGroups.size() + " such Groups.");
        }
        UnitGroup playerGroup =  playerGroups.get(0);

        clientAirfields = clientAirfieldParser.build();

        GeneratedMission gm = new GeneratedMission();
        gm.setMissionOptions(buildMissionOptions(cm, playerGroup));





        buildObjectGroups(cm, gm, playerGroup);
        buildStaticObjectGroups(cm, gm);

        if(cm.getGenerateAAAAtAirfields()) {
            gm.getAirfieldEntities().stream().forEach(af -> {
                gm.getObjectGroups().addAll(generateAAA(af.getXPos(), af.getYPos(), af.getZPos(), 4, 2));
            });
        }

//        if(cm.getGenerateAAAAtBridges()) {
//            gm.getBridges().stream().forEach(ge -> {
//                gm.getObjectGroups().addAll(generateAAA(ge.getxPos(), ge.getyPos(), ge.getzPos(), 2, 2));
//            });
//        }

        gm.setLocalization(localization);

        Float[] bounds = findBounds(gm);
        log.info("Resolved X/Z mission bounds: " + bounds[0] + ", "+ bounds[1] + ", "+ bounds[2] + ", "+ bounds[3]);
        log.info("Remember, BoS coordinate system has origin at bottom left");

        gm.getTowns().addAll(staticGroupsFactory.getReadTownGroupEntities(bounds[0], bounds[1], bounds[2], bounds[3]));
        gm.getBridges().addAll(staticGroupsFactory.getReadBridgeGroupEntities(bounds[0], bounds[1], bounds[2], bounds[3]));
        gm.getRailwayStations().addAll(staticGroupsFactory.getRailwayStationGroupEntities(bounds[0], bounds[1], bounds[2], bounds[3]));
        gm.getAirfields().addAll(staticGroupsFactory.getAirFieldGroupEntities());

        if(cm.getIncludeStalingradCity()) {
            gm.getTowns().addAll(staticGroupsFactory.getStalingradGroupEntities());
        }

        return gm;
    }



    private Float[] findBounds(GeneratedMission gm) {
        Float minX = Stream.concat(gm.getWayPoints().stream(), gm.getObjectGroups().stream()).min( (wo1, wo2) -> wo1.getXPos().compareTo(wo2.getXPos())).get().getXPos();
        Float maxX = Stream.concat(gm.getWayPoints().stream(), gm.getObjectGroups().stream()).max((wo1, wo2) -> wo1.getXPos().compareTo(wo2.getXPos())).get().getXPos();
        Float minZ = Stream.concat(gm.getWayPoints().stream(), gm.getObjectGroups().stream()).min( (wo1, wo2) -> wo1.getZPos().compareTo(wo2.getZPos())).get().getZPos();
        Float maxZ = Stream.concat(gm.getWayPoints().stream(), gm.getObjectGroups().stream()).max( (wo1, wo2) -> wo1.getZPos().compareTo(wo2.getZPos())).get().getZPos();

        return new Float[] {minX-10000f, minZ-10000f, maxX+10000f, maxZ+10000f};
    }

    private Collection<ObjectGroup> generateAAA(float xPos, float yPos, float zPos, int light, int heavy) {
        // Let's create 4 light and 2 heavy AAA guns spread around the airfield centre point by 500 meters
        ObjectGroup lightAAA = new ObjectGroup();
        for(int a = 0; a < light; a++) {
            Vehicle vehicle = VehicleFactory.buildVehicle(VehicleType.FLAK37, 2, 2, xPos, yPos, zPos, 0);

            switch(a) {
                case 0:
                    vehicle.setXPos(vehicle.getXPos()+500);
                    break;
                case 1:
                    vehicle.setZPos(vehicle.getZPos() - 500);
                    break;
                case 2:
                    vehicle.setXPos(vehicle.getXPos()-500);
                    break;
                case 3:
                    vehicle.setZPos(vehicle.getZPos()+500);
                    break;
            }
            lightAAA.getObjects().add(vehicle);
        }

        ObjectGroup heavyAAA = GroupFactory.buildVehicleGroup(2, VehicleType.FLAK38, xPos, yPos, zPos, 0, FormationType.LINE);
        for(int a = 0; a < heavy; a++) {
            Vehicle v = (Vehicle) heavyAAA.getObjects().get(0);
            switch(a) {
                case 0:
                    v.setXPos(v.getXPos()+350);
                    break;
                case 1:
                    v.setZPos(v.getZPos() - 350);
                    break;
            }
        }

        List<ObjectGroup> aaa = new ArrayList<>();
        aaa.add(lightAAA);
        aaa.add(heavyAAA);
        return aaa;
    }

    private void buildStaticObjectGroups(ClientMission cm, GeneratedMission gm) {
        Side ussr = cm.getSides().get(101);
        Side germany = cm.getSides().get(201);

        ussr.getStaticObjectGroups().forEach(sog -> sog.setCountryCode(101));
        germany.getStaticObjectGroups().forEach(sog -> sog.setCountryCode(201));
        Stream<StaticObjectGroup> all = rebuildStaticObjectStream(ussr, germany);

        List<StaticObject> blocks = all.flatMap(sog -> {

            List<StaticObject> sogs = new ArrayList<>();

            for (int a = 0; a < sog.getSize(); a++) {
                StaticObjectType type = StaticObjectType.valueOf(sog.getType());
                StaticObject so = new StaticObject(sog.getX(), sog.getY(), sog.getZ(), sog.getyOri());
                so.setModel(type.getModel());
                so.setScript(type.getScript());
                so.setLinkTrId(0);
                so.setName("Block");

                // Apply positioning offset within group. Use the yOri to always have static object form a line.
                Float[] newPositions = Util.getOffsetFormationLine(a, sog.getX(), sog.getZ(), sog.getyOri(), 50);
                so.setXPos(newPositions[0]);
                so.setZPos(newPositions[1]);
                sogs.add(so);
            }

            return sogs.stream();
        }).collect(Collectors.toList());

        gm.getStaticObjects().addAll(blocks);
    }

    private void buildObjectGroups(ClientMission cm, GeneratedMission gm, UnitGroup playerGroup) {
        Side ussr = cm.getSides().get(101);
        Side germany = cm.getSides().get(201);

        ussr.getUnitGroups().forEach(ug -> ug.setCountryCode(101));
        germany.getUnitGroups().forEach(ug -> ug.setCountryCode(201));

        List<WayPoint> allWaypoints = new ArrayList<>();

        Stream<UnitGroup> all = rebuildStream(ussr, germany);
        // Validate
        all.forEach(ug -> {
            if(ug.getY() == null) {
                throw new RuntimeException("Unit Group " + ug.getName() + " (" + ug.getType() + ") has no Altitude set.");
            }
        });

        all = rebuildStream(ussr, germany);

        List<ObjectGroup> airGroups = all.filter(ug -> ug.getGroupType().equals("AIR_GROUP"))
                .map(ug -> {

                    ObjectGroup objectGroup = null;
                    try {

                        objectGroup = GroupFactory.buildPlaneGroup(ug.getAiLevel() == 0, ug.getSize(), PlaneType.valueOf(ug.getType()), ug.getY() != 0, ug.getX(), ug.getY(), ug.getZ(), ug.getyOri(), ug.getLoadout(), ug.getName(), FormationType.LINE);
                        groupMap.put(ug.getClientId(), objectGroup);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("Caught Exception building Plane Group: " + e.getMessage());
                        throw new RuntimeException(e.getMessage());
                    }

                    return objectGroup;
                })
                .collect(Collectors.toList());

        // Ugly, rebuild stream from scratch
        all = rebuildStream(ussr, germany);
        List<ObjectGroup> vehicleGroups = all.filter(ug -> ug.getGroupType().equals("GROUND_GROUP"))
                .map(ug -> {
                    FormationType formationType = ug.getFormation();
                    ObjectGroup objectGroup = GroupFactory.buildVehicleGroup(ug.getSize(), VehicleType.valueOf(ug.getType()), ug.getX(), ug.getY(), ug.getZ(), ug.getyOri(), formationType);
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
                generateWaypoints(ug, objectGroup, gm, playerGroup);
            } else {
                // Do we need a mission begin translator if there are no waypoints at all?
            }

        });


        gm.getObjectGroups().addAll(airGroups);
        gm.getObjectGroups().addAll(vehicleGroups);
        gm.getWayPoints().addAll(allWaypoints);



        addIconsForPlayerRoute(gm, ussr, germany);
    }

    private Stream<UnitGroup> rebuildStream(Side ussr, Side germany) {
        return Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());
    }

    private Stream<StaticObjectGroup> rebuildStaticObjectStream(Side ussr, Side germany) {
        return Stream.concat(germany.getStaticObjectGroups().stream(), ussr.getStaticObjectGroups().stream());
    }

    private void addIconsForPlayerRoute(GeneratedMission gm, Side ussr, Side germany) {
        Stream<UnitGroup> unitGroupStream = rebuildStream(ussr, germany);
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

        unitGroupStream = rebuildStream(ussr, germany);
        UnitGroup playerUnitGroup = unitGroupStream.filter(ug -> ug.getAiLevel() == 0).findFirst().get();

        // We need a player starting position icon as well
        if(icons.size() > 0) {

            TranslatorIcon ti = new TranslatorIcon(playerUnitGroup.getX(), playerUnitGroup.getY(), playerUnitGroup.getZ());
            ti.setIconId(IconType.BALOON_BLUE.getCode());
            lcId++;
            ti.setLCName(lcId);
            localization.put(lcId, playerUnitGroup.getName());
            lcId++;
            ti.setLCDesc(lcId);
            localization.put(lcId, "Waypoint");
            icons.add(0, ti);
        }

        // Link icons to form route
        for(int a = 0; a < icons.size(); a++) {
            if(a+1 < icons.size()) {
                TranslatorIcon icon = icons.get(a);
                icon.getTargets().add(icons.get(a+1).getId().intValue());
            }
        }

        // Test, generate icons for enemy ground forces
        unitGroupStream = rebuildStream(ussr, germany);
        List<TranslatorIcon> groundUnitIcons = unitGroupStream
                .filter(ug -> ug.getCountryCode().intValue() != playerUnitGroup.getCountryCode().intValue() && ug.getGroupType().equals("GROUND_GROUP"))
                .map(ug -> {
                    TranslatorIcon ti = new TranslatorIcon(ug.getX(), ug.getY(), ug.getZ());
                    ti.setIconId(Util.getIconIdForVehicleType(VehicleType.valueOf(ug.getType())));
                    lcId++;
                    ti.setLCName(lcId);
                    localization.put(lcId, ug.getName());
                    lcId++;
                    ti.setLCDesc(lcId);
                    localization.put(lcId, ug.getType());
                    return ti;
                })
                .collect(Collectors.toList());


         gm.getTranslatorIcons().addAll(icons);
        gm.getTranslatorIcons().addAll(groundUnitIcons);
    }

    private void generateWaypoints(UnitGroup ug, ObjectGroup og, GeneratedMission gm, UnitGroup playerGroup) {
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
                if(ug.getGroupType().equals("GROUP_GROUP") && ug.getFormation().equals("ROAD_COLUMN")) {
                    CommandFormation cmdFormation = new CommandFormation(wp.getX(), wp.getY(), wp.getZ(), FormationType.ON_ROAD_COLUMN.getFormationCode(), 1);
                    cmdFormation.getObjects().add(og.getLeaderId());
                    Timer formationTimer = new Timer(wp.getX(), wp.getY(), wp.getZ());
                    formationTimer.getTargets().add(cmdFormation.getId().intValue());
                    formationTimer.setTime(10); // Wait 10 seconds
                    gm.getTimers().add(formationTimer);
                    gm.getFormationCommands().add(cmdFormation);
                }

                // For air groups starting in air, issue a "loose wedge" FormationCommand after 10 seconds
                if(ug.getGroupType().equals("AIR_GROUP") && ug.getAiLevel() != 0 && ug.getY() > 299) {
                    CommandFormation cmdFormation = new CommandFormation(wp.getX(), wp.getY(), wp.getZ(), ug.getFormation().getFormationCode(), 2);
                    cmdFormation.getObjects().add(og.getLeaderId());
                    Timer formationTimer = new Timer(wp.getX(), wp.getY(), wp.getZ());
                    formationTimer.getTargets().add(cmdFormation.getId().intValue());
                    formationTimer.setTime(13); // Wait 13 seconds
                    gm.getTimers().add(formationTimer);
                    gm.getFormationCommands().add(cmdFormation);
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

            WayPoint wayPoint = wpIter.next();

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
                        attackCompleteTimer.setTime(1200);  // 20 minutes, for now.
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

            // Test, add subtitle for planes of same coalition
            if(ug.getGroupType().equals("AIR_GROUP") && ug.getCountryCode().equals(playerGroup.getCountryCode())) {
                String text = ug.getName() + " (" + ug.getType() + ") has reached waypoint " + (a+1);
                lcId++;
                TranslatorSubtitle subtitle = new TranslatorSubtitle(wayPoint.getXPos(), wayPoint.getYPos(), wayPoint.getZPos(), lcId);
                subtitle.setDuration(10);
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


    private MissionOptions buildMissionOptions(ClientMission cm, UnitGroup playerUnitGroup) {
        MissionOptions mo = MissionFactory.buildDefaultMissionOptions();
        mo.setDate(cm.getDate());
        mo.setTime(cm.getTime());
        mo.setLCName(lcId); // Mission title, for briefing
        localization.put(lcId, cm.getName());
        lcId++; // 1
        mo.setLCDesc(lcId); // Mission briefing text
        localization.put(lcId, cm.getDescription());

        PlaneType planeType = PlaneType.valueOf(playerUnitGroup.getType());
        mo.setPlayerConfig(planeType.getScript());

        return mo;
    }
}
