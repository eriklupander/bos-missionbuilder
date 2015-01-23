package se.lu.bos.misgen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-27
 * Time: 00:44
 * To change this template use File | Settings | File Templates.
 */
public class MCUCommand extends GameEntity {

    private String type = "MCU_CMD_Takeoff"; // Move to enum later

    private String Desc = "Start Spieler";
    private List<Integer> Targets = new ArrayList<>(); //[];
    private List<Integer> Objects = new ArrayList<>(); // [1625];

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    @Override
    public String toString() {
        return type + "\n{" +
                "type='" + type + '\'' +
                ", Desc='" + Desc + '\'' +
                ", Targets=" + Targets +
                ", Objects=" + Objects +
                '}';
    }
}
