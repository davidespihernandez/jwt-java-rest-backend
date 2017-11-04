import React from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'

// The FullRoster iterates over all of the players and creates
// a link to their profile page.
export default class UserList extends React.Component {

  constructor(props) {
    super(props);
    this.state = { users: []};
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/users")
      .then(res => this.setState({ users: res.data.users }))
      .catch(err => console.log(err))
  }

  render() {
    return (    
      <div>
        <ul>
          {
            this.state.users.map(u => (
              <li key={u.id}>
                <Link to={`/api/users/${u.id}`}>{u.name}</Link>
              </li>
            ))
          }
        </ul>
      </div>
    )
  }
}

