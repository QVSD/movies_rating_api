package com.example.movies_rating_api.dto;

public class UserDTO {
    private final Long id;
    private final String username;
    private final String email;
    private final String role;

    public UserDTO(Long id, String username, String email, String role){
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getId(){ return id; }
    public String getUsername(){ return username; }
    public String getEmail(){ return email; }
    public String getRole(){ return role; }
}
