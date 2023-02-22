<template>
  <div>
    <el-dialog fullscreen title="Thêm mới chức năng" :visible.sync="showDialog">
      <ValidationObserver ref="createUpdateFunction" v-slot="{ handleSubmit }">
        <el-form class="form-container" :model="functions">
          <h5 class="text-primary">1. Định nghĩa chức năng </h5>
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="title"
                rules="required"
              >
                <el-form-item
                  prop="title"
                  :error="messageError(functionsName.title, errors[0])"
                >
                  <label>Tiêu đề<span class="text-danger"> * </span></label><br>
                  <el-input
                    ref="title"
                    v-model="functions.title"
                    placeholder="Nhập tiêu đề"
                    class="w-90"
                    maxlength="100"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="path"
                :rules="!functions.isMenuGroup ? 'required' : ''"
              >
                <el-form-item
                  prop="path"
                  :error="messageError(functionsName.path, errors[0])"
                >
                  <label>Path<span class="text-danger"> * </span></label>
                  <el-input
                    ref="path"
                    v-model="functions.path"
                    :disabled="functions.isMenuGroup"
                    placeholder="Nhập path"
                    class="w-full"
                    maxlength="50"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
          </el-row>
          <el-row class="pb-3">
            <el-col :span="12">
              <el-form-item>
                <label>Menu nhóm</label><br>
                <el-switch v-model="functions.isMenuGroup" :disabled="!isAddNew" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <label>Không hiển thị</label><br>
                <el-switch v-model="functions.isDisplayed" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="icon"
                rules="required"
              >
                <el-form-item
                  prop="icon"
                  :error="messageError(functionsName.icon, errors[0])"
                >
                  <label>Icon<span class="text-danger"> * </span></label><br>
                  <el-input
                    ref="icon"
                    v-model="functions.icon"
                    placeholder="Nhập mã icon"
                    class="w-90"
                    maxlength="50"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <label>Trạng thái<span class="text-danger"> * </span></label><br>
                <el-switch
                  v-model="functions.status"
                />  &nbsp; &nbsp; <span :class="!functions.status ? 'text-danger' :'text-primary'">{{ convertTextStatus(functions.status) }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="pb-3">
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                :rules="!functions.isMenuGroup ? 'required' : ''"
                name="component"
              >
                <el-form-item
                  prop="component"
                  :error="messageError(functionsName.component, errors[0])"
                >
                  <label>Mã màn hình <span class="text-danger"> * </span></label><br>
                  <el-input
                    ref="component"
                    v-model="functions.component"
                    :disabled="functions.isMenuGroup"
                    placeholder="Nhập tên mã màn hình"
                    class="w-90"
                    maxlength="50"
                    show-word-limit
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
            <el-col :span="12">
              <ValidationProvider
                v-slot="{ errors }"
                name="priority"
                rules="required"
              >
                <el-form-item
                  prop="priority"
                  :error="messageError(functionsName.priority, errors[0])"
                >
                  <label>Thứ tự <span class="text-danger"> * </span></label><br>
                  <el-input
                    ref="priority"
                    v-model="functions.priority"
                    placeholder="Nhập số thứ tự chức năng"
                    oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*?)\..*/g, '$1');"
                    class="w-full"
                  />
                </el-form-item>
              </ValidationProvider>
            </el-col>
          </el-row>
          <el-row class="pb-3">
            <el-col :span="24">
              <el-form-item>
                <label>Mô tả</label>
                <el-input
                  v-model="functions.description"
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
          <el-row v-if="!functions.isMenuGroup">
            <list-action-apis :is-get-action="isGetActions" :actions="actions" @genAction="pushActionsToFucntions" />
          </el-row>
          <el-row>
            <el-form-item class="dialog-footer pr-1-5">
              <el-button plain type="danger" @click="hideDialog">Đóng</el-button>
              <el-button plain type="primary" @click="handleSubmit(save)">Đồng ý</el-button>
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
import BaseCommon from '@/utils/base-common'
import ListActionApis from './list-action-apis.vue'
import * as functionService from '../service/function-service'
export default {
  name: 'CreateFunction',
  components: { ValidationObserver, ValidationProvider, ListActionApis },
  mixins: [BaseValidate, BaseCommon],
  props: {
    showDialog: {
      type: Boolean,
      default: false
    },
    id: {
      type: Number,
      default: null
    },
    parentId: {
      type: Number,
      default: null
    }
  },
  data() {
    return {
      functions: {
        id: null,
        title: null,
        description: null,
        path: null,
        parentId: null,
        isMenuGroup: true,
        isDisplayed: false,
        status: true,
        priority: null,
        icon: null,
        component: null,
        isDeleted: 0,
        actions: []
      },
      actions: [],
      isGetActions: false,
      isAddNew: true
    }
  },
  watch: {
    showDialog: {
      handler(value) {
        if (value) {
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
    pushActionsToFucntions(value) {
      this.functions.actions = value
    },
    directInsertOrUpdate() {
      if (this.id == null) {
        this.isShowAddNew()
      } else {
        this.isShowEdit()
      }
    },
    isShowAddNew() {
      this.actions = []
      this.functions = {
        id: null,
        title: null,
        description: null,
        path: null,
        parentId: null,
        isMenuGroup: true,
        isDisplayed: false,
        status: true,
        priority: null,
        icon: null,
        component: null,
        isDeleted: 0,
        actions: []
      }
      this.isAddNew = true
    },
    isShowEdit() {
      this.getFunctionAction()
      this.isAddNew = false
    },
    async getFunctionAction() {
      const response = await functionService.getFunction(this.id)
      if (response.code === 200) {
        this.functions = this.mapResponse(response.data)
        this.actions = response.data.actions
        this.isGetActions = true
        this.notifyInfo('Thông tin', response.message)
      }
    },
    mapResponse(obj) {
      const functionResponse = {
        id: obj.id,
        title: obj.title,
        description: obj.description,
        path: obj.path,
        parentId: obj.parentId,
        isMenuGroup: obj.isMenuGroup === 1,
        isDisplayed: obj.isDisplayed !== 0,
        status: obj.status === 1,
        priority: obj.priority,
        icon: obj.icon,
        component: obj.component,
        isDeleted: obj.isDeleted,
        actions: obj.actions
      }
      return functionResponse
    },
    hideDialog() {
      this.$nextTick(() => {
        this.$refs.createUpdateFunction.reset()
      })
      this.parentId = null
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
      if (!this.functions.isMenuGroup) {
        if (this.actions.length === 0) {
          this.notifyWarning('Cảnh báo', 'Yêu cầu khai báo thao tác')
          return
        } else {
          for (let i = 0; i < this.actions.length; i++) {
            try {
              if (this.actions[i].listApi.length === 0) {
                this.notifyWarning('Cảnh báo', 'Yêu cầu khai báo thao tác chi tiết với mã thao tác là:' + this.actions[i].code)
                return
              }
            } catch (ex) {
              if (this.actions[i].code === null) {
                this.notifyWarning('Cảnh báo', 'Yêu cầu khai báo thao tác')
              } else {
                this.notifyWarning('Cảnh báo', 'Yêu cầu khai báo thao tác chi tiết với mã thao tác là:' + this.actions[i].code)
              }

              return
            }
          }
        }
      }
      if (this.isAddNew) {
        const functionRq = this.mapFunctionRequest()
        const response = await functionService.insertFunction(functionRq)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess', true)
          this.hideDialog()
        }
      } else {
        const functionRq = this.mapFunctionRequestForUpdate()
        const response = await functionService.updateFunction(this.functions.id, functionRq)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess', true)
          this.hideDialog()
        }
      }
    },
    mapFunctionRequest() {
      const functionRq = {
        title: this.functions.title,
        description: this.functions.description,
        path: this.functions.path,
        parentId: this.parentId,
        isDisplayed: this.functions.isDisplayed === true ? 1 : 0,
        isMenuGroup: this.functions.isMenuGroup === true ? 1 : 0,
        status: this.functions.status === true ? 1 : 0,
        priority: this.functions.priority,
        icon: this.functions.icon,
        component: this.functions.component,
        isDeleted: this.functions.isDeleted,
        actions: this.functions.actions
      }
      return functionRq
    },
    mapFunctionRequestForUpdate() {
      const functionRq = {
        title: this.functions.title,
        description: this.functions.description,
        path: this.functions.path,
        isMenuGroup: this.functions.isMenuGroup === true ? 1 : 0,
        isDisplayed: this.functions.isDisplayed === true ? 1 : 0,
        status: this.functions.status === true ? 1 : 0,
        priority: this.functions.priority,
        icon: this.functions.icon,
        component: this.functions.component,
        isDeleted: this.functions.isDeleted,
        actions: this.functions.actions
      }
      return functionRq
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

