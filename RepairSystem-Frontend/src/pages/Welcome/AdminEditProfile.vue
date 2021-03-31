<template>
    <div class = "row no-gutters">
        <div class = "col no-gutters">
        <div class="profile">
        <div class="name">
             <div class="text" v-model="name">
             Admin Name
                     <h1> {{admin.name}}</h1>

            </div>
            <div class="ellipse">
                <img src="../../assets/profile-default.png"   width = "60px" length = "60px" >

            </div>
        </div>

        <div class="container">
        <div class="row">
        <div class="col">Residence</div>
        <div class="w-100"></div>
        <input type="text" v-model="address" value=""><br>
        <div class="w-100"></div>
        <div class="col">Email</div>
        <div class="w-100"></div>
        <input type="text" v-model="email" value="" disabled><br>
        <div class="w-100"></div>
        <div class="col">Telephone</div>
        <div class="w-100"></div>
       <input type="text" v-model="phone" value=""><br>
        </div>
        </div>

        <div class="container2">
        <div class="row">
        <div class="col">Password</div>
        <div class="w-100"></div>
        <input type="password" v-model="password" value=""><br>
        <div class="w-100"></div>
        </div>
        </div>

        <div class="container3">
                <button class="button" @click="editAdmin(email, name, password, phone)" align = "right">Edit Profile</button>
        </div>

        

    </div> 
        </div>
        
        <div class = "col no-gutters">
            <div class = "rightside">
                <div class = "row no-gutters">
                    <div class = "top"> 
                        <center><img src="../../assets/profile-default.png"   width = "100px" length = "100px" ></center>
                        <br>
                        <center> <b style = "color: rgb(51 41 134); font-size: 20px;"> {{admin.name}} </b> </center>
                    </div>
                </div>

                <div class = "row no-gutters" >
                    <div class = "middle"> 
                            <date-pick v-model="date" :hasInputElement="false"></date-pick>
                    </div>
                </div>
                <div class = "row no-gutters" >
                    <div class = "bottom"> 
                        <img src="../../assets/paint job 1.jpg" width = "400px" length = "400px">
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    data () {
    return {
	  admin: "",
	  error: ""
    }
    },
    created: function () {
        var id = this.$route.params.userId
        AXIOS.get('/admin/'.concat(id))
        .then(response => {
        // JSON responses are automatically parsed.
        this.admin = response.data
    })
    .catch(e => {
        this.error = e
        console.log(e)
    })
    }
}
</script>

<style scoped>
.profile {
    position: relative;
    top: 20px;
    right: -20px;
    width: 115%;
    height: 500px;
    border-radius: 25px;
    background: white;
    border: 3px solid;
    border-color: #D3D2E1;
    padding: 25px;
}
.name{
position: absolute;
width: 266px;
height: 65px;
left: 19px;
top: 9px;
}
.text{
position: absolute;
width: 190px;
height: 38px;
top: 17px;
left: 95px;
font-family: Roboto;
font-style: normal;
font-weight: 900;
font-size: 26px;
line-height: 38px;

color: #37447E;
}
.ellipse{
    border-radius: 50%;
    position: absolute;
    width: 65px;
    height: 65px;
    left: 19px;
    top: 9px;
}
.container{
position: absolute;
left: 80px;
top: 120px;
font-family: Roboto;
font-style: normal;
font-weight: 900;
font-size: 15px;
line-height: 38px;
color: #37447E;
}

.container2{
position: absolute;
left: 305px;
top: 120px;
font-family: Roboto;
font-style: normal;
font-weight: 900;
font-size: 15px;
line-height: 38px;
color: #37447E;
}

.container3 {
    position: absolute;
    left: 500px;
    top: 200px;
  background: rgb(51 41 134);
  color: white;
  padding: 6px;
  border-radius: 8px;
}
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
    top: 250px;
}

</style>