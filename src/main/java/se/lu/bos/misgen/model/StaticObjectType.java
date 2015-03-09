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

    STATIC_OPEL(201, "LuaScripts\\WorldObjects\\Blocks\\static_opel.txt", "graphics\\blocks\\static_opel.mgm", StaticObjectCategory.VEHICLE, "static_opel.jpg"),
    STATIC_BF109_OPEN(201, "LuaScripts\\WorldObjects\\Blocks\\static_bf109_open.txt", "graphics\\blocks\\static_bf109_open.mgm", StaticObjectCategory.VEHICLE, "static_bf109_open.jpg"),
    STATIC_OPEN_FUEL(201, "LuaScripts\\WorldObjects\\Blocks\\static_opel_fuel.txt", "graphics\\blocks\\static_opel_fuel.mgm", StaticObjectCategory.VEHICLE, "static_opel_fuel.jpg"),
    STATIC_HE111(201, "LuaScripts\\WorldObjects\\Blocks\\static_he111.txt", "graphics\\blocks\\static_he111.mgm", StaticObjectCategory.VEHICLE, "static_he111.mgm.jpg"),
    STATIC_JU87(201, "LuaScripts\\WorldObjects\\Blocks\\static_ju87.txt", "graphics\\blocks\\static_ju87.mgm", StaticObjectCategory.VEHICLE, "static_ju87.jpg"),

    ART_POSITION_SMALL(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_small.txt", "graphics\\blocks\\art_position_small.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_small.jpg"),
    ART_POSITION_BIG(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_big.txt", "graphics\\blocks\\art_position_big.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_big.jpg"),
    ART_POSITION_AT(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_at.txt", "graphics\\blocks\\art_position_at.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_at.jpg"),
    ART_POSITION_AT2(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_at2.txt", "graphics\\blocks\\art_position_at2.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_at2.jpg"),
    ART_POSITION_AMB1(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_amb1.txt", "graphics\\blocks\\art_position_amb1.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_amb1.jpg"),
    ART_POSITION_AMB2(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_amb2.txt", "graphics\\blocks\\art_position_amb2.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_amb2.jpg"),
    DUGOUT(0, "LuaScripts\\WorldObjects\\Blocks\\dugout.txt", "graphics\\blocks\\dugout.mgm", StaticObjectCategory.EMPLACEMENT, "dugout.jpg"),
    TANK_POSITION(0, "LuaScripts\\WorldObjects\\Blocks\\tank_position.txt", "graphics\\blocks\\tank_position.mgm", StaticObjectCategory.EMPLACEMENT, "tank_position.jpg"),
    MG_POSITION(0, "LuaScripts\\WorldObjects\\Blocks\\mg_position.txt", "graphics\\blocks\\mg_position.mgm", StaticObjectCategory.EMPLACEMENT, "mg_position.jpg");


    private final int country;
    private final String script;
    private final String model;
    private final StaticObjectCategory category;
    private final String iconImage;

    StaticObjectType(int country, String script, String model, StaticObjectCategory category, String iconImage) {
        this.country = country;
        this.script = script;
        this.model = model;
        this.category = category;
        this.iconImage = iconImage;
    }

    public String getScript() {
        return script;
    }

    public String getModel() {
        return model;
    }

    public StaticObjectCategory getCategory() {
        return category;
    }

    public int getCountry() {
        return country;
    }

    public String getIconImage() {
        return iconImage;
    }

    public Set<StaticObjectType> values(int country) {
         return Arrays.asList(StaticObjectType.values()).stream().filter(sot -> sot.getCountry() == country).collect(Collectors.toSet());
    }
}
