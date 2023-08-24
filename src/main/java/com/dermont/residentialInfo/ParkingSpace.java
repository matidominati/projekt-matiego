package com.dermont.residentialInfo;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpace extends Room {
    private List<Object> storedItems = new ArrayList<>();

    public ParkingSpace(UsableAreaSpace dimensions) {
        super(dimensions, "P");
    }

    public List<Object> getStoredItems() {
        return storedItems;
    }

    public void setStoredItems(List<Object> storedItems) {
        this.storedItems = storedItems;
    }
}


