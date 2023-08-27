package com.dermont.residentialInfo;

import com.dermont.personInfo.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Space {

    private static int flatIdCounter = 1;
    private static int parkingIdCounter = 1;
    private String id;
    private UsableAreaSpace dimensions;
    private static int ID = 1;
    private int IDNumber;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Person mainTenant;
    private List<Person> tenants = new ArrayList<>();

    public Space(UsableAreaSpace dimensions, String prefix) {
        this.dimensions = new UsableAreaSpace(dimensions.getUsableAreaCapacity());
        if (prefix.equals("F")) {
            this.id = prefix + flatIdCounter++;
        } else {
            this.id = prefix + parkingIdCounter++;
        }
    }

        public boolean isSpaceAvailable (Space space){
            if (space instanceof Flat) {
                Flat flat = (Flat) space;
                return space.getRentalEndDate() == null && flat.getTenants().size() < flat.getMaxNumberOfTenants();
            }
            return space.getRentalEndDate() == null;
        }

        public void displaySpaceContents (Space space){
            if (space instanceof Flat) {
                Flat flat = (Flat) space;
                System.out.print("Ilość lokatorow w mieszkaniu " + flat.getId() + ": ");
                System.out.println(flat.getTenants().size());
                System.out.println(flat.getTenants().toString());
                System.out.println("Glowny najemca" + flat.getMainTenant());
            }
            if (space instanceof ParkingSpace) {
                ParkingSpace parkingSpace = (ParkingSpace) space;
                System.out.println("Zawartosc miejsca parkignowego " + parkingSpace.getId() + ": ");
                System.out.println(parkingSpace.getStoredItems().toString());
            }
        }


        public boolean isRentalExpired () {
            return rentalEndDate != null && rentalEndDate.isBefore(LocalDate.now());
        }

        public static int getFlatIdCounter () {
            return flatIdCounter;
        }

        public static void setFlatIdCounter ( int flatIdCounter){
            Space.flatIdCounter = flatIdCounter;
        }

        public String getId () {
            return id;
        }

        public void setId (String id){
            this.id = id;
        }

        public UsableAreaSpace getDimensions () {
            return dimensions;
        }

        public void setDimensions (UsableAreaSpace dimensions){
            this.dimensions = dimensions;
        }

        public LocalDate getRentalStartDate () {
            return rentalStartDate;
        }

        public void setRentalStartDate (LocalDate rentalStartDate){
            this.rentalStartDate = rentalStartDate;
        }

        public LocalDate getRentalEndDate () {
            return rentalEndDate;
        }

        public void setRentalEndDate (LocalDate rentalEndDate){
            this.rentalEndDate = rentalEndDate;
        }

        public Person getMainTenant () {
            return mainTenant;
        }

        public void setMainTenant (Person mainTenant){
            this.mainTenant = mainTenant;
        }


        public List<Person> getTenants () {
            return tenants;
        }

        public void setTenants (List < Person > tenants) {
            this.tenants = tenants;
        }

        public static int getParkingIdCounter () {
            return parkingIdCounter;
        }

        public static void setParkingIdCounter ( int parkingIdCounter){
            Space.parkingIdCounter = parkingIdCounter;
        }

        @Override
        public String toString () {
            return "Space{" +
                    ", IDNumber=" + id +
                    ", rentalStartDate=" + rentalStartDate +
                    ", rentalEndDate=" + rentalEndDate +
                    ", mainTenant=" + mainTenant +
                    ", tenants=" + tenants +
                    '}';
        }
    }
