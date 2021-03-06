package se.lu.bos.misgen.webmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: eriklupander
 * Date: 15-01-19
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class ClientMission {
    private String name;
    private String description;
    private String date;
    private String time;

    private String serverId;
    private Integer clientId;

    private Map<Integer, Side> sides = new HashMap<>();

    private List<TriggerZone> triggerZones = new ArrayList<>();

    private Boolean generateAAAAtAirfields = true;
    private Boolean generateAAAAtBridges = false;
    private boolean includeStalingradCity = true;

    private Weather weather;

    private Integer missionType = 0; // 3=Training (0=Single, 1=Cooperative, 2=Dogfight)

    private List<Effect> effects = new ArrayList<>();

    private MapType mapType;

    private ClientMission() {
        sides.put(101, new Side("USSR"));
        sides.put(201, new Side("Germany"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Map<Integer, Side> getSides() {
        return sides;
    }

    public void setSides(Map<Integer, Side> sides) {
        this.sides = sides;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public List<TriggerZone> getTriggerZones() {
        return triggerZones;
    }

    public void setTriggerZones(List<TriggerZone> triggerZones) {
        this.triggerZones = triggerZones;
    }

    public Boolean getGenerateAAAAtAirfields() {
        return generateAAAAtAirfields;
    }

    public void setGenerateAAAAtAirfields(Boolean generateAAAAtAirfields) {
        this.generateAAAAtAirfields = generateAAAAtAirfields;
    }

    public Boolean getGenerateAAAAtBridges() {
        return generateAAAAtBridges;
    }

    public void setGenerateAAAAtBridges(Boolean generateAAAAtBridges) {
        this.generateAAAAtBridges = generateAAAAtBridges;
    }

    public boolean getIncludeStalingradCity() {
        return includeStalingradCity;
    }

    public boolean isIncludeStalingradCity() {
        return includeStalingradCity;
    }

    public void setIncludeStalingradCity(boolean includeStalingradCity) {
        this.includeStalingradCity = includeStalingradCity;
    }

    public Weather getWeather() {
        if(weather == null) {
            weather = new Weather();
        }
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public Integer getMissionType() {
        return missionType;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }
}
