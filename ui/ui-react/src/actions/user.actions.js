import { userConstants } from '../constants';
import { userService } from '../services';
import { alertActions } from './alert.actions';
import history from '../history';
import * as Security from '../Security'

export const userActions = {
    login,
    logout,
    createOrUpdate,
    search
};

function login(credentials) {
    return dispatch => {
        dispatch(request({ username: credentials.username }));

        userService.login(credentials)
            .then(
                res => { 
                    Security.login(res.data.token);
                    dispatch(success(Security.currentUser()));
                    history.push('/');
            })
            .catch(error => {
                dispatch(failure(error));
                dispatch(alertActions.error(error));
            });
    };

    function request(user) { return { type: userConstants.LOGIN_REQUEST, user } }
    function success(user) { return { type: userConstants.LOGIN_SUCCESS, user } }
    function failure(error) { return { type: userConstants.LOGIN_FAILURE, error } }
}

function logout() {
    userService.logout();
    history.push('/');
    return { type: userConstants.LOGOUT };
}

function createOrUpdate(user) {
    return dispatch => {
        dispatch(request(user));

        userService.createOrUpdate(user)
            .then(
                res => { 
                    dispatch(success());
                    history.push('/users');
                    dispatch(alertActions.success('User updated successfully'));
            })
            .catch(error => {
                dispatch(failure(error));
                dispatch(alertActions.error(error));
            });
    };

    function request(user) { return { type: userConstants.UPDATE_REQUEST, user } }
    function success(user) { return { type: userConstants.UPDATE_SUCCESS, user } }
    function failure(error) { return { type: userConstants.UPDATE_FAILURE, error } }
}

function search() {
    return dispatch => {
        dispatch(request());

        userService.search()
            .then(res => dispatch(success(res.data)))
            .catch(error => dispatch(failure(error)));
    };

    function request() { return { type: userConstants.SEARCH_REQUEST } }
    function success(users) { return { type: userConstants.SEARCH_SUCCESS, users } }
    function failure(error) { return { type: userConstants.SEARCH_FAILURE, error } }
}
