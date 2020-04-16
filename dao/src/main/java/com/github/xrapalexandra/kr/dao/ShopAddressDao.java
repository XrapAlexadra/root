package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.model.ShopAddress;

import java.util.List;

public interface ShopAddressDao {

    int addAddress(ShopAddress shopAddress);

    void delAddress(ShopAddress shopAddress);

    void updateAddress(ShopAddress newShopAddress);

    List<ShopAddress> getShopAddressList();
}
