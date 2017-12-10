import { userConstants } from '../constants';

export function users(state = { items: [] }, action) {
  switch (action.type) {
    case userConstants.SEARCH_REQUEST:
      return {
        loading: true
      };
    case userConstants.SEARCH_SUCCESS:
      return {
        items: action.users
      };
    case userConstants.SEARCH_FAILURE:
      return { 
        error: action.error,
        items: []
      };
    default:
      return state
  }
}