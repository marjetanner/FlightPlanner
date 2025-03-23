<template>
  <div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Lennuplaan</h1>
    <div class="mb-4 flex gap-4">
      <input v-model="filters.destination" placeholder="Sihtkoht" class="p-2 border rounded" />
      <input v-model="filters.date" type="date" class="p-2 border rounded" />
      <input v-model="filters.price" type="number" placeholder="Max hind" class="p-2 border rounded" />
      <button @click="fetchFlights" class="p-2 bg-blue-500 text-white rounded">Filtreeri</button>
    </div>
    <table class="w-full border-collapse border border-gray-300">
      <thead>
      <tr class="bg-gray-200">
        <th class="border p-2">Sihtkoht</th>
        <th class="border p-2">Kuupäev</th>
        <th class="border p-2">Hind</th>
        <th class="border p-2">Vali</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="flight in flights" :key="flight.id" class="border">
        <td class="p-2">{{ flight.destination }}</td>
        <td class="p-2">{{ flight.departureDate }}</td>
        <td class="p-2">{{ flight.price }}€</td>
        <td class="p-2">
          <button @click="selectFlight(flight.id)" class="bg-green-500 text-white p-1 rounded">Vali</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      flights: [],
      filters: { destination: '', date: '', price: '' }
    };
  },
  methods: {
    async fetchFlights() {
      const { destination, date, price } = this.filters;
      const response = await axios.get('/api/flights/search', { params: { destination, departureDate: date, price } });
      this.flights = response.data;
    },
    selectFlight(flightId) {
      this.$router.push(`/flight/${flightId}`);
    }
  },
  async mounted() {
    await this.fetchFlights();
  }
};
</script>

<style>
.container {
  max-width: 800px;
}
</style>
