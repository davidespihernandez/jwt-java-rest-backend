import React from 'react'
import { Switch, Route } from 'react-router-dom'
import UserList from './UserList'
import User from './User'

// The Users component matches one of two different routes
// depending on the full pathname
const Roster = () => (
  <Switch>
    <Route exact path='/users' component={UserList}/>
    <Route path='/users/:id' component={User}/>
  </Switch>
)

export default Roster
