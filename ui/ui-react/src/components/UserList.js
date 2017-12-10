import React from 'react'
import { Link } from 'react-router-dom'
import { List, Button } from 'semantic-ui-react'
import SecuredComponent from './SecuredComponent'
import { userActions } from '../actions'
import history from '../history';
import { connect } from 'react-redux'

class UserList extends SecuredComponent {

  constructor(props) {
    super(props);
    this.state = { users: []};
  }

  componentDidMount() {
    this.props.dispatch(userActions.search());
  }

  render() {
    return (
      <div>
        <h2>Lista de usuarios</h2>
        <List divided verticalAlign='middle'>
          {
            this.props.users.map(u => (
              <List.Item key={u.id}>
                <List.Content floated='right'>
                  <Button content='Editar' icon='edit' onClick={() => history.push(`/users/${u.id}/edit`)} />
                </List.Content>
                <List.Icon name='user' size='large' verticalAlign='middle' />
                <List.Content>
                  <List.Header><Link to={`/users/${u.id}`}>{u.name}</Link></List.Header>
                  <List.Content>{u.email}</List.Content>
                </List.Content>
              </List.Item>
              )
            )
          }
        </List>
        <Button primary onClick={() => history.push('/users/new')}>Nuevo</Button>
      </div>
    )
  }
}

function mapStateToProps(state) {
  const error = state.alert.error;
  const users = state.users.items || [];
  console.log("mapStateToProps");
  console.log(error);
  console.log(users);
  console.log(state);
  return {
      error,
      users
  };
}

const connectedUserListPage = connect(mapStateToProps)(UserList);
export { connectedUserListPage as UserList }; 