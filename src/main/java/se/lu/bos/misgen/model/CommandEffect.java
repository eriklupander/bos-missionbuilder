package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 MCU_CMD_Effect
 {
 Index = 2366;
 Name = "Command Effect";
 Desc = "";
 Targets = [];
 Objects = [2361,2363,2343,2349,2365,2347,2355,2353,2345,2359,2351,2357];
 XPos = 95506.103;
 YPos = 84.078;
 ZPos = 50710.147;
 XOri = 0.00;
 YOri = 0.00;
 ZOri = 0.00;
 ActionType = 0;
 }

 Add Object links to MCU_Entity of Effect
 */
public class CommandEffect  extends GameEntity {

    private Integer ActionType = 0;

    public CommandEffect(Float xPos, Float yPos, Float zPos) {
        setId(GlobalId.nextLong());
        setName("Command Effect");
        setXPos(xPos);
        setYPos(yPos);
        setZPos(zPos);
    }

    public Integer getActionType() {
        return ActionType;
    }

    public void setActionType(Integer actionType) {
        ActionType = actionType;
    }

    @Override
    public String toString() {
        return  "MCU_CMD_Effect\r\n{\r\n" +
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
                "  ActionType = " + ActionType + ";\r\n" +
                "}\r\n";
    }
}
