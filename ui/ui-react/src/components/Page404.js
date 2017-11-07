import React from 'react'

export default class Page404 extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
    <div class="masthead error segment">
      <div class="container">
        <h1 class="ui dividing header">
          Esto no es una página válida
        </h1>
        <p>Pregunta a Lulú si es la ruta correcta...</p>
      </div>
    </div>
    )
  }
}

