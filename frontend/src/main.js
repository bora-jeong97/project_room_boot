import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import IMP from 'vue-iamport';

import 'expose-loader?$!expose-loader?jQuery!jquery';

//Vue.use(IMP, '가맹점식별코드')
Vue.use(IMP, 'imp23418340'); //아임포트 회원가입 후 발급된 가맹점 고유 코드를 사용해주세요. 예시는 KCP공식 아임포트 데모 계정입니다.
Vue.IMP().load();
Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount('#app');