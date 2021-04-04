<template>

    <div class="main">
                <div style = "position: absolute; left: 5px; top: 10px"> &nbsp; <router-link to="/"><img src="../../assets/admin.png" width = "50px" length = "50px"></router-link>
          </div>
        <center> Sign Up </center>
        <div class="leftCar">
            <img src="../../assets/LogInCarLeft.svg" alt="Left Car" width="600" height="430">
        </div>

        <div class="rightCar">
            <img src="../../assets/LogInCarRight.svg" alt="Right Car" width="500" height="383">
        </div>

        <div class="box">
            <svg width="800" height="300">
                <rect width="1200" height="300" fill-opacity="0.4" style="fill:rgb(17,27,71)" />
            </svg>
        </div>

        <div class="textBox" >
            <form > 
               &nbsp;&nbsp; <label for="email">Email: </label>
                <input type="text" v-model="newEmail" value=""><br>
               &nbsp;&nbsp; <label for="password">Password:</label>
                <input type="password" v-model="newPassword" value=""><br>
               &nbsp;&nbsp; <label for="repeat-password">Repeat Password:</label>
                <input type="password" v-model="newRepeatPassword" value=""><br>
               &nbsp;&nbsp; <label for="name">Name:</label>
                <input type="text" v-model="newName" value=""><br>
               &nbsp;&nbsp; <label for="number">Phone Number:</label>
                <input type="text" v-model="newNumber" value=""><br>
               &nbsp;&nbsp; <label for="address">Address:</label>
                <input type="text" v-model="newAddress" value=""><br>
               &nbsp;&nbsp; <label for="credit">Credit:</label>
                <input type="password" v-model="newCredit" value=""><br>
               &nbsp;&nbsp; <label for="debit">Debit:</label>
                <input type="password" v-model="newDebit" value=""><br>
            </form>
             &nbsp;&nbsp;  <button class = "button1" @click="createCustomer(newEmail, newPassword, newName, newNumber, newAddress, newCredit, newDebit)">Sign Up</button>
        </div>
    </div>

</template>

<script src="./SignUp.js">
</script>

<style scoped>
.main {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    color: rgb(167, 167, 167);
    font-weight: 600;
}
.box {
  position: absolute;
  top: 250px;
  width: 800px;
  height: 300px;
}
.textBox {
  position: absolute;
  top: 260px;
  width: 800px;
  height: 200px;
}
.leftCar {
  position: absolute;
  bottom: 100px;
  left: 20px;
  width: 600px;
  height: 430px;
}
.rightCar {
  position: absolute;
  bottom: 65px;
  right: 20px;
  width: 500px;
  height: 383px;
}
label {
  color: white;
  font-size: 12px;
}
br {
    line-height:0px;
}
input {
  color: rgb(80, 95, 152);
  font-size: 12px;
}
.button1 {
  background: rgb(51 41 134);
  width: 80px;
  height: 30px;
  color: white;
  padding: 2px;
  border-radius: 8px;
}
</style>