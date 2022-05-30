import { createWebHistory, createRouter } from "vue-router";
import Login from "@/views/Login.vue";
import Intra from "@/views/Intra.vue";
import Spotify from "@/views/Spotify.vue";
import Profile from "@/views/Profile.vue";
import Dashperso from "@/views/Dashperso.vue";
import Drive from "@/views/Drive.vue";

const routes = [
  { 
    name: 'dashperso',
    path: '/dashperso', 
    component: Dashperso,
  },
  { 
    name: 'drive',
    path: '/drive', 
    component: Drive,
  },
  { 
    name: 'spotify',
    path: '/', 
    component: Spotify,
  },
  { 
    name: 'intra',
    path: '/intra', 
    component: Intra,
  },
  { 
    name: 'login',
    path: '/login', 
    component: Login,
  },
  { 
    name: 'profile',
    path: '/profile', 
    component: Profile, 
    props:true 
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;