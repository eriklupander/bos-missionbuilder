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
}
