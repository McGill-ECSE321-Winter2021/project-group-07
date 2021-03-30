<template>
    <div id="booking-calendar">
        <h2>Booking an Appointment</h2>
        <date-pick 
            v-model="date"
            :hasInputElement="false"
            :pickTime="true"
            :format="'YYYY-MM-DD HH:mm'"
            :displayFormat="'YYYY.MM.DD H:mm A'"
        ></date-pick>

        <h2>Thomas is KING.</h2>

        <h2>{{date}}</h2>

        <form>
            <label> Service </label>
            <input type="text" v-model="service" value="">
        </form>
        <button class="Book Appointment" @click="createTimeSlot(date, service)">
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
        errorTimeSlot:""
    }),

        methods: {
        isPastDate: function (date) {
            return date < new Date()
        },
        createTimeSlot: function(date) {

            console.log("Starting function")

            var minute = date.getMinutes();
            console.log(minute)
            var hour = date.prototype.getHours();
            var day = date.prototype.getDay();
            var month = date.prototype.getMonth();
            var year = date.prototype.getYear();
            hour++;
            var newDate = new Date();

            console.log(newDate)

            newDate.prototype.setMinutes(minute);
            newDate.prototype.setHours(hour);
            newDate.prototype.setDay(day);
            newDate.prototype.setMonth(month);
            newDate.prototype.setYear(year);

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