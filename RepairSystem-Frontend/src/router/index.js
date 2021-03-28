import Vue from 'vue'
import VueRouter from 'vue-router'
import Landing from '../pages/Landing/Landing.vue'
import customerDashboard from '../pages/Welcome/CustomerDashboard'

Vue.use(VueRouter)

const routes = [
 {
    path: '/',
    name: '',
    component: Landing
  },
  {
    path: '/customerDashboard',
    component: customerDashboard,
    children: [
      {
        path: '',
        component: () => import( '../pages/Welcome/CustomerOverview.vue')
      },
      {
        path: '/customerDashboard/myAccount',
        component: () => import('../pages/Welcome/CustomerEditProfile.vue')
      },
      {
        path: '/customerDashboard/bookAppointment',
        component: () => import('../pages/CustomerBookAppointment.vue')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
