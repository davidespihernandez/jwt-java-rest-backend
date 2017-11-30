import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'semantic-ui-css/semantic.min.css';
import App from './App';
import { Router } from 'react-router-dom'
import registerServiceWorker from './registerServiceWorker';
import history from './history'
import { Provider } from 'react-redux'
import { createStore } from 'redux'
import crazyApp from './reducers'

let store = createStore(crazyApp)

ReactDOM.render((
    <Provider store={store}>
        <Router history={history}>
            <App />
        </Router>
    </Provider>
), document.getElementById('root'));
registerServiceWorker();
