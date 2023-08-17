package com.dermont;

public class Address {

    private String street;
    private String city;
    private String postcode;
    private String flatNumber;
    private String houseNumber;

    public Address(String street, String flatNumber, String houseNumber, String postcode, String city) {
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.flatNumber = flatNumber;
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}