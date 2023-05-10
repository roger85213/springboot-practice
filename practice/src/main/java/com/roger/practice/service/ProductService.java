package com.roger.practice.service;

import com.roger.practice.dto.CreateProductRequest;
import com.roger.practice.dto.ProductQueryParams;
import com.roger.practice.model.Product;

import java.util.List;

public interface ProductService {


    Integer createProduct(CreateProductRequest createProductRequest);

    Product getProductByProductId(Integer productId);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer getTotal (ProductQueryParams productQueryParams);


}
