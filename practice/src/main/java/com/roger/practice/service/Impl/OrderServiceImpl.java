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
    public Integer createOrder(CreateOrderRequest createOrderRequest) {

        if (createOrderRequest.getUserId() != null) {

            List<OrderItem> orderItemList = new ArrayList<>();

            int totalAmount = 0;

            for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {

                Product product = productDao.getProductByProductId(buyItem.getProductId());

                int amount = buyItem.getQuantity() * product.getPrice();
                totalAmount = totalAmount + amount;

                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(buyItem.getProductId());
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setAmount(amount);

                orderItemList.add(orderItem);
            }
            Integer orderId = orderDao.createOrder(totalAmount, createOrderRequest);

            User user = userDao.getUserByUserId(createOrderRequest);

            int point = user.getPoint() + totalAmount / 5;
            userDao.update(createOrderRequest, point);

            orderDao.createOrderItems(orderId, orderItemList);

            return orderId;
        }else {
            List<OrderItem> orderItemList = new ArrayList<>();

            int totalAmount = 0;

            for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {

                Product product = productDao.getProductByProductId(buyItem.getProductId());

                int amount = buyItem.getQuantity() * product.getPrice();
                totalAmount = totalAmount + amount;

                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(buyItem.getProductId());
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setAmount(amount);

                orderItemList.add(orderItem);
            }
            Integer orderId = orderDao.createOrder(totalAmount, createOrderRequest);
            orderDao.createOrderItems(orderId, orderItemList);


            return orderId;
        }

    }

    @Override
    public Ordering getOrderByOrderId(CreateOrderRequest createOrderRequest,Integer orderId) {

        if (createOrderRequest.getUserId() != null){
        Ordering ordering = orderDao.getOrderByOrderId(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
        User user = userDao.getUserByUserId(createOrderRequest);

        ordering.setOrderItemList(orderItemList);
        ordering.setUser(user);

        return ordering;
        }else {
            Ordering ordering = orderDao.getOrderByOrderId(orderId);
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
            ordering.setOrderItemList(orderItemList);
            return ordering;

        }
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {


        return orderDao.getOrderItemByOrderId(orderId);
    }

    @Override
    public Ordering getOrderByFormNumber(String form, Integer number) {
        return orderDao.getOrderByFormNumber(form,number);

    }
}
