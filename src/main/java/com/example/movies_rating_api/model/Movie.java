package com.example.movies_rating_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.lang.management.MonitorInfo;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long Id;

    private String title;
    private String description;
    private int releaseYear;
    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;


    public Movie(){}
    public Long getId(){ return Id;}
    public void setId(Long Id) { this.Id = Id;}

    public String getTitle(){ return title;}
    public void setTitle(String title) { this.title = title;}

    public String getDescription() { return description;}
    public void setDescription(String description){ this.description = description; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public List<Rating> getRatings() { return ratings; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }


}
