package com.roger.practice.service;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.Ordering;

public interface OrderService {

Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

Ordering getOrderByOrderId(Integer userId,Integer orderId);
}
