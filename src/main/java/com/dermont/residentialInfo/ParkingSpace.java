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

public class ParkingSpace extends Space {
    private List<Items> storedItems = new ArrayList<>();

    public ParkingSpace(AreaSpace dimensions) {
        super(dimensions, "P");
    }

    @Override
    public boolean isSpaceAvailable() {
        return getRentalEndDate() == null;
    }

    @Override
    public void displaySpaceContents() {
        System.out.println("Zawartosc miejsca parkignowego " + getId() + ": ");
        System.out.println(getStoredItems().toString());
    }

    @Override
    public void rentParkingSpace(Person newTenant, Space space) {                 // zakladam ze miejsce pakringowe moze wynajmowac tylko jedna osoba
        if (space.isSpaceAvailable()) {
            getTenants().add(newTenant);
            newTenant.getRentedSpaces().add(space);
            setRentalEndDate(LocalDate.now().plusYears(1));
        } else {
            throw new IllegalArgumentException("Miejsce parkingowe jest juz zajete");
        }
    }

    @Override
    public boolean isRentalExpired() {
        return getRentalEndDate() != null && getRentalEndDate().isBefore(LocalDate.now());
    }

    @Override
    public void addItem(Items item) throws TooManyThingsException, ItemToWideException, ItemToHighException, ItemTooLongException {
        calculateOccupiedArea(storedItems);
        if (checkFreeSpaceForItem(item)) {
            if (checkDimensionsOfItem(item)) {
                storedItems.add(item);
            }
        } else throw new TooManyThingsException();
    }

    @Override
    public void removeItem(Items item) {
        storedItems.remove(item);
    }

    @Override
    public boolean checkDimensionsOfItem(Items item) throws ItemTooLongException, ItemToWideException, ItemToHighException {
        if (checkIfItemIsNotTooHigh(item)) {
            if (checkIfItemIsNotTooLong(item)) {
                if (checkIfItemIsNotTooWide(item)) {
                    return true;
                } else throw new ItemToWideException();
            } else throw new ItemTooLongException();
        } else throw new ItemToHighException();

    }

    @Override
    public boolean checkFreeSpaceForItem(Items item) {
        return getDimensions().getCapacity() - calculateOccupiedArea(storedItems) > item.getDimensions().getCapacity();
    }

    @Override
    public boolean checkIfItemIsNotTooWide(Items item) {
        return getDimensions().getWidth() > item.getDimensions().getWidth();

    }

    @Override
    public boolean checkIfItemIsNotTooHigh(Items item) {
        return getDimensions().getHeight() > item.getDimensions().getHeight();
    }

    @Override
    public boolean checkIfItemIsNotTooLong(Items item) {
        return getDimensions().getLength() > item.getDimensions().getLength();
    }

    @Override
    public double calculateOccupiedArea(List<Items> items) {
        return items.stream()
                .mapToDouble(item -> item.getDimensions().getCapacity())
                .sum();
    }

    @Override
    public void addTenant(Person person, Space space) {

    }

    @Override
    public void removeTenant(Person tenantToRemove, Space space) {

    }

    @Override
    public void removeMainTenant(Person tenantToRemove) {

    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "storedItems=" + storedItems +
                '}';
    }

    public List<Items> getStoredItems() {
        return storedItems;
    }


    public void setStoredItems(List<Items> storedItems) {
        this.storedItems = storedItems;
    }

}



