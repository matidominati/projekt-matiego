package com.dermont.exceptions;

public class ItemTooLongException extends Exception {
    public ItemTooLongException() {
        super("Przedmiot nie zmiesci sie w pomieszczeniu. Jest za dlugi");
    }
}
