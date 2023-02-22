<template>
  <div class="fluid-container">
    <filter-table-vue class="mt-5" />
    <div class="d-flex justify-content-center mt-5">
      <el-button type="css-secondary">Tìm kiếm</el-button>
      <el-button type="css-primary ">Thêm mới</el-button>
    </div>
    <div class="mx-5 mt-5">
      <custom-table-vue :table-data="tableData" :table-header="tableHeader" />
      <div class="d-block float-right">
        <el-pagination
          :current-page.sync="pagination.currentPage"
          :page-size="pagination.pageSize"
          layout="prev, pager, next,total"
          :total="pagination.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
  </div>
</template>

<script>
import filterTableVue from './components/filter-table.vue'
import customTableVue from './components/custom-table.vue'
import FakeData from './utils/FakeData'

export default {
  name: 'FinancialReport',
  components: { filterTableVue, customTableVue },
  mixins: [FakeData],
  data() {
    return {
      pagination: {
        currentPage: 1,
        pageSize: 100,
        total: 1000
      }
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`${val} items per page`)
    },
    handleCurrentChange(val) {
      console.log(`current page: ${val}`)
    }
  }
}
</script>

<style scoped>
.float-right {
  float: right;
}
</style>
