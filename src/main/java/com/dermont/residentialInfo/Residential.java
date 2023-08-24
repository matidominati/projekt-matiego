package com.dermont.residentialInfo;

import com.dermont.personInfo.Person;

import java.util.ArrayList;
import java.util.List;

public class Residential {
    private String residentialName;
    private List<House> houses;
    private List<Person> tenants = new ArrayList<>();


    public Residential(String residentialName, List<Block> blocks) {
        this.residentialName = residentialName;
        this.blocks = blocks;
    }

    public Person findPersonByID(int id){
        return tenants.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addHouse (Block block){
        blocks.add(block);
    }

    public String getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(String residentialName) {
        this.residentialName = residentialName;
    }

    public List<Block> getHouses() {
        return blocks;
    }

    public void setHouses(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Person> getTenants() {
        return tenants;
    }

    public void setTenants(List<Person> tenants) {
        this.tenants = tenants;
    }
}
