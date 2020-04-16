package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.dao.impl.DefaultBasketDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultProductDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultUserDao;
import com.github.xrapalexandra.kr.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class BasketDaoTest {

    private BasketDao basketDao = DefaultBasketDao.getInstance();

    static User user;
    static Product product;
    static ProductDao productDao = DefaultProductDao.getInstance();
    static UserDao userDao = DefaultUserDao.getInstance();

    @BeforeAll
    private static void init(){
        user = new User("user15", Role.USER, "pass15");
        product = new Product("item15", 20, 67);
        user.setUserId(userDao.saveUser(user));
        product.setId(productDao.addProduct(product));
    }

    @Test
    void addOrder() {
        Order order = new Order(user.getUserId(), product.getId(), 3);
        order.setId(basketDao.addOrder(order));
        assertNotEquals(0, order.getId());
        basketDao.delOrder(order);
    }

    @Test
    void delOrder(){
        Order order = new Order(user.getUserId(), product.getId(), 6);
        order.setId(basketDao.addOrder(order));
        basketDao.delOrder(order);
        assertNull(basketDao.getUserOrders(user.getUserId()));
    }

    @Test
    void getUserOrdersNull(){
        assertNull(basketDao.getUserOrders(user.getUserId()));
    }

    @Test
    void getUserOrders(){
        Order order = new Order(user.getUserId(), product.getId(), 1);
        order.setId(basketDao.addOrder(order));
        assertEquals(order.getId(),basketDao.getUserOrders(user.getUserId()).get(0).getId());
        basketDao.delOrder(order);
    }

    @Test
    void changeOrderStatus(){
        Order order = new Order(user.getUserId(), product.getId(), 6);
        order.setId(basketDao.addOrder(order));
        basketDao.changeOrderStatus(order.getId(), Status.PAID);
        assertEquals(Status.PAID, basketDao.getUserOrders(user.getUserId()).get(0).getStatus());
        basketDao.delOrder(order);
    }

    @Test
    void getAllOrders(){
        Order order = new Order(user.getUserId(), product.getId(), 2);
        order.setId(basketDao.addOrder(order));
        assertNotNull(basketDao.getAllOrders(1));
        basketDao.delOrder(order);
    }

    @Test
    void getPaidOrders(){
        Order order = new Order(user.getUserId(), product.getId(), 2);
        order.setStatus(Status.PAID);
        order.setId(basketDao.addOrder(order));
        assertNotNull(basketDao.getPaidOrders());
        basketDao.delOrder(order);
    }
    @AfterAll
    private static void cleanBD(){
        userDao.delUser(user);
        productDao.delProduct(product.getId());
    }
}
