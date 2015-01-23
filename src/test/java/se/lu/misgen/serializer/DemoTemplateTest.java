package se.lu.misgen.serializer;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import se.lu.bos.misgen.templates.DemoTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-03
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
@Test
public class DemoTemplateTest {

    public void testWriteDemoTemplate() throws IOException {

        String output = DemoTemplate.buildDemoMission();

        System.out.println(output);

        try {
            IOUtils.write(output,
                    new FileOutputStream(
                            new File("H:\\skyrim\\SteamApps\\common\\IL-2 Sturmovik Battle of Stalingrad\\data\\Missions\\Test\\demo.Mission"))
            );
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

}
