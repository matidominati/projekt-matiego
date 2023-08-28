package com.dermont.storedItems;

import com.dermont.residentialInfo.AreaSpace;

public class CityVehicle extends Vehicle {
    private String engineType;
    private int numberOfDoors;
    private boolean haveLPG;
    private double engineCapacity;
    private double totalMass;

    public CityVehicle(String name, AreaSpace dimensions, String engineType, int numberOfDoors, boolean haveLPG, double engineCapacity, double totalMass) {
        super(name, dimensions);
        this.engineType = engineType;
        this.numberOfDoors = numberOfDoors;
        this.haveLPG = haveLPG;
        this.engineCapacity = engineCapacity;
        this.totalMass = totalMass;
    }


    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isHaveLPG() {
        return haveLPG;
    }

    public void setHaveLPG(boolean haveLPG) {
        this.haveLPG = haveLPG;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(double totalMass) {
        this.totalMass = totalMass;
    }
}
