package com.github.xrapalexandra.kr.service;

import com.github.xrapalexandra.kr.model.ShopAddress;

import java.util.List;

public interface ShopAddressService {

    int addAddress(ShopAddress shopAddress);

    void delAddress(ShopAddress shopAddress);

    void updateAddress(ShopAddress newShopAddress);

    List<ShopAddress> getShopAddressList();
}
