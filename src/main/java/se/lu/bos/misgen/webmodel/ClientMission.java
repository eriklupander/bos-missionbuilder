package se.lu.bos.misgen.webmodel;

import java.util.ArrayList;
import java.util.List;

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

    private Side axis = new Side("Germany");
    private Side allies = new Side("USSR");

    private List<TriggerZone> triggerZones = new ArrayList<>();

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

    public Side getAxis() {
        return axis;
    }

    public void setAxis(Side axis) {
        this.axis = axis;
    }

    public Side getAllies() {
        return allies;
    }

    public void setAllies(Side allies) {
        this.allies = allies;
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
}
