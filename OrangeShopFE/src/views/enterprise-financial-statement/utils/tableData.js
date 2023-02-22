export default {
  data() {
    return {
      tableData: [],
      balanceSheetHeader: [
        {
          label: 'Chỉ tiêu',
          prop: 'variable',
          width: '25%',
          hasSearch: true
        },
        {
          label: 'Mã số năm T',
          prop: 'varCode',
          width: '8%',
          hasSearch: true
        },
        {
          label: 'Mã số năm T-1',
          prop: 'preVarCode',
          width: '8%',
          hasSearch: true
        },
        {
          label: 'Rule năm T',
          prop: 'rule',
          width: '35%'
        },
        {
          label: 'Rule năm T-1',
          prop: 'preRule',
          width: '35%'
        }
      ],
      financialStateHeader: [
        {
          label: 'Chỉ tiêu',
          prop: 'variable',
          width: '25%',
          hasSearch: true
        },
        {
          label: 'Mã số năm T',
          prop: 'varCode',
          width: '8%',
          hasSearch: true
        },
        {
          label: 'Mã số năm T-1',
          prop: 'preVarCode',
          width: '8%',
          hasSearch: true
        },
        {
          label: 'Rule năm T',
          prop: 'rule',
          width: '35%'
        },
        {
          label: 'Rule năm T-1',
          prop: 'preRule',
          width: '35%'
        }
      ],
      cashFlowStatementHeader: [
        {
          label: 'Chỉ tiêu',
          prop: 'variable',
          width: '100',
          hasSearch: true
        },
        {
          label: 'Mã số',
          prop: 'varCode',
          width: '100',
          hasSearch: true
        },
        {
          label: 'Rule',
          prop: 'rule',
          width: '100'
        }
      ]
    }
  }
}
