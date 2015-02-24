package se.lu.bos.misgen.groups;

/**
 * Enums for static groups readable from .group files.
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
