package com.sanstwy27.securityspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@SpringBootTest
class SecuritySpringbootApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123"));
        System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("456"));
    }

}
