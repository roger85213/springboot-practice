package com.roger.practice.rowmapper;

import com.roger.practice.model.Point;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PointRowMapper implements RowMapper<Point> {
    @Override
    public Point mapRow(ResultSet resultSet, int i) throws SQLException {
        Point point = new Point();
        point.setProductId(resultSet.getInt("product_id"));
        point.setProductName(resultSet.getString("product_name"));
        point.setPoint(resultSet.getInt("point"));
        point.setCategory(resultSet.getString("category"));
        point.setCreatedDate(resultSet.getTimestamp("created_date"));

        return point;
    }
}
