<template>
  <div class="fluid-container mx-5 mt-1">
    <el-tabs
      ref="eltab"
      v-model="activeName"
      :stretch="true"
      class="vh-90"
      @tab-click="handleClickTab"
    >
      <el-tab-pane label="Chỉ tiêu phi tài chính" name="nonFinancialNorm">
        <div class="d-block float-right pb-5">
          <el-button type="css-success" @click="exportData">EXPORT</el-button>
        </div>
        <custom-table-vue
          v-loading="loading"
          :table-data="tableData"
          :table-header="nonFinancialNormHeader"
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
  name: 'FinancialNorm',
  components: { CustomTableVue },
  mixins: [tableData, paginationCommon],
  data() {
    return {
      activeName: 'nonFinancialNorm',
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
    handleClickTab(tab, event) {
      this.activeName = tab.$props.name
      this.fetchDataTable()
    },
    convertTypeOfTab(name) {
      return 5
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
    getHeaderByNameTab(name) {
      return this.nonFinancialNormHeader
    },
    getNameFileByNameTab(name) {
      return 'Chỉ tiêu phi tài chính'
    },
    async exportData() {
      const res = await service.exportFinancialVariable({
        type: this.convertTypeOfTab(this.activeName),
        fileName: `${this.getNameFileByNameTab(this.activeName)}.csv`,
        headers: this.getHeaderByNameTab(this.activeName)
      })
      this.dowloadFile(res)
    },
    dowloadFile(res) {
      // create file link in browser's memory
      const href = window.URL.createObjectURL(new Blob([res]))

      // create "a" HTLM element with href to file & click
      const link = document.createElement('a')
      link.href = href
      link.setAttribute(
        'download',
        `${this.getNameFileByNameTab(this.activeName)}.csv`
      ) // or any other extension
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
>>> .is-active {
  color: #5e2e86 !important;
}

>>> .el-tabs__active-bar {
  background-color: #5e2e86 !important;
}

>>> .el-tabs__item:hover {
  color: #5e2e86;
  cursor: pointer;
}
</style>
