package com.sanstwy27.security.springmvc.controller;

import com.sanstwy27.security.springmvc.model.AuthenticationRequest;
import com.sanstwy27.security.springmvc.model.UserDao;
import com.sanstwy27.security.springmvc.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@RestController
public class LoginController {

    @Autowired
    IAuthenticationService iAuthenticationService;

    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
        UserDao userDto = iAuthenticationService.authentication(authenticationRequest);
        session.setAttribute(UserDao.SESSION_USER_KEY, userDto);
        return userDto.getUsername() + " login success";
    }

    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession session){
        session.invalidate();
        return "logout success";
    }

    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object object = session.getAttribute(UserDao.SESSION_USER_KEY);
        if (object == null) {
            fullname = "anonymous";
        } else {
            UserDao userDto = (UserDao) object;
            fullname = userDto.getFullname();
        }
        return fullname + " resource 1";
    }

    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session) {
        String fullname = null;
        Object userObj = session.getAttribute(UserDao.SESSION_USER_KEY);
        if (userObj != null) {
            fullname = ((UserDao) userObj).getFullname();
        } else {
            fullname = "anonymous";
        }
        return fullname + " resource 1";
    }
}
