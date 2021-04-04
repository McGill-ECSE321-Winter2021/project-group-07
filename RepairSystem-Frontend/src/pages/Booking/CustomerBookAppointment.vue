<template>
    <div id="booking-calendar" style = "position: relative; left: 20px; top: 20px;">
        <h1>{{customer.name}}</h1>
          <h2>Booking an Appointment</h2>
          <br>
        <date-pick style = "width: 90%"
            v-model="date"
            :hasInputElement="false"
            :pickTime="true"
            :format="'YYYY-MM-DD-HH:mm'"
            :displayFormat="'YYYY.MM.DD H:mm A'"
        ></date-pick>

        <h2>{{date}}</h2>
        <br>

        <div class = "row" style = "position: relative; width: 100vh; left: 30px">
            <div class = "col">
        	<form>
        	    <label class="typo__label"> Service </label>
            	<multiselect v-model="service" :state="serviceState" :options="availableServices" :multiple="false" :close-on-select="true" :clear-on-select="false" :preserve-search="true" placeholder="Pick a Service" label="serviceType" track-by="serviceType" :preselect-first="true">
                	<template slot="selection" slot-scope="{ values, search, isOpen }">
                    	<span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span>
                    </template>
                </multiselect>
        	</form>

                    	<form>
                            <br>
        	    <label class="typo__label"> Car </label>
            	<multiselect v-model="car" :state="carState" :options="availableCars" :multiple="false" :close-on-select="true" :clear-on-select="false" :preserve-search="true" placeholder="Pick a Car" label="carType" track-by="carType" :preselect-first="true">
                	<template slot="selection" slot-scope="{ values, search, isOpen }">
                    	<span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span>
                    </template>
                </multiselect>
        	</form>
        	        	<form>
            	<br> <label> Note </label>
            	<input type="text" v-model="note" value="">
        	</form>

                    <br> 
        <button class="button1" @click="createAppointment(date, value, service, note, car)">
        	Book Appointment
        </button>
        
            </div>
            <div class = "col">
                <div style = "width: 20 px">
        	<form>
        	    <label class="typo__label"> Mechanic </label>
            	<multiselect v-model="mechanic" :state="mechanicState" :options="availableMechanics" :multiple="false" :close-on-select="true" :clear-on-select="false" :preserve-search="true" placeholder="Pick a Mechanic" label="name" track-by="name" :preselect-first="true">
                	<template slot="selection" slot-scope="{ values, search, isOpen }">
                    	<span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span>
                    </template>
                </multiselect>
        	</form>
            <form>
                <br>
                <br>
                <b-button v-b-modal.modal-prevent-closing class="btn-primary1"> Add Car <img class="img-add" src="../../assets/Admin/plus.png"width="20px" /> </b-button>

            <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="Add Car"
            @show="resetModal"
            @hidden="resetModal"
            @ok="createCar(carType, winterTires, numOfKm)"
            >
            <b-form ref="form" @submit.stop.prevent="handleSubmit">

                <b-form-group
                label="Car Type"
                label-for="name-input"
                invalid-feedback="Car Type is required"
                :state="CarTypeState"
                >
                <b-form-input
                    id="CarType-input"
                    v-model="carType"
                    :state="CarTypeState"
                    required
                ></b-form-input>
                </b-form-group>

               <b-form-group
                label="Winter Tires"
                label-for="winterTires-input"
                invalid-feedback="State of winter tires is required"
                :state="winterTiresState"
                >
                <b-form-input
                    id="winterTires-input"
                    v-model="winterTires"
                    :state="winterTiresState"
                    required
                ></b-form-input>
                </b-form-group> 

                <b-form-group
                label="Number of Kilometers"
                label-for="numOfKm-input"
                invalid-feedback="Number of Kilometers is required"
                :state="numOfKmState"
                >
                <b-form-input
                    id="numOfKm-input"
                    v-model="numOfKm"
                    :state="numOfKmState"
                    required
                ></b-form-input>
                </b-form-group>

            </b-form>
            </b-modal>
            </form>

</div>
            </div>
            
        </div>	

        
         

   </div>

</template>

<script>
import Multiselect from 'vue-multiselect'
import DatePick from 'vue-date-pick';
import 'vue-date-pick/dist/vueDatePick.css';
import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

const currentDate = new Date();

export default {
    components: {DatePick, Multiselect},
    data: () => ({
        date: '',
        timeslot: "",
        errorTimeSlot:"",
        availableServices:[],
        customer: "",
	    error: "",
	    availableMechanics: [],
	    mechanic: '',
	    service: '',
	    car: '',
	    availableCars: []
    }),
    created: function () {
        var id = this.$route.params.userId
        
        AXIOS.get('/customer/'.concat(id))
        .then(response => {
        // JSON responses are automatically parsed.
        this.customer = response.data
    })
    .catch(e => {
        this.error = e
        console.log(e)
    })
    
    AXIOS.get('/mechanics')
        .then(response => {
            this.availableMechanics = response.data;
            
            AXIOS.get('/services')
        		.then(response => {
            		this.availableServices = response.data;
            		
            		AXIOS.get('/cars/'.concat(id), {}, {})
        				.then(response => {
            				this.availableCars = response.data
            			})
            			.catch(e => {
                			var errorMsg = e.response.data.message
            			})
            		
        		})
        		.catch(e => {
            		this.error = e
        		})
        })
        .catch(e => {
            this.error = e
        })
            
    },

    methods: {
        isPastDate: function (date) {
            return date < new Date()
        },
        
        createAppointment: function(startDate, mechanic, service, note, car) {
        
        	if (car == null) {
        		var vehicleId = this.car.id
        	} else {
        		var vehicleId = car.id
        	}
        
            var endDate = startDate.split("-");
            var min_sec_array = endDate[3].split(":");
            var hours = min_sec_array[0];
            hours++;
            if (hours < 10) {
            	min_sec_array[0] = '0'.concat(hours);
            } else {
            	min_sec_array[0] = hours;
            }
     
            min_sec_array = min_sec_array.join(':');
			endDate[3] = min_sec_array;
			endDate = endDate.join('-');

            //console.log("Old date: " + startDate);
            //console.log("New date: " + endDate);
            //console.log('/timeslot/'.concat(startDate + "?endTime="+endDate))

            AXIOS.post('/timeslot/'.concat(startDate + "?endTime="+endDate), {}, {})
            .then(response => {
            // JSON responses are automatically parsed.
                this.timeslot  = response.data
                
                console.log(response.data)
                console.log('/appointment/'.concat(this.customer.id + "?timeSlotId="+this.timeslot.id + "&carId="+vehicleId + "&services="+this.service.serviceType + "&note="+this.note))
            	
            	AXIOS.post('/appointment/'.concat(this.customer.id + "?timeSlotId="+this.timeslot.id + "&carId="+this.car.id + "&services="+this.service.serviceType + "&note="+this.note), {}, {})
        			.then(response => {
  					// open pop up? or redirect to main page?
            	})
            	.catch(e => {
                	var errorMsg = e.response.data.message
                	console.log(errorMsg)
                	this.errorAppointment = errorMsg
            	})
  
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorTimeSlot = errorMsg
            })
            
        }, 
            
        createCar: function(carType, winterTires, numOfKm) {
        	AXIOS.post('/car/'.concat(this.customer.id + "?carType=" + carType + "&winterTires=" + winterTires + "&numOfKilometers=" + numOfKm), {}, {})
            .then(response => {
            // JSON responses are automatically parsed.
            	this.car = response.data
                console.log(response.data)
            })
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