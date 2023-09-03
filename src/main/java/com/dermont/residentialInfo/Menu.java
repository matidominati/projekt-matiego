package com.dermont.residentialInfo;

public class Menu {



    public void printTopMenu() {
        System.out.println("******************* MENU *******************");
        System.out.println("");
    }
    public void printExitMenu() {
        System.out.println("0 - Wyjscie z programu");
    }
    public void printAddToFlatMenu() {
        System.out.println("Wybierz co chcesz zrobic:");
        System.out.println("1 - dodaj lokatora do wybranego mieszkania");
        System.out.println("2 - usun lokatora z wybranego mieszkania");
        System.out.println("9 - powrot");
        System.out.println("0 - zamknij program");
    }
    public void printAddToParkingSpaceMenu() {
        System.out.println("Wybierz co chcesz zrobic:");
        System.out.println("1 - dodaj przedmiot/pojazd do wybranego pomieszczenia");
        System.out.println("2 - usun przedmiot/pojazd z wybranego pomieszczenia");
        System.out.println("9 - powrot");
        System.out.println("0 - zamknij program");

    }
    public void printPersonOptionMenu(){
        System.out.println("Wybierz co chcesz zrobic:");
        System.out.println("1 - Wyswietl informacje");
        System.out.println("2 - wyswietl wolne pomieszczenia");
        System.out.println("3 - Wynajmij nowe pomieszczenie");
    }
    public void printPersonInfoMenu(){
        System.out.println("Wybierz co chcesz zrobic:");
        System.out.println("1 - Wyświetl dane personalne");
        System.out.println("2 - Wyswietl wynajmowane pomieszczenia");
        System.out.println("3 - Sprawdz ilosc przeterminowanych najmow");
        System.out.println("");
    }

    public void printListOfItemsToAddMenu(){
        System.out.println("Wybierz co chcesz dodac:");
        System.out.println("1 - amfibia");
        System.out.println("2 - lodz --> niedokonczone");
        System.out.println("3 - samochod miejski --> niedokonczone");
        System.out.println("4 - samochod terenowy ->> niedokonczone");
        System.out.println("5 - motocykl ->> niedokonczone");
        System.out.println("6 - inny przedmiot");
    }


    public void reverseOrExit() {
        System.out.println("");
        System.out.println("9 - Powrot");
        printExitMenu();
    }


    public void exitProgram() {
        System.out.println("Zapisano stan osiedla. Zamykanie programu...");
        System.exit(0);

    }
}
