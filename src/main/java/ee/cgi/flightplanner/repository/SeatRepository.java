package ee.cgi.flightplanner.repository;

import ee.cgi.flightplanner.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByFlightIdAndIsOccupied(Long flightId, boolean isOccupied);
}