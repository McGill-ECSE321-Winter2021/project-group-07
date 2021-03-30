<template>
    <div class="profile">
         <date-pick v-model="date" :hasInputElement="false"></date-pick>
    </div>
</template>
<script>
import DatePick from 'vue-date-pick';
import 'vue-date-pick/dist/vueDatePick.css';
    export default {
        components: {DatePick},
        
    }
</script>

<style scoped>
.profile {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    font-size: 40px;
    color: rgb(167, 167, 167);
    font-weight: 600;
}
</style>