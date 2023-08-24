package com.dermont.storedItems;

public class BoatVehicle extends Vehicle{
    private boolean haveSail;
    private boolean isRegistered;

    public BoatVehicle(String name, double occupiedArea, boolean haveSail, boolean isRegistered) {
        super(name, occupiedArea);
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
