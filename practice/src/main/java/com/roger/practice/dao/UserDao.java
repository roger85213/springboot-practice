package com.roger.practice.dao;

import com.roger.practice.model.User;

import java.util.List;

public interface UserDao {

    void update(Integer userId, Integer point);
   User getUserByUserId(Integer userId);

}
