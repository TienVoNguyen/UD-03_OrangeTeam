<template>
  <el-dialog
    title="Tải file danh sách người phê duyệt"
    :visible.sync="dialogImportVisible"
    :before-close.sync="closeDialog"
  >
    <h3 slot="title" class="title mt-3"><strong>Tải file danh sách người phê duyệt</strong></h3>
    <el-form ref="dataForm" label-position="left" label-width="70px">
      <label for="excel">Chọn file danh sách khách hàng chấm điểm <span class="text-danger">*</span></label>
      <input ref="excel-upload-input" class="form-control custom-file-input" type="file" accept=".xlsx, .xls">
    </el-form>
    <div slot="footer" class="dialog-footer">
      <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button>
      <button class="btn btn-outline-warning" @click="importData">Đồng ý</button>
    </div>
  </el-dialog>
</template>

<script>

import XLSX from 'xlsx'

export default {
  name: 'ImportUser',
  props: {
    dialogImportVisible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      loading: false,
      excelData: {
        header: null,
        results: null
      }
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogImportVisible', false)
    },
    importData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          //
        }
      })
    },
    beforeUpload(file) {
      const isLt1M = file.size / 1024 / 1024 < 1

      if (isLt1M) {
        return true
      }

      this.$message({
        message: 'Please do not upload files larger than 1m in size.',
        type: 'warning'
      })
      return false
    },
    handleClick(e) {
      const files = e.target.files
      const rawFile = files[0] // only use files[0]
      if (!rawFile) return
      this.upload(rawFile)
    },
    upload(rawFile) {
      this.$refs['excel-upload-input'].value = null // fix can't select the same excel
      const before = this.beforeUpload(rawFile)
      if (before) {
        this.readerData(rawFile)
      }
    },
    readerData(rawFile) {
      this.loading = true
      return new Promise((resolve, reject) => {
        const reader = new FileReader()
        reader.onload = e => {
          const data = e.target.result
          const workbook = XLSX.read(data, { type: 'array' })
          const firstSheetName = workbook.SheetNames[0]
          const worksheet = workbook.Sheets[firstSheetName]
          const results = XLSX.utils.sheet_to_json(worksheet)
          console.log(results)
          this.loading = false
          resolve()
        }
        reader.readAsArrayBuffer(rawFile)
      })
    },
    isExcel(file) {
      return /\.(xlsx|xls|csv)$/.test(file.name)
    }
  }
}
</script>
<style scoped>
.btn-outline-secondary {
  border-color: #C9D1D7;
  color: #C9D1D7;
}
.btn-outline-secondary:hover {
  background-color: #C9D1D7;
  border-color: #C9D1D7;
  color: #fff;
}
.btn-outline-secondary:active,
.btn-outline-secondary:focus {
  color: #fff;
  background-color: #C9D1D7;
  border-color: #C9D1D7;
  box-shadow: 0 0 0 0.2rem #C9D1D7 !important;
}
.btn-outline-warning {
  border-color: #F7B432;
  color: #F7B432;
}
.btn-outline-warning:hover {
  background-color: #F7B432;
  border-color: #F7B432;
  color: #fff;
}
.btn-outline-warning:active,
.btn-outline-warning:focus {
  color: #fff;
  background-color: #F7B432;
  border-color: #F7B432;
  box-shadow: 0 0 0 0.2rem #F7B432 !important;
}
.title {
  color: #5E2E86;
  text-align: center;
}
</style>
