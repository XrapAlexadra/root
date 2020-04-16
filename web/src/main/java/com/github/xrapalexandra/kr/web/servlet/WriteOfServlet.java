package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.service.BasketService;
import com.github.xrapalexandra.kr.service.ProductService;
import com.github.xrapalexandra.kr.service.impl.DefaultBasketService;
import com.github.xrapalexandra.kr.service.impl.DefaultProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WriteOfServlet  extends HttpServlet{

    BasketService basketService = DefaultBasketService.getInstance();
    ProductService productService = DefaultProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<Order> paidOrders = basketService.getPaidOrders();
        for( Order order: paidOrders){
            productService.updateProductQuantity(order);
            basketService.delOrder(order);
        }

        try {
            resp.sendRedirect("/web/adminBasket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
