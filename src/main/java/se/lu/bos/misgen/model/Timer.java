package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-03
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class Timer extends GameEntity {

    private Integer Time;
    private Integer Random;

    public Timer(float x, float y, float z) {
        setId(GlobalId.nextLong());
        setName("Trigger Timer");
        setXPos(x);
        setYPos(y);
        setZPos(z);
        setRandom(100);
        setTime(0);
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
    }

    public Integer getRandom() {
        return Random;
    }

    public void setRandom(Integer random) {
        Random = random;
    }

    @Override
    public String toString() {
        return   "MCU_Timer\r\n{\r\n" +
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
                    "  Time = " + Time + ";\r\n" +
                    "  Random = " + Random + ";\r\n" +
                    "}\r\n";
    }
}
