<template>
<div class="row no-gutters">
    <div class="col no-gutters">
        <div class="row no-gutters">
            <div class="left-top">
                <div class="rcorners">
                    &nbsp; &nbsp; &nbsp;
                    <b style="color: rgb(51 41 134); font-size: 30px; position: absolute; top: 25px; left: 65px"> Hello, </b>
                    <b style="color: #F3BE35; font-size: 30px; position: absolute; top: 25px; left: 150px "> {{this.mechanic.name}} </b>
                    <img src="../../assets/welcome-car.png" width="300px" height="150px" style="position: absolute; right: 25px">
                </div>
            </div>
        </div>

        <div class="row no-gutters">
            <div class="left-bottom">
                <img src="../../assets/appointment.png" width="50px">
                My Appointments
                <div class="container mt-3 mb-3" style="background-color:white; border-radius:30px;">
                    <table class="table table-striped tabled-bordered mydatatable" style="width: 100">
                        <thead>
                            <tr style="text-align:center;  border-radius:30px;">
                                <th> Appointment Id </th>
                                <th> Cusomter </th>
                                <th> Status </th>
                                <th> Start Time</th>
                                <th> End Time </th>
                                <th> Services </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="appointment in appointments" style="text-align:center">
                                <td v-for="mech in appointment.mechanics" v-if="mech.id == mechanic.id" style="text-align:center">
                                    {{appointment.id}}
                                </td>

                                <td v-for="mech in appointment.mechanics" v-if="mech.id == mechanic.id" style="text-align:center">

                                    <span v-for="cus in customers">
                                        <span v-if="cus.id == appointment.customer.id">
                                            {{cus.name}} ({{appointment.customer.id}})
                                        </span>
                                    </span>

                                </td>
                                <td v-for="mech in appointment.mechanics" v-if="mech.id == mechanic.id" style="text-align:center">
                                    {{appointment.status}}</td>

                                <!--timeslot-->
                                <td v-for="mech in appointment.mechanics" v-if="mech.id == mechanic.id" style="text-align:center">
                                    <span v-for="time in timeslots">
                                        <span v-if="time.id == appointment.timeSlot.id">
                                            {{time.startTime}}
                                        </span>
                                    </span>
                                </td>
                                <td v-for="mech in appointment.mechanics" v-if="mech.id == mechanic.id" style="text-align:center">

                                    <span v-for="time in timeslots">
                                        <span v-if="time.id == appointment.timeSlot.id">
                                            {{time.startTime}}
                                        </span>
                                    </span>

                                </td>

                                <td v-for="mech in appointment.mechanics" v-if="mech.id == mechanic.id" style="text-align:center">
                                    <span v-for="service in appointment.services">
                                        {{service.serviceType}}
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <h1 style="color:white"> Footer </h1>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="col no-gutters">
        <div class="rightside">
            <div class="row no-gutters">
                <div class="top">
                    <center><img src="../../assets/profile-default.png" width="100px" length="100px"></center>
                    <br>
                    <center> <b style="color: rgb(51 41 134); font-size: 20px;"> {{mechanic.name}} <br> id: {{mechanic.id}} </b> </center>
                </div>
            </div>

            <div class="row no-gutters">
                <div class="middle">
                    <br>
                    <date-pick v-model="date" :hasInputElement="false"></date-pick>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="bottom">
                    <br> <img src="../../assets/paint job 1.jpg" width="400px" length="400px">
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios';
import DatePick from 'vue-date-pick';
import 'vue-date-pick/dist/vueDatePick.css';
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: {
        'Access-Control-Allow-Origin': frontendUrl
    }
})

export default {
    components: {
        DatePick
    },
    computed: {
        userId() {
            return this.$route.params.userId
        }
    },
    data() {
        return {
            mechanic: "",
            error: "",
            appointments: [],
            mechAppointments: [],
            timeslots: [],
            customers: [],
        }
    },
    created: function () {
        var id = this.$route.params.userId
        AXIOS.get('/mechanic/'.concat(id))
            .then(response => {
                // JSON responses are automatically parsed.
                this.mechanic = response.data
                AXIOS.get('/appointment/').
                then(response => {
                    this.appointments = response.data
                    AXIOS.get('/timeslots/').
                    then(response => {
                        this.timeslots = response.data
                        AXIOS.get('/customer/').
                        then(response => {
                            this.customers = response.data
                        }).catch(e => {
                            this.error = e
                            console.log(e)
                        })
                    }).catch(e => {
                        this.error = e
                        console.log(e)
                    })

                }).catch(e => {
                    this.error = e
                    console.log(e)
                })
            })
            .catch(e => {
                this.error = e
                console.log(e)
            })
    }
}
</script>

<style scoped>
.overview {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    font-size: 40px;
    color: rgb(167, 167, 167);
    font-weight: 600;
}

.rightside {
    height: 98.5vh;
    width: 70%;
    position: absolute;
    content: "";
    right: 0px;
    background: white
}

.top {
    height: 50%;
    width: 100%;
    position: absolute;
    content: "";
    top: 20px;
}

.rcorners {
    border-radius: 25px;
    background: white;
    width: 750px;
    height: 150px;
}

.rcorners2 {
    border-radius: 25px;
    background: white;
    width: 400px;
    height: 300px;
    border: 3px solid;
    border-color: #D3D2E1;
    padding: 25px;
}

.middle {
    height: 30%;
    width: 90%;
    position: absolute;
    content: "";
    left: 20px;
    top: 200px;
}

.bottom {
    height: 30%;
    width: 80%;
    position: absolute;
    content: "";
    left: 20px;
    top: 500px;
}

.left-top {

    position: absolute;
    content: "";
    top: 50px;
    left: 70px
}

.left-bottom {

    position: absolute;
    content: "";
    left: 70px;
    top: 250px;
    width: 80vh;
}
</style>
