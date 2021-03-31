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
    path: '/customerDashboard/Overview/:userId',
    component: customerDashboard,
    children: [
      {
        path: '/customerDashboard/Overview/:userId',
        component: () => import( '../pages/Welcome/CustomerOverview.vue')
      },
      {
        path: '/customerDashboard/myAccount/:userId',
        component: () => import('../pages/Welcome/CustomerEditProfile.vue')
      },
      {
        path: '/customerDashboard/bookAppointment/:userId',
        component: () => import('../pages/Booking/CustomerBookAppointment.vue')
      }
    ]
  },
  {
    path: '/mechanicDashboard/Overview/:userId',
    component: mechanicDashboard,
    children: [
      {
        path:'/mechanicDashboard/Overview/:userId',
        component: () => import( '../pages/Welcome/MechanicOverview.vue')
      },
      {
        path: '/mechanicDashboard/myAccount/:userId',
        component: () => import('../pages/Welcome/MechanicEditProfile.vue')
      }
    ]
  },
  {
    path: '/adminDashboard/Overview/:userId',
    component: adminDashboard,
    children: [
      {
        path: '/adminDashboard/Overview/:userId',
        component: () => import( '../pages/Welcome/AdminOverview.vue')
      },
      {
        path: '/adminDashboard/myAccount/:userId',
        component: () => import('../pages/Welcome/AdminEditProfile.vue')
      },
      {
        path: '/adminDashboard/TeamMembers/:userId',
        component: () => import('../pages/Welcome/AdminTeamMebmers.vue')
      },
      {
        path: '/adminDashboard/Customers/:userId',
        component: () => import('../pages/Welcome/AdminCustomer.vue')
      },
      {
        path: '/adminDashboard/ViewAllCustomersOrMechanics/:userId',
        component: () => import('../pages/Welcome/AdminViewAllCustomersOrMechanics.vue')
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
