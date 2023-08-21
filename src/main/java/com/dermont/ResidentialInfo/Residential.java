package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Person;

import java.util.ArrayList;
import java.util.List;

public class Residential {
    private String residentialName;
    private List<House> houses = new ArrayList<>();
    private List<Person> tenants = new ArrayList<>();


    public Residential(String residentialName, List<House> houses) {
        this.residentialName = residentialName;
        this.houses = houses;
    }

    public void addHouse (House house){
        houses.add(house);
    }

    public String getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(String residentialName) {
        this.residentialName = residentialName;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
