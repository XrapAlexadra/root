package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.OrderDTO;
import com.github.xrapalexandra.kr.model.Product;
import com.github.xrapalexandra.kr.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {

    public static Product createProduct(ResultSet rs){
         try {
            Product product = new Product(
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    rs.getInt("price"));
            product.setId(rs.getInt("product_id"));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static OrderDTO createOrderDTO(ResultSet rs){
        try {
            OrderDTO order = new OrderDTO();
            order.setId(rs.getInt("id"));
            order.setQuantity(rs.getInt("quantity"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getInt("price"));
            order.setUserLogin(rs.getString("login"));
            order.setStatus(Status.valueOf(rs.getString("status")));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Order createOrder(ResultSet rs){
        try {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setProductId(rs.getInt("product_id"));
            order.setQuantity(rs.getInt("quantity"));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
