package com.sanstwy27.security.distributed.uaa.dao;

import com.sanstwy27.security.distributed.uaa.model.PermissionDto;
import com.sanstwy27.security.distributed.uaa.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDto getUserByUsername(String username) {
        String sql = "select id, username, password, fullname, mobile from t_user where username = ?";
        // querying database
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public List<String> findPermissionsByUserId(String userId) {
        String sql = "SELECT * FROM t_permission WHERE id IN(\n" +
                        "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
                            "  SELECT role_id FROM t_user_role WHERE user_id = ? \n" +
                        ")\n" +
                    ")\n";

        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }
}
