package com.roger.practice.controller;

import com.roger.practice.dto.CreateProductRequest;
import com.roger.practice.dto.ProductQueryParams;
import com.roger.practice.model.Product;
import com.roger.practice.service.ProductService;
import com.roger.practice.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class productController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest){

        Integer productId = productService.createProduct(createProductRequest);

        Product product = productService.getProductByProductId(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "5") @Max(20) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParams);
        Integer total = productService.getTotal(productQueryParams);

        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);


    }




}
