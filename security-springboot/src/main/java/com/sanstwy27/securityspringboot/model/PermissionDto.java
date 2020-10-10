package com.sanstwy27.securityspringboot.model;

import lombok.Data;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Data
public class PermissionDto {
    private String id;
    private String code;
    private String description;
    private String url;
}
