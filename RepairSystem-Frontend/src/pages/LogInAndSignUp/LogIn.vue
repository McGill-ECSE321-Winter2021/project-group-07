<template>
    <div class="main">
        Log In
        <div class="leftCar">
            <img src="../../assets/LogInCarLeft.svg" alt="Left Car" width="600" height="430">
        </div>

        <div class="rightCar">
            <img src="../../assets/LogInCarRight.svg" alt="Right Car" width="500" height="383">
        </div>

        <div class="box">
            <svg width="800" height="200">
                <rect width="1200" height="200" fill-opacity="0.4" style="fill:rgb(17,27,71)" />
            </svg>
        </div>

        <div class="textBox">
            <form>
                <label for="email">Email: </label>
                <input type="text" v-model="customerEmail" value=""><br>
                <label for="password">Password: </label>
                <input type="text" v-model="customerPassword" value=""><br>
            </form>
            <button @click="logIn(customerEmail, customerPassword)">Log In</button>
        </div>

        <h2>{{error}}</h2>
    </div>

</template>

<script src="./LogIn.js">
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
  height: 200px;
}

.textBox {
  position: absolute;
  top: 250px;
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
  padding: 8px;
}


</style>