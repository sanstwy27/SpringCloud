package com.sanstwy27.security.springmvc.service;

import com.sanstwy27.security.springmvc.model.AuthenticationRequest;
import com.sanstwy27.security.springmvc.model.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    private Map<String,UserDao> userMap = new HashMap<>();
    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        userMap.put("Alice", new UserDao("1010", "Alice", "123", "Alice", "133443", authorities1));

        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");
        userMap.put("Bob", new UserDao("1011", "Bob", "456", "Bob", "144553", authorities2));
    }

    @Override
    public UserDao authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("Username or password is empty.");
        }

        // Simulate DB
        UserDao user = getUserDao(authenticationRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        if (!authenticationRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Username or password is wrong.");
        }

        // Authenticate done
        return user;
    }

    private UserDao getUserDao(String userName){
        return userMap.get(userName);
    }
}
