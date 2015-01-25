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

    BF109G2(201, "LuaScripts\\WorldObjects\\Planes\\bf109g2.txt", "graphics\\planes\\bf109g2\\bf109g2.mgm"),
    BF109F4(201, "LuaScripts\\WorldObjects\\Planes\\bf109f4.txt", "graphics\\planes\\bf109f4\\bf109f4.mgm"),
    YAK1(101, "LuaScripts\\WorldObjects\\Planes\\yak1s69.txt", "graphics\\planes\\yak1s69\\yak1s69.mgm"),
    IL2M42(101, "LuaScripts\\WorldObjects\\Planes\\il2m42.txt", "graphics\\planes\\il2m42\\il2m42.mgm");

    private final int country;
    private final String script;
    private final String model;

    PlaneType(int country, String script, String model) {
        this.country = country;
        this.script = script;
        this.model = model;
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

    public Set<PlaneType> values(int country) {
        return Arrays.asList(PlaneType.values()).stream().filter(sot -> sot.getCountry() == country).collect(Collectors.toSet());
    }
}
