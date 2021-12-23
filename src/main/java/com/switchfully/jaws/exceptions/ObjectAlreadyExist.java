package com.switchfully.jaws.exceptions;

public class ObjectAlreadyExist extends RuntimeException{
    public ObjectAlreadyExist(String message) {
        super(message + " is already used.");
    }
}
