package com.switchfully.jaws.api;

import com.switchfully.jaws.exceptions.EmailAddressIsInvalidException;
import com.switchfully.jaws.exceptions.ObjectAlreadyExist;
import com.switchfully.jaws.exceptions.ParentDivisionNotFoundException;
import com.switchfully.jaws.exceptions.InvalidMemberShipLevelInputException;
import org.hibernate.PropertyValueException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({InvalidDataAccessApiUsageException.class, PropertyValueException.class,
            IllegalArgumentException.class, NullPointerException.class,
            ParentDivisionNotFoundException.class, EmailAddressIsInvalidException.class,
            ObjectAlreadyExist.class})
    protected void entityDoesNotExistInDb(Exception exception,
                                          HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }
    @ExceptionHandler(InvalidMemberShipLevelInputException.class)
    protected void invalidMemberShipLevelInputException(InvalidMemberShipLevelInputException exception,
                                      HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }
}
