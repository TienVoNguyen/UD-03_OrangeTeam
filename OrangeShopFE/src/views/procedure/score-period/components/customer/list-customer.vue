<template>
  <div>
    <div>
      <el-form ref="customerFilterForm" label-position="left" class="mt-3">
        <div class="row">
          <div class="col-12">
            <label class="label">II. Danh sách khách hàng chấm điểm định kỳ</label>
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-4">
            <el-form-item
              prop="taxCode"
            >
              <label for="tax-code">Mã số thuế</label>
              <el-input id="tax-code" ref="taxCode" v-model="customerFilter.taxCode" class="form-control non-border" placeholder="Nhập mã số thuế" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="businessCode"
            >
              <label for="business-code">Mã số đăng ký kinh doanh</label>
              <el-input id="business-code" ref="businessCode" v-model="customerFilter.businessCode" class="form-control non-border" placeholder="Nhập mã số thuế đăng ký kinh doanh" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item prop="cifCode">
              <div class="row">
                <div class="col-6">
                  <label>Mã CIF (FCC)</label>
                  <el-input v-model="customerFilter.fccCode" class="form-control non-border" placeholder="Nhập CIF" />
                </div>
                <div class="col-6">
                  <label>Mã CIF (Tạm)</label>
                  <el-input v-model="customerFilter.cifDraftCode" class="form-control non-border" placeholder="Nhập CIF" />
                </div>
              </div>
            </el-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col-4">
            <el-form-item
              prop="unit"
            >
              <label for="unit">Tên ĐVQL</label>
              <el-select id="unit" v-model="customerFilter.unit" class="form-control non-border" placeholder="---Chọn---">
                <el-option v-for="item in units" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="businessCode"
            >
              <label for="unit-code">Mã ĐVQL</label>
              <el-input id="unit-code" ref="unitCode" v-model="customerFilter.unitCode" class="form-control non-border" placeholder="Nhập mã ĐVQL" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item>
              <label for="status">Trạng thái</label>
              <el-select id="status" v-model="customerFilter.status" class="form-control non-border" placeholder="---Tất cả---">
                <el-option label="Đang thực hiện" value="1" />
                <el-option label="Khóa" value="2" />
              </el-select>
            </el-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            <button class="btn btn-outline-primary float-end">Tìm kiếm</button>
          </div>
          <div class="col-6" />
        </div>
      </el-form>
    </div>
    <div class="mt-5">
      <div class="row mb-2">
        <div class="col-8" />
        <div class="col-4">
          <button class="btn btn-sm btn-outline-primary float-end">Export</button>
          <button class="btn btn-sm btn-outline-warning float-end me-3" @click="handleImport">Import</button>
        </div>
      </div>
      <el-table
        :key="tableKey"
        v-loading="listLoading"
        :data="list"
        :header-cell-style="{ background: '#5E2E864D', color: '#000000', borderRightStyle: 'none' }"
        :cell-style="{borderRightStyle: 'none'}"
        border
        fit
        highlight-current-row
        style="width: 100%;"
      >
        <el-table-column label="Thao tác" width="100px" align="center">
          <template slot-scope="{row, $index}">
            <span v-if="enabled" @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A" /></span>
          </template>
        </el-table-column>
        <el-table-column label="Mã số thuế">
          <template slot-scope="{row}">
            {{ row.taxCode }}
          </template>
        </el-table-column>
        <el-table-column label="Mã số ĐKKD">
          <template slot-scope="{row}">
            <span>{{ row.businessCode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Mã CIF (FCC)">
          <template slot-scope="{row}">
            {{ row.fccCode }}
          </template>
        </el-table-column>
        <el-table-column label="Mã CIF (Tạm)">
          <template slot-scope="{row}">
            {{ row.cifDraftCode }}
          </template>
        </el-table-column>
        <el-table-column label="Tên ĐVQL">
          <template slot-scope="{row}">
            {{ row.unit?.name }}
          </template>
        </el-table-column>
        <el-table-column label="Mã ĐVQL">
          <template slot-scope="{row}">
            {{ row.unit?.code }}
          </template>
        </el-table-column>
        <el-table-column label="Trạng thái">
          <template slot-scope="{row}">
            {{ row.status === 0 ? 'Chưa xếp hạng' : 'Đã xếp hạng' }}
          </template>
        </el-table-column>
      </el-table>
      <div class="row">
        <div class="col-12">
          <pagination v-show="total>0" class="float-end mt-0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" layout="prev, pager, next, sizes" @pagination="getList" />
        </div>
      </div>
      <div class="row mt-3 float-end">
        <div class="col-12">
          <button class="btn btn-outline-secondary me-3" @click="closePage">Đóng</button>
          <button v-if="enabled" class="btn btn-outline-warning">{{ edited ? 'Cập nhật' : 'Đồng ý' }}</button>
        </div>
      </div>
    </div>
    <delete-customer
      :dialog-delete-visible.sync="dialogDeleteVisible"
    />
    <import-customer
      :dialog-import-visible.sync="dialogImportVisible"
    />
  </div>
</template>

<script>
import waves from '@/directive/waves'
import Pagination from '@/components/Pagination'
import DeleteCustomer from '@/views/procedure/score-period/components/customer/delete-customer'
import ImportCustomer from '@/views/procedure/score-period/components/customer/import-customer'

export default {
  name: 'ListCustomer',
  components: { ImportCustomer, DeleteCustomer, Pagination },
  directives: { waves },
  props: {
    enabled: {
      type: Boolean,
      default: true
    },
    edited: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      tableKey: 0,
      units: null,
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
      dialogImportVisible: false,
      dialogDeleteVisible: false,
      customerFilter: {
        taxCode: '',
        businessCode: '',
        fccCode: '',
        cifDraftCode: '',
        unit: null,
        unitCode: '',
        status: null
      }
    }
  },
  created() {
    this.getList()
    this.getUnits()
  },
  methods: {
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
    closePage() {
      this.$router.back()
    },
    getList() {
      this.listLoading = true
      this.list = [
        {
          taxCode: '0123456789',
          businessCode: '0289456123',
          fccCode: 'A1234561',
          cifDraftCode: 'A1234561',
          unit: {
            id: 1,
            name: 'Chi nhánh Đông Đô',
            code: 'DD001'
          },
          status: 1
        },
        {
          taxCode: '0223456789',
          businessCode: '0389456123',
          fccCode: 'A1234562',
          cifDraftCode: 'A1234562',
          unit: {
            id: 1,
            name: 'Chi nhánh Đông Đô',
            code: 'DD001'
          },
          status: 0
        },
        {
          taxCode: '0323456789',
          businessCode: '0489456123',
          fccCode: 'A1234563',
          cifDraftCode: 'A1234563',
          unit: {
            id: 1,
            name: 'Chi nhánh Đông Đô',
            code: 'DD001'
          },
          status: 0
        },
        {
          taxCode: '0423456789',
          businessCode: '0589456123',
          fccCode: 'A1234564',
          cifDraftCode: 'A1234564',
          unit: {
            id: 1,
            name: 'Chi nhánh Đông Đô',
            code: 'DD001'
          },
          status: 0
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

<style scoped>
.btn-outline-primary {
  border-color: #5E2E86;
  color: #5E2E86;
}
.btn-outline-primary:hover {
  background-color: #5E2E86;
  border-color: #5E2E86;
  color: #fff;
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
}.btn-outline-secondary {
   border-color: #C9D1D7;
   color: #C9D1D7;
 }
.btn-outline-secondary:hover {
  background-color: #C9D1D7;
  border-color: #C9D1D7;
  color: #fff;
}
.btn-outline-secondary:active,
.btn-outline-secondary:focus {
  color: #fff;
  background-color: #C9D1D7;
  border-color: #C9D1D7;
  box-shadow: 0 0 0 0.2rem #C9D1D7 !important;
}
.form-control {
  border: 0;
  padding: 0;
}
.label {
  color: #EF8C1A;
}
</style>
