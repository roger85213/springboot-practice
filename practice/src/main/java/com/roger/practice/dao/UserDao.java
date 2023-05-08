package com.roger.practice.dao;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.User;

import java.util.List;

public interface UserDao {

    void update(CreateOrderRequest createOrderRequest, Integer point);
   User getUserByUserId(CreateOrderRequest createOrderRequest);

}
