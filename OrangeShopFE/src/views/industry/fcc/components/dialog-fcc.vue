<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>{{ title }}</strong></h3>
      <el-form ref="userForm" label-position="left">
        <ValidationProvider
          v-slot="{ errors }"
          name="code"
          :rules="{required: true, regex: /^[0-9]*$/, max: 20}"
        >
          <el-form-item
            prop="code"
            :error="messageError(groupFCCFields.code, errors[0])"
          >
            <label for="code">Mã ngành kinh tế FCC <span class="text-danger">*</span></label>
            <el-input
              id="code"
              ref="code"
              v-model="temp.economicFCCCode"
              class="form-control non-border"
              :disabled="dialogStatus === 'update' || dialogStatus === 'reject'"
              placeholder="Nhập mã ngành kinh tế FCC"
            />
          </el-form-item>
        </ValidationProvider>
        <ValidationProvider
          v-slot="{ errors }"
          name="name"
          :rules="{required: true, regex: /^[A-Z ,]*$/, max: 100}"
        >
          <el-form-item
            prop="name"
            :error="messageError(groupFCCFields.name, errors[0])"
          >
            <label for="unit">Tên ngành kinh tế FCC <span class="text-danger">*</span></label>
            <el-input
              id="code"
              ref="code"
              v-model="temp.economicFCCName"
              class="form-control non-border"
              :disabled="dialogStatus === 'reject'"
              placeholder="Nhập tên ngành kinh tế FCC"
            />
          </el-form-item>
        </ValidationProvider>
        <ValidationProvider
          v-slot="{ errors }"
          name="sbv"
          rules="required"
        >
          <el-form-item
            prop="sbv"
            :error="messageError(groupFCCFields.sbv, errors[0])"
          >
            <label for="sbv_group">Ngành kinh tế SBV <span class="text-danger">*</span></label>
            <el-select
              id="sbv_group"
              ref="sbv"
              v-model="temp.economicSBVId"
              class="form-control"
              :disabled="dialogStatus === 'reject'"
              placeholder="Chọn tên ngành kinh tế SBV"
            >
              <el-option v-for="item in sbvGroups" :key="item.id" :label="item.nameSBV" :value="item.id" />
            </el-select>
          </el-form-item>
        </ValidationProvider>
        <ValidationProvider
          v-slot="{ errors }"
          name="css"
          rules="required"
        >
          <el-form-item
            prop="css"
            :error="messageError(groupFCCFields.css, errors[0])"
          >
            <label for="css_group">Nhóm ngành <span class="text-danger">*</span></label>
            <el-select
              id="css_group"
              v-model="temp.economicCSSId"
              name="css"
              class="form-control"
              :disabled="dialogStatus === 'reject'"
              placeholder="Chọn nhóm ngành"
            >
              <el-option v-for="item in cssGroups" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
        </ValidationProvider>
        <ValidationProvider
          v-if="dialogStatus==='reject' || temp.requestChange"
          v-slot="{ errors }"
          name="requestChange"
          rules="required"
        >
          <el-form-item
            prop="requestChange"
            :error="messageError(groupFCCFields.requestChange, errors[0])"
          >
            <label for="css_group">Yêu cầu điều chỉnh <span class="text-danger">*</span></label>
            <el-input
              id="requestChange"
              ref="requestChange"
              v-model="temp.requestChange"
              :disabled="dialogStatus === 'update'"
              class="form-control non-border"
              placeholder="Nhập yêu cầu điều chỉnh"
            />
          </el-form-item>
        </ValidationProvider>
      </el-form>
      <div slot="footer" class="dialog-footer text-center">
        <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
        <el-button type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="dialogStatus === 'update'" type="success" plain @click="handleApprove">TRÌNH DUYỆT
        </el-button>
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import {
  applyEconomicFCC,
  createEconomicFCC,
  getPageEconomicFCC,
  rejectEconomicFCC,
  updateEconomicFCC
} from '@/api/economic-fcc'
import BaseCommon from '@/utils/base-common'

export default {
  name: 'DialogFcc',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate, BaseCommon],
  props: {
    temp: {
      type: Object,
      default: null
    },
    dialogFormVisible: {
      type: Boolean,
      default: false
    },
    dialogStatus: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      default: ''
    },
    textMap: {
      type: String,
      default: ''
    },
    list: {
      type: Array,
      default: null
    },
    sbvGroups: {
      type: Array,
      default: null
    },
    cssGroups: {
      type: Array,
      default: null
    },
    pagination: {
      type: Object,
      default: null
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogFormVisible', false)
      this.$nextTick(() => {
        this.$refs.observer.reset()
      })
    },
    handleApprove() {
      const data = [this.temp]
      applyEconomicFCC(data).then(() => {
        getPageEconomicFCC({}).then(response => {
          this.$emit('update:list', response.data.result)
        })
        this.notifyInfo('Thông tin', 'Trình duyệt ngành kinh tế FCC thành công')
        this.closeDialog()
      })
    },
    createData() {
      createEconomicFCC(this.temp).then(() => {
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
        this.notifyInfo('Thông tin', 'Thêm mới ngành kinh tế FCC thành công')
        this.closeDialog()
      })
    },
    updateData() {
      updateEconomicFCC(this.temp.id, this.temp).then(() => {
        getPageEconomicFCC({}).then(response => {
          this.$emit('update:list', response.data.result)
        })
        this.notifyInfo('Thông tin', 'Chỉnh sửa ngành kinh tế FCC thành công')
        this.closeDialog()
      })
    },
    submit() {
      if (this.dialogStatus === 'create') {
        this.createData()
      } else if (this.dialogStatus === 'update') {
        this.updateData()
      } else {
        this.rejectEconomicFCC()
      }
    },
    rejectEconomicFCC() {
      rejectEconomicFCC(this.temp.id, this.temp).then(() => {
        getPageEconomicFCC({}).then(response => {
          this.$emit('update:list', response.data.result)
        })
        this.notifyInfo('Thông tin', 'Từ chối ngành kinh tế FCC thành công')
        this.closeDialog()
      })
    },
    messageError(fieldName, error) {
      if (error === undefined) {
        return undefined
      }
      return fieldName + error
    }
  }
}
</script>
<style scoped>

.form-control {
  border: 0;
  padding: 0;
}

.title {
  color: #5E2E86;
  text-align: center;
}
</style>
