package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-01
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
public class CommandTakeOff  extends GameEntity {

    public CommandTakeOff(float x, float y, float z, Integer gameEntityToStartId) {
        setId(GlobalId.nextLong());
        setXPos(x);
        setYPos(y);
        setZPos(z);
        getObjects().add(gameEntityToStartId);
    }

    public CommandTakeOff(float x, float y, float z, GameEntity gameEntityToStart) {
        setId(GlobalId.nextLong());
        setXPos(x);
        setYPos(y);
        setZPos(z);
        getObjects().add(gameEntityToStart.getMCU_TR_Entity().getId().intValue());
    }



    @Override
    public String toString() {
        return  "MCU_CMD_TakeOff\r\n{\r\n" +

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
                "}\r\n";
    }
}
