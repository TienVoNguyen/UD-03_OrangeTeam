<template>
  <div class="app-container">
    <el-form ref="scorePeriodFilterForm" label-position="left" class="mt-3">
      <div class="row">
        <div class="col-3">
          <el-form-item>
            <label for="type">Loại chấm điểm</label>
            <el-select id="type" v-model="filter.type" class="form-control non-border" placeholder="---Tất cả---">
              <el-option label="Thông thường" value="1" />
              <el-option label="Định kỳ" value="2" />
            </el-select>
          </el-form-item>
        </div>
        <div class="col-3">
          <el-form-item>
            <label for="name">Loại hình sản phẩm</label>
            <el-input
              id="name"
              ref="name"
              v-model="filter.name"
              class="form-control non-border"
              placeholder="Nhập loại hình sản phẩm"
            />
          </el-form-item>
        </div>
      </div>
    </el-form>
    <div class="row mt-3 mb-5">
      <div class="col-6">
        <button class="btn btn-outline-primary float-end">Tìm kiếm</button>
      </div>
      <div class="col-6">
        <button class="btn btn-outline-warning" @click="handleCreate">Thêm mới</button>
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
          <span class="me-2" @click="handleUpdate(row)"><svg-icon icon-class="edit" style="color: #083863" /></span>
          <span @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A" /></span>
        </template>
      </el-table-column>
      <el-table-column label="Loại chấm điểm" width="250px">
        <template slot-scope="{row}">
          {{ row.type === 1 ? 'Thông thường' : 'Định kỳ' }}
        </template>
      </el-table-column>
      <el-table-column label="Loại hình sản phẩm">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Thời gian xử lý">
        <template slot-scope="{row}">
          {{ convertTime(row.day, row.hour, row.minute) }}
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" class="float-end" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" layout="prev, pager, next, sizes" @pagination="getList" />
    <add-lending-product
      :temp="temp"
      :dialog-form-visible.sync="dialogFormVisible"
      :list="list"
      :dialog-status="dialogStatus"
      :title="textMap[dialogStatus]"
    />
    <delete-lending-product
      :dialog-delete-visible.sync="dialogDeleteVisible"
    />
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
import AddLendingProduct from '@/views/procedure/lending-product/components/add-lending-product'
import DeleteLendingProduct from '@/views/procedure/lending-product/components/delete-lending-product'

export default {
  name: 'LendingProductList',
  components: { DeleteLendingProduct, AddLendingProduct, Pagination },
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
      filter: {
        name: '',
        status: null
      },
      temp: {
        type: null,
        name: '',
        day: null,
        hour: null,
        minute: null
      },
      dialogFormVisible: false,
      dialogDeleteVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Sửa loại hình sản phẩm',
        create: 'Thêm mới loại hình sản phẩm'
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    resetTemp() {
      this.temp = {
        type: null,
        name: '',
        day: null,
        hour: null,
        minute: null
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
    convertTime(day, hour, minute) {
      let time = ''
      if (day) {
        time = `${day} ngày, ${!hour ? 0 : hour} giờ, ${!minute ? 0 : minute} phút`
      } else {
        if (hour) {
          time = `${hour} giờ, ${!minute ? 0 : minute} phút`
        } else {
          time = `${minute} phút`
        }
      }
      return time
    },
    getList() {
      this.listLoading = true
      this.list = [
        {
          id: 1,
          type: 1,
          name: 'Oto',
          day: null,
          hour: null,
          minute: 50
        },
        {
          id: 2,
          type: 1,
          name: 'Thông thường',
          day: null,
          hour: 6,
          minute: null
        },
        {
          id: 3,
          type: 2,
          name: 'Oto',
          day: 9,
          hour: null,
          minute: null
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
