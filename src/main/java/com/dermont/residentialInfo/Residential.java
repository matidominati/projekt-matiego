package com.dermont.residentialInfo;

import com.dermont.exceptions.ProblematicTenantException;
import com.dermont.personInfo.Person;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Residential {
    private String residentialName;
    private List<Block> blocks;
    private List<Person> tenants = new ArrayList<>();


    public Residential(String residentialName, List<Block> blocks) {
        this.residentialName = residentialName;
        this.blocks = blocks;
    }
    public long checkHowManySpacesPersonRentOn(Person person) {/// przyjmuje ze nie chodzi o mainTenant tylko ogolnie o lokatora
        return getBlocks().stream()
                .flatMap(block -> block.getSpaces().stream())
                .filter(space -> space.getTenants().contains(person))
                .count();
    }

    public long checkHowManyDebbtPersonHaveOn(Person person) {
        return person.getDebbtInfo().stream()
                .filter(infoFile -> infoFile.getName().contains(getResidentialName()))
                .count();
    }
    public void checkPersonRentalExpiration(Person person) {
        person.getRentedSpaces().stream()
                .filter(space -> space.isRentalExpired())
                .forEach(space -> {
                    String info = "Umowa zakonczenia najmu dobiegla konca dla pomieszczenia o ID: " + space.getId();
                    File infoFile = new File("Debbt" + getResidentialName() + space.getId() + ".txt");
                    try (PrintWriter writer = new PrintWriter(infoFile)) {
                        writer.println(info);
                        person.getDebbtInfo().add(infoFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    public void checkIfPersonIsResponsibleForRent(Person person) throws IllegalArgumentException, ProblematicTenantException {
        if (checkHowManySpacesPersonRentOn(person) > 5) {
            throw new IllegalArgumentException("Najemca wynajmuje za duzo pomieszczen na tym osiedlu");
        }
        if (checkHowManyDebbtPersonHaveOn(person) > 3) {
            throw new ProblematicTenantException(person);
        }

    }


    public void checkPersonRentedSpaces(Person person){
        if (person.getRentedSpaces().isEmpty()) {
            System.out.println(person.getFirstName() + " " + person.getLastName() + " " + "nie wynajmuje żadnych pomieszczen.");
        } else {
            System.out.println(person.getFirstName() + " " + person.getLastName() + " " + "wynajmuje nastepujace pomieszcznia:");
            person.getRentedSpaces().stream()
                    .map(space -> "Pomieszczenie ID: " + space.getId())
                    .forEach(s -> System.out.println(s));
        }
    }

    public Person findPersonByID(int id) {
        return tenants.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void displayAllAvailableSpaces() {
        getAvailableSpace();
        getAvailableParkingSpaces();
        getAvailableFlats();

        System.out.println("Miejsca pakrkingowe:");
        getAvailableParkingSpaces().stream()
                .forEach(parkingSpace -> System.out.println(" " + parkingSpace.getId()));
        System.out.println("Mieszkania:");
        getAvailableFlats().stream()
                .forEach(flat -> System.out.println((" " + flat.getId())));


    }

    public List<Space> getAvailableSpace() {
        return getBlocks().stream()
                .flatMap(block -> block.getSpaces().stream())
                .filter(space -> space.isSpaceAvailable())
                .collect(Collectors.toList());
    }

    public List<Space> getAvailableParkingSpaces() {
        return getAvailableSpace().stream()
                .filter(space -> space.getId().startsWith("P"))
                .collect(Collectors.toList());
    }

    public List<Space> getAvailableFlats() {
        return getAvailableSpace().stream()
                .filter(space -> space.getId().startsWith("F"))
                .collect(Collectors.toList());
    }


    public void addBlock(Block block) {
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
