package com.github.xrapalexandra.kr.dao.impl;

import com.github.xrapalexandra.kr.dao.BasketDao;
import com.github.xrapalexandra.kr.dao.DataSource;
import com.github.xrapalexandra.kr.dao.Utils;
import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultBasketDao implements BasketDao {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static volatile BasketDao instance;

    public static BasketDao getInstance() {
        BasketDao localInstance = instance;
        if (localInstance == null) {
            synchronized (BasketDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new DefaultBasketDao();

            }
        }
        return localInstance;
    }

    @Override
    public int addOrder(Order order) {
        final String query = "INSERT INTO basket (product_id, quantity, user_id, status_id) " +
                "VALUES (?, ?, ?, (SELECT status_id FROM status WHERE status = ?));";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getProductId());
            statement.setInt(2, order.getQuantity());
            statement.setInt(3, order.getUserId());
            statement.setString(4, order.getStatus().name());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                logger.info("addOrder");
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delOrder(Order order) {

    }

    private final static int LIMIT = 20;

    @Override
    public List<OrderDTO> getAllOrders(int page) {
        final int offset = LIMIT * (page - 1);
        final String query = "SELECT b.id, b.quantity, p.product_name, p.price, u.login, s.status" +
                "    FROM basket AS b" +
                "    JOIN products AS p on b.product_id = p.product_id" +
                "    JOIN users AS u on b.user_id = u.user_id" +
                "    JOIN status s on b.status_id = s.status_id " +
                "ORDER BY u.login LIMIt ? OFFSET ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setInt(1, LIMIT);
            statement.setInt(2, offset);
            try (ResultSet rs = statement.executeQuery()) {
                final List<OrderDTO> orderDTOList = new ArrayList<>();
                while (rs.next()) {
                    orderDTOList.add(Utils.createOrderDTO(rs));
                }
                return orderDTOList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDTO> getUserOrders(int user_id) {
        final String query = "SELECT b.id, b.quantity, p.product_name, p.price, u.login, s.status\n" +
                "    FROM basket AS b\n" +
                "    JOIN products AS p on b.product_id = p.product_id\n" +
                "    JOIN users AS u on b.user_id = u.user_id\n" +
                "    JOIN status s on b.status_id = s.status_id WHERE b.user_id = ?\n" +
                "ORDER BY s.status;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user_id);
            try (ResultSet rs = statement.executeQuery()) {
                final List<OrderDTO> orderDTOList = new ArrayList<>();
                while (rs.next()) {
                    orderDTOList.add(Utils.createOrderDTO(rs));
                }
                return orderDTOList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}