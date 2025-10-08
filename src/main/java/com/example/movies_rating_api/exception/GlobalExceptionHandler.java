package com.example.movies_rating_api.exception;

import com.example.movies_rating_api.exception.user.email.EmailAlreadyInUseException;
import com.example.movies_rating_api.exception.user.email.EmailNotFoundException;
import jdk.jfr.Experimental;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Invalid input");

        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(LocalDateTime.now().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
