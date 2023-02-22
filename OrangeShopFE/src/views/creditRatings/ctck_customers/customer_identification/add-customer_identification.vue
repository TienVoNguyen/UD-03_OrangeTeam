<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <h3 slot="title" class="title mt-3"><strong>Thêm mới định danh khách hàng</strong></h3>
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px">
      <div class="row">
        <div class="col-4">
          <ValidationProvider
            v-slot="{ errors }"
            name="code_tax"
            rules="required"
          >
            <el-form-item
              prop="code_tax"
              :error="messageError(customer_identificationFields.code_tax, errors[0])"
            >
              <label>Mã số thuế <span class="text-danger">*</span></label>
              <el-input id="code_tax" v-model="tempCus.code_tax" value="" class="form-control" placeholder="Nhập mã số thuế" />
            </el-form-item>
          </ValidationProvider>
        </div>
        <div class="col-4">
          <ValidationProvider
            v-slot="{ errors }"
            name="code_reg"
            rules="required"
          >
            <el-form-item
              prop="code_reg"
              :error="messageError(customer_identificationFields.code_reg, errors[0])"
            >
              <label>Mã số đăng ký kinh doanh <span class="text-danger">*</span></label>
              <el-input id="code_reg" v-model="tempCus.code_reg" value="" class="form-control" placeholder="Nhập mã số đăng ký kinh doanh" />
            </el-form-item>
          </ValidationProvider>
        </div>
        <div class="col-2">
          <ValidationProvider
            name="code_cif_temp"
          >
            <el-form-item
              prop="code_cif_temp"
            >
              <label>Mã CIF (FCC)</label>
              <el-input id="code_cif" v-model="tempCus.code_cif" style="width:85%" value="" class="form-control non-border" placeholder="Nhập mã CIF (FCC)" />
            </el-form-item>
          </ValidationProvider>
        </div>
        <div class="col-2">
          <ValidationProvider
            name="code_cif_temp"
          >
            <el-form-item
              prop="code_cif_temp"
            >
              <label>Mã CIF (Tạm)</label>
              <el-input id="code_cif_temp" v-model="tempCus.code_cif_temp" style="width:85%" value="" class="form-control non-border" placeholder="Nhập mã CIF (Tạm)" />
            </el-form-item>
          </ValidationProvider>
        </div>
      </div>

      <div class="row">
        <div class="col-4">
          <ValidationProvider
            v-slot="{ errors }"
            name="name_dn"
            rules="required"
          >
            <el-form-item
              prop="name_dn"
              :error="messageError(customer_identificationFields.name_dn, errors[0])"
            >
              <label>Tên doanh nghiệp <span class="text-danger">*</span></label>
              <el-input id="name_dn" v-model="tempCus.name_dn" value="" class="form-control" placeholder="Nhập tên doanh nghiệp" />
            </el-form-item>
          </ValidationProvider>
        </div>
        <div class="col-4">
          <ValidationProvider
            v-slot="{ errors }"
            name="name_dept"
            rules="required"
          >
            <el-form-item
              prop="name_dept"
              :error="messageError(customer_identificationFields.name_dept, errors[0])"
            >
              <label>Tên đơn vị quản lý <span class="text-danger">*</span></label>
              <el-input id="name_dept" v-model="tempCus.name_dept" value="" class="form-control" placeholder="Nhập tên đơn vị quản lý" />
            </el-form-item>
          </ValidationProvider>
        </div>
        <div class="col-4">
          <ValidationProvider
            v-slot="{ errors }"
            name="code_dept"
            rules="required"
          >
            <el-form-item
              prop="code_dept"
              :error="messageError(customer_identificationFields.code_dept, errors[0])"
            >
              <label>Mã đơn vị quản lý <span class="text-danger">*</span></label>
              <el-input id="code_dept" v-model="tempCus.code_dept" value="" class="form-control" placeholder="Nhập mã đơn vị quản lý" />
            </el-form-item>
          </ValidationProvider>
        </div>
      </div>
    </el-form>
    <div class="footer">
      <!-- <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button> -->
      <router-link :to="{ path: '/creditRatingsRouter/ctck_customers/customer_identification'}" class="btn btn-outline-secondary me-3" style="">Đóng</router-link>
      <button class="btn btn-outline-warning" @click="handleSubmit">Đồng ý</button>
    </div>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'

export default {
  name: 'AddCustomerIdentification',
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
    list: {
      type: Array,
      default: null
    }
  },
  data() {
    return {
      rules: {
        // name: [{ required: true, message: 'Name is required', trigger: 'change' }],
        // slug: [{ required: true, message: 'Slug is required', trigger: 'change' }]
      },
      tempCus: {
        code_tax: null,
        code_reg: null,
        code_cif: null,
        code_cif_temp: null,
        name_dn: null,
        name_dept: null,
        code_dept: null
      }
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogFormVisible', false)
      this.$nextTick(() => {
        this.$refs.observer.reset()
      })
    },
    handleSubmit() {
      this.createData()
    },
    createData() {

    },
    updateData() {

    },
    openSuccess() {
      this.$notify({
        title: 'Success',
        message: 'This is a success message',
        type: 'success',
        position: 'bottom-right'
      })
    },
    openError() {
      this.$notify.error({
        title: 'Error',
        message: 'This is a Error message',
        position: 'bottom-right'
      })
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
.footer {
    position: fixed;
    height: 70px;
    bottom: 0px;
    /* left: 0px; */
    right: 20px;
    margin-bottom: 0px;
}
body {
    margin-bottom:50px;
}
</style>
