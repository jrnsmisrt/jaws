package com.switchfully.jaws.api;

import com.switchfully.jaws.exceptions.ParentDivisionNotFoundException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({ InvalidDataAccessApiUsageException.class,
                        IllegalArgumentException.class,
                        ParentDivisionNotFoundException.class})
    protected void catchExceptionThrown(Exception exception,
                                          HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

}
