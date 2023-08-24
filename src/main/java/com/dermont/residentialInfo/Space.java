package com.dermont.residentialInfo;

import com.dermont.personInfo.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Space {

    private static int idCounter = 1;
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
        this.id = prefix + idCounter++;
    }

    public boolean isRoomAvailable(Space room) {
        return room.getRentalEndDate() == null;
    }

    public boolean checkIsFlatNotFull(Flat flat) {
        return flat.getTenants().size() < flat.getMaxNumberOfTenants();
    }

    public boolean isRoomEmpty(Space room) {
        return room.getTenants().isEmpty();
    }

    public void displayAvailableRooms(Residential residential) {
        residential.getHouses().stream()
                .flatMap(block -> block.getSpaces().stream())
                .filter(space -> isRoomAvailable(space))
                .forEach(space -> System.out.println("Dostępne pomieszczenie na osiedlu: " + residential.getResidentialName() + " :" + space.getId()));
    }
    public boolean isRentalExpired() {
        return rentalEndDate != null && rentalEndDate.isBefore(LocalDate.now());
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Space.idCounter = idCounter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsableAreaSpace getDimensions() {
        return dimensions;
    }

    public void setDimensions(UsableAreaSpace dimensions) {
        this.dimensions = dimensions;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Person getMainTenant() {
        return mainTenant;
    }

    public void setMainTenant(Person mainTenant) {
        this.mainTenant = mainTenant;
    }


    public List<Person> getTenants() {
        return tenants;
    }

    public void setTenants(List<Person> tenants) {
        this.tenants = tenants;
    }

    @Override
    public String toString() {
        return "Space{" +
                ", IDNumber=" + id +
                ", rentalStartDate=" + rentalStartDate +
                ", rentalEndDate=" + rentalEndDate +
                ", mainTenant=" + mainTenant +
                ", tenants=" + tenants +
                '}';
    }
}
