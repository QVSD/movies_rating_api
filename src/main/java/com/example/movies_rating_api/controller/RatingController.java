package com.example.movies_rating_api.controller;

import com.example.movies_rating_api.dto.RatingDTO;
import com.example.movies_rating_api.model.Movie;
import com.example.movies_rating_api.model.Rating;
import com.example.movies_rating_api.model.User;
import com.example.movies_rating_api.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) { this.ratingService = ratingService; }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() { return ResponseEntity.ok(ratingService.getAllRatings()); }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) { return ResponseEntity.ok(ratingService.getRatingById(id)); }

    @PostMapping
    public ResponseEntity<Rating> addRating(@Valid @RequestBody RatingDTO ratingDTO) {
        Rating rating = ratingService.addRating(ratingDTO.getUserId(), ratingDTO.getMovieId(), dtoToEntity(ratingDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    private Rating dtoToEntity(RatingDTO ratingDTO){
        Rating r = new Rating();
        r.setValue(ratingDTO.getValue());
        r.setComment(ratingDTO.getComment());
        return r;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating ratingBody){
        return ResponseEntity.ok(ratingService.updateRating(id, ratingBody));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id){
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

}
