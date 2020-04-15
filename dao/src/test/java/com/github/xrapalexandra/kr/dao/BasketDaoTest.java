package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.dao.impl.DefaultBasketDao;
import com.github.xrapalexandra.kr.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasketDaoTest {

    private BasketDao basketDao = DefaultBasketDao.getInstance();

//    @Test
//    void addOrder() {
//        Order order = new Order(8, 65, 5);
//        assertNotEquals(0, basketDao.addOrder(order));
//    }

    @Test
    void getAllOrders(){
        System.out.println(basketDao.getUserOrders(8));
    }
}
