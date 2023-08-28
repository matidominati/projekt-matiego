package com.dermont.residentialInfo;

import com.dermont.exceptions.ItemToHighException;
import com.dermont.exceptions.ItemToWideException;
import com.dermont.exceptions.ItemTooLongException;
import com.dermont.exceptions.TooManyThingsException;
import com.dermont.personInfo.Person;
import com.dermont.storedItems.Items;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Flat extends Space {
    private int maxNumberOfTenants;
    private List<Person> tenants = new ArrayList<>();


    public Flat(AreaSpace dimensions, int maxNumberOfTenants) {
        super(dimensions, "F");
        this.maxNumberOfTenants = maxNumberOfTenants;

    }

    @Override
    public boolean isSpaceAvailable() {
        return getRentalEndDate() == null && getTenants().size() < getMaxNumberOfTenants();
    }

    @Override
    public void displaySpaceContents() {
        System.out.print("Ilość lokatorow w mieszkaniu " + getId() + ": ");
        System.out.println(getTenants().size());
        System.out.println(getTenants().toString());
        System.out.println("Glowny najemca" + getMainTenant());
    }

    @Override
    public boolean isRentalExpired() {
        return getRentalEndDate() != null && getRentalEndDate().isBefore(LocalDate.now());
    }

    @Override
    public void addTenant(Person newTenant, Space space) {
        if (getTenants().size() < maxNumberOfTenants) {
            getTenants().add(newTenant);
            newTenant.getRentedSpaces().add(space);
            if (getTenants().size() == 1) {
                setMainTenant(newTenant);
                setRentalStartDate(LocalDate.now());
                setRentalEndDate(LocalDate.now().plusYears(1));
            }
        } else {
            throw new IllegalArgumentException("Mieszkanie jest pelne. Brak wolnych miejsc");
        }
    }

    @Override
    public void removeTenant(Person tenantToRemove, Space space) {
        if (!getTenants().contains(tenantToRemove)) {
            throw new IllegalArgumentException("Podana osoba nie jest lokatorem tego mieszkania");
        } else {
            getTenants().remove(tenantToRemove);
            tenantToRemove.removeRentedRoom(space);
            setMainTenant(getTenants().stream()
                    .findFirst()
                    .orElse(null));
        }
    }

    @Override
    public void removeMainTenant(Person tenantToRemove) {
        if (getMainTenant() != null && tenantToRemove.equals(getMainTenant())) {
            getTenants().remove(tenantToRemove);
            if (!getTenants().isEmpty()) {
                setMainTenant(getTenants().get(0));
            } else {
                setMainTenant(null);
            }
        }
    }


    @Override
    public void rentParkingSpace(Person newTenant, Space space) {

    }

    @Override
    public void addItem(Items item) throws TooManyThingsException, ItemToWideException, ItemToHighException, ItemTooLongException {

    }

    @Override
    public void removeItem(Items item) {

    }

    @Override
    public boolean checkDimensionsOfItem(Items item) throws ItemTooLongException, ItemToWideException, ItemToHighException {
        return false;
    }

    @Override
    public boolean checkFreeSpaceForItem(Items item) {
        return false;
    }

    @Override
    public boolean checkIfItemIsNotTooWide(Items item) {
        return false;
    }

    @Override
    public boolean checkIfItemIsNotTooHigh(Items item) {
        return false;
    }

    @Override
    public boolean checkIfItemIsNotTooLong(Items item) {
        return false;
    }

    @Override
    public double calculateOccupiedArea(List<Items> items) {
        return 0;
    }

    public List<Person> getTenants() {
        return tenants;
    }

    public void setTenants(List<Person> tenants) {
        this.tenants = tenants;
    }

    public int getMaxNumberOfTenants() {
        return maxNumberOfTenants;
    }

    public void setMaxNumberOfTenants(int maxNumberOfTenants) {
        this.maxNumberOfTenants = maxNumberOfTenants;
    }
}

