package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.dao.BasketDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultBasketDao;
import com.github.xrapalexandra.kr.service.BasketService;
import com.github.xrapalexandra.kr.service.impl.DefaultBasketService;
import com.github.xrapalexandra.kr.web.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminBasketServlet extends HttpServlet {

    BasketService basketService = DefaultBasketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("allorders", basketService.getAllOrders(1));

        WebUtils.forwardJSP("pages/adminbasket", req, resp);
    }
}
