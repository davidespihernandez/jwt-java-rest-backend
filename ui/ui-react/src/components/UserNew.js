import React from 'react'
import { Form, Input, TextArea, Message } from 'semantic-ui-react'
import SecuredComponent from './SecuredComponent'
import { connect } from 'react-redux'
import { userActions } from '../actions'

class UserNew extends SecuredComponent {
  constructor(props) {
    super(props);
    this.state = { name: '', email: '', mobile: '', fullAddress: '', error: null }
  }

  handleChange = (e, { name, value }) => {
    this.setState({ [name]: value });
  }

  submitForm() {
    let user = { name: this.state.name, email: this.state.email, userInfo: { mobile: this.state.mobile, fullAddress: this.state.fullAddress} };
    this.props.dispatch(userActions.createOrUpdate(user));
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
        {
          this.props.error &&
          <Message
            error
            header='Error saving user'
            content={this.props.error}
          />
        }
      </div>
    )
  }
}

function mapStateToProps(state) {
  const { error } = state.alert;
  return {
      error
  };
}

const connectedUserNewPage = connect(mapStateToProps)(UserNew);
export { connectedUserNewPage as UserNew }; 