package com.roger.practice.rowmapper;

import com.roger.practice.model.Ordering;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderingRowMapper implements RowMapper<Ordering> {
@Override
    public Ordering mapRow(ResultSet resultSet, int i) throws SQLException {

        Ordering ordering = new Ordering();

        ordering.setOrderId(resultSet.getInt("order_id"));
        ordering.setForm(resultSet.getString("form"));
        ordering.setNumber(resultSet.getInt("number"));
        ordering.setTotalAmount(resultSet.getInt("total_amount"));
        ordering.setCreatedDate(resultSet.getTimestamp("created_date"));
        ordering.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        ordering.setPoint(resultSet.getInt("point"));
        ordering.setUserId(resultSet.getInt("user_id"));

        return ordering;

    }

    }
