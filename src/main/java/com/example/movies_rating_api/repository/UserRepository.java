package com.example.movies_rating_api.repository;

import com.example.movies_rating_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /*By doing this you might find yourself able to call
     * userRepository.findByEmail("posearazvanel25@yahoo.com")
     * and spring is going to automatlically generate a querry equivlent of:
     * SELECT * FROM users WHERE email = 'posearazvanel25@yahoo.com';
     * */
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}