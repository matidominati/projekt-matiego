package com.dermont.residentialInfo;

import com.dermont.personInfo.Person;

import java.util.List;

public class Flat extends Space {
    private int maxNumberOfTenants;
    private List<Person> tenants;


    public Flat(UsableAreaSpace dimensions, int maxNumberOfTenants) {
        super(dimensions, "F");
        this.maxNumberOfTenants = maxNumberOfTenants;

    }

    public void addTenant(Person newTenant) {
        if (getTenants().size() < maxNumberOfTenants) {
            getTenants().add(newTenant);
            if (getTenants().size() == 1) {
                setMainTenant(newTenant);
            }
        } else {
            throw new IllegalArgumentException("Mieszkanie jest pelne. Brak wolnych miejsc");
        }
    }

    public void removeTenant(Person tenantToRemove, Flat flat) {
        if (!getTenants().contains(tenantToRemove)) {
            throw new IllegalArgumentException("Podana osoba nie jest lokatorem tego mieszkania");
        } else {
            getTenants().remove(tenantToRemove);
            tenantToRemove.removeRentedRoom(flat);
            setMainTenant(getTenants().stream()
                    .findFirst()
                    .orElse(null));
        }
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

