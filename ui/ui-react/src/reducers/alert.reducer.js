import { alertConstants } from '../constants';

export function alert(state = {}, action) {
  switch (action.type) {
    case alertConstants.SUCCESS:
      return {
        success: action.message
      };
    case alertConstants.ERROR:
      return {
        error: action.message
      };
    case alertConstants.CLEAR:
      return {};
    default:
      return state
  }
}