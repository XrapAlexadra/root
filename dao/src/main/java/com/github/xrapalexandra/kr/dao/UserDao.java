package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.model.User;

public interface UserDao {

    int saveUser(User user);

    User getUserByLogin(String login);

    void delUser(User user);
}
