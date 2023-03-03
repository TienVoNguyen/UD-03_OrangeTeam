<template>
  <el-page-header class="m-auto" @back="goBack">
    <template v-slot:content>
      <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />
    </template>
  </el-page-header>
</template>

<script>
import Breadcrumb from '@/views/user/component/Breadcrumb/index.vue'
export default {
  components: { Breadcrumb },
  methods: {
    goBack() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>
