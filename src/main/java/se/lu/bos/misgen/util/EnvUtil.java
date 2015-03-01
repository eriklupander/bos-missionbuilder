package se.lu.bos.misgen.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.rest.MissionDataServiceBean;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-01
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
@Component
public class EnvUtil {

    @Autowired
    Environment env;

    public String getBasePath() {
        String basePath = env.getProperty("bos.data.directory", MissionDataServiceBean.DEFAULT_DATA_DIR);
        if(!basePath.endsWith("\\")) {
            basePath = basePath + "\\";
        }
        return basePath;
    }

}
