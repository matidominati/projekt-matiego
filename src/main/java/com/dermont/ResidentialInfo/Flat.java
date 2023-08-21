package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Person;

import java.util.List;

public class Flat extends Room {
//    private List<Person> tenants;
    private int maxNumberOfTenants;

    public Flat(double usableAreaLength, double usableAreaWidth, double usableAreaHeight, int maxNumberOfTenants) {
        super(usableAreaLength, usableAreaWidth, usableAreaHeight);
        this.maxNumberOfTenants = maxNumberOfTenants;
    }

    public Flat(double usableAreaCapacity, int maxNumberOfTenants) {
        super(usableAreaCapacity);
        this.maxNumberOfTenants = maxNumberOfTenants;
    }

//    public List<Person> getTenants() {
//        return tenants;
//    }
//
//    public void setTenants(List<Person> tenants) {
//        this.tenants = tenants;
//    }

    public int getMaxNumberOfTenants() {
        return maxNumberOfTenants;
    }

    public void setMaxNumberOfTenants(int maxNumberOfTenants) {
        this.maxNumberOfTenants = maxNumberOfTenants;
    }
}

