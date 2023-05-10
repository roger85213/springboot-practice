package com.roger.practice.service.Impl;

import com.roger.practice.dao.ProductDao;
import com.roger.practice.dto.CreateProductRequest;
import com.roger.practice.dto.ProductQueryParams;
import com.roger.practice.model.Product;
import com.roger.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer createProduct(CreateProductRequest createProductRequest) {
        return productDao.createProduct(createProductRequest);
    }


    @Override
    public Product getProductByProductId(Integer productId) {
        return productDao.getProductByProductId(productId);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Integer getTotal(ProductQueryParams productQueryParams) {
        return productDao.getTotal(productQueryParams);
    }
}
