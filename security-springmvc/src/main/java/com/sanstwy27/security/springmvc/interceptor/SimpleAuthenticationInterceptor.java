package com.sanstwy27.security.springmvc.interceptor;

import com.sanstwy27.security.springmvc.model.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    // Verify url and user authentication relationship
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // User info
        Object object = request.getSession().getAttribute(UserDao.SESSION_USER_KEY);
        if (object == null) {
            writeContent(response, "please login");
        }

        UserDao userDto = (UserDao) object;
        String requestURI = request.getRequestURI();
        if (userDto.getAuthorities().contains("p1") && requestURI.contains("/r/r1")) {
            return true;
        }
        if (userDto.getAuthorities().contains("p2") && requestURI.contains("/r/r2")) {
            return true;
        }
        writeContent(response, "request denied.");

        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
    }
}
