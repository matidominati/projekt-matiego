package com.dermont.PersonInfo;

import com.dermont.ResidentialInfo.*;


import java.util.List;


public class Person {
    private java.lang.String firstName;
    private java.lang.String lastName;
    private java.lang.String pesel;
    private java.lang.String dateOfBirth;
    private Address address;
    private List<Room> rentedRooms;
    private List<String> info;

    public Person(java.lang.String firstName, java.lang.String lastName, java.lang.String pesel, java.lang.String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public boolean checkIfPersonIsResponsibleForRent() {
        return true;

    }

    public int checkHowManyRoomsPersonRent(Person person) {
        return getRentedRooms().size();
    }

    public int checkHowManyDebbtPersonHas(Person person) {
        return getMessages().size();
    }


    public void printPersonInfo(Person person, Address address) {
        System.out.println("Imię: " + person.firstName);
        System.out.println("Nazwisko: " + person.lastName);
        System.out.println("Numer PESEL: " + person.pesel);
        System.out.println("Data urodzenia: " + person.dateOfBirth);
        System.out.println("Adres zamieszkania:");
        System.out.println("   ulica: " + person.address.getStreet());
        System.out.println("   numer mieszkania / numer domu: " + person.address.getFlatNumber() + " / " + person.address.getHouseNumber());
        System.out.println("   kod pocztowy: " + person.address.getPostcode());
        System.out.println("   miasto: " + person.address.getCity());

        // TODO dodac info o wynajmach
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

    public List<String> getMessages() {
        return info;
    }

    public void setMessages(List<String> strings) {
        this.info = strings;
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