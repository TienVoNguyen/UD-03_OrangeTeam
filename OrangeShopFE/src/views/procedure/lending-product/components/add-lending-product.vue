<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>{{ title }}</strong></h3>
      <el-form ref="lendingProductForm" label-position="left">
        <div class="row">
          <div class="col-6">
            <ValidationProvider
              v-slot="{ errors }"
              name="type"
              rules="required"
            >
              <el-form-item
                prop="type"
                :error="messageError(lendingFields.type, errors[0])"
              >
                <label for="type">Loại chấm điểm <span class="text-danger">*</span></label>
                <el-select id="type" v-model="temp.type" class="form-control non-border" placeholder="---Tất cả---">
                  <el-option label="Thông thường" value="1" />
                  <el-option label="Định kỳ" value="2" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-6">
            <ValidationProvider
              v-slot="{ errors }"
              name="name"
              rules="required"
            >
              <el-form-item
                prop="name"
                :error="messageError(lendingFields.name, errors[0])"
              >
                <label for="name">Loại hình sản phẩm <span class="text-danger">*</span></label>
                <el-input id="name" ref="name" v-model="temp.name" class="form-control non-border" placeholder="Nhập tên loại hình sản phẩm" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            <el-form-item>
              <label>Thời gian xử lý hồ sơ <span class="text-danger">*</span></label>
              <div class="row">
                <div class="col-4">
                  <el-input v-model="temp.day" class="form-control non-border" placeholder="Nhập ngày" />
                </div>
                <div class="col-4">
                  <el-input v-model="temp.hour" class="form-control non-border" placeholder="Nhập giờ" />
                </div>
                <div class="col-4">
                  <el-input v-model="temp.minute" class="form-control non-border" placeholder="Nhập phút" />
                </div>
              </div>
            </el-form-item>
          </div>
          <div class="col-6" />
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button>
        <button class="btn btn-outline-warning" @click="handleSubmit(submit)">Đồng ý</button>
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'

export default {
  name: 'AddLendingProduct',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate],
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
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogFormVisible', false)
      this.$nextTick(() => {
        this.$refs.observer.reset()
      })
    },
    createData() {
      //
    },
    updateData() {
      //
    },
    submit() {
      if (this.dialogStatus === 'create') {
        this.createData()
      } else {
        this.updateData()
      }
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
.form-control {
  border: 0;
  padding: 0;
}
.title {
  color: #5E2E86;
  text-align: center;
}
</style>
