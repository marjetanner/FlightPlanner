package ee.cgi.flightplanner.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeatService {

    public List<String> recommendSeats(Long flightId, int passengerCount, Boolean windowSeat, Boolean extraLegroom, Boolean nearExit) {
        // Loome kõik võimalikud istekohad
        List<String> allSeats = generateSeats();

        // Genereerime juhuslikult hõivatud istekohad
        Set<String> occupiedSeats = generateOccupiedSeats();

        // Leiame vabad istekohad
        List<String> availableSeats = allSeats.stream()
                .filter(seat -> !occupiedSeats.contains(seat))
                .collect(Collectors.toList());

        // Filtreerime istekoha eelistuste järgi
        List<String> recommendedSeats = filterSeats(availableSeats, passengerCount, windowSeat, extraLegroom, nearExit);

        return recommendedSeats.isEmpty() ? List.of("No suitable seats found") : recommendedSeats;
    }

    private List<String> generateSeats() {
        List<String> seats = new ArrayList<>();
        for (int row = 1; row <= 10; row++) {
            seats.add(row + "A");
            seats.add(row + "B");
            seats.add(row + "C");
            seats.add(row + "D");
            seats.add(row + "E");
            seats.add(row + "F");
        }
        return seats;
    }

    private Set<String> generateOccupiedSeats() {
        Set<String> occupied = new HashSet<>();
        Random random = new Random();
        List<String> allSeats = generateSeats();
        int occupiedCount = random.nextInt(20) + 10; // 10-30 juhuslikku hõivatud kohta

        for (int i = 0; i < occupiedCount; i++) {
            occupied.add(allSeats.get(random.nextInt(allSeats.size())));
        }
        return occupied;
    }

    private List<String> filterSeats(List<String> availableSeats, int passengerCount, Boolean windowSeat, Boolean extraLegroom, Boolean nearExit) {
        List<String> filteredSeats = new ArrayList<>(availableSeats);

        if (windowSeat != null && windowSeat) {
            filteredSeats.removeIf(seat -> !(seat.endsWith("A") || seat.endsWith("F")));
        }

        if (extraLegroom != null && extraLegroom) {
            filteredSeats.removeIf(seat -> !seat.startsWith("1") && !seat.startsWith("10"));
        }

        if (nearExit != null && nearExit) {
            filteredSeats.removeIf(seat -> !seat.startsWith("1") && !seat.startsWith("10"));
        }

        return filteredSeats.stream().limit(passengerCount).collect(Collectors.toList());
    }
}