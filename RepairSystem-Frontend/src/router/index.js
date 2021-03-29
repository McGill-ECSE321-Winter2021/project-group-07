import Vue from 'vue'
import VueRouter from 'vue-router'
import Landing from '../pages/Landing/Landing.vue'
import customerDashboard from '../pages/Welcome/CustomerDashboard'
import mechanicDashboard from '../pages/Welcome/MechanicDashboard'
import adminDashboard from '../pages/Welcome/AdminDashboard'
import LogIn from '../pages/LogInAndSignUp/LogIn'
import SignUp from '../pages/LogInAndSignUp/SignUp'

Vue.use(VueRouter)

const routes = [
 {
    path: '/',
    name: '',
    component: Landing
  },
  {
    path: '/login',
    component: LogIn,
    children: [
      {
        path: '',
        component: () => import( '../pages/LogInAndSignUp/LogIn.vue')
      },
    ]
  },
  {
    path: '/signup',
    component: SignUp,
    children: [
      {
        path: '',
        component: () => import( '../pages/LogInAndSignUp/SignUp.vue')
      },
    ]
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
  },
  {
    path: '/adminDashboard',
    component: adminDashboard,
    children: [
      {
        path: '',
        component: () => import( '../pages/Welcome/AdminOverview.vue')
      },
      {
        path: '/adminDashboard/myAccount',
        component: () => import('../pages/Welcome/AdminEditProfile.vue')
      },
      {
        path: '/adminDashboard/addCutomerOrMechanic',
        component: () => import('../pages/Welcome/AdminAddCustomerOrMechanic.vue')
      },
      {
        path: '/adminDashboard/editCustomerOrMechanic',
        component: () => import('../pages/Welcome/AdminEditCustomerOrMechanic.vue')
      },
      {
        path: '/adminDashboard/viewCustomerOrMechanic',
        component: () => import('../pages/Welcome/AdminViewAllCustomerOrMechanic.vue')
      },
      {
        path: '/adminDashboard/addAppointment',
        component: () => import('../pages/Welcome/AdminAddAppointment.vue')
      },
      {
        path: '/adminDashboard/editAppointment',
        component: () => import('../pages/Welcome/AdminEditAppointment.vue')
      },
      {
        path: '/adminDashboard/addTimeSlot',
        component: () => import('../pages/Welcome/AdminAddTimeSlot.vue')
      },
      {
        path: '/adminDashboard/editTimeSlot',
        component: () => import('../pages/Welcome/AdminEditTimeSlot.vue')
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
