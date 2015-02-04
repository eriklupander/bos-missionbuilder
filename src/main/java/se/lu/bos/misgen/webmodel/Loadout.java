package se.lu.bos.misgen.webmodel;

import se.lu.bos.misgen.model.PlaneType;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-02
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public class Loadout {

    private PlaneType planeType;
    private Integer loadoutId;
    private String name;

    public Loadout() {}

    public Loadout(PlaneType planeType, int loadoutId, String name) {
        this.planeType = planeType;
        this.loadoutId = loadoutId;
        this.name = name;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType = planeType;
    }

    public Integer getLoadoutId() {
        return loadoutId;
    }

    public void setLoadoutId(Integer loadoutId) {
        this.loadoutId = loadoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
