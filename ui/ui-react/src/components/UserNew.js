import React from 'react'
import axios from 'axios'
import { Form, Input, TextArea, Message } from 'semantic-ui-react'
import history from '../history'

export default class User extends React.Component {
  constructor(props) {
    super(props);
    this.state = { name: '', email: '', mobile: '', fullAddress: '', error: null }
  }

  handleChange = (e, { name, value }) => {
    this.setState({ [name]: value });
  }

  submitForm() {
    let user = { name: this.state.name, email: this.state.email, userInfo: { mobile: this.state.mobile, fullAddress: this.state.fullAddress} };
    this.setState({ error: null });
     axios
       .post("http://localhost:8080/api/users", user)
       .then(res => {
           history.push('/users')
         }
       )
       .catch(err => {
          this.setState({ error: err.response.data.message });
       })
  }

  render() {
    const { name, email, mobile, fullAddress } = this.state
    
    console.log(this.state.error)
    return (
      <div>
        <h2>Nuevo usuario</h2>
        <Form onSubmit={this.submitForm.bind(this)}>
          <Form.Field name='name' value={name} control={Input} label='Nombre' placeholder='Nombre' onChange={this.handleChange} />
          <Form.Field name='email' value={email} control={Input} label='Email' placeholder='Email' onChange={this.handleChange} />
          <Form.Field name='mobile' value={mobile} control={Input} label='Móvil' placeholder='Móvil' onChange={this.handleChange} />
          <Form.Field name='fullAddress' value={fullAddress} control={TextArea} label='Dirección' placeholder='Dirección' onChange={this.handleChange} />
          <Form.Button>Crear</Form.Button>
        </Form>
        {
          this.state.error !== null &&
          <Message
            error
            header='Error saving user'
            content={this.state.error}
          />
        }
      </div>
    )
  }
}

