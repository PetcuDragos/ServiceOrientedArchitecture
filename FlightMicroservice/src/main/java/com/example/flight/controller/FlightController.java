package com.example.flight.controller;

import com.example.flight.client.HotelClient;
import com.example.flight.dtos.HotelDto;
import com.example.flight.dtos.RoadGoatAPIDataDto;
import com.example.flight.model.Flight;
import com.example.flight.repository.FlightRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") //this line
@RestController
public class FlightController {

    private Logger logger = LoggerFactory.getLogger(FlightController.class);
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private HotelClient hotelClient;

//    private ConnectionFactory factory;
//    private Connection connection;
//    private Channel channel;
//
//    String EXCHANGE_NAME = "notification";

    public FlightController() throws IOException, TimeoutException {
//        factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        connection = factory.newConnection();
//        channel = connection.createChannel();
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    }

    @GetMapping(path = "/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping(path = "/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightRepository.findAll());
    }

    @GetMapping(path = "/flight/{id}")
    @Transactional
    public ResponseEntity<Flight> bookFlight(@PathVariable Integer id) {
        logger.info("bookFlight entered");
        Optional<Flight> possibleFlightById = flightRepository.findById(id);
        if (possibleFlightById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Flight flight = possibleFlightById.get();
        flight.setPlacesLeft(flight.getPlacesLeft() - 1);

//        logger.info("flight changed");
//        TextMessageDto textMessageDto = new TextMessageDto();
//        textMessageDto.setMessage("The flight" + flight.getOrigin() + " - " + flight.getDestination() + " has been booked!");
//        template.convertAndSend("/topic/message", textMessageDto);
//        String message = "The flight " + flight.getOrigin() + " - " + flight.getDestination() + " has been booked!";
//        try {
//            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
//            System.out.println(" [x] Sent '" + "':'" + message + "'");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return ResponseEntity.ok(flight);
    }

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
}
