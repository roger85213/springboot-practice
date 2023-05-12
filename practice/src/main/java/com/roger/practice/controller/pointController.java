package com.roger.practice.controller;

import com.roger.practice.dto.CreatePointProduct;
import com.roger.practice.dto.PointQueryParams;
import com.roger.practice.model.Point;
import com.roger.practice.service.PointService;
import com.roger.practice.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/point")
    public ResponseEntity<?> getPointProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            @RequestParam(defaultValue = "5") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset
    ){
        PointQueryParams pointQueryParams = new PointQueryParams();
        pointQueryParams.setCategory(category);
        pointQueryParams.setOrderBy(orderBy);
        pointQueryParams.setSort(sort);
        pointQueryParams.setLimit(limit);
        pointQueryParams.setOffset(offset);

        List<Point> pointList = pointService.getPointProducts(pointQueryParams);

        Integer total = pointService.countPointProducts(pointQueryParams);

        Page<Point> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(pointList);

        return ResponseEntity.status(HttpStatus.OK).body(page);




    }





}
