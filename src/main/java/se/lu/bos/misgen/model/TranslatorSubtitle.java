package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 MCU_TR_Subtitle
 {
 Index = 1711;
 Name = "Translator Subtitle";
 Desc = "";
 Targets = [];
 Objects = [];
 XPos = 105276.255;
 YPos = 82.599;
 ZPos = 83261.382;
 XOri = 0.00;
 YOri = 357.95;
 ZOri = 0.00;
 Enabled = 1;
 SubtitleInfo
 {
 Duration = 20;
 FontSize = 20;
 HAlign = 0;
 VAlign = 1;
 RColor = 255;
 GColor = 255;
 BColor = 255;
 LCText = 3285;
 }

 Coalitions = [0, 1, 2];
 }
 */
public class TranslatorSubtitle extends GameEntity {

    private Integer Enabled = 1;

    private Integer     Duration = 20;
    private Integer     FontSize = 20;
    private Integer     HAlign = 0;
    private Integer     VAlign = 1;
    private Integer     RColor = 255;
    private Integer     GColor = 255;
    private Integer     BColor = 255;
    private Integer     LCText = 3285;

    public TranslatorSubtitle(float x, float y, float z, Integer LCText) {
        setId(GlobalId.nextLong());
        setName("Translator Subtitle");
        setLCText(LCText);
    }

    public Integer getEnabled() {
        return Enabled;
    }

    public void setEnabled(Integer enabled) {
        Enabled = enabled;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Integer getFontSize() {
        return FontSize;
    }

    public void setFontSize(Integer fontSize) {
        FontSize = fontSize;
    }

    public Integer getHAlign() {
        return HAlign;
    }

    public void setHAlign(Integer HAlign) {
        this.HAlign = HAlign;
    }

    public Integer getVAlign() {
        return VAlign;
    }

    public void setVAlign(Integer VAlign) {
        this.VAlign = VAlign;
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

    public Integer getLCText() {
        return LCText;
    }

    public void setLCText(Integer LCText) {
        this.LCText = LCText;
    }

    //Coalitions = [0, 1, 2];
    @Override
    public String toString() {
        return  "MCU_TR_Subtitle\r\n{\r\n" +

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
                "  Enabled = " + Enabled + ";\r\n" +
                "  SubtitleInfo\r\n  {\r\n" +
                "    Duration = " + Duration + ";\r\n" +
                "    FontSize = " + FontSize + ";\r\n" +
                "    HAlign = " + HAlign + ";\r\n" +
                "    VAlign = " + VAlign + ";\r\n" +
                "    RColor = " + RColor + ";\r\n" +
                "    GColor = " + GColor + ";\r\n" +
                "    BColor = " + BColor + ";\r\n" +
                "    LCText = " + LCText + ";\r\n" +
                "  }\r\n" +
                "  Coalitions = [0, 1, 2];\r\n" +
                "}\r\n";
    }
}
