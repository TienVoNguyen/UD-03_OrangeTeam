import * as reportService from '../service/report-service'
export default {
  data() {
    return {
      optionAll: { value: null, label: '--Tất cả--' },
      nowDate: new Date(),
      currentMonth: 0,
      currentYear: 0,
      listAccount: null,
      currentDate: new Date().toJSON().slice(0, 10).split('-').reverse().join('/')
    }
  },
  created() {
    this.currentMonth = this.nowDate.getMonth().toString().length === 1 ? '0' + (this.nowDate.getMonth() + 1) : this.nowDate.getMonth() + 1
    this.currentYear = this.nowDate.getFullYear()
  },
  methods: {
    async getListAccount() {
      const params = {
        'status': 1,
        'isDelete': 0,
        'isSale': -1,
        'accountType': 2 // 2 - account chính thức; 1 - account test
      }
      const response = await reportService.getAccount(params)
      this.listAccount = response.data.map((item) => ({ value: item.id, label: item.username }))
      this.listAccount.unshift(this.optionAll)
    },
    genYears() {
      var currentYear = parseInt(this.nowDate.getFullYear())
      var startYear = parseInt(currentYear) - 10
      var years = Array(currentYear - startYear + 1).fill().map((_, idx) => ({ value: startYear + idx, label: startYear + idx }))
      years.sort(function(a, b) { return b.value - a.value })
      // years.unshift(this.optionAll) // thêm vào đầu mảng
      return years
    },
    genMonths() {
      var months = Array.from({ length: 12 }, (_, i) => (
        {
          value: (i + 1).toString().length === 1 ? '0' + (i + 1) : i + 1,
          label: (i + 1).toString().length === 1 ? '0' + (i + 1) : i + 1
        }))
      // months.unshift(this.optionAll) thêm vào đầu mảng
      return months
    },
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getData()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getData()
    },
    isEmpty(str) {
      return (!str || str.length === 0)
    },
    convertMessage(input) {
      let msg = input
      if (input === 'success') {
        msg = 'Lấy dữ liệu thành công'
      }
      return msg
    },
    convertDateObjectToString(inputDate) {
      if (typeof inputDate === 'object') {
        return inputDate.toJSON().slice(0, 10).replace(/-/g, '-')
      } else {
        return inputDate
      }
    },
    convertDate(input) {
      return this.convertDateToLocalUTC(input, 'DD/MM/YYYY HH:mm:ss')
    },
    formatNumber(row, col, value, index) {
      if (value) {
        value = Math.round(value * 100) / 100
      }
      return value ? value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,') : 0
    },
    downloadExcel(data, fileNameExcel) {
      const url = window.URL.createObjectURL(new Blob([data]))
      const link = document.createElement('a')
      link.href = url
      const fileName = fileNameExcel
      link.setAttribute('download', fileName)
      document.body.appendChild(link)
      link.click()
    }
  }
}
