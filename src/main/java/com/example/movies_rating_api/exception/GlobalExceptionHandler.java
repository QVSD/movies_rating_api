package com.example.movies_rating_api.exception;

import com.example.movies_rating_api.exception.user.email.EmailAlreadyInUseException;
import com.example.movies_rating_api.exception.user.email.EmailNotFoundException;
import jdk.jfr.Experimental;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(status).body(errorResponse);
    }


    @ExceptionHandler({
            UserNotFoundException.class,
            EmailNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex){
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(EmailAlreadyInUseException ex){
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
