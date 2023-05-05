package com.roger.practice.controller;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.Ordering;
import com.roger.practice.model.User;
import com.roger.practice.service.OrderService;
import com.roger.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class orderController {

    @Autowired
    private OrderService orderService;
@PostMapping("/order/{userId}")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest,
                                         @PathVariable @Valid Integer userId){

    Integer orderId = orderService.createOrder(userId,createOrderRequest);
    Ordering ordering = orderService.getOrderByOrderId(userId,orderId);
    return ResponseEntity.status(HttpStatus.OK).body(ordering);


}



}
