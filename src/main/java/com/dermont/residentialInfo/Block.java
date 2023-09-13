package com.dermont.residentialInfo;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private int blockNumber;
    private List<Space> spaces;

    public Block(int blockNumber, List<Space> spaces) {
        this.blockNumber = blockNumber;
        this.spaces = spaces;
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
