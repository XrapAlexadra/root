package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.dao.impl.DefaultShopAddressDao;
import com.github.xrapalexandra.kr.model.ShopAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopAddressDaoTest {

    private ShopAddressDao shopAddressDao = DefaultShopAddressDao.getInstance();

    @Test
    void addAddress(){
        ShopAddress address = new ShopAddress("Минск", "Ленина", 12);
        address.setId(shopAddressDao.addAddress(address));
        assertNotEquals(0, address.getId());
        shopAddressDao.delAddress(address);
    }

    @Test
    void updateAddress(){
        ShopAddress address = new ShopAddress("Минск", "Ленина", 12);
        address.setId(shopAddressDao.addAddress(address));
        address.setHouseNumber(52);
        shopAddressDao.updateAddress(address);
        assertEquals(address.getHouseNumber(), shopAddressDao.getShopAddressList().get(0).getHouseNumber());
        shopAddressDao.delAddress(address);
    }

    @Test
    void delAddress(){
        ShopAddress address = new ShopAddress("Минск", "Ленина", 12);
        address.setId(shopAddressDao.addAddress(address));
        shopAddressDao.delAddress(address);
        assertNull(shopAddressDao.getShopAddressList());
    }

    @Test
    void getShopAddressList(){
        ShopAddress address = new ShopAddress("Минск", "Ленина", 12);
        address.setId(shopAddressDao.addAddress(address));
        assertEquals(address.getId(), shopAddressDao.getShopAddressList().get(0).getId());
        shopAddressDao.delAddress(address);
    }
}
