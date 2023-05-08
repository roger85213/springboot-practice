package com.roger.practice.controller;

import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.OrderItem;
import com.roger.practice.model.Ordering;
import com.roger.practice.model.User;
import com.roger.practice.service.OrderService;
import com.roger.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class orderController {

    @Autowired
    private OrderService orderService;

    //創建訂單
@PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest){

    Integer orderId = orderService.createOrder(createOrderRequest);
    Ordering ordering = orderService.getOrderByOrderId(createOrderRequest,orderId);
    return ResponseEntity.status(HttpStatus.CREATED).body(ordering);


}

//查詢訂單

    @GetMapping("/order/{form}/{number}")
    public ResponseEntity<?> checkOrderByFormNumber(@PathVariable String form,
                                        @PathVariable Integer number){

        Ordering ordering = orderService.getOrderByFormNumber(form,number);

        //for(Ordering ordering1 : ordering) {
        List<OrderItem> orderItemList = orderService.getOrderItemByOrderId(ordering.getOrderId());

        //ordering1.setOrderItemList(orderItemList);

        //}

        return ResponseEntity.status(HttpStatus.OK).body(orderItemList);

    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> checkOrderByOrderId(@PathVariable Integer orderId){

    List<OrderItem> orderItemList = orderService.getOrderItemByOrderId(orderId);
    return ResponseEntity.status(HttpStatus.OK).body(orderItemList);

    }





}
