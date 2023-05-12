package com.roger.practice.service.Impl;

import com.roger.practice.dao.PointDao;
import com.roger.practice.dto.CreatePointProduct;
import com.roger.practice.model.Point;
import com.roger.practice.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointServiceImpl implements PointService {

    @Autowired
    private PointDao pointDao;

    @Override
    public Integer createPointProduct(CreatePointProduct createPointProduct) {
        return pointDao.createPointProduct(createPointProduct);
    }

    @Override
    public Point getPointProduct(Integer productId) {
        return pointDao.getPointProduct(productId);
    }
}
