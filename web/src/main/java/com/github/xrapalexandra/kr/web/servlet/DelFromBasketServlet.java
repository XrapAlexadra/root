package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.web.BasketBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DelFromBasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        BasketBean bean = BasketBean.get(session);
        int productId = Integer.parseInt(req.getParameter("delProduct"));
        bean.delProduct(productId);
        try {
            resp.sendRedirect("/web/basket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
