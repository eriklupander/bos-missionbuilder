package se.lu.bos.misgen.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-19
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public enum StaticObjectType {

    STATIC_OPEL(201, "LuaScripts\\WorldObjects\\Blocks\\static_opel.txt", "graphics\\blocks\\static_opel.mgm"),
    STATIC_BF109_OPEN(201, "LuaScripts\\WorldObjects\\Blocks\\static_bf109_open.txt", "graphics\\blocks\\static_bf109_open.mgm"),
    STATIC_OPEN_FUEL(201, "LuaScripts\\WorldObjects\\Blocks\\static_opel_fuel.txt", "graphics\\blocks\\static_opel_fuel.mgm"),
    STATIC_HE111(201, "LuaScripts\\WorldObjects\\Blocks\\static_he111.txt", "graphics\\blocks\\static_he111.mgm"),
    STATIC_JU87(201, "LuaScripts\\WorldObjects\\Blocks\\static_ju87.txt", "graphics\\blocks\\static_ju87.mgm");

    private final int country;
    private final String script;
    private final String model;

    StaticObjectType(int country, String script, String model) {
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

    public Set<StaticObjectType> values(int country) {
         return Arrays.asList(StaticObjectType.values()).stream().filter(sot -> sot.getCountry() == country).collect(Collectors.toSet());
    }
}
