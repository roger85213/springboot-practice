package com.roger.practice.dao.Impl;

import com.roger.practice.dao.PointDao;
import com.roger.practice.dto.CreatePointProduct;
import com.roger.practice.dto.PointQueryParams;
import com.roger.practice.model.Point;
import com.roger.practice.rowmapper.PointRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PointDaoImpl implements PointDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer createPointProduct(CreatePointProduct createPointProduct) {
        String sql = "INSERT INTO point(product_name, category, point, created_date) " +
                "VALUES (:productName, :category, :point, :createdDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("productName", createPointProduct.getProductName());
        map.put("category", createPointProduct.getCategory());
        map.put("point", createPointProduct.getPoint());
        Date now = new Date();
        map.put("createdDate",now );

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public Point getPointProduct(Integer productId) {
    String sql = "SELECT product_id, product_name, category, point, created_date " +
            "FROM point WHERE product_id = :productId";
    Map<String, Object> map = new HashMap<>();
    map.put("productId", productId);
    List<Point> pointList = namedParameterJdbcTemplate.query(sql,map, new PointRowMapper());
    if (pointList.size()>0){
        return pointList.get(0);
    }else {
        return null;
    }
    }

    @Override
    public List<Point> getPointProducts(PointQueryParams pointQueryParams) {
        String sql = "SELECT product_id, product_name, category, point, created_date FROM point WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        if(pointQueryParams.getCategory() != null){
            sql = sql + " AND category= :category";
            map.put("category", pointQueryParams.getCategory());
        }

        sql = sql + " ORDER BY " + pointQueryParams.getOrderBy() + " " + pointQueryParams.getSort();

        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", pointQueryParams.getLimit());
        map.put("offset", pointQueryParams.getOffset());

        List<Point> pointList = namedParameterJdbcTemplate.query(sql,map, new PointRowMapper());

        return pointList;
    }

    @Override
    public Integer countPointProducts(PointQueryParams pointQueryParams) {
    String sql =  "SELECT count(*) FROM point WHERE 1=1";
    Map<String, Object> map = new HashMap<>();
    if (pointQueryParams.getCategory() != null){
        sql= sql + " AND category = :category";
        map.put("category", pointQueryParams.getCategory());
    }

    Integer total = namedParameterJdbcTemplate.queryForObject(sql,map, Integer.class);
    return total;

    }
}
