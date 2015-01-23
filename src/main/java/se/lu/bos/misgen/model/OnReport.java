package se.lu.bos.misgen.model;

/**
 OnReport
 {
 Type = 3;
 CmdId = 1636;
 TarId = 1635;
 }
 OnReport
 {
 Type = 4;
 CmdId = 1638;
 TarId = 1637;
 }
 */
public class OnReport extends BaseEntity {

    private Integer Type = 3;
    private Integer CmdId = 1636; // Command ID such as Takeoff or landing
    private Integer TarId = 1635; // Target timer for example

}
