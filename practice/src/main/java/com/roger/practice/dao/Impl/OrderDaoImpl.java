package com.roger.practice.dao.Impl;

import com.roger.practice.dao.OrderDao;
import com.roger.practice.dto.CreateOrderRequest;
import com.roger.practice.model.OrderItem;
import com.roger.practice.model.Ordering;
import com.roger.practice.rowmapper.OrderItemRowMapper;
import com.roger.practice.rowmapper.OrderingRowMapper;
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
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount, CreateOrderRequest createOrderRequest) {
        String sql = "INSERT INTO ordering(form,number,total_amount,created_date,last_modified_date) " +
                "VALUES (:form, :number, :totalAmount, :createdDate, :lastModifiedDate)";

        Map<String,Object> map = new HashMap();
        map.put("form", createOrderRequest.getForm());
        map.put("number", createOrderRequest.getNumber());
        map.put("totalAmount",totalAmount);
        Date now = new Date();

        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int orderId = keyHolder.getKey().intValue();
        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount) " +
                "VALUES (:orderId, :productId, :quantity, :amount)";


        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];
        for (int i = 0; i<orderItemList.size(); i ++){

            OrderItem orderItem = orderItemList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId",orderItem.getProductId());
            parameterSources[i].addValue("quantity",orderItem.getQuantity());
            parameterSources[i].addValue("amount",orderItem.getAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);

    }

    @Override
    public Ordering getOrderByOrderId(Integer orderId) {
        String sql = "SELECT order_id,form,number,total_amount,created_date,last_modified_date " +
                "FROM ordering WHERE order_id = :orderId";
        Map<String,Object> map = new HashMap();
        map.put("orderId", orderId);

        List<Ordering> orderingList = namedParameterJdbcTemplate.query(sql,map,new OrderingRowMapper());
        if (orderingList.size() > 0){
            return orderingList.get(0);
        }else {

            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, " +
                "p.product_name " +
                "FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = :orderId";

        Map<String,Object> map = new HashMap();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql,map, new OrderItemRowMapper());
        return orderItemList;


    }
}
