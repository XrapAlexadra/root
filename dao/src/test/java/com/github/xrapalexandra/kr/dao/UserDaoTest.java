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
        assertNotEquals(0, userDao.saveUser(user));
    }

    @Test
    void getUserByLoginIsExsist(){
        User user = new User("user", Role.USER, "user345");
        userDao.saveUser(user);
        assertEquals(user, userDao.getUserByLogin(user.getLogin()));
    }

    @Test
    void getUserByLoginNoExsist(){
        assertNull(userDao.getUserByLogin("popolo"));
    }
}
