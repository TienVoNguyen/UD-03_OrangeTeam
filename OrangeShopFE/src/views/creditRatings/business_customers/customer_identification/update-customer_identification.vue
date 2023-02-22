<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <!-- <h3 slot="title" class="title mt-3"><strong>Cập nhật định danh khách hàng</strong></h3> -->
    <span class="sub_title"> I. Thông tin khách hàng </span>
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px">
      <div class="row mt-3">
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
      </div>
      <span class="sub_title"> II. Đơn vị quản lý </span>
      <div class="row mt-3" style="margin-left: 70px;">
        <el-table
          v-loading="listLoading"
          :data="listDept"
          :header-cell-style="{ background: '#5E2E864D', color: '#000000', borderRightStyle: 'dashed' }"
          :cell-style="{borderRightStyle: 'dashed'}"
          border
          fit
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column label="Tên đơn vị quản lý" align="center">
            <template slot-scope="{row}">
              {{ row.name }}
            </template>
          </el-table-column>
          <el-table-column label="Mã đơn vị quản lý" width="330px" align="center">
            <template slot-scope="{row}">
              <span>{{ row.code }}</span>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="listDept.length>0" :total="listDept.length" :page.sync="queryDept.page" :limit.sync="queryDept.limit" @pagination="getListDept" />

      </div>

      <span class="sub_title"> III. Lịch sử cập nhật </span>
      <div class="row mt-3" style="margin-left: 70px;">
        <el-table
          v-loading="listLoading"
          :data="listHistory"
          :header-cell-style="{ background: '#5E2E864D', color: '#000000', borderRightStyle: 'dashed' }"
          :cell-style="{borderRightStyle: 'dashed'}"
          border
          fit
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column label="Mã số thuế" align="center">
            <template slot-scope="{row}">
              {{ row.code_tax }}
            </template>
          </el-table-column>
          <el-table-column label="Mã số ĐKKD" align="center">
            <template slot-scope="{row}">
              <span>{{ row.code_reg }}</span>
            </template>
          </el-table-column>
          <el-table-column label="Mã CIF (FCC)" align="center">
            <template slot-scope="{row}">
              <span>{{ row.code_cif }}</span>
            </template>
          </el-table-column>
          <el-table-column label="Mã CIF (Tạm)" align="center">
            <template slot-scope="{row}">
              <span>{{ row.code_cif_temp }}</span>
            </template>
          </el-table-column>

          <el-table-column label="Tên doanh nghiệp" align="center">
            <template slot-scope="{row}">
              <span>{{ row.name_dn }}</span>
            </template>
          </el-table-column>

          <el-table-column label="Tên ĐVQL" align="center">
            <template slot-scope="{row}">
              <span>{{ row.name_dept }}</span>
            </template>
          </el-table-column>

          <el-table-column label="Mã ĐVQL" align="center">
            <template slot-scope="{row}">
              <span>{{ row.code_dept }}</span>
            </template>
          </el-table-column>

          <el-table-column label="Người cập nhật" align="center">
            <template slot-scope="{row}">
              <span>{{ row.user_updated }}</span>
            </template>
          </el-table-column>
          <el-table-column label="Ngày cập nhật" align="center">
            <template slot-scope="{row}">
              <span>{{ row.date_updated }}</span>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="listHistory.length>0" :total="listHistory.length" :page.sync="queryDept.page" :limit.sync="queryDept.limit" @pagination="getListHistory" />

      </div>
    </el-form>
    <div class="footer">
      <!-- <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button> -->
      <router-link :to="{ path: '/creditRatingsRouter/business_customers/customer_identification'}" class="btn btn-outline-secondary me-3" style="">Đóng</router-link>
      <button class="btn btn-outline-warning" @click="handleSubmit">Cập nhật</button>
    </div>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import Pagination from '@/components/Pagination'

export default {
  name: 'AddCustomerIdentification',
  components: { ValidationObserver, ValidationProvider, Pagination },
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
    }
  },
  data() {
    return {
      rules: {
        // name: [{ required: true, message: 'Name is required', trigger: 'change' }],
        // slug: [{ required: true, message: 'Slug is required', trigger: 'change' }]
      },
      listLoading: true,
      tempCus: {
        code_tax: null,
        code_reg: null,
        code_cif: null,
        code_cif_temp: null,
        name_dn: null,
        name_dept: null,
        code_dept: null
      },
      listDept: [],
      queryDept: {
        page: 1,
        limit: 20,
        sort: '+id'
      },
      listHistory: [],
      queryHistory: {
        page: 1,
        limit: 20,
        sort: '+id'
      }
    }
  },
  created() {
    this.getListDept()
    this.getListHistory()
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
    },
    getListDept() {
      this.listLoading = true
      this.listDept = [
        {
          id: 1,
          name: 'Chi nhánh Đông Đô',
          code: 'DD001'
        },
        {
          id: 2,
          name: 'Chi nhánh Cao Bằng',
          code: 'CB002'
        },
        {
          id: 3,
          name: 'Chi nhanh HongKong',
          code: 'HK003'
        }
      ]
      this.total = this.listDept.length
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    },
    getListHistory() {
      this.listLoading = true
      this.listHistory = [
        {
          id: 1,
          code_tax: '876543',
          code_reg: '54345678',
          code_cif: 'F676543',
          code_cif_temp: 'K198765',
          name_dn: 'Công ty TNHH X',
          name_dept: 'Chi nhánh Đông đô',
          code_dept: 'DD001',
          user_updated: 'TungNT',
          date_updated: '11/11/2011'
        },
        {
          id: 2,
          code_tax: '123456',
          code_reg: '23456',
          code_cif: 'F98765r',
          code_cif_temp: 'F9876543',
          name_dn: 'Công ty TNHH X1',
          name_dept: 'Chi nhánh Đông đô',
          code_dept: 'DD002',
          user_updated: 'TungNT',
          date_updated: '11/12/2011'
        },
        {
          id: 3,
          code_tax: '2345678',
          code_reg: '65432',
          code_cif: 'F12345',
          code_cif_temp: 'K987654',
          name_dn: 'Công ty TNHH X2',
          name_dept: 'Chi nhánh Đông đô',
          code_dept: 'DD003',
          user_updated: 'TungNT',
          date_updated: '13/11/2011'
        }
      ]
      this.total = this.listHistory.length
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
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

.sub_title {
    font-weight: bold;
    color: #EF8C1A;
    margin-left: 70px;
}
</style>
