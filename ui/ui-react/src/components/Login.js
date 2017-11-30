import React from 'react'
import axios from 'axios'
import { Header, Segment, Grid, Form, Input, Message } from 'semantic-ui-react'
import history from '../history'
import * as Security from '../Security'
import * as Api from '../Api'
import { connect } from 'react-redux'
import * as Actions from '../actions'
import { bindActionCreators } from 'redux';

function mapStateToProps(state, props) {
  // armamos un objeto solo con los
  // datos del store que nos interesan
  // y lo devolvemos
  return { };
}

function mapDispatchToProps(dispatch, props) {
  // creamos un objeto con un método para crear
  // y despachar acciones fácilmente y en
  // una sola línea
  const actions = {
    login: bindActionCreators(Actions.login, dispatch),
  };
  
  // devolvemos nuestras funciones dispatch
  // y los props normales del componente
  return { actions };
}

class Login extends React.Component {
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
            console.log('Login');
            Security.login(res.data.token);
            this.props.actions.login(Security.currentUserName());
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
              <Form.Button primary fluid>Login</Form.Button>
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

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login);
