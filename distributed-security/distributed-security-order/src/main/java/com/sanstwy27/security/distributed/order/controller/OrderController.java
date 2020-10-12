package com.sanstwy27.security.distributed.order.controller;

import com.sanstwy27.security.distributed.order.model.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanstwy27
 * @create 10/11/2020
 */
@RestController
public class OrderController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')") // authority access this url
    public String r1(){
        // user info
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getFullname() + " resource 1";
    }

}
