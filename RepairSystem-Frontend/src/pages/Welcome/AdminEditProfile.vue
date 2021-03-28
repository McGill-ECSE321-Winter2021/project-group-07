<template>
    <div class="profile">
        <div class="name">
             <div class="text">
            Admin Name
            </div>
            <div class="ellipse">
            </div>
        </div>

        <div class="container">
        <div class="row">
        <div class="col">Residence</div>
        <div class="w-100"></div>
        <input type="address" value="address" disabled></input>
        <div class="w-100"></div>
        <div class="col">Email</div>
        <div class="w-100"></div>
        <input type="email" value="email" disabled></input>
        <div class="w-100"></div>
        <div class="col">Telephone</div>
        <div class="w-100"></div>
        <input type="telephone" value="phone number" disabled></input>
        </div>
        </div>

        <div class="container2">
        <div class="row">
        <div class="col">Password</div>
        <div class="w-100"></div>
        <input type="password" value="password" disabled> </input>
        </div>
        </div>

        <div class="container3">
                <font class = "button" align = "right"> Edit Information</font>
        </div>

        

    </div>     
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
.profile {
    position: relative;
    width: 738px;
    height: 374px;
    background: #FFFFFF;
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
    background: #C4C4C4;
}
.container{
position: absolute;
left: 43px;
top: 87px;
font-family: Roboto;
font-style: normal;
font-weight: 900;
font-size: 15px;
line-height: 38px;
color: #37447E;
}

.container2{
position: absolute;
left: 285px;
top: 87px;
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
</style>