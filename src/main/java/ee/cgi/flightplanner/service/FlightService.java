package ee.cgi.flightplanner.service;


import ee.cgi.flightplanner.entity.Flight;
import ee.cgi.flightplanner.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getFilteredFlights(String destination, LocalDate departureDate, LocalTime flightTime, Double price) {
        if (destination == null && departureDate == null && flightTime == null && price == null) {
            return flightRepository.findAll();
        }

        // Erinevad filtreerimisvõimalused
        if (destination != null && departureDate == null && price == null && flightTime == null) {
            return flightRepository.findByDestinationIgnoreCase(destination);
        }

        if (destination != null && departureDate != null && flightTime == null && price == null) {
            return flightRepository.findByDestinationIgnoreCaseAndDepartureDate(destination, departureDate);
        }

        if (destination != null && departureDate != null && flightTime != null && price == null) {
            return flightRepository.findByDestinationIgnoreCaseAndDepartureDateAndFlightTime(destination, departureDate, flightTime);
        }

        if (destination != null && departureDate != null && flightTime != null) {
            return flightRepository.findByDestinationIgnoreCaseAndDepartureDateAndFlightTimeAndPriceLessThan(destination, departureDate, flightTime, price);
        }

        return flightRepository.findAll();
    }
}