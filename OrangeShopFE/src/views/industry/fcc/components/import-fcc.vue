<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      :visible.sync="dialogImportVisible"
      :before-close.sync="closeDialog"
      width="40%"
    >
      <h3 slot="title" class="title mt-3"><strong>Import ngành kinh tế FCC</strong></h3>
      <el-form ref="dataForm" label-position="left">
        <div class="row">
          <div class="col-2" />
          <div class="col-2">
            <label for="excel">Tải tệp lên <span class="text-danger">*</span></label>
          </div>
          <div class="col-7">
            <ValidationProvider
              ref="file"
              v-slot="{ errors }"
              name="file"
              rules="required|ext:xlsx,xls"
            >
              <el-form-item
                prop="file"
                :error="messageError(groupFCCImportFields.file, errors[0])"
              >
                <input
                  ref="excel-upload-input"
                  class="form-control custom-file-input"
                  type="file"
                  accept=".xlsx, .xls"
                  @change="selected"
                >
              </el-form-item>
            </ValidationProvider>
            <a href="#" role="button" @click="handleExportExample()"><u>Tải file mẫu tại đây</u></a>
          </div>
        </div>
        <div class="row">
          <div class="col-2" />
          <div class="col-9">
            <div
              v-if="validRecord < totalRecord"
              class="text-center p-4 mt-3"
              style="border: solid 1px; color: #C6C6C6"
            >
              <p class="text-danger">Tổng số bản ghi hợp lệ là {{ validRecord }}/{{ totalRecord }}</p>
              <p class="text-danger">Vui lòng tải kết quả và import lại danh sách đúng.</p>
            </div>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer text-center">
        <el-button type="info" plain class="btn btn-outline-primary me-3" @click="closeDialog">ĐÓNG</el-button>
        <el-button type="warning" plain @click="handleSubmit(importData)">LƯU</el-button>
        <el-button v-if="validRecord < totalRecord" type="success" @click="handleDownload">TẢI XUỐNG
        </el-button>
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import XLSX from 'xlsx'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'
import { getPageEconomicFCC, importEconomicFCC } from '@/api/economic-fcc'
import { Message } from 'element-ui'
import BaseCommon from '@/utils/base-common'

