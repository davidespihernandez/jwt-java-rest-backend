import jwtDecode from 'jwt-decode'
import * as Constants from './Constants'

export function logout() {
    localStorage.removeItem(Constants.JWT_TOKEN);
}

export function login(token) {
    localStorage.setItem(Constants.JWT_TOKEN, token);
}

export function getSecurityHeader() {
    let token = localStorage.getItem(Constants.JWT_TOKEN)
    if (!token) {
        return {};
    }
    return { headers : { Authorization : 'Bearer ' + localStorage.getItem(Constants.JWT_TOKEN)} }
}

export function isLogged() {
    return localStorage.getItem(Constants.JWT_TOKEN);
}

export function getTokenInfo() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(Constants.JWT_TOKEN));
    return decoded;
}

export function currentUserName() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(Constants.JWT_TOKEN));
    return decoded.name;
}

export function currentUserId() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(Constants.JWT_TOKEN));
    return decoded.userId;
}

export function currentUserEmail() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(Constants.JWT_TOKEN));
    return decoded.sub;
}
