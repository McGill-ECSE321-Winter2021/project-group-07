import axios from 'axios'
import Multiselect from 'vue-multiselect'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function MechanicDto(name, password, phone, emai){
    this.name = name;
    this.password=password;
    this.phone = phone;
    this.email = email;
    this.id =""
}
    export default {
     components: {
            Multiselect
        },
      data() {
      return {
        name: '',
        email:'',
        phone:'',
        password:'',
        address:'',
        mechanic:"",
        mechanics: [],
        capacities:[],
        options: [
            {name: "CarRepair"},
            {name: "Oil Change"},
            {name: "Regular Checkup"},
            {name: "CarWash"},
            {name: "TireChange"},
            {name:"Roadside Assistance"},
            {name: "Towing"},
            {name: "Car Inspection"},
            {name: "Other"}
        ],
        
        nameState: null,
        emailState:null,
        phoneState:null,
        passwordState:null,
        addressState:null,
        capabilitiesState:null,
        error: "",
      }
    },
    created: function () {
    // Initializing persons from backend
    AXIOS.get('/mechanics')
    .then(response => {
      // JSON responses are automatically parsed.
      this.mechanics = response.data
    })
    .catch(e => {
      this.error = e
    })
    },
    methods: {
        createMechanic: function (name,password,phone,email){
        AXIOS.post('/mechanic/'.concat(name), {},
        {
            params:{
                name: name,
                phone: phone,
                password: password,
                email: email,
            }}).then(response => {
                this.mechanics.push(response.data);
            })
            .catch(e => {
                this.error = e
            })

        },
        
        searchForMechanics: function(search){
            AXIOS.get('/mechanics')
            .then(response => {
                if(search.length == 0){
                    this.mechanics = response.data;
                } else {
                    this.mechanics = [];
                    var i;
                    console.log(response.data.length)
                    for(i = 0; i < response.data.length; i++){
                        var mechanic = response.data[i];
                        console.log(mechanic);
                        if(mechanic.name.includes(search)){
                            this.mechanics.push(mechanic);
                        } else if(mechanic.email.includes(search)){
                            this.mechanics.push(mechanic);
                        } else if(mechanic.phone.toString().includes(search)){
                            this.mechanics.push(mechanic);
                        }
                    }
                }
                
            })
            .catch(e => {
                this.error = e
            })
        },

      checkFormValidity() {
        const valid = this.$refs.form.checkValidity()
        this.nameState = valid
        this.emailState = valid
        this.phoneState = valid
        this.passwordState = valid 
        this.addressState = valid
        return valid
      },
      handleOk(bvModalEvt) {
        bvModalEvt.preventDefault()
        this.handleSubmit()
      },
       resetModal() {
        this.name = ''
        this.email=''
        this.phone=''
        this.password=''
        this.address=''
        this.mechanic=""
        this.capacities=""
        this.nameState = null
        this.emailState =null
        this.phoneState = null
        this.passwordState = null
        this.addressState =null
        this.capabilitiesState = null
      },
      handleSubmit() {
        if (!this.checkFormValidity()) {
          return
        }
        
        this.$nextTick(() => {
          this.$bvModal.hide('modal-prevent-closing')
        })
      }
    }
    
       
    }