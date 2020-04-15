package com.github.xrapalexandra.kr.service;

import com.github.xrapalexandra.kr.dao.UserDao;
import com.github.xrapalexandra.kr.model.Role;
import com.github.xrapalexandra.kr.model.User;
import com.github.xrapalexandra.kr.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserDao userDao;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    void saveUserInDB(){

        when(userDao.getUserByLogin(any())).thenReturn(null);
        when(userDao.saveUser(any())).thenReturn(10);
        User user = new User("qwerty", Role.USER, "123456");
        assertEquals(10, userService.saveUserInDB(user).getUserId());
    }

    @Test
    void saveUserInDBAlreadyExsist(){
        User user = new User("qwerty", Role.USER, "123456");
        when(userDao.getUserByLogin(any())).thenReturn(user);
        assertNull(userService.saveUserInDB(user));
    }

    @Test
    void login(){
        User user = new User("qwerty", Role.USER, "123456");
        when(userDao.getUserByLogin(any())).thenReturn(user);
        assertEquals(user, userService.login(user.getLogin(), user.getPass()));
    }

    @Test
    void loginWithNotRightPass(){
        User user = new User("qwerty", Role.USER, "123456");
        when(userDao.getUserByLogin(any())).thenReturn(user);
        assertNull(userService.login(user.getLogin(), "qwerty"));
    }

    @Test
    void loginUserNotExsist(){
        when(userDao.getUserByLogin(any())).thenReturn(null);
        assertNull(userService.login("qwerty", "123456"));
    }
}
