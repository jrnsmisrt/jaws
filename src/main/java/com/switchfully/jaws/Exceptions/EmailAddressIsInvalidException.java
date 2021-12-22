package com.switchfully.jaws.Exceptions;

public class EmailAddressIsInvalidException extends RuntimeException {
    public EmailAddressIsInvalidException(String emailAddress) {
        super("The email address that you entered is not valid : " + emailAddress);
    }
}
