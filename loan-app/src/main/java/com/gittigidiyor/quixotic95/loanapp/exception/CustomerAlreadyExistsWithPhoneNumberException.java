package com.gittigidiyor.quixotic95.loanapp.exception;

public class CustomerAlreadyExistsWithPhoneNumberException extends RuntimeException {

    public CustomerAlreadyExistsWithPhoneNumberException(String message) {
        super(message);
    }

}