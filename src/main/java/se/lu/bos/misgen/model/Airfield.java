package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;

/**

 Airfield
 {
 Name = "!x43425z7602_ori-111";
 Index = 1117;
 LinkTrId = 1115;
 XPos = 43425.000;
 YPos = 82.599;
 ZPos = 7602.000;
 XOri = 0.00;
 YOri = 111.00;
 ZOri = 0.00;
 Model = "graphics\airfields\fakefield.mgm";
 Script = "LuaScripts\WorldObjects\Airfields\fakefield.txt";
 Country = 201;
 Desc = "";
 Durability = 25000;
 DamageReport = 50;
 DamageThreshold = 1;
 DeleteAfterDeath = 1;
 Callsign = 21;
 Callnum = 2;
 Chart
 {
 Point
 {
 Type = 0;
 X = 0.0145036;
 Y = -0.00628763;
 }
 Point
 {
 Type = 1;
 X = 16.8412;
 Y = -0.0562229;
 }
 Point
 {
 Type = 1;
 X = 17.486;
 Y = -32.6795;
 }
 Point
 {
 Type = 1;
 X = 18.2282;
 Y = -66.8996;
 }
 Point
 {
 Type = 1;
 X = -16.4387;
 Y = -67.5232;
 }
 Point
 {
 Type = 1;
 X = -35.3177;
 Y = -72.3544;
 }
 Point
 {
 Type = 1;
 X = -47.2355;
 Y = -90.0572;
 }
 Point
 {
 Type = 1;
 X = -52.6547;
 Y = -112.876;
 }
 Point
 {
 Type = 1;
 X = -57.2108;
 Y = -136.664;
 }
 Point
 {
 Type = 1;
 X = -70.2495;
 Y = -170.673;
 }
 Point
 {
 Type = 2;
 X = -67.6704;
 Y = -217.456;
 }
 Point
 {
 Type = 2;
 X = 1270.6;
 Y = -774.703;
 }
 Point
 {
 Type = 1;
 X = 1318.55;
 Y = -747.989;
 }
 Point
 {
 Type = 1;
 X = 1332.19;
 Y = -721.056;
 }
 Point
 {
 Type = 1;
 X = 1324.01;
 Y = -697.658;
 }
 Point
 {
 Type = 1;
 X = 1276.56;
 Y = -653.335;
 }
 Point
 {
 Type = 1;
 X = 1228.99;
 Y = -608.862;
 }
 Point
 {
 Type = 1;
 X = 1171.59;
 Y = -562.797;
 }
 Point
 {
 Type = 1;
 X = 1112.01;
 Y = -532.908;
 }
 Point
 {
 Type = 1;
 X = 1058.03;
 Y = -506.586;
 }
 Point
 {
 Type = 0;
 X = 1052.47;
 Y = -471.376;
 }
 }
 ReturnPlanes = 0;
 Hydrodrome = 0;
 RepairFriendlies = 0;
 RearmFriendlies = 0;
 RefuelFriendlies = 0;
 RepairTime = 0;
 RearmTime = 0;
 RefuelTime = 0;
 MaintenanceRadius = 1000;
 }

 MCU_TR_Entity
 {
 Index = 1115;
 Name = "Airfield entity";
 Desc = "";
 Targets = [];
 Objects = [];
 XPos = 43425.000;
 YPos = 82.799;
 ZPos = 7602.000;
 XOri = 0.00;
 YOri = 0.00;
 ZOri = 0.00;
 Enabled = 1;
 MisObjID = 1117;
 }

 */
public class Airfield extends GameEntity {

    private Integer LinkTrId = 1625;
    private String Model = "graphics\\airfields\\fakefield.mgm";
    private String Script = "LuaScripts\\WorldObjects\\Airfields\\fakefield.txt";
    private Integer Country = 201;
    private String Desc = "";
    private Integer Durability = 25000;
    private Integer DamageReport = 50;
    private Integer DamageThreshold = 1;
    private Integer DeleteAfterDeath = 1;
    private Integer Callsign = 0;
    private Integer Callnum = 0;
    
    private String Chart = "Chart\r\n" +
            " {\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 0;\r\n" +
            " X = 0.0145036;\r\n" +
            " Y = -0.00628763;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 16.8412;\r\n" +
            " Y = -0.0562229;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 17.486;\r\n" +
            " Y = -32.6795;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 18.2282;\r\n" +
            " Y = -66.8996;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = -16.4387;\r\n" +
            " Y = -67.5232;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = -35.3177;\r\n" +
            " Y = -72.3544;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = -47.2355;\r\n" +
            " Y = -90.0572;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = -52.6547;\r\n" +
            " Y = -112.876;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = -57.2108;\r\n" +
            " Y = -136.664;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = -70.2495;\r\n" +
            " Y = -170.673;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 2;\r\n" +
            " X = -67.6704;\r\n" +
            " Y = -217.456;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 2;\r\n" +
            " X = 1270.6;\r\n" +
            " Y = -774.703;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1318.55;\r\n" +
            " Y = -747.989;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1332.19;\r\n" +
            " Y = -721.056;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1324.01;\r\n" +
            " Y = -697.658;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1276.56;\r\n" +
            " Y = -653.335;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1228.99;\r\n" +
            " Y = -608.862;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1171.59;\r\n" +
            " Y = -562.797;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1112.01;\r\n" +
            " Y = -532.908;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 1;\r\n" +
            " X = 1058.03;\r\n" +
            " Y = -506.586;\r\n" +
            " }\r\n" +
            " Point\r\n" +
            " {\r\n" +
            " Type = 0;\r\n" +
            " X = 1052.47;\r\n" +
            " Y = -471.376;\r\n" +
            " }\r\n" +
            " }";

