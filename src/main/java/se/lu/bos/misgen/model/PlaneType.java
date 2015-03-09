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
    HE111(201, "LuaScripts\\WorldObjects\\Planes\\He111H6.txt", "graphics\\planes\\He111H6\\He111H6.mgm", PlaneCategory.BOMBER, "He111H6", "He111H6.jpg"),
    JU87D3(201, "LuaScripts\\WorldObjects\\Planes\\ju87d3.txt", "graphics\\planes\\ju87d3\\ju87d3.mgm", PlaneCategory.BOMBER, "Ju87D3", "Ju87D3.jpg"),    // 4 == 1x500, 2x250.
    BF109G2(201, "LuaScripts\\WorldObjects\\Planes\\bf109g2.txt", "graphics\\planes\\bf109g2\\bf109g2.mgm", PlaneCategory.FIGHTER, "Bf109G2", "Bf109G2.jpg"),
    BF109F4(201, "LuaScripts\\WorldObjects\\Planes\\bf109f4.txt", "graphics\\planes\\bf109f4\\bf109f4.mgm", PlaneCategory.FIGHTER, "Bf109F4", "Bf109F4.jpg"),  // 3
    FW190A3(201, "LuaScripts\\WorldObjects\\Planes\\FW190A3.txt", "graphics\\planes\\FW190A3\\FW190A3.mgm", PlaneCategory.FIGHTER, "Fw190A3", "Fw190A3.jpg"),
    YAK1(101, "LuaScripts\\WorldObjects\\Planes\\yak1s69.txt", "graphics\\planes\\yak1s69\\yak1s69.mgm", PlaneCategory.FIGHTER, "Yak1s69", "Yak1s69.jpg"),
    LAGG3(101, "LuaScripts\\WorldObjects\\Planes\\lagg3s29.txt", "graphics\\planes\\lagg3s29\\lagg3s29.mgm", PlaneCategory.FIGHTER, "LaGG3s29", "LaGG3s29.jpg"),
    LA5(101, "LuaScripts\\WorldObjects\\Planes\\la5s8.txt", "graphics\\planes\\la5s8\\la5s8.mgm", PlaneCategory.FIGHTER, "La5s8", "La5s8.jpg"),
    PE2(101, "LuaScripts\\WorldObjects\\Planes\\pe2s87.txt", "graphics\\planes\\pe2s87\\pe2s87.mgm", PlaneCategory.BOMBER, "Pe2s87", "Pe2s87.jpg"),
    IL2M42(101, "LuaScripts\\WorldObjects\\Planes\\il2m42.txt", "graphics\\planes\\il2m42\\il2m42.mgm", PlaneCategory.BOMBER, "IL2M42", "Il2m42.jpg");   // 18

    private final int country;
    private final String script;
    private final String model;
    private final PlaneCategory category;
    private final String skinFolder;
    private final String iconImage;

    PlaneType(int country, String script, String model, PlaneCategory category, String skinFolder, String iconImage) {
        this.country = country;
        this.script = script;
        this.model = model;
        this.category = category;
        this.skinFolder = skinFolder;
        this.iconImage = iconImage;
    }

    public String getScript() {
        return script;
    }

    public String getModel() {
        return model;
    }

    public PlaneCategory getCategory() {
        return category;
    }

    public int getCountry() {
        return country;
    }

    public String getSkinFolder() {
        return skinFolder;
    }

    public String getIconImage() {
        return iconImage;
    }

    public Set<PlaneType> values(int country) {
        return Arrays.asList(PlaneType.values()).stream().filter(sot -> sot.getCountry() == country).collect(Collectors.toSet());
    }

}
