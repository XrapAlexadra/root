package com.github.xrapalexandra.kr.service;

import com.github.xrapalexandra.kr.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductList(int page);

    void addProduct(Product product);

    Boolean updateProduct(Product product);

    void delProduct(int id);

    Product getProductById(int product_id);
}
