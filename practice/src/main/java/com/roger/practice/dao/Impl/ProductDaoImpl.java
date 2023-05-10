package com.roger.practice.dao.Impl;

import com.roger.practice.dao.ProductDao;
import com.roger.practice.dto.CreateProductRequest;
import com.roger.practice.dto.ProductQueryParams;
import com.roger.practice.model.Product;
import com.roger.practice.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.rmi.MarshalledObject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductByProductId(Integer productId) {

        String sql = "SELECT product_id, product_name,category , price, created_date, last_modified_date " +
                "FROM product WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        if (productList.size()> 0){
            return productList.get(0);
        }else {
            return null;
        }
    }


    @Override
    public Integer createProduct(CreateProductRequest createProductRequest) {
        String sql = "INSERT INTO product(product_name, category, price, created_date, last_modified_date) " +
                "VALUES(:productName, :category, :price, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("productName", createProductRequest.getProductName());
        map.put("category", createProductRequest.getCategory());
        map.put("price", createProductRequest.getPrice());

        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int productId = keyHolder.getKey().intValue();
        return productId;

    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id, product_name, category, price, created_date, last_modified_date " +
                "FROM product WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        if (productQueryParams.getCategory() != null){
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory());
        }

        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return productList;
    }

    @Override
    public Integer getTotal(ProductQueryParams productQueryParams) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        if (productQueryParams.getCategory() != null){
            sql =sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory());
        }
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }
}
