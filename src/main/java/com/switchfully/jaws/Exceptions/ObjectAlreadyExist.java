package com.switchfully.jaws.Exceptions;

public class ObjectAlreadyExist extends RuntimeException{
    public ObjectAlreadyExist(String message) {
        super(message + " is already used.");
    }
}
