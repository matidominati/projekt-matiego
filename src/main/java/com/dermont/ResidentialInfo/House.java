package com.dermont.ResidentialInfo;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int houseNumber;
    private List<Room> rooms;

    public House(int houseNumber, List<Room> rooms) {
        this.houseNumber = houseNumber;
        this.rooms = rooms;
    }

    public void addRoom(Room room){
        rooms.add(room);
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


}
