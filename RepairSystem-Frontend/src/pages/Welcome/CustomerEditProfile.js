import axios from 'axios'
import DatePick from 'vue-date-pick';
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function CustomerDto(email, password, name, phone, address, credit, debit) {
	this.name = name;
	this.password = password;
	this.phone = phone;
	this.email = email;
	this.address = address;
	this.credit = credit;
	this.debit = debit;
	this.appointments = "";
	this.id = "";
	this.cars = "";
	this.lastActive = "";
}

export default {
    components: {DatePick},
    data () {
    return {
	  name: '',
		password: '',
		phone: '',
		email: '',
		address: '',
		credit: '',
		debit: '',
		customer: "",
		customers: [],
		error: "",

		 nameState: null,
        emailState:null,
        phoneState:null,
        passwordState:null,
        addressState:null,
        creditState: null,
        debitState : null,
		  
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
			editCustomers : function(oldEmail, newPassword, newPhone, newCredit, newDebit, newAddress)
			{
				AXIOS.put('/customer/editAllCustomerCredentials/'.concat(oldEmail + "?newPassword=" + newPassword + "?newPhone=" + newPhone + "?newCredit=" + newCredit + "?newDebit=" + newDebit + "?newAddress=" + newAddress), {}, 
				{})
				.then(response => {
					this.customer = response.data;
					location.reload();
				}).catch(e => {
					this.error = e;
				})
			},

			fillCredentials: function()
			{
				this.editName = this.name;
				this.editEmail = this.email;
				this.editPhone = this.phone;
				this.editPassword = this.password;
				this.editResidence = this.address;
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
    }
}