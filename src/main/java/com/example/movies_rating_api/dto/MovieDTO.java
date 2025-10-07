package com.example.movies_rating_api.dto;

public class MovieDTO {
    private final Long id;
    private final String title;
    private final String description;
    private final double averageRating;

    public MovieDTO(Long id, String title, String description, double averageRating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.averageRating = averageRating;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getAverageRating() { return averageRating; }
}
