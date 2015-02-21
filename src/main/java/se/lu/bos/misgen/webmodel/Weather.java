package se.lu.bos.misgen.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.lu.bos.misgen.model.WindLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-21
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
public class Weather {

    private Integer cloudLevel = 500;
    private Integer cloudHeight = 850;
    private Integer precLevel = 10;
    private Integer precType = 0;
    private String CloudConfig = "00_clear_03\\sky.ini";
    private Integer seaState = 0;
    private Integer turbulence = 1;
    private Integer tempPressLevel = 0;
    private Integer temperature = -10;
    private Integer pressure = 785;

    private List<WindLayer> windLayers = new ArrayList<>();
//    WindLayers
//    {
//        0 :245 :2.0;
//        500 :245 :2.0;
//        1000 :245 :2.0;
//        2000 :245 :2.0;
//        5000 :245 :2.0;
//    }
    public Weather() {

    }

    public Integer getCloudLevel() {
        return cloudLevel;
    }

    public void setCloudLevel(Integer cloudLevel) {
        this.cloudLevel = cloudLevel;
    }

    public Integer getCloudHeight() {
        return cloudHeight;
    }

    public void setCloudHeight(Integer cloudHeight) {
        this.cloudHeight = cloudHeight;
    }

    public Integer getPrecLevel() {
        return precLevel;
    }

    public void setPrecLevel(Integer precLevel) {
        this.precLevel = precLevel;
    }

    public Integer getPrecType() {
        return precType;
    }

    public void setPrecType(Integer precType) {
        this.precType = precType;
    }

    public String getCloudConfig() {
        return CloudConfig;
    }

    public void setCloudConfig(String cloudConfig) {
        CloudConfig = cloudConfig;
    }

    public Integer getSeaState() {
        return seaState;
    }

    public void setSeaState(Integer seaState) {
        this.seaState = seaState;
    }

    public Integer getTurbulence() {
        return turbulence;
    }

    public void setTurbulence(Integer turbulence) {
        this.turbulence = turbulence;
    }

    public Integer getTempPressLevel() {
        return tempPressLevel;
    }

    public void setTempPressLevel(Integer tempPressLevel) {
        this.tempPressLevel = tempPressLevel;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public List<WindLayer> getWindLayers() {
        return windLayers;
    }

    public void setWindLayers(List<WindLayer> windLayers) {
        this.windLayers = windLayers;
    }

//    @JsonIgnore
//    public Integer getwl0h() {
//        return windLayers.get(0).getHeadingDegrees();
//    }
//
//    @JsonIgnore
//    public Integer getwl500h() {
//        return windLayers.get(1).getHeadingDegrees();
//    }
//
//    @JsonIgnore
//    public Integer getwl1000h() {
//        return windLayers.get(2).getHeadingDegrees();
//    }
//
//    @JsonIgnore
//    public Integer getwl2000h() {
//        return windLayers.get(3).getHeadingDegrees();
//    }
//
//    @JsonIgnore
//    public Integer getwl5000h() {
//        return windLayers.get(4).getHeadingDegrees();
//    }
//
//    @JsonIgnore
//    public Float getwl0s() {
//        return windLayers.get(0).getSpeed();
//    }
//
//    @JsonIgnore
//    public Float getwl500s() {
//        return windLayers.get(1).getSpeed();
//    }
//
//    @JsonIgnore
//    public Float getwl1000s() {
//        return windLayers.get(2).getSpeed();
//    }
//
//    @JsonIgnore
//    public Float getwl2000s() {
//        return windLayers.get(3).getSpeed();
//    }
//
//    @JsonIgnore
//    public Float getwl5000s() {
//        return windLayers.get(4).getSpeed();
//    }
}
