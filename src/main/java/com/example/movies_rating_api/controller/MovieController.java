package com.example.movies_rating_api.controller;

import com.example.movies_rating_api.dto.MovieDTO;
import com.example.movies_rating_api.model.Movie;
import com.example.movies_rating_api.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) { this.movieService = movieService; }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() { return ResponseEntity.ok(movieService.getAllMovies()); }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) { return ResponseEntity.ok(movieService.getMovieById(id)); }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody Movie movie) {
        Movie movieCreated = movieService.createMovie(movie);
        double avg = movieService.computeRatingAverage(movie);
        MovieDTO dto = new MovieDTO(movieCreated.getId(), movieCreated.getTitle(), movieCreated.getDescription(), avg);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){ return ResponseEntity.ok(movieService.updateMovie(id, movie)); }

    @GetMapping("/{id}/average")
    public ResponseEntity<Double> computeRatingAverage(@PathVariable Long id){
        Movie movie = movieService.getMovieById(id); // we do this because we cannot send a RequestBody variable in the GET request, so we have to first get the movie by its id
        return ResponseEntity.ok(movieService.computeRatingAverage(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
