import React from 'react'
import { Link } from 'react-router-dom'

// The Player looks up the player using the number parsed from
// the URL's pathname. If no player is found with the given
// number, then a "player not found" message is displayed.
const User = (props) => {
  const user = {
      id: 1,
      username: "David",
      email: "davidespihernandez@gmail.com"
  }
  if (!user) {
    return <div>Sorry, but the user was not found</div>
  }
  return (
    <div>
      <h1>{user.name} (#{user.id})</h1>
      <h2>Email: {user.email}</h2>
      <Link to='/users'>Back</Link>
    </div>
  )
}

export default User
