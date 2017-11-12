import React from 'react'
import axios from 'axios'
import { Form, Input, TextArea, Message } from 'semantic-ui-react'
import history from '../history'

export default class UserEdit extends React.Component {
  constructor(props) {
    super(props);
    this.state = { id: null, name: '', email: '', mobile: '', fullAddress: '', error: null }
  }

  componentDidMount() {
    axios
      .get(`http://localhost:8080/api/users/${this.props.match.params.id}`)
      .then(res => {
        this.setState({ ...res.data,  error404: false });
        this.setState({ mobile: res.data.userInfo.mobile, fullAddress: res.data.userInfo.fullAddress })
        console.log("After reading");
        console.log(this.state);
      })
      .catch(err => {
          if (err.response.status === 404) {
            history.push('./Page404')
          }
        }
      )
  }

  handleChange = (e, { name, value }) => {
    this.setState({ [name]: value });
  }

  submitForm() {
    let user = { id: this.state.id, 
      name: this.state.name, 
      email: this.state.email, 
      userInfo: { mobile: this.state.mobile, fullAddress: this.state.fullAddress} 
    };

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
    return (
      <div>
        <h2>Editar {this.state.name}</h2>
        <Form onSubmit={this.submitForm.bind(this)}>
          <Form.Field name='name' value={name} control={Input} label='Nombre' placeholder='Nombre' onChange={this.handleChange} />
          <Form.Field name='email' value={email} control={Input} label='Email' placeholder='Email' onChange={this.handleChange} />
          <Form.Field name='mobile' value={mobile} control={Input} label='Móvil' placeholder='Móvil' onChange={this.handleChange} />
          <Form.Field name='fullAddress' value={fullAddress} control={TextArea} label='Dirección' placeholder='Dirección' onChange={this.handleChange} />
          <Form.Button>Actualizar</Form.Button>
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

