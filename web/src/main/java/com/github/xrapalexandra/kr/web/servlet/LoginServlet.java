package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.model.User;
import com.github.xrapalexandra.kr.service.UserService;
import com.github.xrapalexandra.kr.service.impl.DefaultUserService;
import com.github.xrapalexandra.kr.web.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Object user = req.getSession().getAttribute("user");
        if (user == null)
            WebUtils.forwardJSP("login", req, resp);
        else
            WebUtils.forwardJSP("index", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        User user = userService.login(login, pass);
        if (user == null){
            req.setAttribute("error", "Неправильный логин или пароль!");
            WebUtils.forwardJSP("pages/login", req, resp);
        }
        else {
            req.getSession().setAttribute("user", user);
            WebUtils.forwardJSP("index", req, resp);
        }
    }
}
