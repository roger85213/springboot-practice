package com.roger.practice.controller;

import com.roger.practice.dto.CreatePointProduct;
import com.roger.practice.model.Point;
import com.roger.practice.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pointController {

    @Autowired
    private PointService pointService;


    @PostMapping("/point/create")
    public ResponseEntity<?> createPointProduct (@RequestBody CreatePointProduct createPointProduct){

        Integer productId = pointService.createPointProduct(createPointProduct);
        Point point = pointService.getPointProduct(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(point);


    }
}
