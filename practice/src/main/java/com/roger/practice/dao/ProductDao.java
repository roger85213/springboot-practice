package com.roger.practice.dao;

import com.roger.practice.model.Product;

public interface ProductDao {

    Product getProductByProductId(Integer productId);
}
