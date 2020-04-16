package com.github.xrapalexandra.kr.dao.impl;

import com.github.xrapalexandra.kr.dao.DataSource;
import com.github.xrapalexandra.kr.dao.ShopAddressDao;
import com.github.xrapalexandra.kr.dao.Utils;
import com.github.xrapalexandra.kr.model.ShopAddress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultShopAddressDao implements ShopAddressDao {

    private static volatile ShopAddressDao instance;

    public static ShopAddressDao getInstance() {
        ShopAddressDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ShopAddressDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new DefaultShopAddressDao();

            }
        }
        return localInstance;
    }

    @Override
    public int addAddress(ShopAddress shopAddress) {
        final String query = "INSERT INTO shop_address (city_id, street, house_number)" +
                " VALUES ((SELECT city_id FROM city WHERE city_name = ?), ?, ?);";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, shopAddress.getCity());
            statement.setString(2, shopAddress.getStreet());
            statement.setInt(3, shopAddress.getHouseNumber());
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
    public void delAddress(ShopAddress shopAddress) {
        final String query = "DELETE FROM shop_address WHERE id_shop = ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, shopAddress.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException(shopAddress + " don't delete! ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAddress(ShopAddress newShopAddress) {
        final String query = "UPDATE shop_address SET " + "" +
                "street = ?, house_number = ?, city_id = (SELECT city_id FROM city WHERE city_name = ?) " +
                "WHERE id_shop = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newShopAddress.getStreet());
            statement.setInt(2, newShopAddress.getHouseNumber());
            statement.setString(3, newShopAddress.getCity());
            statement.setInt(4, newShopAddress.getId());
            statement.executeUpdate();
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("ShopAddress " + newShopAddress.getId() + " don't update with " + newShopAddress);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ShopAddress> getShopAddressList() {
        final String query = "SELECT sa.id_shop, sa.house_number, sa.street, c.city_name FROM shop_address AS sa" +
                " JOIN city AS c ON sa.city_id = c.city_id";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                final List<ShopAddress> shopAddressesList = new ArrayList<>();
                while (rs.next()) {
                    shopAddressesList.add(Utils.createShopAddress(rs));
                }
                if(shopAddressesList.size() == 0)
                    return null;
                else
                    return shopAddressesList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
