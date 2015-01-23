package se.lu.bos.misgen.model;

import se.lu.bos.misgen.util.Util;

import java.util.ArrayList;
import java.util.List;

/**

 Options
 {
 LCName  =  0;
 LCDesc  =  1;
 LCAuthor  =  2;
 PlayerConfig  =  "LuaScripts\WorldObjects\Planes\bf109g2.txt";
 Time  =  9:30:0;
 Date  =  11.12.1942;
 HMap  =  "graphics\LANDSCAPE_Stalin_w\height.hini";
 Textures  =  "graphics\LANDSCAPE_Stalin_w\textures.tini";
 Forests  =  "graphics\LANDSCAPE_Stalin_w\trees\woods.wds";
 Layers  =  "";
 GuiMap  =  "stalingrad-1942";
 SeasonPrefix  =  "wi";
 MissionType  =  0;
 AqmId  =  0;
 CloudLevel  =  500;
 CloudHeight  =  850;
 PrecLevel  =  10;
 PrecType  =  0;
 CloudConfig  =  "00_clear_03\sky.ini";
 SeaState  =  0;
 Turbulence  =  1;
 TempPressLevel  =  0;
 Temperature  =  -10;
 Pressure  =  785;
 WindLayers
 {
 0 :     245 :     2;
 500 :     245 :     2.2;
 1000 :     245 :     2.5;
 2000 :     245 :     2.7;
 5000 :     245 :     3;
 }
 Countries
 {
 0 : 0;
 50 : 0;
 101 : 1;
 201 : 2;
 }
 }

 */
public class MissionOptions extends BaseEntity {

    private Integer LCName  =  0;
    private Integer LCDesc  =  1;
    private Integer LCAuthor  =  2;
    private String PlayerConfig  =  "LuaScripts\\WorldObjects\\Planes\\bf109g2.txt";
    private String Time  =  "9:30:0";     // No quotes in output
    private String Date  =  "11.12.1942"; // No quotes in output

    private String HMap  =  "graphics\\LANDSCAPE_Stalin_w\\height.hini";
    private String Textures  =  "graphics\\LANDSCAPE_Stalin_w\\textures.tini";
    private String Forests  =  "graphics\\LANDSCAPE_Stalin_w\\trees\\woods.wds";
    private String Layers  =  "";
    private String GuiMap  =  "stalingrad-1942";
    private String SeasonPrefix  =  "wi";

    private Integer MissionType  =  0;
    private Integer AqmId  =  0;
    private Integer CloudLevel  =  500;
    private Integer CloudHeight  =  850;
    private Integer PrecLevel  =  10;
    private Integer PrecType  =  0;
    private String CloudConfig  =  "00_clear_03\\sky.ini";
    private Integer SeaState  =  0;
    private Integer Turbulence  =  1;
    private Integer TempPressLevel  =  0;
    private Integer Temperature  =  -10;
    private Integer Pressure  =  785;

    private List<WindLayer> WindLayers  =  new ArrayList<>();

    private String Countries  =  "{\r\n" +
            "    0 : 0;\r\n" +
            "    50 : 0;\r\n" +
            "    101 : 1;\r\n" +
            "    201 : 2;\r\n" +
            "  }";


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

    public Integer getLCAuthor() {
        return LCAuthor;
    }

    public void setLCAuthor(Integer LCAuthor) {
        this.LCAuthor = LCAuthor;
    }

    public String getPlayerConfig() {
        return PlayerConfig;
    }

