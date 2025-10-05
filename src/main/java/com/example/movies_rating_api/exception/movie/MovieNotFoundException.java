package com.example.movies_rating_api.exception.movie;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message) { super(message); }
}
