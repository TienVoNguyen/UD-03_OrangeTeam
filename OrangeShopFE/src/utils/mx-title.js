import $ from 'jquery'
export default {
  data() {
    return {
      pageTitle: null
    }
  },
  created() {
  },
  mounted() {
    this.pageTitle = $('.router-link-active li span')[0].innerText
    this.equalWidthLabel()
  },
  methods: {
    getTitle() {
      return $('.router-link-active li span')[0].innerText
    },
    equalWidthLabel() {
      const labels = $('.label-custom')
      let maxWidth = 0
      labels.each(function() {
        if ($(this).width() > maxWidth) {
          maxWidth = $(this).width()
        }
      })
      $('.label-custom').width(maxWidth)
    },
    isNullorEmpty(str) {
      if (str === null || str === '' || str === undefined) {
        return true
      } else {
        return false
      }
    }
  }
}
