<template>
  <div class="app-container">
    <div class="col-sm-12 row">
      <div class="col-sm-3">
        <span class="">Tên đăng nhập</span>
        <el-input
          v-model="search.username"
          placeholder="Nhập tên đăng nhập"
        />
      </div>
      <div class="col-sm-6">
        <span class="" style="padding-left: 7%">Ngày tạo</span>
        <div class="col-sm-12 d-flex justify-content-center">
          <el-date-picker
            v-model="search.createdFrom"
            style="width: 40%"
            placeholder="Từ ngày"
            type="date"
            value-format="yyyy-MM-dd"
            format="dd/MM/yyyy"
          />
          <i
            class="el-icon-minus font-weight-bold"
            style="padding: 1% 2% 0 2%"
          />
          <el-date-picker
            v-model="search.createdTo"
            style="width: 40%"
            placeholder="Đến ngày"
            type="date"
            format="dd/MM/yyyy"
            value-format="yyyy-MM-dd"
          />
        </div>
      </div>
      <div class="col-sm-3">
        <span class="">Trạng thái</span>
        <div>
          <el-select
            v-model="search.status"
            class="w-100"
            placeholder="Lựa chọn trạng thái"
          >
            <el-option
              v-for="item in listStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
      </div>
    </div>
    <div class="col-sm-12 row pt-2">
      <div class="col-sm-3">
        <span class="">Nhóm người dùng</span>
        <el-select
          v-model="search.groupId"
          class="w-100"
          filterable
          placeholder="Lựa chọn nhóm người dùng"
        >
          <el-option
            v-for="item in dataGroups"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </div>
      <div class="col-sm-6">
        <span class="" style="padding-left: 7%">Đơn vị</span>
        <div class="col-sm-12 d-flex justify-content-center">
          <el-select
            v-model="search.branchId"
            style="width: 86%"
            filterable
            placeholder="Lựa chọn nhóm người dùng"
          >
            <el-option
              v-for="item in dataGroups"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </div>
      </div>
    </div>
    <div class="col-sm-12 d-flex justify-content-center pt-4">
      <el-button
        icon="el-icon-search"
        type="primary"
        plain
        @click="getUsers()"
      >Tìm kiếm</el-button>
      <el-button
        icon="el-icon-refresh"
        type="warning"
        plain
        @click="removeFilter()"
      >Xoá tìm kiếm</el-button>
    </div>
    <div class="pt-4 pb-4 d-block float-right">
      <el-button
        v-if="hasPermission($options.name,'user.insert')"
        type="primary"
        plain
        @click="showDialog"
      >
        Thêm mới
      </el-button>
      <el-button
        v-if="hasPermission($options.name,'user.import')"
        type="warning"
        plain
      >
        Import
      </el-button>
      <el-button
        v-if="hasPermission($options.name,'user.export')"
        type="success"
        plain
      >
        Export
      </el-button>
    </div>
    <div class="pt-4">
      <el-table
        :data="users"
        style="width: 100%"
      >
        <el-table-column label="Thao tác">
          <template slot-scope="scope" fixed>
            <el-tooltip class="item" effect="dark" content="Sửa" placement="bottom">
              <el-button
                v-if="hasPermission($options.name,'user.update')"
                size="mini"
                class="button-custom"
                plain
                @click="handleEdit(scope.$index, scope.row)"
              >
                <svg-icon icon-class="edit" class="disabled " />
              </el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" :content="scope.row.status === 1 ? 'Tạm ngưng':'Hoạt động'" placement="bottom">
              <el-button
                v-if="hasPermission($options.name,'user.change-status')"
                size="mini"
                class="button-custom"
                plain
                @click="handleChangeStatus(scope.$index, scope.row)"
              > <svg-icon :icon-class="scope.row.status === 1 ? 'deactive':'active'" /></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column
          v-for="item in headers"
          :key="item.prop"
          :label="item.label"
          :prop="item.prop"
          :formatter="item.formatter"
          :align="item.contentAlign"
          :header-align="item.headerAlign"
          :class-name="item.class"
        />
      </el-table>
      <div class="mt-4 d-block float-right">
        <el-pagination
          :current-page.sync="pagination.pageNumber"
          :page-size="pagination.pageSize"
          :page-sizes="pageSizes"
          layout="sizes,prev, pager, next, total"
          :total="pagination.totalItems"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
    <ModifyDialog
      :id="id"
      :show-dialog="isShowDialog"
      @hideDialog="hideDialog"
      @modifySuccess="getUsers"
    />
  </div>
