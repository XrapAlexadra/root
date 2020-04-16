package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.service.ProductService;
import com.github.xrapalexandra.kr.service.impl.DefaultProductService;
import com.github.xrapalexandra.kr.web.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductToChangeServlet extends HttpServlet {

    private ProductService productService = DefaultProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int product_id = Integer.parseInt(req.getParameter("productId"));
        req.setAttribute("product", productService.getProductById(product_id));
        WebUtils.forwardJSP("pages/changeProduct", req, resp);
    }
}
