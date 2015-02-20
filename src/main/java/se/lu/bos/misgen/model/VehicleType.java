package se.lu.bos.misgen.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO Add human-readable name for all vehicles. Perhaps also add properties for max speed
 */
public enum VehicleType {

    BM13(101, "LuaScripts\\WorldObjects\\vehicles\\bm13.txt", "graphics\\vehicles\\zis\\bm13.mgm"),
    ML20(101, "LuaScripts\\WorldObjects\\vehicles\\ML20.txt", "graphics\\vehicles\\ML20\\ML20.mgm"),
    ZIS3_GUN(101, "LuaScripts\\WorldObjects\\vehicles\\zis3gun.txt", "graphics\\artillery\\zis3gun\\zis3gun.mgm"),
    DHSK(101, "LuaScripts\\WorldObjects\\vehicles\\dshk.txt", "graphics\\artillery\\dshk\\dshk.mgm"),
    T34(101, "LuaScripts\\WorldObjects\\vehicles\\t34-76stz.txt", "graphics\\artillery\\t34-76stz\\t34-76stz.mgm"),
    BA64(101, "LuaScripts\\WorldObjects\\Vehicles\\BA64.txt", "graphics\\vehicles\\BA64\\BA64.mgm"),
    BA10M(101, "LuaScripts\\WorldObjects\\Vehicles\\BA10M.txt", "graphics\\vehicles\\BA10M\\BA10M.mgm"),
    GAZ_M(101, "LuaScripts\\WorldObjects\\Vehicles\\GAZ-M.txt", "graphics\\vehicles\\GAZ-M\\GAZ-M.mgm"),
    GAZ_AA_M4_AA(101, "LuaScripts\\WorldObjects\\Vehicles\\gaz-aa-m4-aa.txt", "graphics\\Artillery\\GAZ\\gaz-aa-m4-aa.mgm"),
    FLAK37_USSR(101, "LuaScripts\\WorldObjects\\vehicles\\flak37.txt", "graphics\\artillery\\flak37\\flak37.mgm"),
    FLAK38_USSR(101, "LuaScripts\\WorldObjects\\vehicles\\flak38.txt", "graphics\\artillery\\flak38\\flak38.mgm"),

    MG34_AA(201, "LuaScripts\\WorldObjects\\vehicles\\mg34-aa.txt", "graphics\\artillery\\mg34-aa\\mg34-aa.mgm"),
    FORD_G917(201, "LuaScripts\\WorldObjects\\vehicles\\ford-g917.txt", "graphics\\vehicles\\ford\\ford-g917.mgm"),
    OPEL_BLITZ(201, "LuaScripts\\WorldObjects\\vehicles\\opel-blitz.txt", "graphics\\vehicles\\opel\\opel-blitz.mgm"),
    STUG4(201, "LuaScripts\\WorldObjects\\vehicles\\stug40l43.txt", "graphics\\vehicles\\stug40l43\\stug40l43.mgm"),
    PZIII(201, "LuaScripts\\WorldObjects\\vehicles\\pziii-l.txt", "graphics\\vehicles\\pziii-l\\pziii-l.mgm"),
    SDKFZ251(201, "LuaScripts\\WorldObjects\\vehicles\\sdkfz251-1c.txt", "graphics\\vehicles\\sdkfz251\\sdkfz251-1c.mgm"),
    FLAK37(201, "LuaScripts\\WorldObjects\\vehicles\\flak37.txt", "graphics\\artillery\\flak37\\flak37.mgm"),
    FLAK38(201, "LuaScripts\\WorldObjects\\vehicles\\flak38.txt", "graphics\\artillery\\flak38\\flak38.mgm");

    private final int country;
    private final String script;
    private final String model;

    VehicleType(int country, String script, String model) {
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

    public Set<VehicleType> values(int country) {
        return Arrays.asList(VehicleType.values()).stream().filter(sot -> sot.getCountry() == country).collect(Collectors.toSet());
    }
}
