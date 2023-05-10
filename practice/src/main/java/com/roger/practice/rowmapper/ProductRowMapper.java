package com.roger.practice.rowmapper;

import com.roger.practice.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        Product product = new Product();
        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setPrice(resultSet.getInt("price"));
        product.setCreatedDate(resultSet.getDate("created_date"));
        product.setLastModifiedDate(resultSet.getDate("last_modified_date"));
        product.setCategory(resultSet.getString("category"));
        return product;


    }
}
