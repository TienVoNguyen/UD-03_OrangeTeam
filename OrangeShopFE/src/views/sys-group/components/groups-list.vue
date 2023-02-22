<template>
  <div class="app-container">
    <div class="col-sm-12 row">
      <div class="col-sm-3">
        <span class="">Tên nhóm</span>
        <el-input
          v-model="search.name"
          placeholder="Nhập tên nhóm"
          @keyup.enter.native="getGroups"
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
            @change="getGroups"
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
            @change="getGroups"
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
            @change="getGroups"
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
    <div class="col-sm-12 d-flex justify-content-center pt-4">
      <el-button
        icon="el-icon-search"
        type="primary"
        plain
        @click="getGroups"
      >Tìm kiếm</el-button>
      <el-button
        icon="el-icon-refresh"
        type="warning"
        plain
        @click="removeFilter"
      >Xoá tìm kiếm</el-button>
    </div>
    <div class="pt-4 pb-4 d-block float-right">
      <el-button
        v-if="hasPermission($options.name,'group.insert')"
        type="primary"
        plain
        @click="showDialog"
      >
        Thêm mới
      </el-button>
      <el-button
        v-if="hasPermission($options.name,'group.import')"
        type="warning"
        plain
      >
        Import
      </el-button>
      <el-button
        v-if="hasPermission($options.name,'group.export')"
        type="success"
        plain
      >
        Export
      </el-button>
    </div>
    <div class="pt-4">
      <el-table
        :data="groups"
        style="width: 100%"
      >
        <el-table-column label="Thao tác">
          <template slot-scope="scope" fixed>
            <el-tooltip class="item" effect="dark" content="Sửa" placement="bottom">
              <el-button
                v-if="hasPermission($options.name,'group.update')"
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
                v-if="hasPermission($options.name,'group.change-status')"
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
    </div>
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
    <ModifyDialog
      :id="id"
      :show-dialog="isShowDialog"
      @hideDialog="hideDialog"
      @modifySuccess="getGroups"
    />
  </div>
</template>
<script>
import baseCommon from '@/utils/base-common'
import * as groupservice from '../service/groups-service'
import ModifyDialog from './modify-groups.vue'
export default {
  name: 'Group',
  components: { ModifyDialog },
  mixins: [baseCommon],
  data() {
    return {
      listStatus: [
        {
          value: null,
          label: '-- Tất cả -- '
        },
        {
          value: 0,
          label: 'Tạm ngưng'
        },
        {
          value: 1,
          label: 'Hoạt động'
        }
      ],
      search: {
        name: null,
        createdFrom: null,
        createdTo: null,
        status: null
      },
      groups: [],
      headers: [
        {
          label: 'Mã nhóm',
          prop: 'code'
        },
        {
          label: 'Tên nhóm',
          prop: 'name'
        },
        {
          label: 'Người tạo',
          prop: 'createdBy'
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
      id: null
    }
  },
  created() {
    this.getGroups()
  },
  methods: {
    handleChangeStatus(index, data) {
      this.$confirm(
        'Bạn có chắc chắn muốn ' + (data.status === 1 ? 'khoá' : 'mở khoá') + ' nhóm người dùng?',
        (data.status === 1 ? 'Khoá nhóm người dùng' : 'Mở khoá nhóm người dùng'),
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
        const response = await groupservice.changeStatus(data.id, body)
        if (response.code === 200) {
          this.notifyInfo('Thông tin', response.message)
          this.getGroups()
        }
      }).catch(action => {
      })
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
    convertStatusName(row) {
      return this.listStatus.filter(st => st.value === row.status)[0]?.label
    },
    convertDate(row) {
      return this.convertDateToLocalUTC(row.createdAt, 'DD/MM/YYYY')
    },
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getGroups()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getGroups()
    },
    removeFilter() {
      this.search = {
        name: null,
        createdFrom: null,
        createdTo: null,
        status: null
      }
      this.pagination = {
        pageNumber: 1,
        pageSize: 10,
        totalItems: 0,
        totalPages: 0
      }
      this.getGroups()
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
    async getGroups() {
      if (!this.validateSearchDatetime()) {
        return
      }
      this.showLoading()
      const params = {
        name: this.search.name,
        createdFrom: this.search.createdFrom,
        createdTo: this.search.createdTo,
        status: this.search.status,
        pageIndex: this.pagination.pageNumber,
        pageSize: this.pagination.pageSize
      }
      const response = await groupservice.getGroups(params)
      if (response.code === 200) {
        this.groups = response.data.result
        this.pagination = {
          pageNumber: response.data.pageNumber,
          pageSize: response.data.pageSize,
          totalItems: response.data.totalItems,
          totalPages: response.data.totalPages
        }
        this.notifyInfo('Thông tin', response.message)
        this.hideLoading()
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
