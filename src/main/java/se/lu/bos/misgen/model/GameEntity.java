package se.lu.bos.misgen.model;

import se.lu.bos.misgen.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-27
 * Time: 00:26
 * To change this template use File | Settings | File Templates.
 */
public abstract class GameEntity extends BaseEntity {

    protected String Name = "Rabe";
    //protected Integer Index = 1622;
    protected String Desc = "";

    // Use Targets to group wingmen to flight leader. Note that
    // its the Index of the flight leader's MCU_TR_Entity one uses, not the
    // Index of the Plane itself.
    private List<Integer> Targets = new ArrayList<>();// [1649,1626,1722];
    private List<Integer> Objects = new ArrayList<>();
    protected Float XPos = 95946.699f;  // North/South
    protected Float YPos = 85.608f;     // Height
    protected Float ZPos = 50313.633f;  // East/West
    protected Float XOri = 0.00f;
    protected Float YOri = 0.0f;      // Heading of nose
    protected Float ZOri = 0.00f;

    protected MCUTREntity MCU_TR_Entity;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

//    public Integer getIndex() {
//        return Index;
//    }
//
//    public void setIndex(Integer index) {
//        Index = index;
//    }

    public Float getXPos() {
        return XPos;
    }

    public void setXPos(Float XPos) {
        this.XPos = XPos;
    }

    public Float getYPos() {
        return YPos;
    }

    public void setYPos(Float YPos) {
        this.YPos = YPos;
    }

    public Float getZPos() {
        return ZPos;
    }

    public void setZPos(Float ZPos) {
        this.ZPos = ZPos;
    }

    public Float getXOri() {
        return XOri;
    }

    public void setXOri(Float XOri) {
        this.XOri = XOri;
    }

    public Float getYOri() {
        return YOri;
    }

    public void setYOri(Float YOri) {
        this.YOri = YOri;
    }

    public Float getZOri() {
        return ZOri;
    }

    public void setZOri(Float ZOri) {
        this.ZOri = ZOri;
    }

    public MCUTREntity getMCU_TR_Entity() {
        return MCU_TR_Entity;
    }

    public void setMCU_TR_Entity(MCUTREntity MCU_TR_Entity) {
        this.MCU_TR_Entity = MCU_TR_Entity;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public List<Integer> getTargets() {
        return Targets;
    }

    public void setTargets(List<Integer> targets) {
        Targets = targets;
    }

    public List<Integer> getObjects() {
        return Objects;
    }

    public void setObjects(List<Integer> objects) {
        Objects = objects;
    }


    @Override
    public String toString() {
        return
                "  Name = \"" + Name + "\";\r\n" +
                "  Index = " + getId() + ";\r\n" +
                "  Desc = \"" + Desc + "\";\r\n" +
                "  Targets = " + Util.toArrStr(Targets) + ";\r\n" +
                "  Objects = " + Util.toArrStr(Objects) + ";\r\n" +
                "  XPos = " + XPos + ";\r\n" +
                "  YPos = " + YPos + ";\r\n" +
                "  ZPos = " + ZPos + ";\r\n" +
                "  XOri = " + XOri + ";\r\n" +
                "  YOri = " + YOri + ";\r\n" +
                "  ZOri = " + ZOri + ";\r\n";
    }
}
