<template>
  <div>
    <el-dialog :title="isAddNew ? 'Thêm mới đơn vị' : 'Chỉnh sửa đơn vị'" :visible.sync="showDialog">
      <ValidationObserver ref="createUpdateBranch" v-slot="{ handleSubmit }">
        <el-form class="form-container" :model="branch">
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="branchCode"
                rules="required"
              >
                <el-form-item
                  prop="branchCode"
                  :error="messageError(branches.branchCode, errors[0])"
                >
                  <label>Mã đơn vị<span class="text-danger"> * </span></label><br>
                  <el-input
                    ref="branchCode"
                    v-model="branch.branchCode"
                    :disabled="!isAddNew"
                    placeholder="Nhập mã đơn vị"
                    class="w-90"
                    maxlength="20"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="branchCodeNumber"
                rules="required"
              >
                <el-form-item
                  prop="branchCodeNumber"
                  :error="messageError(branches.branchCodeNumber, errors[0])"
                >
                  <label>Mã số đơn vị<span class="text-danger"> * </span></label><br>
                  <el-input
                    ref="branchCodeNumber"
                    v-model="branch.branchCodeNumber"
                    oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*?)\..*/g, '$1');"
                    :disabled="!isAddNew"
                    placeholder="Nhập mã số đơn vị"
                    class="w-full"
                    maxlength="20"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
          </el-row>
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="branchName"
                rules="required"
              >
                <el-form-item
                  prop="branchName"
                  :error="messageError(branches.branchName, errors[0])"
                >
                  <label>Tên đơn vị<span class="text-danger"> * </span></label><br>
                  <el-input
                    ref="branchName"
                    v-model="branch.branchName"
                    placeholder="Nhập tên đơn vị"
                    class="w-90"
                    maxlength="200"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="branchTypeName"
                rules="required"
              >
                <el-form-item
                  prop="branchTypeName"
                  :error="messageError(branches.branchTypeName, errors[0])"
                >
                  <label>Loại đơn vị<span class="text-danger"> * </span></label><br>
                  <el-select
                    ref="branchTypeName"
                    v-model="branch.branchTypeName"
                    class="w-100"
                    filterable
                    placeholder="Lựa chọn loại đơn vị"
                  >
                    <el-option
                      v-for="item in branchType"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name"
                    />
                  </el-select>
                </el-form-item>
              </ValidationProvider>
            </el-col>
          </el-row>
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="districtName"
                rules="required"
              >
                <el-form-item
                  prop="districtName"
                  :error="messageError(branches.districtName, errors[0])"
                >
                  <label>Miền<span class="text-danger"> * </span></label><br>
                  <el-select
                    ref="districtName"
                    v-model="branch.districtName"
                    class="w-90"
                    filterable
                    placeholder="Lựa chọn miền"
                  >
                    <el-option
                      v-for="item in districtBranch"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name"
                    />
                  </el-select>
                </el-form-item>
              </ValidationProvider>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <label>Trạng thái<span class="text-danger"> * </span></label><br>
                <el-select
                  v-model="branch.status"
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
              <el-form-item>
                <label>Mô tả</label>
                <el-input
                  v-model="branch.description"
                  type="textarea"
                  :autosize="{ minRows: 4, maxRows: 10}"
                  placeholder="Nhập mô tả"
                  class="w-full"
                  maxlength="500"
                  show-word-limit
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
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
import * as branchservice from '../service/branch-service'
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
        status: 1,
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
      ]

    }
  },
  watch: {
    'showDialog': {
      handler(value) {
        if (value) {
          this.getTaxonomy()
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
    async getTaxonomy() {
      const params = {
        groupCode: 'DISTRICT_BRANCH'
      }
      const response = await branchservice.getTaxonomy(params)
      if (response.code === 200) {
        this.districtBranch = response.data.result
      }

      const paramsType = {
        groupCode: 'BRANCH_TYPE'
      }
      const responseType = await branchservice.getTaxonomy(paramsType)
      if (responseType.code === 200) {
        this.branchType = responseType.data.result
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
      this.branch = {
        id: null,
        branchCode: null,
        branchCodeNumber: null,
        branchTypeName: null,
        branchName: null,
        districtName: null,
        status: 1,
        description: null
      }
      this.isAddNew = true
    },
    isShowEdit() {
      this.isAddNew = false
      this.getbranch()
    },
    hideDialog() {
      this.$nextTick(() => {
        this.$refs.createUpdateBranch.reset()
      })
      this.$emit('hideDialog', false)
    },
    async save() {
      if (this.isAddNew) {
        const branchRq = this.mapBranchInsert()
        const response = await branchservice.insertBranch(branchRq)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      } else {
        const branchRqUpdate = this.mapBranchUpdate()
        const response = await branchservice.updateBranch(this.id, branchRqUpdate)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      }
    },
    mapBranchInsert() {
      const branchRq = {
        branchCode: this.branch.branchCode,
        branchCodeNumber: this.branch.branchCodeNumber,
        branchTypeName: this.branch.branchTypeName,
        branchName: this.branch.branchName,
        districtName: this.branch.districtName,
        status: this.branch.status,
        description: this.branch.description
      }
      return branchRq
    },
    mapBranchUpdate() {
      const branchUpdateRq = {
        branchTypeName: this.branch.branchTypeName,
        branchName: this.branch.branchName,
        districtName: this.branch.districtName,
        status: this.branch.status,
        description: this.branch.description,
        version: this.branch.version
      }
      return branchUpdateRq
    },
    async getbranch() {
      const response = await branchservice.getBranch(this.id)
      if (response.code === 200) {
        this.branch = this.mapResponse(response.data)
        this.notifyInfo('Thông tin', response.message)
      }
    },
    mapResponse(data) {
      const branchResponse = {
        id: data.id,
        branchCode: data.branchCode,
        branchCodeNumber: data.branchCodeNumber,
        branchTypeName: data.branchTypeName,
        branchName: data.branchName,
        districtName: data.districtName,
        status: data.status,
        description: data.description,
        version: data.version
      }
      return branchResponse
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
