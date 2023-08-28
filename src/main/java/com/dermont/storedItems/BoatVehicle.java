package com.dermont.storedItems;

import com.dermont.residentialInfo.AreaSpace;

public class BoatVehicle extends Vehicle{
    private boolean haveSail;
    private boolean isRegistered;

    public BoatVehicle(String name, AreaSpace dimensions, boolean haveSail, boolean isRegistered) {
        super(name, dimensions);
        this.haveSail = haveSail;
        this.isRegistered = isRegistered;
    }

    public boolean isHaveSail() {
        return haveSail;
    }

    public void setHaveSail(boolean haveSail) {
        this.haveSail = haveSail;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
