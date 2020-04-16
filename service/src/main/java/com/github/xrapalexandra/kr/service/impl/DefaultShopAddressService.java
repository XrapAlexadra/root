package com.github.xrapalexandra.kr.service.impl;

import com.github.xrapalexandra.kr.dao.ShopAddressDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultShopAddressDao;
import com.github.xrapalexandra.kr.model.ShopAddress;
import com.github.xrapalexandra.kr.service.ShopAddressService;

import java.util.List;

public class DefaultShopAddressService implements ShopAddressService {

    private DefaultShopAddressService() {
    }

    private static volatile ShopAddressService instance;


    public static ShopAddressService getInstance() {
        ShopAddressService localInstance = instance;
        if (localInstance == null) {
            synchronized (ShopAddressService.class) {
                localInstance = instance;
                if (localInstance == null)
                    localInstance = instance = new DefaultShopAddressService();
            }
        }
        return localInstance;
    }

    private ShopAddressDao shopAddressDao = DefaultShopAddressDao.getInstance();

    @Override
    public int addAddress(ShopAddress shopAddress) {
        return shopAddressDao.addAddress(shopAddress);
    }

    @Override
    public void delAddress(ShopAddress shopAddress) {
        shopAddressDao.delAddress(shopAddress);
    }

    @Override
    public void updateAddress(ShopAddress newShopAddress) {
        shopAddressDao.updateAddress(newShopAddress);
    }

    @Override
    public List<ShopAddress> getShopAddressList() {
        return shopAddressDao.getShopAddressList();
    }
}
