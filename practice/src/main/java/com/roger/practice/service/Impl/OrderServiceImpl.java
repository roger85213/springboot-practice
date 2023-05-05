package com.roger.practice.service.Impl;

import com.roger.practice.dao.OrderDao;
import com.roger.practice.dao.ProductDao;
import com.roger.practice.dao.UserDao;
import com.roger.practice.dto.BuyItem;
import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.OrderItem;
import com.roger.practice.model.Ordering;
import com.roger.practice.model.Product;
import com.roger.practice.model.User;
import com.roger.practice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        List<OrderItem> orderItemList = new ArrayList<>();

        int totalAmount = 0 ;

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()){

            Product product = productDao.getProductByProductId(buyItem.getProductId());

            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount =totalAmount + amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
        Integer orderId = orderDao.createOrder(userId,totalAmount,createOrderRequest);
        int point = totalAmount/5;
        userDao.update(userId,point);

        orderDao.createOrderItems(orderId,orderItemList);

        return orderId;

    }

    @Override
    public Ordering getOrderByOrderId(Integer userId,Integer orderId) {
        Ordering ordering = orderDao.getOrderByOrderId(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
        User user = userDao.getUserByUserId(userId);

        ordering.setOrderItemList(orderItemList);
        ordering.setUser(user);

        return ordering;
    }
}
