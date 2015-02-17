package se.lu.bos.misgen.model;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-17
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
public enum IconType {
    TRUCK_RED(500), TANK_RED(501), TANK_GROUP_RED(502), AAA_RED(503), ARTILLERY_RED(504), BALOON_RED(505), FACTORY_RED(506), TRAIN_RED(507), BOAT_RED(508), BRIDGE_RED(509), FORTIFICATION_RED(510),
    TRUCK_BLUE(551), TANK_BLUE(552), TANK_GROUP_BLUE(553), AAA_BLUE(554), ARTILLERY_BLUE(555), BALOON_BLUE(556), FACTORY_BLUE(557), TRAIN_BLUE(558), BOAT_BLUE(559), BRIDGE_BLUE(560), FORTIFICATION_BLUE(561);

    private int code;

    private IconType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
