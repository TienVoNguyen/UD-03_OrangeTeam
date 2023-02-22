<template>
  <div class="app-container">
    <div class="filter-container">
      <h4>Danh sách quy trình</h4>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      :header-cell-style="{ background: '#F2F2F3', color: '#000000', border: 'none' }"
      :cell-style="{borderRight: 'none'}"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="Thao tác" width="100px" align="center">
        <template slot-scope="{row}">
          <router-link :to="{ path: '/procedures/'+row.id+'/enterprises'}"><span><i class="el-icon-s-tools" style="color: #EF8C1A" /></span></router-link>
        </template>
      </el-table-column>
      <el-table-column label="Tên quy trình" min-width="150px">
        <template slot-scope="{row}">
          {{ row.name }}
        </template>
      </el-table-column>
      <el-table-column label="Đơn vị áp dụng">
        <template slot-scope="{row}">
          <span>{{ row.unit?.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Luồng duyệt">
        <template slot-scope="{row}">
          {{ row.browsingFlow?.name }}
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>listQuery.limit" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" layout="prev, pager, next, sizes" @pagination="getList" />
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'Procedure',
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
        type: undefined
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
          name: 'Quy trình chấm điểm và xếp hạng tín dụng KHDN',
          unit: {
            name: 'Khách hàng doanh nghiệp'
          },
          browsingFlow: {
            name: 'Nhanh'
          }
        },
        {
          id: 2,
          name: 'Quy trình chấm điểm và xếp hạng tín dụng KHDN',
          unit: {
            name: 'Khách hàng doanh nghiệp'
          },
          browsingFlow: {
            name: 'Thông thường'
          }
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
