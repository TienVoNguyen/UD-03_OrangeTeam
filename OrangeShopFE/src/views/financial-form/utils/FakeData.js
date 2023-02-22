export default {
  data() {
    return {
      typeFReports: [
        {
          label: 'A',
          value: 1
        }
      ],
      statusFStatuses: [
        {
          label: 'A',
          value: 1
        }
      ],
      tableData: [
        {
          name: 'Bảng cân đối kế toán_2022',
          code: 'TT200',
          createdDate: '02/08/2022',
          approvedDate: '',
          status: 'Tạo mới'
        }
      ],
      tableHeader: [
        {
          label: 'Tên biểu mẫu nhập liệu',
          prop: 'name',
          width: '100'
        },
        {
          label: 'Mẫu biểu BCTC',
          prop: 'code',
          width: '100'
        },
        {
          label: 'Ngày tạo',
          prop: 'createdDate',
          width: '100'
        },
        {
          label: 'Ngày duyệt',
          prop: 'approvedDate',
          width: '100'
        },
        {
          label: 'Trạng thái',
          prop: 'status',
          width: '100'
        }
      ],
      tableData1: [
        {
          stt: 1,
          ttc: 'TT200',
          mnt: 'STR',
          mnt1: 'TTRY'
        }
      ],
      tableHeader1: [
        {
          label: 'STT',
          prop: 'stt',
          width: '100',
          align: 'center'
        },
        {
          label: 'Tên tiêu chí',
          prop: 'ttc',
          width: '100',
          hasSearch: true
        },
        {
          label: 'Mã năm T',
          prop: 'mnt',
          width: '100',
          hasSearch: true
        },
        {
          label: 'Mã năm T-1',
          prop: 'mnt1',
          width: '100',
          hasSearch: true
        }
      ]
    }
  }
}
