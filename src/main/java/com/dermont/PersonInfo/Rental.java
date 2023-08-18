package com.dermont.PersonInfo;

import com.dermont.ResidentialInfo.Flat;
import com.dermont.ResidentialInfo.ParkingSpace;

import java.time.LocalDate;
import java.util.Date;

public class Rental {
    private Person tenant;
    private Flat flat;
    private ParkingSpace parkingSpace;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;


    public Person getTenant() {
        return tenant;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
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

    public Flat getFlat() {
        return flat;
    }
    public void setFlat(Flat flat) {
        this.flat = flat;
    }
    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }
    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }
}
