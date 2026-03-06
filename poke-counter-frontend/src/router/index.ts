import { createRouter, createWebHistory } from 'vue-router'
import PokeCalculatorView from '../views/PokeCalculatorView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Poke Calculator',
      component: PokeCalculatorView,
    },
  ],
})

export default router