export default {
  name: 'ImportFcc',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate, BaseCommon],
  props: {
    dialogImportVisible: {
      type: Boolean,
      default: false
    },
    list: {
      type: Array,
      default: null
    }
  },
  data() {
    return {
      loading: false,
      excelData: {
        header: null,
        results: null
      },
      exampleDataExport:
        [
          {
            index: 0,
            economicFCCCode: 'Economic FCC Code Example 1',
            economicFCCName: 'Economic FCC Name Example 1',
            economicSBVName: 'Economic SBV Name Example 1',
            economicCSSName: 'Economic CSS Name Example 1'
          },
          {
            index: 1,
            economicFCCCode: 'Economic FCC Code Example 2',
            economicFCCName: 'Economic FCC Name Example 2',
            economicSBVName: 'Economic SBV Name Example 2',
            economicCSSName: 'Economic CSS Name Example 2'
          },
          {
            index: 2,
            economicFCCCode: 'Economic FCC Code Example 3',
            economicFCCName: 'Economic FCC Name Example 3',
            economicSBVName: 'Economic SBV Name Example 3',
            economicCSSName: 'Economic CSS Name Example 3'
          }

        ],
      validRecord: null,
      totalRecord: null,
      economicFCCImportList: null
    }
  },
  methods: {
    async selected({ target: { files }}) {
      await this.$refs.file.validate(files)
    },
    closeDialog() {
      this.$emit('update:dialogImportVisible', false)
      this.$refs['excel-upload-input'].value = null
      this.validRecord = 0
      this.totalRecord = 0
      this.$nextTick(() => {
        this.$refs.observer.reset()
      })
    },
    handleExportExample() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(async excel => {
        const tHeader = ['STT', 'Mã ngành kinh tế FCC', 'Tên ngành kinh tế FCC', 'Ngành kinh tế SBV', 'Nhóm ngành CSS']
        const filterVal = ['index', 'economicFCCCode', 'economicFCCName', 'economicSBVName', 'economicCSSName']
        const list = this.exampleDataExport
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
        this.notifyInfo('Thông tin', `${filename}.${bookType} tải xuống thành công`)
        this.downloadLoading = false
      })
    },
    importData() {
      const files = this.$refs['excel-upload-input'].files
      const rawFile = files[0]
      if (!rawFile) return
      this.upload(rawFile).then(response => {
        importEconomicFCC(response).then((importResponse) => {
          this.totalRecord = importResponse.data.totalRecord
          this.validRecord = importResponse.data.validRecord
          this.economicFCCImportList = importResponse.data.economicFCCImportList
          const status = importResponse.data.statusCode
          if (status === 1) {
            getPageEconomicFCC({}).then(response => {
              const pagination = {
                pageNumber: response.data.pageNumber,
                pageSize: 10,
                totalItems: response.data.totalItems,
                totalPages: response.data.totalPages
              }
              this.$emit('update:list', response.data.result)
              this.$emit('update:pagination', pagination)
            })
            this.notifyInfo('Thông tin', `${rawFile.name} tải lên thành công`)
            this.closeDialog()
          }
        })
      }).catch(e => {
        Message({
          message: e.message,
          type: 'error',
          duration: 5 * 1000
        })
      })
    },
    handleDownload() {
      this.exportResult(this.economicFCCImportList)
    },
    upload(rawFile) {
      this.$refs['excel-upload-input'].value = null // fix can't select the same excel
      return this.readerData(rawFile)
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
          if (header.length === 5) {
            const filterVal = ['index', 'economicFCCCode', 'economicFCCName', 'economicSBVName', 'economicCSSName']
            const worksheetJSON = XLSX.utils.sheet_to_json(worksheet)
            const results = this.convertObjectKey(filterVal, worksheetJSON, header)
            resolve(results)
          } else {
            reject({ message: 'Định dạng file không đúng' })
          }
          this.loading = false
        }
        reader.readAsArrayBuffer(rawFile)
      })
    },
    getHeaderRow(sheet) {
      const headers = []
      const range = XLSX.utils.decode_range(sheet['!ref'])
      let C
      const R = range.s.r
      /* start in the first row */
      for (C = range.s.c; C <= range.e.c; ++C) { /* walk every column in the range */
        const cell = sheet[XLSX.utils.encode_cell({ c: C, r: R })]
        /* find the cell in the first row */
        let hdr = 'UNKNOWN ' + C // <-- replace with your desired default
        if (cell && cell.t) hdr = XLSX.utils.format_cell(cell)
        headers.push(hdr)
      }
      return headers
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
    exportResult(list) {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['STT', 'Mã ngành kinh tế FCC', 'Tên ngành kinh tế FCC', 'Ngành kinh tế SBV', 'Nhóm ngành CSS', 'Kết quả']
        const filterVal = ['index', 'economicFCCCode', 'economicFCCName', 'economicSBVName', 'economicCSSName', 'statusCode']
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
        this.notifyInfo('Thông tin', `${filename}.${bookType} tải xuống thành công`)
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v, index) => filterVal.map(j => {
        if (j === 'index') {
          return index + 1
        } else {
          return v[j]
        }
      }))
    },
    formatFilenameExport() {
      const currentDate = new Date()
      return `${currentDate.getFullYear()}${currentDate.getMonth() + 1}${currentDate.getDate()}${currentDate.getHours()}${currentDate.getMinutes()}${currentDate.getSeconds()}_nganhKinhTeFCC`
    },
    isExcel(file) {
      return /\.(xlsx|xls)$/.test(file.name)
    }
  }
}
</script>
<style scoped>

.title {
  color: #5E2E86;
  text-align: center;
}
</style>
