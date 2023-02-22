<template>
  <div>
    <el-dialog
      :title="
        isAddNew ? 'Thêm mới người dùng' : 'Chỉnh sửa người dùng'"
      :visible.sync="showDialog"
      fullscreen
    >
      <ValidationObserver ref="createUpdateUser" v-slot="{ handleSubmit }">
        <el-form class="form-container" :model="user">
          <h5 class="text-warning">I.Thông tin người dùng </h5>
          <div class="app-container">
            <el-row class="pb-3">
              <el-col :span="16">
                <ValidationProvider v-slot="{ errors }" name="groupId" rules="requiredSelect">
                  <el-form-item prop="groupId" :error="messageError(groups.name, errors[0])">
                    <label>Tên nhóm người dùng<span class="text-danger"> * </span></label><br>
                    <el-select v-model="user.groupId" style="width: 95%;" filterable placeholder="Chọn nhóm người dùng">
                      <el-option v-for="item in dataGroups" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                  </el-form-item>
                </ValidationProvider>
              </el-col>
              <el-col :span="8">
                <el-form-item>
                  <label>Trạng thái<span class="text-danger"> * </span></label><br>
                  <div>
                    <el-select v-model="user.status" class="w-100" placeholder="Lựa chọn trạng thái">
                      <el-option v-for="item in listStatus" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                  </div>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="pb-3">
              <el-col :span="8">
                <ValidationProvider v-slot="{ errors }" name="username" rules="required">
                  <el-form-item prop="username" :error="messageError(users.username, errors[0])">
                    <label>Tên đăng nhập<span class="text-danger"> * </span></label><br>
                    <el-input
                      v-model="user.username"
                      :disabled="!isAddNew"
                      placeholder="Nhập tên đăng nhập"
                      style="width: 90%;"
                      maxlength="30"
                      show-word-limit
                    />
                  </el-form-item>
                </ValidationProvider>
              </el-col>
              <el-col :span="8">
                <el-form-item>
                  <label>Email nhân viên<span class="text-danger"> * </span></label><br>
                  <el-input
                    v-model="user.email"
                    placeholder="Nhập tên nhân viên"
                    style="width: 90%;"
                    maxlength="50"
                    show-word-limit
                    disabled
                  />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <ValidationProvider v-slot="{ errors }" name="name" rules="required">
                  <el-form-item prop="name" :error="messageError(users.name, errors[0])">
                    <label>Tên nhân viên<span class="text-danger"> * </span></label><br>
                    <el-input
                      v-model="user.name"
                      class="w-full"
                      placeholder="Nhập tên nhân viên"
                      maxlength="100"
                      show-word-limit
                    />
                  </el-form-item>
                </ValidationProvider>
              </el-col>
            </el-row>

            <el-row class="pb-4" style="display: none;">
              <el-col :span="24">
                <label>Danh sách đơn vị quản lý<span class="text-danger"> * </span></label>
                <el-tooltip class="item" effect="dark" content="Thêm đơn vị" placement="bottom">
                  <el-button class="button-custom" @click="addNewUserBranch">
                    <svg-icon icon-class="plus-altplus" class="disabled " />
                  </el-button>
                </el-tooltip>
                <br>
                <el-checkbox v-model="user.isAllBranches">All Branch</el-checkbox>
                <div class="pt-4">
                  <el-table :data="userBranches" style="width: 100%" border max-height="300">
                    <el-table-column label="Thao tác" width="120" :align="'center'">
                      <template slot-scope="scope">
                        <el-tooltip
                          v-if="scope.$index !== 0"
                          class="item"
                          effect="dark"
                          content="Xoá"
                          placement="bottom"
                        >
                          <el-button
                            class="button-custom"
                            @click="handleDeleteUserBranches(scope.$index, scope.row)"
                          ><svg-icon icon-class="remove" class="disabled " />
                          </el-button>
                        </el-tooltip>
                      </template>
                    </el-table-column>
                    <el-table-column :render-header="customizeHeader" label="Mã số đơn vị" :align="'center'">
                      <template
                        slot-scope="scope"
                      ><el-select
                        v-model="scope.row.branchId"
                        class="w-100"
                        filterable
                        placeholder="Lựa chọn mã số đơn vị"
                        @change="getDataDepartment(scope.row.branchId)"
                      >
                        <el-option
                          v-for="item in dataBranches"
                          :key="item.id"
                          :label="item.branchCodeNumber"
                          :value="item.id"
                        />
                      </el-select></template>
                    </el-table-column>
                    <el-table-column :render-header="customizeHeader" label="Mã đơn vị" :align="'center'">
                      <template
                        slot-scope="scope"
                      ><el-select
                        v-model="scope.row.branchId"
                        class="w-100"
                        filterable
                        placeholder="Lựa chọn mã đơn vị"
                        @change="getDataDepartment(scope.row.branchId)"
                      >
                        <el-option
                          v-for="item in dataBranches"
                          :key="item.id"
                          :label="item.branchCode"
                          :value="item.id"
                        /></el-select></template>
                    </el-table-column>
                    <el-table-column :render-header="customizeHeader" label="Tên đơn vị quản lý" :align="'center'">
                      <template
                        slot-scope="scope"
                      ><el-select
                        v-model="scope.row.branchId"
                        class="w-100"
                        filterable
                        placeholder="Lựa chọn tên đơn vị quản lý"
                        @change="getDataDepartment(scope.row.branchId,scope.row)"
                      >
                        <el-option
                          v-for="item in dataBranches"
                          :key="item.id"
                          :label="item.name"
                          :value="item.id"
                        /></el-select></template>
                    </el-table-column>
                    <el-table-column label="Phòng ban" :align="'center'">
                      <template
                        slot-scope="scope"
                      ><el-select
                        v-model="scope.row.departmentId"
                        class="w-100"
                        filterable
                        placeholder="Lựa chọn phòng ban"
                        @change="getDataPositions(scope.row.departmentId,scope.row.branchId)"
                      >
                        <el-option
                          v-for="item in dataDepartments"
                          :key="item.id"
                          :label="item.name"
                          :value="item.id"
                        /></el-select></template>
                    </el-table-column>
                    <el-table-column label="Chức vụ" :align="'center'">
                      <template
                        slot-scope="scope"
                      ><el-select
                        v-model="scope.row.positionId"
                        class="w-100"
                        filterable
                        placeholder="Lựa chọn chức vụ"
                      >
                        <el-option
                          v-for="item in dataPositons"
                          :key="item.id"
                          :label="item.name"
                          :value="item.id"
                        /></el-select></template>
                    </el-table-column>
                    <el-table-column :render-header="customizeHeader" label="Đơn vị chính" width="120" :align="'center'">
                      <template
                        slot-scope="scope"
                      >
                        <el-radio v-model="isMain" :label="scope.$index" size="small">&nbsp;</el-radio> </template>
                    </el-table-column>
                  </el-table>
                </div>
              </el-col>
            </el-row>
          </div>

          <h5 class="text-warning">II.Danh sách chức năng gán quyền </h5>
          <el-row>
            <el-col :span="10">
              <label class="pl-2 pb-3">Danh sách chức năng gán quyền</label>
              <div class="app-container tree-box">
                <el-tree
                  ref="tree"
                  :data="functions"
                  node-key="code"
                  default-expand-all
                  :expand-on-click-node="false"
                >
                  <span slot-scope="{ data }" class="custom-tree-node">

                    <span
                      :class="data.status == 0 ? 'text-red' : 'text-primary'"
                    >{{ data.title }} {{ data.name }}</span>
                  </span></el-tree>
              </div>
            </el-col>
            <el-col :span="1">
              &nbsp;
            </el-col>

            <el-col :span="13" style="display: none;">
              <label class="pb-3">Vai trò phê duyệt</label>
              <el-table :data="roles" style="width: 97%" border>
                <el-table-column
                  prop="groupName"
                  label="Đối tượng"
                  header-align="center"
                />
                <el-table-column
                  prop="name"
                  label="Vai trò"
                  width="200"
                  header-align="center"
                />
                <el-table-column width="250" label="Luồng duyệt thông thường" :align="'center'">
                  <template
                    slot-scope="scope"
                  ><el-checkbox v-if="scope.row.isShowNormal" v-model="scope.row.normalValue" />
                  </template>
                </el-table-column>
                <el-table-column width="150" label="Luồng duyệt nhanh" :align="'center'">
                  <template
                    slot-scope="scope"
                  ><el-checkbox v-if="scope.row.isShowFast" v-model="scope.row.fastValue" />
                  </template>
                </el-table-column>
                <el-table-column width="100" label="HO" :align="'center'">
                  <template
                    slot-scope="scope"
                  ><el-checkbox v-if="scope.row.isShowHo" v-model="scope.row.hoValue" />
                  </template>
                </el-table-column>
              </el-table>
            </el-col>

          </el-row>
          <el-row class="pt-4">
            <el-form-item class="dialog-footer pr-1-5">
              <el-button plain type="danger" @click="hideDialog">Đóng</el-button>
              <el-button plain type="primary" @click="handleSubmit(save)">{{ isAddNew ? 'Đồng ý' : 'Cập nhật' }}
              </el-button>
            </el-form-item>
          </el-row>
        </el-form>
      </ValidationObserver>
    </el-dialog>
  </div>
