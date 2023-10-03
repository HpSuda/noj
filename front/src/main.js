import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './components/router/router.js'
import axios from 'axios';
// main.ts
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import VueCookies from 'vue3-cookies'

const api = axios.create({
  baseURL: 'http://localhost:9090',
});



const app = createApp(App)
app.use(VueCookies)
// app.config.globalProperties.$cookies = VueCookies
app.config.globalProperties.$api = api;
app.use(ElementPlus)
app.use(router)
app.use(VueCookies)
app.mount('#app')

// createApp(App).mount('#app')