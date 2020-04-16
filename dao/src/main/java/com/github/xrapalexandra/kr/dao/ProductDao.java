package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.Product;

import java.util.List;

public interface ProductDao {

    int addProduct(Product product);

    int getIdProduct(Product product);

    List<Product> getProductList(int page);

    void updateProduct(Product product);

    Product getProductById(int id);

    void delProduct(int id);

    void updateProductQuantity(Order order);
}
