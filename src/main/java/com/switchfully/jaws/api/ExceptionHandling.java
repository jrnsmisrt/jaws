package com.switchfully.jaws.api;

import com.switchfully.jaws.exceptions.EmailAddressIsInvalidException;
import com.switchfully.jaws.exceptions.ObjectAlreadyExist;
import com.switchfully.jaws.exceptions.ParentDivisionNotFoundException;
import org.hibernate.PropertyValueException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({InvalidDataAccessApiUsageException.class,
            IllegalArgumentException.class,
            ParentDivisionNotFoundException.class, PropertyValueException.class, NullPointerException.class,
            EmailAddressIsInvalidException.class, ObjectAlreadyExist.class})
    protected void entityDoesNotExistInDb(Exception exception,
                                          HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }


}

