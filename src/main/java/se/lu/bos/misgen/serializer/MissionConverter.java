package se.lu.bos.misgen.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.factory.GroupFactory;
import se.lu.bos.misgen.factory.MissionFactory;
import se.lu.bos.misgen.factory.VehicleFactory;
import se.lu.bos.misgen.groups.ClientAirfieldParser;
import se.lu.bos.misgen.groups.StaticGroupsFactory;
import se.lu.bos.misgen.helper.ObjectGroup;
import se.lu.bos.misgen.model.*;
import se.lu.bos.misgen.model.Effect;
import se.lu.bos.misgen.model.Timer;
import se.lu.bos.misgen.model.WayPoint;
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
        if(playerGroups.size() != 1 && cm.getMissionType().intValue() == 0) {
            throw new RuntimeException("Can't generate mission, mission must have exactly ONE (1) group with Skill Level \"Player\". You have " + playerGroups.size() + " such Groups.");
        }
        if(playerGroups.stream().anyMatch(ug -> !PlaneType.valueOf(ug.getType()).getFlyable())) {
            throw new RuntimeException("Can't generate mission. There is a non-playable aircraft (e.g. JU-52) specified as \"Player\". This causes the simulator to crash, so please change your mission and try again");
        }
        UnitGroup playerGroup =  playerGroups != null && playerGroups.size() > 0 ? playerGroups.get(0) : null;

        clientAirfields = clientAirfieldParser.build();

        GeneratedMission gm = new GeneratedMission();
        gm.setMissionOptions(buildMissionOptions(cm, playerGroup));





        buildObjectGroups(cm, gm);
        buildEffects(cm, gm);
        buildStaticObjectGroups(cm, gm);


        generateAAA(cm, gm);

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

    /**
     * Builds a structure like this to activate and add all effects to the mission:
     *
     * MCU_TR_MissionBegin -> [target link] -> Timer -> [target link] -> MCU_CMD_Effect -> [object-link(s)] -> MCU_TR_Entity -> [MisObjID] -> Effect
     *
     * @param cm
     * @param gm
     */
    private void buildEffects(ClientMission cm, GeneratedMission gm) {
        if(cm.getEffects().size() == 0) return;

        List<Effect> effectList =  cm.getEffects().stream()
                .map(e -> new Effect(e.getX(), e.getY(), e.getZ(), e.getEffectType()))
                .collect(Collectors.toList());

        final TranslatorMissionBegin translatorMissionBegin = new TranslatorMissionBegin(effectList.get(0).getXPos(), effectList.get(0).getYPos(), effectList.get(0).getZPos());
        final Timer effectsTimer = new Timer(effectList.get(0).getXPos(), effectList.get(0).getYPos(), effectList.get(0).getZPos());
        final CommandEffect cmdEffect = new CommandEffect(effectList.get(0).getXPos(), effectList.get(0).getYPos(), effectList.get(0).getZPos());

        effectList.stream().forEach(e -> cmdEffect.getObjects().add(e.getLinkTrId()));
        effectsTimer.getTargets().add(cmdEffect.getId().intValue());
        translatorMissionBegin.getTargets().add(effectsTimer.getId().intValue());

        gm.getTranslatorMissionBegins().add(translatorMissionBegin);
        gm.getEffectCommands().add(cmdEffect);
        gm.getTimers().add(effectsTimer);
        gm.getEffects().addAll(effectList);

    }

    private void generateAAA(ClientMission cm, GeneratedMission gm) {
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
                Float[] newPositions = Util.getOffsetFormationLine(a, sog.getX(), sog.getY(), sog.getZ(), sog.getyOri(), 50);
                so.setXPos(newPositions[0]);
                so.setZPos(newPositions[2]);
                sogs.add(so);
            }

            return sogs.stream();
        }).collect(Collectors.toList());

        gm.getStaticObjects().addAll(blocks);
    }

    private void buildObjectGroups(ClientMission cm, GeneratedMission gm) {
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

        List<ObjectGroup> airGroups = all.filter(ug -> ug.getGroupType() == GroupType.AIR_GROUP)
                .map(ug -> {

                    ObjectGroup objectGroup = null;
                    try {
                        objectGroup = GroupFactory.buildPlaneGroup(ug, FormationType.VEE);

                        // Need some ugly stuff here to handle coop missions...
                        if (cm.getMissionType().intValue() == 1) {
                            // Make sure no aiLevel = 0 exists
                            objectGroup.getObjects().forEach(ge -> {
                                Plane p = (Plane) ge;
                                if (p.getAILevel().intValue() == 0) {
                                    p.setAILevel(3);
                                }
                                for(int a = 0; a < ug.getCoop().size(); a++) {
                                    if(a == p.getNumberInFormation().intValue()) {
                                        p.setCoopStart(ug.getCoop().get(a));
                                    }
                                }
                            });


                        }

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
        List<ObjectGroup> vehicleGroups = all.filter(ug -> ug.getGroupType() == GroupType.GROUND_GROUP)
                .map(ug -> {
                    FormationType formationType = ug.getFormation();
                    if(formationType == FormationType.NONE) {
                        formationType = FormationType.LINE;
                    }
                    ObjectGroup objectGroup = GroupFactory.buildVehicleGroup(ug.getSize(), VehicleType.valueOf(ug.getType()), ug.getX(), ug.getY(), ug.getZ(), ug.getyOri(), formationType);
                    objectGroup.getObjects().stream().forEach( o -> {
                        Vehicle v = (Vehicle) o;
                        v.setName(ug.getName() + " " + v.getNumberInFormation() + "(" + ug.getType() + ")");
                    });
                    groupMap.put(ug.getClientId(), objectGroup);
                    return objectGroup;
                })
                .collect(Collectors.toList());

        addKilledMessageToGroundVehicle(vehicleGroups, gm);

        gm.getObjectGroups().addAll(airGroups);
        gm.getObjectGroups().addAll(vehicleGroups);

        // Next, run through the UnitGroups again but this time we want to generate waypoints and commands.
        all = rebuildStream(ussr, germany);
        all.forEach( ug -> {
             // Find the corresponding objectGroup
            ObjectGroup objectGroup = groupMap.get(ug.getClientId());
            if(ug.getWaypoints().size() > 0) {
                generateWaypoints(ug, objectGroup, gm);
            } else {
                // Do we need a mission begin translator if there are no waypoints at all?
            }

        });



        gm.getWayPoints().addAll(allWaypoints);



        addIcons(gm, ussr, germany);
    }

    private Stream<UnitGroup> rebuildStream(Side ussr, Side germany) {
        return Stream.concat(germany.getUnitGroups().stream(), ussr.getUnitGroups().stream());
    }

    private Stream<StaticObjectGroup> rebuildStaticObjectStream(Side ussr, Side germany) {
        return Stream.concat(germany.getStaticObjectGroups().stream(), ussr.getStaticObjectGroups().stream());
    }

    private void addIcons(GeneratedMission gm, Side ussr, Side germany) {
        Stream<UnitGroup> unitGroupStream = rebuildStream(ussr, germany);
        List<TranslatorIcon> waypointIcons = new ArrayList<>();
        unitGroupStream
                .filter(ug -> ug.isBriefingIcon() && ug.isBriefingWaypointIcons())
                .forEach(ug -> {
                    List<TranslatorIcon> tmpList = new ArrayList<>();

                    // Iterate over all waypoints, make an icon for each
                    for(int a = 0; a < ug.getWaypoints().size(); a++) {
                        se.lu.bos.misgen.webmodel.WayPoint wp = ug.getWaypoints().get(a);
                        TranslatorIcon ti = new TranslatorIcon(wp.getX(), wp.getY(), wp.getZ());
                        lcId++;
                        ti.setLCName(lcId);
                        localization.put(lcId, wp.getName());
                        lcId++;
                        ti.setLCDesc(lcId);
                        localization.put(lcId, "Waypoint");

                        tmpList.add(ti);
                    }

                    // Link up all icons to form a route
                    for(int a = 0; a < tmpList.size(); a++) {
                        if(a+1 < tmpList.size()) {
                            TranslatorIcon icon = tmpList.get(a);
                            icon.getTargets().add(tmpList.get(a+1).getId().intValue());
                        }
                    }

                    // Finally, add a link between starting position and first waypoint, if applicable
                    if(tmpList.size() > 0) {
                        TranslatorIcon ti = new TranslatorIcon(ug.getX(), ug.getY(), ug.getZ());
                        ti.setIconId(IconType.BALOON_BLUE.getCode());
                        lcId++;
                        ti.setLCName(lcId);
                        localization.put(lcId, ug.getName());
                        lcId++;
                        ti.setLCDesc(lcId);
                        ti.getTargets().add(tmpList.get(0).getId().intValue());
                        localization.put(lcId, "Waypoint");
                        tmpList.add(0, ti);

                        waypointIcons.addAll(tmpList);
                    }

                });


        unitGroupStream = rebuildStream(ussr, germany);
        Optional<UnitGroup> first = unitGroupStream.filter(ug -> ug.getAiLevel() == 0).findFirst();
        int xCountryId = 201;
        if(first.isPresent()) {
            UnitGroup playerUnitGroup = first.get();
            xCountryId = playerUnitGroup.getCountryCode();
        }
        final int countryId = xCountryId;



        // Generate unit icons
        unitGroupStream = rebuildStream(ussr, germany);
        List<TranslatorIcon> groundUnitIcons = unitGroupStream
                .filter(ug -> ug.isBriefingIcon() && ug.getGroupType() == GroupType.GROUND_GROUP)
                .map(ug -> {
                    TranslatorIcon ti = new TranslatorIcon(ug.getX(), ug.getY(), ug.getZ());
                    ti.setIconId(Util.getIconIdForVehicleType(VehicleType.valueOf(ug.getType()), ug.getCountryCode().intValue() == countryId));
                    lcId++;
                    ti.setLCName(lcId);
                    localization.put(lcId, ug.getName());
                    lcId++;
                    ti.setLCDesc(lcId);
                    localization.put(lcId, ug.getType());
                    return ti;
                })
                .collect(Collectors.toList());

        unitGroupStream = rebuildStream(ussr, germany);
        List<TranslatorIcon> planeUnitIcons = unitGroupStream
                .filter(ug -> ug.isBriefingIcon() && ug.getGroupType() == GroupType.AIR_GROUP)
                .map(ug -> {
                    TranslatorIcon ti = new TranslatorIcon(ug.getX(), ug.getY(), ug.getZ());
                    ti.setIconId(Util.getIconIdForPlaneType(PlaneType.valueOf(ug.getType()), ug.getCountryCode().intValue() == countryId));
                    lcId++;
                    ti.setLCName(lcId);
                    localization.put(lcId, ug.getName());
                    lcId++;
                    ti.setLCDesc(lcId);
                    localization.put(lcId, ug.getType());
                    return ti;
                })
                .collect(Collectors.toList());

        List<TranslatorIcon> staticUssrGroupUnitIcons = ussr.getStaticObjectGroups().stream()
                .filter(ug -> ug.isBriefingIcon())
                .map(ug -> {
                    TranslatorIcon ti = new TranslatorIcon(ug.getX(), ug.getY(), ug.getZ());
                    ti.setIconId(Util.getIconIdForStaticObject(ug.getType(), 101 == countryId));
                    lcId++;
                    ti.setLCName(lcId);
                    localization.put(lcId, ug.getName());
                    lcId++;
                    ti.setLCDesc(lcId);
                    localization.put(lcId, ug.getType());
                    return ti;
                })
                .collect(Collectors.toList());

        List<TranslatorIcon> staticGermanyGroupUnitIcons = germany.getStaticObjectGroups().stream()
                .filter(ug -> ug.isBriefingIcon())
                .map(ug -> {
                    TranslatorIcon ti = new TranslatorIcon(ug.getX(), ug.getY(), ug.getZ());
                    ti.setIconId(Util.getIconIdForStaticObject(ug.getType(), 201 == countryId));
                    lcId++;
                    ti.setLCName(lcId);
                    localization.put(lcId, ug.getName());
                    lcId++;
                    ti.setLCDesc(lcId);
                    localization.put(lcId, ug.getType());
                    return ti;
                })
                .collect(Collectors.toList());




        gm.getTranslatorIcons().addAll(waypointIcons);
        gm.getTranslatorIcons().addAll(groundUnitIcons);
        gm.getTranslatorIcons().addAll(planeUnitIcons);
        gm.getTranslatorIcons().addAll(staticUssrGroupUnitIcons);
        gm.getTranslatorIcons().addAll(staticGermanyGroupUnitIcons);
    }

    private void generateWaypoints(UnitGroup ug, ObjectGroup og, GeneratedMission gm) {
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
                if(ug.getGroupType() == GroupType.GROUND_GROUP && ug.getFormation().equals(FormationType.ON_ROAD_COLUMN)) {
                    CommandFormation cmdFormation = new CommandFormation(wp.getX(), wp.getY(), wp.getZ(), FormationType.ON_ROAD_COLUMN.getFormationCode(), 1);
                    cmdFormation.getObjects().add(og.getLeaderId());
                    Timer formationTimer = new Timer(wp.getX(), wp.getY(), wp.getZ());
                    formationTimer.getTargets().add(cmdFormation.getId().intValue());
                    formationTimer.setTime(10); // Wait 10 seconds
                    gm.getTimers().add(formationTimer);
                    gm.getFormationCommands().add(cmdFormation);
                    missionBegin.getTargets().add(formationTimer.getId().intValue());
                }

                // For air groups starting in air, issue a "loose wedge" FormationCommand after 10 seconds
                if(ug.getGroupType() == GroupType.AIR_GROUP && ug.getAiLevel() != 0 && ug.getY() > 299) {
                    CommandFormation cmdFormation = new CommandFormation(wp.getX(), wp.getY(), wp.getZ(), FormationType.VEE.getFormationCode(), 2);
                    cmdFormation.getObjects().add(og.getLeaderId());
                    Timer formationTimer = new Timer(wp.getX(), wp.getY(), wp.getZ());
                    formationTimer.getTargets().add(cmdFormation.getId().intValue());
                    formationTimer.setTime(13); // Wait 13 seconds
                    gm.getTimers().add(formationTimer);
                    gm.getFormationCommands().add(cmdFormation);
                    missionBegin.getTargets().add(formationTimer.getId().intValue());
                }


            }

            // Now, check if there is an interesting command on the waypoint.
            if(wp.getAction() != null) {
                // Generate applicable command
                switch(wp.getAction().getActionType()) {
                    case FLY:
                        // Make sure ground groups don't fly away ;) and keep within speed limits.
                        if(ug.getGroupType() == GroupType.GROUND_GROUP) {
                            eWp.setYPos(0.0f);
                            if(eWp.getSpeed() > 30) {
                                eWp.setSpeed(30);
                            }
                            eWp.setPriority(2); // MEDIUM priority, will try to engage stuff if possible
                        }
                        generatedWaypoints.add(eWp);
                        break;
                    case ATTACK_AREA:
                        // TODO Fix 0 0 1 flags

                        CommandAttackArea attackAreaCmd = new CommandAttackArea(
                                wp.getX(), wp.getY(), wp.getZ(), wp.getArea(), og.getLeaderId());
                        if (wp.getAction().getProperties().containsKey("time")) {
                            Integer time = Integer.parseInt(wp.getAction().getProperties().get("time"));
                            attackAreaCmd.setTime(time * 60); // Remember, BoS timers are in seconds, we specify this timeout in minutes.
                        }
                        if (wp.getAction().getProperties().containsKey("target_air") && Boolean.valueOf(wp.getAction().getProperties().get("target_air"))) {
                            attackAreaCmd.setAttackAir(1);
                        } else {
                            attackAreaCmd.setAttackAir(0);
                        }
                        if (wp.getAction().getProperties().containsKey("target_ground") && Boolean.valueOf(wp.getAction().getProperties().get("target_ground"))) {
                            attackAreaCmd.setAttackGround(1);
                        } else {
                            attackAreaCmd.setAttackGround(0);
                        }
                        if (wp.getAction().getProperties().containsKey("target_gtargets") && Boolean.valueOf(wp.getAction().getProperties().get("target_gtargets"))) {
                            attackAreaCmd.setAttackGTargets(1);
                        } else {
                            attackAreaCmd.setAttackGTargets(0);
                        }

                        attackAreaCmd.setPriority(wp.getPriority());

                        gm.getAreaAttackCommands().add(attackAreaCmd);
                        eWp.getTargets().clear();
                        eWp.getTargets().add(attackAreaCmd.getId().intValue());

                        generatedWaypoints.add(eWp);

                        break;
                    case COVER:

                        // We must know the targetId for the group to be covered
                        ObjectGroup targetObjectGroup = groupMap.get(wp.getAction().getTargetClientId());

                        if(wp.getAction().getTargetClientId() == null) {
                            String msg = "Unit " + ug.getName() + "(" + ug.getType() + ") has a COVER command at waypoint (" + (a+1) + ") without cover target. Please correct.";
                            log.error(msg);
                            throw new RuntimeException(msg);
                        }

                        // Next, figure out the generated targetId for its entity.
                        CommandCover coverCmd = new CommandCover(
                                wp.getX(), wp.getY(), wp.getZ(), og.getLeaderId(), targetObjectGroup.getLeaderId());
                        coverCmd.setPriority(wp.getPriority());
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
                            attackCmd.setPriority(wp.getPriority());
                            gm.getAttackTargetCommands().add(attackCmd);

                            // Add attack target to waypoint target list. Or should this be done on the previous waypoint??
                            // TODO How do we resume route after command has completed? We use the OnEvent mechanism on the target group

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
                        
                        final ClientAirfield field = findClosestAirfield(eWp.getXPos(), eWp.getZPos());
                        // B: Use the waypoint position and find the closest airfield and add it.
                        Airfield startAirfield = new Airfield(field.getX(), field.getY(), field.getZ(), field.getName());

                        // Only add once if there are several flights using same airfield.
                        if(gm.getAirfieldEntities().stream().filter(ae -> ae.getName().equals(field.getName())).count() == 0) {
                            gm.getAirfieldEntities().add(startAirfield);
                        }
                        
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





                        attackCompleteTimer.getTargets().add(subtitle.getId().intValue());
                        gm.getTranslatorSubtitles().add(subtitle);

                        gm.getTimers().add(attackCompleteTimer);

                        wpIter.remove();
                        a++;
                        continue;
                    }
                }
            }

            // Test, add subtitle for planes of same coalition
//            if(ug.getGroupType() == GroupType.AIR_GROUP && ug.getCountryCode().equals(playerGroup.getCountryCode())) {
//                String text = ug.getName() + " (" + ug.getType() + ") has reached waypoint " + (a+1);
//                lcId++;
//                TranslatorSubtitle subtitle = new TranslatorSubtitle(wayPoint.getXPos(), wayPoint.getYPos(), wayPoint.getZPos(), lcId);
//                subtitle.setDuration(10);
//                localization.put(lcId, text);
//                wayPoint.getTargets().add(subtitle.getId().intValue());
//                gm.getTranslatorSubtitles().add(subtitle);
//            }
            lastWaypoint = wayPoint;
            a++;
        }

        gm.getWayPoints().addAll(generatedWaypoints);
    }

    private GameEntity findGameEntity(Integer entityTrId, GeneratedMission gm) {
        Optional<ObjectGroup> first = gm.getObjectGroups().stream().filter(og -> og.getLeader().getMCU_TR_Entity().getId().intValue() == entityTrId.intValue()).findFirst();
        return first.get().getLeader();
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
        mo.setMissionType(0); //cm.getMissionType());
        mo.setDate(cm.getDate());
        mo.setTime(cm.getTime());
        mo.setLCName(lcId); // Mission title, for briefing
        localization.put(lcId, cm.getName());
        lcId++; // 1
        mo.setLCDesc(lcId); // Mission briefing text
        localization.put(lcId, cm.getDescription());

        if (cm.getMissionType().intValue() == 0) {
            PlaneType planeType = PlaneType.valueOf(playerUnitGroup.getType());
            mo.setPlayerConfig(planeType.getScript());
        }

        applyWeather(cm, mo);
        applyMap(cm, mo);
        return mo;
    }

    private void applyMap(ClientMission cm, MissionOptions mo) {
        if (cm.getMapType() == null) {
           cm.setMapType(MapType.STALINGRAD_WINTER);
        }

        switch(cm.getMapType()) {
            case STALINGRAD_SUMMER:
            case STALINGRAD_AUTUMN:
                mo.setGuiMap(SeasonConstants.STALINGRAD_S_GuiMap);
                mo.setHMap(SeasonConstants.STALINGRAD_S_HMap);
                mo.setForests(SeasonConstants.STALINGRAD_S_Forests);
                mo.setTextures(SeasonConstants.STALINGRAD_S_Textures);
                mo.setSeasonPrefix(SeasonConstants.SUMMER_SeasonPrefix);
                break;
            case STALINGRAD_WINTER:
                mo.setGuiMap(SeasonConstants.STALINGRAD_W_GuiMap);
                mo.setHMap(SeasonConstants.STALINGRAD_W_HMap);
                mo.setForests(SeasonConstants.STALINGRAD_W_Forests);
                mo.setTextures(SeasonConstants.STALINGRAD_W_Textures);
                mo.setSeasonPrefix(SeasonConstants.WINTER_SeasonPrefix);
                break;
            default:
                mo.setGuiMap(SeasonConstants.STALINGRAD_W_GuiMap);
                mo.setHMap(SeasonConstants.STALINGRAD_W_HMap);
                mo.setForests(SeasonConstants.STALINGRAD_W_Forests);
                mo.setTextures(SeasonConstants.STALINGRAD_W_Textures);
                mo.setSeasonPrefix(SeasonConstants.WINTER_SeasonPrefix);
        }
    }

    private void applyWeather(ClientMission cm, MissionOptions mo) {
        // A little hack to make sure a summer cloudconfig is used if not properly set, game look blue otherwise...
        if (cm.getMapType() == MapType.STALINGRAD_SUMMER && !cm.getWeather().getCloudConfig().toLowerCase().contains("summer")) {
            cm.getWeather().setCloudConfig("summer\\01_Light_03\\sky.ini");
        }
        mo.setCloudConfig(cm.getWeather().getCloudConfig());
        mo.setCloudHeight(cm.getWeather().getCloudHeight());
        mo.setCloudLevel(cm.getWeather().getCloudLevel());
        mo.setPrecLevel(cm.getWeather().getPrecLevel());
        mo.setPrecType(cm.getWeather().getPrecType());
        mo.setTemperature(cm.getWeather().getTemperature());
        mo.setTurbulence(cm.getWeather().getTurbulence());
        mo.setWindLayers(cm.getWeather().getWindLayers());
    }

    private void addKilledMessageToGroundVehicle(List<ObjectGroup> vehicleGroups, GeneratedMission gm) {

        vehicleGroups.stream().forEach( og -> {
            og.getObjects().stream().forEach(o -> {
                Vehicle v = (Vehicle) o;
                lcId++;
                TranslatorSubtitle tgtKilledSub = new TranslatorSubtitle(v.getXPos(), v.getYPos(), v.getZPos(), lcId);
                localization.put(lcId, "Target " + v.getName() + " killed");
                OnEvent destroyedEvent = new OnEvent(EventType.ON_KILLED.getEventId(), tgtKilledSub.getId().intValue());
                v.getMCU_TR_Entity().getOnEvents().add(destroyedEvent);
                gm.getTranslatorSubtitles().add(tgtKilledSub);

                lcId++;
                TranslatorSubtitle tgtDamagedSub = new TranslatorSubtitle(v.getXPos(), v.getYPos(), v.getZPos(), lcId);
                localization.put(lcId, "Target " + v.getName() + " damaged");
                OnEvent damagedEvent = new OnEvent(EventType.ON_DAMAGED.getEventId(), tgtDamagedSub.getId().intValue());
                v.getMCU_TR_Entity().getOnEvents().add(damagedEvent);
                gm.getTranslatorSubtitles().add(tgtDamagedSub);
            });

        });


    }
}
