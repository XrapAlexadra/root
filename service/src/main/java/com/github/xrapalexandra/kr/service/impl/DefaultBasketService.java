package com.github.xrapalexandra.kr.service.impl;

import com.github.xrapalexandra.kr.dao.BasketDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultBasketDao;
import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.service.BasketService;

public class DefaultBasketService implements BasketService {


    private DefaultBasketService() {
    }

    private static volatile BasketService instance;
    private BasketDao basketDao = DefaultBasketDao.getInstance();

    public static BasketService getInstance() {
        BasketService localInstance = instance;
        if (localInstance == null) {
            synchronized (BasketService.class) {
                localInstance = instance;
                if (localInstance == null)
                    localInstance = instance = new DefaultBasketService();
            }
        }
        return localInstance;
    }

    @Override
    public void addOrder(Order order) {
        basketDao.addOrder(order);
    }
}
