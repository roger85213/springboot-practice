package com.roger.practice.dao;

import com.roger.practice.dto.CreatePointProduct;
import com.roger.practice.model.Point;

public interface PointDao {

    Integer createPointProduct(CreatePointProduct createPointProduct);

    Point getPointProduct(Integer productId);
}
