export default {
  data() {
    return {
      loading: null
    }
  },
  methods: {
    showLoading() {
      this.loading = this.$loading({
        lock: true,
        text: 'Đang tải'
      })
      setTimeout(() => {
        this.hideLoading()
      }, 50000)
    },
    hideLoading() {
      this.loading.close()
    }
  }
}

