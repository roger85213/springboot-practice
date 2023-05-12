package com.roger.practice.service;

import com.roger.practice.dto.CreatePointProduct;
import com.roger.practice.dto.PointQueryParams;
import com.roger.practice.model.Point;

import java.util.List;

public interface PointService {

    Integer createPointProduct(CreatePointProduct createPointProduct);

    Point getPointProduct(Integer productId);

    List<Point> getPointProducts(PointQueryParams pointQueryParams);

    Integer countPointProducts(PointQueryParams pointQueryParams);
}
