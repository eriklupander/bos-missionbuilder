package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**

 Effect
 {
 Name = "Effect";
 Index = 12;
 LinkTrId = 13;
 XPos = 105907.650;
 YPos = 46.414;
 ZPos = 149181.771;
 XOri = 0.00;
 YOri = 93.28;
 ZOri = 0.00;
 Script = "luascripts\worldobjects\mapemitters\test\041.txt";
 Model = "";
 Desc = "";
 }



 MCU_TR_Entity
 {
 Index = 13;
 Name = "Effect entity";
 Desc = "";
 Targets = [];
 Objects = [];
 XPos = 105907.650;
 YPos = 46.614;
 ZPos = 149181.771;
 XOri = 0.00;
 YOri = 0.00;
 ZOri = 0.00;
 Enabled = 1;
 MisObjID = 12;
 }

 */
public class Effect extends GameEntity {

    private Integer Index = 12;  // This should be set at serialization time?
    private Integer LinkTrId = 13;

    private String Script = "luascripts\\worldobjects\\mapemitters\\test\\041.txt";
    private String Model = "";

    public Effect(float x, float y, float z, EffectType effectType) {
        setId(GlobalId.nextLong());
        setXPos(x);
        setYPos(y);
        setZPos(z);
        setScript(effectType.getScript());

        this.MCU_TR_Entity = new MCUTREntity(this);
        this.MCU_TR_Entity.setMisObjID(GlobalId.lastInt());
        this.MCU_TR_Entity.setId(GlobalId.nextLong());
        setLinkTrId(GlobalId.lastInt());
    }

    public Integer getLinkTrId() {
        return LinkTrId;
    }

    public void setLinkTrId(Integer linkTrId) {
        LinkTrId = linkTrId;
    }

    public String getScript() {
        return Script;
    }

    public void setScript(String script) {
        Script = script;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    @Override
    public String toString() {
        return   "Effect\r\n{\r\n" +
                "  Name = \"" + Name + "\";\r\n" +
                "  Index = " + getId() + ";\r\n" +
                "  LinkTrId = " + MCU_TR_Entity.getId() + ";\r\n" +// MCU_TR_Entity
                "  XPos = " + XPos + ";\r\n" +
                "  YPos = " + YPos + ";\r\n" +
                "  ZPos = " + ZPos + ";\r\n" +
                "  XOri = " + XOri + ";\r\n" +
                "  YOri = " + YOri + ";\r\n" +
                "  ZOri = " + ZOri + ";\r\n" +
                "  Script = \"" + Script + "\";\r\n" +
                "  Model = \"\";\r\n" +
                "  Desc = \"" + Desc + "\";\r\n" +
                "}\r\n\r\n\r\n" + MCU_TR_Entity.toString();
    }
}
