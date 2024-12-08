package com.sash.volleyballApp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PlayerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false) // Owning side of the relationship
    private User user;

    private String bio;
    private Integer skillLevel; // e.g., Beginner, Intermediate, Pro
    private String motivation; // e.g., Recreational, Competitive
    private Double height;     // Optional, in meters or feet
    private String homeCity;
    private String gender;     // Add this field for gender (e.g., Male, Female, Other)
    private String profileImageUrl;
}
