package se.lu.bos.misgen.util;

import com.sun.javafx.geom.Vec3f;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.shape.Line;
import se.lu.bos.misgen.model.IconType;
import se.lu.bos.misgen.model.OnEvent;
import se.lu.bos.misgen.model.PlaneType;
import se.lu.bos.misgen.model.VehicleType;
import se.lu.bos.misgen.webmodel.UnitGroup;
import se.lu.bos.misgen.webmodel.WayPoint;

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

    public static Integer getIconIdForVehicleType(VehicleType vehicleType, boolean friendly) {
        switch(vehicleType) {
            case BA10M:
            case BM13:
            case ML20:
            case ZIS3_GUN:
                return friendly ? IconType.ARTILLERY_BLUE.getCode() : IconType.ARTILLERY_RED.getCode();
            case T34:
            case STUG4:
            case SDKFZ251:
            case PZIII:
                return friendly ? IconType.TANK_GROUP_BLUE.getCode() : IconType.TANK_GROUP_RED.getCode();
            case GAZ_M:
            case FORD_G917:
            case OPEL_BLITZ:
            case BA64:
                return friendly ?  IconType.TRUCK_BLUE.getCode() : IconType.TRUCK_RED.getCode();
            case FLAK37:
            case FLAK38:
            case FLAK37_USSR:
            case FLAK38_USSR:
            case GAZ_AA_M4_AA:
            case MG34_AA:
                return friendly ? IconType.AAA_BLUE.getCode() : IconType.AAA_RED.getCode();

            default:
                return friendly ? IconType.FORTIFICATION_BLUE.getCode() :  IconType.FORTIFICATION_RED.getCode();
        }
    }

    public static Integer getIconIdForPlaneType(PlaneType planeType, boolean friendly) {
        return friendly ? IconType.BALOON_BLUE.getCode() : IconType.BALOON_RED.getCode();
    }

    public static Integer getIconIdForStaticObject(String type, boolean friendly) {
        return friendly ? IconType.FORTIFICATION_BLUE.getCode() :  IconType.FORTIFICATION_RED.getCode();
    }

    public static void applyTimeOnTarget(UnitGroup unitGroup) {
        Point3D unitPos = new Point3D(unitGroup.getX(), unitGroup.getY(), unitGroup.getZ());
        for(int a = 0; a < unitGroup.getWaypoints().size(); a++) {
            WayPoint wp = unitGroup.getWaypoints().get(a);
            Point3D wpPos = new Point3D(wp.getX(), wp.getY(), wp.getZ());
            Point3D nextWpPos = null;
            if(a < unitGroup.getWaypoints().size() -1) {
                WayPoint nextWp = unitGroup.getWaypoints().get(a+1);
                nextWpPos = new Point3D(nextWp.getX(), nextWp.getY(), nextWp.getZ());
            }

            double dist;
            if(a == 0) {
                dist = wpPos.distance(unitPos);
            } else if(nextWpPos != null) {
                dist = nextWpPos.distance(wpPos);
            } else {
                dist = 0.0f;
            }
        }
    }
}
