package com.dermont.ResidentialInfo;

import com.dermont.PersonInfo.Person;

import java.time.LocalDate;

public class RentalService {
    private Person tenant;
    private Room rentRoom;

//    public RentalService(Person tenant, Room rentRoom) {
//        this.tenant = tenant;
//        this.rentRoom = rentRoom;
//    }

    public void rentRoom(Person tenant, Residential residential, House house, Room room) {
        room.findRoom(residential, house, room)
                .ifPresentOrElse(rentedRoom -> {
                            if (!isRoomAvailable(room)) {
                                throw new IllegalArgumentException("Wybrane pomieszczenie jest juz zajete");
                            }
                            if (rentedRoom instanceof Flat) {
                                Flat flat = (Flat) rentedRoom;
                                if (!checkIsFlatNotFull(flat)) {
                                    throw new IllegalArgumentException("Mieszkanie jest juz pelne. Brak wolnych miejsc.");
                                }
                                flat.setRentalStartDate(LocalDate.now());
                                if (isRoomEmpty(flat)) {
                                    flat.setMainTenant(tenant);
                                } else {
                                    flat.getTenants().add(tenant);
                                }
                            } else if (rentedRoom instanceof ParkingSpace) {
                                ParkingSpace parkingSpace = (ParkingSpace) rentedRoom;
                                if (isRoomEmpty(parkingSpace)) {
                                    parkingSpace.getTenants().add(tenant);
                                } else {
                                    throw new IllegalArgumentException("Miejsce parkingowe jest juz zajete");
                                }
                                parkingSpace.setRentalStartDate(LocalDate.now());
                            } else {
                                throw new IllegalArgumentException("Nieznany typ pomieszczenia");
                            }
                        },
                        () -> {
                            throw new IllegalArgumentException("Nie znaleziono pomieszczenia w bloku nr " + house.getHouseNumber()
                                    + " na osiedlu " + residential.getResidentialName());
                        });
    }


    public boolean isRoomAvailable(Room room) {
        return room.getRentalEndDate() == null;
    }

    public boolean checkIsFlatNotFull(Flat flat) {
        return flat.getTenants().size() < flat.getMaxNumberOfTenants();
    }

    public boolean isRoomEmpty(Room room) {
        return room.getTenants().isEmpty();
    }

    public void sendInfo(Person person, Room room) {
        LocalDate currentDate = LocalDate.now();
        if (room.getRentalEndDate() != null && room.getRentalEndDate().isBefore(currentDate)) {
            String info = "Tresc zawiadomienia";
            person.getMessages().add(info);
        }

    }

    public Person getTenant() {
        return tenant;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }


    public Room getRentRoom() {
        return rentRoom;
    }

    public void setRentRoom(Room rentRoom) {
        this.rentRoom = rentRoom;
    }
}
