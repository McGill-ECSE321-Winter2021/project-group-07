import axios from 'axios';
import DatePick from 'vue-date-pick';
import 'vue-date-pick/dist/vueDatePick.css';

var config = require('../../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function AdministrativeAssistantDto(name, password,phone, email)
{
	this.name = name;
	this.password = password,
	this.phone = phone;
	this.email = email;
	this.id = ""
}

export default {
	components: {DatePick},
	
/*	data(){
		name: '',
        email:'',
        phone:'',
		password:'',
		admin: '',

	}*/
};