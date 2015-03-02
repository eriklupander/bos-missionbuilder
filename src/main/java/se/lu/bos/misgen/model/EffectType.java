package se.lu.bos.misgen.model;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-02
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public enum EffectType {

    CITY_FIRE("luascripts\\worldobjects\\mapemitters\\city_fire.txt"),
    CITY_FIRE_SMALL("luascripts\\worldobjects\\mapemitters\\city_firesmall.txt"),
    HOUSE_SMOKE("luascripts\\worldobjects\\mapemitters\\house_smoke.txt"),
    LAND_FIRE("luascripts\\worldobjects\\mapemitters\\landfire.txt"),
    VILLAGE_SMOKE("luascripts\\worldobjects\\mapemitters\\villagesmoke.txt");

    private final String script;

    EffectType(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }
}
