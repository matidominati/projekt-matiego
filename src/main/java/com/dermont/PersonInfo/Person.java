package com.dermont.PersonInfo;

import com.dermont.ResidentialInfo.Flat;
import com.dermont.ResidentialInfo.ParkingSpace;
import com.dermont.ResidentialInfo.Residential;

import java.util.List;


public class Person {
    private String firstName;
    private String lastName;
    private String pesel;
    private String dateOfBirth;
    private Address address;
    private List<ParkingSpace> rentedParkingSpace;
    private List<Flat> rentedFlat;

    public Person(String firstName, String lastName, String pesel, String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
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

    public boolean checkIfRentFlat(Person person, Residential residential) {
        return
                person.getRentedFlat().stream()
                        .anyMatch(flat -> flat.getResidential().equals(residential));

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ParkingSpace> getRentedParkingSpace() {
        return rentedParkingSpace;
    }

    public void setRentedParkingSpace(List<ParkingSpace> rentedParkingSpace) {
        this.rentedParkingSpace = rentedParkingSpace;
    }

    public List<Flat> getRentedFlat() {
        return rentedFlat;
    }

    public void setRentedFlat(List<Flat> rentedFlat) {
        this.rentedFlat = rentedFlat;
    }
}
