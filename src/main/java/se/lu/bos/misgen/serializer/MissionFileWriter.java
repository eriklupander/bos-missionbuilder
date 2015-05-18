package se.lu.bos.misgen.serializer;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import se.lu.bos.misgen.util.HashUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Writes the mission directly to disk.
 *
 * See
 */
@Controller
public class MissionFileWriter {

    private static final String TRANSLATION_FILE_ENCODING = "UTF-16LE";
    private static final String MISSION_FILE_ENCODING = "US-ASCII";


    public void write(String name, Map<Integer, String> localization, String missionBody, String targetPath) throws IOException {

        File dir = new File(targetPath);
        if(!dir.exists()) dir.mkdirs();

        name = name.replaceAll(" ", "_");
        name = name.substring(0, name.length() > 15 ? 15 : name.length());

        StringBuilder buf = buildLocalizationString(localization);
        FileOutputStream fos1 = new FileOutputStream(
                new File(targetPath + name + ".eng"));
        IOUtils.write(buf.toString(),
                fos1
                , TRANSLATION_FILE_ENCODING
        );
        IOUtils.closeQuietly(fos1);

        FileOutputStream fos2 = new FileOutputStream(
                new File(targetPath + name + ".Mission"));
        IOUtils.write(missionBody,
                fos2
                , MISSION_FILE_ENCODING
        );
        IOUtils.closeQuietly(fos2);

    }

    private StringBuilder buildLocalizationString(Map<Integer, String> localization) {
        StringBuilder buf = new StringBuilder();
        localization.entrySet().stream().forEach(entry -> buf.append(entry.getKey()).append(":").append(entry.getValue() != null ? entry.getValue() : "").append("\r\n"));
        return buf;
    }

    public void writeLstFile(String name, Map<Integer, String> localization, String path) throws IOException {

        StringBuilder loc = buildLocalizationString(localization);
        int crc32 = HashUtil.ModRTU_CRC(loc.toString().getBytes(), loc.toString().length());
        String hash = Integer.toHexString(crc32);

        File dir = new File(path);
        if(!dir.exists()) dir.mkdirs();

        name = name.replaceAll(" ", "_");
        name = name.substring(0, name.length() > 15 ? 15 : name.length());

        StringBuilder buf = new StringBuilder();
        buf.append("filename=\"multiplayer/dogfight/" + name + ".eng\",\"" + hash + "\"");

        FileOutputStream fos1 = new FileOutputStream(
                new File(path + name + ".list"));
        IOUtils.write(buf.toString(),
                fos1
                , TRANSLATION_FILE_ENCODING
        );
        IOUtils.closeQuietly(fos1);
    }
}
