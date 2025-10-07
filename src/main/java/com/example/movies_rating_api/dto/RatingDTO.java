package com.example.movies_rating_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingDTO {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer value;

    private String comment;

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    public Integer getValue() { return value; }
    public String getComment() { return comment; }
    public Long getUserId() { return userId; }
    public Long getMovieId() { return movieId; }
}
