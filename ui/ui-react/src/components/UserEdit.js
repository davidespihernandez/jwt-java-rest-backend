import React from 'react'
import { Form, Input, TextArea, Message } from 'semantic-ui-react'
import SecuredComponent from './SecuredComponent'
import history from '../history'
import { userService } from '../services'
import { alertActions, userActions } from '../actions'
import { connect } from 'react-redux'

class UserEdit extends SecuredComponent {
  constructor(props) {
    super(props);
    this.state = { id: null, name: '', email: '', mobile: '', fullAddress: '', error: null }
  }

  componentDidMount() {
    this.props.dispatch(alertActions.clear());
    userService.getById(this.props.match.params.id)
      .then(res => {
        this.setState({ ...res.data,  error: false });
        this.setState({ mobile: res.data.userInfo.mobile, fullAddress: res.data.userInfo.fullAddress })
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

    this.props.dispatch(userActions.createOrUpdate(user));
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

const connectedUserEditPage = connect(mapStateToProps)(UserEdit);
export { connectedUserEditPage as UserEdit }; 