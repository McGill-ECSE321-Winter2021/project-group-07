<template>
    <div id="booking-calendar">
        <h1>{{customer.name}}</h1>
        <h2>Booking an Appointment</h2>
        <date-pick 
            v-model="date"
            :hasInputElement="false"
            :pickTime="true"
            :format="'YYYY-MM-DD-HH:mm'"
            :displayFormat="'YYYY.MM.DD H:mm A'"
        ></date-pick>

        <h2>{{date}}</h2>

        <form>
            <label> Service </label>
            <input type="text" v-model="service" value="">
        </form>

        <button class="Book Appointment" @click="createTimeSlot(date)">
        Book Appointment
        </button>


   </div>

</template>

<script>

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
    components: {DatePick},
    data: () => ({
        date: '',
        timeslot: "",
        errorTimeSlot:"",
        services:['oil change', 'maintenance'],
        customer: "",
	    error: ""
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
    },

        methods: {
        isPastDate: function (date) {
            return date < new Date()
        },
        createTimeSlot: function(date) {
        
            var date_array = date.split("-");
            var min_sec_array = date_array[3].split(":");
            var hours = min_sec_array[0];
            hours++;

            var newDate = new Date();
            newDate.setMinutes(min_sec_array[1]);
            newDate.setHours(hours);
            newDate.setDate(date_array[2]);
            newDate.setMonth(date_array[1]);
            newDate.setYear(date_array[0]);

            console.log(newDate);

            AXIOS.post('/timeSlot/'.concat(date + "?endTime="+newDate), {}, {})
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

            console.log("FINISHED METHOD");
        }
    }

}
</script>

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
</style>