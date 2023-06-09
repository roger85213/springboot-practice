package com.roger.practice.dao;

import com.roger.practice.dto.CreateProductRequest;
import com.roger.practice.dto.ProductQueryParams;
import com.roger.practice.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProductByProductId(Integer productId);

    Integer createProduct(CreateProductRequest createProductRequest);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer getTotal (ProductQueryParams productQueryParams);


}
