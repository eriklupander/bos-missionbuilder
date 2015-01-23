package se.lu.bos.misgen.model;

/**
 OnEvents
 {
 OnEvent
 {
 Type = 4;
 TarId = 2782;
 }
 }
 */
public class OnEvent {
    private Integer Type = 4;
    private Integer TarId = -1;

    public OnEvent(Integer type, Integer tarId) {
        Type = type;
        TarId = tarId;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Integer getTarId() {
        return TarId;
    }

    public void setTarId(Integer tarId) {
        TarId = tarId;
    }

    @Override
    public String toString() {
        return "    OnEvent\r\n    {\r\n" +
               "      Type = " + Type + ";\r\n" +
               "      TarId = " + TarId + ";\r\n" +
               "    }\r\n";
    }
}
