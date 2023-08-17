package com.dermont.StoredItems;

public class AmphibiousVehicle extends Vehicle {
    private String color;

    public AmphibiousVehicle(String name, double occupiedArea, String color) {
        super(name, occupiedArea);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
