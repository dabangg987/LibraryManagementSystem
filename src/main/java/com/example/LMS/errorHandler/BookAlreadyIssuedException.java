package com.example.LMS.errorHandler;


public class BookAlreadyIssuedException extends RuntimeException {

    public BookAlreadyIssuedException(String message) {
        super(message);
    }
}
