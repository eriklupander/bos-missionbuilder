package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 MCU_TR_MissionBegin
 {
 Index = 2309;
 Name = "Translator Mission Begin";
 Desc = "";
 Targets = [2308];
 Objects = [];
 XPos = 94458.756;
 YPos = 79.282;
 ZPos = 51090.248;
 XOri = 0.00;
 YOri = 0.00;
 ZOri = 0.00;
 Enabled = 1;
 }
 */
public class TranslatorMissionBegin extends GameEntity {

    private Integer Enabled = 1;

    public TranslatorMissionBegin(float x, float y, float z) {
        setId(GlobalId.nextLong());
        setName("Translator Mission Begin");
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

    @Override
    public String toString() {
        return  "MCU_TR_MissionBegin\r\n{\r\n" +

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
                "}\r\n";
    }
}
