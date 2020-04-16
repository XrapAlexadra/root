package com.github.xrapalexandra.kr.model;

public class ShopAddress {

    private int id;
    private String city;
    private String street;
    private int houseNumber;

    public ShopAddress() {
    }

    public ShopAddress(String city, String street, int houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "г." + city + " ,ул." + street + " ,д." + houseNumber + ".";
    }
}
