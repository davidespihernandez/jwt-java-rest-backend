import * as Security from './Security'
import axios from 'axios'

const env = {
	local: {
		api: "http://localhost:8080/api"
	},
	prod: {
		api: "https://www.prod.url.com/api"
	}
};

export const local = window.location.host.match(/localhost/) || window.location.host.match(/127\.0\.0/);
export const prod = window.location.host.match(/www\.prod\.url\.com/);

export function BASE_URLS() {
	if (local) {
		return env.local;
	} else if (prod) {
		return env.prod;
    } 
}

export function get(uri) {
    return axios.get(BASE_URLS().api + uri, Security.getSecurityHeader());
}

export function post(uri, data) {
    return axios.post(BASE_URLS().api + uri, data, Security.getSecurityHeader());
}


