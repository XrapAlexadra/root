package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.dao.impl.DefaultProductDao;
import com.github.xrapalexandra.kr.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDaoTest {

    public ProductDao productDao = DefaultProductDao.getInstance();

    @Test
    void addProduct() {
        Product product = new Product("somth", 78, 56);
        product.setId(productDao.addProduct(product));
        assertNotEquals(0, product.getId() );
        productDao.delProduct(product.getId());
    }

    @Test
    void getIdProductExist() {
        Product product = new Product("item", 56, 50);
        product.setId(productDao.addProduct(product));
        assertEquals(product.getId(), productDao.getIdProduct(product));
        productDao.delProduct(product.getId());
    }

    @Test
    void getIdProductNotExist() {
        Product product = new Product("item7", 56, 45);
        assertEquals(0, productDao.getIdProduct(product));
    }

    @Test
    void  getProductByIdExist(){
        Product product = new Product("item9", 56, 45);
        int productId = productDao.addProduct(product);
        assertEquals(product, productDao.getProductById(productId));
        productDao.delProduct(productId);
    }

    @Test
    void getProductByIdNotExist(){
        assertNull(productDao.getProductById(15000));
    }
}
