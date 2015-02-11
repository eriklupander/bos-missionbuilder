package se.lu.bos.misgen.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-11
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class JSPController {

    @RequestMapping("/jsptest")
    public String test(ModelAndView modelAndView) {

        return "jsp-spring-boot";
    }

}

