package com.example.flight.controller;

import com.example.flight.client.HotelClient;
import com.example.flight.dtos.HotelDto;
import com.example.flight.dtos.RoadGoatAPIDataDto;
import com.example.flight.model.Flight;
import com.example.flight.repository.FlightRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") //this line
@RestController
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelClient hotelClient;

//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightRepository.findAll());
    }

//    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.PUT,RequestMethod.GET})
    @GetMapping(path = "/flight/{id}")
    @Transactional
    public ResponseEntity<Flight> bookFlight(@PathVariable Integer id) {
        Optional<Flight> possibleFlightById = flightRepository.findById(id);
        if (possibleFlightById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Flight flight = possibleFlightById.get();
        flight.setPlacesLeft(flight.getPlacesLeft() - 1);
        return ResponseEntity.ok(flight);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/flight/{id}/hotels")
    public ResponseEntity<List<HotelDto>> getHotelsForFlight(@PathVariable Integer id) {
        Optional<Flight> possibleFlightById = flightRepository.findById(id);
        if (possibleFlightById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Flight flight = possibleFlightById.get();
        List<HotelDto> hotelsByLocation = hotelClient.getHotelsByLocation(flight.getDestination());

        return ResponseEntity.ok(hotelsByLocation);
    }

    @GetMapping(path = "/flight/{id}/knownfor")
    public ResponseEntity<String> getKnownForForFlightDestination(@PathVariable Integer id) throws IOException, InterruptedException {
        Optional<Flight> possibleFlightById = flightRepository.findById(id);
        if (possibleFlightById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Flight flight = possibleFlightById.get();

        String username = "f551cd60a7e1b8f4dcab7089c6366161";
        String password = "bbd278334eefd29965103da083b6a9c9";
        String key = "Authorization";
        String value = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        String url = "https://api.roadgoat.com/api/v2/destinations/auto_complete?q=" + flight.getDestination();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(key, value)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().create();
        RoadGoatAPIDataDto roadGoatAPIDataDto = gson.fromJson(response.body(), RoadGoatAPIDataDto.class);
        return ResponseEntity.ok(roadGoatAPIDataDto.getIncluded().stream()
                .map(included -> included.getAttributes().getName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(",")));
    }

    @RequestMapping(value = "/**",method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
