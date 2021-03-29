<template>
    <div class="main">
        Sign Up
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

        <div class="textBox">
            <form>
                <label for="email">Email: </label>
                <input type="text" v-model="newEmail" value=""><br>
                <label for="password">Password:</label>
                <input type="text" v-model="newPassword" value=""><br>
                <label for="repeat-password">Repeat Password:</label>
                <input type="text" v-model="newRepeatPassword" value=""><br>
                <label for="name">Name:</label>
                <input type="text" v-model="newName" value=""><br>
                <label for="number">Phone Number:</label>
                <input type="text" v-model="newNumber" value=""><br>
                <label for="address">Address:</label>
                <input type="text" v-model="newAddress" value=""><br>
                <label for="credit">Credit:</label>
                <input type="text" v-model="newCredit" value=""><br>
                <label for="debit">Debit:</label>
                <input type="text" v-model="newDebit" value=""><br>
            </form>
            <button @click="createCustomer(newEmail, newPassword, newName, newNumber, newAddress, newCredit, newDebit)">Sign Up</button>
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
  left: 330px;
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


</style>