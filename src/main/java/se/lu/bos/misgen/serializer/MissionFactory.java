package se.lu.bos.misgen.serializer;

import se.lu.bos.misgen.model.MissionOptions;
import se.lu.bos.misgen.model.WindLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-03
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public class MissionFactory {

    public static MissionOptions buildDefaultMission() {
        MissionOptions m = new MissionOptions();
        List<WindLayer> windLayers = new ArrayList<>();
        WindLayer wl1 = new WindLayer(0, 245, 2.0f);
        WindLayer wl2 = new WindLayer(500, 245, 2.0f);
        WindLayer wl3 = new WindLayer(1000, 245, 2.0f);
        WindLayer wl4 = new WindLayer(2000, 245, 2.0f);
        WindLayer wl5 = new WindLayer(5000, 245, 2.0f);
        windLayers.add(wl1);
        windLayers.add(wl2);
        windLayers.add(wl3);
        windLayers.add(wl4);
        windLayers.add(wl5);
        m.setWindLayers(windLayers);
        return m;
    }
}
