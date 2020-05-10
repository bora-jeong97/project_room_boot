// =========================================================
// * Vue Material Dashboard - v1.4.0
// =========================================================
//
// * Product Page: https://www.creative-tim.com/product/vue-material-dashboard
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-dashboard/blob/master/LICENSE.md)
//
// * Coded by Creative Tim
//
// =========================================================
//
// * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './App';
import store from './store/';
import Toasted from 'vue-toasted';

// router setup
import routes from './routes/routes';

// Plugins
import GlobalComponents from './globalComponents';
import GlobalDirectives from './globalDirectives';
// import Notifications from './components/NotificationPlugin';
import Noti from 'vue-notification';
// MaterialDashboard plugin
import MaterialDashboard from './material-dashboard';

import Chartist from 'chartist';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
// configure router
const router = new VueRouter({
    mode: 'history',
    routes, // short for routes: routes
    linkExactActiveClass: 'nav-item active',
});

Vue.prototype.$Chartist = Chartist;

Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
Vue.use(VueRouter);
Vue.use(MaterialDashboard);
Vue.use(GlobalComponents);
Vue.use(GlobalDirectives);
// Vue.use(Notifications);
Vue.use(Toasted);
Vue.use(Noti);
/* eslint-disable no-new */
new Vue({
    el: '#app',
    render: (h) => h(App),
    store,
    router,
    data: {
        Chartist: Chartist,
    },
});