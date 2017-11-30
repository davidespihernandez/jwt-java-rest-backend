export const LOGIN = 'LOGIN'
export const LOGOUT = 'LOGOUT'

export const login = (userName) => {
    console.log('Login action ' + userName);
    
    return {
        type: LOGIN,
        userName: userName
      }
}

export const logout = () => {
    return {
        type: LOGOUT
      }
}