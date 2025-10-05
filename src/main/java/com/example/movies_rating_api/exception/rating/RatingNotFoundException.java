package com.example.movies_rating_api.exception.rating;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(String message){ super(message); }
}
