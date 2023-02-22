<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>{{ title }}</strong></h3>
      <el-form ref="userForm" label-position="left">
        <div class="row">
          <div class="col-6">
            <ValidationProvider
              v-slot="{ errors }"
              name="unit"
              rules="required"
            >
              <el-form-item
                prop="unit"
                :error="messageError(approverFields.unit, errors[0])"
              >
                <label for="unit">Tên đơn vị quản lý <span class="text-danger">*</span></label>
                <el-select id="unit" ref="unit" v-model="temp.unit" class="form-control" placeholder="Chọn đơn vị quản lý">
                  <el-option v-for="item in units" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-6">
            <el-form-item
              prop="department"
            >
              <label for="department">Phòng ban</label>
              <el-select id="department" ref="department" v-model="temp.department" class="form-control" placeholder="Chọn phòng ban">
                <el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            <ValidationProvider
              v-slot="{ errors }"
              name="position"
              rules="required"
            >
              <el-form-item
                prop="position"
                :error="messageError(approverFields.position, errors[0])"
              >
                <label for="position">Chức vụ <span class="text-danger">*</span></label>
                <el-select id="position" ref="position" v-model="temp.position" class="form-control" placeholder="Nhập chức vụ">
                  <el-option v-for="item in positions" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-6">
            <ValidationProvider
              v-slot="{ errors }"
              name="approver"
              rules="required"
            >
              <el-form-item
                prop="approver"
                :error="messageError(approverFields.approver, errors[0])"
              >
                <label for="approver">Họ tên <span class="text-danger">*</span></label>
                <el-select id="approver" v-model="temp.approver" class="form-control" placeholder="Chọn người phê duyệt">
                  <el-option v-for="item in approvers" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <el-checkbox id="checkbox" label="Hiển thị mặc định (HO)" />
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
  name: 'AddUser',
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
    },
    units: {
      type: Array,
      default: null
    },
    departments: {
      type: Array,
      default: null
    },
    positions: {
      type: Array,
      default: null
    },
    approvers: {
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
