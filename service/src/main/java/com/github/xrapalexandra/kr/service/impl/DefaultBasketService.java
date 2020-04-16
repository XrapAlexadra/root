package com.github.xrapalexandra.kr.service.impl;

import com.github.xrapalexandra.kr.dao.BasketDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultBasketDao;
import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.OrderDTO;
import com.github.xrapalexandra.kr.model.Status;
import com.github.xrapalexandra.kr.service.BasketService;

import java.util.List;

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

    @Override
    public List<OrderDTO> getOrdersByUserId(int user_id) {
        return basketDao.getUserOrders(user_id);
    }

    @Override
    public List<OrderDTO> getAllOrders(int page) {
        return basketDao.getAllOrders(page);
    }

    @Override
    public void changeOrderStatus(int orderId, Status status) {
        basketDao.changeOrderStatus(orderId, status);
    }

    @Override
    public List<Order> getPaidOrders() {
        return basketDao.getPaidOrders();
    }

    @Override
    public void delOrder(Order order) {
        basketDao.delOrder(order);
    }
}
