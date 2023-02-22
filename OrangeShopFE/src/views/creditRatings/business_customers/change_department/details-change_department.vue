<template>
  <ValidationObserver ref="observer">
    <h3 slot="title" class="title mt-3"><strong>Xem thông tin đơn vị quản lý</strong></h3>
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
        <!-- <div class="col-4">
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
        </div> -->
        <div class="row mt-3">
          <label style="margin-left: 10px">Danh sách đơn vị quản lý
            <span class="text-danger">*  </span>
            <!-- <span class="el-icon-circle-plus-outline" style="color: #EF8C1A" @click="addRow()" /> -->
          </label>
        </div>
        <div class="row mt-3">
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
            <el-table-column label="Thao tác" width="200px" align="center">
              <template slot-scope="">
                <!-- <span class="el-icon-remove-outline" style="color: #FF0000" @click="handleDelete(row, $index)" /> -->
                <!-- <router-link :to="{ path: '#'}" class="el-icon-position" style="color: #EF8C1A" /> -->
                <!-- <span @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A; display:none" /></span> -->
              </template>
            </el-table-column>
            <el-table-column label="Tên đơn vị quản lý">
              <template slot-scope="{row, $index}">
                {{ row.name_dept }}
                <div v-if="row.name_dept === null">
                  <ValidationProvider
                    v-slot="{ errors }"
                    name="name_dept"
                    rules="required"
                  >
                    <el-form-item
                      style="margin-right: 10px; margin-left: -50px"
                      prop="name_dept"
                      :error="messageError(customer_identificationFields.name_dept, errors[0])"
                    >
                      <el-select id="dept_name_select" v-model="listDept[$index].name_dept" value="" class="form-control" placeholder="--Chọn đơn vị quản lý--">
                        <el-option v-for="item in listDeptNotSelected" :key="item.id" :label="item.name_dept" :value="item.name_dept" />
                      </el-select>
                    </el-form-item>
                  </ValidationProvider>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="Mã đơn vị quản lý" width="460px" align="center">
              <template slot-scope="{row, $index}">
                {{ row.code_dept }}
                <div v-if="row.code_dept === null">
                  <ValidationProvider
                    v-slot="{ errors }"
                    name="code_dept"
                    rules="required"
                  >
                    <el-form-item
                      style="margin-right: 20px; margin-left: -50px"
                      prop="code_dept"
                      :error="messageError(customer_identificationFields.code_dept, errors[0])"
                    >
                      <el-select id="dept_code_select" v-model="listDept[$index].code_dept" value="" class="form-control" placeholder="--Chọn mã--">
                        <el-option v-for="item in listDeptNotSelected" :key="item.id" :label="item.code_dept" :value="item.code_dept" />
                      </el-select>
                    </el-form-item>
                  </ValidationProvider>
                </div>
              </template>
            </el-table-column>
            <!-- <el-table-column label="Hiển thị mặc định (HO)" width="150" align="center">
        <template slot-scope="{row}">
          <el-checkbox v-model="row.showDefault" />
        </template>
      </el-table-column> -->
          </el-table>

          <pagination v-show="total>0" :total="listDept.length" :page.sync="queryDept.page" :limit.sync="queryDept.limit" @pagination="getListDept" />

        </div>
      </div>
    </el-form>
    <div class="footer">
      <!-- <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button> -->
      <router-link :to="{ path: '/creditRatingsRouter/business_customers/change_department'}" class="btn btn-outline-secondary me-3" style="">Đóng</router-link>
      <!-- <button class="btn btn-outline-warning" @click="handleSubmit">Đồng ý</button> -->
    </div>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import Pagination from '@/components/Pagination'

export default {
  name: 'DetailsChangeDepartment',
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
      },
      queryDept: {
        page: 1,
        limit: 20,
        sort: '+id'
      },
      listLoading: true,
      listDept: {
      },
      listDeptNotSelected: {
      }
    }
  },
  created() {
    this.getListDept()
    this.getListDeptNotSelected()
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
          name_dept: 'Chi nhánh Đông Đô',
          code_dept: 'DD001'
        },
        {
          id: 2,
          name_dept: 'Chi nhánh Cao Bằng',
          code_dept: 'CB002'
        },
        {
          id: 3,
          name_dept: 'Chi nhanh HongKong',
          code_dept: 'HK003'
        }
      ]
      this.total = this.listDept.length
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    },
    getListDeptNotSelected() {
      this.listLoading = true
      this.listDeptNotSelected = [
        {
          id: 11,
          name_dept: 'Chi nhánh Đông Đô NEW',
          code_dept: 'DD001NEW'
        },
        {
          id: 12,
          name_dept: 'Chi nhánh Cao Bằng NEW',
          code_dept: 'CB002NEW'
        },
        {
          id: 13,
          name_dept: 'Chi nhanh HongKong NEW',
          code_dept: 'HK003NEW'
        }
      ]
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    },
    addRow() {
      var dept = {
        id: this.listDept.length + 1,
        name_dept: null,
        code_dept: null
      }
      this.listDept.push(dept)
    },
    handleDelete(row, $index) {
      console.log($index)
      console.log(row)
      // this.listDept.splice($index, $index)
      this.listDept = this.listDept.filter(data => data.id !== row.id)
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
