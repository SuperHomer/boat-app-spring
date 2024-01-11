import { createApp } from 'vue'
import * as VueRouter from 'vue-router'
import './style.css'
import App from './App.vue'
import Create from './components/boat/Create.vue'
import ShowEditDelete from './components/boat/ShowEditDelete.vue'
import List from './components/boat/List.vue'

const routes = [
    { path: '/', component: List },
    { path: '/boat', component: Create },
    { path: '/boats/:id', component: ShowEditDelete, name: 'boat' },
]

const router = VueRouter.createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: VueRouter.createWebHashHistory(),
    routes, // short for `routes: routes`
})

createApp(App)
.use(router)
.mount('#app')
