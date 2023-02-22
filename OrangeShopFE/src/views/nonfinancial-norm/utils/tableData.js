export default {
  data() {
    return {
      tableData: [],
      nonFinancialNormHeader: [
        {
          label: 'Chỉ tiêu',
          prop: 'variable',
          width: '25%',
          hasSearch: true
        },
        {
          label: 'Mã số',
          prop: 'varCode',
          width: '8%',
          hasSearch: true
        },
        {
          label: 'Rule',
          prop: 'rule',
          width: '60%'
        }
      ]
    }
  }
}
