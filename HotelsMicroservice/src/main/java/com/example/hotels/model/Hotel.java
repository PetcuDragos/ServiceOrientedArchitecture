package com.example.hotels.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "price_per_night")
    private Float pricePerNight;

    @Column(name = "places_left")
    private Integer placesLeft;
}
