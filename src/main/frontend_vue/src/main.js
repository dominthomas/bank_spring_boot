import Vue from 'vue'
import App from './App.vue'
import Vuelidate from "vuelidate/src";
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

Vue.config.productionTip = true;
Vue.use(Vuelidate);

//TODO change port number to 8080 before production.
export const serverAPIPath = "http://localhost:8080/api/";

export const bus = new Vue();

new Vue({
    render: h => h(App),
}).$mount('#app');
