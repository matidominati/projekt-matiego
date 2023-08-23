package com.dermont.PersonInfo;

import com.dermont.ResidentialInfo.*;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Person {
    private java.lang.String firstName;
    private java.lang.String lastName;
    private java.lang.String pesel;
    private java.lang.String dateOfBirth;
    private Address address;
    private List<Room> rentedRooms = new ArrayList<>();
    private List<File> DebbtInfo = new ArrayList<>();

    public Person(java.lang.String firstName, java.lang.String lastName, java.lang.String pesel, java.lang.String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public long checkHowManyRoomsRent(Residential residential) {/// przyjmuje ze nie chodzi o mainTenant tylko ogolnie o lokatora
        return residential.getHouses().stream()
                .flatMap(house -> house.getRooms().stream())
                .filter(room -> room.getTenants().contains(this))
                .count();

    }
    public long checkHowManyDebbt(Residential residential) {
        return DebbtInfo.stream()
                .filter(infoFIle -> infoFIle.getName().contains(residential.getResidentialName()))
                .count();
    }
    public void checkRentalExpiration(Residential residential) {
        rentedRooms.stream()
                .filter(room -> room.isRentalExpired())
                .forEach( room -> {
                    String info = "Umowa zakonczenia najmu dobiegla konca dla pomieszczenia o ID: " + room.getIDNumber();
                    File infoFile = new File("Debbt" +  residential.getResidentialName() + room.getIDNumber() + ".txt");
                    try (PrintWriter writer = new PrintWriter(infoFile)){
                        writer.println(info);
                        getDebbtInfo().add(infoFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    public boolean checkIfResponsibleForRent(Residential residential) throws IllegalArgumentException, ProblematicTenantException {
        if (checkHowManyRoomsRent(residential) > 5) {
            throw new IllegalArgumentException("Najemca wynajmuje za duzo pomieszczen na tym osiedlu");
        }
        if (checkHowManyDebbt(residential) > 3) {
            throw new ProblematicTenantException("Osoba " + getFirstName() + " " + getLastName()
                    + "posiadala juz najem pomieszczen: " + getRentedRooms().toString());
        }
        return false;
    }


    public void displayInfo() {
        System.out.println("Imię: " + getFirstName());
        System.out.println("Nazwisko: " + getLastName());
        System.out.println("Numer PESEL: " + getPesel());
        System.out.println("Data urodzenia: " + getDateOfBirth());
        System.out.println("Adres zamieszkania:");
        System.out.println("   ulica: " + address.getStreet());
        System.out.println("   numer mieszkania / numer domu: " + address.getFlatNumber() + " / " + address.getHouseNumber());
        System.out.println("   kod pocztowy: " + address.getPostcode());
        System.out.println("   miasto: " + address.getCity());

        if (rentedRooms.isEmpty()) {
            System.out.println(getFirstName() + " " + getLastName() + " " + "nie wynajmuje żadnych pomieszczen.");
        } else {
            System.out.println(getFirstName() + " " + getLastName() + " " + "wynajmuje nastepujace pomieszcznia:");
            rentedRooms.stream()
                    .map(room -> "Pomieszczenie ID: " + room.getIDNumber())
                    .forEach(r -> System.out.println(r));
        }

    }

    public void addRentedRoom(Room room) {
        rentedRooms.add(room);
    }

    public void removeRentedRoom(Room room) {
        rentedRooms.remove(room);
    }

    public java.lang.String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }

    public java.lang.String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }

    public java.lang.String getPesel() {
        return pesel;
    }

    public void setPesel(java.lang.String pesel) {
        this.pesel = pesel;
    }

    public java.lang.String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.lang.String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Room> getRentedRooms() {
        return rentedRooms;
    }

    public void setRentedRooms(List<Room> rentedRooms) {
        this.rentedRooms = rentedRooms;
    }

    public List<File> getDebbtInfo() {
        return DebbtInfo;
    }

    public void setDebbtInfo(List<File> debbtInfo) {
        this.DebbtInfo = debbtInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                '}';
    }
}