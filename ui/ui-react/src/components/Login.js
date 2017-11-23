import React from 'react'
import axios from 'axios'
import { Form, Input, Message } from 'semantic-ui-react'
import history from '../history'

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
       .post("http://localhost:8080/auth", credentials)
       .then(res => {
            console.log("Logged");
            console.log(res);
            localStorage.setItem("JWT_TOKEN", res.data.token);
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
      <div>
        <h2>Login</h2>
        <Form onSubmit={this.submitForm.bind(this)}>
          <Form.Field name='username' value={username} control={Input} label='Email' placeholder='Email' onChange={this.handleChange} />
          <Form.Field name='password' value={password} control={Input} type='password' label='Password' placeholder='Password' onChange={this.handleChange} />
          <Form.Button>Login</Form.Button>
        </Form>
        {
          this.state.error !== null &&
          <Message
            error
            header='Error in login'
            content={this.state.error}
          />
        }
      </div>
    )
  }
}

