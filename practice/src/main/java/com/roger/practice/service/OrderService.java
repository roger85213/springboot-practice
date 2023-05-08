package com.roger.practice.service;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.OrderItem;
import com.roger.practice.model.Ordering;

import java.util.List;

public interface OrderService {

Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

Ordering getOrderByOrderId(Integer userId,Integer orderId);


List<OrderItem> getOrderItemByOrderId(Integer orderId);

Ordering getOrderByFormNumber(String form,Integer number);

}
