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
 */
public class CommandAttackArea extends GameEntity {

    private Integer AttackGround = 1;
    private Integer AttackAir = 0;
    private Integer AttackGTargets = 0;
    private Integer AttackArea = 3000;
    private Integer Time = 600;
    private Integer Priority = 1;

    public CommandAttackArea(Float xPos, Float yPos, Float zPos, int areaSize, int attackGround, int attackAir, int attackGTargets, GameEntity executingObject) {
        setId(GlobalId.nextLong());
        setName("command AttackArea");
        setXPos(xPos);
        setYPos(yPos);
        setZPos(zPos);
        setAttackArea(areaSize);
        setAttackGround(attackGround);
        setAttackAir(attackAir);
        setAttackGTargets(attackGTargets);
        getObjects().add(executingObject.getMCU_TR_Entity().getId().intValue());
    }

    public CommandAttackArea(Float xPos, Float yPos, Float zPos, int areaSize, int attackGround, int attackAir, int attackGTargets, Integer executingObjectId) {
        setId(GlobalId.nextLong());
        setName("command AttackArea");
        setXPos(xPos);
        setYPos(yPos);
        setZPos(zPos);
        setAttackArea(areaSize);
        setAttackGround(attackGround);
        setAttackAir(attackAir);
        setAttackGTargets(attackGTargets);
        getObjects().add(executingObjectId);
    }

    public Integer getAttackGround() {
        return AttackGround;
    }

    public void setAttackGround(Integer attackGround) {
        AttackGround = attackGround;
    }

    public Integer getAttackAir() {
        return AttackAir;
    }

    public void setAttackAir(Integer attackAir) {
        AttackAir = attackAir;
    }

    public Integer getAttackGTargets() {
        return AttackGTargets;
    }

    public void setAttackGTargets(Integer attackGTargets) {
        AttackGTargets = attackGTargets;
    }

    public Integer getAttackArea() {
        return AttackArea;
    }

    public void setAttackArea(Integer attackArea) {
        AttackArea = attackArea;
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
    }

    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    @Override
    public String toString() {
        return  "MCU_CMD_AttackArea\r\n{\r\n" +
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
                "  AttackGround = " + AttackGround + ";\r\n" +
                "  AttackAir = " + AttackAir + ";\r\n" +
                "  AttackGTargets = " + AttackGTargets + ";\r\n" +
                "  AttackArea = " + AttackArea + ";\r\n" +
                "  Time = " + Time + ";\r\n" +
                "  Priority = " + Priority + ";\r\n" +
                "}\r\n";
    }
}
