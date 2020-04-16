package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.dao.impl.DefaultUserDao;
import com.github.xrapalexandra.kr.model.Role;
import com.github.xrapalexandra.kr.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    public UserDao userDao = DefaultUserDao.getInstance();

    @Test
    void saveUser(){
        User user = new User("login", Role.USER, "user");
        user.setUserId(userDao.saveUser(user));
        assertNotEquals(0, user.getUserId());
        userDao.delUser(user);
    }

    @Test
    void getUserByLogin(){
        User user = new User("user", Role.USER, "user345");
        user.setUserId(userDao.saveUser(user));
        assertEquals(user, userDao.getUserByLogin(user.getLogin()));
        userDao.delUser(user);
    }

    @Test
    void getUserByLoginNoExist(){
        assertNull(userDao.getUserByLogin("прпр"));
    }

    @Test
    void delUser(){
        User user = new User("zxcvb", Role.USER, "425146");
        user.setUserId(userDao.saveUser(user));
        userDao.delUser(user);
        assertNull(userDao.getUserByLogin(user.getLogin()));
    }
}
