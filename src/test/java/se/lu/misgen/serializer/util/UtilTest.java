package se.lu.misgen.serializer.util;

import org.testng.annotations.Test;
import se.lu.bos.misgen.util.Util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-16
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
@Test
public class UtilTest {

    public void testWedgeFormation() {
        Float[] pos = Util.getOffsetWedge(0, 1000f, 1000f, 1000f, 0f, 100);

        assertEquals(pos[0], 1000f);
        assertEquals(pos[2], 1000f);

        pos = Util.getOffsetWedge(1, 1000f, 1000f, 1000f, 0f, 100);

        assertEquals(round(pos[0]), 929f);
        assertEquals(round(pos[2]), 929f);

        pos = Util.getOffsetWedge(1, 1000f, 1000f, 1000f, 180f, 100);

        assertEquals(round(pos[0]), 1070f);
        assertEquals(round(pos[2]), 1070f);

        pos = Util.getOffsetWedge(1, 1000f, 1000f, 1000f, 90f, 100);

        assertEquals(round(pos[0]), 1070f);
        assertEquals(round(pos[2]), 929f);
    }

    private float round(float f) {
        return (float) Math.floor(f);
    }

    @Test
    public void testWedgeFormationHeadingNorth() {
        Float[] pos = Util.getOffsetWedge(0, 1000f, 1000f, 1000f, 0f, 100);
        assertEquals(round(pos[0]), 1000f);
        assertEquals(pos[1], 1000f);
        assertEquals(round(pos[2]), 1000f);

        pos = Util.getOffsetWedge(1, 1000f, 1000f, 1000f, 0f, 100);
        assertEquals(round(pos[0]), 929f);
        assertEquals(pos[1], 1010f);
        assertEquals(round(pos[2]), 929f);

        pos = Util.getOffsetWedge(2, 1000f, 1000f, 1000f, 0f, 100);
        assertEquals(round(pos[0]), 929f);
        assertEquals(pos[1], 1010f);
        assertEquals(round(pos[2]), 1070f);

        pos = Util.getOffsetWedge(3, 1000f, 1000f, 1000f, 0f, 100);
        assertEquals(round(pos[0]), 858f);
        assertEquals(pos[1], 1020f);
        assertEquals(round(pos[2]), 858f);

        pos = Util.getOffsetWedge(4, 1000f, 1000f, 1000f, 0f, 100);
        assertEquals(round(pos[0]), 858f);
        assertEquals(pos[1], 1020f);
        assertEquals(round(pos[2]), 1141f);
    }

    @Test
    public void testWedgeFormationHeadingEast() {
//        Float[] pos = Util.getOffsetWedge(0, 1000f, 1000f, 1000f, 90f, 100);
//        assertEquals(round(pos[0]), 1000f);
//        assertEquals(pos[1], 1000f);
//        assertEquals(pos[2], 1000f);

        Float[] pos  = Util.getOffsetWedge(1, 1000f, 1000f, 1000f, 90f, 100);
        assertEquals(round(pos[0]), 1070f);
        assertEquals(pos[1], 1010f);
        assertEquals(round(pos[2]), 929f);

        pos = Util.getOffsetWedge(2, 1000f, 1000f, 1000f, 90f, 100);
        assertEquals(round(pos[0]), 929f);
        assertEquals(pos[1], 1010f);
        assertEquals(round(pos[2]), 929f);
    }

    @Test
    public void testColumnFormation() {

        Float[] pos = Util.getOffsetFormationColumn(0, 1000f, 1000f,  1000f, 0f, 100);
        assertEquals(pos[0], 1000f);
        assertEquals(pos[2], 1000f);

        pos = Util.getOffsetFormationColumn(1, 1000f, 1000f, 1000f, 0f, 100);
        assertEquals(pos[0], 900f);
        assertEquals(pos[2], 1000f);

        pos = Util.getOffsetFormationColumn(1, 1000f, 1000f, 1000f, 90f, 100);
        assertEquals(round(pos[0]), 1000f);
        assertEquals(pos[2], 900f);

        pos = Util.getOffsetFormationColumn(1, 1000f, 1000f, 1000f, 270f, 100);
        assertEquals(round(pos[0]), 1000f);
        assertEquals(pos[2], 1100f);

        pos = Util.getOffsetFormationColumn(2, 1000f, 1000f,  1000f, 270f, 100);
        assertEquals(round(pos[0]), 1000f);
        assertEquals(pos[2], 1200f);

        pos = Util.getOffsetFormationColumn(2, 1000f, 1000f, 1000f, 180f, 100);
        assertEquals(round(pos[0]), 1200f);
        assertEquals(pos[2], 1000f);

        pos = Util.getOffsetFormationColumn(2, 1000f, 1000f, 1000f, 45f, 100);
        assertTrue(round(pos[0]) < 1000f);
        assertTrue(pos[2] < 1000f);
    }

    @Test
    public void testLineFormation() {
        Float[] pos = Util.getOffsetFormationLine(0, 1000f, 1000f, 1000f, 0f, 270);
        assertEquals(round(pos[0]), 1000f);
        assertEquals(pos[1], 1000f);

        pos = Util.getOffsetFormationLine(1, 1000f, 1000f, 1000f, 270f, 100);
        assertEquals(round(pos[0]), 1100f);
        assertEquals(pos[1], 1000f);
        pos = Util.getOffsetFormationLine(2, 1000f, 1000f, 1000f, 270f, 100);
        assertEquals(round(pos[0]), 1200f);
        assertEquals(pos[1], 1000f);
    }

}