    private Integer ReturnPlanes = 0;
    private Integer Hydrodrome = 0;
    private Integer RepairFriendlies = 0;
    private Integer RearmFriendlies = 0;
    private Integer RefuelFriendlies = 0;
    private Integer RepairTime = 0;
    private Integer RearmTime = 0;
    private Integer RefuelTime = 0;
    private Integer MaintenanceRadius = 1000;

    public Airfield(Float xPos, Float yPos, Float zPos, String name) {
        setId(GlobalId.nextLong());
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        setZPos(zPos);

        this.MCU_TR_Entity = new MCUTREntity(this);
        this.MCU_TR_Entity.setMisObjID(GlobalId.lastInt());
        this.MCU_TR_Entity.setId(GlobalId.nextLong());
        this.MCU_TR_Entity.setName("Airfield entity");
        setLinkTrId(GlobalId.lastInt());
    }

    public Integer getLinkTrId() {
        return LinkTrId;
    }

    public void setLinkTrId(Integer linkTrId) {
        LinkTrId = linkTrId;
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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
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

    public String getChart() {
        return Chart;
    }

    public void setChart(String chart) {
        Chart = chart;
    }

    public Integer getReturnPlanes() {
        return ReturnPlanes;
    }

    public void setReturnPlanes(Integer returnPlanes) {
        ReturnPlanes = returnPlanes;
    }

    public Integer getHydrodrome() {
        return Hydrodrome;
    }

    public void setHydrodrome(Integer hydrodrome) {
        Hydrodrome = hydrodrome;
    }

    public Integer getRepairFriendlies() {
        return RepairFriendlies;
    }

    public void setRepairFriendlies(Integer repairFriendlies) {
        RepairFriendlies = repairFriendlies;
    }

    public Integer getRearmFriendlies() {
        return RearmFriendlies;
    }

    public void setRearmFriendlies(Integer rearmFriendlies) {
        RearmFriendlies = rearmFriendlies;
    }

    public Integer getRefuelFriendlies() {
        return RefuelFriendlies;
    }

    public void setRefuelFriendlies(Integer refuelFriendlies) {
        RefuelFriendlies = refuelFriendlies;
    }

    public Integer getRepairTime() {
        return RepairTime;
    }

    public void setRepairTime(Integer repairTime) {
        RepairTime = repairTime;
    }

    public Integer getRearmTime() {
        return RearmTime;
    }

    public void setRearmTime(Integer rearmTime) {
        RearmTime = rearmTime;
    }

    public Integer getRefuelTime() {
        return RefuelTime;
    }

    public void setRefuelTime(Integer refuelTime) {
        RefuelTime = refuelTime;
    }

    public Integer getMaintenanceRadius() {
        return MaintenanceRadius;
    }

    public void setMaintenanceRadius(Integer maintenanceRadius) {
        MaintenanceRadius = maintenanceRadius;
    }



    @Override
    public String toString() {
        return "Airfield\r\n{\r\n" +
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
                "  Durability = " + Durability + ";\r\n" +
                "  DamageReport = " + DamageReport + ";\r\n" +
                "  DamageThreshold = " + DamageThreshold + ";\r\n" +
                "  DeleteAfterDeath = " + DeleteAfterDeath + ";\r\n" +
                "  Callsign = " + Callsign + ";\r\n" +
                "  Callnum = " + Callnum + ";\r\n" +
                " " + Chart + "\r\n" +
                "  ReturnPlanes = " + Callsign + ";\r\n" +
                "  Hydrodrome = " + Hydrodrome + ";\r\n" +
                "  RepairFriendlies = " + RepairFriendlies + ";\r\n" +
                "  RearmFriendlies = " + RearmFriendlies + ";\r\n" +
                "  RefuelFriendlies = " + RefuelFriendlies + ";\r\n" +
                "  RepairTime = " + RepairTime + ";\r\n" +
                "  RearmTime = " + RearmTime + ";\r\n" +
                "  RefuelTime = " + RefuelTime + ";\r\n" +
                "  MaintenanceRadius = " + MaintenanceRadius + ";\r\n" +
                "}\r\n\r\n\r\n" + MCU_TR_Entity.toString();


    }
}
