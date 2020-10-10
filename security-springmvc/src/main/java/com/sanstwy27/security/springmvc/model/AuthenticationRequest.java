package com.sanstwy27.security.springmvc.model;

import lombok.Data;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
