package com.example.movies_rating_api.controller;

import com.example.movies_rating_api.dto.UserDTO;
import com.example.movies_rating_api.dto.UserRegisterDTO;
import com.example.movies_rating_api.model.User;
import com.example.movies_rating_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins ="*") //TODO document what this does later rn is related to front-end
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() { return ResponseEntity.ok(userService.getAllUsers()); }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) { return ResponseEntity.ok(userService.getUserById(id)); }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterDTO userBody){
        User user = userService.registerUserFromDTO(userBody);
        UserDTO response = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
