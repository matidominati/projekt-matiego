package com.dermont.storedItems;

import com.dermont.residentialInfo.AreaSpace;

public class AmphibiousVehicle extends Vehicle {
    private String color;

    public AmphibiousVehicle(String name, AreaSpace dimensions, String color) {
        super(name, dimensions);
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
