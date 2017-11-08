import React from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { List, Button, Divider } from 'semantic-ui-react'
import history from '../history'

// The Users page iterates over all of the players and creates
// a link to their user page.
export default class UserList extends React.Component {

  constructor(props) {
    super(props);
    this.state = { users: []};
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/users")
      .then(res => this.setState({ users: res.data }))
      .catch(err => console.log(err))
  }

  render() {
    return (
      <div>
        <h2>User list</h2>
        <List divided relaxed>
          {
            this.state.users.map(u => (
              <List.Item key={u.id}>
                <List.Icon name='user' size='large' verticalAlign='middle' />
                <List.Content>
                  <List.Header><Link to={`/users/${u.id}`}>{u.name}</Link></List.Header>
                  <List.Description as='a'>{u.email}</List.Description>
                </List.Content>
              </List.Item>
              )
            )
          }
        </List>
        <Divider />
        <Button primary onClick={() => history.push('/users/new')}>New user</Button>
      </div>
    )
  }
}

