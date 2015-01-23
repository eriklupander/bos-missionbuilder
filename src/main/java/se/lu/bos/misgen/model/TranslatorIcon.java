package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**

 MCU_Icon //MCU type
 {
 Index = 19; //MCU ID
 Targets = [];// Objects’ ID that this translator points at with OL
 Objects = [];// Objects’ ID that this translator points at with OL, this translator can’t have any OL
 XPos = 2045.03; // X axis coordinate
 YPos = 112.067; // Y axis coordinate
 ZPos = 4283.91; // Z axis coordinate
 XOri = 0; //orientation (doesn’t have any functions in a mission)
 YOri = 0; //orientation (doesn’t have any functions in a mission)
 ZOri = 0; //orientation (doesn’t have any functions in a mission)
 Enabled = 1; //is not used through the Editor’s interface
 LCName = 3; //index of localization line in localization file of the mission. Will be displayed in GUI as
 icon name
 LCDesc = 4; //index of localization line in localization file of the mission. Will be displayed in GUI as
 icon description (window will pop-up when you move mouse over it)
 OppositeAirplanes = 1; //will be visible only for Order team
 99
 OrderAirplanes = 1; // will be visible only for Opposite team
 IconId = 0; //icon type
 RColor = 255; //red color component
 GColor = 255; // green color component
 BColor = 255; // blue color component
 LineType = 0; //line type
 }

 */
public class TranslatorIcon extends GameEntity {

    private Integer Enabled = 1; //is not used through the Editor’s interface
    private Integer LCName = 3; //index of localization line in localization file of the mission. Will be displayed in GUI as
    // icon name
    private Integer LCDesc = 4; //index of localization line in localization file of the mission. Will be displayed in GUI as
    // icon description (window will pop-up when you move mouse over it)
    private Integer OppositeAirplanes = 1; //will be visible only for Order team

    private Integer  OrderAirplanes = 1; // will be visible only for Opposite team
    private Integer  IconId = 0; //icon type
    private Integer  RColor = 255; //red color component
    private Integer GColor = 25; // green color component
    private Integer  BColor = 25; // blue color component
    private Integer LineType = 0; //line type

    public TranslatorIcon(float x, float y, float z) {
        setId(GlobalId.nextLong());
        setXPos(x);
        setYPos(y);
        setZPos(z);
    }

    public Integer getEnabled() {
        return Enabled;
    }

    public void setEnabled(Integer enabled) {
        Enabled = enabled;
    }

    public Integer getLCName() {
        return LCName;
    }

    public void setLCName(Integer LCName) {
        this.LCName = LCName;
    }

    public Integer getLCDesc() {
        return LCDesc;
    }

    public void setLCDesc(Integer LCDesc) {
        this.LCDesc = LCDesc;
    }

    public Integer getOppositeAirplanes() {
        return OppositeAirplanes;
    }

    public void setOppositeAirplanes(Integer oppositeAirplanes) {
        OppositeAirplanes = oppositeAirplanes;
    }

    public Integer getOrderAirplanes() {
        return OrderAirplanes;
    }

    public void setOrderAirplanes(Integer orderAirplanes) {
        OrderAirplanes = orderAirplanes;
    }

    public Integer getIconId() {
        return IconId;
    }

    public void setIconId(Integer iconId) {
        IconId = iconId;
    }

    public Integer getRColor() {
        return RColor;
    }

    public void setRColor(Integer RColor) {
        this.RColor = RColor;
    }

    public Integer getGColor() {
        return GColor;
    }

    public void setGColor(Integer GColor) {
        this.GColor = GColor;
    }

    public Integer getBColor() {
        return BColor;
    }

    public void setBColor(Integer BColor) {
        this.BColor = BColor;
    }

    public Integer getLineType() {
        return LineType;
    }

    public void setLineType(Integer lineType) {
        LineType = lineType;
    }


    @Override
    public String toString() {
        return
              "MCU_Icon\r\n{\r\n" +
                        "  Index = " + getId() + ";\r\n" +
                        "  Desc = \"" + Desc + "\";\r\n" +
                        "  Targets = " + Util.toArrStr(getTargets()) + ";\r\n" +
                        "  Objects = " + Util.toArrStr(getObjects()) + ";\r\n" +
                        "  XPos = " + XPos + ";\r\n" +
                        "  YPos = " + YPos + ";\r\n" +
                        "  ZPos = " + ZPos + ";\r\n" +
                        "  XOri = " + XOri + ";\r\n" +
                        "  YOri = " + YOri + ";\r\n" +
                        "  ZOri = " + ZOri + ";\r\n" +
                      "  Enabled = " + Enabled + ";\r\n" +
                      "  LCName = " + LCName + ";\r\n" +
                      "  LCDesc = " + LCDesc + ";\r\n" +
                      "  OppositeAirplanes = " + OppositeAirplanes + ";\r\n" +
                      "  OrderAirplanes = " + OrderAirplanes + ";\r\n" +
                      "  IconId = " + IconId + ";\r\n" +
                      "  RColor = " + RColor + ";\r\n" +
                      "  GColor = " + GColor + ";\r\n" +
                      "  BColor = " + BColor + ";\r\n" +
                      "  LineType = " + LineType + ";\r\n" +
                      "  Coalitions = [1, 2];\r\n" +
                      "}\r\n";
    }
}
