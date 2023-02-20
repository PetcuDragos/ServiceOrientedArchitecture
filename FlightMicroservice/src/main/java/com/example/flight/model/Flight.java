package com.example.flight.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @Column(name = "price")
    private Float price;

    @Column(name = "places_left")
    private Integer placesLeft;
}
