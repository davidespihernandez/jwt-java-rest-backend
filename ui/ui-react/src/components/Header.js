import React from 'react'
import { Menu, Button } from 'semantic-ui-react'
import history from '../history'
import * as Security from '../Security'

// The Header creates links that can be used to navigate
// between routes.
export default class Header extends React.Component {

  constructor(props) {
    super(props);
    this.state = { activeItem: "", userName: "", logged: false }
  }

  componentDidMount() {
    if (Security.isLogged()) {
      this.setState({ logged: true, userName: Security.currentUserName() })
    }
  }

  handleItemClick = (e, { name }) => {
    this.setState({ activeItem: name });
    history.push('/' + name);
  }
  
  doLogout = (e) => {
    Security.logout();
    this.setState({ logged: false, userName: "" })
    history.push('/');
  }

  render(props) {
    const { activeItem } = this.state

    return (
        <Menu secondary pointing>
            <Menu.Item 
              name=""
              active={activeItem === ''}
              onClick={this.handleItemClick}
            >
            Home
            </Menu.Item>
            <Menu.Item
              name="users"
              active={activeItem === 'users'}
              onClick={this.handleItemClick}
            >
            Users
            </Menu.Item>
            <Menu.Menu position='right'>
              {
                !this.state.logged && 
                (
                  <Button primary name='login' onClick={this.handleItemClick}>Login</Button>
                )
              }
              {
                this.state.logged && 
                (
                  <span>
                    {this.state.userName + " "}
                    <Button as="a" size="small" secondary name='logout' onClick={this.doLogout}>Logout</Button>
                  </span>
                )
              }
              
          </Menu.Menu>
      </Menu>
    )
  }
}

