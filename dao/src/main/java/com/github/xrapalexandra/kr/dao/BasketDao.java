package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.OrderDTO;
import com.github.xrapalexandra.kr.model.Status;

import java.util.List;

public interface BasketDao {

    int addOrder(Order order);

    void delOrder(Order order);

    List<OrderDTO> getAllOrders(int page);

    List<OrderDTO> getUserOrders(int user_id);

    void changeOrderStatus(int orderId, Status status);

    List<Order> getPaidOrders();
}
