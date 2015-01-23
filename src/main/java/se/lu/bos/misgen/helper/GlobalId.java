package se.lu.bos.misgen.helper;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class GlobalId {

    private static Integer id = 0;

    public static Long nextLong() {
        id += 1;
        return id.longValue();
    }

    public static Long lastLong() {
        return id.longValue();
    }

    public static Integer lastInt() {
        return id;
    }

    public static Integer nextInt() {
        id += 1;
        return id;
    }
}
