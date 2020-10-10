package com.sanstwy27.securityspringboot.service;

import com.sanstwy27.securityspringboot.dao.UserDao;
import com.sanstwy27.securityspringboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null) {
            // invalid user, return null
            // provider will throw an exception
            return null;
        }

        // query permission by user id
        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(userDto.getUsername())
                                      .password(userDto.getPassword())
                                      .authorities(permissionArray).build();
        return userDetails;
    }
}
