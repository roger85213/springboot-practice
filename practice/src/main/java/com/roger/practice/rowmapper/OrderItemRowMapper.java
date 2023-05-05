package com.roger.practice.rowmapper;

import com.roger.practice.model.OrderItem;
import com.roger.practice.model.Ordering;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
@Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {

        OrderItem orderItem = new OrderItem();

        orderItem.setOrderItemId(resultSet.getInt("oi.order_item_id"));
        orderItem.setOrderId(resultSet.getInt("oi.order_id"));
        orderItem.setProductId(resultSet.getInt("oi.product_id"));
        orderItem.setQuantity(resultSet.getInt("oi.quantity"));
        orderItem.setAmount(resultSet.getInt("oi.amount"));

        //擴充

        orderItem.setProductName(resultSet.getString("p.product_name"));

        return orderItem;
    }
    }
