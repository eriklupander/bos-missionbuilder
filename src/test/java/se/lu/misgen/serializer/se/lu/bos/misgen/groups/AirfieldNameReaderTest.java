package se.lu.misgen.serializer.se.lu.bos.misgen.groups;

import org.testng.annotations.Test;
import se.lu.bos.misgen.groups.AirfieldNameReader;
import se.lu.bos.misgen.webmodel.ClientAirfield;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-01
 * Time: 12:02
 * To change this template use File | Settings | File Templates.
 */
@Test
public class AirfieldNameReaderTest {

    public void test() throws IOException {
        List<ClientAirfield> airfields = AirfieldNameReader.buildAirfields();
        assertNotNull(airfields);
        assertEquals(airfields.size(), 173);
    }


}
