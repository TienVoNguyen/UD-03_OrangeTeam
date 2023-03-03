import { ext, regex, required, mimes, image, size, email } from 'vee-validate/dist/rules'
import { extend } from 'vee-validate'

extend('required', {
  ...required,
  message: ' bắt buộc nhập'
})
extend('requiredSelect', {
  ...required,
  message: ' bắt buộc chọn'
})
extend('ext', {
  ...ext,
  message: ' không đúng định dạng'
})
extend('regex', {
  ...regex,
  message: ' không đúng định dạng'
})

extend('mimes', {
  ...mimes,
  message: ' không đúng định dạng'
})

extend('image', {
  ...image,
  message: ' không đúng định dạng'
})

extend('size', {
  ...size,
  message: ` phải nhỏ hơn {size}kb`
})

extend('max', {
  validate(value, args) {
    return value.toString().length <= args.length
  },
  params: ['length'],
  message: ` không vượt quá {length} ký tự`
})

extend('isLower', {
  params: ['target'],
  validate(value, { target }) {
    return !target || +value < +target
  },
  message: ' phải nhỏ hơn chặn trên'
})

extend('email', {
  ...email,
  message: ` Email phải đúng định dạng`
})
export default {
  data() {
    return {
      loginFields: {
        username: 'Tên đăng nhập',
        password: 'Mật khẩu'
      },
      approverFields: {
        unit: 'Đơn vị',
        position: 'Chức vụ',
        approver: 'Họ và tên'
      },
      scorePeriodFields: {
        name: 'Kỳ chấm điểm'
      },
      lendingFields: {
        type: 'Loại chấm điểm',
        name: 'Loại hình chấm điểm'
      },
      systemFields: {
        dept1: 'Đơn vị',
        dept1_type: 'Loại đơn vị',
        dept2: 'Phòng ban',
        side: 'Miền',
        name: 'Tên',
        status: 'Trạng thái',
        code: 'Mã',
        position: 'Chức vụ',
        file: 'File'
      },
      customer_identificationFields: {
        code_tax: 'Mã số thuế',
        code_reg: 'Mã số đăng ký kinh doanh',
        name_dn: 'Tên doanh nghiệp',
        name_dept: 'Tên đơn vị quản lý',
        code_dept: 'Mã đơn vị quản lý'
      },
      rank_adjustFields: {
        rule_change: 'Quy tắc điều chỉnh',
        cause_change: 'Nguyên nhân điều chỉnh',
        reason_change: 'Lý do điều chỉnh'
      },
      functionsName: {
        title: 'Tiêu đề',
        path: 'Path',
        icon: 'Icon',
        component: 'Mã màn hình',
        priority: 'Thứ tự'
      },
      economic_sbvFields: {
        codeSBV: 'Mã ngành kinh tế SBV',
        nameSBV: 'Tên ngành kinh tế SBV',
        requestChange: 'Yêu cầu điều chỉnh',
        file: 'File'
      },
      groupFCCFields: {
        code: 'Mã ngành kinh tế FCC',
        name: 'Tên ngành kinh tế FCC',
        sbv: 'Ngành kinh tế SBV',
        css: 'Nhóm ngành'
      },
      groupFCCImportFields: {
        file: 'File'
      },
      matrixClassifyFields: {
        nameMatrix: 'Tên ma trận',
        rules: 'Loại quy tắc phân loại quy mô',
        tpBankRule: 'Quy tắc phân loại TPBank',
        pointRule: 'Quy tắc phân loại theo điểm chỉ tiêu',
        establishMatrix: 'Thiết lập ma trận',
        requestChangeMatrix: 'Lý do từ chối'
      },
      tpBankRuleFields: {
        name: 'Tên quy tắc',
        unit: 'Đơn vị báo cáo tài chính',
        sales: 'Doanh thu',
        salesCriteria: 'Doanh thu - Chọn tiêu chí',
        equity: 'Vốn chủ sở hữu',
        equityCriteria: 'Vốn chủ sở hữu - Chọn tiêu chí',
        configScalePointList: 'Thiết lập điểm quy mô'
      },
      salesScalePointFields: {
        fromSign: 'Dấu',
        fromValue: 'Chặn dưới',
        toValue: 'Chặn trên',
        toSign: 'Dấu'
      },
      equityScalePointFields: {
        fromSign: 'Dấu',
        fromValue: 'Chặn dưới',
        toValue: 'Chặn trên',
        toSign: 'Dấu'
      },
      configScalePointFields: {
        size: 'Size',
        scale: 'Quy mô'
      },
      branches: {
        branchCode: 'Mã đơn vị',
        branchCodeNumber: 'Mã số đơn vị',
        branchTypeName: 'Loại đơn vị',
        branchName: 'Tên đơn vị',
        districtName: 'Miền'
      },
      departments: {
        branchName: 'Tên đơn vị',
        name: 'Tên phòng ban'
      },
      positions: {
        branchName: 'Tên đơn vị',
        name: 'Tên chức vụ',
        departmentName: 'Tên phòng ban'
      },
      groups: {
        name: 'Tên nhóm',
        code: 'Mã nhóm'
      },
      users: {
        groupName: 'Tên nhóm',
        username: 'Tên đăng nhập',
        name: 'Tên nhân viên'
      },
      ruleChangeRankFields: {
        content: 'Lý do'
      },
      singleScoringModelKHDNFields: {
        codeModel: 'Mã mô hình đơn',
        nameModel: 'Tên mô hình đơn',
        typeRank: 'Loại xếp hạng',
        scaleClassify: 'Phân loại quy mô',
        industryGroup: 'Nhóm ngành',
        inputModel: 'Tên mô hình nhập liệu',
        typeCode: 'Loại code',
        fileCodeModel: 'File code model',
        fileImport: 'File',
        userCreated: 'Người tạo',
        userApproved: 'Người duyệt'
      },
      configParameterPropertyFields: {
        codeProperty: 'Mã thuộc tính',
        nameProperty: 'Tên thuộc tính',
        description: 'Mô tả',
        file: 'File'
      }
    }
  },
  methods: {
    messageError(fieldName, error) {
      if (error === undefined) {
        return undefined
      }
      return fieldName + error
    }
  }
}
