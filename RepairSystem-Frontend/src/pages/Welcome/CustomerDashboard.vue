<template>
    <div class="customerDashboard">
        <Sidebar/>
        <div class="content">
            <router-view/>
        </div>
    </div>
</template>

<script>
import Sidebar from '../../components/Sidebar'
export default {
    name: 'home',

    components: {
        Sidebar
    }
}
</script>

<style>
.customerDashboard {
    display: grid;
    grid-template-columns: 1fr 5fr;
    background-color: rgb(51 41 134);
    height: 100vh;
    width: 100vw;
}

.content {
    background-color: #D3D2E1;
    border-radius: 10px;
    margin: 6px 6px 6px 0px;
}
</style>