</template>
<script>
import BaseCommon from '@/utils/base-common'
import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
// import * as branchservice from '../../sys-branch/service/branch-service'
import * as departmentservice from '../../sys-department/service/department-service'
import * as groupservice from '../../sys-group/service/groups-service'
import * as positionservice from '../../sys-position/service/position-service'
import * as userservice from '../service/user-service'
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
          value: 1,
          label: 'Hoạt động'
        },
        {
          value: 0,
          label: 'Tạm ngưng'
        }
      ],
      metaDataGroups: {
        status: 1,
        deleted: 0
      },
      user: {
        username: null,
        groupId: null,
        status: 1,
        email: null,
        name: null,
        isAllBranches: false,
        version: 0
      },
      userBranches: [{ branchId: 462, positionId: null, departmentId: null, isMian: 1 }],
      branchInfo: {
        branchId: null,
        departmentId: null,
        positionId: null
      },
      functions: [],
      dataGroups: [],
      isMain: 0,
      dataBranches: [],
      dataDepartments: [],
      dataPositons: [],
      group: {
        code: null,
        name: null,
        status: 1,
        description: null,
        version: null
      },

      // user role
      roles: [],
      userRoles: []
    }
  },
  watch: {
    showDialog: {
      async handler(value) {
        if (value) {
          await this.getDataGroups()
          await this.getDataBranches()
          await this.getRoles()
          this.directInsertOrUpdate()
        } else {
          this.hideDialog()
        }
      }
    },
    'user.username': {
      handler(value) {
        this.user.email = value + '@tbp.com.vn'
      }
    },
    'user.groupId': {
      handler(value) {
        if (value !== null) {
          this.getGroupFunction(value)
        }
      }
    }
  },
  methods: {
    async getRoles() {
      this.roles = []
      const params = {}
      const response = await userservice.getRoles(params)
      if (response.code === 200) {
        for (let i = 0; i < response.data.length; i++) {
          const obj = {
            id: response.data[i].id,
            isShowNormal: response.data[i].normal,
            normalValue: false,
            isShowFast: response.data[i].fast,
            fastValue: false,
            isShowHo: response.data[i].ho,
            hoValue: false,
            groupName: response.data[i].groupName,
            name: response.data[i].name
          }
          this.roles.push(obj)
        }
      }
    },
    async getGroupFunction(id) {
      this.functions = []
      const response = await groupservice.getFunctionsGroup(id)
      if (response.code === 200) {
        this.functions = this.generateTreeMenu(response.data)
        this.notifyInfo('Thông tin', response.message)
      }
    },
    mapToRequestUserRole() {
      this.userRoles = []
      for (let i = 0; i < this.roles.length; i++) {
        const obj = {
          roleId: this.roles[i].id,
          normal: this.roles[i].normalValue === true ? 1 : 0,
          fast: this.roles[i].fastValue === true ? 1 : 0,
          ho: this.roles[i].hoValue === true ? 1 : 0
        }
        this.userRoles.push(obj)
      }
      return this.userRoles
    },
    generateTreeMenu(data) {
      const map = {}
      const root = []
      for (const item of data) {
        item.children = []
        map[item?.id] = item
      }
      for (const item of data) {
        if (item.parentId) {
          map[item.parentId].children.push(item)
          if (item.actions && item.actions.length !== 0) {
            item.children = item.children.concat(item.actions)
          }
        } else {
          root.push(item)
        }
      }
      return root
    },
    validateUserBranches() {
      this.userBranches = [{ branchId: 462, positionId: null, departmentId: null, isMian: 1 }]
      if (this.isMain === null) {
        this.notifyWarning('Thông báo', 'Vui lòng chọn đơn vị chính')
        return false
      }
      for (let i = 0; i < this.userBranches.length; i++) {
        if (this.userBranches[i].branchId === null) {
          this.notifyWarning('Thông báo', 'Vui lòng điền đầy đủ thông tin đơn vị')
          return false
        }
      }
      return true
    },
    async getDataBranches() {
      this.dataBranches = [{ branchId: 462, positionId: null, departmentId: null, isMian: 1 }]
      /* const params = {}
      const response = await branchservice.getDataBranches(params)
      if (response.code === 200) {
        this.dataBranches = response.data
      }*/
    },

    async getDataDepartment(branchId, obj) {
      obj.departmentId = null
      obj.positionId = null
      this.dataDepartments = []
      const branch = this.dataBranches.filter(obj => obj.id === branchId)
      const params = {
        branchCode: branch[0].code
      }
      const response = await departmentservice.getDataDepartments(params)
      if (response.code === 200) {
        this.dataDepartments = response.data
      }
    },

    async getDataPositions(departmetId, branchId) {
      this.dataPositons = []
      const branch = this.dataBranches.filter(obj => obj.id === branchId)
      const departmet = this.dataDepartments.filter(obj => obj.id === departmetId)
      const params = {
        branchCode: branch[0].code,
        departmentCode: departmet[0].code
      }
      const response = await positionservice.getDataPositions(params)
      if (response.code === 200) {
        this.dataPositons = response.data
      }
    },

    hideDialog() {
      this.$nextTick(() => {
        this.$refs.createUpdateUser.reset()
      })
      this.$emit('hideDialog', false)
    },
    async save() {
      if (!this.validateUserBranches()) {
        return
      }
      if (this.isAddNew) {
        const bodyForInsert = this.mapRequestForInsert()
        const response = await userservice.insertUser(bodyForInsert)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      } else {
        const bodyForUpdate = this.mapRequestForUpdate()
        console.log(bodyForUpdate)
        const response = await userservice.updateUser(this.id, bodyForUpdate)
        if (response.code === 200) {
          this.notifySuccess('Thông báo', response.message)
          this.$emit('modifySuccess')
          this.hideDialog()
        }
      }
    },
    mapRequestForInsert() {
      const bodyToInsert = {
        username: this.user.username,
        status: this.user.status,
        email: this.user.email,
        name: this.user.name,
        groupId: this.user.groupId,
        userBranches: this.userBranches,
        userRoles: this.mapToRequestUserRole(),
        isMain: this.isMain,
        isAllBranches: this.user.isAllBranches
      }
      return bodyToInsert
    },
    mapRequestForUpdate() {
      const bodyToInsert = {
        username: this.user.username,
        status: this.user.status,
        name: this.user.name,
        groupId: this.user.groupId,
        userBranches: this.mapToRequestBranches(this.userBranches),
        userRoles: this.mapToRequestUserRole(),
        isMain: this.isMain,
        isAllBranches: this.user.isAllBranches,
        version: this.user.version
      }
      return bodyToInsert
    },
    mapToRequestBranches(data) {
      const userBranchesForUpdate = []
      for (let i = 0; i < data.length; i++) {
        const object = {
          branchId: data[i].branchId,
          departmentId: data[i]?.departmentId,
          positionId: data[i]?.positionIddata
        }
        userBranchesForUpdate.push(object)
      }
      console.log(userBranchesForUpdate)
      return userBranchesForUpdate
    },
    directInsertOrUpdate() {
      if (this.id === null) {
        this.isShowAddNew()
      } else {
        this.isShowEdit()
      }
    },
    isShowAddNew() {
      this.userBranches = []
      this.branchInfo = {
        branchId: null,
        departmentId: null,
        positionId: null
      }
      this.userBranches.push(this.branchInfo)
      this.user = {
        username: null,
        groupId: null,
        status: 1,
        email: null,
        name: null,
        isAllBranches: false
      }
      this.functions = []
      this.isMain = 0
      this.isAddNew = true
    },
    addNewUserBranch() {
      this.branchInfo = {
        branchId: null,
        departmentId: null,
        positionId: null
      }
      this.getDataBranches()
      this.userBranches.push(this.branchInfo)
    },
    isShowEdit() {
      this.getUser()
      this.isAddNew = false
    },
    async getUser() {
      const response = await userservice.getUser(this.id)
      if (response.code === 200) {
        this.user = {
          username: response.data.username,
          groupId: response.data.groupId,
          status: response.data.status,
          email: response.data.email,
          name: response.data.name,
          isAllBranches: response.data.isAllBranches,
          version: 0 // response.data.version
        }
        this.userBranches = response.data.userBranches
        this.roles = this.getUserRoles(response.data.userRoles)
        this.notifyInfo('Thông tin', response.message)
      }
    },

    getUserRoles(userRoles) {
      const rolesTemp = []
      for (let i = 0; i < this.roles.length; i++) {
        for (let j = 0; j < userRoles.length; j++) {
          if (this.roles[i].id === userRoles[j].roleId) {
            const obj = {
              id: this.roles[i].id,
              isShowNormal: this.roles[i].isShowNormal,
              normalValue: userRoles[j].normal === 1,
              isShowFast: this.roles[i].isShowFast,
              fastValue: userRoles[j].fast === 1,
              isShowHo: this.roles[i].isShowHo,
              hoValue: userRoles[j].ho === 1,
              groupName: this.roles[i].groupName,
              name: this.roles[i].name
            }
            rolesTemp.push(obj)
          }
        }
      }
      return rolesTemp
    },
    async getDataGroups() {
      const params = {
        status: this.metaDataGroups.status,
        deleted: this.metaDataGroups.deleted
      }
      const response = await groupservice.getDataGroups(params)
      if (response.code === 200) {
        this.dataGroups = response.data
      }
    },
    handleDeleteUserBranches(index, item) {
      this.$confirm(
        'Bạn có chắc chắn muốn xoá bản ghi này không?',
        'Cảnh báo',
        {
          confirmButtonText: 'Đồng ý',
          cancelButtonText: 'Huỷ',
          type: 'warning',
          center: true
        }
      ).then(() => {
        this.userBranches.splice(index, 1)
        this.isMain = null
        this.notifySuccess('Thông báo', 'Xoá thành công')
      }).catch(action => {
      })
    },
    customizeHeader(createElement, { column, $index }) {
      return (
        <div>
          <div class='d-block c-header text-center'>
            <span>{column.label} <span class='text-danger'>* </span></span>
          </div>
        </div>
      )
    }
  }
}
</script>
<style lang="scss" scoped>
.form-container {
  padding-left: 15px;
  padding-right: 15px;
}

.el-form-item {
  margin-bottom: 0;
}

.dialog-footer {
  padding: 0px !important;
  padding-top: 10px !important;
  text-align: right !important;
  bottom: 0;
  -webkit-box-sizing: border-box !important;
  box-sizing: border-box !important;
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

.tree-box {
  border: 1px solid;
  border-radius: 2rem;
  border-color: #5e2e86;
}

.button-custom {
  border-color: transparent !important;
}

.svg-icon {
  width: 20px;
  height: 20px;
  vertical-align: -0.25rem;
  fill: currentColor;
  overflow: hidden;
}
</style>
