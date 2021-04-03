<!-- Customers -->
<template>
  
    <div class="profile">
       <nav class="navbar">
            <span class="navbar-brand mb-0 h1">
                <div class="row">
                      <form>
                        <input type="text" v-model="s" value="" class="search-input" placeholder="  Search">
                    </form>

                  <button class="search-btn" @click="searchForCustomers(s)"> <img class="img-add" src="../../assets/Admin/search.png"/> </button>          
                  <b-button v-b-modal.modal-prevent-closing class="btn-primary"> Add Customer <img class="img-add" src="../../assets/Admin/plus.png"/> </b-button>

                        <b-modal
                        id="modal-prevent-closing"
                        ref="modal"
                        title="Add New Customer"
                        @show="resetModal"
                        @hidden="resetModal"
                        @ok="createCustomer(name,password,phone,email,credit,debit, address)"
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

                             <b-form-group
                            label="Credit Card"
                            label-for="CreditCard"
                            >
                            <b-form-input
                                id="credit-input"
                                v-model="credit"
                                :state="creditState"
                            ></b-form-input>
                            </b-form-group>  

                             <b-form-group
                            label="Debit Card"
                            label-for="DebitCard"
                            >
                            <b-form-input
                                id="debit-input"
                                v-model="debit"
                                :state="debitState"
                            ></b-form-input>
                            </b-form-group>  
                        </b-form>
                        
                        </b-modal>
                    
                </div>
            </span>
        </nav>

    
     
  <!--
        The Table containing all the mechanic information#D3D2E1 --> 
        <div class="container mt-3 mb-3" style="background-color:white; border-radius:30px;">
          <table class = "table table-striped tabled-bordered mydatatable" style="width: 100">
              <thead>
                    <tr style="text-align:center;  border-radius:30px;">
                        <th> ID </th>
                        <th> Name </th>
                        <th>Email</th>
                        <th>Phone Number </th>
                        <th>Address</th>
                   <!--     <th> Credit Card </th>
                        <th> Debit Card </th>-->
                        <th> Actions </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="customer in customers" style="text-align:center">
                        <td> {{customer.id}} </td>
                        <td>{{ customer.name }}</td>
                        <td>{{ customer.email }}</td>
                        <td>{{customer.phone }}</td>
                        <td>{{ customer.address  }}</td>
                   <!--     <td>{{ customer.creditHash}} </td>
                        <td>{{ customer.debitHash}} </td>-->
                        <td> 
                        <button class="btn-edit" @click=" modalShow =!modalShow; fillCredentials(customer)"> <img  class="img-add" src="../../assets/Admin/edit.png"/>  </button>
                       <button class="btn-remove" @click="removeCustomer(customer.id)"> <img  class="img-add" src="../../assets/Admin/delete.png"/>  </button> 
                        
                        <b-modal
                        v-model="modalShow"
                        title="Edit Profile"
                        id="modal-scoped"
                        >
                        <b-form ref="form" @submit.stop.prevent="handleSubmit">
                           
                            <b-form-group
                            label="Name"
                            label-for="editName-input"
                            invalid-feedback="Name is required"
                            :state="editEmailState"
                            >
                            <b-form-input
                                id="editName"
                                type="text"
                                v-model="editName"
                                name="editName"
                                :value="editName"
                            >
                            </b-form-input>
                            </b-form-group>

                              <b-form-group
                            label="Email"
                            label-for="editEmail-input"
                            invalid-feedback="Email is required"
                            :state="editEmailState"
                            >
                            <b-form-input
                                id="editEmail"
                                type="text"
                                v-model="editEmail"
                                name="editEmail"
                                :value="editEmail"
                            >
                            </b-form-input>
                            </b-form-group> 

                            <b-form-group
                            label="Phone"
                            label-for="editPhone-input"
                            invalid-feedback="Phone is required"
                            :state="editPhoneState"
                            >
                            <b-form-input
                               id="editPhone"
                               v-model="editPhone"
                               type="text"
                               name="editPhone"
                               :value="editPhone"
                            > 
                            </b-form-input>
                            </b-form-group>

                             <b-form-group
                            label="Password"
                            label-for="editPassword-input"
                            invalid-feedback="Password is required"
                            :state="editPhoneState"
                            >
                            <b-form-input
                               id="editPassword"
                               v-model="editPassword"
                               type="text"
                               name="editPassword"
                               :value="editPassword"
                            > 
                            </b-form-input>
                            </b-form-group>

                            
                             <b-form-group
                            label="Residence "
                            :state="editResidenceState"
                            >
                            <b-form-input
                               id="editPassword"
                               v-model="editResidence"
                               type="text"
                               name="editPassword"
                               :value="editResidence"
                            > 
                            </b-form-input>
                            </b-form-group>

                            
                             <b-form-group
                            label="Credit Card"
                            label-for="editCredit-input"
                            invalid-feedback="Credit is required"
                            :state="editCreditState"
                            >
                            <b-form-input
                               id="editCredit"
                               v-model="editCredit"
                               type="text"
                               name="editCredit"
                               :value="editCredit"
                            > 
                            </b-form-input>
                            </b-form-group>

                           <b-form-group
                            label="Debit Card"
                            label-for="editDebit-input"
                            invalid-feedback="Debit is required"
                            :state="editDebitState"
                            >
                            <b-form-input
                               id="editDebit"
                               v-model="editDebit"
                               type="text"
                               name="editDebit"
                               :value="editDebit"
                            > 
                            </b-form-input>
                            </b-form-group>
   
                            </b-form>
                            <template #modal-footer="{Save, Cancel}">
                              
                                <!-- Emulate built in modal footer ok and cancel button actions -->
                                <b-button size="sm" variant="success" @click=" editCustomer(editEmail, editName, editPassword, editPhone, editCredit, editDebit, editResidence)"> Save </b-button>
                                <b-button size="sm" variant="danger" @click="modalShow =!modalShow">Cancel</b-button> 
      
                            </template>
                        </b-modal> 

                             
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <h1 style="color:white"> Footer </h1>
                </tfoot>
            </table>
        </div>
        
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


 /**For edit profile  */


