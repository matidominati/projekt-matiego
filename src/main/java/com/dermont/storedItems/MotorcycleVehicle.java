package com.dermont.storedItems;

public class MotorcycleVehicle extends Vehicle{
    private String type;
    private double loudness;

    public MotorcycleVehicle(String name, double occupiedArea, String type, double loudness) {
        super(name, occupiedArea);
        this.type = type;
        this.loudness = loudness;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLoudness() {
        return loudness;
    }

    public void setLoudness(double loudness) {
        this.loudness = loudness;
    }
}
