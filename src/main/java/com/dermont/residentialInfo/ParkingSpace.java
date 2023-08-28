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

public class ParkingSpace extends Room {
    private List<Object> storedItems = new ArrayList<>();

    public ParkingSpace(AreaSpace dimensions) {
        super(dimensions, "P");
    }

    public void rentParkingSpace(Person newTenant, ParkingSpace parkingSpace) {                 // zakladam ze miejsce pakringowe moze wynajmowac tylko jedna osoba
        if (parkingSpace.isSpaceAvailable(parkingSpace)) {
            getTenants().add(newTenant);
            newTenant.getRentedSpaces().add(parkingSpace);
            setRentalEndDate(LocalDate.now().plusYears(1));
        } else {
            throw new IllegalArgumentException("Miejsce parkingowe jest juz zajete");
        }
    }

    public void addItem(Items item) throws TooManyThingsException, ItemToWideException, ItemToHighException, ItemTooLongException {
        calculateOccupiedArea(storedItems);
        if (checkFreeSpaceForItem(item)) {
            if (checkDimensionsOfItem(item)) {
                storedItems.add(item);
            }
        } else throw new TooManyThingsException();
    }

    public void removeItem(Items item){
        storedItems.remove(item);
    }


    public boolean checkDimensionsOfItem(Items item) throws ItemTooLongException, ItemToWideException, ItemToHighException {
        if (checkIfItemIsNotTooHigh(item)) {
            if (checkIfItemIsNotTooLong(item)) {
                if (checkIfItemIsNotTooWide(item)) {
                    return true;
                } else throw new ItemToWideException();
            } else throw new ItemTooLongException();
        } else throw new ItemToHighException();

    }

    public boolean checkFreeSpaceForItem(Items item) {
        return getDimensions().getCapacity() - calculateOccupiedArea(storedItems) > item.getDimensions().getCapacity();
    }

    public boolean checkIfItemIsNotTooWide(Items item) {
        return getDimensions().getWidth() > item.getDimensions().getWidth();

    }

    public boolean checkIfItemIsNotTooHigh(Items item) {
        return getDimensions().getHeight() > item.getDimensions().getHeight();
    }


    public boolean checkIfItemIsNotTooLong(Items item) {
        return getDimensions().getLength() > item.getDimensions().getLength();
    }


    public double calculateOccupiedArea(List<Items> items) {
        return items.stream()
                .mapToDouble(item -> item.getDimensions().getCapacity())
                .sum();
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



