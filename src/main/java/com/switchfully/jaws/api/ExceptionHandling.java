package com.switchfully.jaws.api;

import com.switchfully.jaws.Exceptions.EmailAddressIsInvalidException;
import com.switchfully.jaws.Exceptions.ObjectAlreadyExist;
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
    protected void propertyValueException(PropertyValueException exception,
                                          HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    protected void nullPointerException(NullPointerException exception,
                                        HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }


    @ExceptionHandler(EmailAddressIsInvalidException.class)
    protected void emailAddressInvalidException(EmailAddressIsInvalidException exception,
                                                HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(ObjectAlreadyExist.class)
    protected void objectAlreadyExist(ObjectAlreadyExist exception,
                                      HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }
}
