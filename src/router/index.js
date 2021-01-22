import Vue from 'vue'
import Router from 'vue-router'
import LoginAndRegister from '@/components/LoginAndRegister/LoginAndRegister'
import Home from '@/components/Home/Home'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      component: LoginAndRegister
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginAndRegister
    },
    {
      path: '/register',
      name: 'Register',
      component: LoginAndRegister
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    }
  ]
})
