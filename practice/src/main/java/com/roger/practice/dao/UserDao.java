package com.roger.practice.dao;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.dto.CreateUserRequest;
import com.roger.practice.dto.UserLoginRequest;
import com.roger.practice.model.User;

import java.util.List;

public interface UserDao {

    void update(CreateOrderRequest createOrderRequest, Integer point);
   User getUserByUserId(CreateOrderRequest createOrderRequest);

   User getUserByIntUserId(Integer userId);

   Integer createUser(CreateUserRequest createUserRequest);
   User getUserByEmail(String email);


}
