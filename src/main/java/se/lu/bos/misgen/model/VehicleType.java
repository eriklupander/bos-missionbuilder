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
    ZIS2_GUN(101, "LuaScripts\\WorldObjects\\vehicles\\zis2gun.txt", "graphics\\artillery\\zis2gun\\zis2gun.mgm"),
    ZIS3_GUN(101, "LuaScripts\\WorldObjects\\vehicles\\zis3gun.txt", "graphics\\artillery\\zis3gun\\zis3gun.mgm"),
    ZIS5(101, "LuaScripts\\WorldObjects\\vehicles\\zis5.txt", "graphics\\artillery\\zis5\\zis5.mgm"),
    DHSK(101, "LuaScripts\\WorldObjects\\vehicles\\dshk.txt", "graphics\\artillery\\dshk\\dshk.mgm"),
    BT7(101, "LuaScripts\\WorldObjects\\vehicles\\bt7m.txt", "graphics\\artillery\\bt7m\\bt7m.mgm"),
    T34(101, "LuaScripts\\WorldObjects\\vehicles\\t34-76stz.txt", "graphics\\artillery\\t34-76stz\\t34-76stz.mgm"),
    T70(101, "LuaScripts\\WorldObjects\\vehicles\\t70.txt", "graphics\\vehicles\\t70\\t70.mgm"),
    KV1(101, "LuaScripts\\WorldObjects\\vehicles\\kv1-42.txt", "graphics\\artillery\\kv1-42\\kv1-42.mgm"),
    BA64(101, "LuaScripts\\WorldObjects\\Vehicles\\BA64.txt", "graphics\\vehicles\\BA64\\BA64.mgm"),
    BA10M(101, "LuaScripts\\WorldObjects\\Vehicles\\BA10M.txt", "graphics\\vehicles\\BA10M\\BA10M.mgm"),
    GAZ_M(101, "LuaScripts\\WorldObjects\\Vehicles\\GAZ-M.txt", "graphics\\vehicles\\GAZ-M\\GAZ-M.mgm"),
    GAZ_AA(101, "LuaScripts\\WorldObjects\\Vehicles\\gaz-aa.txt", "graphics\\Artillery\\GAZ\\gaz-aa.mgm"),
    GAZ_AA_M4_AA(101, "LuaScripts\\WorldObjects\\Vehicles\\gaz-aa-m4-aa.txt", "graphics\\Artillery\\GAZ\\gaz-aa-m4-aa.mgm"),
    FLAK37_USSR(101, "LuaScripts\\WorldObjects\\vehicles\\flak37.txt", "graphics\\artillery\\flak37\\flak37.mgm"),
    FLAK38_USSR(101, "LuaScripts\\WorldObjects\\vehicles\\flak38.txt", "graphics\\artillery\\flak38\\flak38.mgm"),
    AAA_52K(101, "LuaScripts\\WorldObjects\\vehicles\\52k.txt", "graphics\\artillery\\52k\\52k.mgm"),
    AAA_61K(101, "LuaScripts\\WorldObjects\\vehicles\\61k.txt", "graphics\\artillery\\61k\\61k.mgm"),
    MAKSIM4_AA(101, "LuaScripts\\WorldObjects\\vehicles\\maksim4-aa.txt", "graphics\\artillery\\maksim4-aa\\maksim4-aa.mgm"),

    LAND_LIGHT_USSR(101, "LuaScripts\\WorldObjects\\vehicles\\landlightsu.txt", "graphics\\artillery\\landlightsu\\landlightsu.mgm"),
    SEARCH_LIGHT_USSR(101, "LuaScripts\\WorldObjects\\vehicles\\searchlightsu.txt", "graphics\\artillery\\searchlightsu\\searchlightsu.mgm"),
    NDB_GER_USSR(201, "LuaScripts\\WorldObjects\\vehicles\\ndb.txt", "graphics\\vehicles\\ndb\\ndb.mgm"),

    MG34(201, "LuaScripts\\WorldObjects\\vehicles\\mg34.txt", "graphics\\artillery\\mg34\\mg34.mgm"),
    MG34_AA(201, "LuaScripts\\WorldObjects\\vehicles\\mg34-aa.txt", "graphics\\artillery\\mg34-aa\\mg34-aa.mgm"),
    FORD_G917(201, "LuaScripts\\WorldObjects\\vehicles\\ford-g917.txt", "graphics\\vehicles\\ford\\ford-g917.mgm"),
    OPEL_BLITZ(201, "LuaScripts\\WorldObjects\\vehicles\\opel-blitz.txt", "graphics\\vehicles\\opel\\opel-blitz.mgm"),
    STUG3(201, "LuaScripts\\WorldObjects\\vehicles\\stug37l24.txt", "graphics\\vehicles\\stug37l24\\stug37l24.mgm"),
    STUG4(201, "LuaScripts\\WorldObjects\\vehicles\\stug40l43.txt", "graphics\\vehicles\\stug40l43\\stug40l43.mgm"),
    PZIII(201, "LuaScripts\\WorldObjects\\vehicles\\pziii-l.txt", "graphics\\vehicles\\pziii-l\\pziii-l.mgm"),
    PZIV(201, "LuaScripts\\WorldObjects\\vehicles\\pziv-g.txt", "graphics\\vehicles\\pziv-g\\pziv-g.mgm"),
    SDKFZ251(201, "LuaScripts\\WorldObjects\\vehicles\\sdkfz251-1c.txt", "graphics\\vehicles\\sdkfz251\\sdkfz251-1c.mgm"),
    SDKFZ251_SZF(201, "LuaScripts\\WorldObjects\\vehicles\\sdkfz251-szf.txt", "graphics\\vehicles\\sdkfz251\\sdkfz251-szf.mgm"),
    SDKFZ10_FLAK38(201, "LuaScripts\\WorldObjects\\vehicles\\sdkfz10-flak38.txt", "graphics\\vehicles\\sdkfz10-flak38\\sdkfz10-flak38.mgm"),
    HORCH830(201, "LuaScripts\\WorldObjects\\vehicles\\horch830.txt", "graphics\\vehicles\\horch\\horch830.mgm"),

    LEFH18(201, "LuaScripts\\WorldObjects\\vehicles\\lefh18.txt", "graphics\\artillery\\lefh18\\lefh18.mgm"),
    PAK38(201, "LuaScripts\\WorldObjects\\vehicles\\pak38.txt", "graphics\\artillery\\pak38\\pak38.mgm"),
    PAK40(201, "LuaScripts\\WorldObjects\\vehicles\\pak40.txt", "graphics\\artillery\\pak40\\pak40.mgm"),

    LAND_LIGHT_GER(201, "LuaScripts\\WorldObjects\\vehicles\\landlightger.txt", "graphics\\artillery\\searchlightger\\landlightger.mgm"),
    SEARCH_LIGHT_GER(201, "LuaScripts\\WorldObjects\\vehicles\\searchlightger.txt", "graphics\\artillery\\searchlightger\\searchlightger.mgm"),
    NDB_GER(201, "LuaScripts\\WorldObjects\\vehicles\\ndb.txt", "graphics\\vehicles\\ndb\\ndb.mgm"),

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
