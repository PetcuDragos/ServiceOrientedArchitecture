package com.example.flight.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class HotelDto implements Serializable {
    private Integer id;

    private String name;

    private String city;

    private Float pricePerNight;

    private Integer placesLeft;
}
