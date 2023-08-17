package com.dermont;

import com.dermont.PersonInfo.Address;
import com.dermont.PersonInfo.Person;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Bukowinska", "3", "13", "00-000", "Warszawa");
        Person person1 = new Person("Andrzej", "Kibala", "60100204181", "10.05.1960", address1);

        person1.printPersonInfo(person1, address1);
    }
}
