<template>
  <div>
    <el-dialog :title="isAddNew ? 'Thêm mới chức vụ' : 'Chỉnh sửa chức vụ'" :visible.sync="showDialog">
      <ValidationObserver ref="createUpdatePosition" v-slot="{ handleSubmit }">
        <el-form class="form-container" :model="position">
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="branchName"
                rules="requiredSelect"
              >
                <el-form-item
                  prop="branchName"
                  :error="messageError(positions.branchName, errors[0])"
                >
                  <label>Tên đơn vị<span class="text-danger"> * </span></label><br>
                  <el-select
                    v-model="position.branchCode"
                    class="w-90"
                    placeholder="Chọn đơn vị"
                    :loading="loadingSelectForBranches"
                    @change="position.departmentCode = null"
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
                  v-model="position.status"
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
          <el-row class="pb-3">
            <el-col :span="24">
              <ValidationProvider
                v-slot="{ errors }"
                name="departmentName"
                rules="requiredSelect"
              >
                <el-form-item
                  prop="branchName"
                  :error="messageError(positions.departmentName, errors[0])"
                >
                  <label>Tên phòng ban<span class="text-danger"> * </span></label><br>
                  <el-select
                    v-model="position.departmentCode"
                    class="w-full"
                    placeholder="Chọn phòng ban"
                    :loading="loadingSelectForDepartments"
                    :disabled="position.branchCode === null"
                  >
                    <el-option
                      v-for="item in departments"
                      :key="item.code"
                      :label="item.name"
                      :value="item.code"
                    />
                  </el-select>
                </el-form-item>
              </ValidationProvider>
            </el-col>
          </el-row>
          <el-row class="pb-3">
            <el-col :span="24">
              <ValidationProvider
                v-slot="{ errors }"
                name="name"
                rules="requiredSelect"
              >
                <el-form-item
                  prop="branchName"
                  :error="messageError(positions.name, errors[0])"
                >
                  <label>Tên chức vụ<span class="text-danger"> * </span></label><br>
                  <el-input
                    v-model="position.name"
                    placeholder="Nhập tên chức vụ"
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

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import * as positionservice from '../service/position-service'
import * as branchservice from '@/views/sys-branch/service/branch-service'
import * as departmentservice from '@/views/sys-department/service/department-service'
import BaseCommon from '@/utils/base-common'
export default {
  name: 'UpdatePosition',
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
      position: {
        id: null,
        branchCode: null,
        status: 1,
        departmentCode: null,
        name: null,
        version: null
      },
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
      isAddNew: true,
      branches: [],
      departments: [],
      loadingSelectForBranches: true,
      loadingSelectForDepartments: true
    }
  },
  watch: {
    'showDialog': {
      handler(value) {
        if (value) {
          this.getDataBranch()
          this.directInsertOrUpdate()
        } else {
          this.hideDialog()
        }
      }
    },
    'position.branchCode': {
      handler(value) {
        if (value) {
          this.getDataDepartment(value)
        }
      }
    }
  },
  methods: {
    async getDataBranch() {
      this.loadingSelectForBranches = true
      const params = {}
      const response = await branchservice.getDataBranches(params)
      if (response.code === 200) {
        this.branches = response.data
        this.loadingSelectForBranches = false
      }
    },
    async getDataDepartment(obj) {
      console.log(obj)
      this.loadingSelectForDepartments = true
      const params = {
        branchCode: obj
      }
      const response = await departmentservice.getDataDepartments(params)
      if (response.code === 200) {
        this.departments = response.data
        this.loadingSelectForDepartments = false
      }
    },
    hideDialog() {
      this.$nextTick(() => {
        this.$refs.createUpdatePosition.reset()
      })
      this.$emit('hideDialog', false)
    },
    directInsertOrUpdate() {
      if (this.id === null) {
        this.isShowAddNew()
      } else {
        this.isShowEdit()
      }
    },
    isShowAddNew() {
      this.position = {
        branchCode: null,
        status: 1,
        departmentCode: null,
        name: null,
        version: null
      }
      this.isAddNew = true
    },
    isShowEdit() {
      this.getPosition()
      this.isAddNew = false
    },
    async getPosition() {
      const response = await positionservice.getPosition(this.id)
      if (response.code === 200) {
        this.position = this.mapResponse(response.data)
        this.notifyInfo('Thông tin', response.message)
      }
    },
    mapResponse(data) {
      const positionResponse = {
        name: data.name,
        branchCode: data.branchCode,
        departmentCode: data.departmentCode,
        status: data.status,
        version: data.version
      }
      return positionResponse
    },
    async save() {
      if (this.isAddNew) {
        const positionInsertRq = this.mapPositionInsert()
        const response = await positionservice.insertPosition(positionInsertRq)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      } else {
        const positionUpdateRq = this.mapPositionUpdate()
        const response = await positionservice.updatePosition(this.id, positionUpdateRq)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      }
    },
    mapPositionInsert() {
      const positionInsertRq = {
        branchCode: this.position.branchCode,
        status: this.position.status,
        departmentCode: this.position.departmentCode,
        name: this.position.name
      }
      return positionInsertRq
    },
    mapPositionUpdate() {
      const positionUpdateRq = {
        branchCode: this.position.branchCode,
        status: this.position.status,
        departmentCode: this.position.departmentCode,
        name: this.position.name,
        version: this.position.version
      }
      return positionUpdateRq
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
