<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      title="Import ngành kinh tế SBV"
      :visible.sync="dialogImportVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>Import ngành kinh tế SBV</strong></h3>
      <el-form ref="dataForm" label-position="left" label-width="70px">
        <ValidationProvider
          ref="file"
          v-slot="{ errors }"
          name="file"
          rules="required"
        >
          <el-form-item
            prop="file"
            :error="messageError(economic_sbvFields.file, errors[0])"
          >
            <div style="display: flex;">
              <label for="excel" style="width: 100px">Tải tệp lên<span class="text-danger">*</span></label>
              <input
                ref="excel-upload-input"
                class="form-control custom-file-input"
                type="file"
                accept=".xlsx, .xls"
                @change="selected"
              >
            </div>
          </el-form-item>
        </ValidationProvider>

      </el-form>
      <div style="margin-left: 70px">
        <span>Tải file mẫu </span> <a href="#" role="button" style="margin-top: -4px; color:blue" @click="handleExport_Example">tại đây</a>
        <div v-if="validRequest < totalRequest" class="box_import" style="text-align: center;">
          <span style="color: red;">Tổng số bản ghi hợp lệ {{ validRequest }} / {{ totalRequest }}. Vui lòng tải kết quả và import lại danh sách đúng </span>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="info" plain @click="closeDialog">Đóng</el-button>
        <el-button type="warning" plain @click="handleSubmit(importData)">Lưu</el-button>
        <el-button v-if="validRequest < totalRequest" type="success" plain @click="handleExport_ResImport">Tải xuống</el-button>

      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import { importSBV } from '@/api/economic-sbv'
import XLSX from 'xlsx'
// import axios from 'axios'

