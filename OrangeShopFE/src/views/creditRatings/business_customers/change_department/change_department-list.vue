<template>
  <div class="app-container">
    <div class="row mt-3">
      <div class="col-4">
        <label>Mã số thuế</label>
        <el-input id="code_tax" v-model="temp.code_tax" value="" class="form-control non-border" placeholder="Nhập mã đơn vị" />
      </div>
      <div class="col-4">
        <label>Mã số đăng ký kinh doanh</label>
        <el-input id="code_reg" v-model="temp.code_reg" value="" class="form-control non-border" placeholder="Nhập mã số đăng ký kinh doanh" />
      </div>
      <div class="col-4" style="display:flex">
        <div class="col-5">
          <label>Mã CIF (FCC)</label>
          <el-input id="code_cif" v-model="temp.code_cif" value="" class="form-control non-border" placeholder="Nhập mã CIF (FCC)" />
        </div>
        <div class="col-1" />
        <div class="col-5">
          <label>Mã CIF (Tạm)</label>
          <el-input id="code_cif_temp" v-model="temp.code_cif_temp" value="" class="form-control non-border" placeholder="Nhập mã CIF (Tạm)" />
        </div>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-6">
        <button class="btn btn-outline-primary float-end">Tìm kiếm</button>
      </div>
    </div>
    <!-- <div class="filter-container mt-5 row">
      <div class="col-8" />
      <div class="col-4">
        <button class="btn btn-sm btn-outline-primary float-end">Export</button>
        <button class="btn btn-sm btn-outline-warning float-end me-3" @click="handleImport">Import</button>
      </div>
    </div> -->

    <div class="row mt-3">
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
          <template slot-scope="{row}">
            <!-- <span class="me-2" @click="handleUpdate(row, $index)"><svg-icon icon-class="edit" style="color: #083863" /></span> -->
            <router-link :to="{ path: '/creditRatingsRouter/business_customers/update_change_department/'+row.id}" class="el-icon-edit" style="color: #083863" />
            <!-- <span @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A" /></span> -->
            <!-- <router-link :to="{ path: '#'}" class="el-icon-position" style="color: #EF8C1A" /> -->
            <!-- <span @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A; display:none" /></span> -->
          </template>
        </el-table-column>
        <el-table-column label="Mã số thuế" width="130px" align="center">
          <template slot-scope="{row}">
            <!-- <el-link style="margin-top: -4px;" type="primary" @click="handleUpdate(row, $index)">{{ row.code_tax }}</el-link> -->
            <router-link :to="{ path: '/creditRatingsRouter/business_customers/details_change_department/'+row.id}">
              <span style="color: #0033ed">{{ row.code_tax }}</span>
            </router-link>
            <br>
            <span style="font-size: 12px">Xem chi tiết</span>
            <i class="el-icon-right" style="font-size: 11px" />
          </template>
        </el-table-column>
        <el-table-column label="Mã số ĐKKD" width="130px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.code_reg }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Mã CIF (FCC)" width="130px" align="center">
          <template slot-scope="{row}">
            {{ row.code_cif }}
          </template>
        </el-table-column>
        <el-table-column label="Mã CIF (Tạm)" width="130px" align="center">
          <template slot-scope="{row}">
            {{ row.code_cif_temp }}
          </template>
        </el-table-column>
        <el-table-column label="Tên doanh nghiệp">
          <template slot-scope="{row}">
            {{ row.name_dn }}
          </template>
        </el-table-column>
        <el-table-column label="Tên ĐVQL">
          <template slot-scope="{row}">
            {{ row.name_dept }}
          </template>
        </el-table-column>
        <el-table-column label="Mã ĐVQL" width="160px" align="center">
          <template slot-scope="{row}">
            {{ row.code_dept }}
          </template>
        </el-table-column>
      <!-- <el-table-column label="Hiển thị mặc định (HO)" width="150" align="center">
        <template slot-scope="{row}">
          <el-checkbox v-model="row.showDefault" />
        </template>
      </el-table-column> -->
      </el-table>

      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
      <UpdateRank
        :dialog-form-visible.sync="dialogFormUpdateVisible"
        :rule="ruleChanges"
        :cause="causeChanges"
      />
    </div>
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
import UpdateRank from '@/views/creditRatings/business_customers/rank_adjustment/update-rank_adjustment' // secondary package based on el-pagination

export default {
  name: 'CustomerIdentificationList',
  components: { Pagination, UpdateRank },
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
      type_scores: null,
      type_report: null,
      dialogFormVisible: false,
      dialogStatus: '',
      dialogFormUpdateVisible: false,
      dialogImportVisible: false,
      dialogDeleteVisible: false,
      temp: {
        code_tax: null,
        code_reg: null,
        code_cif: null,
        code_cif_temp: null
      },
      ruleChanges: null,
      causeChanges: null
    }
  },
  created() {
    this.getList()
    this.getType_scores()
    this.getType_report()
    this.getRuleChanges()
    this.getCauseChanges()
  },
  methods: {
    resetTemp() {
      this.temp = {
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
    getType_scores() {
      this.type_scores = [
        {
          id: 1,
          name: 'Loại chấm điểm 1'
        },
        {
          id: 2,
          name: 'Loại chấm điểm 2'
        }
      ]
    },
    getType_report() {
      this.type_reports = [
        {
          id: 1,
          name: 'Loại BCTC 1'
        },
        {
          id: 2,
          name: 'Loại BCTC 2'
        }
      ]
    },
    getRuleChanges() {
      this.ruleChanges = [
        {
          id: 1,
          name: 'Quy tắc 1'
        },
        {
          id: 2,
          name: 'Quy tắc 2'
        }
      ]
    },
    getCauseChanges() {
      this.causeChanges = [
        {
          id: 1,
          name: 'Nguyên nhân 1'
        },
        {
          id: 2,
          name: 'Nguyên nhân 2'
        }
      ]
    },
    getList() {
      this.listLoading = true
      this.list = [
        {
          id: 1,
          code_tax: 'TAX011',
          code_reg: 'REG011',
          code_cif: 'CIF011',
          code_cif_temp: 'CIF_TEMP011',
          name_dn: 'DN011',
          name_dept: 'DEPT011',
          code_dept: 'CODE_DEPT011'
        },
        {
          id: 2,
          code_tax: 'TAX012',
          code_reg: 'REG012',
          code_cif: 'CIF012',
          code_cif_temp: 'CIF_TEMP012',
          name_dn: 'DN012',
          name_dept: 'DEPT012',
          code_dept: 'CODE_DEPT012'
        },
        {
          id: 3,
          code_tax: 'TAX013',
          code_reg: 'REG013',
          code_cif: 'CIF013',
          code_cif_temp: 'CIF_TEMP013',
          name_dn: 'DN013',
          name_dept: 'DEPT013',
          code_dept: 'CODE_DEPT013'
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
