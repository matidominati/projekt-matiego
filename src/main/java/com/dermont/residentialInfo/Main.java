package com.dermont.residentialInfo;

import com.dermont.personInfo.Address;
import com.dermont.personInfo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScannerMethods scannerMethods = new ScannerMethods();

        UsableAreaSpace smallFlat = new UsableAreaSpace(28);
        UsableAreaSpace mediumFlat = new UsableAreaSpace(50);
        UsableAreaSpace largeFlat = new UsableAreaSpace(85);
        UsableAreaSpace smallParkingSpace = new UsableAreaSpace(8);
        UsableAreaSpace mediumParkingSpace = new UsableAreaSpace(10);
        UsableAreaSpace largeParkingSpace = new UsableAreaSpace(15);

        Flat flat1 = new Flat(smallFlat, 2);
        Flat flat2 = new Flat(mediumFlat, 3);
        Flat flat3 = new Flat(mediumFlat, 3);
        Flat flat4 = new Flat(mediumFlat, 3);
        Flat flat5 = new Flat(largeFlat, 4);
        Flat flat6 = new Flat(largeFlat, 4);
        ParkingSpace parkingSpace1 = new ParkingSpace(smallParkingSpace);
        ParkingSpace parkingSpace2 = new ParkingSpace(smallParkingSpace);
        ParkingSpace parkingSpace3 = new ParkingSpace(mediumParkingSpace);
        ParkingSpace parkingSpace4 = new ParkingSpace(largeParkingSpace);
        ParkingSpace parkingSpace5 = new ParkingSpace(mediumParkingSpace);

        List<Space> spacesBlock1 = new ArrayList<>();
        spacesBlock1.add(flat1);
        spacesBlock1.add(flat2);
        spacesBlock1.add(flat3);
        spacesBlock1.add(flat4);
        spacesBlock1.add(flat5);
        spacesBlock1.add(flat6);
        spacesBlock1.add(parkingSpace1);
        spacesBlock1.add(parkingSpace2);
        spacesBlock1.add(parkingSpace3);
        spacesBlock1.add(parkingSpace4);
        spacesBlock1.add(parkingSpace5);

        Block blok1 = new Block(9, spacesBlock1);
        List<Block> blocks = new ArrayList<>();
        blocks.add(blok1);
        Residential osiedle1 = new Residential("Sloneczne Wzgorze", blocks);

        Address adres1 = new Address("Utrata", "10", "5", "16-400", "Suwalki");
        Address adres2 = new Address("Nowomiejska", "65", "6", "02-004", "Warszawa");
        Address adres3 = new Address("Wawelska", "2", "22", "33-545", "Krakow");
        Address adres4 = new Address("Kosciuszki", "6", "8", "13-520", "Olecko");
        Address adres5 = new Address("Rejmonta", "10", "4", "06-999", "Piasczno");
        Address adres6 = new Address("Postepu", "10", "124C", "05-505", "Lesznowola");

        Person person1 = new Person("Maciej", "Dybala", "93020104171", "01.02.1993", adres1);
        Person person2 = new Person("Marek", "Mostowiak", "88020104171", "01.02.1988", adres2);
        Person person3 = new Person("Andrzej", "Kapusta", "70020104171", "01.02.1970", adres3);
        Person person4 = new Person("Jadwiga", "Kowalska", "95021604171", "16.02.1996", adres4);
        Person person5 = new Person("Urszula", "Nowak", "90121204171", "12.12.1990", adres5);
        Person person6 = new Person("Julia", "Pawlak", "86051304171", "13.05.1986", adres6);

        osiedle1.getTenants().add(person1);
        osiedle1.getTenants().add(person2);
        osiedle1.getTenants().add(person3);
        osiedle1.getTenants().add(person4);
        osiedle1.getTenants().add(person5);
        osiedle1.getTenants().add(person6);


        scannerMethods.printMenuListOfUsers(osiedle1);




        }



}
