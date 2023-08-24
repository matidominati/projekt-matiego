package com.dermont.storedItems;

public class OffroadVehicle extends CityVehicle{
    private boolean haveAllWheelDrive;
    private boolean haveOffRoadTires;

    public OffroadVehicle(String name, double occupiedArea, String engineType, int numberOfDoors, boolean haveLPG,
                          double engineCapacity, double totalMass, boolean haveAllWheelDrive, boolean haveOffRoadTires) {
        super(name, occupiedArea, engineType, numberOfDoors, haveLPG, engineCapacity, totalMass);
        this.haveAllWheelDrive = haveAllWheelDrive;
        this.haveOffRoadTires = haveOffRoadTires;
    }

    public boolean isHaveAllWheelDrive() {
        return haveAllWheelDrive;
    }

    public void setHaveAllWheelDrive(boolean haveAllWheelDrive) {
        this.haveAllWheelDrive = haveAllWheelDrive;
    }

    public boolean isHaveOffRoadTires() {
        return haveOffRoadTires;
    }

    public void setHaveOffRoadTires(boolean haveOffRoadTires) {
        this.haveOffRoadTires = haveOffRoadTires;
    }
}

