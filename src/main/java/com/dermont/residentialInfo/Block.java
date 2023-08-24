package com.dermont.residentialInfo;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private int blockNumber;
    private List<Space> spaces = new ArrayList<>();

    public House(int houseNumber, List<Room> rooms) {
        this.houseNumber = houseNumber;
        this.rooms = rooms;
    }

    public void addSpace(Space space){
        spaces.add(space);
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }


}
