<!-- Team Members -->
<template>
<div class="profile">
    <nav class="navbar">
        <span class="navbar-brand mb-0 h1">
            <div class="row" style = "position: relative; top: 20px">
                <form onSubmit="return false;">
                    <input type="text" v-model="s" value="" class="search-input" placeholder="  Search">
                </form>

                <button class="search-btn" @click="searchForMechanics(s)"> <img class="img-add" src="../../assets/Admin/search.png" /> </button>

                <b-button v-b-modal.modal-prevent-closing class="btn-primary"> Add New Member <img class="img-add" src="../../assets/Admin/plus.png" /> </b-button>

                <b-modal id="modal-prevent-closing" ref="modal" title="Add New Team Member" @show="resetModal" @hidden="resetModal" @ok="createMechanic(name,password,phone,email,value)">

                    <!-- Personal Information part of the form -->
                    <label> <b>Personal Information </b> </label>
                    <b-form ref="form" @submit.stop.prevent="handleSubmit">

                        <b-form-group label="Name" label-for="name-input" invalid-feedback="Name is required" :state="nameState">
                            <b-form-input id="name-input" v-model="name" :state="nameState" required></b-form-input>
                        </b-form-group>

                        <b-form-group label="Email" label-for="email-input" invalid-feedback="email is required" :state="emailState">
                            <b-form-input id="email-input" v-model="email" :state="emailState" required></b-form-input>
                        </b-form-group>

                        <b-form-group label="Password" label-for="password-input" invalid-feedback="password is required" :state="passwordState">
                            <b-form-input id="password-input" v-model="password" :state="passwordState" required></b-form-input>
                        </b-form-group>

                        <b-form-group label="Phone Number" label-for="phone-input" invalid-feedback="Phone number is required" :state="phoneState">
                            <b-form-input id="Phone Number-input" v-model="phone" :state="phoneState" required></b-form-input>
                        </b-form-group>

                        <b-form-group label="Residence Address" label-for="address-input" invalid-feedback="Address is required" :state="addressState">
                            <b-form-input id="address-input" v-model="address" :state="addressState" required></b-form-input>
                        </b-form-group>

                        <!-- For WorkHours -->
                        <label> <b> Work Hours </b> </label>

                        <div class="row">
                            <p style="margin-left:20px"> Monday </p>
                            <div class="col">
                                <b-form-input id="mondayStart" type="time" class="form-control" v-model="mondayStart" placeholder="Monday">
                                    <label for="Monday">Choose your time</label>
                                </b-form-input>

                            </div>
                            <div class="col">
                                <b-form-input id="mondayEnd" type="time" class="form-control" v-model="mondayEnd">
                                </b-form-input>
                            </div>
                        </div>

                        <div class="row">
                            <p style="margin-left:20px">Tuesday</p>
                            <div class="col">
                                <b-form-input id="tuesdayStart" type="time" class="form-control" v-model="tuesdayStart" placeholder="Monday">
                                </b-form-input>

                            </div>
                            <div class="col">
                                <b-form-input id="tuesdayEnd" type="time" class="form-control" v-model="tuesdayEnd">
                                </b-form-input>
                            </div>
                        </div>

                        <div class="row">
                            <p style="margin-left:20px">Wednesday</p>
                            <div class="col">
                                <b-form-input id="wednesdayStart" type="time" class="form-control" v-model="wednesdayStart">
                                </b-form-input>

                            </div>
                            <div class="col">
                                <b-form-input id="wednesdayEnd" type="time" class="form-control" v-model="wednesdayEnd">
                                </b-form-input>
                            </div>
                        </div>

                        <div class="row">
                            <p style="margin-left:20px">Thursday</p>
                            <div class="col">
                                <b-form-input id="thursdayStart" type="time" class="form-control" v-model="thursdayStart">
                                </b-form-input>

                            </div>
                            <div class="col">
                                <b-form-input id="thursdayEnd" type="time" class="form-control" v-model="thursdayEnd">
                                </b-form-input>
                            </div>
                        </div>

                        <div class="row">
                            <p style="margin-left:20px">Friday</p>
                            <div class="col">
                                <b-form-input id="fridayStart" type="time" class="form-control" v-model="fridayStart">
                                </b-form-input>

                            </div>
                            <div class="col">
                                <b-form-input id="fridayEnd" type="time" class="form-control" v-model="fridayEnd">
                                </b-form-input>
                            </div>
                        </div>

                        <div class="row">
                            <p style="margin-left:20px">Saturday</p>
                            <div class="col">
                                <b-form-input id="saturdayStart" type="time" class="form-control" v-model="saturdayStart">
                                </b-form-input>

                            </div>
                            <div class="col">
                                <b-form-input id="saturdayEnd" type="time" class="form-control" v-model="saturdayEnd">
                                </b-form-input>
                            </div>
                        </div>
                    </b-form>

                    <!-- Capabilities part of the form -->
                    <label class="typo__label"> <b> Capabilities </b> </label>
                    <multiselect v-model="value" :state="capabilitiesState" :options="options" :multiple="true" :close-on-select="false" :clear-on-select="false" :preserve-search="true" placeholder="Pick some" label="name" track-by="name" :preselect-first="true">
                        <template slot="selection" slot-scope="{ values, search, isOpen }">
                            <span class="multiselect__single" v-if="values .length &amp;&amp; !isOpen">{{ values.length }} options selected</span>
                        </template>
                    </multiselect>
                </b-modal>

            </div>
        </span>
    </nav>

    <!--
        The Table containing all the mechanic information#D3D2E1 -->
    <div class="container mt-3 mb-3" style="background-color:white; border-radius:30px; position: relative; top: 80; left: -20px">
        <table class="table table-striped tabled-bordered mydatatable" style="width: 200px">
            <thead>
                <tr style="text-align:center;  border-radius:30px;">
                    <th> Name </th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th> Work Schedule </th>
                    <th>Capabilities</th>
                    <th> Actions </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="mechanic in mechanics" style="text-align:center">
                    <td>{{ mechanic.name }}</td>
                    <td>{{ mechanic.email }}</td>
                    <td>{{ mechanic.phone }}</td>
                    <td>
                        <table class="table">
                            <tbody>
                                <td> M : </td>
                                <td> Tu : </td>
                                <td> W: </td>
                                <td> Th </td>
                                <td> F </td>
                                <td> Sat </td>
                                <td> Sun </td>
                            </tbody>
                        </table>

                    </td>
                    <td>
                        <span v-for="service in mechanic.services" style="text-align:center">
                            {{service.serviceType}}
                        </span>
                    </td>
                    <td>
                        <button class="btn-edit" @click="modalShow =!modalShow; fillCredentials(mechanic)"> <img class="img-add" src="../../assets/Admin/edit.png" /> </button>
                        <button class="btn-remove" @click="removeMechanic(mechanic.id)"> <img class="img-add" src="../../assets/Admin/delete.png" /> </button>

                        <b-modal v-model="modalShow" title="Edit Profile" id="modal-scoped">
                            <b-form ref="form" @submit.stop.prevent="handleSubmit">

                                <b-form-group label="Name" label-for="editName-input" invalid-feedback="Name is required" :state="editEmailState">
                                    <b-form-input id="editName" type="text" v-model="editName" name="editName" :value="editName">
                                    </b-form-input>
                                </b-form-group>

                                <b-form-group label="Phone" label-for="editPhone-input" invalid-feedback="Phone is required" :state="editPhoneState">
                                    <b-form-input id="editPhone" v-model="editPhone" type="text" name="editPhone" :value="editPhone">
                                    </b-form-input>
                                </b-form-group>

                                <b-form-group label="Password" label-for="editPassword-input" invalid-feedback="Password is required" :state="editPhoneState">
                                    <b-form-input id="editPassword" v-model="editPassword" type="text" name="editPassword" :value="editPhone">
                                    </b-form-input>
                                </b-form-group>
                            </b-form>
                            <label class="typo__label"> Capabilities </label>
                            <multiselect v-model="value" :state="capabilitiesState" :options="options" :multiple="true" :close-on-select="false" :clear-on-select="false" :preserve-search="true" placeholder="Pick some" label="name" track-by="name" :preselect-first="true"> >
                                <template slot="selection" slot-scope="{ values, search, isOpen }">
                                    <span class="multiselect__single" v-if="values .length &amp;&amp; !isOpen">{{ values.length }} options selected</span>
                                </template>
                            </multiselect>

                            <template #modal-footer="{Save, Cancel}">

                                <!-- Emulate built in modal footer ok and cancel button actions -->
                                <b-button size="sm" variant="success" @click="editMechanic(mechanic.email, editName, editPassword, editPhone, value); modalShow =!modalShow"> Save </b-button>
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

<script src="./AdminAddTeamMember.js">
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style><style scoped>
.profile {
    height: 100%;
    width: 100%;
    /**color: rgb(167, 167, 167);   */
    color: rgb(51 41 134);
    background: #D3D2E1;

}

.navbar {
    color: rgb(51 41 134)
}

.title-header {
    margin-left: 40px;
    margin-right: 20px;
}

.btn-primary {
    border-radius: 10px;
    margin-right: 20px;
    transform: translateY(-5px);

}

.btn-edit {
    background-color: #D3D2E1;
    border-color: transparent;
    border-radius: 10px;
}

.btn-secondary {
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

.search-btn {
    border-radius: 20px;
    border-color: transparent;
    background-color: transparent;
    transform: translate(-35px, -1px);
}

.btn-remove {
    border-color: #5430be;
    background-color: transparent;
    border-radius: 10px;
    border-width: 2px;

}

.line {
    height: 2px;
    background-color: rgba(64, 57, 134, 1)
}

.img-add {
    max-height: 20px;
    transform: translateY(-1px);
}

.table {
    border-radius: 30px;
    color: #111B47;

}
</style>
