package se.lu.bos.misgen.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.lu.bos.misgen.dao.UserDao;
import se.lu.bos.misgen.sec.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Reg {

    private static final Logger log = LoggerFactory.getLogger(Reg.class);

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseEntity<String> doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cylon = req.getParameter("cylon");
        if(!(cylon.trim().equals("3") || cylon.trim().toLowerCase().equals("three"))) {
            return new ResponseEntity<String>("Not the answer I was expecting...", HttpStatus.BAD_REQUEST);
        }

        else {
            String username = req.getParameter("username").replaceAll("['\"\\\\]", "\\\\$0");;
            String password1 = req.getParameter("password").replaceAll("['\"\\\\]", "\\\\$0");;
            String password2 = req.getParameter("password2").replaceAll("['\"\\\\]", "\\\\$0");;
            if(!password1.equals(password2)) {
                return new ResponseEntity<String>("Password must match.", HttpStatus.BAD_REQUEST);
            }

            User user = userDao.findUser(username);
            if(user != null) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {}
                return new ResponseEntity<String>("Username not available", HttpStatus.BAD_REQUEST);
            }

            User user1 = userDao.createUser(username, password1);
            log.info("User "  + user1.getUsername() + " created");
            return new ResponseEntity<String>("User created! You can now login <a href=\"/login.jsp\">here</a>", HttpStatus.OK);
        }
    }
}
