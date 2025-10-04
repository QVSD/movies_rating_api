package com.example.movies_rating_api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue
    private Long Id;

    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;


    public User(){}
    public Long getId(){ return Id;}
    public void setId(Long Id) { this.Id = Id;}

    public String getUsername(){ return username;}
    public void setUsername(String username) { this.username = username;}

    public String getPassword() { return password;}
    public void setPassword(String password){ this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role){ this.role = role; }

    public List<Rating> getRatings() { return ratings; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

}
