package com.sash.volleyballApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Facility name

    @Column(nullable = false)
    private String location; // City, State, or detailed address

    @Column(nullable = false)
    private String surfaceType; // Sand, Grass, Indoor

    @Column(nullable = false)
    private Integer courts; // Number of courts

    @Column(nullable = true)
    private String organizationAffiliation; // Organization Affiliation(s)

    @Column(nullable = true)
    private String websiteUrl; // Website URL

    @Column(nullable = true)
    private String rentalRequirement; // Rental (required/optional/none)
}
