package ee.cgi.flightplanner.service;

import ee.cgi.flightplanner.entity.Seat;
import ee.cgi.flightplanner.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    // Istekohtade leidmine lennu järgi, võttes arvesse, kas need on vabad või mitte
    public List<Seat> getAvailableSeats(Long flightId) {
        return seatRepository.findByFlightIdAndIsOccupied(flightId, false); // Vabad kohad
    }

    // Istekoha broneerimine
    public void bookSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));
        seat.setOccupied(true);
        seatRepository.save(seat);
    }
}