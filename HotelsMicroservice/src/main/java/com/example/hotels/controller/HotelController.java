package com.example.hotels.controller;

import com.example.hotels.model.Hotel;
import com.example.hotels.repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping(path = "/hotels")
    public ResponseEntity<List<Hotel>> getHotelsByLocation(@RequestParam String location) {
        return ResponseEntity.ok(hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getCity().equals(location)).toList());
    }

    @PutMapping(path = "/hotel/{id}")
    @Transactional
    public ResponseEntity<Hotel> bookHotel(@PathVariable Integer id) {
        Optional<Hotel> possibleHotelById = hotelRepository.findById(id);
        if (possibleHotelById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Hotel hotel = possibleHotelById.get();
        hotel.setPlacesLeft(hotel.getPlacesLeft() - 1);
        return ResponseEntity.ok(hotel);
    }
}
