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

    private WaypointAction action = new WaypointAction();

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

    public WaypointAction getAction() {
        return action;
    }

    public void setAction(WaypointAction action) {
        this.action = action;
    }
}
