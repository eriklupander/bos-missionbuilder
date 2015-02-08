package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;

/**
 Block
 {
 Name = "Block";
 Index = 6116;
 LinkTrId = 0;
 XPos = 112721.367;
 YPos = 66.630;
 ZPos = 184355.359;
 XOri = 0.00;
 YOri = 224.98;
 ZOri = 0.00;
 Model = "graphics\blocks\static_he111_open.mgm";
 Script = "LuaScripts\WorldObjects\Blocks\static_he111_open.txt";
 Country = 201;
 Desc = "";
 Durability = 1500;
 DamageReport = 50;
 DamageThreshold = 1;
 DeleteAfterDeath = 1;
 }
 */
public class StaticObject extends GameEntity {

    private Integer LinkTrId = 0;
    private String Model = "graphics\\blocks\\static_he111_open.mgm";
    private String Script = "LuaScripts\\WorldObjects\\Blocks\\static_he111_open.txt";
    private Integer Country = 201;
    private Integer Durability = 1500;
    private Integer DamageReport = 50;
    private Integer DamageThreshold = 1;
    private Integer DeleteAfterDeath = 1;

    public StaticObject(float x, float y, float z, float yOri) {
        setId(GlobalId.nextLong());
        setXPos(x);
        setYPos(y);
        setZPos(z);
        setYOri(yOri);

        setLinkTrId(GlobalId.lastInt());
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getScript() {
        return Script;
    }

    public void setScript(String script) {
        Script = script;
    }

    public Integer getCountry() {
        return Country;
    }

    public void setCountry(Integer country) {
        Country = country;
    }

    public Integer getDurability() {
        return Durability;
    }

    public void setDurability(Integer durability) {
        Durability = durability;
    }

    public Integer getDamageReport() {
        return DamageReport;
    }

    public void setDamageReport(Integer damageReport) {
        DamageReport = damageReport;
    }

    public Integer getDamageThreshold() {
        return DamageThreshold;
    }

    public void setDamageThreshold(Integer damageThreshold) {
        DamageThreshold = damageThreshold;
    }

    public Integer getDeleteAfterDeath() {
        return DeleteAfterDeath;
    }

    public void setDeleteAfterDeath(Integer deleteAfterDeath) {
        DeleteAfterDeath = deleteAfterDeath;
    }

    public Integer getLinkTrId() {
        return LinkTrId;
    }

    public void setLinkTrId(Integer linkTrId) {
        LinkTrId = linkTrId;
    }

    @Override
    public String toString() {
        return "Block\r\n{\r\n" +
                "  Name = \"" + Name + "\";\r\n" +
                "  Index = " + getId() + ";\r\n" +
                "  LinkTrId = " + LinkTrId + ";\r\n" +// MCU_TR_Entity
                "  XPos = " + XPos + ";\r\n" +
                "  YPos = " + YPos + ";\r\n" +
                "  ZPos = " + ZPos + ";\r\n" +
                "  XOri = " + XOri + ";\r\n" +
                "  YOri = " + YOri + ";\r\n" +
                "  ZOri = " + ZOri + ";\r\n" +
                "  Model = \"" + Model + "\";\r\n" +
                "  Script = \"" + Script + "\";\r\n" +
                "  Country = " + Country + ";\r\n" +
                "  Desc = \"" + Desc + "\";\r\n" +
                "  Durability = " + Durability + ";\r\n" +
                "  DamageReport = " + DamageReport + ";\r\n" +
                "  DamageThreshold = " + DamageThreshold + ";\r\n" +
                "  DeleteAfterDeath = " + DeleteAfterDeath + ";\r\n" +
                "}\r\n";
         }
    }
