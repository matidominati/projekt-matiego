package com.dermont.residentialInfo;

import com.dermont.personInfo.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpace extends Room {
    private List<Object> storedItems = new ArrayList<>();

    public ParkingSpace(UsableAreaSpace dimensions) {
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

    public void addItem() {

    }


    public List<Object> getStoredItems() {
        return storedItems;
    }

    public void setStoredItems(List<Object> storedItems) {
        this.storedItems = storedItems;
    }
}


