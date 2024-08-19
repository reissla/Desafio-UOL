package com.Reis.deesafio_uol.exceptions;

public class EmptyGroupException extends RuntimeException{

    public EmptyGroupException(){
        super("Grupo vazio!");
    }

    public EmptyGroupException(String message){
        super(message);
    }
}
