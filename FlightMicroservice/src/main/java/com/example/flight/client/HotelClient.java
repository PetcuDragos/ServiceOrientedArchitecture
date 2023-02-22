package com.example.flight.client;

import com.example.flight.dtos.HotelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "http://hotels:8081", name = "hotelClient")
//@FeignClient(url = "http://localhost:8081", name = "hotelClient")
public interface HotelClient {

    @GetMapping(path = "/hotels")
    List<HotelDto> getHotelsByLocation(@RequestParam String location);
}
