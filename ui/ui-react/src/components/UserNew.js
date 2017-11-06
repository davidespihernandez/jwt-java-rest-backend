import React from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { Form, Input, TextArea } from 'semantic-ui-react'

export default class User extends React.Component {
  constructor(props) {
    super(props);
    this.state = { name: '', email: '', mobile: '', fullAddress: '' }
  }

  handleChange = (e, { name, value }) => {
    this.setState({ [name]: value });
  }

  submitForm() {
    console.log(this.state);
    let user = { name: this.state.name, email: this.state.email, userInfo: { mobile: this.state.mobile, fullAddress: this.state.fullAddress} };
     axios
       .post("http://localhost:8080/api/users", user)
       .then(res => {
           console.log("Created user ");
           console.log(user);
         }
       )
       .catch(err => console.log(err))
  }

  render() {
    const { name, email, mobile, fullAddress } = this.state
    
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
      </div>
    )
  }
}

