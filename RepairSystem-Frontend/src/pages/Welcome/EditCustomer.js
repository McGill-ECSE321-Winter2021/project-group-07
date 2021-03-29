import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function CustomerDto(email, password, name, phone, address, credit, debit) {
	this.name = name;
	this.password = password;
	this.phone = phone;
	this.email = email;
	this.address = address;
	this.creditHash = credit;
	this.debitHash = debit;
	this.appointments = "";
	this.id = "";
	this.cars = "";
	this.lastActive = "";
}

export default {
    name: 'editCustomer',
    data () {
      return {
          customer: "",
          error: "",
      }
    },
    methods: {
        editCustomer: function (oldEmail, password, phone, creditHash, debitHash, address){
            AXIOS.put('/customer/editAllCustomerCredentials/'.concat(oldEmail), {},
            
	  {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.customer = response.data
      })
      .catch(e => {
        this.error = e
      })
  

        }
    }
  }