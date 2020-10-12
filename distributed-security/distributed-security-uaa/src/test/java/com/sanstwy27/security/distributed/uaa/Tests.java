package com.sanstwy27.security.distributed.uaa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Sanstwy27
 * @create 10/11/2020
 */

@SpringBootTest
public class Tests {

    @Test
    void contextLoads() {
        System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("secret"));
    }
}
