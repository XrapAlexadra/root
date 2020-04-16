package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.model.Status;
import com.github.xrapalexandra.kr.service.BasketService;
import com.github.xrapalexandra.kr.service.impl.DefaultBasketService;
import com.github.xrapalexandra.kr.web.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeBasketServlet extends HttpServlet {

    BasketService basketService = DefaultBasketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Status status = WebUtils.createStatus(req.getParameter("newStatus"));
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        basketService.changeOrderStatus(orderId, status);

        try {
            resp.sendRedirect("/web/adminBasket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
