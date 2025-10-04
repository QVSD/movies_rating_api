package com.example.movies_rating_api.repository;

import com.example.movies_rating_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {}
