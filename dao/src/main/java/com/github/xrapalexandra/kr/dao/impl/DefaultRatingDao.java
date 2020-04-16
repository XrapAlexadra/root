package com.github.xrapalexandra.kr.dao.impl;

import com.github.xrapalexandra.kr.dao.DataSource;
import com.github.xrapalexandra.kr.dao.RatingDao;
import com.github.xrapalexandra.kr.model.Rating;

import java.sql.*;

public class DefaultRatingDao implements RatingDao {

    private static volatile RatingDao instance;

    public static RatingDao getInstance() {
        RatingDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RatingDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new DefaultRatingDao();

            }
        }
        return localInstance;
    }

    @Override
    public int addRating(Rating rating) {
        final String query = "INSERT INTO rating_product (rating, user_id, product_id) " +
                "VALUES (?, ?, ?);";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, rating.getRating());
            statement.setInt(2, rating.getUserId());
            statement.setInt(3, rating.getProductId());
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
    public void delRating(Rating rating) {
        final String query = "DELETE FROM rating_product WHERE rating_id = ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rating.getRatingId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException(rating + " don't delete!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getAvrRatingByProductId(int productId) {
        final String query =
                "SELECT AVG(r.rating) FROM (SELECT rating FROM rating_product WHERE product_id = ? ) AS r;";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next())
                    return rs.getDouble(1);

                else
                    return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
