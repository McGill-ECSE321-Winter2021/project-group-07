<!-- Team Members -->
<template>
  
    <div class="profile">
       <nav class="navbar">
            <span class="navbar-brand mb-0 h1">
                <div class="row">
                    <h3 class="title-header"> <b> Team Members </b> </h3>
                    <b-button v-b-modal.modal-prevent-closing class="btn btn-primary"> Add New Member <img class="img-add" src="../../assets/Admin/plus.png"/> </b-button>

                        <b-modal
                        id="modal-prevent-closing"
                        ref="modal"
                        title="Add New Team Member"
                        @show="resetModal"
                        @hidden="resetModal"
                        @ok="createMechanic(name,password,phone,email,address,allCapabilities)"
                        >
                        <b-form ref="form" @submit.stop.prevent="handleSubmit">

                            <b-form-group
                            label="Name"
                            label-for="name-input"
                            invalid-feedback="Name is required"
                            :state="nameState"
                            >
                            <b-form-input
                                id="name-input"
                                v-model="name"
                                :state="nameState"
                                required
                            ></b-form-input>
                            </b-form-group>

                           <b-form-group
                            label="Email"
                            label-for="email-input"
                            invalid-feedback="email is required"
                            :state="emailState"
                            >
                            <b-form-input
                                id="email-input"
                                v-model="email"
                                :state="emailState"
                                required
                            ></b-form-input>
                            </b-form-group> 

                            <b-form-group
                            label="Password"
                            label-for="password-input"
                            invalid-feedback="password is required"
                            :state="passwordState"
                            >
                            <b-form-input
                                id="password-input"
                                v-model="password"
                                :state="passwordState"
                                required
                            ></b-form-input>
                            </b-form-group> 

                            <b-form-group
                            label="Phone Number"
                            label-for="phone-input"
                            invalid-feedback="Phone number is required"
                            :state="phoneState"
                            >
                            <b-form-input
                                id="Phone Number-input"
                                v-model="phone"
                                :state="phoneState"
                                required
                            ></b-form-input>
                            </b-form-group> 

                            <b-form-group
                            label="Residence Address"
                            label-for="address-input"
                            invalid-feedback="Address is required"
                            :state="addressState"
                            >
                            <b-form-input
                                id="address-input"
                                v-model="address"
                                :state="addressState"
                                required
                            ></b-form-input>
                            </b-form-group>  
                           

                        </b-form>
                         <label class="typo__label"> Capabilities </label>
  <multiselect v-model="allCapabilities" :state="capabilitiesState" :options="options" :multiple="true" :close-on-select="false" :clear-on-select="false" :preserve-search="true" placeholder="Pick some" label="name" track-by="name" :preselect-first="true">
    <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single" v-if="capacities.length &amp;&amp; !isOpen">{{ values.length }} options selected</span></template>
  </multiselect>
                        </b-modal>
                    <button type="button" class="btn btn-secondary"> Search  <img class="img-add" src="../../assets/Admin/search.png"/></button>
                
                </div>
            </span>
        </nav>

    
     
  <!--      <div class="line">
        </div>
  
        The Table containing all the mechanic information 
        <div class="container" style="font-family: Roboto">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name </th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Capabilities</th>
                    <th> </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>John Cena</td>
                    <td>john@example.com</td>
                    <td>514875689</td>
                    <td> 312 Boulevard Nixon, Montreal, Qc </td>
                    <td> CarRepair, OilChange, RegularCheckup, CarWash </td>
                    <td> <button type="button"> <img class="img-add" src="../../assets/Admin/settings.png"/> </button></td>
                </tr>
                <tr>
                    <td>Mary-Kate Olsen </td>
                    <td>mary@example.com</td>
                    <td>5146789023</td>
                    <td> 432 Ariel Street, Montreal, Qc </td>
                    <td> CarRepair, OilChange, RegularCheckup, RoadSide Assistance </td>
                    <td> <button type="button"> <img class="img-add" src="../../assets/Admin/settings.png"/> </button></td>
                </tr>
                <tr>
                    <td>Harry Styles</td>
                    <td>Harry@example.com</td>
                    <td>438987612</td>
                    <td> 432 Sad Street, Montreal, Qc </td>
                    <td> Towing, OilChange, RegularCheckup, RoadSide Assistance </td>
                     <td> <button type="button"> <img class="img-add" src="../../assets/Admin/settings.png"/> </button></td>
                </tr>
                </tbody>
            </table>
        </div>-->
        
    </div>
</template>

<script>
import axios from 'axios'
import Multiselect from 'vue-multiselect'
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
      }
    },
    methods: {
        createMechanic: function (name,password,phone,email,address,allCapabilities){
        AXIOS.post('/mechanic/'.concat(name), {},
        {
            params:{
                name: name,
                phone: phone,
                password: password,
                email: email,
            }}).then(response => {
                this.mechanic = response.data;
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
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>

#modal-prevent-closing{
      color:rgb(51 41 134)
}
.profile {
    height: 100%;
    width: 100%;
    font-family: Roboto;
    /**color: rgb(167, 167, 167);   */
    color:rgb(51 41 134)
   
}

.title-header{
    margin-left: 40px;
    margin-right:20px;  
}

.btn-primary{
    border-radius: 10px;
    margin-right:20px;  
    background-color:  #F3BE35;
    border-color:  #D3D2E1;
    transform: translateY(-5px);
    
}

.btn-secondary
{
    border-radius: 10px;
    border-width: 2px;
    background-color:  #D3D2E1;
    border-color: #F3BE35; 
    transform: translateY(-5px);
}

.line
{
    height:2px;
    background-color:rgba(64, 57, 134, 1)
}
.img-add{
    max-height:20px;
    transform: translateY(-1px);
}
</style>