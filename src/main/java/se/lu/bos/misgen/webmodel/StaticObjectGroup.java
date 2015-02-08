package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-08
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class StaticObjectGroup extends MapObject {

    private String groupType;
    private Integer size;
    private Integer countryCode = -1;
    private String name;
    private String description;
    private String type;

    public StaticObjectGroup() {
        setGroupType("STATIC_OBJECT_GROUP");
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