function CustomerDto(name, password, phone, email, credit, debit, address){
    this.name = name;
    this.password = password;
    this.phone = phone;
    this.email = email;
    this.credit = credit;
    this.debit = debit;
    this.address= address;
    this.lastDate= "";
    this.id ="";
}
    export default {

     components: {
            Multiselect
      },

      data() {
      return {
        modalShow:false,
        name: '',
        email:'',
        phone:'',
        password:'',
        address:'',
        credit:'',
        debit:'',
        customer:"",
        lastDate:"",
       	customers: [],
       admin: "",
        nameState: null,
        emailState:null,
        phoneState:null,
        passwordState:null,
        addressState:null,
        creditState: null,
        debitState : null,
       
        error: "",

        editName : "",
        editEmail : " ",
        editPhone : " ",
        editPassword : " ", 
        editResidence: " ",
        editCredit: " ",
        editDebit:" ",
      }
    },


    created: function () {
    var id = this.$route.params.userId
    AXIOS.get('/admin/'.concat(id))
    .then(response => {
        this.admin = response.data
        // JSON responses are automatically parsed.
        AXIOS.get('/customer').
        then(response => {
            this.customers = response.data
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
         * Creating a customer and posting it in the backend 
         */
        createCustomer: function (name,password,phone,email,credit, debit,address,){
           console.log(credit);
          AXIOS.post('/customer/'.concat(name+"?="+credit), {},
            {  
                params:{
                  name: name,
                  phone: phone,
                  password: password,
                  email: email,
                  credit: credit,
                  debit: debit,
                  address: address,
                }
            }).then(response => {
                 
              this.customers.push(response.data);
            })
            .catch(e => {
                    this.error = e
                })

            },
            
        
        editCustomer : function(email, name, password, phone, credit, debit, address)
        {
          console.log(email);
          console.log(name);
          console.log(password);
          console.log(phone);
          console.log(credit);
          console.log(debit);
          console.log(address);
          AXIOS.put('/customer/'.concat(email+"?newName="+name+"&newPassword="+password+"&newPhone="+phone+"&newCredit="+credit+"&newDebit="+debit+"&newAddress="+address),{},{})
          .then(response => {
            this.customer = response.data;
            location.reload();
          }).catch(e => {
            this.error = e;
          })
        },

        /** To AutoComplete the Edit Profile Modal */
        fillCredentials : function(row)
        {
            
          this.editName = row.name;
          this.editEmail = row.email;
          this.editPhone = row.phone;
          this.editPassword = row.password;
          this.editResidence = row.address;
          AXIOS.get('/customer/'.concat(row.id), {}, {})
          .then(response => {
            this.customer = response.data;
            this.editCredit = this.customer.creditHash;
            console.log(this.customer.debitHash);
            this.editDebit =this.customer.debitHash;
          }).
          catch(e=>{
            this.error =e;
          })
        
         
        },
        removeCustomer: function(id){
          console.log("entered removeCustomer function")
          console.log("id: "+ id)
          AXIOS.delete('/customer/'.concat(id), {}, {})
            .then(response => {
              console.log(response)
              location.reload();
            })
            .catch(e => {
              this.error = e;
            })
        },

        searchForCustomers: function(search){
            AXIOS.get('/customer')
            .then(response => {
                if(search.length == 0){
                    this.customers = response.data;
                } else {
                    this.customers = [];
                    var i;
                    for(i = 0; i < response.data.length; i++){
                        var customer = response.data[i];
                        if(customer.name.includes(search)){
                            this.customers.push(customer);
                        } else if(customer.email.includes(search)){
                            this.customers.push(customer);
                        } else if(customer.phone.toString().includes(search)){
                            this.mechanics.push(customer);
                        }else if (customer.address.includes(search))
                        {
                          this.customers.push(customer);
                        }else if (customer.creditHash.includes(search))
                        {
                          this.customers.push(customer);
                        }else if (customer.debitHash.includes(search))
                        {
                          this.customers.push(customer);
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
        this.customer=""
        this.credit =" "
        this.debit = " "
        this.nameState = null
        this.emailState =null
        this.phoneState = null
        this.passwordState = null
        this.addressState =null
        this.creditState = null
        this.debitState = null
       
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


<style scoped>
.profile {
    height: 100%;
    width: 100%;
    font-family: Roboto;
    /**color: rgb(167, 167, 167);   */
    color:rgb(51 41 134);
    background:  #D3D2E1;
}

.navbar{
    color:rgb(51 41 134)
}
.title-header{
    margin-left: 40px;
    margin-right:20px;  
}

.btn-primary{
    border-radius: 10px;
    margin-right:20px;  
    transform: translateY(-5px);
    
}

.btn-edit{
    background-color: #D3D2E1;
    border-color:transparent;
    border-radius:10px;
}
.btn-secondary
{
    border-radius: 10px;
    border-width: 2px;
    background: #5430be;
    border-color: transparent;
    transform: translateY(-5px);
}

.search-input {
    margin-left: 80px;
    border-radius: 20px;
    background-color: white;
    width: 400px;
    border-color: transparent;
}
.search-btn{
    border-radius: 20px;
    border-color: transparent;
    background-color: transparent;
    transform: translate(-35px,-1px);
}

.btn-remove{
    border-color: #5430be;
    background-color: transparent;
    border-radius:10px;
    border-width:2px;

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

.table
{
    border-radius: 30px;
    color:#111B47;

}

</style>