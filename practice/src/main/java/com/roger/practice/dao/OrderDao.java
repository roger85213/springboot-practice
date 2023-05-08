package com.roger.practice.dao;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.OrderItem;
import com.roger.practice.model.Ordering;

import java.util.List;

public interface OrderDao {

Integer createOrder(Integer pointForOne, Integer totalAmount, CreateOrderRequest createOrderRequest);
Integer createOrderWithoutUser(Integer totalAmount, CreateOrderRequest createOrderRequest);


void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

Ordering getOrderByOrderId(Integer orderId);
List<OrderItem> getOrderItemByOrderId(Integer orderId);

Ordering getOrderByFormNumber(String form, Integer number);
List<Ordering> getOrderByUserId(Integer userId);


}
