package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Person;

public class Flat extends Room {
//    private List<Person> tenants;
    private int maxNumberOfTenants;
    private static int ID = 1;
    private String flatID;

//    public Flat(double usableAreaLength, double usableAreaWidth, double usableAreaHeight, int maxNumberOfTenants) {
//        super(usableAreaLength, usableAreaWidth, usableAreaHeight);
//        this.maxNumberOfTenants = maxNumberOfTenants;
//        this.flatID = "F" + ID++;
//    }
//
//    public Flat(double usableAreaCapacity, int maxNumberOfTenants) {
//        super(usableAreaCapacity);
//        this.maxNumberOfTenants = maxNumberOfTenants;
//        this.flatID = "F" + ID++;
//    }


    public Flat(UsableAreaSpace dimensions, int IDNumber, int maxNumberOfTenants) {
        super(dimensions, IDNumber);
        this.maxNumberOfTenants = maxNumberOfTenants;
        this.flatID = flatID;
    }

    public Flat(UsableAreaSpace dimensions, int maxNumberOfTenants) {
        super(dimensions);
        this.maxNumberOfTenants = maxNumberOfTenants;
        this.flatID = flatID;
    }

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

    public void addTenant(Person newTenant){
        if(getTenants().size() < maxNumberOfTenants){
            getTenants().add(newTenant);
            if (getTenants().size() == 1){
                setMainTenant(newTenant);
            }
        } else {
            throw new IllegalArgumentException("Mieszkanie jest pelne. Brak wolnych miejsc");
        }
    }

    public void removeTenant(Person tenantToRemove, Flat flat){
        if(!getTenants().contains(tenantToRemove)){
            throw new IllegalArgumentException("Podana osoba nie jest lokatorem tego mieszkania");
        } else {
            getTenants().remove(tenantToRemove);
            tenantToRemove.removeRentedRoom(flat);
            setMainTenant(getTenants().stream()
                    .findFirst()
                    .orElse(null));
        }
    }

//    public List<Person> getTenants() {
//        return tenants;
//    }
//
//    public void setTenants(List<Person> tenants) {
//        this.tenants = tenants;
//    }

    public int getMaxNumberOfTenants() {
        return maxNumberOfTenants;
    }

    public void setMaxNumberOfTenants(int maxNumberOfTenants) {
        this.maxNumberOfTenants = maxNumberOfTenants;
    }
}

