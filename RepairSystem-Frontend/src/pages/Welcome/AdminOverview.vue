<template>
    <div class = "row no-gutters">
        <div class = "col no-gutters">
                <div class = "row no-gutters" >
                    <div class = "left-top">
                        <img src="../../assets/appointment.png" width = "50px" >
                            Upcoming Appointments 
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                            <router-link to="adminDashboard/addAppointment"><font class = "button1" align = "right"> Add Appointment</font></router-link>
                            <router-link to="adminDashboard/editAppointment"><font class = "button1" align = "right"> Edit Appointment</font></router-link>

                    </div>
                </div>
                
                <div class = "row no-gutters" >
                    <div class = "left-bottom"> 
                        <img src="../../assets/appointment.png" width = "50px" >
                        Upcoming Mechanic Schedule 
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                        <router-link to="adminDashboard/addTimeSlot"><font class = "button1" align = "right"> Add Time Slot</font></router-link>
                        <router-link to="adminDashboard/editTimeSlot"><font class = "button1" align = "right"> Edit Time Slot</font></router-link>

                    </div>
                </div>
        </div>
        
        <div class = "col no-gutters">
            <div class = "rightside">
                <div class = "row no-gutters">
                    <div class = "top"> 
                        <center><img src="../../assets/profile-default.png"   width = "100px" length = "100px" ></center>
                        <br>
                        <center> <b style = "color: rgb(51 41 134); font-size: 20px;"> admin name </b> </center>
                    </div>
                </div>

                <div class = "row no-gutters" >
                    <div class = "middle"> 
                            <date-pick v-model="date" :hasInputElement="false"></date-pick>
                    </div>
                </div>
                <div class = "row no-gutters" >
                    <div class = "bottom"> 
                        <div>
                        <b style = "color: #F3BE35; font-size: 20px;"> &nbsp; Reminder </b>
                        </div>
                        <div class = "rcorners2">
                            <b>reminder</b>
                        </div>                                
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import DatePick from 'vue-date-pick';
import 'vue-date-pick/dist/vueDatePick.css';

export default {
    components: {DatePick},
};
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

.rightside{
    height: 98.5vh;
    width: 70%;
    position: absolute;
    content: "";
    right: 0px;
    background: white
}

.top{
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

.middle{
    height: 30%;
    width: 90%;
    position: absolute;
    content: "";
    left:20px;
    top: 200px;
}

.bottom{
    height: 30%;
    width: 80%;
    position: absolute;
    content: "";
    left:20px;
    top: 500px;
}

.left-top{
    position: absolute;
    content: "";
    top: 50px;
    left: 70px
}

.left-bottom{

    position: absolute;
    content: "";
    left:70px;
    top: 450px;
}


</style>