import jwtDecode from 'jwt-decode'
import { userConstants } from './constants'

export function logout() {
    localStorage.removeItem(userConstants.JWT_TOKEN);
}

export function login(token) {
    localStorage.setItem(userConstants.JWT_TOKEN, token);
}

export function getSecurityHeader() {
    let token = localStorage.getItem(userConstants.JWT_TOKEN)
    if (!token) {
        return {};
    }
    return { headers : { Authorization : 'Bearer ' + localStorage.getItem(userConstants.JWT_TOKEN)} }
}

export function isLogged() {
    return localStorage.getItem(userConstants.JWT_TOKEN);
}

export function getTokenInfo() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(userConstants.JWT_TOKEN));
    return decoded;
}

export function currentUserName() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(userConstants.JWT_TOKEN));
    return decoded.name;
}

export function currentUserId() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(userConstants.JWT_TOKEN));
    return decoded.userId;
}

export function currentUserEmail() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(userConstants.JWT_TOKEN));
    return decoded.sub;
}

export function currentUser() {
    if (!isLogged) {
        return null;
    }
    var decoded = jwtDecode(localStorage.getItem(userConstants.JWT_TOKEN));
    return {
        id: decoded.userId,
        email: decoded.sub,
        name: decoded.name
    }    
}
