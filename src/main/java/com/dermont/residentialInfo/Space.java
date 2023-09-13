package com.dermont.residentialInfo;

import com.dermont.exceptions.ItemToHighException;
import com.dermont.exceptions.ItemToWideException;
import com.dermont.exceptions.ItemTooLongException;
import com.dermont.exceptions.TooManyThingsException;
import com.dermont.personInfo.Person;
import com.dermont.storedItems.Items;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Space {

    private static int flatIdCounter = 1;
    private static int parkingIdCounter = 1;
    private String id;
    private AreaSpace dimensions;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Person mainTenant;
    private List<Person> tenants = new ArrayList<>();



//    public Space(AreaSpace dimensions, String prefix) {
//        this.dimensions = new AreaSpace(dimensions.getCapacity());
//        if (prefix.equals("F")) {
//            this.id = prefix + flatIdCounter++;
//        } else {
//            this.id = prefix + parkingIdCounter++;
//        }
//    }

    public Space(AreaSpace dimensions, String prefix) {
        this.dimensions = new AreaSpace(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
        if (prefix.equals("F")) {
            this.id = prefix + flatIdCounter++;
        } else {
            this.id = prefix + parkingIdCounter++;
        }
    }

    // shared
    public abstract boolean isSpaceAvailable();

    public abstract void displaySpaceContents();

    public abstract boolean isRentalExpired();

    // parkingSpace
    public abstract void rentParkingSpace(Person newTenant, Space space);
    public abstract void addItem(Items item) throws TooManyThingsException, ItemToWideException, ItemToHighException, ItemTooLongException;
    public abstract void removeItem(Items item);
    public abstract boolean checkDimensionsOfItem(Items item) throws ItemTooLongException, ItemToWideException, ItemToHighException;
    public abstract boolean checkFreeSpaceForItem(Items item);
    public abstract boolean checkIfItemIsNotTooWide(Items item);
    public abstract boolean checkIfItemIsNotTooHigh(Items item);
    public abstract boolean checkIfItemIsNotTooLong(Items item);
    public abstract double calculateOccupiedArea(List<Items> items);


    // flat
    public abstract void addTenant(Person person, Space space);
    public abstract void removeTenant(Person tenantToRemove, Space space);
    public abstract void removeMainTenant(Person tenantToRemove);


    public static int getFlatIdCounter() {
        return flatIdCounter;
    }

    public static void setFlatIdCounter(int flatIdCounter) {
        Space.flatIdCounter = flatIdCounter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AreaSpace getDimensions() {
        return dimensions;
    }

    public void setDimensions(AreaSpace dimensions) {
        this.dimensions = dimensions;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Person getMainTenant() {
        return mainTenant;
    }

    public void setMainTenant(Person mainTenant) {
        this.mainTenant = mainTenant;
    }


    public List<Person> getTenants() {
        return tenants;
    }

    public void setTenants(List<Person> tenants) {
        this.tenants = tenants;
    }

    public static int getParkingIdCounter() {
        return parkingIdCounter;
    }

    public static void setParkingIdCounter(int parkingIdCounter) {
        Space.parkingIdCounter = parkingIdCounter;
    }




    @Override
    public String toString() {
        return "Space{" +
                ", IDNumber=" + id +
                ", rentalStartDate=" + rentalStartDate +
                ", rentalEndDate=" + rentalEndDate +
                ", mainTenant=" + mainTenant +
                ", tenants=" + tenants +
                '}';
    }


}
