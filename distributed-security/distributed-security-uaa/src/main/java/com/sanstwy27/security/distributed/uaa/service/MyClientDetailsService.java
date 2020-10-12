package com.sanstwy27.security.distributed.uaa.service;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author Sanstwy27
 * @create 10/12/2020
 */
@Service
public class MyClientDetailsService extends JdbcClientDetailsService {
    public MyClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }
}
