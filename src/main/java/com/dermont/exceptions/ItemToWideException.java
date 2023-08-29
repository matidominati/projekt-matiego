package com.dermont.exceptions;

public class ItemToWideException extends Exception{
    public ItemToWideException(){
        super("Przedmiot nie zmiesci sie w pomieszczeniu. Jest za szeroki");
    }
}
