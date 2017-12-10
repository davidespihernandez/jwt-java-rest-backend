import { combineReducers } from 'redux'
import { authentication } from './authentication.reducer';
import { alert } from './alert.reducer';
import { users } from './user.reducer';

const rootReducer = combineReducers({
  authentication,
  alert,
  users
})

export default rootReducer