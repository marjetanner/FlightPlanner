<template>
  <div id="app">
    <h1>Flight Planner</h1>

    <!-- Available flights -->
    <div v-if="flights.length > 0">
      <h2>Available Flights</h2>
      <ul>
        <li v-for="flight in flights" :key="flight.id">
          <div>
            <strong>Flight: </strong>{{ flight.name }}
            <button @click="selectFlight(flight.id)">Select Flight</button>
          </div>
        </li>
      </ul>
    </div>

    <!-- Seat selection -->
    <div v-if="selectedFlight">
      <h2>Select Your Seat</h2>
      <div v-for="seat in seats" :key="seat.id" class="seat" :class="{ occupied: seat.occupied }" @click="selectSeat(seat)">
        <div>{{ seat.position }}</div>
        <button v-if="!seat.occupied">Select</button>
        <span v-if="seat.occupied">Occupied</span>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  name: 'App',
  setup() {
    const flights = ref([]);
    const seats = ref([]);
    const selectedFlight = ref(null);

    // Fetch flights data from backend API
    const fetchFlights = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/flights');
        flights.value = response.data;
      } catch (error) {
        console.error('Error fetching flights:', error);
      }
    };

    // Fetch seats data based on the selected flight
    const fetchSeats = async (flightId) => {
      try {
        const response = await axios.get(`http://localhost:8080/api/flights/${flightId}/seats`);
        seats.value = response.data;
      } catch (error) {
        console.error('Error fetching seats:', error);
      }
    };

    // Handle flight selection
    const selectFlight = (flightId) => {
      selectedFlight.value = flightId;
      fetchSeats(flightId);
    };

    // Handle seat selection
    const selectSeat = (seat) => {
      if (!seat.occupied) {
        alert(`Seat ${seat.position} selected!`);
      }
    };

    // Fetch flights when component loads
    fetchFlights();

    return {
      flights,
      seats,
      selectedFlight,
      selectFlight,
      selectSeat
    };
  }
};
</script>

<style scoped>
/* Basic styling */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
}

.seat {
  padding: 10px;
  margin: 5px;
  border: 1px solid #ccc;
  cursor: pointer;
}

.seat.occupied {
  background-color: #f00;
  color: #fff;
}
</style>