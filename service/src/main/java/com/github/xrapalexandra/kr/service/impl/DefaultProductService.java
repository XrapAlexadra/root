package com.github.xrapalexandra.kr.service.impl;

import com.github.xrapalexandra.kr.dao.ProductDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultProductDao;
import com.github.xrapalexandra.kr.model.Order;
import com.github.xrapalexandra.kr.model.Product;
import com.github.xrapalexandra.kr.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class DefaultProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ProductDao productDao = DefaultProductDao.getInstance();

    private DefaultProductService() {
    }

    private static volatile ProductService instance;

    public static ProductService getInstance() {
        ProductService localInstance = instance;
        if (localInstance == null) {
            synchronized (ProductService.class) {
                localInstance = instance;
                if (localInstance == null)
                    localInstance = instance = new DefaultProductService();
            }
        }
        return localInstance;
    }

    @Override
    public List<Product> getProductList(int page) {
        return productDao.getProductList(page);
    }

    @Override
    public void addProduct(Product product) {
        int productId = productDao.getIdProduct(product);
        if (productId == 0) {
            product.setId(productDao.addProduct(product));
            logger.info("{} add in database (products).", product);
        } else {
            Product newProduct = productDao.getProductById(productId);
            int allQuantity = newProduct.getQuantity() + product.getQuantity();
            newProduct.setQuantity(allQuantity);
            productDao.updateProduct(newProduct);
            logger.info("{} is already exist in database. Update product {}", product, newProduct);
        }
    }

    @Override
    public Boolean updateProduct(Product product) {
        if(productDao.getIdProduct(product) == 0){
            productDao.updateProduct(product);
            logger.info("{} update in database.", product);
            return true;
        } else {
            logger.info("{}is already exist in database.", product);
            return false;
        }
    }

    @Override
    public void updateProductQuantity(Order order) {
        productDao.updateProductQuantity(order);
    }

    @Override
    public void delProduct(int id) {
        productDao.delProduct(id);
        logger.info("Delete product with ID: {} from database.", id);
    }

    @Override
    public Product getProductById(int product_id) {
        return productDao.getProductById(product_id);
    }
}
