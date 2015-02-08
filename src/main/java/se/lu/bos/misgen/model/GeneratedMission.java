package se.lu.bos.misgen.model;

import se.lu.bos.misgen.groups.GroupEntity;
import se.lu.bos.misgen.helper.ObjectGroup;

import javax.print.attribute.AttributeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-05
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
public class GeneratedMission {

    /* Use these to limit the number of cities, bridges and railway stations to include in the mission by
        geographical bounds. Remember - the coordinate system in BoS seems to use X as vertical starting from south and
        Z as horizontal starting in the WEST.
     */
    private Float boundaryLowerX, boundaryUpperX, boundaryLowerZ, boundaryUpperZ;

    private MissionOptions missionOptions;
    private List<Timer> timers = new ArrayList<>();
    private List<ObjectGroup> objectGroups = new ArrayList<>();
    private List<StaticObject> staticObjects = new ArrayList<>();

    private List<WayPoint> wayPoints = new ArrayList<>();
    private List<Effect> effects = new ArrayList<>();

    private List<TranslatorMissionBegin> translatorMissionBegins = new ArrayList<>();
    private List<TranslatorSubtitle> translatorSubtitles = new ArrayList<>();
    private List<TranslatorIcon> translatorIcons = new ArrayList<>();

    private List<CommandAttackArea> areaAttackCommands = new ArrayList<>();
    private List<CommandAttackTarget> attackTargetCommands = new ArrayList<>();
    private List<CommandCover> coverCommands = new ArrayList<>();
    private List<CommandTakeOff> takeOffCommands = new ArrayList<>();
    private List<CommandLand> landCommands = new ArrayList<>();

    private List<GroupEntity> airfields = new ArrayList<>();
    private List<GroupEntity> bridges = new ArrayList<>();
    private List<GroupEntity> towns = new ArrayList<>();
    private List<GroupEntity> railwayStations = new ArrayList<>();

    // Means Airfield {..} entties with MCU_TR_Entity attached. Useful for AI landing or something
    private List<Airfield> airfieldEntities = new ArrayList<>();

    private Map<Integer, String> localization;

    public Map<Integer, String> getLocalization() {
        return localization;
    }

    public void setLocalization(Map<Integer, String> localization) {
        this.localization = localization;
    }

    public Float getBoundaryLowerX() {
        return boundaryLowerX;
    }

    public void setBoundaryLowerX(Float boundaryLowerX) {
        this.boundaryLowerX = boundaryLowerX;
    }

    public Float getBoundaryUpperX() {
        return boundaryUpperX;
    }

    public void setBoundaryUpperX(Float boundaryUpperX) {
        this.boundaryUpperX = boundaryUpperX;
    }

    public Float getBoundaryLowerZ() {
        return boundaryLowerZ;
    }

    public void setBoundaryLowerZ(Float boundaryLowerZ) {
        this.boundaryLowerZ = boundaryLowerZ;
    }

    public Float getBoundaryUpperZ() {
        return boundaryUpperZ;
    }

    public void setBoundaryUpperZ(Float boundaryUpperZ) {
        this.boundaryUpperZ = boundaryUpperZ;
    }

    public MissionOptions getMissionOptions() {
        return missionOptions;
    }

    public void setMissionOptions(MissionOptions missionOptions) {
        this.missionOptions = missionOptions;
    }

    public List<Timer> getTimers() {
        return timers;
    }

    public void setTimers(List<Timer> timers) {
        this.timers = timers;
    }

    public List<ObjectGroup> getObjectGroups() {
        return objectGroups;
    }

    public void setObjectGroups(List<ObjectGroup> objectGroups) {
        this.objectGroups = objectGroups;
    }

    public List<WayPoint> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<TranslatorMissionBegin> getTranslatorMissionBegins() {
        return translatorMissionBegins;
    }

    public void setTranslatorMissionBegins(List<TranslatorMissionBegin> translatorMissionBegins) {
        this.translatorMissionBegins = translatorMissionBegins;
    }

    public List<TranslatorSubtitle> getTranslatorSubtitles() {
        return translatorSubtitles;
    }

    public void setTranslatorSubtitles(List<TranslatorSubtitle> translatorSubtitles) {
        this.translatorSubtitles = translatorSubtitles;
    }

    public List<TranslatorIcon> getTranslatorIcons() {
        return translatorIcons;
    }

    public void setTranslatorIcons(List<TranslatorIcon> translatorIcons) {
        this.translatorIcons = translatorIcons;
    }

    public List<CommandAttackArea> getAreaAttackCommands() {
        return areaAttackCommands;
    }

    public void setAreaAttackCommands(List<CommandAttackArea> areaAttackCommands) {
        this.areaAttackCommands = areaAttackCommands;
    }

    public List<CommandCover> getCoverCommands() {
        return coverCommands;
    }

    public void setCoverCommands(List<CommandCover> coverCommands) {
        this.coverCommands = coverCommands;
    }

    public List<CommandLand> getLandCommands() {
        return landCommands;
    }

    public void setLandCommands(List<CommandLand> landCommands) {
        this.landCommands = landCommands;
    }

    public List<GroupEntity> getAirfields() {
        return airfields;
    }

    public void setAirfields(List<GroupEntity> airfields) {
        this.airfields = airfields;
    }

    public List<GroupEntity> getBridges() {
        return bridges;
    }

    public void setBridges(List<GroupEntity> bridges) {
        this.bridges = bridges;
    }

    public List<GroupEntity> getTowns() {
        return towns;
    }

    public void setTowns(List<GroupEntity> towns) {
        this.towns = towns;
    }

    public List<GroupEntity> getRailwayStations() {
        return railwayStations;
    }

    public void setRailwayStations(List<GroupEntity> railwayStations) {
        this.railwayStations = railwayStations;
    }

    public List<CommandAttackTarget> getAttackTargetCommands() {
        return attackTargetCommands;
    }

    public void setAttackTargetCommands(List<CommandAttackTarget> attackTargetCommands) {
        this.attackTargetCommands = attackTargetCommands;
    }

    public List<CommandTakeOff> getTakeOffCommands() {
        return takeOffCommands;
    }

    public void setTakeOffCommands(List<CommandTakeOff> takeOffCommands) {
        this.takeOffCommands = takeOffCommands;
    }

    public List<Airfield> getAirfieldEntities() {
        return airfieldEntities;
    }

    public void setAirfieldEntities(List<Airfield> airfieldEntities) {
        this.airfieldEntities = airfieldEntities;
    }

    public List<StaticObject> getStaticObjects() {
        return staticObjects;
    }

    public void setStaticObjects(List<StaticObject> staticObjects) {
        this.staticObjects = staticObjects;
    }
}
