<template>
    <div class = "row no-gutters">
        <div class = "col no-gutters">
            <div class = "leftside"></div>
        </div>
        
        <div class = "col no-gutters">
            <div class = "rightside">
                <div class = "row no-gutters">
                    <div class = "top"> 
                        <center><img src="../../assets/profile-default.png"   width = "100px" length = "100px" ></center>
                        <br>
                        <center> <b style = "color: rgb(51 41 134); font-size: 20px;"> cutomer name </b> </center>
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

</style>