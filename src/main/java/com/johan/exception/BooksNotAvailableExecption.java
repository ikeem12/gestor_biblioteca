package com.johan.exception;

/**
 * Excepción personalizada que indica que no hay libros disponibles.
 */
public class BooksNotAvailableExecption extends Exception {
    public BooksNotAvailableExecption(String message){
        super(message);
    }
}
