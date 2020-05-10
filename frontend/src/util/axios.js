import axios from 'axios';
const DOMAIN = 'http://localhost:8081/api/';

// 일반 URlParams 요청하기
export const request = (method, url, data) => {
    return axios({
            method: method,
            url: DOMAIN + url,
            data: data,
        })
        .then((result) => result.data)
        .catch((error) => {
            console.log(error);
        });
};

// params(get)전송
export const requestParams = (method, url, data) => {
    return axios({
            method: method,
            url: DOMAIN + url,
            params: data,
        })
        .then((result) => result.data)
        .catch((error) => {
            console.log(error);
        });
};

// 파일전송
export const requestFile = (method, url, data) => {
    return axios({
            method: method,
            url: DOMAIN + url,
            data: data,
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        })
        .then((result) => result.data)
        .catch((error) => {
            console.log(error);
        });
};

// export const request = {
//     registerUser() {
//         return request('post', 'user/register');
//     },
// };