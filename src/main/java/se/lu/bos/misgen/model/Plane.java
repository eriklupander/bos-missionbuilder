package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-27
 * Time: 00:29
 * To change this template use File | Settings | File Templates.
 */
public class Plane extends GameEntity {

    private Integer LinkTrId = 1625;
    private String Script = "LuaScripts\\WorldObjects\\Planes\\bf109g2.txt";
    private String Model = "graphics\\planes\\bf109g2\\bf109g2.mgm";
    private Integer Country = 201;
    private String Skin = "";
    private Integer AILevel = 0;
    private Integer CoopStart = 0;
    private Integer NumberInFormation = 0;
    private Integer Vulnerable = 1;
    private Integer Engageable = 1;
    private Integer LimitAmmo = 1;
    private Integer StartInAir = 0;     // 0 == Will start in air, 1 ==  Will start on the ground...
    private Integer Callsign = 3;
    private Integer Callnum = 1;
    private Integer Time = 60;
    private Integer DamageReport = 50;
    private Integer DamageThreshold = 1;
    private Integer PayloadId = 0;
    private Integer WMMask = 1;
    private Integer AiRTBDecision = 1;
    private Integer DeleteAfterDeath = 1;
    private Float Fuel = 0.7f;

    public Plane(float x, float y, float z, float yOri) {
        setId(GlobalId.nextLong());
        setXPos(x);
        setYPos(y);
        setZPos(z);
        setYOri(yOri);
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

    public Integer getStartInAir() {
        return StartInAir;
    }

    public void setStartInAir(Integer startInAir) {
        StartInAir = startInAir;
    }

    public Integer getCallsign() {
        return Callsign;
    }

    public void setCallsign(Integer callsign) {
        Callsign = callsign;
    }

    public Integer getCallnum() {
        return Callnum;
    }

    public void setCallnum(Integer callnum) {
        Callnum = callnum;
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
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

    public Integer getPayloadId() {
        return PayloadId;
    }

    public void setPayloadId(Integer payloadId) {
        PayloadId = payloadId;
    }

    public Integer getWMMask() {
        return WMMask;
    }

    public void setWMMask(Integer WMMask) {
        this.WMMask = WMMask;
    }

    public Integer getAiRTBDecision() {
        return AiRTBDecision;
    }

    public void setAiRTBDecision(Integer aiRTBDecision) {
        AiRTBDecision = aiRTBDecision;
    }

    public Integer getDeleteAfterDeath() {
        return DeleteAfterDeath;
    }

    public void setDeleteAfterDeath(Integer deleteAfterDeath) {
        DeleteAfterDeath = deleteAfterDeath;
    }

    public Float getFuel() {
        return Fuel;
    }

    public void setFuel(Float fuel) {
        Fuel = fuel;
    }

    public void setMCU_TR_Entity(MCUTREntity MCU_TR_Entity) {
        this.MCU_TR_Entity = MCU_TR_Entity;
    }

    @Override
    public String toString() {
        return "Plane\r\n{\r\n" +
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
                "  Country = " + Country + ";\r\n" +
                "  Desc = \"" + Desc + "\";\r\n" +
                "  Skin = \"" + Skin + "\";\r\n" +
                "  AILevel = " + AILevel + ";\r\n" +
                "  CoopStart = " + CoopStart + ";\r\n" +
                "  NumberInFormation = " + NumberInFormation + ";\r\n" +
                "  Vulnerable = " + Vulnerable + ";\r\n" +
                "  Engageable = " + Engageable + ";\r\n" +
                "  LimitAmmo = " + LimitAmmo + ";\r\n" +
                "  StartInAir = " + StartInAir + ";\r\n" +
                "  Callsign = " + Callsign + ";\r\n" +
                "  Callnum = " + Callnum + ";\r\n" +
                "  Time = " + Time + ";\r\n" +
                "  DamageReport = " + DamageReport + ";\r\n" +
                "  DamageThreshold = " + DamageThreshold + ";\r\n" +
                "  PayloadId = " + PayloadId + ";\r\n" +
                "  WMMask = " + WMMask + ";\r\n" +
                "  AiRTBDecision = " + AiRTBDecision + ";\r\n" +
                "  DeleteAfterDeath = " + DeleteAfterDeath + ";\r\n" +
                "  Fuel = " + Fuel + ";\r\n" +
                "}\r\n\r\n\r\n" + MCU_TR_Entity.toString();


    }
}
