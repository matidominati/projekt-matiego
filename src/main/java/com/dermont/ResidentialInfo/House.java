package com.dermont.ResidentialInfo;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int houseNumber;
    private Residential residentialName;
    private List<Room> rooms = new ArrayList<>();

    public House(int houseNumber, Residential residentialName, List<Room> rooms) {
        this.houseNumber = houseNumber;
        this.residentialName = residentialName;
        this.rooms = rooms;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Residential getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(Residential residentialName) {
        this.residentialName = residentialName;
    }
}
