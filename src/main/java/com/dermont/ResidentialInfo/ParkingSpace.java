package com.dermont.ResidentialInfo;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpace extends Room {
    private List<Object> storedItems = new ArrayList<>();


//    public ParkingSpace(double usableAreaLength, double usableAreaWidth, double usableAreaHeight) {
//        super(usableAreaLength, usableAreaWidth, usableAreaHeight);
//    }
//
//    public ParkingSpace(double usableAreaCapacity) {
//        super(usableAreaCapacity);
//    }


    public ParkingSpace(UsableAreaSpace dimensions) {
        super(dimensions);
    }
}


