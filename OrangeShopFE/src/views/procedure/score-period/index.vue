<template>
  <div class="app-container">
    <el-form ref="scorePeriodFilterForm" label-position="left" class="mt-3">
      <div class="row">
        <div class="col-3">
          <el-form-item>
            <label for="name">Kỳ chấm điểm</label>
            <el-select id="name" v-model="filter.name" class="form-control non-border" placeholder="Nhập kỳ chấm điểm">
              <el-option label="Đang thực hiện" value="1" />
              <el-option label="Khóa" value="2" />
            </el-select>
          </el-form-item>
        </div>
        <div class="col-3">
          <el-form-item>
            <label for="status">Trạng thái</label>
            <el-select id="status" v-model="filter.status" class="form-control non-border" placeholder="---Tất cả---">
              <el-option label="Đang thực hiện" value="1" />
              <el-option label="Khóa" value="2" />
            </el-select>
          </el-form-item>
        </div>
      </div>
    </el-form>
    <div class="row mt-3 mb-5">
      <div class="col-6">
        <button class="btn btn-outline-primary float-end">Tìm kiếm</button>
      </div>
      <div class="col-6">
        <router-link to="/score-periods/add" class="btn btn-outline-warning">Thêm mới</router-link>
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
        <template slot-scope="{row}">
          <router-link
            :to="{ name: 'EditScorePeriod', params: { scorePeriod: row, id: row.id } }"
          >
            <span><svg-icon icon-class="edit" style="color: #083863" /></span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="Kỳ chấm điểm" width="200px">
        <template slot-scope="{row}">
          {{ row.name }}
          <br>
          <router-link
            :to="{ name: 'DetailScorePeriod', params: { scorePeriod: row, id: row.id } }"
          >
            Xem chi tiết
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="Thời gian tổ chức chấm điểm">
        <template slot-scope="{row}">
          <span>{{ row.startDate }} - {{ row.endDate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Trạng thái" width="200px">
        <template slot-scope="{row}">
          {{ row.status === 1 ? 'Đang thực hiện' : 'Khóa' }}
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" class="float-end" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" layout="prev, pager, next, sizes" @pagination="getList" />
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'

export default {
  name: 'ScorePeriod',
  components: { Pagination },
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
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      this.list = [
        {
          id: 1,
          name: '2022',
          startDate: '01/04/2022',
          endDate: '01/04/2022',
          status: 1
        },
        {
          id: 2,
          name: '2021',
          startDate: '01/04/2021',
          endDate: '01/04/2021',
          status: 2
        },
        {
          id: 3,
          name: '2020',
          startDate: '01/04/2020',
          endDate: '01/04/2020',
          status: 2
        },
        {
          id: 4,
          name: '2019',
          startDate: '01/04/2019',
          endDate: '01/04/2019',
          status: 2
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
