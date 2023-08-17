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

    public Rental(Person tenant, LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.tenant = tenant;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }

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
}
