package com.Reis.deesafio_uol.exceptions;

public class CredentialsAlreadyUsedException extends RuntimeException{

        public CredentialsAlreadyUsedException() {
            super("Nome ou email ja foram usados!");
        }

        public CredentialsAlreadyUsedException(String message) {
            super(message);
        }

}