export default {
  name: 'ImportEconomicSBV',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate],
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
        results: {
          onsuccess: false,
          num_success: null,
          total: null
        }
      },
      result_import: [],
      validRequest: null,
      totalRequest: null,
      exampleDataExport:
        [
          {
            index: 0,
            codeSBV: 'CODE_EXAMPLE_SBV_01',
            nameSBV: 'TÊN_EXAMPLE_SBV_01'
          },
          {
            index: 1,
            codeSBV: 'CODE_EXAMPLE_SBV_02',
            nameSBV: 'TÊN_EXAMPLE_SBV_02'
          },
          {
            index: 2,
            codeSBV: 'CODE_EXAMPLE_SBV_03',
            nameSBV: 'TÊN_EXAMPLE_SBV_03'
          }
        ]

    }
  },
  methods: {
    async selected({ target: { files }}) {
      await this.$refs.file.validate(files)
    },
    isExcel(file) {
      return /\.(xlsx|xls)$/.test(file.name)
    },
    doneImport() {
      this.excelData.results.onsuccess = true
    },
    closeDialog() {
      this.$emit('update:dialogImportVisible', false)
    },
    importData() {
      const files = this.$refs['excel-upload-input'].files
      const rawFile = files[0] // only use files[0]
      if (!rawFile) return
      this.upload(rawFile).then(response => {
        importSBV(response).then((importResponse) => {
          this.result_import = importResponse.data.lstQuery
          this.validRequest = importResponse.data.validRequest
          this.totalRequest = importResponse.data.totalRequest
          if (this.totalRequest === this.validRequest) {
            this.notifySuccess('Thông tin', 'Import thành công')
          }
        })
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
      return this.readerData(rawFile)
    },
    handleExport_Example() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['STT', 'Mã ngành kinh tế SBV', 'Tên ngành kinh tế SBV']
        const filterVal = ['index', 'codeSBV', 'nameSBV']

        const data = this.formatJson(filterVal, this.exampleDataExport)
        const filename = 'Example_File_Import_EconomicSBV'
        const bookType = 'xlsx'
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: filename,
          autoWidth: 'true',
          bookType: bookType
        })
        this.$notify.success({
          title: 'Success',
          message: `${filename}.${bookType} tải xuống thành công`,
          showClose: false
        })
        this.downloadLoading = false
      })
    },
    handleExport_ResImport() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['STT', 'Mã ngành kinh tế SBV', 'Tên ngành kinh tế SBV', 'Kết quả']
        const filterVal = ['index', 'codeSBV', 'nameSBV', 'statusImport']
        const list = this.result_import

        const data = this.formatJson(filterVal, list)
        const filename = this.formatFilenameExport()
        const bookType = 'xlsx'
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: filename,
          autoWidth: 'true',
          bookType: bookType
        })
        this.$notify.success({
          title: 'Success',
          message: `${filename}.${bookType} tải xuống thành công`,
          showClose: false
        })
        this.downloadLoading = false
      })
    },
    getHeaderRow(sheet) {
      const headers = []
      const range = XLSX.utils.decode_range(sheet['!ref'])
      let C
      const R = range.s.r
      for (C = range.s.c; C <= range.e.c; ++C) {
        const cell = sheet[XLSX.utils.encode_cell({ c: C, r: R })]
        let hdr = 'UNKNOWN ' + C // <-- replace with your desired default
        if (cell && cell.t) hdr = XLSX.utils.format_cell(cell)
        headers.push(hdr)
      }
      return headers
    },
    formatJson_Import(filterVal, results, header) {
      return results.map(v => {
        for (let i = 0; i < header.length; i++) {
          const variable = filterVal[i]
          const mapVariable = header[i]
          v[variable] = v[mapVariable]
          delete v[mapVariable]
        }
        return v
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v, index) => filterVal.map(j => {
        if (j === 'index') {
          return index + 1
        } else if (j === 'statusImport') {
          return this.renderResult(v[j])
        } else {
          return v[j]
        }
      }))
    },
    renderResult(statusImport) {
      if (!statusImport) {
        return ''
      }
      let str = ''
      switch (statusImport) {
        case 1: {
          str = 'Bản ghi thỏa mãn'
          break
        }
        case 2: {
          str = 'Mã ngành sai định dạng'
          break
        }
        case 3: {
          str = 'Mã ngành đã tồn tại'
          break
        }
        case 4: {
          str = 'Mã ngành bị trùng'
          break
        }
        case 5: {
          str = 'Mã ngành không được bỏ trống'
          break
        }
        case 6: {
          str = 'Tên ngành đã tồn tại'
          break
        }
        case 7: {
          str = 'Tên ngành bị trùng'
          break
        }
        case 8: {
          str = 'Tên ngành không được bỏ trống'
          break
        }
        case 9: {
          str = 'Mã ngành, tên ngành không được bỏ trống'
          break
        }
      }
      return str
    },
    formatFilenameExport() {
      const currentDate = new Date()
      return `${currentDate.getFullYear()}${currentDate.getMonth() + 1}${currentDate.getDate()}${currentDate.getHours()}${currentDate.getMinutes()}${currentDate.getSeconds()}_nganhKinhTeSBV`
    },
    convertObjectKey(filterVal, results, header) {
      return results.map(v => {
        for (let i = 0; i < header.length; i++) {
          const variable = filterVal[i]
          const mapVariable = header[i]
          v[variable] = v[mapVariable]
          delete v[mapVariable]
          if (i === 0) {
            delete v[variable]
          }
        }
        return v
      })
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
          const header = this.getHeaderRow(worksheet)
          const filterVal = ['id', 'codeSBV', 'nameSBV']
          const worksheetJSON = XLSX.utils.sheet_to_json(worksheet)
          const results = this.convertObjectKey(filterVal, worksheetJSON, header)
          this.loading = false
          resolve(results)
        }
        reader.readAsArrayBuffer(rawFile)
      })
    }
  }
}
</script>
<style scoped>
.title {
  color: #5E2E86;
  text-align: center;
}
.box_import {
  background-color: rgb(248, 248, 248);
  height: 70px;
  border: 1px solid rgb(0, 0, 0);
  padding: 20px;
  margin: 20px;
  text-align: center;
}
</style>
