import React from 'react'
import { Menu, Button } from 'semantic-ui-react'
import history from '../history'
import * as Security from '../Security'
import { connect } from 'react-redux'
import * as Actions from '../actions'
import { bindActionCreators } from 'redux';

function mapStateToProps(state, props) {
  // armamos un objeto solo con los
  // datos del store que nos interesan
  // y lo devolvemos
  return { 
    logged: state.header.logged,
    userName: state.header.userName
  };
}

function mapDispatchToProps(dispatch, props) {
  // creamos un objeto con un método para crear
  // y despachar acciones fácilmente y en
  // una sola línea
  const actions = {
    logout: bindActionCreators(Actions.logout, dispatch),
  };

  return { actions };
}


// The Header creates links that can be used to navigate between routes.
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
    Security.logout();
    this.props.actions.logout();
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
  mapStateToProps,
  mapDispatchToProps
)(Header);
