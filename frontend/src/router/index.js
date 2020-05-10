import Vue from 'vue';
import VueRouter from 'vue-router';
import Toasted from 'vue-toasted';
import VueTypedJs from 'vue-typed-js';
import Notifications from 'vue-notification';
import VCalendar from 'v-calendar';
import VueSlider from 'vue-slider-component';
import 'vue-slider-component/theme/default.css';
import VueClipboard from 'vue-clipboard2';

import { Pagination, Table, TableColumn } from 'element-ui';
import lang from 'element-ui/lib/locale/lang/ko';
import locale from 'element-ui/lib/locale';
import 'element-ui/lib/theme-chalk/index.css';

// import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
// import locale from 'element-ui/lib/locale/lang/ko';

//view 쪽
import Main from '../views/Main.vue';
import Attention from '../views/Attention.vue';
import Mypage from '../views/MyPage.vue';
import SellRoom from '../views/SellRoom.vue';
import SearchView from '../views/SearchView.vue';
import RoomDetail from '../views/RoomDetail.vue';
import BrokerView from '../views/BrokerView.vue';
import RoomUpdate from '../views/UpdateSellRoom.vue';
import NotFound from '../views/NotFound.vue';

//컴포넌트쪽
import LikeRoomContent from '../components/banner/LikeRoomContent.vue';
import RecentSearchRoom from '../components/content2/RecentSearchRoom.vue';
import KeepRoom from '../components/content2/KeepRoom.vue';
import KeepRoomBanner from '../components/banner/KeepRoom.vue';
import RecentRoomBanner from '../components/banner/RecentSearchRoom.vue';
import Profile from '../components/banner/Profile.vue';
import RoomWrite from '../components/banner/RoomWrite.vue';
import store from '../store';
import Payment from '../components/banner/Payment.vue';
import Qna from '../components/banner/Qna.vue';
import QnaWrite from '../components/banner/QnaWrite.vue';
import QnaList from '../components/banner/QnaList.vue';
import LikeRoom from '../components/banner/LikeRoom.vue';

Vue.use(VueRouter);
Vue.use(VueTypedJs);
Vue.use(Toasted);
Vue.use(Notifications);
Vue.use(VCalendar);
Vue.component('VueSlider', VueSlider);
Vue.use(VueClipboard);
locale.use(lang);
Vue.use(Pagination);
Vue.use(Table);
Vue.use(TableColumn);

// Vue.use(ElementUI, { locale });
const requireAuth = (to, from, next) => {
    // const isAuth = localStorage.getItem("token");
    const loginData = sessionStorage.getItem('login');
    // 쿼리문자열은 encdoing이 필요하다
    console.log(to);
    console.log(next);

    //로그인에 갔다가 현재페이지로 돌아와야해서 path
    //로그인 완료 후에 rPath로 redirect
    //현재경로는 파라미터 to 에 있다
    // to 대상 route 객체로 이동
    // from 현재 라우트로 오기전 라우트
    // next를 실행해야지 한다
    // next안에 인자가 있을경우 다른위치로 redirection 된다
    // const loginPath = `/login?rPath=${encodeURIComponent(to.path)}`;
    // console.log(loginPath);
    //토큰이 없을때는 loginPath로 redirect 해버리기
    loginData ? next() : next('/');
};

const routes = [{
        path: '/',
        name: 'Main',
        component: Main,
        children: [
            // {
            //     path: '/',
            //     component: RecentSearchRoom,
            // },
            {
                path: '/',
                component: LikeRoomContent,
            },
        ],
    },

    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/About.vue'),
    },

    {
        path: '/attention',
        name: Attention,
        component: Attention,
        beforeEnter: requireAuth,
        children: [{
                path: '/',
                component: RecentRoomBanner,
            },
            {
                path: '/attention/keep',
                component: LikeRoom,
            },
        ],
    },
    {
        path: '/mypage',
        name: 'Mypage',
        component: Mypage,
        beforeEnter: requireAuth,
        children: [{
                path: '/',
                component: Profile,
            },
            { path: '/mypage/payment', component: Payment },
            { path: '/mypage/wishList', component: LikeRoom },
            {
                path: '/mypage/qna',
                component: Qna,

                children: [{
                        path: '/mypage/qna/write',
                        component: QnaWrite,
                    },
                    {
                        path: '/mypage/qna/list',
                        component: QnaList,
                    },
                ],
            },
            {
                // path: '/mypage/room/list',
                // component: LikeRoom,
                path: '/mypage/broker/:brokerId',
                name: 'broker',
                component: BrokerView,
            },
        ],
    },
    {
        path: '/sellRoom',
        name: 'SellRoom',
        component: SellRoom,
        children: [{
            path: '/',
            component: RoomWrite,
        }, ],
    },
    {
        path: '/search',
        name: 'search',
        component: SearchView,
    },
    {
        path: '/search/detail/:roomId',
        name: 'detail',
        component: RoomDetail,
    },
    {
        path: '/search/detail/update/:roomId',
        name: 'update',
        component: RoomUpdate,
    },
    {
        path: '/broker/:brokerId',
        name: 'broker',
        component: BrokerView,
    },
    {
        path: '/*',
        name: 'ERROR',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: NotFound,
    },
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});
router.beforeEach((to, from, next) => {
    // ${//to and from are Route Object,next() must be called to resolve the hook}
    store.commit('SET_LOADING', true);
    // console.log('hello');
    next();
});

router.afterEach((route) => {
    // ${//these hooks do not get a next function and cannot affect the navigation}
    setTimeout(() => {
        store.commit('SET_LOADING', false);
    }, 0);
    // console.log('hello');
});

export default router;