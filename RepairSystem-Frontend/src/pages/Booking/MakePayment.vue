<template>
    <div id="payment" style = "position: relative; left: 20px; top: 20px;">
          <h2>Booking an Appointment</h2>
          <br>

        <div class = "row" style = "position: relative; width: 100vh; left: 30px">
            <div class = "col">

			<h5>Billing Information</h5>
			
			<h7>Email</h7>
            <b-group>
            	<b-input type="text" v-model="email" :value="email" readonly>
                </b-input>
                <br>
            </b-group>
			
            <h7>Address</h7>
            <b-group>
            	<b-input type="text" v-model="address" :value="address" readonly>
                </b-input>
                <br>
            </b-group>
			
            <h7>Credit</h7>
            <b-group>
            	<b-input type="text" v-model="credit" :value="credit" readonly>
                </b-input>
                <br>
            </b-group>
            
            <h7>Debit</h7>
            <b-group>
            	<b-input type="text" v-model="debit" :value="debit" readonly>
                </b-input>
            </b-group>
           <br> 
           
			<toggle-button :height="25" :color="{checked: '#75C791', unchecked: '#75C791'}" :width="75" :labels="{checked: 'Debit', unchecked: 'Credit'}"/>
			<br> 
			
			<h7>Amount</h7>
            <b-group>
            	<b-input type="text" v-model="amount">
                </b-input>
                <br>
            </b-group>

        	<button class="button1" @click="makePayment()">
        		Make Payment
        	</button>
        
			</div>
        </div>
   </div>

</template>

<script>
import axios from 'axios'
import Vue from 'vue'
import ToggleButton from 'vue-js-toggle-button'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

Vue.use(ToggleButton)

export default {
    components: {},
    data () {
    return {
		email: '',
		address: '',
		credit: '',
		debit: '',
		customer: "",
	}
},
created: function () {
        var id = this.$route.params.userId
        AXIOS.get('/customer/'.concat(id))
        .then(response => {
		this.customer = response.data
		this.name = response.data.name
		this.email = response.data.email
		this.credit = response.data.creditHash
		this.debit = response.data.debitHash
		this.address = response.data.address
    })
    .catch(e => {
        this.error = e
        console.log(e)
    })
    },
    
    methods: {
        makePayment: function () {
        	location.replace(frontendUrl+"/customerDashboard/Overview/"+this.customer.id);
        } 
    }
}

</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

<style scoped>
.messages {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    font-size: 40px;
    color: rgb(167, 167, 167);
    font-weight: 600;
}

.btn-primary{
	width: 100px;
	height: 30px; 
    border-radius: 10px;
    margin-right:20px;  
    transform: translateY(-5px);
}

.button1 {
  background: rgb(51 41 134);
  width: 200px;
  height: 30px;
  color: white;
  border-radius: 8px;
}

.btn-primary1{
    border-radius: 10px;
    margin-right:20px;  
    border-color: rgb(51 41 134);
border-width: 3px;
color: black;
    transform: translateY(-5px);
    background: #D3D2E1;
    position:relative;
    top: 20px;
}
</style>