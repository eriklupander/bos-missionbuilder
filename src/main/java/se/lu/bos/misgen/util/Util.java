package se.lu.bos.misgen.util;

import se.lu.bos.misgen.model.OnEvent;
import se.lu.bos.misgen.model.OnReport;
import se.lu.bos.misgen.model.WindLayer;

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
    public static Float[] getOffsetFormationLine(int index, Float x, Float z, Float yOri) {
        Float[] pos = new Float[2];

        pos[0] = new Double(x + ((30*index) * Math.sin(Math.toRadians(yOri)))).floatValue();
        pos[1] = new Double(z + ((30*index) * Math.cos(Math.toRadians(yOri)))).floatValue();
        return pos;
    }
}
