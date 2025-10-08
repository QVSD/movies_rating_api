package com.example.movies_rating_api.service;

import com.example.movies_rating_api.dto.UserRegisterDTO;
import com.example.movies_rating_api.exception.user.email.EmailAlreadyInUseException;
import com.example.movies_rating_api.exception.user.email.EmailNotFoundException;
import com.example.movies_rating_api.exception.UserNotFoundException;
import com.example.movies_rating_api.model.Role;
import com.example.movies_rating_api.model.User;
import com.example.movies_rating_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    public List<User> getAllUsers(){ return userRepository.findAll(); }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("User not found with the email" + email));
    }

    public User registerUser(User newUser){
        if(!userRepository.existsByEmail(newUser.getEmail())) {
            newUser.setRole(Role.USER);
            return userRepository.save(newUser);
        }
        else{
            throw new EmailAlreadyInUseException("User email already in use!");
        }
    }

    public User registerUserFromDTO(UserRegisterDTO dto){
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());
        return registerUser(newUser);
    }

    public User updateUser(Long id, User userBody){
        User userToBeUpdated = getUserById(id);
        if (!userBody.getEmail().equals(userToBeUpdated.getEmail()) &&
                userRepository.existsByEmail(userBody.getEmail())) {
            throw new EmailAlreadyInUseException("Email already taken: " + userBody.getEmail());
        }
        else {
            userToBeUpdated.setEmail(userBody.getEmail());

        }
        userToBeUpdated.setPassword(userBody.getPassword());

        return userRepository.save(userToBeUpdated);
    }

    public void deleteUser(Long id){ userRepository.deleteById(id); }
}

