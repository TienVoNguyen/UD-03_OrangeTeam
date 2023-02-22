<template>
  <div>
    <el-dialog
      :title="
        isAddNew ? 'Thêm mới nhóm người dùng' : 'Chỉnh sửa nhóm người dùng'
      "
      :visible.sync="showDialog"
      fullscreen
    >
      <ValidationObserver ref="createUpdateGroup" v-slot="{ handleSubmit }">
        <el-form class="form-container" :model="group">
          <h5 class="text-warning">I.Thông tin chung </h5>
          <div class="app-container">
            <el-row class="pb-3">
              <el-col :span="8">
                <ValidationProvider
                  v-slot="{ errors }"
                  name="code"
                  rules="required"
                >
                  <el-form-item
                    prop="code"
                    :error="messageError(groups.code, errors[0])"
                  >
                    <label>Mã nhóm<span class="text-danger"> * </span></label><br>
                    <el-input
                      v-model="group.code"
                      placeholder="Nhập mã nhóm"
                      class="w-90"
                      maxlength="50"
                      show-word-limit
                      :disabled="!isAddNew"
                    />
                  </el-form-item>
                </ValidationProvider>
              </el-col>
              <el-col :span="8">
                <ValidationProvider
                  v-slot="{ errors }"
                  name="name"
                  rules="required"
                >
                  <el-form-item
                    prop="name"
                    :error="messageError(groups.name, errors[0])"
                  >
                    <label>Tên nhóm<span class="text-danger"> * </span></label><br>
                    <el-input
                      v-model="group.name"
                      placeholder="Nhập tên nhóm"
                      style="width: 95%;"
                      maxlength="100"
                      show-word-limit
                    />
                  </el-form-item>
                </ValidationProvider>
              </el-col>
              <el-col :span="8">
                <el-form-item>
                  <label>Trạng thái<span class="text-danger"> * </span></label><br>
                  <el-select
                    v-model="group.status"
                    class="w-90"
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
                  <label>Mô tả</label><br>
                  <el-input
                    v-model="group.description"
                    type="textarea"
                    :autosize="{ minRows: 4, maxRows: 10}"
                    placeholder="Nhập mô tả"
                    style="width: 97%"
                    maxlength="500"
                    show-word-limit
                  />
                </el-form-item>
              </el-col>
            </el-row>
          </div>
          <h5 class="text-warning">II.Danh sách chức năng gán quyền </h5>
          <el-row>
            <el-col :span="12">
              <label class="pt-4 pb-2" style="padding-left:2rem"> Danh sách chức năng gán quyền </label>
              <div class="app-container tree-box">
                <el-tree
                  ref="tree"
                  :data="functions"
                  show-checkbox
                  node-key="code"
                  default-expand-all
                  :expand-on-click-node="false"
                  @check-change="handleCheckChange"
                >
                  <span slot-scope="{ data }" class="custom-tree-node">

                    <span
                      :class="data.status == 0 ? 'text-red' : 'text-primary'"
                    ><svg-icon :icon-class="data.icon ? data.icon : 'config'" class-name="disabled" />&nbsp;&nbsp; {{ data.title }} {{ data.name }}</span>
                  </span></el-tree>
              </div>
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
import BaseCommon from '@/utils/base-common'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'
import * as functionService from '../../sys-function/service/function-service'
import * as groupservice from '../service/groups-service'
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
      group: {
        code: null,
        name: null,
        status: 1,
        description: null,
        version: null
      },
      functions: [],
      functionsAction: [],
      getFunctionActions: []
    }
  },
  watch: {
    'showDialog': {
      async handler(value) {
        if (value) {
          await this.getFunctions()
          this.directInsertOrUpdate()
        } else {
          this.hideDialog()
        }
      }
    }
  },
  methods: {
    hideDialog() {
      this.$nextTick(() => {
        this.$refs.createUpdateGroup.reset()
      })
      this.setCheckedKeys([])
      this.$emit('hideDialog', false)
    },
    async getFunctions() {
      const params = {}
      const response = await functionService.getFunctions(params)
      if (response.code === 200) {
        this.functions = this.generateTreeMenu(response.data)
      }
    },
    directInsertOrUpdate() {
      if (this.id === null) {
        this.isShowAddNew()
      } else {
        this.isShowEdit()
      }
      this.getFunctionActions = []
    },
    isShowAddNew() {
      this.group = {
        code: null,
        name: null,
        status: 1,
        description: null
      }
      this.functionsAction = []
      this.isAddNew = true
    },
    isShowEdit() {
      this.isAddNew = false
      this.getGroup()
    },
    async save() {
      if (this.isAddNew) {
        const groupRequest = this.mapGroupInsert()
        const response = await groupservice.insertGroup(groupRequest)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      } else {
        const groupRequestUpdate = this.mapGroupUpdate()
        const response = await groupservice.updateGroup(this.id, groupRequestUpdate)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      }
    },
    mapGroupInsert() {
      const groupRq = {
        code: this.group.code,
        name: this.group.name,
        status: this.group.status,
        description: this.group.description,
        functionActions: this.functionsAction
      }
      return groupRq
    },
    mapGroupUpdate() {
      const groupUpdateRq = {
        name: this.group.name,
        status: this.group.status,
        description: this.group.description,
        version: this.group.version,
        functionActions: this.functionsAction
      }
      return groupUpdateRq
    },
    async getGroup() {
      const response = await groupservice.getGroup(this.id)
      if (response.code === 200) {
        this.group = this.mapResponse(response.data)
        this.notifyInfo('Thông tin', response.message)
      }
    },
    mapResponse(data) {
      const groupResponse = {
        code: data.code,
        name: data.name,
        status: data.status,
        description: data.description,
        version: data.version
      }
      this.getFunctionActions = data.actionCodes
      this.setCheckedKeys(this.getFunctionActions)
      return groupResponse
    },
    handleCheckChange(data, checked, indeterminate) {
      if (data.code !== undefined && checked) {
        const actions = {
          functionId: data.functionId,
          code: data.code
        }
        this.functionsAction.push(actions)
      } else if (data.code !== undefined && !checked) {
        this.functionsAction = this.functionsAction.filter(obj => obj.code !== data.code)
      }
    },
    setCheckedKeys(data) {
      this.$refs.tree.setCheckedKeys(data)
    },
    generateTreeMenu(data) {
      const map = {}
      const root = []
      for (const item of data) {
        item.children = []
        map[item?.id] = item
        if (item.actions && item.actions.length !== 0) {
          item.children = item.children.concat(item.actions)
        }
      }
      for (const item of data) {
        if (item.parentId) {
          map[item.parentId].children.push(item)
          // if (item.actions && item.actions.length !== 0) {
          //   item.children = item.children.concat(item.actions)
          // }
        } else {
          root.push(item)
        }
      }
      return root
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
  .custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 18px;
  padding-right: 8px;
}
.el-tree-node__content {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  height: 2.5rem;
  cursor: pointer;
}
.tree-box{
    border: 1px solid;
    border-radius: 2rem;
    border-color: #5E2E86;
}
  </style>
