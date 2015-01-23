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
}
