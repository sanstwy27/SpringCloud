package com.sanstwy27.security.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Data
@AllArgsConstructor
public class UserDao {
    public static final String SESSION_USER_KEY = "_user";

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    /**
     * User authorities
     */
    private Set<String> authorities;
}
