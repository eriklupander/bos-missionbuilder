package se.lu.bos.misgen.model;

/**

 Effect
 {
 Name = "Effect";
 Index = 12;
 LinkTrId = 13;
 XPos = 105907.650;
 YPos = 46.414;
 ZPos = 149181.771;
 XOri = 0.00;
 YOri = 93.28;
 ZOri = 0.00;
 Script = "luascripts\worldobjects\mapemitters\test\041.txt";
 Model = "";
 Desc = "";
 }



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
public class Effect extends BaseEntity {

    private String Name = "Effect";
    private Integer Index = 12;  // This should be set at serialization time?
    private Integer LinkTrId = 13;
    private Float XPos = 105907.650f;
    private Float YPos = 46.414f;
    private Float ZPos = 149181.771f;
    private Float XOri = 0.00f;
    private Float YOri = 93.28f;
    private Float ZOri = 0.00f;
    private String Script = "luascripts\\worldobjects\\mapemitters\\test\\041.txt";
    private String Model = "";
    private String Desc = "";

    private MCUTREntity MCU_TR_Entity;
}
