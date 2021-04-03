import axios from 'axios'
import Multiselect from 'vue-multiselect'
import 'vue-date-pick/dist/vueDatePick.css';
import DatePick from 'vue-date-pick';

var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: {
        'Access-Control-Allow-Origin': frontendUrl
    }
})

function TimeSlotDto(startTime, endTime) {
    this.startTime = endTime;
    this.endTime = endTime;
    this.id = "";
    this.mechanics = [];
    this.appointments = [];
}

export default {

    components: {
        Multiselect,
        DatePick
    },

    data() {
        return {
            admin: "",
            modalShow: false,
            startTime: '',
            endTime: '',
            id: '',
            timeslots: [],
            mechanics: [],
            appointments: [],
            appointment: "",

            startTimeState: null,
            endTimeState: null,

            error: "",
        }
    },

    created: function () {
        var id = this.$route.params.userId
        AXIOS.get('/admin/'.concat(id))
            .then(response => {
                this.admin = response.data
                // JSON responses are automatically parsed.
                AXIOS.get('/timeslots/').
                then(response => {
                    this.timeslots = response.data
                    AXIOS.get('/appointment').
                    then(response => {
                        this.appointments = response.data
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
    },

    methods: {

        /**
         * Creating a timeslot and posting it in the backend 
         */
        createTimeSlot: function (startTime, endTime) {
            console.log(startTime);
            AXIOS.post('/timeslot/'.concat(startTime + "?endTime=" + endTime), {}, {})
            .then(response => {
					this.timeslots = response.data;
					console.log(this.timeslots)
                })
                .catch(e => {
                    this.error = e
                })
        },

        handleSubmit() {
            if (!this.checkFormValidity()) {
                return
            }

            this.$nextTick(() => {
                this.$bvModal.hide('modal-prevent-closing')
            })
        },
        resetModal() {
            this.modalShow= false
            this.startTime= ''
            this.endTime= ''
            this.id= ''
            this.timeslots= []
            this.mechanics= []
            this.appointments= []
            this.startTimeState=null
            this.endTimeState= null
            this.error= ""
        }
    }

}