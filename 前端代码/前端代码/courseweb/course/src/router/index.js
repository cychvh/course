import { createRouter, createWebHashHistory } from 'vue-router'
import Teacher from '../views/Teacher.vue'
import Director from '../views/Director.vue'
import Course from '../views/Course.vue'
import Notice from '../views/Notice.vue'
import Welcome from '../views/Welcome.vue'
import Curelation from '../views/Curelation.vue'
import UpPwd from '../views/UpPwd.vue'
import Role from '../views/Role.vue'

const routes = [
  {
    path: '/0000',
    name: 'Welcome',
    component: Welcome
  },
  {
    path: '/1001',
    name: 'Teacher',
    component: Teacher
  },
  {
    path: '/1002',
    name: 'Director',
    component: Director
  },
  {
    path: '/3001',
    name: 'Course',
    component: Course
  },
  {
    path: '/2001',
    name: 'Notice',
    component: Notice
  },
  {
    path: '/3002',
    name: 'Curelation',
    component: Curelation
  },
  {
    path: '/4002',
    name: 'UpPwd',
    component: UpPwd
  },
  {
    path: '/4001',
    name: 'Role',
    component: Role
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
