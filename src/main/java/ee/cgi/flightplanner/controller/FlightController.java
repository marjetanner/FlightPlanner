package ee.cgi.flightplanner.controller;

import ee.cgi.flightplanner.entity.Flight;
import ee.cgi.flightplanner.service.FlightService;
import ee.cgi.flightplanner.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;
    private final SeatService seatService;

    @Autowired
    public FlightController(FlightService flightService, SeatService seatService) {
        this.flightService = flightService;
        this.seatService = seatService;
    }

    // Kuvab kõik lennud
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    // Filtreerimine sihtkoha, kuupäeva, lennuaja ja hinna järgi
    @GetMapping("/search")
    public List<Flight> getFilteredFlights(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) LocalDate departureDate,
            @RequestParam(required = false) LocalTime flightTime,
            @RequestParam(required = false) Double price) {
        return flightService.getFilteredFlights(destination, departureDate, flightTime, price);
    }

    // Istekohtade soovitamine
    @GetMapping("/{flightId}/seats")
    public List<String> recommendSeats(
            @PathVariable Long flightId,
            @RequestParam int passengerCount,
            @RequestParam(required = false) Boolean windowSeat,
            @RequestParam(required = false) Boolean extraLegroom,
            @RequestParam(required = false) Boolean nearExit) {
        return seatService.recommendSeats(flightId, passengerCount, windowSeat, extraLegroom, nearExit);
    }
}