import React from 'react'
import { Grid, Statistic, Card } from 'semantic-ui-react'
import SecuredComponent from './SecuredComponent'
import Page404 from './Page404'
import { userService } from '../services'

export default class User extends SecuredComponent {
  constructor(props) {
    super(props);
    this.state = { user: { userInfo: {} }, error404: false};
    this.props = props;
  }

  componentDidMount() {
    userService.getById(this.props.match.params.id)
      .then(res => this.setState({ user: res.data }))
      .catch(err => {
          if (err.response.status === 404) {
            this.setState( { error404: true } )
          }
        }
      )
  }
  
  render() {
    if (this.state.error404) {
      return <Page404 />
    }
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

