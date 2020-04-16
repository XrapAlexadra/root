package com.github.xrapalexandra.kr.dao.impl;

import com.github.xrapalexandra.kr.dao.DataSource;
import com.github.xrapalexandra.kr.dao.ProductDao;
import com.github.xrapalexandra.kr.dao.Utils;
import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultProductDao implements ProductDao {

    private static volatile ProductDao instance;

    public static ProductDao getInstance() {
        ProductDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ProductDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new DefaultProductDao();

            }
        }
        return localInstance;
    }

    @Override
    public int addProduct(Product product) {
        final String query = "INSERT INTO products (product_name, price, quantity) VALUES (?, ?, ?);";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdProduct(Product product) {
        final String query =
                "SELECT product_id FROM products WHERE product_name = ? AND price = ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next())
                    return rs.getInt("product_id");
                else
                    return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    final static int LIMIT = 20;

    @Override
    public List<Product> getProductList(int page) {
        final int offset = LIMIT * (page - 1);
        final String query = "SELECT * FROM products ORDER BY product_name LIMIT ? OFFSET ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, LIMIT);
            statement.setInt(2, offset);
            try (ResultSet rs = statement.executeQuery()) {
                final List<Product> productList = new ArrayList<>();
                while (rs.next()) {
                    productList.add(Utils.createProduct(rs));
                }
                return productList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        final String query = "UPDATE products SET product_name = ?, price = ?, quantity = ? WHERE product_id = ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException(product + " don't update! ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(int id) {
        final String query = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Utils.createProduct(rs);
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delProduct(int id) {
        final String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new RuntimeException("Can't delete product with ID: " + id + " from database!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProductQuantity(Order order) {
        int newQuantity = this.getProductById(order.getProductId()).getQuantity()- order.getQuantity();
        final String query = "UPDATE products SET quantity = ? WHERE product_id = ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, order.getProductId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("product" + order.getProductId() + " don't update with" + order + "! ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}