</template>
<script>
import * as userservice from '../service/user-service'
import * as groupservice from '../../sys-group/service/groups-service'
import baseCommon from '@/utils/base-common'
import ModifyDialog from './modify-user.vue'
export default {
  name: 'User', // Mã màn hình trong khi tạo menu
  components: { ModifyDialog },
  mixins: [baseCommon],
  data() {
    return {
      dataGroups: [],
      listStatus: [
        {
          value: null,
          label: '-- Tất cả -- '
        },
        {
          value: 0,
          label: 'Tạm ngưng'
        },
        {
          value: 1,
          label: 'Hoạt động'
        }
      ],
      search: {
        username: null,
        createdTo: null,
        createdFrom: null,
        groupId: null,
        branchId: null,
        status: null
      },
      metaDataGroups: {
        status: 1,
        deleted: 0
      },
      users: [],
      headers: [
        {
          label: 'Tên đăng nhập',
          prop: 'username',
          formatter: null
        },
        {
          label: 'Nhóm người dùng',
          prop: 'groupName',
          formatter: null
        },
        {
          label: 'Mã đơn vị',
          prop: 'listBranchCode',
          formatter: this.isAllBranches
        },
        {
          label: 'Mã số đơn vị',
          prop: 'listBranchCodeNumber',
          formatter: this.isAllBranches
        },
        {
          label: 'Ngày tạo',
          prop: 'createdAt',
          formatter: this.convertDate
        },
        {
          label: 'Trạng thái',
          prop: 'status',
          formatter: this.convertStatusName,
          contentAlign: 'left',
          headerAlign: 'left'
        }
      ],
      isShowDialog: false,
      user: {},
      id: 0
    }
  },
  created() {
    this.getUsers()
    this.getDataGroups()
  },
  methods: {
    isAllBranches(row, column, cellValue, index) {
      return row.allBranches ? 'Tất cả đơn vị' : cellValue
    },
    handleChangeStatus(index, data) {
      this.$confirm(
        'Bạn có chắc chắn muốn ' + (data.status === 1 ? 'khoá' : 'mở khoá') + ' người dùng?',
        (data.status === 1 ? 'Khoá người dùng' : 'Mở khoá người dùng'),
        {
          confirmButtonText: 'Đồng ý',
          cancelButtonText: 'Đóng',
          type: 'warning',
          center: true
        }
      ).then(async() => {
        const body = {
          version: data.version
        }
        const response = await userservice.changeStatus(data.id, body)
        if (response.code === 200) {
          this.notifyInfo('Thông tin', response.message)
          this.getUsers()
        }
      }).catch(action => {
      })
    },
    convertDate(row) {
      return this.convertDateToLocalUTC(row.createdAt, 'DD/MM/YYYY HH:mm:ss')
    },
    removeFilter() {
      this.search = {
        username: null,
        createdTo: null,
        createdFrom: null,
        groupId: null,
        branchId: null,
        status: null
      }
      this.pagination = {
        pageNumber: 1,
        pageSize: 10,
        totalItems: 0,
        totalPages: 0
      }
      this.getUsers()
    },
    convertStatusName(row) {
      return this.listStatus.filter(st => st.value === row.status)[0]?.label
    },
    hideDialog(obj) {
      this.id = null
      this.isShowDialog = obj
    },
    showDialog() {
      this.id = null
      this.isShowDialog = true
    },
    handleEdit(index, data) {
      this.id = data.id
      this.isShowDialog = true
    },
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getUsers()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getUsers()
    },
    validateSearchDatetime() {
      if (this.search.createdFrom != null && this.search.createdTo != null) {
        if (this.search.createdFrom > this.search.createdTo) {
          this.notifyWarning('Cảnh báo', 'Từ ngày không được lớn hơn đến ngày')
          return false
        }
      }
      return true
    },
    async getUsers() {
      if (!this.validateSearchDatetime()) {
        return
      }
      this.showLoading()
      // debugger
      const params = {
        username: this.search.username,
        createdTo: this.search.createdTo,
        createdFrom: this.search.createdFrom,
        groupId: this.search.groupId,
        branchId: this.search.branchId,
        status: this.search.status,
        pageIndex: this.pagination.pageNumber,
        pageSize: this.pagination.pageSize
      }
      const response = await userservice.getUsers(params)
      if (response.code === 200) {
        this.users = response.data.result
        console.log(this.users)
        this.pagination = {
          pageNumber: response.data.pageNumber,
          pageSize: response.data.pageSize,
          totalItems: response.data.totalItems,
          totalPages: response.data.totalPages
        }
        this.notifyInfo('Thông tin', response.message)
        this.hideLoading()
      }
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
    }
  }
}
</script>
<style scoped>
.float-right {
    float: right;
  }
  .button-custom {
      border-color: transparent !important;
  }
</style>
