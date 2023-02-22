<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      :visible.sync="dialogImportVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>Import thuộc tính</strong></h3>
      <el-form ref="dataForm" label-position="left" label-width="70px">
        <ValidationProvider
          ref="file"
          v-slot="{ errors }"
          name="file"
          rules="required"
        >
          <el-form-item
            prop="file"
            :error="messageError(configParameterPropertyFields.file, errors[0])"
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
      <div class="col-12 text-center">
        <span href="#" role="button" style="margin-top: -4px; color:blue; text-decoration: underline" @click="handleExport_Example">Tải file mẫu tại đây</span>
        <br>
        <div v-if="validRequest < totalRequest" class="box_import" style="text-align: center;">
          <span style="color: red;">Tổng số thuộc tính trùng mã trên hệ thống là {{ duplicateCodeRequest }} / {{ totalRequest }}</span>
          <br>
          <span style="color: red;">Tổng số thuộc tính mới hợp lệ là {{ validRequest - duplicateCodeRequest }} / {{ totalRequest }}</span>
          <br>
          <span style="color: red;">Bạn có chắc chắn muốn update các thuộc tình có mã đã tồn tại trên hệ thống </span>
          <br>
          <span style="color: red;">  và cập nhật thêm các thuộc tính mới hợp lệ vào hệ thống không?</span>
        </div>
        <br>
        <div v-if="totalRequest !==null">
          <span href="#" role="button" style="margin-top: -4px; color:blue; text-decoration: underline" @click="handleExport_ResImport">Tải File kết quả Import tại đây</span>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="info" plain @click="closeDialog">Đóng</el-button>
        <el-button v-if="totalRequest ===null" type="warning" plain @click="handleSubmit(importData)">Lưu</el-button>
        <el-button v-if="totalRequest !==null" type="warning" plain @click="forceImportData()">Đồng ý</el-button>

      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import baseCommon from '@/utils/base-common'
import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import { importConfigParamProperty, forceImportConfigParamProperty } from '@/api/config-parameter-property'
import XLSX from 'xlsx'
// import axios from 'axios'

export default {
  name: 'ImportProperty',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate, baseCommon],
  props: {
    dialogImportVisible: {
      type: Boolean,
      default: false
    },
    parent: {
      type: Object,
      default: null
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
      dataExcel: [],
      duplicateCodeRequest: 0,
      validRequest: null,
      totalRequest: null,
      exampleDataExport:
        [
          {
            index: 0,
            codeProperty: 'codeProperty_EXAMPLE_01',
            nameProperty: 'nameProperty_EXAMPLE_01'
          },
          {
            index: 1,
            codeProperty: 'codeProperty_EXAMPLE_02',
            nameProperty: 'nameProperty_EXAMPLE_02'
          },
          {
            index: 2,
            codeProperty: 'codeProperty_EXAMPLE_03',
            nameProperty: 'nameProperty_EXAMPLE_03'
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
        if (response !== null && response !== undefined && response.length > 0) {
          for (let i = 0; i < response.length; i++) {
            response[i].idTypeConfig = this.parent.id
          }
        }
        importConfigParamProperty(response).then((importResponse) => {
          this.result_import = importResponse.data.configParameterPropertyImportList
          this.validRequest = importResponse.data.validRecord
          this.totalRequest = importResponse.data.totalRecord
          if (this.result_import !== null && this.result_import !== undefined && this.result_import.length > 0) {
            for (let i = 0; i < this.result_import.length; i++) {
              if (this.result_import[i].statusCode === 3) {
                this.duplicateCodeRequest += 1
                this.dataExcel.push(response[i])
              }
            }
          }

          if (this.totalRequest === this.validRequest) {
            this.notifySuccess('Thông tin', 'Import thành công')
            this.$emit('update:dialogImportVisible', false)
          }
        })
      })
    },
    forceImportData() {
      forceImportConfigParamProperty(this.dataExcel).then((importResponse) => {
        if (importResponse !== null && importResponse !== undefined) {
          this.notifySuccess('Thông tin', 'Import thành công')
          this.$emit('update:dialogImportVisible', false)
          this.$emit('reload')
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
      return this.readerData(rawFile)
    },
    handleExport_Example() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        var headerType = ''
        if (this.type === 1) {
          headerType = 'Lý do tăng hạng'
        } else if (this.type === 2) {
          headerType = 'Lý do giảm hạng'
        }
        const tHeader = ['STT', headerType]
        const filterVal = ['index', 'content']

        const data = this.formatJson(filterVal, this.exampleDataExport)
        const filename = 'Example_File_Import_Rule_Change_Rank'
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
        const tHeader = ['STT', 'Mã thuộc tính', 'Tên thuộc tính', 'Kết quả']
        const filterVal = ['index', 'codeProperty', 'nameProperty', 'statusCode']
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
        } else if (j === 'statusCode') {
          return this.renderResult(v[j])
        } else {
          return v[j]
        }
      }))
    },
    renderResult(statusCode) {
      if (!statusCode) {
        return ''
      }
      let str = ''
      switch (statusCode) {
        case 1: {
          str = 'Bản ghi thỏa mãn'
          break
        }
        case 2: {
          str = 'Mã thuộc tính bắt buộc nhập'
          break
        }
        case 3: {
          str = 'Mã thuộc tính đã tồn tại. Cập nhật thông tin mới cho mã thuộc tính'
          break
        }
        case 4: {
          str = 'Mã thuộc tính bị trùng'
          break
        }
        case 5: {
          str = 'Mã thuộc tính sai định dạng'
          break
        }
        case 6: {
          str = 'Tên thuộc tính bắt buộc nhập'
          break
        }
        case 7: {
          str = 'Tên thuộc tính không được trùng tên thuộc tính trên hệ thống'
          break
        }
        case 8: {
          str = 'Tên thuộc tính bị trùng'
          break
        }
      }

      return str
    },
    formatFilenameExport() {
      const currentDate = new Date()
      return `${currentDate.getFullYear()}${currentDate.getMonth() + 1}${currentDate.getDate()}${currentDate.getHours()}${currentDate.getMinutes()}${currentDate.getSeconds()}_thuocTinhCauHinh`
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
          const filterVal = ['id', 'codeProperty', 'nameProperty']
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
  height: 130px;
  border: 1px solid rgb(0, 0, 0);
  padding: 20px;
  margin: 20px;
  text-align: center;
}
</style>
