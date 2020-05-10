export const requestPay = () => {
    // IMP.request_pay(param, callback) 호출
    IMP.request_pay({
            // param
            pg: 'inicis',
            pay_method: 'card',
            merchant_uid: 'ORD20180131-0000011',
            name: '구해방 글쓰기 패키지',
            amount: 64900,
            buyer_email: '',
            buyer_name: '홍길동',
            buyer_tel: '010-4242-4242',
            buyer_addr: '서울특별시 강남구 신사동',
            buyer_postcode: '01181',
        },
        (rsp) => {
            // callback
            if (rsp.success) {
                console.log('s');
                // ...,
                // // 결제 성공 시 로직,
                // ...
            } else {
                console.log('n');
                // ...,
                // // 결제 실패 시 로직,
                // ...
            }
        },
    );
};