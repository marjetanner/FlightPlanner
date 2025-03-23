package ee.cgi.flightplanner.controller;

import ee.cgi.flightplanner.entity.Seat;
import ee.cgi.flightplanner.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // Vabad istekoha soovitused
    @GetMapping
    public List<Seat> getAvailableSeats(@RequestParam Long flightId) {
        return seatService.getAvailableSeats(flightId);
    }

    // Istekoha broneerimine
    @PostMapping("/book")
    public void bookSeat(@RequestParam Long seatId) {
        seatService.bookSeat(seatId);
    }
}
