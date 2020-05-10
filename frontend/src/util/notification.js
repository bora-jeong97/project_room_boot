import Notifications from 'vue-notification';
export const error = (title, location) => {
    location.$notify({
        group: 'notifyApp',
        type: 'error',
        duration: 2000,
        title: title,
    });
};

export const success = (title, location) => {
    location.$notify({
        group: 'notifyApp',
        type: 'success',
        duration: 2000,
        title: title,
    });
};

export const normal = (title, location) => {
    location.$notify({
        group: 'notifyApp',
        duration: 2000,
        position: 'right',
        title: title,
    });
};