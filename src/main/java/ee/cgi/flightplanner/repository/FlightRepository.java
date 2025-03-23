package ee.cgi.flightplanner.repository;

import ee.cgi.flightplanner.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDestinationIgnoreCaseAndDepartureDateAndPriceLessThan(
            String destination, LocalDate departureDate, double price);

    List<Flight> findByDestinationIgnoreCaseAndDepartureDate(
            String destination, LocalDate departureDate);

    List<Flight> findByDestinationIgnoreCase(String destination);

    List<Flight> findByDestinationIgnoreCaseAndDepartureDateAndFlightTime(
            String destination, LocalDate departureDate, LocalTime flightTime);

    List<Flight> findByDestinationIgnoreCaseAndDepartureDateAndFlightTimeAndPriceLessThan(
            String destination, LocalDate departureDate, LocalTime flightTime, Double price);
}