    public void setPlayerConfig(String playerConfig) {
        PlayerConfig = playerConfig;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHMap() {
        return HMap;
    }

    public void setHMap(String HMap) {
        this.HMap = HMap;
    }

    public String getTextures() {
        return Textures;
    }

    public void setTextures(String textures) {
        Textures = textures;
    }

    public String getForests() {
        return Forests;
    }

    public void setForests(String forests) {
        Forests = forests;
    }

    public String getLayers() {
        return Layers;
    }

    public void setLayers(String layers) {
        Layers = layers;
    }

    public String getGuiMap() {
        return GuiMap;
    }

    public void setGuiMap(String guiMap) {
        GuiMap = guiMap;
    }

    public String getSeasonPrefix() {
        return SeasonPrefix;
    }

    public void setSeasonPrefix(String seasonPrefix) {
        SeasonPrefix = seasonPrefix;
    }

    public Integer getMissionType() {
        return MissionType;
    }

    public void setMissionType(Integer missionType) {
        MissionType = missionType;
    }

    public Integer getAqmId() {
        return AqmId;
    }

    public void setAqmId(Integer aqmId) {
        AqmId = aqmId;
    }

    public Integer getCloudLevel() {
        return CloudLevel;
    }

    public void setCloudLevel(Integer cloudLevel) {
        CloudLevel = cloudLevel;
    }

    public Integer getCloudHeight() {
        return CloudHeight;
    }

    public void setCloudHeight(Integer cloudHeight) {
        CloudHeight = cloudHeight;
    }

    public Integer getPrecLevel() {
        return PrecLevel;
    }

    public void setPrecLevel(Integer precLevel) {
        PrecLevel = precLevel;
    }

    public Integer getPrecType() {
        return PrecType;
    }

    public void setPrecType(Integer precType) {
        PrecType = precType;
    }

    public String getCloudConfig() {
        return CloudConfig;
    }

    public void setCloudConfig(String cloudConfig) {
        CloudConfig = cloudConfig;
    }

    public Integer getSeaState() {
        return SeaState;
    }

    public void setSeaState(Integer seaState) {
        SeaState = seaState;
    }

    public Integer getTurbulence() {
        return Turbulence;
    }

    public void setTurbulence(Integer turbulence) {
        Turbulence = turbulence;
    }

    public Integer getTempPressLevel() {
        return TempPressLevel;
    }

    public void setTempPressLevel(Integer tempPressLevel) {
        TempPressLevel = tempPressLevel;
    }

    public Integer getTemperature() {
        return Temperature;
    }

    public void setTemperature(Integer temperature) {
        Temperature = temperature;
    }

    public Integer getPressure() {
        return Pressure;
    }

    public void setPressure(Integer pressure) {
        Pressure = pressure;
    }

    public List<WindLayer> getWindLayers() {
        return WindLayers;
    }

    public void setWindLayers(List<WindLayer> windLayers) {
        WindLayers = windLayers;
    }

    public String getCountries() {
        return Countries;
    }

    public void setCountries(String countries) {
        Countries = countries;
    }

    @Override
    public String toString() {
        return "\r\n\r\n\r\nOptions\r\n{\r\n" +
                "  LCName = " + LCName + ";\r\n" +
                "  LCDesc = " + LCDesc + ";\r\n" +
                "  LCAuthor = " + LCAuthor + ";\r\n" +
                "  PlayerConfig = \"" + PlayerConfig + "\";\r\n" +
                "  Time = " + Time + ";\r\n" +
                "  Date = " + Date + ";\r\n" +
                "  HMap = \"" + HMap + "\";\r\n" +
                "  Textures = \"" + Textures + "\";\r\n" +
                "  Forests = \"" + Forests + "\";\r\n" +
                "  Layers = \"" + Layers + "\";\r\n" +
                "  GuiMap = \"" + GuiMap + "\";\r\n" +
                "  SeasonPrefix = \"" + SeasonPrefix + "\";\r\n" +
                "  MissionType = " + MissionType + ";\r\n" +
                "  AqmId = " + AqmId + ";\r\n" +
                "  CloudLevel = " + CloudLevel + ";\r\n" +
                "  CloudHeight = " + CloudHeight + ";\r\n" +
                "  PrecLevel = " + PrecLevel + ";\r\n" +
                "  PrecType = " + PrecType + ";\r\n" +
                "  CloudConfig = \"" + CloudConfig + "\";\r\n" +
                "  SeaState = " + SeaState + ";\r\n" +
                "  Turbulence = " + Turbulence + ";\r\n" +
                "  TempPressLevel = " + TempPressLevel + ";\r\n" +
                "  Temperature = " + Temperature + ";\r\n" +
                "  Pressure = " + Pressure + ";\r\n" +
                "  WindLayers\r\n" + Util.objectsArrStr(WindLayers) + "\r\n" +
                "  Countries\r\n" + Countries + "\r\n" +
                "}\r\n";
    }
}
