import React from 'react'
import { Switch, Route } from 'react-router-dom'
import UserList from './UserList'
import User from './User'
import UserNew from './UserNew'

// The Users component matches one of two different routes
// depending on the full pathname
const Users = () => (
  <Switch>
    <Route exact path='/users' component={UserList}/>
    <Route path='/users/new' component={UserNew}/>
    <Route path='/users/:id' component={User}/>
  </Switch>
)

export default Users
