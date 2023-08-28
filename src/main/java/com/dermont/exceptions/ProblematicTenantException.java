package com.dermont.exceptions;

import com.dermont.personInfo.Person;

public class ProblematicTenantException extends Exception {
    public ProblematicTenantException(Person person) {
        super("Osoba" + person.getFirstName() + " " + person.getLastName() + " posiadala juz najem pomieszczen: "
        + person.getRentedSpaces().toString());
    }
}

