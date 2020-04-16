package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.Status;
import com.github.xrapalexandra.kr.model.User;
import com.github.xrapalexandra.kr.service.BasketService;
import com.github.xrapalexandra.kr.service.impl.DefaultBasketService;
import com.github.xrapalexandra.kr.web.BasketBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SettingOrderServlet extends HttpServlet {
    BasketService basketService = DefaultBasketService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        int[] quantity = Arrays.stream(req.getParameterValues("quantity"))
                .mapToInt(Integer::parseInt).toArray();

        BasketBean bean = (BasketBean) req.getSession().getAttribute("basket");
        List<Integer> productIdList =bean.getOrders();
        for(int i = 0; i < productIdList.size(); i++){
            Order order = new Order();
            order.setProductId(productIdList.get(i));
            order.setQuantity(quantity[i]);
            order.setUserId(user.getUserId());
            order.setStatus(Status.ORDER);
            basketService.addOrder(order);
        }

        req.getSession().removeAttribute("basket");
        try {
            resp.sendRedirect("/web/basket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
