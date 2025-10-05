package com.example.movies_rating_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Rating {

    @Id @GeneratedValue
    private Long Id;

    @Min(1) @Max(5)
    private int value;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Rating(){}

    public Long getId(){ return Id;}
    public void setId(Long Id) { this.Id = Id;}

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public String getComment(){ return comment;}
    public void setComment(String comment) { this.comment = comment;}

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user;}
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
}
