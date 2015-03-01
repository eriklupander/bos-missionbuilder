package se.lu.bos.misgen.serializer.skin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.model.PlaneType;
import se.lu.bos.misgen.util.EnvUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-01
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SkinManager {

    @Autowired
    EnvUtil envUtil;

    public Map<PlaneType, List<String>> getSkins() {
        Map<PlaneType, List<String>> skins = new HashMap<>();
        for(PlaneType pt : PlaneType.values() ) {
            skins.put(pt, getSkins(pt));
        }

        return skins;
    }

    public List<String> getSkins(PlaneType planeType) {
        String skinsRootDir = envUtil.getBasePath() + "graphics" + File.separator + "skins" + File.separator + planeType.getSkinFolder();
        File dir = new File(skinsRootDir);
        List<String> skins = new ArrayList<>();
        for(String s : dir.list()) {
            skins.add(s);
        }

        skins.add(0, "Default");



        return skins;
    }
}
