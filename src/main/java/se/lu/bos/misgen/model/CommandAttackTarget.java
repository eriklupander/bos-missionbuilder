package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 MCU_CMD_AttackArea
 {
 Index = 2036;
 Name = "command AttackArea";
 Desc = "";
 Targets = [];
 Objects = [2022];
 XPos = 113631.766;
 YPos = 77.030;
 ZPos = 135949.767;
 XOri = 0.00;
 YOri = 90.21;
 ZOri = 0.00;
 AttackGround = 1;
 AttackAir = 0;
 AttackGTargets = 0;
 AttackArea = 1000;
 Time = 600;
 Priority = 1;
 }
 MCU_CMD_AttackTarget //MCU type
 {
 Index = 5; //MCU ID
 Name = "command Attack"; // MCU name (not displayed in GUI)
 Desc = ""; // MCU description (not displayed in GUI)
 Targets = []; // objects’ ID that this command points at with TL
 Objects = []; // objects’ ID that this command points at with OL
 XPos = 2283.32; // Х axis coordinates
 YPos = 119.646; // Y axis coordinates
 ZPos = 4280.93; // Z axis coordinates
 XOri = 0;
 YOri = 0;
 ZOri = 0;
 AttackGroup = 1; //attack group (if TL is pointed at the group leader)
 GoalType = 0; //goal type, affects how icon is displayed in GUI
 Priority = 1; //priority settings for command execution
 }
 */
public class CommandAttackTarget extends GameEntity {

    private Integer AttackGroup = 1;
    private Integer GoalType = 0;
    private Integer Priority = 1;

    public CommandAttackTarget(Float xPos, Float yPos, Float zPos, int attackGroup, GameEntity executingObject, GameEntity targetObject) {
        setId(GlobalId.nextLong());
        setName("command Attack");
        setXPos(xPos);
        setYPos(yPos);
        setZPos(zPos);
        setAttackGroup(attackGroup);
        getObjects().add(executingObject.getMCU_TR_Entity().getId().intValue());
        getTargets().add(targetObject.getMCU_TR_Entity().getId().intValue());
    }

    public CommandAttackTarget(Float xPos, Float yPos, Float zPos, int attackGroup, Integer executingObjectId, Integer targetObjectId) {
        setId(GlobalId.nextLong());
        setName("command Attack");
        setXPos(xPos);
        setYPos(yPos);
        setZPos(zPos);
        setAttackGroup(attackGroup);
        getObjects().add(executingObjectId);
        getTargets().add(targetObjectId);
    }

    public Integer getAttackGroup() {
        return AttackGroup;
    }

    public void setAttackGroup(Integer attackGroup) {
        AttackGroup = attackGroup;
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
        return  "MCU_CMD_AttackTarget\r\n{\r\n" +
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
                "  AttackGroup = " + AttackGroup + ";\r\n" +
                "  Priority = " + Priority + ";\r\n" +
                "}\r\n";
    }
}
