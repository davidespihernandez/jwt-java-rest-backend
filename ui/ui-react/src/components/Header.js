import React from 'react'
import { Menu } from 'semantic-ui-react'
import history from '../history'

// The Header creates links that can be used to navigate
// between routes.
export default class Header extends React.Component {

  constructor(props) {
    super(props);
    this.state = { activeItem: "home" }
  }

  handleItemClick = (e, { name }) => {
    this.setState({ activeItem: name });
    history.push('/' + name);
  }
  
  render(props) {
    const { activeItem } = this.state

    return (
        <Menu secondary>
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
              <Menu.Item name='login' active={activeItem === 'login'} onClick={this.handleItemClick} />
          </Menu.Menu>
      </Menu>
    )
  }
}

