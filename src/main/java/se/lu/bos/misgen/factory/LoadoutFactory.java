package se.lu.bos.misgen.factory;

import se.lu.bos.misgen.model.PlaneType;
import se.lu.bos.misgen.webmodel.Loadout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-02
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class LoadoutFactory {

    public static List<Loadout> getLoadout(PlaneType planeType) {
        List<Loadout> loadouts = new ArrayList<>();
        loadouts.add(new Loadout(planeType, 0, "Default"));
        switch(planeType) {
            case JU87D3:
                loadouts.add(new Loadout(planeType, 4, "1x500, 2x250"));
                break;
            case BF109F4:
                loadouts.add(new Loadout(planeType, 1, "Unknown?"));
                loadouts.add(new Loadout(planeType, 2, "1x250 kg bomb"));
                loadouts.add(new Loadout(planeType, 3, "A bomb?"));
                break;
            case FW190A3:
                loadouts.add(new Loadout(planeType, 5, "2x20mm 180 rnds"));
                break;
            case IL2M42:
                loadouts.add(new Loadout(planeType, 18, "Some bombs?"));
                break;
            case PE2:
                loadouts.add(new Loadout(planeType, 2, "Bombs?"));
                break;
            case BF109G2:
                break;
            case HE111:
                break;
            case LA5:
                break;
            case LAGG3:
                break;
            case YAK1:
                break;
        }

        return loadouts;
    }

}
