package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.model.*;

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

    public static ShopAddress createShopAddress(ResultSet rs){
        try {
            ShopAddress shopAddress = new ShopAddress();
            shopAddress.setId(rs.getInt("id"));
            shopAddress.setCity(rs.getString("city_name"));
            shopAddress.setStreet(rs.getString("street"));
            shopAddress.setHouseNumber(rs.getInt("house_number"));
            return shopAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
