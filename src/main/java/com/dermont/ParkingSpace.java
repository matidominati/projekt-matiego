package com.dermont;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpace extends Flat {
    private List<Object> storedItems = new ArrayList<>();

    public ParkingSpace(double usableArea, Rental rental, List<Object> storedItems) {
        super(usableArea, rental);
        this.storedItems = storedItems;
    }

    public List<Object> getStoredItems() {
        return storedItems;
    }

    public void setStoredItems(List<Object> storedItems) {
        this.storedItems = storedItems;
    }
}

