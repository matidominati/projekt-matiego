package com.dermont.storedItems;

import com.dermont.residentialInfo.AreaSpace;

public class OffroadVehicle extends CityVehicle{
    private boolean haveAllWheelDrive;
    private boolean haveOffRoadTires;

    public OffroadVehicle(String name, AreaSpace dimensions, String engineType, int numberOfDoors, boolean haveLPG,
                          double engineCapacity, double totalMass, boolean haveAllWheelDrive, boolean haveOffRoadTires) {
        super(name, dimensions, engineType, numberOfDoors, haveLPG, engineCapacity, totalMass);
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

