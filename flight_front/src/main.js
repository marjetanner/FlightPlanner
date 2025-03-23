import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import './assets/tailwind.css';
import axios from 'axios';
import router from "@/router/index.js";

const app = createApp(App)
app.config.globalProperties.$axios = axios;
app.use(router)

app.mount('#app')
