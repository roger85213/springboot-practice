package com.roger.practice.service;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.OrderItem;
import com.roger.practice.model.Ordering;

import java.util.List;

public interface OrderService {

Integer createOrder(CreateOrderRequest createOrderRequest);

Ordering getOrderByOrderId(CreateOrderRequest createOrderRequest,Integer orderId);


List<OrderItem> getOrderItemByOrderId(Integer orderId);

Ordering getOrderByFormNumber(String form,Integer number);

}
