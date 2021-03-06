package se.lu.bos.misgen.groups;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.util.EnvUtil;
import se.lu.bos.misgen.webmodel.ClientAirfield;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-01
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AirfieldNameReader {

    private static final Logger log = LoggerFactory.getLogger(AirfieldNameReader.class);

    @Autowired
    EnvUtil envUtil;

    public List<ClientAirfield> buildAirfields() throws IOException {
        List<ClientAirfield> airfields = new ArrayList<>();
        String src = readGroupFromFile("Stalingrad_ALL_AIRFIELDS.eng");
        Scanner scanner = new Scanner(src);

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                if(!line.endsWith(":")) {
                    String[] parts = line.split(":");
                    ClientAirfield clientAirfield = new ClientAirfield();
                    clientAirfield.setLcId(Integer.parseInt(parts[0]));
                    clientAirfield.setName(parts[1]);

                    airfields.add(clientAirfield);
                }
            } catch (Exception e) {
                log.warn("Problem parsing line: " + line);
            }
        }
        return airfields;
    }

    private String readGroupFromFile(String file) throws IOException {
        InputStream resourceAsStream = FileUtils.openInputStream(new File(envUtil.getBasePath() + "\\Template\\" + file)); //StaticGroupsFactory.class.getClassLoader().getResourceAsStream(file);
        return IOUtils.toString(resourceAsStream, "UTF-16LE");
    }
}
