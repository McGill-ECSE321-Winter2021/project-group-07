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
                        @ok="createMechanic(name,password,phone,email)"
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
  <multiselect v-model="value" :state="capabilitiesState" :options="options" :multiple="true" :close-on-select="false" :clear-on-select="false" :preserve-search="true" placeholder="Pick some" label="name" track-by="name" :preselect-first="true">
    <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span></template>
  </multiselect>
                        </b-modal>
                    <form>
                        <input type="text" v-model="s" value="" class="search-input">
                    </form>
                   <button class="search-btn" @click="searchForMechanics(s)"> <img class="img-add" src="../../assets/Admin/search.png"/> </button> 
                
                </div>
            </span>
        </nav>

    
     
  <div class="line">
  </div>
  <!--
        The Table containing all the mechanic information --> 
        <div class="container mt-3 mb-3">
          <table class = "table table-striped tabled-bordered mydatatable" style="width: 100">
              <thead>
                    <tr style="text-align:center">
                        <th> ID </th>
                        <th> Name </th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Capabilities</th>
                        <th> Actions </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="mechanic in mechanics" style="text-align:center">
                        <td> {{mechanic.id}} </td>
                        <td>{{ mechanic.name }}</td>
                        <td>{{ mechanic.email }}</td>
                        <td>{{ mechanic.phone }}</td>
                        <td>{{ mechanic.services }}</td>
                        <td> 
                        <b-button class="btn btn-primary" @click=" modalShow =!modalShow; fillCredentials(mechanic)"> <img  class="img-add" src="../../assets/Admin/edit.png"/>  </b-button>
                       <b-button class="btn btn-primary" @click="removeMechanic(mechanic.id)"> <img  class="img-add" src="../../assets/Admin/delete.png"/>  </b-button> 
                        
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
                               :value="editPhone"
                            > 
                            </b-form-input>
                            </b-form-group>
   
                            </b-form>
                            <template #modal-footer="{save , cancel}">
                              
                                <!-- Emulate built in modal footer ok and cancel button actions -->
                                <b-button size="sm" variant="success" @click="editMechanic(editEmail, editName, editPassword, editPhone)"> Save </b-button>
                                <b-button size="sm" variant="danger" @click="modalShow =!modalShow">Cancel</b-button> 
      
                            </template>
                        </b-modal> 

                             
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr style="text-align:center">
                      Footer
                    </tr>
                </tfoot>
            </table>
        </div>
        
    </div>
</template>

<script src="./AdminAddTeamMember.js">
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>


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
    background: rgb(51 41 134);
    border-color:  rgb(51 41 134);
    border-color: #F3BE35; 
    transform: translateY(-5px);
}

.search-btn{
    border-radius: 10px;
    background-color: #F3BE35;
    transform: translateY(-3px);

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