package se.lu.bos.misgen.webmodel;

import se.lu.bos.misgen.model.VehicleCategory;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-04
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class VehicleMetadata {

    private String identifier;
    private int country;
    private String script;
    private String model;
    private VehicleCategory vehicleCategory;
    private String iconImage;

    public VehicleMetadata() {}

    public VehicleMetadata(String identifier, int country, String model, String script, VehicleCategory vehicleCategory, String iconImage) {
        this.identifier = identifier;

        this.country = country;
        this.model = model;
        this.script = script;
        this.vehicleCategory = vehicleCategory;
        this.iconImage = iconImage;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
