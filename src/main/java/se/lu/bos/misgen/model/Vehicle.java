package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 00:11
 * To change this template use File | Settings | File Templates.
 */
public class Vehicle extends GameEntity {

    private Integer LinkTrId = 1625;
    private String Script = "LuaScripts\\WorldObjects\\vehicles\\mg34-aa.txt";
    private String Model = "graphics\\artillery\\mg34-aa\\mg34-aa.mgm";
    private Integer Country = 201;
    private String Skin = "";
    private Integer AILevel = 2;
    private Integer CoopStart = 0;
    private Integer Spotter = -1;
    private Integer BeaconChannel = 0;
    private Integer NumberInFormation = 0;
    private Integer Vulnerable = 1;
    private Integer Engageable = 1;
    private Integer LimitAmmo = 1;
    private Integer Callsign = 0;

    private Integer DamageReport = 50;
    private Integer DamageThreshold = 1;
    private Integer DeleteAfterDeath = 1;

    private MCUTREntity MCU_TR_Entity;

    public Vehicle(float x, float y, float z, float yOri) {
        setId(GlobalId.nextLong());
        setXPos(x);
        setYPos(y);
        setZPos(z);
        setYOri(yOri);
        this.MCU_TR_Entity = new MCUTREntity(this);
        this.MCU_TR_Entity.setMisObjID(GlobalId.lastInt());
        this.MCU_TR_Entity.setId(GlobalId.nextLong());
        setLinkTrId(GlobalId.lastInt());
        super.setName("Vehicle");
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

    public Integer getCountry() {
        return Country;
    }

    public void setCountry(Integer country) {
        Country = country;
    }

    public String getSkin() {
        return Skin;
    }

    public void setSkin(String skin) {
        Skin = skin;
    }

    public Integer getAILevel() {
        return AILevel;
    }

    public void setAILevel(Integer AILevel) {
        this.AILevel = AILevel;
    }

    public Integer getCoopStart() {
        return CoopStart;
    }

    public void setCoopStart(Integer coopStart) {
        CoopStart = coopStart;
    }

    public Integer getSpotter() {
        return Spotter;
    }

    public void setSpotter(Integer spotter) {
        Spotter = spotter;
    }

    public Integer getBeaconChannel() {
        return BeaconChannel;
    }

    public void setBeaconChannel(Integer beaconChannel) {
        BeaconChannel = beaconChannel;
    }

    public Integer getNumberInFormation() {
        return NumberInFormation;
    }

    public void setNumberInFormation(Integer numberInFormation) {
        NumberInFormation = numberInFormation;
    }

    public Integer getVulnerable() {
        return Vulnerable;
    }

    public void setVulnerable(Integer vulnerable) {
        Vulnerable = vulnerable;
    }

    public Integer getEngageable() {
        return Engageable;
    }

    public void setEngageable(Integer engageable) {
        Engageable = engageable;
    }

    public Integer getLimitAmmo() {
        return LimitAmmo;
    }

    public void setLimitAmmo(Integer limitAmmo) {
        LimitAmmo = limitAmmo;
    }

    public Integer getCallsign() {
        return Callsign;
    }

    public void setCallsign(Integer callsign) {
        Callsign = callsign;
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

    public MCUTREntity getMCU_TR_Entity() {
        return MCU_TR_Entity;
    }

    public void setMCU_TR_Entity(MCUTREntity MCU_TR_Entity) {
        this.MCU_TR_Entity = MCU_TR_Entity;
    }


    @Override
    public String toString() {
        return "Vehicle\r\n{\r\n" +
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
                "  Model = \"" + Model + "\";\r\n" +
                "  Desc = \"" + Desc + "\";\r\n" +
                "  Country = " + Country + ";\r\n" +
                "  NumberInFormation = " + NumberInFormation + ";\r\n" +
                "  Vulnerable = " + Vulnerable + ";\r\n" +
                "  Engageable = " + Engageable + ";\r\n" +
                "  LimitAmmo = " + LimitAmmo + ";\r\n" +
                "  AILevel = " + AILevel + ";\r\n" +
                "  DamageReport = " + DamageReport + ";\r\n" +
                "  DamageThreshold = " + DamageThreshold + ";\r\n" +
                "  DeleteAfterDeath = " + DeleteAfterDeath + ";\r\n" +
                "  CoopStart = " + CoopStart + ";\r\n" +
                "  Spotter = " + Spotter + ";\r\n" +
                "  BeaconChannel = " + BeaconChannel + ";\r\n" +
                "  Callsign = " + Callsign + ";\r\n" +

                "}\r\n\r\n\r\n" + MCU_TR_Entity.toString();


    }
}
