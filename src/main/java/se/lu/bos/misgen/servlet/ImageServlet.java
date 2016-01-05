package se.lu.bos.misgen.servlet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.lu.bos.misgen.util.EnvUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-04
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ImageServlet {

    @Autowired
    EnvUtil envUtil;

    @RequestMapping(value = "/thumbs", method = RequestMethod.GET, produces = "image/jpg")
    public @ResponseBody ResponseEntity<byte[]> getImage(@RequestParam String imageUrl) throws IOException {

        File file = new File(envUtil.getBasePath() + File.separator + "GUI" + File.separator + "preview" + File.separator + imageUrl);
        FileInputStream fileInputStream = FileUtils.openInputStream(file);

        byte[] data = IOUtils.toByteArray(fileInputStream);

        fileInputStream.close();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
