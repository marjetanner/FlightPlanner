package ee.cgi.flightplanner.controller;

import ee.cgi.flightplanner.entity.Flight;
import ee.cgi.flightplanner.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Lennu filtreerimine
    @GetMapping
    public List<Flight> getFlights(@RequestParam(required = false) String destination,
                                   @RequestParam(required = false) String departureDate,
                                   @RequestParam(required = false) double price) {
        return flightService.getFilteredFlights(destination, departureDate, price);
    }
}
