<template>
  <div>
    <nav-bar />
    <side-bar />
    <router-view />
  </div>
</template>

<script>
import SideBar from '@/UserLayout/components/SideBar/SideBar.vue'
import NavBar from '@/UserLayout/components/NavBar.vue'
export default {
  name: 'UserLayout',
  components: {
    SideBar: SideBar,
    NavBar: NavBar
  }
}
</script>

<style scoped>

</style>
