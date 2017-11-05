import React from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { Grid, Statistic, Card } from 'semantic-ui-react'

// The Player looks up the player using the number parsed from
// the URL's pathname. If no player is found with the given
// number, then a "player not found" message is displayed.
export default class User extends React.Component {
  constructor(props) {
    super(props);
    this.state = { user: { userInfo: {} }};
    this.props = props;
  }

  componentDidMount() {
    axios
      .get(`http://localhost:8080/api/users/${this.props.match.params.id}`)
      .then(res => this.setState({ user: res.data.users }))
      .catch(err => console.log(err))
  }
  
  render() {
    console.log("User info -> " + this.state.user.userInfo)
    return (
      <div>
        <h2>Perfil de usuario</h2>
        <Grid divided='vertically'>
          <Grid.Row columns={2}>
            <Grid.Column>
              <Card>
                <Card.Content>
                  <Card.Header>
                    {this.state.user.name}
                  </Card.Header>
                  <Card.Meta>
                    <span className='date'>
                      {this.state.user.userInfo.mobile}
                    </span>
                  </Card.Meta>
                  <Card.Description>
                    {this.state.user.email}
                  </Card.Description>
                </Card.Content>
              </Card>
            </Grid.Column>
            <Grid.Column>
              <Statistic.Group>
                <Statistic>
                  <Statistic.Value>22</Statistic.Value>
                  <Statistic.Label>Puntuaciones</Statistic.Label>
                </Statistic>
                <Statistic>
                  <Statistic.Value>2,1</Statistic.Value>
                  <Statistic.Label>Media locura</Statistic.Label>
                </Statistic>
              </Statistic.Group>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </div>
    )
  }
}

