package se.lu.bos.misgen.model;

import se.lu.bos.misgen.util.Util;

import java.util.ArrayList;
import java.util.List;

/**

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
public class MCUTREntity extends GameEntity {

    private Integer Enabled = 1;
    private Integer MisObjID = 12;  // Always references parent...

    private List<OnReport> OnReports = new ArrayList<>();
    private List<OnEvent> OnEvents = new ArrayList<>();

    public MCUTREntity(GameEntity parent) {
        super.setXPos(parent.getXPos());
        super.setYPos(parent.getYPos());
        super.setZPos(parent.getZPos());
        super.setXOri(parent.getXOri());
        super.setYOri(parent.getYOri());
        super.setZOri(parent.getZOri());
    }

    public Integer getEnabled() {
        return Enabled;
    }

    public void setEnabled(Integer enabled) {
        Enabled = enabled;
    }

    public Integer getMisObjID() {
        return MisObjID;
    }

    public void setMisObjID(Integer misObjID) {
        MisObjID = misObjID;
    }

    public List<OnReport> getOnReports() {
        return OnReports;
    }

    public void setOnReports(List<OnReport> onReports) {
        OnReports = onReports;
    }

    public List<OnEvent> getOnEvents() {
        return OnEvents;
    }

    public void setOnEvents(List<OnEvent> onEvents) {
        OnEvents = onEvents;
    }

    @Override
    public String toString() {
        return "MCU_TR_Entity\r\n{\r\n" +
                super.toString() +
                "  Enabled = " + Enabled + ";\r\n" +
                "  MisObjID = " + MisObjID + ";\r\n" +
                //(OnReports.size() > 0 ? "  OnReports\r\n{\r\n" + OnReports + "\r\n}\r\n"  : "") +
                (OnEvents.size() > 0 ? "  OnEvents\r\n  {\r\n" + Util.eventsToString(OnEvents) + "  }\r\n"  : "") +
                "}\r\n";
    }
}
