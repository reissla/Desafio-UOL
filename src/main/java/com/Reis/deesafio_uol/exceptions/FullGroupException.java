package com.Reis.deesafio_uol.exceptions;

public class FullGroupException extends RuntimeException{

    public FullGroupException(){
        super("O grupo está cheio!");
    }

    public FullGroupException(String message){
        super(message);
    }
}
