package com.github.xrapalexandra.kr.web.servlet;

import com.github.xrapalexandra.kr.model.Role;
import com.github.xrapalexandra.kr.model.User;
import com.github.xrapalexandra.kr.service.UserService;
import com.github.xrapalexandra.kr.service.impl.DefaultUserService;
import com.github.xrapalexandra.kr.web.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet {

    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        WebUtils.forwardJSP("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        User user = new User(login, Role.USER, req.getParameter("pass"));
        if (userService.saveUserInDB(user) == null) {
            req.setAttribute("alreadyExist", login);
            WebUtils.forwardJSP("pages/auth", req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            WebUtils.forwardJSP("index", req, resp);
        }
    }
}
