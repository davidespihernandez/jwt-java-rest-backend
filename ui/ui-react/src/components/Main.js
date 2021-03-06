import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './Home'
import Users from './Users'
import { Login } from './Login'
import Page404 from './Page404'

const Main = () => (
  <main>
    <Switch>
      <Route exact path='/' component={Home}/>
      <Route path='/login' component={Login}/>
      <Route path='/users' component={Users}/>
      <Route path='*' component={Page404}/>
    </Switch>
  </main>
)

export default Main
