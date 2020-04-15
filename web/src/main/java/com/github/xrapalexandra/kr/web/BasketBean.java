package com.github.xrapalexandra.kr.web;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BasketBean {

    private List<Integer> basket = new ArrayList<>();

    public static BasketBean get(HttpSession session) {
        BasketBean basket = (BasketBean) session.getAttribute("basket");
        if (basket == null) {
            basket = new BasketBean();
            session.setAttribute("basket", basket);
        }
        return basket;
    }

    public synchronized void addProductId(int productId){
        if (!WebUtils.isExist(basket, productId))
            basket.add(productId);
    }

    public synchronized void delProduct(Integer productId){
        basket.remove(productId);
    }

    public synchronized List<Integer> getOrders(){
        return new ArrayList<>(basket);
    }
}
