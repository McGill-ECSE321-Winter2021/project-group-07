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
        	<form>
        	    <label class="typo__label"> Service </label>
            	<multiselect v-model="service" :state="serviceState" :options="availableServices" :multiple="false" :close-on-select="true" :clear-on-select="false" :preserve-search="true" placeholder="Pick a Service" label="name" track-by="name" :preselect-first="true">
                	<template slot="selection" slot-scope="{ services, search, isOpen }">
                    	<span class="multiselect__single" v-if="services.length &amp;&amp; !isOpen">{{ services.length }} options selected</span>
                    </template>
                </multiselect>
        	</form>
        	<form>
            	<label> Note </label>
            	<input type="text" v-model="note" value="">
        	</form>
        	<form>
        	    <label class="typo__label"> Mechanic </label>
            	<multiselect v-model="value" :state="mechanicState" :options="availableMechanics" :multiple="false" :close-on-select="true" :clear-on-select="false" :preserve-search="true" placeholder="Pick a Mechanic" label="name" track-by="name" :preselect-first="true">
                	<template slot="selection" slot-scope="{ values, search, isOpen }">
                    	<span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span>
                    </template>
                </multiselect>
        	</form>
        <br> 
        <button class="button1" @click="createAppointment(date, value, service, note)">
        	Book Appointment
        </button>
        
         <b-button v-b-modal.modal-prevent-closing class="btn-primary"> Add Car <img class="img-add" src="../../assets/Admin/plus.png"/> </b-button>

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
	    car: '',
	    availableMechanics: [],
	    value: [],
	    service: []
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
        })
        .catch(e => {
            this.error = e
        })
            
    AXIOS.get('/services')
        .then(response => {
            this.availableServices = response.data;
        })
        .catch(e => {
            this.error = e
        })
    },

    methods: {
        isPastDate: function (date) {
            return date < new Date()
        },
        
        createAppointment: function(startDate, mechanic, service, note) {
        
            var endDate = startDate.split("-");
            var min_sec_array = endDate[3].split(":");
            var hours = min_sec_array[0];
            hours++;
            min_sec_array[0] = hours;
            min_sec_array = min_sec_array.join(':');
			endDate[3] = min_sec_array;
			endDate = endDate.join('-');

            //console.log("Old date: " + startDate);
            //console.log("New date: " + endDate);

            AXIOS.post('/timeslot/'.concat(startDate + "?endTime="+endDate), {}, {})
            .then(response => {
            // JSON responses are automatically parsed.
                this.timeslot  = response.data
                console.log(response.data)
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorTimeSlot = errorMsg
            })
            
            AXIOS.post('/appointment/'.concat(this.customer.id + "?timeSlotId="+this.timeslot.id + "?carId="+this.car.id, + "?services="+service + "?note="+note), {}, {})
        	.then(response => {
            	// JSON responses are automatically parsed.
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorAppointment = errorMsg
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
</style>