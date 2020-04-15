package com.github.xrapalexandra.kr.web;

import com.github.xrapalexandra.kr.model.Status;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WebUtils {

    public static void forwardJSP(String page, HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/" + page + ".jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException();
        }
    }
    public static void forward(String page, HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/" + page).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException();
        }
    }

    public static Boolean isExist(List<Integer> list, int item){
        for(int i : list){
            if (i == item)
                return true;
        }
        return false;
    }

    public static Status createStatus(String s){
        Status[] status = {Status.ORDER, Status.CONFIRMED, Status.PAID};
        return status[Integer.parseInt(s)-1];
    }
}
