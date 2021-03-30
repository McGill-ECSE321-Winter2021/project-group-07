import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: "Landing",
  data () {
    return {
	  error: ""
    }
  },
  created: function () {
    // Initializing persons from backend
	AXIOS.get('/admins')
    .then(response => {
	  // JSON responses are automatically parsed.
		if(response.data.length == 0){
			console.log("test");
			AXIOS.post('/admin/'.concat("admin"), {}, 
	  		{params: {
				password: "admin",
				phone: "1234567",
				email: "admin@gmail.com"}})
			.then(response => {})
    		.catch(e => {
      			this.error = e
			})
	  	}
    })
    .catch(e => {
      this.error = e
    })
    }
}
