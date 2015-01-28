package se.lu.bos.misgen.webmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eriklupander
 * Date: 15-01-19
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class UnitGroup extends MapObject {

    private String groupType;
    private Integer size;
    private Integer countryCode = -1;
    private String name;
    private String description;
    private String type;

    private boolean startsInAir;
    private Integer loadout = 0;
    private Integer aiLevel = 2;

    private List<WayPoint> waypoints = new ArrayList<WayPoint>();
    private String serverId;

    private FormationType formation = FormationType.LINE;

    public UnitGroup() {
        super.setObjectType(MapObjectType.UNIT_GROUP);
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStartsInAir() {
        return startsInAir;
    }

    public void setStartsInAir(boolean startsInAir) {
        this.startsInAir = startsInAir;
    }

    public Integer getLoadout() {
        return loadout;
    }

    public void setLoadout(Integer loadout) {
        this.loadout = loadout;
    }

    public Integer getAiLevel() {
        return aiLevel;
    }

    public void setAiLevel(Integer aiLevel) {
        this.aiLevel = aiLevel;
    }

    public List<WayPoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<WayPoint> waypoints) {
        this.waypoints = waypoints;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerId() {
        return serverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FormationType getFormation() {
        return formation;
    }

    public void setFormation(FormationType formation) {
        this.formation = formation;
    }
}
