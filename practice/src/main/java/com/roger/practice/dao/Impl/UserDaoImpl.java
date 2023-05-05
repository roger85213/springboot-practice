package com.roger.practice.dao.Impl;

import com.roger.practice.dao.UserDao;
import com.roger.practice.model.User;
import com.roger.practice.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void update(Integer userId, Integer point) {

        String sql = "UPDATE user SET point = :point, last_modified_date = :lastModifiedDate WHERE user_id = :userId";

        Map map = new HashMap();
        map.put("point", point);
        map.put("userId", userId);
        map.put("lastModifiedDate", new Date());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        String sql = "SELECT user_id,email, password, point, created_date, last_modified_date FROM user " +
                "WHERE user_id = :userId";
        Map<String,Object> map = new HashMap();
        map.put("userId", userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if (userList.size() > 0){
            return userList.get(0);
        }else {
            return null;
        }
    }
}
