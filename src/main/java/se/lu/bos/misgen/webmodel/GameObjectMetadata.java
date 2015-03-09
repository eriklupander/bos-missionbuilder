package se.lu.bos.misgen.webmodel;

import se.lu.bos.misgen.model.VehicleCategory;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-04
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class GameObjectMetadata {

    private String identifier;
    private int country;
    private String script;
    private String model;
    private String category;
    private String iconImage;

    public GameObjectMetadata() {}

    public GameObjectMetadata(String identifier, int country, String model, String script, String vehicleCategory, String iconImage) {
        this.identifier = identifier;

        this.country = country;
        this.model = model;
        this.script = script;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
