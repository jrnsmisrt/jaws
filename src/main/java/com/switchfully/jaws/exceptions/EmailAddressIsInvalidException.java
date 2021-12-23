package com.switchfully.jaws.exceptions;

public class EmailAddressIsInvalidException extends RuntimeException {
    public EmailAddressIsInvalidException(String emailAddress) {
        super("The email address that you entered is not valid : " + emailAddress);
    }
}
