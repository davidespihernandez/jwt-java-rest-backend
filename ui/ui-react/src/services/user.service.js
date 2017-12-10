import axios from 'axios'
import * as Security from '../Security'
import * as Api from '../Api'

export const userService = {
    login,
    logout,
    search,
    getById,
    createOrUpdate
};

function login(credentials) {
    return axios.post(Api.BASE_URLS().api + "/auth", credentials);
}

function logout() {
    Security.logout();
}

function search() {
    return Api.get('/users');
}

function getById(id) {
    return Api.get(`/users/${id}`);
    
}

function createOrUpdate(user) {
    return Api.post("/users", user);
}
