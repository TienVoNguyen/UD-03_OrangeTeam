export default {
  data() {
    return {
      tableData: [],
      financialNormHeader: [
        {
          label: 'Chỉ tiêu',
          prop: 'variable',
          width: '25%',
          hasSearch: true
        },
        {
          label: 'Mã số năm',
          prop: 'varCode',
          width: '8%',
          hasSearch: true
        },
        {
          label: 'Rule',
          prop: 'rule',
          width: '60%'
        },
        {
          label: 'Exc Type',
          prop: 'exceptionCode',
          width: '7%'
        }
      ],
      excTypeHeader: [
        {
          label: 'Exc Type',
          prop: 'code',
          width: '25%',
          hasSearch: true
        },
        {
          label: 'Rule',
          prop: 'description',
          width: '75%'
        }
      ]
    }
  }
}
