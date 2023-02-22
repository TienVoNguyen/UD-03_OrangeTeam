<template>
  <div class="fluid-container mx-5 mt-1">
    <el-tabs
      ref="eltab"
      v-model="activeName"

      :stretch="true"
      class="vh-90"
      @tab-click="handleClickTab"
    >
      <el-tab-pane label="Bảng cân đối kế toán" name="balanceSheet">
        <div class="d-block float-right pb-5">
          <el-button type="css-success" @click="exportData">EXPORT</el-button>
        </div>
        <custom-table-vue
          v-loading="loading"
          :table-data="tableData"
          :table-header="balanceSheetHeader"
          :height="tableHeight"
          @filter="handleFilter"
        />
      </el-tab-pane>
      <el-tab-pane label="Báo cáo kết quả kinh doanh" name="financialState">
        <div class="d-block float-right pb-5">
          <el-button type="css-success" @click="exportData">EXPORT</el-button>
        </div>
        <custom-table-vue
          v-loading="loading"
          :table-data="tableData"
          :table-header="financialStateHeader"
          :height="tableHeight"
          @filter="handleFilter"
        />
      </el-tab-pane>
      <el-tab-pane label="Báo cáo lưu chuyển tiền tệ" name="cashFlowStatement">
        <div class="d-block float-right pb-5">
          <el-button type="css-success" @click="exportData">EXPORT</el-button>
        </div>
        <custom-table-vue
          v-loading="loading"
          :table-data="tableData"
          :table-header="cashFlowStatementHeader"
          :height="tableHeight"
          @filter="handleFilter"
        />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import CustomTableVue from './components/custom-table.vue'
import tableData from './utils/tableData'
import paginationCommon from '@/utils/pagination-common'
import * as service from './service/financial-service'

export default {
  name: 'EnterpriseFinancialStatuement',
  components: { CustomTableVue },
  mixins: [tableData, paginationCommon],
  data() {
    return {
      activeName: 'balanceSheet',
      tableHeight: 0,
      filter: {
        variable: '',
        varCode: '',
        preVarCode: ''
      },
      loading: true
    }
  },
  async created() {
    await this.fetchDataTable()
    this.tableHeight = this.calculateHeightTable()
  },
  mounted() {
    window.addEventListener('resize', this.handleResize)
  },
  methods: {
    handleSizeChange(val) {
      console.log(`${val} items per page`)
    },
    handleCurrentChange(val) {
      console.log(`current page: ${val}`)
    },
    handleClickTab(tab, event) {
      this.activeName = tab.$props.name
      this.fetchDataTable()
    },
    convertTypeOfTab(name) {
      switch (name) {
        case 'balanceSheet':
          return 1
        case 'financialState':
          return 2
        case 'cashFlowStatement':
          return 3
        default:
          return 1
      }
    },
    getHeaderByNameTab(name) {
      switch (name) {
        case 'balanceSheet':
          return this.balanceSheetHeader
        case 'financialState':
          return this.financialStateHeader
        case 'cashFlowStatement':
          return this.cashFlowStatementHeader
        default:
          return this.balanceSheetHeader
      }
    },
    getNameFileByNameTab(name) {
      switch (name) {
        case 'balanceSheet':
          return 'Bảng cân đối kế toán'
        case 'financialState':
          return 'Báo cáo kết quả kinh doanh'
        case 'cashFlowStatement':
          return 'Báo cáo lưu chuyển tiền tệ'
        default:
          return 'Bảng cân đối kế toán'
      }
    },
    handleResize() {
      this.tableHeight = this.calculateHeightTable()
    },
    calculateHeightTable() {
      const tabheight = this.$refs.eltab.$el.clientHeight
      const tabHeader = this.$refs.eltab.$el.children[0].clientHeight
      const buttonExport =
        this.$refs.eltab.$el.children[1].children[0].children[0].clientHeight
      return tabheight - tabHeader - buttonExport
    },
    async fetchDataTable() {
      this.loading = true
      const res = await service.fetchFinancialVariable({
        type: this.convertTypeOfTab(this.activeName),
        ...this.filter
      })
      this.tableData = res.data.result
      this.loading = false
    },
    handleFilter(data) {
      this.filter = { ...data }
      this.fetchDataTable()
    },
    async exportData() {
      const res = await service.exportFinancialVariable({
        type: this.convertTypeOfTab(this.activeName),
        fileName: `${this.getNameFileByNameTab(this.activeName)}.csv`,
        headers: this.getHeaderByNameTab(this.activeName)
      })

      // create file link in browser's memory
      const href = window.URL.createObjectURL(new Blob([res]))

      // create "a" HTLM element with href to file & click
      const link = document.createElement('a')
      link.href = href
      link.setAttribute('download', `${this.getNameFileByNameTab(this.activeName)}.csv`) // or any other extension
      document.body.appendChild(link)
      link.click()

      // clean up "a" element & remove ObjectURL
      document.body.removeChild(link)
      window.URL.revokeObjectURL(href)
    }
  }
}
</script>

<style scoped>
.float-right {
  float: right;
}
.vh-90 {
  height: 90vh;
}
>>> .is-active{
  color: #5E2E86 !important;
}

>>> .el-tabs__active-bar{
  background-color:#5E2E86 !important;
}

>>> .el-tabs__item:hover {
    color: #5E2E86;
    cursor: pointer;
}
</style>
