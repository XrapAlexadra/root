package com.github.xrapalexandra.kr.dao.impl;

import com.github.xrapalexandra.kr.dao.DataSource;
import com.github.xrapalexandra.kr.dao.UserDao;
import com.github.xrapalexandra.kr.model.Role;
import com.github.xrapalexandra.kr.model.User;

import java.sql.*;

public class DefaultUserDao implements UserDao {

    private static volatile UserDao instance;

    private DefaultUserDao() {
    }

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) localInstance = instance = new DefaultUserDao();
            }
        }
        return localInstance;
    }

    @Override
    public int saveUser(User user) {
        final String query = "INSERT INTO users (login, pass, role) VALUES (?, ?, (SELECT role_id FROM role WHERE role_name = ?));";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getRole().name().toLowerCase());
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
    public User getUserByLogin(String login) {
        final String query =
                "SELECT u.login, u.pass, u.user_id, r.role_name FROM users AS u JOIN role AS r ON u.role = r.role_id WHERE u.login = ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setLogin(rs.getString("login"));
                    user.setPass(rs.getString("pass"));
                    user.setRole(Role.valueOf(rs.getString("role_name").toUpperCase()));
                    return user;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delUser(User user) {
        if(user.getUserId() == 0)
            throw new RuntimeException(user + " cannot be deleted. UserId didn't set!");
        final String query = "DELETE FROM users WHERE user_id = ?;";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getUserId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException(user + " don't delete!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
