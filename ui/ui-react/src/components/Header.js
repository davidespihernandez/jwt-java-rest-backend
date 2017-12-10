import React from 'react'
import { Menu, Button } from 'semantic-ui-react'
import { connect } from 'react-redux'
import { userActions } from '../actions'
import history from '../history';

function mapStateToProps(state, props) {
  let userName = '';
  if (state.authentication.user) {
    userName = state.authentication.user.name;
  }
  return { 
    logged: state.authentication.loggedIn,
    userName: userName
  };
}

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = { activeItem: "" }
  }

  handleItemClick = (e, { name }) => {
    this.setState({ activeItem: name });
    history.push('/' + name);
  }
  
  doLogout = (e) => {
    this.props.dispatch(userActions.logout());
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
                !this.props.logged && 
                (
                  <Button primary name='login' onClick={this.handleItemClick}>Login</Button>
                )
              }
              {
                this.props.logged && 
                (
                  <span>
                    {this.props.userName + " "}
                    <Button as="a" size="small" secondary name='logout' onClick={this.doLogout}>Logout</Button>
                  </span>
                )
              }
              
          </Menu.Menu>
      </Menu>
    )
  }
}

export default connect(
  mapStateToProps
)(Header);
