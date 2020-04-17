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
}
