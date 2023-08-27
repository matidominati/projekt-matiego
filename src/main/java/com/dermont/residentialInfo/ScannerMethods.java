package com.dermont.residentialInfo;

import com.dermont.personInfo.Person;

import java.util.Scanner;

public class ScannerMethods {

    public void printTopMenu() {
        System.out.println("******************* MENU *******************");
        System.out.println("");
    }

    public void printExitMenu() {
        System.out.println("0 - Wyjscie z programu");
    }


    public void reverseOrExit() {
        System.out.println("");
        System.out.println("9 - Powrot");
        printExitMenu();
    }

    public void exitProgram() {
        System.out.println("Zamykanie programu...");
        System.exit(0);

    }

    public void printAvaiablePerson(Residential residential) {
        System.out.println("Dostepne osoby: ");
        System.out.println("");
        residential.getTenants().forEach(person -> {
            System.out.println("ID " + person.getId() + "  " + person.getFirstName() + " " + person.getLastName());
        });
    }

    public Person printMenuListOfUsers(Residential residential) throws ProblematicTenantException {

        printTopMenu();
        printAvaiablePerson(residential);
        printExitMenu();
        Scanner scanner = new Scanner(System.in);
        Person selectedPerson = null;
        System.out.println("Wybierz osobe (podaj ID) lub zamknij program: ");
        int selectedPersonID = scanner.nextInt();

        if (selectedPersonID == 0) {
            exitProgram();
            return null;
        } else {
            selectedPerson = residential.findPersonByID(selectedPersonID);
            if (selectedPerson == null) {
                System.out.println("Nie znaleziono osoby o podanym ID!");
            }
            return selectedPerson;
        }
    }

    public void handleUserMenu(Person selectedPerson, Residential residential) throws ProblematicTenantException {
        printTopMenu();
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        if (selectedPerson.checkHowManyDebbtHasOn(residential) > 3) {
            System.out.println("UWAGA! WYBRANA OSOBA POSIADA ZALEGLE NAJMY!");
        }
        System.out.println("Wybrana osoba: " + selectedPerson.getFirstName() + " " + selectedPerson.getLastName());
        System.out.println("Wybierz co chcesz zrobic:");
        System.out.println("1 - Wyswietl informacje");
        System.out.println("2 - wyswietl wolne pomieszczenia");
        System.out.println("3 - Wynajmij nowe pomieszczenie");
        reverseOrExit();
        selectedOption = scanner.nextInt();

        switch (selectedOption) {
            case 0:
                exitProgram();
                break;
            case 1:
                System.out.println("INFORMACJE:");
                personInfo(selectedPerson, residential);
                break;
            case 2:
                System.out.println("WOLNE POMIESZCZENIA");
                displayRoomsForRent(residential);
                break;
            case 3:
                System.out.println("WYNAJMIJ POMIESZCZENIA");
                selectedPerson.checkIfPersonIsResponsibleForRent(residential);
                rentSpaceByOn(selectedPerson, residential);
                break;
            case 9:
                Person selectedAnotherPerson = printMenuListOfUsers(residential);
                handleUserMenu(selectedAnotherPerson, residential);
                break;
            default:
                System.out.println("Nieprawidlowy wybor. Sprobuj ponownie");
        }


    }

    public void rentSpaceByOn(Person selectedPerson, Residential residential) throws ProblematicTenantException {
        if (residential.getAvailableSpace().size() > 0) {
            selectedPerson.checkIfPersonIsResponsibleForRent(residential);
            System.out.println("WYBIERZ POMIESZCZENIE, KTORE CHCESZ WYNAJAC (PODAJ ID):");
            displayRoomsForRent(residential);
            checkTypeOfSpaceForRent(selectedPerson, residential);

        }
    }

    public void checkTypeOfSpaceForRent(Person selectedPerson, Residential residential) {
        Space selectedSpace = selectSpaceById(residential);
        if (selectedSpace instanceof Flat) {
            ((Flat) selectedSpace).addTenant(selectedPerson, (Flat) selectedSpace);
            System.out.println("Poprawnie wynajeto mieszkanie");
        } else if (selectedSpace instanceof ParkingSpace) {
            ((ParkingSpace) selectedSpace).rentParkingSpace(selectedPerson, (ParkingSpace) selectedSpace);
            System.out.println("Poprawnie wynajeto miejsce parkingowe");
        } else {
            System.out.println("Błędne ID pomieszczenia");
        }
    }


    public Space selectSpaceById(Residential residential) {
        Scanner scanner = new Scanner(System.in);
        String selectedSpaceId = scanner.nextLine().toUpperCase();
        return residential.getAvailableSpace().stream()
                .filter(space -> selectedSpaceId.equals(space.getId()))
                .findFirst()
                .orElse(null);
    }



    public void checkSpaceToShowContents(Person selectedPerson, Residential residential) {
        Space selectedSpace = selectSpaceById(residential);
        if(selectedPerson.getRentedSpaces().contains(selectedSpace)){
            selectedSpace.displaySpaceContents(selectedSpace);
        } else {
            System.out.println("Pomieszczenie o wybranym ID nie nalezy do tego najemcy");
        }
    }


    public void personInfo(Person selectedPerson, Residential residential) {
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        System.out.println("Wybierz co chcesz zrobic:");
        System.out.println("1 - Wyświetl dane personalne");
        System.out.println("2 - Wyswietl wynajmowane pomieszczenia");
        System.out.println("3 - Sprawdz ilosc przeterminowanych najmow");
        System.out.println("");
        reverseOrExit();
        selectedOption = scanner.nextInt();
        switch (selectedOption) {
            case 0:
                exitProgram();
                break;
            case 1:
                System.out.println("DANE PERSONALNE");
                selectedPerson.displayInfo();
                break;
            case 2:
                System.out.println("WYNAJMOWANE POMIESZCZENIA");
                selectedPerson.checkRentedSpaces();
                System.out.println("Ilosc wynajmowanych pomieszczen:");
                System.out.println(selectedPerson.checkHowManyRoomsRentOn(residential));
                System.out.println("");
                if(selectedPerson.getRentedSpaces().size() != 0){
                System.out.println("Aby wyswietlic zawartosc pomieszczenia wpisz jego ID: ");
                checkSpaceToShowContents(selectedPerson, residential);
                }
                break;
            case 3:
                System.out.println("ILOSC PRZETERMINOWANYCH NAJMOW:");
                System.out.println(selectedPerson.checkHowManyDebbtHasOn(residential));
                break;
            default:
                System.out.println("Nieprawidlowy wybor. Sprobuj ponownie");

        }
    }


    public void displayRoomsForRent(Residential residential) {
        residential.displayAllAvailableSpaces();
    }


}
