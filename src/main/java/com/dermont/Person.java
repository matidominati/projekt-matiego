package com.dermont;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private String PESEL;
    private String dateOfBirth;
    private Address address;

    public Person(String firstName, String lastName, String PESEL, String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.PESEL = PESEL;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void PrintPersonInfo(Person person, Address address) {
        System.out.println("Imię: " + person.firstName);
        System.out.println("Nazwisko: " + person.lastName);
        System.out.println("Numer PESEL: " + person.PESEL);
        System.out.println("Data urodzenia: " + person.dateOfBirth);
        System.out.println("Adres zamieszkania:");
        System.out.println("   ulica: " + person.address.getStreet());
        System.out.println("   numer mieszkania / numer domu: " + person.address.getFlatNumber() + " / " + person.address.getHouseNumber());
        System.out.println("   kod pocztowy: " + person.address.getPostcode());
        System.out.println("   miasto: " + person.address.getCity());
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
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
}
