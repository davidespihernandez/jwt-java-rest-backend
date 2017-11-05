import React, { Component } from 'react';
import './App.css';
import Header from './components/Header'
import Main from './components/Main'
import { Container } from 'semantic-ui-react'

class App extends Component {
  render() {
    return (
      <Container>
        <Header />
        <Main />
      </Container>
    );
  }
}

export default App;
