package tpp.example.chatserver.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tpp.example.chatserver.exception.InvalidMessageException;
import tpp.example.chatserver.exception.InvalidMessageTypeException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(InvalidMessageException.class)
    public void handleInvalidMessageException(InvalidMessageException e) {
        log.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidMessageTypeException.class)
    public void handleInvalidMessageTypeException(InvalidMessageTypeException e) {
        log.error(e.getMessage());
    }
}
