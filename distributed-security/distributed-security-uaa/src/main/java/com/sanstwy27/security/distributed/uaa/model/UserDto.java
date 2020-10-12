package com.sanstwy27.security.distributed.uaa.model;

import lombok.Data;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
