package com.example.movies_rating_api.service;

import com.example.movies_rating_api.exception.UserNotFoundException;
import com.example.movies_rating_api.exception.movie.MovieNotFoundException;
import com.example.movies_rating_api.exception.rating.DuplicateRatingException;
import com.example.movies_rating_api.exception.rating.RatingNotFoundException;
import com.example.movies_rating_api.model.Movie;
import com.example.movies_rating_api.model.Rating;
import com.example.movies_rating_api.model.User;
import com.example.movies_rating_api.repository.MovieRepository;
import com.example.movies_rating_api.repository.RatingRepository;
import com.example.movies_rating_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public RatingService(RatingRepository ratingRepository,
                         UserRepository userRepository,
                         MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public List<Rating> getAllRatings() { return ratingRepository.findAll(); }

    public Rating getRatingById(Long id){
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException("Rating not found: id  = " + id));
    }

    public Rating addRating(Long userId, Long movieId, Rating ratingData){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found: " + movieId));

        boolean alreadyRated = ratingRepository.existsByUserIdAndMovieId(userId, movieId);
        if (alreadyRated) {
            throw new DuplicateRatingException("User already rated this movie");
        }

        ratingData.setUser(user);
        ratingData.setMovie(movie);

        return ratingRepository.save(ratingData);
    }

    public Rating updateRating(Long id, Rating ratingBody) {
        Rating ratingToUpdate = getRatingById(id);
        ratingToUpdate.setValue(ratingBody.getValue());
        ratingToUpdate.setComment(ratingBody.getComment());
        return ratingRepository.save(ratingToUpdate);
    }

    public void deleteRating(Long id){
        ratingRepository.deleteById(id);
    }

}
