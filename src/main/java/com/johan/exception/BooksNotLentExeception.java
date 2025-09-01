package com.johan.exception;

/**
 * Excepción personalizada que indica que no hay libros prestados.
 */
public class BooksNotLentExeception extends Exception{
    public BooksNotLentExeception(String message){
        super(message);
    }
}