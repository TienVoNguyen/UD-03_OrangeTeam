<template>
  <div class="app-container">
    <div class="row mt-3">
      <div class="col-3">
        <label>Mã nhóm ngành</label>
        <el-input id="search_code" v-model="searchObj.search_code" value="" class="form-control non-border" placeholder="Nhập mã đơn vị" />
      </div>
      <div class="col-1" />
      <div class="col-3">
        <label>Tên nhóm ngành</label>
        <el-input id="search_name" v-model="searchObj.search_name" value="" class="form-control non-border" placeholder="Nhập mã số đăng ký kinh doanh" />
      </div>
      <div class="col-1" />
      <div class="col-3">
        <label for="status">Trạng thái</label>
        <el-select id="status" value="" class="form-control non-border">
          <el-option v-for="item in status" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </div>
    </div>
    <div class="row mt-5" style="text-align: center;">
      <div class="col-1" />
      <div class="col-2">
        <button class="btn btn-outline-primary" @click="handleSearch">Tìm kiếm</button>
      </div>
      <div class="col-2">
        <button class="btn btn-outline-warning" style="color: #EF8C1A" @click="handleCreate">Thêm mới</button>
      </div>
      <div class="col-2">
        <button class="btn btn-outline-danger" style="color: #FF0000" @click="handleImport">Import</button>
      </div>
      <div class="col-2">
        <button class="btn btn-outline-info" style="color: #2E7EB7" @click="handleExport">Export</button>
      </div>
      <div class="col-2">
        <button class="btn btn-outline-success" style="color: #34B780" @click="handleReview">Trình duyệt</button>
      </div>
    </div>
    <div class="row mt-5">
      <el-table
        :key="tableKey"
        v-loading="listLoading"
        :data="list"
        :header-cell-style="{ background: '#5E2E864D', color: '#000000', borderRightStyle: 'dashed' }"
        :cell-style="{borderRightStyle: 'dashed'}"
        border
        fit
        highlight-current-row
        style="width: 100%;"
      >
        <el-table-column label="Thao tác" width="100px" align="center">
          <template slot-scope="{row, $index}">
            <el-checkbox v-model="list[$index].checked" />
            <!-- <span class="me-2" @click="handleUpdate(row, $index)"><svg-icon icon-class="edit" style="color: #083863" /></span> -->
            <router-link :to="{ path: '/creditRatingsRouter/business_customers/update_customer_identification/'+row.id}" class="el-icon-edit" style="color: #083863" />
            <span @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A" /></span>
          </template>
        </el-table-column>
        <el-table-column label="Mã nhóm ngành" align="center">
          <template slot-scope="{row}">
            {{ row.code_group }}
          </template>
        </el-table-column>
        <el-table-column label="Tên nnhóm ngành" width="330px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.name_group }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Yêu cầu điều chỉnh" width="330px" align="center">
          <template slot-scope="{row}">
            {{ row.req_change }}
          </template>
        </el-table-column>
        <el-table-column label="Trạng thái" width="200px" align="center">
          <template slot-scope="{row}">
            {{ row.status_group }}
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
      <DeleteCustomer
        :dialog-delete-visible.sync="dialogDeleteVisible"
      />
    </div>
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
import DeleteCustomer from '@/views/creditRatings/business_customers/customer_identification/delete-customer_identification' // secondary package based on el-pagination

export default {
  name: 'CustomerIdentificationList',
  components: { Pagination, DeleteCustomer },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        sort: '+id'
      },
      status: null,
      type: null,
      dialogFormVisible: false,
      dialogStatus: '',
      dialogFormUpdateVisible: false,
      dialogImportVisible: false,
      dialogDeleteVisible: false,
      searchObj: {
        code_tax: null,
        code_reg: null,
        code_cif: null,
        code_cif_temp: null
      }
    }
  },
  created() {
    this.getList()
    this.getStatus()
    this.getType()
    this.getSide()
  },
  methods: {
    resetSearchObj() {
      this.searchObj = {
        code_tax: null,
        code_reg: null,
        code_cif: null,
        code_cif_temp: null
      }
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.resetTemp()
      this.dialogFormVisible = true
    },
    handleUpdate(row, $index) {
      this.dialogStatus = 'update'
      this.temp = Object.assign({}, row) // copy obj
      // this.resetTemp()
      this.dialogFormUpdateVisible = true
    },
    handleDelete(row, index) {
      this.dialogDeleteVisible = true
      this.temp = Object.assign({}, row) // copy obj
    },
    handleImport() {
      this.dialogImportVisible = true
    },
    handleExport() {
      alert('HandleExport')
    },
    handleReview() {
      alert('handleReview')
    },
    handleSearch() {
      alert('handleSearch')
    },
    resetListChecked() {
      this.listChecked = {}
    },
    getStatus() {
      this.status = [
        {
          id: 1,
          name: 'Không hoạt động'
        },
        {
          id: 2,
          name: 'Hoạt động'
        }
      ]
    },
    getList() {
      this.listLoading = true
      this.list = [
        {
          id: 1,
          code_group: 'CODE_GROUP01',
          name_group: 'NAME_GROUP01',
          req_change: 'REQ_CHANGE01',
          status_group: 1,
          checked: false
        },
        {
          id: 2,
          code_group: 'CODE_GROUP02',
          name_group: 'NAME_GROUP02',
          req_change: 'REQ_CHANGE02',
          status_group: 2,
          checked: false
        }
      ]
      this.total = this.list.length
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    }
  }
}
</script>
  <style>
  .btn-outline-primary {
    border-color: #5E2E86;
    color: #5E2E86;
  }
  .btn-outline-primary:hover {
    background-color: #5E2E86;
    border-color: #5E2E86;
  }
  .btn-outline-primary:active,
  .btn-outline-primary:focus {
    color: #fff;
    background-color: #5E2E86;
    border-color: #5E2E86;
    box-shadow: 0 0 0 0.2rem #5E2E86 !important;
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
  .non-border {
    border: 0;
    padding: 0;
  }
  </style>

