package com.example.movies_rating_api.exception.rating;

public class DuplicateRatingException extends RuntimeException{
    public DuplicateRatingException(String message) { super(message); }
}
