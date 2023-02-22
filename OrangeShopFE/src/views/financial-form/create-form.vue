<template>
  <div class="fluid-container">
    <div class="fluid-container mx-5 mt-3">
      <div class="row row-cols-3">
        <div class="col">
          <span style="font-weight: bold">Mẫu biểu BCTC</span>
          <el-select
            v-model="data.typeFReport"
            clearable
            placeholder="---Tất cả---"
            class="mt-3 d-block"
          >
            <el-option
              v-for="item in typeFReports"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
        <div class="col">
          <span style="font-weight: bold">Tên biểu mẫu nhập liệu</span>
          <el-input
            v-model="data.nameFReport"
            placeholder="Nhập tên biểu mẫu"
            prefix-icon="el-icon-search"
            class="mt-3"
          />
        </div>
        <div class="col">
          <span style="font-weight: bold">Mô tả</span>
          <el-input
            v-model="data.description"
            placeholder="Nhập tên biểu mẫu"
            prefix-icon="el-icon-search"
            class="mt-3"
          />
        </div>
      </div>
      <!-- Template -->
      <div class="row mt-5">
        <div class="col">
          <div class="d-inline">
            <p
              style="font-weight: bold; margin-right: 10px"
              class="d-inline mr-2"
            >
              Template BCTC <span style="color: red">*</span>
            </p>
            <el-button
              circle
              icon="el-icon-circle-plus"
              type="css-secondary"
              size="mini"
              style="margin-right: 10px"
            />
            <el-button
              round
              icon="el-icon-circle-plus"
              type="css-primary"
              size="mini"
              style="margin-right: 10px"
            >IMPORT</el-button>
          </div>
          <el-tabs type="border-card" stretch class="mt-3">
            <el-tab-pane label="Bảng cân đối kế toán">
              <custom-table-filter-vue
                :table-data="tableData1"
                :table-header="tableHeader1"
              />
              <div
                v-if="pagination.total > pagination.pageSize"
                class="d-block float-right"
              >
                <el-pagination
                  :current-page.sync="pagination.currentPage"
                  :page-size="pagination.pageSize"
                  layout="prev, pager, next,total"
                  :total="pagination.total"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                />
              </div>
            </el-tab-pane>
            <el-tab-pane label="Báo cáo kết quả hoạt động kinh doanh">
              <custom-table-filter-vue
                :table-data="tableData1"
                :table-header="tableHeader1"
              />
              <div
                v-if="pagination.total > pagination.pageSize"
                class="d-block float-right"
              >
                <el-pagination
                  :current-page.sync="pagination.currentPage"
                  :page-size="pagination.pageSize"
                  layout="prev, pager, next,total"
                  :total="pagination.total"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                /></div></el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FakeData from './utils/FakeData'
import customTableFilterVue from './components/custom-table-filter.vue'

export default {
  name: 'CreateForm',
  components: { customTableFilterVue },
  mixins: [FakeData],
  data() {
    return {
      data: {},
      pagination: {
        currentPage: 1,
        pageSize: 100,
        total: 10
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
