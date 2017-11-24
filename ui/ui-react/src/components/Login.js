import React from 'react'
import axios from 'axios'
import { Header, Segment, Grid, Form, Input, Message } from 'semantic-ui-react'
import history from '../history'
import * as Security from '../Security'
import * as Api from '../Api'

export default class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = { name: '', email: '', mobile: '', fullAddress: '', error: null }
  }

  handleChange = (e, { name, value }) => {
    this.setState({ [name]: value });
  }

  submitForm() {
    let credentials = { username: this.state.username, password: this.state.password};
    this.setState({ error: null });
     axios
       .post(Api.BASE_URLS().api + "/auth", credentials)
       .then(res => {
            Security.login(res.data.token);
            history.push('/');
        }
      )
      .catch(err => {
        this.setState({ error: err.response.data.message });
      })
  }

  render() {
    const { username, password } = this.state
    return (
      <Grid container centered columns={1} verticalAlign='middle' padded>
        <Grid.Column width={6}>
          <Header as='h2' block textAlign='center'>
          Login to your account
          </Header>
          <Segment secondary>
            <Form onSubmit={this.submitForm.bind(this)}>
              <Form.Field name='username' icon='mail' iconPosition='left' value={username} control={Input} label='Email' placeholder='Email' onChange={this.handleChange} />
              <Form.Field name='password' icon='lock' iconPosition='left' value={password} control={Input} type='password' label='Password' placeholder='Password' onChange={this.handleChange} />
              <Form.Button primary fluid large>Login</Form.Button>
            </Form>
          </Segment>
          {
            this.state.error !== null &&
            <Message
              error
              header='Error in login'
              content={this.state.error}
            />
          }
        </Grid.Column>
      </Grid>
    )
  }
}

