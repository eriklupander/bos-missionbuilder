package se.lu.bos.misgen.model;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.util.Util;

/**
 MCU_CMD_Formation // MCU type
 {
 Index = 11; // MCU ID
 Name = "Command Formation"; // MCU name (not displayed in GUI)
 Desc = ""; // MCU description (not displayed in GUI)
 Targets = []; // objects’ ID that this command points at with TL, this command can’t have any TL
 Objects = []; // objects’ ID that this command points at with OL
 XPos = 2036.09; // Х axis coordinates
 YPos = 111.578; // Y axis coordinates
 ZPos = 4209.45; // Z axis coordinates
 XOri = 0; // orientation (doesn’t have any functions in a mission)
 YOri = 0; // orientation, used only by Set Direction And Stop
 ZOri = 0; //(doesn’t have any functions in a mission)
 FormationType = 0; // formation type 0=Plane: None, 1= Plane: V-Form, 2= Plane: Left Edge Form, 3=
 Plane: Right Edge Form, 4= Vehicle: On Road Column, 5= Vehicle: Off Road Column, 6= Vehicle: Off
 Road User Formation, 7= Vehicle: Forward, 8= Vehicle: Backward, 9= Vehicle: Stop, 10= Vehicle: Panic
 Stop, 11= Vehicle: Continue Moving, 12= Vehicle: Set Direction and Stop
 FormationDensity = 0; //formation density 0=Dence, 1=Safe, 2=Loose
 }
 */
public class CommandFormation extends GameEntity {

    private Integer FormationType = 0; // formation type 0=Plane: None, 1= Plane: V-Form, 2= Plane: Left Edge Form, 3=
                                      // Plane: Right Edge Form, 4= Vehicle: On Road Column, 5= Vehicle: Off Road Column, 6= Vehicle: Off
                                    // Road User Formation, 7= Vehicle: Forward, 8= Vehicle: Backward, 9= Vehicle: Stop, 10= Vehicle: Panic
                                    // Stop, 11= Vehicle: Continue Moving, 12= Vehicle: Set Direction and Stop
    private Integer FormationDensity = 1; //formation density 0=Dence, 1=Safe, 2=Loose

    public CommandFormation(Float xPos, Float yPos, Float zPos, Integer formationType, Integer formationDensity) {
        setId(GlobalId.nextLong());
        setName("Command Formation");
        setXPos(xPos);
        setYPos(yPos);
        setZPos(zPos);
        FormationType = formationType;
        FormationDensity = formationDensity;
    }

    public Integer getFormationType() {
        return FormationType;
    }

    public void setFormationType(Integer formationType) {
        FormationType = formationType;
    }

    public Integer getFormationDensity() {
        return FormationDensity;
    }

    public void setFormationDensity(Integer formationDensity) {
        FormationDensity = formationDensity;
    }

    @Override
    public String toString() {
        return  "MCU_CMD_Formation\r\n{\r\n" +
                "  Index = " + getId() + ";\r\n" +
                "  Name = \"" + Name + "\";\r\n" +
                "  Desc = \"" + Desc + "\";\r\n" +
                "  Targets = " + Util.toArrStr(getTargets()) + ";\r\n" +
                "  Objects = " + Util.toArrStr(getObjects()) + ";\r\n" +
                "  XPos = " + XPos + ";\r\n" +
                "  YPos = " + YPos + ";\r\n" +
                "  ZPos = " + ZPos + ";\r\n" +
                "  XOri = " + XOri + ";\r\n" +
                "  YOri = " + YOri + ";\r\n" +
                "  ZOri = " + ZOri + ";\r\n" +
                "  FormationType = " + FormationType + ";\r\n" +
                "  FormationDensity = " + FormationDensity + ";\r\n" +
                "}\r\n";
    }
}
