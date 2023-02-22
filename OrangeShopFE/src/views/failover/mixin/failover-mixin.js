// import * as reportService from '@/views/reports/service/report-service'

export default {
  data() {
    return {
      optionAll: { value: null, label: '--Tất cả--' },
      listAccount: null,
      priorityList: [
        { value: 25, label: '1 - Zalo' },
        { value: 26, label: '2 - Viber' },
        { value: 99, label: '3 - SMS' }
      ],
      prioritySelectedList: null
    }
  },
  created() {
    this.getListAccount()
  },
  methods: {
    async getListAccount() {
      this.listAccount = [{ value: 1, label: 'Tài khoản 1' }, { value: 2, label: 'Tài khoản 2' }]
      this.listAccount.unshift(this.optionAll)
    }
  }
}

