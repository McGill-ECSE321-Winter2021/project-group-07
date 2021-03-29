import Multiselect from 'vue-multiselect'
import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function MechanicDto(name, password, phone, email, address, allCapabilities){
    this.name = name;
    this.password=password;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.capabilities = allCapacities;
    this.id =""
}

 export default {
	 name:"AddAddTeamMember",
	components: {
            Multiselect
        },
	 data() {
		 return{
			 mechanic: "",
			 error:""
		 }
	 },

	 methods: {
		createMechanic: function (name,password,phone,email,address,allCapabilities){
        AXIOS.post('/mechanic/'.concat(name),  {},
        {
            params:{
                name: name,
                phone: phone,
                password: password,
                email: email,
			}})
			.then(response => {
                this.mechanic = response.data;
            })
            .catch(e => {
                this.error = e
            })

		}
	}
	 }