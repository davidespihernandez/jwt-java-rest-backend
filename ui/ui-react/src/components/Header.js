import React from 'react'
import { Link } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'

// The Header creates links that can be used to navigate
// between routes.
export default class Header extends React.Component {

  constructor(props) {
    super(props);
    this.state = { activeItem: "home" }
  }

  handleItemClick = (e, { name }) => {
    this.setState({ activeItem: name });
  }
  
  render(props) {
    const { activeItem } = this.state

    return (
        <Menu>
          <Link to="/">
            <Menu.Item 
              name="home"
              active={activeItem === 'home'}
              onClick={this.handleItemClick}
            >
            Home
            </Menu.Item>
          </Link>
          <Link to="/users">
            <Menu.Item
              name="users"
              active={activeItem === 'users'}
              onClick={this.handleItemClick}
            >
            Users
            </Menu.Item>
          </Link>
      </Menu>
    )
  }
}

