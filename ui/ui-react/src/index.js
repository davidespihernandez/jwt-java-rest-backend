import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'semantic-ui-css/semantic.min.css';
import App from './App';
import { Router } from 'react-router-dom'
import registerServiceWorker from './registerServiceWorker';
import history from './history'
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux'
import crazyApp from './reducers'
import thunkMiddleware from 'redux-thunk';

let store = createStore(crazyApp,
    applyMiddleware(thunkMiddleware)
    );

ReactDOM.render((
    <Provider store={store}>
        <Router history={history}>
            <App />
        </Router>
    </Provider>
), document.getElementById('root'));
registerServiceWorker();
