package com.sash.volleyballApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location; // City or venue

    @Column(nullable = false)
    private String surfaceType; // Sand, Grass, Indoor

    @Column(nullable = false)
    private Integer teamSize; // 2s, 4s, 6s

    @Column(nullable = false)
    private Integer skillLevel; // 1-13 scale

    private String genderFormat; // Men, Women, Coed

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    private Long organizerId; // User ID of the organizer

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility; // Reference to the Facility entity
}
