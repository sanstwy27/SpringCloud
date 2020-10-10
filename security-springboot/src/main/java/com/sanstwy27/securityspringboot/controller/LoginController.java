package com.sanstwy27.securityspringboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess() {
        return getUsername() + " login success";
    }

    /**
     * resource 1
     *
     * @return
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('p1')")
    public String r1() {
        return getUsername() + " get resource 1";
    }

    /**
     * resource 2
     *
     * @return
     */
    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('p2')")
    public String r2() {
        return getUsername() + " get resource 2";
    }

    private String getUsername() {
        // User hasAuthority
        String username = null;
        // User principle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "guest";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
