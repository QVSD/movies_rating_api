package com.example.movies_rating_api.exception.user.email;

public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException(String message){ super(message); }
}
