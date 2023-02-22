<template>
  <div>
    <el-dialog :title="isAddNew ? 'Thêm mới phòng ban' : 'Chỉnh sửa phòng ban'" :visible.sync="showDialog" :before-close="handleClose">
      <ValidationObserver ref="createUpdateDepartment" v-slot="{ handleSubmit }">
        <el-form class="form-container" :model="branch">
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="branchName"
                rules="requiredSelect"
              >
                <el-form-item
                  prop="branchName"
                  :error="messageError(departments.branchName, errors[0])"
                >
                  <label>Đơn vị<span class="text-danger"> * </span></label><br>
                  <el-select
                    v-model="department.branchCode"
                    class="w-90"
                    placeholder="Chọn đơn vị"
                    :loading="loadingSelect"
                  >
                    <el-option
                      v-for="item in branches"
                      :key="item.code"
                      :label="item.name"
                      :value="item.code"
                    />
                  </el-select>
                </el-form-item>
              </ValidationProvider>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <label>Trạng thái<span class="text-danger"> * </span></label><br>
                <el-select
                  v-model="department.status"
                  class="w-full"
                  placeholder="Lựa chọn trạng thái"
                >
                  <el-option
                    v-for="item in listStatus"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <ValidationProvider
                v-slot="{ errors }"
                name="name"
                rules="required"
              >
                <el-form-item
                  prop="name"
                  :error="messageError(departments.name, errors[0])"
                >
                  <label>Tên phòng ban<span class="text-danger"> * </span></label><br>
                  <el-input
                    v-model="department.name"
                    placeholder="Nhập tên phòng ban"
                    class="w-full"
                    maxlength="200"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
          </el-row>
          <el-row class="pt-4">
            <el-form-item class="dialog-footer pr-1-5">
              <el-button plain type="danger" @click="hideDialog">Đóng</el-button>
              <el-button plain type="primary" @click="handleSubmit(save)">{{ isAddNew ? 'Đồng ý' : 'Cập nhật' }}</el-button>
            </el-form-item>
          </el-row>
        </el-form>
      </ValidationObserver>
    </el-dialog>
  </div>
</template>
<script>
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'
import * as branchservice from '@/views/sys-branch/service/branch-service'
import * as departmentservice from '../service/department-service'
import BaseCommon from '@/utils/base-common'
export default {
  name: 'ModifyDialog',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate, BaseCommon],
  props: {
    showDialog: {
      type: Boolean,
      default: false
    },
    id: {
      type: Number,
      default: null
    }
  },
  data() {
    return {
      branch: {
        id: null,
        branchCode: null,
        branchCodeNumber: null,
        branchTypeName: null,
        branchName: null,
        districtName: null,
        status: true,
        description: null,
        version: null
      },
      districtBranch: [],
      branchType: [],
      isAddNew: true,
      listStatus: [
        {
          value: 0,
          label: 'Tạm ngưng'
        },
        {
          value: 1,
          label: 'Hoạt động'
        }
      ],
      department: {
        id: null,
        branchCode: null,
        status: null,
        name: null,
        version: null
      },
      branches: [],
      loadingSelect: true
    }
  },
  watch: {
    showDialog: {
      handler(value) {
        if (value) {
          this.getDataBranch()
          this.directInsertOrUpdate()
        } else {
          this.hideDialog()
        }
      }
    }
  },
  created() {
  },
  methods: {
    async getDataBranch() {
      this.loadingSelect = true
      const params = {
        status: 1,
        deleted: 0
      }
      const response = await branchservice.getDataBranches(params)
      if (response.code === 200) {
        this.branches = response.data
        this.loadingSelect = false
      }
    },
    directInsertOrUpdate() {
      if (this.id === null) {
        this.isShowAddNew()
      } else {
        this.isShowEdit()
      }
    },
    isShowAddNew() {
      this.department = {
        id: null,
        branchCode: null,
        status: 1,
        name: null
      }
      this.isAddNew = true
    },
    isShowEdit() {
      this.isAddNew = false
      this.getDepartment()
    },
    hideDialog() {
      this.$nextTick(() => {
        this.$refs.createUpdateDepartment.reset()
      })
      this.$emit('hideDialog', false)
    },
    convertTextStatus(obj) {
      if (obj) {
        return 'Hoạt động'
      } else {
        return 'Không hoạt động'
      }
    },
    async save() {
      if (this.isAddNew) {
        const departmentRq = this.mapDepartmentInsert()
        const response = await departmentservice.insertDepartment(departmentRq)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      } else {
        const departmentUpdateRq = this.mapDepartmentUpdate()
        const response = await departmentservice.updateDepartment(this.id, departmentUpdateRq)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      }
    },
    mapDepartmentInsert() {
      const departmentRq = {
        branchCode: this.department.branchCode,
        name: this.department.name,
        status: this.department.status
      }
      return departmentRq
    },
    mapDepartmentUpdate() {
      const departmentUpdateRq = {
        branchCode: this.department.branchCode,
        name: this.department.name,
        status: this.department.status,
        version: this.department.version
      }
      return departmentUpdateRq
    },
    async getDepartment() {
      const response = await departmentservice.getDepartment(this.id)
      if (response.code === 200) {
        this.department = this.mapResponse(response.data)
        this.notifyInfo('Thông tin', response.message)
      }
    },
    mapResponse(data) {
      const departmentResponse = {
        name: data.name,
        branchCode: data.branchCode,
        status: data.status,
        version: data.version
      }
      return departmentResponse
    }
  }
}
</script>

  <style lang="scss" scoped>
      .form-container{
        padding-left: 15px;
        padding-right: 15px;
      }
      .el-form-item{
        margin-bottom: 0;
      }
      .dialog-footer {
        padding: 0px !important;
        padding-top: 10px  !important;
        text-align: right  !important;
        bottom: 0;
        -webkit-box-sizing: border-box  !important;
        box-sizing: border-box  !important;
    }
    </style>

