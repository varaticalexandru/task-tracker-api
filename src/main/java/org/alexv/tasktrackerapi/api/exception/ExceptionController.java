package org.alexv.tasktrackerapi.api.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleBadRequest(Exception ex) {
        log.error("Exception during the execution of application", ex);

        return new ResponseEntity<>(new ErrorDto(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFound(Exception ex) {
        log.error("Exception during the execution of application", ex);

        return new ResponseEntity<>(new ErrorDto(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

}
