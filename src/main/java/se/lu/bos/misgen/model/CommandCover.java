package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**

 OBJECT: Which group that should cover the target
 TARGET: WHAT TO COVER
 */
public class CommandCover extends GameEntity {
    private Integer CoverGroup = 1; //cover the group yes=1/net=0
    private Integer GoalType = 0; //goal type, affects icon type in GUI
    private Integer Priority = 1; //command priority

    public CommandCover(GameEntity executingObject, GameEntity targetObject) {
        setId(GlobalId.nextLong());
        setName("command Cover");
        setXPos(executingObject.getXPos());
        setYPos(executingObject.getYPos());
        setZPos(executingObject.getZPos());
        getObjects().add(executingObject.getMCU_TR_Entity().getId().intValue());
        getTargets().add(targetObject.getMCU_TR_Entity().getId().intValue());
    }

    public CommandCover(Float x, Float y, Float z, Integer executingObjectId, Integer targetObjectId) {
        setId(GlobalId.nextLong());
        setName("command Cover");
        setXPos(x);
        setYPos(y);
        setZPos(z);
        getObjects().add(executingObjectId);
        getTargets().add(targetObjectId);
    }

    public Integer getCoverGroup() {
        return CoverGroup;
    }

    public void setCoverGroup(Integer coverGroup) {
        CoverGroup = coverGroup;
    }

    public Integer getGoalType() {
        return GoalType;
    }

    public void setGoalType(Integer goalType) {
        GoalType = goalType;
    }

    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    @Override
    public String toString() {
        return   "MCU_CMD_Cover\r\n{\r\n" +
                "  Index = " + getId() + ";\r\n" +
                "  Name = \"" + Name + "\";\r\n" +
                "  Desc = \"" + Desc + "\";\r\n" +
                "  Targets = " + Util.toArrStr(getTargets()) + ";\r\n" +
                "  Objects = " + Util.toArrStr(getObjects()) + ";\r\n" +
                "  XPos = " + XPos + ";\r\n" +
                "  YPos = " + YPos + ";\r\n" +
                "  ZPos = " + ZPos + ";\r\n" +
                "  XOri = " + XOri + ";\r\n" +
                "  YOri = " + YOri + ";\r\n" +
                "  ZOri = " + ZOri + ";\r\n" +
                "  CoverGroup = " + CoverGroup + ";\r\n" +
              //  "  GoalType = " + GoalType + ";\r\n" +
                "  Priority = " + Priority + ";\r\n" +
                "}\r\n";
    }
}
