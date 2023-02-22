<template>
  <div class="app-container">
    <div class="row mt-3">
      <div class="col-4">
        <label for="unit">Đơn vị</label>
        <el-select id="unit" v-model="filter.unit" class="form-control non-border" placeholder="Chọn đơn vị">
          <el-option v-for="item in units" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </div>
      <div class="col-4">
        <label for="department">Phòng ban</label>
        <el-select id="department" v-model="filter.department" class="form-control non-border" placeholder="Chọn phòng ban">
          <el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </div>
      <div class="col-4">
        <label for="position">Chức vụ</label>
        <el-select id="position" v-model="filter.position" class="form-control non-border" placeholder="Chọn chức vụ">
          <el-option v-for="item in positions" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </div>
    </div>
    <div class="row mt-3">
      <div class="col-6">
        <button class="btn btn-outline-primary float-end">Tìm kiếm</button>
      </div>
      <div class="col-6">
        <button class="btn btn-outline-warning" @click="handleCreate">Thêm mới</button>
      </div>
    </div>
    <div class="filter-container mt-5 row">
      <div class="col-8">
        <h4>Danh sách người phê duyệt</h4>
      </div>
      <div class="col-4">
        <button class="btn btn-sm btn-outline-primary float-end">Export</button>
        <button class="btn btn-sm btn-outline-warning float-end me-3" @click="handleImport">Import</button>
      </div>
    </div>

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
          <span class="me-2" @click="handleUpdate(row)"><svg-icon icon-class="edit" style="color: #083863" /></span>
          <span @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A" /></span>
        </template>
      </el-table-column>
      <el-table-column label="Tên đơn vị quản lý">
        <template slot-scope="{row}">
          {{ row.unit?.name }}
        </template>
      </el-table-column>
      <el-table-column label="Phòng ban">
        <template slot-scope="{row}">
          <span>{{ row.department?.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Chức vụ">
        <template slot-scope="{row}">
          {{ row.position?.name }}
        </template>
      </el-table-column>
      <el-table-column label="Họ và tên">
        <template slot-scope="{row}">
          {{ row.approver?.name }}
        </template>
      </el-table-column>
      <el-table-column label="Hiển thị mặc định (HO)" width="150" align="center">
        <template slot-scope="{row}">
          <el-checkbox v-model="row.showDefault" disabled />
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" class="float-end" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" layout="prev, pager, next, sizes" @pagination="getList" />
    <div class="row fixed-bottom">
      <div class="col-12">
        <button class="btn btn-outline-secondary float-end me-3 mb-3" @click="closePage">Đóng</button>
      </div>
    </div>
    <add-user
      :temp="temp"
      :dialog-form-visible.sync="dialogFormVisible"
      :list="list"
      :dialog-status="dialogStatus"
      :title="textMap[dialogStatus]"
      :units="units"
      :departments="departments"
      :approvers="approvers"
      :positions="positions"
    />
    <import-user
      :dialog-import-visible.sync="dialogImportVisible"
    />
    <delete-user
      :dialog-delete-visible.sync="dialogDeleteVisible"
    />
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
import AddUser from '@/views/procedure/approver/components/user/add-user'
import ImportUser from '@/views/procedure/approver/components/user/import-user'
import DeleteUser from '@/views/procedure/approver/components/user/delete-user' // secondary package based on el-pagination

export default {
  name: 'UserList',
  components: { DeleteUser, ImportUser, AddUser, Pagination },
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
        type: undefined,
        sort: '+id'
      },
      units: null,
      departments: null,
      positions: null,
      approvers: null,
      dialogFormVisible: false,
      dialogImportVisible: false,
      dialogDeleteVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Sửa người phê duyệt',
        create: 'Thêm mới người phê duyệt'
      },
      temp: {
        unit: null,
        department: null,
        position: null,
        approver: null
      },
      filter: {
        unit: null,
        department: null,
        position: null
      }
    }
  },
  created() {
    this.getList()
    this.getUnits()
    this.getDepartments()
    this.getPositions()
    this.getApprovers()
  },
  methods: {
    closePage() {
      this.$router.back()
    },
    resetTemp() {
      this.temp = {
        unit: null,
        department: null,
        position: null,
        approver: null
      }
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.resetTemp()
      this.dialogFormVisible = true
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    handleDelete(row, index) {
      this.dialogDeleteVisible = true
      this.temp = Object.assign({}, row) // copy obj
    },
    handleImport() {
      this.dialogImportVisible = true
    },
    getUnits() {
      this.units = [
        {
          id: 1,
          name: 'Đông Đô'
        },
        {
          id: 2,
          name: 'Đông Đô 2'
        }
      ]
    },
    getDepartments() {
      this.departments = [
        {
          id: 1,
          name: 'Marketing'
        },
        {
          id: 2,
          name: 'Kế toán'
        }
      ]
    },
    getPositions() {
      this.positions = [
        {
          id: 1,
          name: 'Trưởng phòng Marketing'
        },
        {
          id: 2,
          name: 'Phó phòng Marketing'
        },
        {
          id: 3,
          name: 'Nhân viên'
        }
      ]
    },
    getApprovers() {
      this.approvers = [
        {
          id: 1,
          name: 'Nguyễn Văn A'
        },
        {
          id: 2,
          name: 'Nguyễn Văn B'
        },
        {
          id: 3,
          name: 'Nguyễn Văn C'
        }
      ]
    },
    getList() {
      this.listLoading = true
      this.list = [
        {
          unit: {
            id: 1,
            name: 'Đông Đô'
          },
          department: {
            id: 1,
            name: 'Marketing'
          },
          position: {
            id: 1,
            name: 'Trưởng phòng Marketing'
          },
          approver: {
            id: 1,
            name: 'Nguyễn Văn A'
          },
          showDefault: true
        },
        {
          unit: {
            id: 2,
            name: 'Đông Đô 2'
          },
          department: {
            id: 2,
            name: 'Kế toán'
          },
          position: {
            id: 3,
            name: 'Nhân viên'
          },
          approver: {
            id: 3,
            name: 'Nguyễn Văn C'
          },
          showDefault: false
        },
        {
          unit: {
            id: 1,
            name: 'Đông Đô'
          },
          department: {
            id: 1,
            name: 'Marketing'
          },
          position: {
            id: 2,
            name: 'Phó phòng Marketing'
          },
          approver: {
            id: 2,
            name: 'Nguyễn Văn B'
          },
          showDefault: false
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
