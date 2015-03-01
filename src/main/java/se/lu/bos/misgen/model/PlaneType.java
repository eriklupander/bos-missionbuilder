package se.lu.bos.misgen.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public enum PlaneType {

    // TODO we should add a default cruise speed to use as default waypoint speed
    HE111(201, "LuaScripts\\WorldObjects\\Planes\\He111H6.txt", "graphics\\planes\\He111H6\\He111H6.mgm", "He111H6"),
    JU87D3(201, "LuaScripts\\WorldObjects\\Planes\\ju87d3.txt", "graphics\\planes\\ju87d3\\ju87d3.mgm", "Ju87D3"),    // 4 == 1x500, 2x250.
    BF109G2(201, "LuaScripts\\WorldObjects\\Planes\\bf109g2.txt", "graphics\\planes\\bf109g2\\bf109g2.mgm", "Bf109G2"),
    BF109F4(201, "LuaScripts\\WorldObjects\\Planes\\bf109f4.txt", "graphics\\planes\\bf109f4\\bf109f4.mgm", "Bf109F4"),  // 3
    FW190A3(201, "LuaScripts\\WorldObjects\\Planes\\FW190A3.txt", "graphics\\planes\\FW190A3\\FW190A3.mgm", "Fw190A3"),
    YAK1(101, "LuaScripts\\WorldObjects\\Planes\\yak1s69.txt", "graphics\\planes\\yak1s69\\yak1s69.mgm", "Yak1s69"),
    LAGG3(101, "LuaScripts\\WorldObjects\\Planes\\lagg3s29.txt", "graphics\\planes\\lagg3s29\\lagg3s29.mgm", "LaGG3s29"),
    LA5(101, "LuaScripts\\WorldObjects\\Planes\\la5s8.txt", "graphics\\planes\\la5s8\\la5s8.mgm", "La5s8"),
    PE2(101, "LuaScripts\\WorldObjects\\Planes\\pe2s87.txt", "graphics\\planes\\pe2s87\\pe2s87.mgm", "Pe2s87"),
    IL2M42(101, "LuaScripts\\WorldObjects\\Planes\\il2m42.txt", "graphics\\planes\\il2m42\\il2m42.mgm", "IL2M42");   // 18

    private final int country;
    private final String script;
    private final String model;
    private final String skinFolder;

    PlaneType(int country, String script, String model, String skinFolder) {
        this.country = country;
        this.script = script;
        this.model = model;
        this.skinFolder = skinFolder;
    }

    public String getScript() {
        return script;
    }

    public String getModel() {
        return model;
    }

    public int getCountry() {
        return country;
    }

    public String getSkinFolder() {
        return skinFolder;
    }

    public Set<PlaneType> values(int country) {
        return Arrays.asList(PlaneType.values()).stream().filter(sot -> sot.getCountry() == country).collect(Collectors.toSet());
    }
}
