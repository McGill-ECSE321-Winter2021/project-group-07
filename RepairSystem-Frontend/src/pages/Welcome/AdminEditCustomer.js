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
    // Initializing customers from backend
    AXIOS.get('/customer')
    .then(response => {
      // JSON responses are automatically parsed.
      this.customers = response.data
    })
    .catch(e => {
      this.error = e
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