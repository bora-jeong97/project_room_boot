import axios from 'axios';
const DOMAIN = 'http://localhost:8081/api/';

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