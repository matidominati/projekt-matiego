package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Room {
    //    private double usableAreaLength;
//    private double usableAreaWidth;
//    private double usableAreaHeight;
//    private double usableAreaCapacity;
    private usableArea dimensions;
    private static int ID = 1;
    private int IDNumber;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Person mainTenant;
    private List<Person> tenants = new ArrayList<>();

//    public Room(double usableAreaLength, double usableAreaWidth, double usableAreaHeight) {
//        this.usableAreaLength = usableAreaLength;
//        this.usableAreaWidth = usableAreaWidth;
//        this.usableAreaHeight = usableAreaHeight;
//        this.IDNumber = ID++;
//}


    public Room(usableArea dimensions, int IDNumber) {
        this.dimensions = dimensions;
        this.IDNumber = IDNumber;
    }

    public Room(usableArea dimensions) {
        this.IDNumber = ID++;
    }
//
//    public Room(double usableAreaCapacity) {
//        this.usableAreaCapacity = usableAreaCapacity;
//        this.IDNumber = ID++;
//}

    public boolean isRentalExpired() {
        return rentalEndDate != null && rentalEndDate.isBefore(LocalDate.now());
    }

    public Optional<Room> findRoom(Residential residential, House house, Room room) {
        return residential.getHouses().stream()
                .filter(h -> h.equals(house))
                .flatMap(h -> h.getRooms().stream())
                .filter(r -> r.equals(room))
                .findFirst();
    }

//
//public double getUsableAreaLength(){
//        return usableAreaLength;
//        }
//
//public void setUsableAreaLength(double usableAreaLength){
//        this.usableAreaLength=usableAreaLength;
//        }
//
//public double getUsableAreaWidth(){
//        return usableAreaWidth;
//        }
//
//public void setUsableAreaWidth(double usableAreaWidth){
//        this.usableAreaWidth=usableAreaWidth;
//        }
//
//public double getUsableAreaHeight(){
//        return usableAreaHeight;
//        }
//
//public void setUsableAreaHeight(double usableAreaHeight){
//        this.usableAreaHeight=usableAreaHeight;
//        }
//
//public double getUsableAreaCapacity(){
//        return usableAreaCapacity;
//        }
//
//public void setUsableAreaCapacity(double usableAreaCapacity){
//        this.usableAreaCapacity=usableAreaCapacity;
//        }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Room.ID = ID;
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Person getMainTenant() {
        return mainTenant;
    }

    public void setMainTenant(Person mainTenant) {
        this.mainTenant = mainTenant;
    }


    public List<Person> getTenants() {
        return tenants;
    }

    public void setTenants(List<Person> tenants) {
        this.tenants = tenants;
    }

    @Override
    public String toString() {
        return "Room{" +
                ", IDNumber=" + IDNumber +
                ", rentalStartDate=" + rentalStartDate +
                ", rentalEndDate=" + rentalEndDate +
                ", mainTenant=" + mainTenant +
                ", tenants=" + tenants +
                '}';
    }
}
