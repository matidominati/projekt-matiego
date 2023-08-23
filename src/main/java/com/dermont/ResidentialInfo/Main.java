package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Address;
import com.dermont.PersonInfo.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UsableAreaSpace small = new UsableAreaSpace(25);
        UsableAreaSpace medium = new UsableAreaSpace(5, 10, 3);
        UsableAreaSpace large = new UsableAreaSpace(200);
        RentalService rentalService = new RentalService();
        Flat flat1 = new Flat(small, 3);
        Flat flat2 = new Flat(medium, 6);
        Flat flat3 = new Flat(small, 2);
        Flat flat4 = new Flat(small, 1);
        Flat flat5 = new Flat(medium, 1);
        Flat flat6 = new Flat(large, 8);

        ParkingSpace parkgingSpace1 = new ParkingSpace(small);
        ParkingSpace parkgingSpace2 = new ParkingSpace(small);

        House house1 = new House(1, new ArrayList<>());
        house1.addRoom(flat1);
        house1.addRoom(flat2);
        House house2 = new House(2, new ArrayList<>());
        house2.addRoom(flat3);
        house2.addRoom(flat4);
        House house3 = new House(3, new ArrayList<>());
        house3.addRoom(flat5);
        house3.addRoom(flat6);

        List<House> osiedle1 = new ArrayList<>();
        List<House> osiedle2 = new ArrayList<>();

        osiedle1.add(house1);
        osiedle1.add(house2);
        osiedle2.add(house3);

        Residential os1 = new Residential("Osiedle pod gruszka", osiedle1);
        Residential os2 = new Residential("Osiedle pod jablkiem", osiedle2);

        Address adres1 = new Address("Utrata", "2", "4", "16-400", "Suwalki");
        Address adres2 = new Address("Kowalskiego", "60", "2", "16-400", "Suwalki");
        Person person1 = new Person("Andrzej", "Andrzejewski", "0000000000", "19.02.2000", adres1);
        Person person2 = new Person("Maria", "Marianna", "111111111", "01.01.2001", adres2);
        Person person3 = new Person("Krzysztof", "Krzep", "1112313112", "05.05.2005", adres1);
        Person person4 = new Person("Angelika", "Nowak", "1012920912", "01.01.2000", adres2);
        Person person5 = new Person("Slawek", "Slawusiowski", "10129912012", "02.02.2002", adres2);
        Person person6 = new Person("Ryszard", "Kowalski", "12012901212", "01.01.1991", adres1);
        Person person7 = new Person("Monika", "Zieba", "12092401212", "04.12.1992", adres2);
        Person person8 = new Person("Justyna", "Jakas", "1092101212", "04.04.1994", adres1);

        flat1.addTenant(person1);
        flat1.addTenant(person2);
        flat1.addTenant(person3);

        flat2.addTenant(person3);
        flat2.addTenant(person4);
        flat2.addTenant(person1);

        flat6.addTenant(person8);
        flat6.addTenant(person7);
        flat6.addTenant(person6);
        flat6.addTenant(person1);
        flat6.addTenant(person2);

        System.out.println("Czy mieszkanie flat1 jest wolne?");
        System.out.println(rentalService.checkIsFlatNotFull(flat1));
        System.out.println(flat1.getTenants().toString());
//        flat1.addTenant(person8);   // dziala
//        flat1.removeTenant(person4); // dziala
        flat1.removeMainTenant(person1);
        System.out.println("Głowny najemca: " + flat1.getMainTenant().getFirstName() + " " + flat1.getMainTenant().getLastName());
        flat1.removeTenant(person2, flat1);
        System.out.println("Głowny najemca: " + flat1.getMainTenant().getFirstName() + " " + flat1.getMainTenant().getLastName());
        flat1.addTenant(person8);
        System.out.println(flat1.getTenants().toString());

        System.out.print("Czy mieszkanie flat4 jest puste?");
        System.out.println(rentalService.isRoomEmpty(flat4));

//        flat1.addTenant(person3);
//        flat1.addTenant(person4);

        rentalService.rentRoom(person1, os1, house1, flat1); // dziala
rentalService.sendInfo(person1, flat1);
rentalService.sendInfo(person1, flat2);
rentalService.sendInfo(person1, flat3);
        rentalService.displayAvailableRooms(os2);

        System.out.println(person1.getRentedRooms().size());


        person1.displayInfo();


    }
}
