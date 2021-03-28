import Vue from 'vue'
import VueRouter from 'vue-router'
import Landing from '../pages/Landing/Landing.vue'
import customerDashboard from '../pages/Welcome/CustomerDashboard'
import mechanicDashboard from '../pages/Welcome/MechanicDashboard'

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
  },
  {
    path: '/mechanicDashboard',
    component: mechanicDashboard,
    children: [
      {
        path: '',
        component: () => import( '../pages/Welcome/MechanicOverview.vue')
      },
      {
        path: '/mechanicDashboard/myAccount',
        component: () => import('../pages/Welcome/MechanicEditProfile.vue')
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
