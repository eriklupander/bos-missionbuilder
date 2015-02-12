package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: eriklupander
 * Date: 15-01-19
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public class WaypointAction {
    private ActionType actionType;
    private Long targetClientId;

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Long getTargetClientId() {
        return targetClientId;
    }

    public void setTargetClientId(Long targetClientId) {
        this.targetClientId = targetClientId;
    }
}
