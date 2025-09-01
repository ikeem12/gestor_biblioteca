package com.johan.exception;

/**
 * Excepción personalizada que indica que un libro específico no está disponible.
 */
public class BookNotAvailable extends Exception {
    public BookNotAvailable(String message){
        super(message);
    }
}
