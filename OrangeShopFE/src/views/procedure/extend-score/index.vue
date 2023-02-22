<template>
  <div class="app-container">
    <el-form ref="extendScore" label-position="left">
      <div class="row">
        <div class="col-4">
          <el-form-item
            prop="taxCode"
          >
            <label for="tax-code">Mã số thuế</label>
            <el-input id="tax-code" ref="taxCode" v-model="extendScoreFilter.taxCode" class="form-control non-border" placeholder="Nhập mã số thuế" />
          </el-form-item>
        </div>
        <div class="col-4">
          <el-form-item
            prop="businessCode"
          >
            <label for="business-code">Mã số đăng ký kinh doanh</label>
            <el-input id="business-code" ref="businessCode" v-model="extendScoreFilter.businessCode" class="form-control non-border" placeholder="Nhập mã số thuế đăng ký kinh doanh" />
          </el-form-item>
        </div>
        <div class="col-4">
          <el-form-item prop="cifCode">
            <div class="row">
              <div class="col-6">
                <label>Mã CIF (FCC)</label>
                <el-input v-model="extendScoreFilter.fccCode" class="form-control non-border" placeholder="Nhập CIF" />
              </div>
              <div class="col-6">
                <label>Mã CIF (Tạm)</label>
                <el-input v-model="extendScoreFilter.cifDraftCode" class="form-control non-border" placeholder="Nhập CIF" />
              </div>
            </div>
          </el-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col-4">
          <el-form-item
            prop="report"
          >
            <label for="report">Loại BCTC</label>
            <el-select id="report" v-model="extendScoreFilter.report" class="form-control non-border" placeholder="---Chọn---">
              <el-option v-for="item in reports" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
        </div>
        <div class="col-4">
          <el-form-item
            prop="businessCode"
          >
            <label for="year">Năm BCTC</label>
            <el-input id="year" ref="year" v-model="extendScoreFilter.year" class="form-control non-border" placeholder="Nhập năm BCTC" />
          </el-form-item>
        </div>
        <div class="col-4">
          <el-form-item>
            <label for="unit-name">Tên đơn vị quản lý</label>
            <el-input id="unit-name" ref="unit" v-model="extendScoreFilter.unit" class="form-control non-border" placeholder="Nhập tên đơn vị quản lý" />
          </el-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col-6">
          <button class="btn btn-outline-primary float-end">Tìm Kiếm</button>
        </div>
        <div class="col-6">
          <button class="btn btn-outline-warning" @click="handleExtend">Gia Hạn</button>
        </div>
      </div>
    </el-form>

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
      class="mt-4"
    >
      <el-table-column label="Thao tác" width="100px" align="center">
        <template slot-scope="{row, $index}">
          <span @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A" /></span>
        </template>
      </el-table-column>
      <el-table-column label="Mã số thuế" width="100px">
        <template slot-scope="{row}">
          {{ row.taxCode }}
        </template>
      </el-table-column>
      <el-table-column label="Mã số ĐKKD" width="120px">
        <template slot-scope="{row}">
          <span>{{ row.businessCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Mã CIF (FCC)" width="120px">
        <template slot-scope="{row}">
          <span>{{ row.fccCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Mã CIF (Tạm)" width="120px">
        <template slot-scope="{row}">
          <span>{{ row.cifDraftCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Tên doanh nghiệp" min-width="100px">
        <template slot-scope="{row}">
          <span>{{ row.company?.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Loại BCTC">
        <template slot-scope="{row}">
          <span>{{ row.report?.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Mã ĐVQL" width="100px">
        <template slot-scope="{row}">
          <span>{{ row.unit?.code }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Tên ĐVQL">
        <template slot-scope="{row}">
          <span>{{ row.unit?.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Năm BCTC" width="100px">
        <template slot-scope="{row}">
          <span>{{ row.year }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Ngày tạo">
        <template slot-scope="{row}">
          <span>{{ row.createDate }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" class="float-end" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" layout="prev, pager, next, sizes" @pagination="getList" />
    <delete-extend-score
      :dialog-delete-visible.sync="dialogDeleteVisible"
    />
    <dialog-extend-score
      :dialog-form-visible.sync="dialogFormVisible"
      :list="list"
    />
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
import DeleteExtendScore from '@/views/procedure/extend-score/components/delete-extend-score'
import DialogExtendScore from '@/views/procedure/extend-score/components/dialog-extend-score'

export default {
  name: 'ExtendScore',
  components: { DialogExtendScore, DeleteExtendScore, Pagination },
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
      extendScoreFilter: {
        taxCode: '',
        businessCode: '',
        fccCode: '',
        cifDraftCode: '',
        report: null,
        year: null,
        unit: ''
      },
      dialogFormVisible: false,
      dialogDeleteVisible: false,
      reports: null
    }
  },
  created() {
    this.getList()
    this.getReports()
  },
  methods: {
    handleExtend() {
      this.dialogFormVisible = true
    },
    handleDelete(row, index) {
      this.dialogDeleteVisible = true
      this.temp = Object.assign({}, row) // copy obj
    },
    getList() {
      this.listLoading = true
      this.list = [
        {
          id: 1,
          taxCode: '012412512',
          businessCode: '0789456123',
          fccCode: 'F1234567',
          cifDraftCode: 'A1234567',
          company: {
            id: 1,
            name: 'Công Ty TNHH Một Thành Viên A'
          },
          report: {
            id: 1,
            name: 'Báo cáo thuế'
          },
          unit: {
            id: 1,
            code: 'DD001',
            name: 'Chi nhánh Đông Đô'
          },
          year: 2021,
          createDate: '10/12/2019 11:00:00'
        },
        {
          id: 2,
          taxCode: '235123512',
          businessCode: '0789456123',
          fccCode: 'F1234567',
          cifDraftCode: 'A1234567',
          company: {
            id: 2,
            name: 'Công Ty TNHH Một Thành Viên B'
          },
          report: {
            id: 2,
            name: 'Báo cáo kiểm toán'
          },
          unit: {
            id: 1,
            code: 'DD001',
            name: 'Chi nhánh Đông Đô'
          },
          year: 2021,
          createDate: '10/12/2019 11:00:00'
        },
        {
          id: 3,
          taxCode: '235126235',
          businessCode: '0789456123',
          fccCode: 'F1234567',
          cifDraftCode: 'A1234567',
          company: {
            id: 3,
            name: 'Công Ty TNHH Một Thành Viên C'
          },
          report: {
            id: 3,
            name: 'Báo cáo kiểm toán nội bộ'
          },
          unit: {
            id: 1,
            code: 'DD001',
            name: 'Chi nhánh Đông Đô'
          },
          year: 2021,
          createDate: '10/12/2019 11:00:00'
        },
        {
          id: 4,
          taxCode: '457345664',
          businessCode: '0789456123',
          fccCode: 'F1234567',
          cifDraftCode: 'A1234567',
          company: {
            id: 1,
            name: 'Công Ty TNHH Một Thành Viên D'
          },
          report: {
            id: 4,
            name: 'Không có BCTC'
          },
          unit: {
            id: 1,
            code: 'DD001',
            name: 'Chi nhánh Đông Đô'
          },
          year: 2021,
          createDate: '10/12/2019 11:00:00'
        }
      ]
      this.total = this.list.length
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    },
    getReports() {
      this.reports = [
        {
          id: 1,
          name: 'Báo cáo thuế'
        },
        {
          id: 2,
          name: 'Báo cáo kiểm toán'
        },
        {
          id: 3,
          name: 'Báo cáo kiểm toán nội bộ'
        },
        {
          id: 4,
          name: 'Không có BCTC'
        }
      ]
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
