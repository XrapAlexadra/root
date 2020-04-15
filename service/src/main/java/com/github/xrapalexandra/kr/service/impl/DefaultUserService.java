package com.github.xrapalexandra.kr.service.impl;

import com.github.xrapalexandra.kr.dao.UserDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultUserDao;
import com.github.xrapalexandra.kr.model.User;
import com.github.xrapalexandra.kr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

public class DefaultUserService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private UserDao userDao = DefaultUserDao.getInstance();

    private DefaultUserService() {
    }

    private static volatile UserService instance;

    public static UserService getInstance(){
        UserService localInstance = instance;
        if (localInstance == null){
            synchronized (UserService.class){
                localInstance = instance;
                if ( localInstance == null)
                    localInstance = instance = new DefaultUserService();
            }
        }
        return localInstance;
    }

    @Override
    public User saveUserInDB(User user) {
        if(userDao.getUserByLogin(user.getLogin())== null) {
            user.setUserId(userDao.saveUser(user));
            logger.info("Save user: {} in table users.", user.getLogin());
            return user;
        }
        else {
            logger.info("Can't save user: {} in the table users, becouse user with this login is already exsist!", user.getLogin());
            return null;
        }
    }

    @Override
    public User login(String login, String pass) {
        User user = userDao.getUserByLogin(login);
        if (user == null) {
            logger.info("Incorrect data entry (login).");
            return null;
        }
        if (user.getPass().equals(pass)) {
            logger.info("User {} Authenticated.", user.getLogin());
            return user;
        }
        logger.info("User {} entered incorrect pass.", login);
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
