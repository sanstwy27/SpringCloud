package com.sanstwy27.security.springmvc.service;

import com.sanstwy27.security.springmvc.model.AuthenticationRequest;
import com.sanstwy27.security.springmvc.model.UserDao;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */

public interface IAuthenticationService {
    UserDao authentication(AuthenticationRequest authenticationRequest);
}
