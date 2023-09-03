
//TODO brakuje: dodawania/usuwania niektorych przedmiotow -> metody są napiasne, ale jeszcze nie zaimplementowane w scannera. Na sucho dzialaja (zakomentowane 102-117)



package com.dermont.residentialInfo;
import com.dermont.exceptions.*;
import com.dermont.personInfo.Address;
import com.dermont.personInfo.Person;
import com.dermont.storedItems.CityVehicle;
import com.dermont.storedItems.Items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ProblematicTenantException, TooManyThingsException, ItemToWideException, ItemToHighException, ItemTooLongException, IOException {
        Scanner scannerMain = new Scanner(System.in);
        RentalService rentalService = new RentalService();

        AreaSpace smallFlat = new AreaSpace(10,2,2.5);
        AreaSpace mediumFlat = new AreaSpace(20,2, 2.5);
        AreaSpace largeFlat = new AreaSpace(30,2,2.5);
        AreaSpace smallParkingSpace = new AreaSpace(5, 1.8, 2.5);
        AreaSpace mediumParkingSpace = new AreaSpace(8, 2.5, 2.5);
        AreaSpace largeParkingSpace = new AreaSpace(10, 3, 2.5);


        Space flat1 = new Flat(smallFlat, 2);
        Space flat2 = new Flat(mediumFlat, 3);
        Space flat3 = new Flat(mediumFlat, 3);
        Space flat4 = new Flat(mediumFlat, 3);
        Space flat5 = new Flat(largeFlat, 4);
        Space flat6 = new Flat(largeFlat, 4);
        Space parkingSpace1 = new ParkingSpace(smallParkingSpace);
        Space parkingSpace2 = new ParkingSpace(smallParkingSpace);
        Space parkingSpace3 = new ParkingSpace(mediumParkingSpace);
        Space parkingSpace4 = new ParkingSpace(largeParkingSpace);
        Space parkingSpace5 = new ParkingSpace(mediumParkingSpace);

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

        AreaSpace bmwI3 = new AreaSpace(3, 1.6, 1.5);
        CityVehicle cityVehicle1 = new CityVehicle("BMW", bmwI3, "petrol", 2, false, 999.3, 1250);

        AreaSpace item1 = new AreaSpace(0.1, 0.1, 0.1);
        Items item = new Items("wihajster", item1);

        osiedle1.getTenants().add(person1);
        osiedle1.getTenants().add(person2);
        osiedle1.getTenants().add(person3);
        osiedle1.getTenants().add(person4);
        osiedle1.getTenants().add(person5);
        osiedle1.getTenants().add(person6);

        flat1.addTenant(person1, flat1);
        flat2.addTenant(person1, flat2);
        flat3.addTenant(person2, flat3);
        flat4.addTenant(person3, flat4);
        parkingSpace1.rentParkingSpace(person1, parkingSpace1);
        parkingSpace1.addItem(cityVehicle1);
        parkingSpace1.addItem(item);
        parkingSpace2.rentParkingSpace(person1, parkingSpace2);
        parkingSpace3.rentParkingSpace(person2, parkingSpace3);
        parkingSpace4.rentParkingSpace(person3, parkingSpace4);


        Person selectedPerson = rentalService.displayListOfUsers(osiedle1, scannerMain);
        rentalService.handleUserMenu(selectedPerson, osiedle1);


        /// Te metody dzialaja
//        parkingSpace1.addItem(item);
//        System.out.println(parkingSpace1.getStoredItems());
//        parkingSpace1.removeItem(item);
//        System.out.println(parkingSpace1.getStoredItems());

//        flat1.addTenant(person2, flat1); // dodaje Mostowiaka
//        flat1.addTenant(person3,flat1); // dodaje Kapuste
//        System.out.println(flat1.getTenants()); // oczekiwane 3 lokatorow
//        flat1.removeMainTenant(person1); // usuwam Dybale z roli szefa
//        flat1.removeTenant(person3,flat1); // usuwam Kapuste z mieszkania
//        flat1.addTenant(person6,flat1); // dodaje Pawlak
//
//        System.out.println(flat1.getMainTenant()); // sprawdzam szefa: oczekiwany Mostowiak
//        flat1.removeTenant(person2, flat1); // usuwam Mostowiaka z roli szefa
//        System.out.println(flat1.getMainTenant()); // sprawdzam szefa: oczekiwana Pawlak


    }


}





