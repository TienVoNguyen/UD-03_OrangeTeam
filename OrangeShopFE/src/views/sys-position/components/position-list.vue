<template>
  <div class="app-container">
    <div class="col-sm-12 row">
      <div class="col-sm-4">
        <div class="col-sm-12 d-flex justify-content-center">
          <div class="col-sm-5">
            <div class="mb-1">Mã đơn vị</div>
            <el-input
              v-model="search.branchCode"
              class="d-flex justify-content-center w-100"
              placeholder="Nhập mã đơn vị"
              @keyup.enter.native="getPositions"
            />
          </div>
          <div class="col-sm-2">
            <i
              class="el-icon-minus font-weight-bold d-flex justify-content-center"
              style="padding-top: 2.5rem !important;"
            />
          </div>
          <div class="col-sm-5">
            <div class="mb-1">Mã số đơn vị</div>
            <el-input
              v-model="search.branchCodeNumber"
              class="w-100"
              placeholder="Nhập mã số đơn vị"
              @keyup.enter.native="getPositions"
            />
          </div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="col-sm-12">
          <div class="mb-1" style="padding-left: 7%">Tên đơn vị</div>
          <div class="col-sm-12 d-flex justify-content-center">
            <el-input v-model="search.branchName" style="width: 86%" placeholder="Nhập tên đơn vị" @keyup.enter.native="getPositions" />
          </div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="mb-1">Trạng thái</div>
        <div>
          <el-select
            v-model="search.status"
            class="w-100"
            placeholder="Lựa chọn trạng thái"
            @change="getPositions"
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
    <div class="col-sm-12 row pt-4">
      <div class="col-sm-4">
        <div class="col-sm-12">
          <div class="mb-1">Tên phòng ban</div>
          <div class="col-sm-12 d-flex justify-content-center">
            <el-input v-model="search.departmentName" placeholder="Nhập tên phòng ban" @keyup.enter.native="getPositions" />
          </div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="col-sm-12">
          <div class="mb-1" style="padding-left: 7%">Tên chức vụ</div>
          <div class="col-sm-12 d-flex justify-content-center">
            <el-input v-model="search.name" style="width: 86%" placeholder="Nhập tên chức vụ" @keyup.enter.native="getPositions" />
          </div>
        </div>
      </div>
    </div>
    <div class="col-sm-12 d-flex justify-content-center pt-4">
      <el-button
        icon="el-icon-search"
        type="primary"
        plain
        @click="getPositions"
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
        v-if="hasPermission($options.name,'position.insert')"
        type="primary"
        plain
        @click="showDialog"
      >
        Thêm mới
      </el-button>
      <el-button
        v-if="hasPermission($options.name,'position.imporrt')"
        type="warning"
        plain
      >
        Import
      </el-button>
      <el-button
        v-if="hasPermission($options.name,'position.export')"
        type="success"
        plain
      >
        Export
      </el-button>
    </div>
    <div class="pt-4">
      <el-table
        :data="positions"
        style="width: 100%"
      >
        <el-table-column label="Thao tác">
          <template slot-scope="scope" fixed>
            <el-tooltip class="item" effect="dark" content="Sửa" placement="bottom">
              <el-button
                v-if="hasPermission($options.name,'position.update')"
                size="mini"
                class="button-custom"
                plain
                @click="handleEdit(scope.$index, scope.row)"
              >
                <svg-icon icon-class="edit" class="disabled " />
              </el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="Xoá" placement="bottom">
              <el-button
                v-if="hasPermission($options.name,'position.delete')"
                size="mini"
                class="button-custom"
                plain
                @click="handleDelete(scope.$index, scope.row)"
              > <svg-icon icon-class="trash" /></el-button>
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
      @modifySuccess="getPositions"
    />
  </div>
</template>

<script>
import baseCommon from '@/utils/base-common'
import * as positionservice from '../service/position-service'
import ModifyDialog from './modify-position.vue'
export default {
  name: 'Position',
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
        branchCode: null,
        branchCodeNumber: null,
        branchName: null,
        status: null,
        departmentName: null,
        name: null
      },
      headers: [
        {
          label: 'Mã đơn vị',
          prop: 'branchCode',
          formatter: null
        },
        {
          label: 'Mã số đơn vị',
          prop: 'branchCodeNumber'
        },
        {
          label: 'Tên đơn vị',
          prop: 'branchName'
        },
        {
          label: 'Chức vụ',
          prop: 'name'
        },
        {
          label: 'trạng thái',
          prop: 'status',
          formatter: this.convertStatusName,
          contentAlign: 'left',
          headerAlign: 'left'
        }
      ],
      positions: [],
      isShowDialog: false
    }
  },
  created() {
    this.getPositions()
  },
  methods: {
    handleDelete(index, data) {
      this.$confirm(
        'Bạn có chắc chắn muốn xoá bản ghi này không?',
        'Cảnh báo',
        {
          confirmButtonText: 'Đồng ý',
          cancelButtonText: 'Huỷ',
          type: 'warning',
          center: true
        }
      ).then(async() => {
        const body = {
          id: data.id,
          version: data.version
        }
        const response = await positionservice.deletePosition(data.id, body)
        if (response.code === 200) {
          this.notifyInfo('Thông tin', response.message)
          this.getPositions()
        }
      }).catch(action => {
      })
    },
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getBranches()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getBranches()
    },
    hideDialog(obj) {
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
    removeFilter() {
      this.search = {
        branchCode: null,
        branchCodeNumber: null,
        branchName: null,
        status: null,
        departmentName: null,
        name: null
      }
      this.pagination = {
        pageNumber: 1,
        pageSize: 10,
        totalItems: 0,
        totalPages: 0
      }
      this.getPositions()
    },
    convertStatusName(row) {
      return this.listStatus.filter(st => st.value === row.status)[0]?.label
    },
    async getPositions() {
      this.showLoading()
      const params = {
        branchCode: this.search.branchCode,
        branchCodeNumber: this.search.branchCodeNumber,
        branchName: this.search.branchName,
        status: this.search.status,
        departmentName: this.search.departmentName,
        name: this.search.name,
        pageIndex: this.pagination.pageNumber,
        pageSize: this.pagination.pageSize
      }
      const response = await positionservice.getPositions(params)
      if (response.code === 200) {
        this.positions = response.data.result
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
