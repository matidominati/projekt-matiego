package com.dermont.StoredItems;

public class Items {
    private String name;
    private double occupiedArea;

    public Items(String name, double occupiedArea) {
        this.name = name;
        this.occupiedArea = occupiedArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOccupiedArea() {
        return occupiedArea;
    }

    public void setOccupiedArea(double occupiedArea) {
        this.occupiedArea = occupiedArea;
    }
}
