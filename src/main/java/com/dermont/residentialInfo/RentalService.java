
package com.dermont.residentialInfo;

import com.dermont.exceptions.*;
import com.dermont.personInfo.Address;
import com.dermont.personInfo.Person;
import com.dermont.storedItems.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class RentalService {
    Menu menu = new Menu();
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerString = new Scanner(System.in);
    Scanner scannerItem = new Scanner(System.in);
    ResidentialFileWriter residentialFileWriter = new ResidentialFileWriter();

    public void displayAvaiablePerson(Residential residential) {
        System.out.println("Dostepne osoby: ");
        System.out.println("");
        residential.getTenants().forEach(person -> {
            System.out.println("ID " + person.getId() + "  " + person.getFirstName() + " " + person.getLastName());
        });
    }

    public Person displayListOfUsers(Residential residential, Scanner scannerInt) throws IOException {
        menu.printTopMenu();
        displayAvaiablePerson(residential);
        menu.printExitMenu();
        Person selectedPerson = null;
        boolean personNotFound = true;
        while (personNotFound) {
            System.out.println("");
            System.out.println("Wybierz osobe (podaj ID) lub zamknij program: ");
            int selectedPersonID = scannerInt.nextInt();
            if (selectedPersonID == 0) {
                residentialFileWriter.writeResidentialToFile(residential, "stan osiedla_" + residential.getResidentialName());
                menu.exitProgram();
                return null;
            } else {
                selectedPerson = residential.findPersonByID(selectedPersonID);
                if (selectedPerson == null) {
                    System.out.println("Nie znaleziono osoby o podanym ID! Wybierz osobe z listy ponizej:");
                    displayAvaiablePerson(residential);
                } else {
                    personNotFound = false;
                }
            }
        }
        return selectedPerson;
    }

    public void chooseWhatToDisplayElse(Person selectedPerson, Residential residential) throws ProblematicTenantException, ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException, IOException {
        int reverseSelectionOption;
        reverseSelectionOption = scannerInt.nextInt();
        if (reverseSelectionOption == 0) {
            residentialFileWriter.writeResidentialToFile(residential, "stan osiedla_" + residential.getResidentialName());
            menu.exitProgram();
        } else if (reverseSelectionOption == 9) {
            menu.printPersonOptionMenu();
            menu.reverseOrExit();
            chooseWhatToDisplayMenuAfterPersonID(selectedPerson, residential, scannerInt);
            chooseWhatToDisplayElse(selectedPerson, residential);

        } else {
            System.out.println("Nieznana opcja.");
            System.out.println("");
            menu.printPersonOptionMenu();
            chooseWhatToDisplayMenuAfterPersonID(selectedPerson, residential, scannerInt);

        }
    }

    public void chooseWhatToDisplayMenuAfterPersonID(Person selectedPerson, Residential residential, Scanner scannerInt) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException, ProblematicTenantException, IOException {
        int selectedOption;
        selectedOption = scannerInt.nextInt();
        switch (selectedOption) {
            case 0:
                residentialFileWriter.writeResidentialToFile(residential, "stan osiedla_" + residential.getResidentialName());
                menu.exitProgram();
                break;
            case 1:
                System.out.println("INFORMACJE:");
                personInfo(selectedPerson, residential, scannerInt);
                menu.reverseOrExit();
                chooseWhatToDisplayElse(selectedPerson, residential);
                break;
            case 2:
                System.out.println("WOLNE POMIESZCZENIA");
                displayRoomsForRent(residential);
                menu.reverseOrExit();
                chooseWhatToDisplayElse(selectedPerson, residential);
                menu.reverseOrExit();
                break;
            case 3:
                System.out.println("WYNAJMIJ POMIESZCZENIA");
                residential.checkIfPersonIsResponsibleForRent(selectedPerson);
                rentSpaceByOn(selectedPerson, residential);
                menu.reverseOrExit();
                chooseWhatToDisplayElse(selectedPerson, residential);
                menu.reverseOrExit();
                break;
            case 9:
                Person selectedPersonDummy = displayListOfUsers(residential, scannerInt);
                handleUserMenu(selectedPersonDummy, residential);
                break;
            default:
                System.out.println("Nieprawidlowy wybor. Wybierz z ponizszych opcji:");
                handleUserMenu(selectedPerson, residential);
                break;

        }
    }

    public void handleUserMenu(Person selectedPerson, Residential residential) throws ProblematicTenantException, ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException, IOException {
        if (residential.checkHowManyDebbtPersonHaveOn(selectedPerson) > 3) {
            System.out.println("UWAGA! WYBRANA OSOBA POSIADA ZALEGLE NAJMY!");
        }
        System.out.println("Wybrana osoba: " + selectedPerson.getFirstName() + " " + selectedPerson.getLastName());
        menu.printPersonOptionMenu();
        menu.reverseOrExit();
        chooseWhatToDisplayMenuAfterPersonID(selectedPerson, residential, scannerInt);
    }

    public void rentSpaceByOn(Person selectedPerson, Residential residential) throws ProblematicTenantException {
        if (residential.getAvailableSpace().size() > 0) {
            residential.checkIfPersonIsResponsibleForRent(selectedPerson);
            System.out.println("WYBIERZ POMIESZCZENIE, KTORE CHCESZ WYNAJAC (PODAJ ID):");
            displayRoomsForRent(residential);
            checkTypeOfSpaceForRent(selectedPerson, residential);
        }
    }

    public void checkTypeOfSpaceForRent(Person selectedPerson, Residential residential) {
        Space selectedSpace = selectSpaceByIdIfAvailable(residential, scannerString);
        if (selectedSpace instanceof Flat) {
            selectedSpace.addTenant(selectedPerson, selectedSpace);
            System.out.println("Poprawnie wynajeto mieszkanie");
        } else if (selectedSpace instanceof ParkingSpace) {
            selectedSpace.rentParkingSpace(selectedPerson, selectedSpace);
            System.out.println("Poprawnie wynajeto miejsce parkingowe");
        } else {
            System.out.println("Błędne ID pomieszczenia");
        }
    }

    public Space selectSpaceByIdIfAvailable(Residential residential, Scanner scannerString) {
        String selectedSpaceId = scannerString.nextLine().toUpperCase();
        return residential.getAvailableSpace().stream()
                .filter(space -> selectedSpaceId.equals(space.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pomieszczenia o ID: " + selectedSpaceId + " nie jest dostepne."));

    }

    public Space selectSpaceByIdIfExist(Residential residential, Scanner scannerString) {
        String selectedSpaceId = scannerString.nextLine().toUpperCase();
        return residential.getBlocks().stream()
                .flatMap(block -> block.getSpaces().stream())
                .filter(space -> selectedSpaceId.equals(space.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pomieszczenia o ID: " + selectedSpaceId + " nie istnieje."));
    }

    public void checkIfPersonRentSpaceToShowContents(Person selectedPerson, Residential residential) {
        Space selectedSpace = checkPersonRentedSpace(selectedPerson, residential);
        if (selectedSpace != null) {
            selectedSpace.displaySpaceContents();
        }
    }

    public void checkTypeOfSpaceToAddSomething(Person selectedPerson, Residential residential) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException, IOException {
        if(selectedPerson.getRentedSpaces().size()>0) {


            System.out.println("");
            System.out.println("Aby zmodyfikować zawartość pomieszczenia, wpisz jego ID: ");
            Space selectedSpace = checkPersonRentedSpace(selectedPerson, residential);

            if (selectedSpace != null) {
                boolean continueModifying = true;

                while (continueModifying) {
                    if (selectedSpace instanceof Flat) {
                        menu.printAddToFlatMenu();
                    } else if (selectedSpace instanceof ParkingSpace) {
                        menu.printAddToParkingSpaceMenu();
                    }
                    int innerOption = scannerInt.nextInt();
                    switch (innerOption) {
                        case 0:
                            residentialFileWriter.writeResidentialToFile(residential, "stan osiedla_" + residential.getResidentialName());
                            continueModifying = false;
                            menu.exitProgram();
                            break;
                        case 1:
                            if (selectedSpace instanceof Flat) {
                                createTenant((Flat) selectedSpace);

                            } else if (selectedSpace instanceof ParkingSpace) {
                                menu.printListOfItemsToAddMenu();
                                handleParkingSpaceItemOption(selectedSpace);
                            }
                            continueModifying = false;
                            break;
                        case 2:
                            if (selectedSpace instanceof Flat) {
                                removeTenant((Flat) selectedSpace);
                            } else if (selectedSpace instanceof ParkingSpace) {

                            }
                            continueModifying = false;
                            break;
                        case 9:
                            displayRentedSpacesAndContents(selectedPerson, residential);
                            break;

                        default:
                            System.out.println("Nieznana opcja.");
                            break;
                    }
                }
            }
        } else throw new  RuntimeException(selectedPerson.getFirstName() +  " " +selectedPerson.getLastName() + " nie wynajmuje zadnego pomieszczenia");

    }

    public void handleParkingSpaceItemOption(Space selectedSpace) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        menu.printListOfItemsToAddMenu();
        int itemOption = scannerInt.nextInt();
        switch (itemOption) {
            case 1:
                createAmphibiousVehicle(selectedSpace);
                break;
            case 2:
                System.out.println("Funkcja jeszcze niedostepna");
                break;
            case 3:
                System.out.println("Funkcja jeszcze niedostepna");
                break;
            case 4:
                System.out.println("Funkcja jeszcze niedostepna");
                break;
            case 5:
                System.out.println("Funkcja jeszcze niedostepna");
                break;
            case 6:
                createOtherItem(selectedSpace);
                break;
            default:
                System.out.println("Nieznany rodzaj przedmiotu");
                break;
        }

    }



    private Space checkPersonRentedSpace(Person selectedPerson, Residential residential) {
        Space selectedSpace = null;
        while (selectedSpace == null) {
            selectedSpace = selectSpaceByIdIfExist(residential, scannerString);
            if (!selectedPerson.getRentedSpaces().contains(selectedSpace)) {
                System.out.println(selectedPerson.getFirstName() + " " + selectedPerson.getLastName() +
                        " nie wynajmuje pomieszczenia o podanym ID!");
                System.out.println("Wpisz ponownie poprawne ID: ");
                selectedSpace = null;
            }
        }
        return selectedSpace;
    }


    public void personInfo(Person selectedPerson, Residential residential, Scanner scannerInt) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException, ProblematicTenantException, IOException {
        menu.printPersonInfoMenu();
        menu.reverseOrExit();
        int selectedOption;
        selectedOption = scannerInt.nextInt();
        switch (selectedOption) {
            case 0:
                residentialFileWriter.writeResidentialToFile(residential, "stan osiedla_" + residential.getResidentialName());
                menu.exitProgram();
                break;
            case 1:
                System.out.println("DANE PERSONALNE");
                selectedPerson.displayInfo();
                break;
            case 2:
                displayRentedSpacesAndContents(selectedPerson, residential);
                checkTypeOfSpaceToAddSomething(selectedPerson, residential);
                break;
            case 3:
                System.out.println("ILOSC PRZETERMINOWANYCH NAJMOW:");
                System.out.println(residential.checkHowManyDebbtPersonHaveOn(selectedPerson));
                break;
            case 9:
                menu.printPersonOptionMenu();
                menu.reverseOrExit();
                chooseWhatToDisplayMenuAfterPersonID(selectedPerson, residential, scannerInt);

                break;
            default:
                System.out.println("Nieprawidlowy wybor. Sprobuj ponownie");
                personInfo(selectedPerson, residential, scannerInt);
                break;

        }
    }


    public void displayRoomsForRent(Residential residential) {
        residential.displayAllAvailableSpaces();
    }

    public void displayRentedSpacesAndContents(Person selectedPerson, Residential residential) {
        System.out.println("WYNAJMOWANE POMIESZCZENIA");
        residential.checkPersonRentedSpaces(selectedPerson);
        System.out.println("Ilosc wynajmowanych pomieszczen: " + residential.checkHowManySpacesPersonRentOn(selectedPerson));
        System.out.println("");
        if (selectedPerson.getRentedSpaces().size() != 0) {
            System.out.println("Aby wyswietlic zawartosc pomieszczenia wpisz jego ID: ");
            checkIfPersonRentSpaceToShowContents(selectedPerson, residential);

        }

    }


    public AreaSpace createDimensionsFromUserInput() {
        System.out.println("Podaj dlugosc [m]:");
        double lengthItem = scannerItem.nextDouble();
        System.out.println("Podaj szerokosc [m]:");
        double widthItem = scannerItem.nextDouble();
        System.out.println("Podaj wysokosc [m]:");
        double heightItem = scannerItem.nextDouble();

        return new AreaSpace(lengthItem, widthItem, heightItem);
    }

    public Address createTenantAddress(){
        System.out.println("Podaj adres zamieszkania:");
        System.out.println("ulica: ");
        String street = scannerString.nextLine();
        System.out.println("numer mieszkania: ");
        String flatNumber = scannerString.nextLine();
        System.out.println("numer domu: ");
        String houseNumber = scannerString.nextLine();
        System.out.println("kod pocztowy: ");
        String postcode = scannerString.nextLine();
        System.out.println("miasto: ");
        String city = scannerString.nextLine();

        return new Address(street, flatNumber, houseNumber, postcode, city);
    }

    public Person createTenant(String firstName, String lastName, String pesel, String dateOfBirth, Address address){
        return new Person(firstName, lastName, pesel, dateOfBirth, address);
    }

    public void createTenant(Flat flat){
        System.out.println("Podaj imie:");
        String tenantName = scannerString.nextLine();
        System.out.println("Podaj nazwisko:");
        String tenantLastName = scannerString.nextLine();
        System.out.println("Podaj PESEL:");
        String tenantPesel = scannerString.nextLine();
        System.out.println("Podaj date urodzenia:");
        String tenantDateOfBirth = scannerString.nextLine();
        Address tenantAdress = createTenantAddress();
        Person newTenant = createTenant(tenantName, tenantLastName, tenantPesel, tenantDateOfBirth, tenantAdress);
        if(flat.getTenants().size() < flat.getMaxNumberOfTenants()){
        flat.addTenant(newTenant,flat);
            System.out.println("Dodano lokatora " + newTenant.getFirstName() + " " + newTenant.getLastName() +
                    " do mieszkania " + flat.getId());
        }else{
            System.out.println("Brak wolnych miejsc");
        }
    }

    public Person findTenantByPesel(Flat flat, String pesel){
        return flat.getTenants().stream()
                .filter(tenant -> tenant.getPesel().equals(pesel))
                .findFirst()
                .orElseThrow(() ->  new RuntimeException("Nie znaleziono lokatora o podanym nr pesel"));
    }

    public void removeTenant(Flat flat){
        System.out.println("Podaj nr PESEL lokatora: ");
        String tenantPesel = scannerString.nextLine();
        Person foundTenant = findTenantByPesel(flat, tenantPesel);
        flat.removeTenant(foundTenant, flat);
        System.out.println("Usunieto lokatora");
    }


    public Items createItem(String itemName, AreaSpace itemDimensions) {
        return new Items(itemName, itemDimensions);
    }

    public Items createAmphibiousVehicle(String name, AreaSpace dimensions, String color) {
        return new AmphibiousVehicle(name, dimensions, color);
    }

    public Items createBoatVehicle(String name, AreaSpace dimensions, boolean haveSail, boolean isRegistered) {
        return new BoatVehicle(name,dimensions, haveSail, isRegistered);
    }

    public Items createCityVehicle(String name, AreaSpace dimensions, String engineType, int numberOfDoors, boolean haveLPG, double engineCapacity, double totalMass){
        return new CityVehicle(name, dimensions, engineType, numberOfDoors, haveLPG, engineCapacity, totalMass);
    }

    public Items createMotorcycleVehicle(String name, AreaSpace dimensions, String type, double loudness){
        return new MotorcycleVehicle(name, dimensions, type, loudness);
    }

    public Items createOfroadVehicle(String name, AreaSpace dimensions, String engineType, int numberOfDoors, boolean haveLPG,
                                     double engineCapacity, double totalMass, boolean haveAllWheelDrive, boolean haveOffRoadTires){
        return new OffroadVehicle(name, dimensions, engineType, numberOfDoors, haveLPG, engineCapacity, totalMass, haveAllWheelDrive, haveOffRoadTires);
    }

    public void addItemToSpace(Space space, Items newItem) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        space.addItem(newItem);
        System.out.println("Dodano " + newItem.getName() + " do pomieszczenia " + space.getId());
    }

    public void createOtherItem(Space space) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        System.out.println("Podaj nazwe:");
        String itemName = scannerItem.nextLine();
        AreaSpace itemDimensions = createDimensionsFromUserInput();
        Items newItem = createItem(itemName, itemDimensions);
        addItemToSpace(space, newItem);
    }

    public void createAmphibiousVehicle(Space space) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        System.out.println("Podaj nazwe:");
        String itemName = scannerItem.nextLine();
        System.out.println("Podaj kolor:");
        String itemColor = scannerItem.nextLine();
        AreaSpace itemDimensions = createDimensionsFromUserInput();
        Items newItem = createAmphibiousVehicle(itemName, itemDimensions, itemColor);
        addItemToSpace(space, newItem);
    }

    public void createBoatVehicle(Space space) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        System.out.println("Podaj nazwe:");
        String itemName = scannerItem.nextLine();
        AreaSpace itemDimensions = createDimensionsFromUserInput();

    }


}



