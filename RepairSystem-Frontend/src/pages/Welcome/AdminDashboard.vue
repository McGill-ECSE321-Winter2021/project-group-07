<template>
    <div class="adminDashboard">
        <AdminSidebar/>
        <div class="content">
            <router-view/>
        </div>
    </div>
</template>

<script>
import AdminSidebar from '../../components/AdminSidebar'
export default {
    name: 'home',

    components: {
        AdminSidebar
    }
}
</script>

<style>
.adminDashboard {
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