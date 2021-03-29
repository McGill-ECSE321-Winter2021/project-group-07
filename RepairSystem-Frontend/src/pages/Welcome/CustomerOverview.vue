<template>
    <div class = "row no-gutters">
        <div class = "col no-gutters">
                <div class = "row no-gutters" >
                    <div class = "left-top">
                        <div class = "rcorners">
                            &nbsp; &nbsp; &nbsp; 
                            <b style = "color: rgb(51 41 134); font-size: 30px; position: absolute; top: 25px; left: 65px"> Hello, </b>
                            <b style = "color: #F3BE35; font-size: 30px; position: absolute; top: 25px; left: 150px "> {{customer.name}} </b>
                            <img src="../../assets/welcome-car.png" width = "300px" height = "150px"  style = "position: absolute; right: 25px">
                        </div>
                    </div>
                </div>
                
                <div class = "row no-gutters" >
                    <div class = "left-bottom"> 
                        <img src="../../assets/appointment.png" width = "50px" >
                        My Appointments

                    </div>
                </div>
        </div>
        
        <div class = "col no-gutters">
            <div class = "rightside">
                <div class = "row no-gutters">
                    <div class = "top"> 
                        <center><img src="../../assets/profile-default.png"   width = "100px" length = "100px" ></center>
                        <br>
                        <center> <b style = "color: rgb(51 41 134); font-size: 20px;"> {{customer.name}} </b> </center>
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

<script src="./CustomerOverview.js">
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