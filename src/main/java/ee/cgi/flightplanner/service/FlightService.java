package ee.cgi.flightplanner.service;

import ee.cgi.flightplanner.entity.Flight;
import ee.cgi.flightplanner.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getFilteredFlights(String destination, String departureDate, double price) {
        if (destination != null && departureDate != null && price > 0) {
            return flightRepository.findByDestinationIgnoreCaseAndDepartureDateAndPriceLessThan(destination, departureDate, price);
        } else if (destination != null && departureDate != null) {
            return flightRepository.findByDestinationIgnoreCaseAndDepartureDate(destination, departureDate);
        }
        return flightRepository.findAll();
    }
}
