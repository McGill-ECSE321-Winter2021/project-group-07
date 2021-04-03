<template>
<div class="row no-gutters">
    <div class="col no-gutters">
        <div class="row no-gutters">
            <div class="left-top">
                 <nav class="navbar">
                    <span class="navbar-brand mb-0 h1">
                        <img src="../../assets/appointment.png" width="50px">
                            Upcoming Appointments
                        <b-button v-b-modal.modal-prevent-closing class="btn-primary"> Add Appointment <img class="img-add" src="../../assets/Admin/plus.png" /> </b-button>
                        <b-modal
                            id="modal-prevent-closing"
                            ref="modal"
                            title="Add New Team Member"
                            @show="resetModal"
                            @hidden="resetModal"
                        >
                        </b-modal>
                    </span>
                </nav>

                  <!--The Table containing all the timeslot information--> 
                    <div class="container mt-3 mb-3" style="background-color:white; border-radius:30px;">
                        <table class="table table-striped tabled-bordered mydatatable" style="width: 100">
                            <thead>
                                <tr style="text-align:center;  border-radius:30px;">
                                    <th> Appointment Id </th>
                                    <th> Cusomter </th>
                                    <th> Status </th>
                                    <th> Time Slot</th>
                                    <th> Mechanics </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="appointment in appointments" style="text-align:center">
                                    <td> {{appointment.id}} </td>
                                    <td>{{ appointment.customer }}</td>
                                    <td>{{ appointment.status }}</td>
                                    <td>{{ appointment.timeslot }}</td>
                                    <td>{{ appointment.mechanics }}</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <h1 style="color:white"> Footer </h1>
                            </tfoot>
                        </table>
                    </div>

        <div class="row no-gutters">
            <div class="left-bottom">
                   <nav class="navbar">
                        <span class="navbar-brand mb-0 h1">
                            <img src="../../assets/appointment.png" width="50px">
                            Upcoming Mechanic Schedule
                            <button class="btn-edit" @click="modalShow =!modalShow"> <img  class="img-add" src="../../assets/Admin/plus.png"/>  </button>
                        </span>
                    </nav>
                    <div class="profile">
                            <div class="row">
                                <b-modal
                                v-model="modalShow"
                                title="Add Time Slot"
                                id="modal-scoped"
                                >
                                <b-form ref="form" @submit.stop.prevent="handleSubmit">
                                
                                    <b-form-group
                                    label="Enter Start Time (YYYY-MM-DD-HH:mm)"
                                    label-for="startTime-input"
                                    invalid-feedback="Start Time is required"
                                    :state="editStartTimeState"
                                    required
                                    >
                                    <b-form-input
                                        id="startTime"
                                        type="text"
                                        v-model="startTime"
                                        name="startTime"
                                    >
                                    </b-form-input>
                                    </b-form-group>

                                    <b-form-group
                                        label="Enter End Time (YYYY-MM-DD-HH:mm)"
                                        label-for="endTime-input"
                                        invalid-feedback=" End Time is required"
                                        :state="editEndTimeState"
                                        required
                                    >
                                    <b-form-input
                                        id="endTime"
                                        type="text"
                                        v-model="endTime"
                                        name="endTime"
                                    >
                                    </b-form-input>
                                    </b-form-group>
                                </b-form> 
                                <template #modal-footer="{Save, Cancel}">
                                    <!-- Emulate built in modal footer ok and cancel button actions -->
                                    <b-button size="sm" variant="success" @click="createTimeSlot(startTime,endTime); modalShow =!modalShow"> Save </b-button>
                                    <b-button size="sm" variant="danger" @click="modalShow =!modalShow">Cancel</b-button> 
                
                                </template>
                                </b-modal> 
                            </div>
                        
                    <!-- The Table containing all the timeslot information -->
                    <div class="container mt-3 mb-3" style="background-color:white; border-radius:30px;">
                        <table class="table table-striped tabled-bordered mydatatable" style="width: 100">
                            <thead>
                                <tr style="text-align:center;  border-radius:30px;">
                                    <th> TimeSlotId </th>
                                    <th> Start Time </th>
                                    <th> End Time </th>
                                    <th> Appointment </th>
                                    <th> Mechanic</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="timeslot in timeslots" style="text-align:center">
                                    <td> {{timeslot.id}} </td>
                                    <td>{{ timeslot.startTime }}</td>
                                    <td>{{ timeslot.endTime }}</td>
                                    <td>{{ timeslot.appointments }}</td>
                                    <td>{{ timeslot.mechanics }}</td>
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
            </div>
        </div>
    </div>

    <div class="col no-gutters">
        <div class="rightside">
            <div class="row no-gutters">
                <div class="top">
                    <center><img src="../../assets/profile-default.png" width="100px" length="100px"></center>
                    <br>
                    <center> <b style="color: rgb(51 41 134); font-size: 20px;"> {{admin.name}} <br> id: {{admin.id}} </b> </center>
                </div>
            </div>

            <div class="row no-gutters">
                <br>
                <div class="middle">
                    <br>
                    <date-pick v-model="date" :hasInputElement="false"></date-pick>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="bottom">
                    <div>
                        <br>
                        <b style="font-family: Roboto; color: #F3BE35; font-size: 20px;"> &nbsp; Reminder </b>
                    </div>
                    <div class="rcorners2">
                        <b>reminder</b>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>


<script src="./AdminOverview.js">
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

.button1 {
    background: rgb(51 41 134);
    width: 100px;
    height: 60px;
    color: white;
    padding: 6px;
    border-radius: 8px;
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
    left: 70px;
    width: 80vh;
}

.left-bottom {

    position: absolute;
    content: "";
    left: 70px;
    top: 450px;
    width: 80vh;
}

.profile {
    height: 100%;
    width: 100%;
    font-family: Roboto;
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
