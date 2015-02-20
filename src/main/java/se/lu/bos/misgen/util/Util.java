package se.lu.bos.misgen.util;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import se.lu.bos.misgen.model.IconType;
import se.lu.bos.misgen.model.OnEvent;
import se.lu.bos.misgen.model.VehicleType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-27
 * Time: 00:50
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    public static String toArrStr(List<Integer> l) {
        StringBuilder buf = new StringBuilder();
        if(l == null || l.size() == 0) {
            return "[]";
        }
        buf.append("[");
        for(Integer i : l) {
            buf.append(i).append(",");
        }
        buf.setLength(buf.length() - 1);
        buf.append("]");
        return buf.toString();
    }

    public static String objectsArrStr(List<? extends Object> objects) {
        StringBuilder buf = new StringBuilder();
        if(objects == null || objects.size() == 0) {
            return "  {}";
        }
        buf.append("  {\r\n");
        for(Object o : objects) {
            buf.append(o.toString()).append("\r\n");
        }

        buf.append("  }");
        return buf.toString();
    }

    public static Float getOffset(int a) {
        return 50.0f + (a*150.0f);
    }

    public static String eventsToString(List<OnEvent> onReports) {
        if(onReports == null) return "";
        StringBuilder buf = new StringBuilder();
        for(OnEvent or : onReports) {
            buf.append(or.toString());
        }
        return buf.toString();
    }

    /**
     * Given the base point x,z - this method returns an offset position based on the yOri and the index. index = 0 returns same.
     *
     * Example. Index 2 == 200 meter offset. 90 degrees. We stack to the "right". This means that we will return x = x, z = z+2*100;
     *
     * @param index
     * @param x
     * @param z
     * @param yOri
     * @return
     */
    public static Float[] getOffsetFormationLine(int index, Float x, Float y, Float z, Float yOri, Integer spacingMeters) {
//        Float[] pos = new Float[2];
//
//        pos[0] = new Double(x - ((spacingMeters*index) * Math.sin(Math.toRadians(yOri)))).floatValue();
//        pos[1] = new Double(z + ((spacingMeters*index) * Math.cos(Math.toRadians(yOri)))).floatValue();
//        return pos;

        if(index == 0) return new Float[]{x,y,z};
        Line node = new Line();
        node.setRotate(yOri+90);
        Point2D point2D = node.sceneToLocal(0, spacingMeters*index);
        return new Float[]{x + (float) point2D.getY(), y, z + (float) point2D.getX()};
    }

    public static Float[] getOffsetFormationColumn(int index, Float x, Float y, Float z, Float yOri, Integer spacingMeters) {
//        Float[] pos = new Float[2];
//
//        pos[0] = new Double(x - ((spacingMeters*index) * Math.cos(Math.toRadians(yOri)))).floatValue();
//        pos[1] = new Double(z - ((spacingMeters*index) * Math.sin(Math.toRadians(yOri)))).floatValue();
//        return pos;
        if(index == 0) return new Float[]{x,y,z};
        Line node = new Line();
        node.setRotate(yOri+180);
        Point2D point2D = node.sceneToLocal(0, spacingMeters*index);
        return new Float[]{x + (float) point2D.getY(), y, z + (float) point2D.getX()};
    }

    /**
     * Builds a formation like this (if 0 degrees yOri)
     *
     *                  0
 *                  1       2
*                3             4
*             5                    6
     *    ..                            ..
     *
     * @param index
     * @param x
     * @param y
     * @param z
     * @param yOri
     * @param spacingMeters
     * @return
     */
    public static Float[] getOffsetWedge(int index, Float x, Float y, Float z, Float yOri, Integer spacingMeters) {
        if(index == 0) return new Float[]{x, y, z};
        Line node = new Line();
        Point2D point2D = null;
        boolean left = index % 2 != 0;
        float fraction = ((float)index)/2f;
        int offsetSteps = new Double(Math.ceil(fraction)).intValue();

        if(left) {
            node.setRotate(yOri+225);
        } else {
            node.setRotate(yOri+135);
        }
        point2D = node.sceneToLocal(0, spacingMeters*offsetSteps);
        return new Float[]{x + (float) point2D.getY(), y + (10*offsetSteps) , z + (float) point2D.getX()};

    }

    public static Integer getIconIdForVehicleType(VehicleType vehicleType) {
        switch(vehicleType) {
            case BA10M:
            case BM13:
            case ML20:
                return IconType.ARTILLERY_RED.getCode();
            case T34:
                return IconType.TANK_GROUP_RED.getCode();
            case GAZ_M:
            case BA64:
                return IconType.TRUCK_RED.getCode();
            case FLAK37:
            case FLAK38:
            case FLAK37_USSR:
            case FLAK38_USSR:
            case GAZ_AA_M4_AA:
            case MG34_AA:
                return IconType.AAA_RED.getCode();

            default:
                return IconType.FORTIFICATION_RED.getCode();
        }
    }
}
