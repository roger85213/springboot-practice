package com.roger.practice.dao.Impl;

import com.roger.practice.dao.UserDao;
import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.dto.CreateUserRequest;
import com.roger.practice.dto.UserLoginRequest;
import com.roger.practice.model.User;
import com.roger.practice.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public void update(CreateOrderRequest createOrderRequest,Integer point) {

        String sql = "UPDATE user SET point = :point, last_modified_date = :lastModifiedDate WHERE user_id = :userId";

        Map map = new HashMap();
        map.put("point", point);
        map.put("userId", createOrderRequest.getUserId());
        map.put("lastModifiedDate", new Date());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public User getUserByUserId(CreateOrderRequest createOrderRequest) {
        String sql = "SELECT user_id,email, password, point, created_date, last_modified_date FROM user " +
                "WHERE user_id = :userId";
        Map<String,Object> map = new HashMap();
        map.put("userId", createOrderRequest.getUserId());
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if (userList.size() > 0){
            return userList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User getUserByIntUserId(Integer userId) {
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

    @Override
    public Integer createUser(CreateUserRequest createUserRequest) {
        String sql = "INSERT INTO user (email, password, created_date, last_modified_date, point) " +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate, 0)";

        Map<String,Object> map = new HashMap<>();
        map.put("email", createUserRequest.getEmail());
        map.put("password", createUserRequest.getPassword());
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();
        return userId;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, point, created_date, last_modified_date " +
                "FROM user WHERE email = :email";
        Map<String,Object> map = new HashMap<>();
        map.put("email", email);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        if (userList.size()>0){
            return userList.get(0);
        }else {
            return null;
        }
    }
}
