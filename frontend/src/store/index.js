import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loginData: !sessionStorage.getItem('login') ?
            '' :
            JSON.parse(sessionStorage.getItem('login')),
        profileImage: !sessionStorage.getItem('login') ?
            '' :
            JSON.parse(sessionStorage.getItem('login')).profile_image,
        loginModal: false,
        registerModal: false,
        localPath: '/', //경로를 저장,
        searchModal: false,
        selectRegister: '',
        imageList: [],
        imageListModal: false,
        loading: false, //로딩 컴포넌트를 위함
        qnaModal: false,
        qnaReplyModal: false,
        profileModal: false,



        searchList: [ ],

        dongLatitude: 0.0,  //DONG의 위도
        dongLongitude: 0.0, //DONG의 경도
        
        roomLatitude: 0.0,  //마커용 방의 위도
        roomLongitude: 0.0, //마커용 방의 경도

        roomTypeOption: [ ],
        roomPayOption: [ ],
        deposit: 0, //보증금
        lease: 0, //전세
        monthRent: 0, //월세
        roomSpace: 0, //몇평

        swLat: 0.0, //남서 위도
        swLng: 0.0, //남서 경도
        neLat: 0.0, //북동 위도
        neLng: 0.0, //북동 경도

    },
    mutations: {
        SET_SW_LAT(state, data) {
            state.swLat = data;
        },
        SET_SW_LNG(state, data) {
            state.swLng = data;
        },
        SET_NE_LAT(state, data) {
            state.neLat = data;
        },
        SET_NE_LNG(state, data) {
            state.neLng = data;
        },
        SET_DONG_LATITUDE(state, data) {
            state.dongLatitude = data;
        },
        SET_DONG_LONGITUDE(state, data) {
            state.dongLongitude = data;
        },
        SET_ROOM_SPACE(state, data) {
            state.roomSpace = data;
        },
        SET_MONTH_RENT(state, data) {
            state.monthRent = data;
        },
        SET_LEASE(state, data) {
            state.lease = data;
        },
        SET_DEPOSIT(state, data) {
            state.deposit = data;
        },
        SET_ROOM_PAY_OPTION(state, data) {
            state.roomPayOption = data;
        },
        SET_ROOM_TYPE_OPTION(state, data) {
            state.roomTypeOption = data;
        },
        SET_DONG_LONGITUDE(state, data) {
            state.dongLongitude = data;
        },
        SET_DONG_LATITUDE(state, data) {
            state.dongLatitude = data;
        },
        SET_SEARCH_LIST(state, data) {
            state.searchList = data;
        },
        SET_LOGIN(state, data) {
            state.loginData = data;
        },
        SET_PROFILE_IMAGE(state, data) {
            state.profileImage = data;
        },
        SET_LOGIN_MODAL(state, data) {
            state.loginModal = data;
            state.registerModal = false;
        },
        SET_REGISTER_MODAL(state, data) {
            //로그인 창 닫기
            state.loginModal = false;
            //회원가입창 열어주기
            state.registerModal = data;
        },
        SET_LOCAL_PATH(state, data) {
            state.localPath = data;
        },
        SET_SEARCH_MODAL(state, data) {
            state.searchModal = data;
        },
        SET_SELECT_REGISTER(state, data) {
            state.selectRegister = data;
        },
        SET_IMAGE_LIST(state, data) {
            state.imageList = data;
        },
        SET_IMAGE_LIST_MODAL(state, data) {
            state.imageListModal = data;
        },
        SET_LOADING(state, data) {
            state.loading = data;
        },
        SET_QNA_MODAL(state, data) {
            state.qnaModal = data;
        },
        SET_QNA_REPLY_MODAL(state, data) {
            state.qnaReplyModal = data;
        },
        SET_PROFILE_MODAL(state, data) {
            state.profileModal = data;
        },
    },
    actions: {},
    modules: {},
});