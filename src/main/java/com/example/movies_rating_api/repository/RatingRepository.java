package com.example.movies_rating_api.repository;

import com.example.movies_rating_api.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {}
