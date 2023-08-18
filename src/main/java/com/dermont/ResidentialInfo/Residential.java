package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Person;
import com.dermont.PersonInfo.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Residential {

    private String residentialName;
    private List<House> houses = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();




    public Residential(String residentialName, List<House> houses) {
        this.residentialName = residentialName;
        this.houses = houses;
    }

    public void rentParkingSpace (Person person, ParkingSpace parkingSpace, Residential residential){
        if(person.checkIfRentFlatInResidential(person, residential) && person.numberOfRentals(person) <= 5){
            person.addParkingSpace(parkingSpace);

            Rental rental = new Rental();
            rental.setTenant(person);
            rental.setParkingSpace(parkingSpace);
            rental.setRentalStartDate(LocalDate.now());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj datę zakończenia wynajmu (RRRR-MM-DD): ");
            String endDateString = scanner.nextLine();
            LocalDate endDate = LocalDate.parse(endDateString);
            rental.setRentalEndDate(endDate);





        } else {
            throw new IllegalArgumentException("Osoba nie może wynająć miejsca parkingowego. Możliwe powody: a) osoba nie wynajmuje mieszkania na tym osiedlu, " +
                    "b) osoba wynajmuje już więcej niż 5 pomieszczeń na tym osiedlu");
        }
    }

    public void addHouse (House house){
        houses.add(house);
    }



    public String getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(String residentialName) {
        this.residentialName = residentialName;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
