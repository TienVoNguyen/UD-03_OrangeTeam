<template>
  <div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 50px">
      <h1 class="font-weight-semi-bold text-uppercase pt-3">{{ title }}</h1>
      <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />
    </div>
  </div>
</template>

<script>
import Breadcrumb from '@/views/user/component/Breadcrumb/index.vue'
export default {
  components: { Breadcrumb },
  data() {
    return {
      title: ''
    }
  },
  created() {
    const matched = this.$route.matched.filter(item => item.meta && item.meta.title)
    const lastItem = matched.at(-1)
    this.title = lastItem.meta.title
  }
}
</script>

<style scoped>

</style>
