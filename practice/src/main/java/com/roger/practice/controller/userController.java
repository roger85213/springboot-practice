package com.roger.practice.controller;

import com.roger.practice.dao.OrderDao;
import com.roger.practice.dao.UserDao;
import com.roger.practice.dto.CreateUserRequest;
import com.roger.practice.dto.UserLoginRequest;
import com.roger.practice.model.Ordering;
import com.roger.practice.model.User;
import com.roger.practice.service.OrderService;
import com.roger.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @PostMapping("user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest){

        Integer userId = userService.createUser(createUserRequest);
        User user = userService.getUserByIntUserId(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest){

        User user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }


    @GetMapping("/user/history/{userId}")
    public ResponseEntity<?> checkHistory(@PathVariable Integer userId){

        List<Ordering> orderingList = orderService.getOrderByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(orderingList);

    }



}
