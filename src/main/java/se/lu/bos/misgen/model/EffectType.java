package se.lu.bos.misgen.model;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-02
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public enum EffectType {

    CITY_FIRE("luascripts\\worldobjects\\mapemitters\\city_fire.txt", "city_fire.jpg"),
    CITY_FIRE_SMALL("luascripts\\worldobjects\\mapemitters\\city_firesmall.txt", "city_firesmall.jpg"),
    HOUSE_SMOKE("luascripts\\worldobjects\\mapemitters\\house_smoke.txt", "house_smoke.jpg"),
    LAND_FIRE("luascripts\\worldobjects\\mapemitters\\landfire.txt", "landfire.jpg"),
    VILLAGE_SMOKE("luascripts\\worldobjects\\mapemitters\\villagesmoke.txt", "villagesmoke.jpg");

    private final String script;
    private final String iconImage;

    EffectType(String script, String iconImage) {
        this.script = script;
        this.iconImage = iconImage;
    }

    public String getScript() {
        return script;
    }

    public String getIconImage() {
        return iconImage;
    }
}
