import React from 'react'
import { Switch, Route } from 'react-router-dom'
import UserList from './UserList'
import User from './User'
import UserNew from './UserNew'
import UserEdit from './UserEdit'
import Page404 from './Page404'

// The Users component matches one of two different routes
// depending on the full pathname
const Users = () => (
  <Switch>
    <Route exact path='/users' component={UserList}/>
    <Route path='/users/:id/edit' component={UserEdit}/>
    <Route path='/users/new' component={UserNew}/>
    <Route path='/users/:id' component={User}/>
    <Route path='*' component={Page404}/>
  </Switch>
)

export default Users
