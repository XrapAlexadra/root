package com.github.xrapalexandra.kr.service;

import com.github.xrapalexandra.kr.model.User;

public interface UserService {

    User saveUserInDB(User user);

    User login(String login, String pass);

    User getUserByLogin(String login);

}
