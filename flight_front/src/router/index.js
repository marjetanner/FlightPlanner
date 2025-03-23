import { createRouter, createWebHistory } from 'vue-router';
import FlightsView from '../views/HomeView.vue';

const routes = [
  { path: '/', component: FlightsView }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

export default router
