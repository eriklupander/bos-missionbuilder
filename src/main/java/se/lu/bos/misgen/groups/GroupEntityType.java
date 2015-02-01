package se.lu.bos.misgen.groups;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-05
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */
public enum GroupEntityType {
    AIRFIELD("Group"), TOWN("Group"), BRIDGE("Bridge"), TRAIN_STATION("Group"), STALINGRAD_CITY("Group"), AIRFIELD_ICONS("MCU_Icon");

    private String rootObjectName;

    private GroupEntityType(String rootObjectName) {
        this.rootObjectName = rootObjectName;
    }

    public String getRootObjectName() {
        return rootObjectName;
    }
}
