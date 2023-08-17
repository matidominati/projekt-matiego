package com.dermont;

import java.util.List;

public class Flat {
    private double usableArea;
    private static int IDNumber;
    private List<Person> tenants;
    private Rental rental;

    public Flat(double usableArea, Rental rental) {
        this.usableArea = usableArea;
        this.rental = rental;
        this.IDNumber = IDNumber++;
    }

    public double getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(double usableArea) {
        this.usableArea = usableArea;
    }

    public static int getIDNumber() {
        return IDNumber;
    }

    public static void setIDNumber(int IDNumber) {
        Flat.IDNumber = IDNumber;
    }

    public List<Person> getTenants() {
        return tenants;
    }

    public void setTenants(List<Person> tenants) {
        this.tenants = tenants;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
