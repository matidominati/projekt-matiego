package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Person;
import com.dermont.PersonInfo.Rental;

import java.util.List;

public class Flat {

    private double usableAreaLength;
    private double usableAreaWidth;
    private double usableAreaHeight;
    private double usableAreaCapacity = usableAreaHeight * usableAreaLength * usableAreaWidth;
    private static int ID = 1;
    private int IDNumber;
    private List<Person> tenants;
    private Rental rental;
    private Residential residential;

    public Flat(double usableAreaCapacity, Rental rental) {
        this.usableAreaCapacity = usableAreaCapacity;
        this.rental = rental;
        this.IDNumber = ID++;
    }

    public Flat(double usableAreaLength, double usableAreaWidth, double usableAreaHeight, List<Person> tenants, Rental rental) {
        this.usableAreaLength = usableAreaLength;
        this.usableAreaWidth = usableAreaWidth;
        this.usableAreaHeight = usableAreaHeight;
        this.tenants = tenants;
        this.rental = rental;
        this.IDNumber = ID++;
    }

    public void PrintSpaceInfo() {
        System.out.println("Numer mieszkania: " + IDNumber);
        System.out.println("Powierzchnia użytkowa: " + usableAreaCapacity + " m3");
        System.out.println("Płatnik: " + rental.getTenant());
        System.out.println("Wynajmujący:");
        tenants.stream()
                .forEach(tenants -> System.out.println(tenants.getFirstName() + " " + tenants.getLastName()));
    }

    public void addTenant(Person tenant) {
        tenants.add(tenant);
    }

    public void removeTenant(Person tenant) {
        tenants.remove(tenant);
    }

    public boolean checkIfHaveTenantByPESEL(String pesel) {
        return tenants.stream()
                .anyMatch(person -> pesel.equals(person.getPesel()));
    }


    public double getUsableAreaCapacity() {
        return usableAreaCapacity;
    }

    public void setUsableAreaCapacity(double usableAreaCapacity) {
        this.usableAreaCapacity = usableAreaCapacity;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Flat.ID = ID;
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

    public double getUsableAreaLength() {
        return usableAreaLength;
    }

    public void setUsableAreaLength(double usableAreaLength) {
        this.usableAreaLength = usableAreaLength;
    }

    public double getUsableAreaWidth() {
        return usableAreaWidth;
    }

    public void setUsableAreaWidth(double usableAreaWidth) {
        this.usableAreaWidth = usableAreaWidth;
    }

    public double getUsableAreaHeight() {
        return usableAreaHeight;
    }

    public void setUsableAreaHeight(double usableAreaHeight) {
        this.usableAreaHeight = usableAreaHeight;
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }

    public Residential getResidential() {
        return residential;
    }

    public void setResidential(Residential residential) {
        this.residential = residential;
    }
}

