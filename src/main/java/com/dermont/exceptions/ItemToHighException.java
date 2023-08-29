package com.dermont.exceptions;

public class ItemToHighException extends Exception{
    public ItemToHighException (){
        super("Przedmiot nie zmiesci sie w pomieszczeniu. Jest za wysoki.");
    }

}
