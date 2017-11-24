import React from 'react'
import history from '../history'
import * as Security from '../Security'

export default class SecuredComponent extends React.Component {

  componentWillMount() {
    if (!Security.isLogged()) {
      history.push('/login');
    }
  }
}

