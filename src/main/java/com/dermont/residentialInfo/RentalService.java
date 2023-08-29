package com.dermont.residentialInfo;

import com.dermont.exceptions.*;
import com.dermont.personInfo.Person;
import com.dermont.storedItems.Items;

import java.util.Scanner;

public class RentalService {
    Menu menu = new Menu();

    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerString = new Scanner(System.in);

    public void displayAvaiablePerson(Residential residential) {
        System.out.println("Dostepne osoby: ");
        System.out.println("");
        residential.getTenants().forEach(person -> {
            System.out.println("ID " + person.getId() + "  " + person.getFirstName() + " " + person.getLastName());
        });
    }

    public Person displayListOfUsers(Residential residential, Scanner scannerInt) {
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

    public void chooseWhatToDisplayElse(Person selectedPerson, Residential residential) throws ProblematicTenantException, ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        int reverseSelectionOption;
        reverseSelectionOption = scannerInt.nextInt();
        if (reverseSelectionOption == 0) {
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

    public void chooseWhatToDisplayMenuAfterPersonID(Person selectedPerson, Residential residential, Scanner scannerInt) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException, ProblematicTenantException {
        int selectedOption;
        selectedOption = scannerInt.nextInt();
        switch (selectedOption) {
            case 0:
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
                selectedPerson.checkIfPersonIsResponsibleForRent(selectedPerson, residential);
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

    public void handleUserMenu(Person selectedPerson, Residential residential) throws ProblematicTenantException, ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        if (selectedPerson.checkHowManyDebbtHasOn(residential) > 3) {
            System.out.println("UWAGA! WYBRANA OSOBA POSIADA ZALEGLE NAJMY!");
        }
        System.out.println("Wybrana osoba: " + selectedPerson.getFirstName() + " " + selectedPerson.getLastName());
        menu.printPersonOptionMenu();
        menu.reverseOrExit();
        chooseWhatToDisplayMenuAfterPersonID(selectedPerson, residential, scannerInt);
    }
    public void rentSpaceByOn(Person selectedPerson, Residential residential) throws ProblematicTenantException {
        if (residential.getAvailableSpace().size() > 0) {
            selectedPerson.checkIfPersonIsResponsibleForRent(selectedPerson, residential);
            System.out.println("WYBIERZ POMIESZCZENIE, KTORE CHCESZ WYNAJAC (PODAJ ID):");
            displayRoomsForRent(residential);
            checkTypeOfSpaceForRent(selectedPerson, residential);
        }
    }
    public void checkTypeOfSpaceForRent(Person selectedPerson, Residential residential) {
        Space selectedSpace = selectSpaceByIdIfAvailable(residential, scannerString);
        if (selectedSpace instanceof Flat) {
            selectedSpace.addTenant(selectedPerson,selectedSpace);
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
                .orElse(null);
    }
    public Space selectSpaceByIdIfExist(Residential residential, Scanner scannerString) {
        String selectedSpaceId = scannerString.nextLine().toUpperCase();
        return residential.getBlocks().stream()
                .flatMap(block -> block.getSpaces().stream())
                .filter(space -> selectedSpaceId.equals(space.getId()))
                .findFirst()
                .orElse(null);
    }
    public void checkIfPersonRentSpaceToShowContents(Person selectedPerson, Residential residential) {
        Space selectedSpace = selectSpaceByIdIfExist(residential, scannerString);
        if (selectedPerson.getRentedSpaces().contains(selectedSpace)) {
            selectedSpace.displaySpaceContents();
        } else {
            System.out.println(selectedPerson.getFirstName() + " " + selectedPerson.getLastName() +
                    " nie wynajmuje pomieszczenia o podanym ID!");
        }
    }
    public void checkTypeOfSpaceToAddSomething(Residential residential) {
        Space selectedSpace = selectSpaceByIdIfExist(residential, scannerInt);
        if (selectedSpace instanceof Flat) {
            menu.printAddToFlatMenu();
        } else if (selectedSpace instanceof ParkingSpace) {
            menu.printAddToParkingSpaceMenu();
        } else {
            System.out.println("Błędne ID pomieszczenia");
        }

    }
    public void personInfo(Person selectedPerson, Residential residential, Scanner scannerInt) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException, ProblematicTenantException {
        menu.printPersonInfoMenu();
        menu.reverseOrExit();
        int selectedOption;
        selectedOption = scannerInt.nextInt();
        switch (selectedOption) {
            case 0:
                menu.exitProgram();
                break;
            case 1:
                System.out.println("DANE PERSONALNE");
                selectedPerson.displayInfo();
                break;
            case 2:
                displayRentedSpacesAndContents(selectedPerson, residential);
                checkTypeOfSpaceToAddSomething(residential);
                int innerOption = scannerInt.nextInt();
                switch (innerOption) {
                    case 0:
                        menu.exitProgram();
                        break;
                    case 1:
                        menu.printListOfItemsToAddMenu();
                        int itemOption = scannerInt.nextInt();
                        switch (itemOption) {
                            case 0:
                                menu.exitProgram();
                                break;
                            case 6:
//                                createItem;
                                break;
                        }
                        break;
                    case 9:
                        displayRentedSpacesAndContents(selectedPerson, residential);
                        break;

                }
                break;
            case 3:
                System.out.println("ILOSC PRZETERMINOWANYCH NAJMOW:");
                System.out.println(selectedPerson.checkHowManyDebbtHasOn(residential));
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
        selectedPerson.checkRentedSpaces();
        System.out.println("Ilosc wynajmowanych pomieszczen: " + selectedPerson.checkHowManyRoomsRentOn(residential));
        System.out.println("");
        if (selectedPerson.getRentedSpaces().size() != 0) {
            System.out.println("Aby wyswietlic zawartosc pomieszczenia wpisz jego ID: ");
            checkIfPersonRentSpaceToShowContents(selectedPerson, residential);

        }
    }


    public void createItem(Space selectedSpace) throws ItemToWideException, TooManyThingsException, ItemToHighException, ItemTooLongException {
        Scanner scannerItem = new Scanner(System.in);
        System.out.println("Podaj nazwe przedmiotu:");
        String itemName = scannerItem.nextLine();
        System.out.println("Podaj dlugosc przedmiotu:");
        double lengthItem = scannerItem.nextDouble();
        System.out.println("Podaj szerokosc przedmiotu:");
        double widthItem = scannerItem.nextDouble();
        System.out.println("Podaj wysokosc przedmiotu:");
        double heightItem = scannerItem.nextDouble();

        AreaSpace itemDimensions = new AreaSpace(lengthItem, widthItem, heightItem);
        Items newItem = new Items(itemName, itemDimensions);
        ((ParkingSpace) selectedSpace).addItem(newItem);

    }


}
