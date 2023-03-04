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
extend('phone', {
  validate: value => {
    const regexPattern = /^0(3\d{8}|5\d{8}|7\d{8}|8\d{8}|9\d{8})$/
    return regexPattern.test(value)
  },
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
