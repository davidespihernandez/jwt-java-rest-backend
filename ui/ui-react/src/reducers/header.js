import { LOGIN, LOGOUT } from '../actions';

const header = (state = { logged: false, userName: "" }, action) => {
    switch (action.type) {
      case LOGIN:
        return Object.assign({}, state, {
          logged: true, 
          userName: action.userName
        })
      case LOGOUT:
        return Object.assign({}, state, {
          logged: false, 
          userName: ""
        })
      default:
        return state;
    }
  }
  
  export default header;