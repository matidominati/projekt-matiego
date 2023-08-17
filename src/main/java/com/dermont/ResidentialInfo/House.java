package com.dermont.ResidentialInfo;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int houseNumber;
    private List<Flat> flats = new ArrayList<>();
    private List<ParkingSpace> parkingSpaces = new ArrayList<>();

    public House(int houseNumber, List<Flat> flats, List<ParkingSpace> parkingSpaces) {
        this.houseNumber = houseNumber;
        this.flats = flats;
        this.parkingSpaces = parkingSpaces;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }
}
