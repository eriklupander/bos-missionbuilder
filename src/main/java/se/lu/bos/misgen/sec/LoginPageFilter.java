package se.lu.bos.misgen.sec;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = "/login.jsp")
public class LoginPageFilter { //implements Filter {


//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        if (request.getRemoteUser() != null) {
//            response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redirect to home page.
//        } else {
//            chain.doFilter(req, res);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }

    // Add/generate init() and destroy() with NOOP.
}
