package com.switchfully.jaws.api;

import com.switchfully.jaws.Exceptions.EmailAddressIsInvalidException;
import org.hibernate.PropertyValueException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    protected void entityDoesNotExistInDb(InvalidDataAccessApiUsageException exception,
                                          HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(PropertyValueException.class)
    protected void PropertyValueException(PropertyValueException exception,
                                          HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    protected void NullPointerException(NullPointerException exception,
                                        HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }


    @ExceptionHandler(EmailAddressIsInvalidException.class)
    protected void emailAddressInvalidException(EmailAddressIsInvalidException exception,
                                                HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }
}
