import { userConstants } from '../constants';
import * as Security from '../Security'

const initialState = { loggedIn: Security.isLogged() };

export function authentication(state = initialState, action) {
  switch (action.type) {
    case userConstants.LOGIN_REQUEST:
      return {
        loggingIn: true
      };
    case userConstants.LOGIN_SUCCESS:
      return {
        loggedIn: true,
        user: action.user
      };
    case userConstants.LOGIN_FAILURE:
      return {
        loggedIn: false
      };
    case userConstants.LOGOUT:
      return {
        loggedIn: false
      };
    default:
          return state
  }
}