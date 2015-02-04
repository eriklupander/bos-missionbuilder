package se.lu.bos.misgen.serializer;

import org.apache.commons.io.IOUtils;
import se.lu.bos.misgen.model.GeneratedMission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-02
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class MissionFileWriter {

    // TODO take target path from application.properties


    public void write(String name, Map<Integer, String> localization, String missionBody) throws IOException {

        StringBuilder buf = new StringBuilder();
        localization.entrySet().stream().forEach(entry -> buf.append(entry.getKey()).append(":").append(entry.getValue() != null ? entry.getValue() : "").append("\r\n"));
        FileOutputStream fos1 = new FileOutputStream(
                new File("H:\\skyrim\\SteamApps\\common\\IL-2 Sturmovik Battle of Stalingrad\\data\\Missions\\Test\\" + name.replaceAll(" ", "_").substring(0, 10) + ".eng"));
        IOUtils.write(buf.toString(),
                fos1
                ,"UTF-16LE"
        );
        IOUtils.closeQuietly(fos1);

        FileOutputStream fos2 = new FileOutputStream(
                new File("H:\\skyrim\\SteamApps\\common\\IL-2 Sturmovik Battle of Stalingrad\\data\\Missions\\Test\\" + name.replaceAll(" ", "_").substring(0, 10) + ".Mission"));
        IOUtils.write(missionBody,
                fos2
                , "US-ASCII"
        );
        IOUtils.closeQuietly(fos2);

    }
}
