package ee.cgi.flightplanner.repository;

import ee.cgi.flightplanner.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDestinationIgnoreCaseAndDepartureDateAndPriceLessThan(
            String destination, String departureDate, double price);

    List<Flight> findByDestinationIgnoreCaseAndDepartureDate(
            String destination, String departureDate);
}
