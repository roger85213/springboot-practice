package com.roger.practice.service;

import com.roger.practice.dto.CreatePointProduct;
import com.roger.practice.model.Point;

public interface PointService {

    Integer createPointProduct(CreatePointProduct createPointProduct);

    Point getPointProduct(Integer productId);
}
