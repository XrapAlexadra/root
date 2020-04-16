package com.github.xrapalexandra.kr.service;

import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.OrderDTO;
import com.github.xrapalexandra.kr.model.Status;

import java.util.List;

public interface BasketService {

    void addOrder (Order order);

    List<OrderDTO> getOrdersByUserId(int user_id);

    List<OrderDTO> getAllOrders(int page);

    void changeOrderStatus(int orderId, Status status);

    List<Order> getPaidOrders();

    void delOrder(Order order);
}
