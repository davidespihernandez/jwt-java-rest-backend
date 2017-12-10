import React from 'react'
import { Header, Segment, Grid, Form, Input, Message } from 'semantic-ui-react'
import { connect } from 'react-redux'
import { userActions } from '../actions';
import { userService } from '../services'

class Login extends React.Component {
  constructor(props) {
    super(props);
    // reset login status
    userService.logout();
    this.state = { 
      username: '', 
      password: '', 
      submitted: false 
    }
  }

  handleChange = (e, { name, value }) => {
    this.setState({ [name]: value });
  }

  submitForm(e) {
    e.preventDefault();
    let credentials = { username: this.state.username, password: this.state.password};
    this.setState({ submitted: true });
    const { dispatch } = this.props;
    if (credentials.username && credentials.password) {
      dispatch(userActions.login(credentials));
    }
  }

  render() {
    const { username, password, submitted } = this.state
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
              <Form.Button primary fluid>Login</Form.Button>
            </Form>
          </Segment>
          {
            this.props.error && submitted &&
            <Message
              error
              header='Error in login'
              content={this.props.error}
            />
          }
        </Grid.Column>
      </Grid>
    )
  }
}

function mapStateToProps(state) {
  const { loggingIn } = state.authentication;
  const { error } = state.alert;
  return {
      loggingIn,
      error
  };
}

const connectedLoginPage = connect(mapStateToProps)(Login);
export { connectedLoginPage as Login }; 