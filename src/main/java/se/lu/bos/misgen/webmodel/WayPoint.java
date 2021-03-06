package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: eriklupander
 * Date: 15-01-19
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class WayPoint extends MapObject {

    private Integer speed = 300;
    private String name;
    private Long clientId;
    // Back-reference to owning unit
    private Long unitGroupClientId;

    private WaypointAction action = new WaypointAction();
    private Integer priority = 1;
    private Integer area = 500;

    // Experimental Time on Target
    private Integer timeOffset;

    public WayPoint() {
        super.setObjectType(MapObjectType.WAYPOINT);
        this.action = new WaypointAction();
        this.action.setActionType(ActionType.FLY);
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUnitGroupClientId() {
        return unitGroupClientId;
    }

    public void setUnitGroupClientId(Long unitGroupClientId) {
        this.unitGroupClientId = unitGroupClientId;
    }

    public WaypointAction getAction() {
        return action;
    }

    public void setAction(WaypointAction action) {
        this.action = action;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(Integer timeOffset) {
        this.timeOffset = timeOffset;
    }
}
