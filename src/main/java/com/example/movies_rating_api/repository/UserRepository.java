package com.example.movies_rating_api.repository;

import com.example.movies_rating_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
