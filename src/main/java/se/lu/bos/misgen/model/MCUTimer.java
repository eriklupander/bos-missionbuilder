package se.lu.bos.misgen.model;

import java.util.ArrayList;
import java.util.List;

/**

 MCU_Timer
 {
 Index = 1635;
 Name = "Trigger Timer";
 Desc = "Nach Start";
 Targets = [1649,1626,1722];
 Objects = [];
 XPos = 96163.205;
 YPos = 81.650;
 ZPos = 50725.485;
 XOri = 0.00;
 YOri = 0.00;
 ZOri = 0.00;
 Time = 30;
 Random = 100;
 }



 MCU_Timer
 {
 Index = 1637;
 Name = "Trigger Timer";
 Desc = "gelandet";
 Targets = [1799,1800];
 Objects = [];
 XPos = 96020.136;
 YPos = 84.078;
 ZPos = 50839.009;
 XOri = 0.00;
 YOri = 0.00;
 ZOri = 0.00;
 Time = 1;
 Random = 100;
 }
 */
public class MCUTimer extends GameEntity {

    //private Integer Index = 1635;

    private String Desc = "Nach Start";
    private List<Integer> Targets = new ArrayList<>();// [1649,1626,1722];
    private List<Integer> Objects = new ArrayList<>(); // [];

    private Integer Time = 30;
    private Integer Random = 100;



    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public List<Integer> getTargets() {
        return Targets;
    }

    public void setTargets(List<Integer> targets) {
        Targets = targets;
    }

    public List<Integer> getObjects() {
        return Objects;
    }

    public void setObjects(List<Integer> objects) {
        Objects = objects;
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
    }

    public Integer getRandom() {
        return Random;
    }

    public void setRandom(Integer random) {
        Random = random;
    }
}
