package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.dao.impl.DefaultProductDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultRatingDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultUserDao;
import com.github.xrapalexandra.kr.model.Product;
import com.github.xrapalexandra.kr.model.Rating;
import com.github.xrapalexandra.kr.model.Role;
import com.github.xrapalexandra.kr.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RatingDaoTest {

    private RatingDao ratingDao = DefaultRatingDao.getInstance();

    static User user;
    static Product product;
    static ProductDao productDao = DefaultProductDao.getInstance();
    static UserDao userDao = DefaultUserDao.getInstance();

    @BeforeAll
    private static void init(){
        user = new User("user16", Role.USER, "pass16");
        product = new Product("item16", 21, 30);
        user.setUserId(userDao.saveUser(user));
        product.setId(productDao.addProduct(product));
    }
    @Test
    void addRating(){
        Rating rating = new Rating(5, user.getUserId(), product.getId());
        rating.setRatingId(ratingDao.addRating(rating));
        assertNotEquals(0, rating.getRatingId());
        ratingDao.delRating(rating);
    }

    @Test
    void getAvrRatingByProductId(){
        Rating rating = new Rating(5, user.getUserId(), product.getId());
        rating.setRatingId(ratingDao.addRating(rating));
        assertEquals(rating.getRating(), Math.round(ratingDao.getAvrRatingByProductId(rating.getProductId())));
        ratingDao.delRating(rating);
    }

    @Test
    void delRating(){
        Rating rating = new Rating(5, user.getUserId(), product.getId());
        rating.setRatingId(ratingDao.addRating(rating));
        ratingDao.delRating(rating);
        assertEquals(0, Math.round(ratingDao.getAvrRatingByProductId(rating.getProductId())));
    }
    @AfterAll
    private static void cleanBD(){
        userDao.delUser(user);
        productDao.delProduct(product.getId());
    }
}
