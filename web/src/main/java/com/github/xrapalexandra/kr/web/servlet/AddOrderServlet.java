package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.web.BasketBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        BasketBean bean = BasketBean.get(session);

        for (String value : req.getParameterValues("products[]"))
            bean.addProductId(Integer.parseInt(value));

        try {
            resp.sendRedirect("/web/productList");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/web/productList");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
