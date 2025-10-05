package com.example.movies_rating_api.service;

import com.example.movies_rating_api.exception.movie.MovieNotFoundException;
import com.example.movies_rating_api.model.Movie;
import com.example.movies_rating_api.model.Rating;
import com.example.movies_rating_api.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){ this.movieRepository = movieRepository; }

    public List<Movie> getAllMovies() { return movieRepository.findAll(); }

    public Movie getMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id " + id));
    }

    public Movie createMovie(Movie movieData){ return movieRepository.save(movieData); }

    public Movie updateMovie(Long id, Movie movieBody){
        Movie movieToBeUpdated = getMovieById(id);
        movieToBeUpdated.setTitle(movieBody.getTitle());
        movieToBeUpdated.setDescription(movieBody.getDescription());

        return movieRepository.save(movieToBeUpdated);
    }

    public double computeRatingAverage(Movie movie){
        return movie.getRatings().stream()
                .mapToInt(Rating::getValue)
                .average()
                .orElse(0.0);
    }

    public void deleteMovie(Long id){ movieRepository.deleteById(id); }
}
