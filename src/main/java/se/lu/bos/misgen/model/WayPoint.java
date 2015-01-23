package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 MCU_Waypoint
 {
 Index = 1654;
 Name = "Trigger Waypoint";
 Desc = "Hinflug1 Falke";
 Targets = [1655];
 Objects = [1651];     // This is the Entity ID of the lead plane
 XPos = 101389.438;
 YPos = 1200.000;
 ZPos = 109207.327;
 XOri = 0.00;
 YOri = 354.62;
 ZOri = 0.00;
 Area = 200;
 Speed = 400;
 Priority = 1;
 }
 */
public class WayPoint extends GameEntity {

    private Integer Area = 200;
    private Integer Speed = 400;
    private Integer Priority = 1;

    private Timer timer;

    public WayPoint(float x, float y, float z, Integer area, Integer speed, Integer priority) {
        super.setId(GlobalId.nextLong());
        super.setName("Trigger Waypoint");
        super.setXPos(x);
        super.setYPos(y);
        super.setZPos(z);
        this.Area = area;
        this.Speed = speed;
        this.Priority = priority;
        this.timer = new Timer(x, y, z);
    }

    public Integer getArea() {
        return Area;
    }

    public void setArea(Integer area) {
        Area = area;
    }

    public Integer getSpeed() {
        return Speed;
    }

    public void setSpeed(Integer speed) {
        Speed = speed;
    }

    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "MCU_Waypoint\r\n" +
                "{\r\n" +
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
            "  Area = " + Area + ";\r\n" +
            "  Speed = " + Speed + ";\r\n" +
            "  Priority = " + Priority + ";\r\n" +
            "}\r\n";
    }
}
