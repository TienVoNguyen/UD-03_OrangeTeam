<template>
  <div class="app-container">
    <div class="page-title">{{ pageTitle }}</div>
    <div class="col-sm-12 row">
      <div class="col-sm-3">
        <span class="">Năm <i class="required">*</i></span>
        <div>
          <el-select
            v-model="search.year"
            class="w-100"
            placeholder="Chọn năm"
          > <!--@change="searchF"-->
            <el-option
              v-for="item in years"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
      </div>

      <div class="col-sm-3">
        <span class="">Tháng <i class="required">*</i></span>
        <div>
          <el-select
            v-model="search.month"
            class="w-100"
            placeholder="Chọn tháng"
          > <!--@change="searchF"-->
            <el-option
              v-for="item in months"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
      </div>

      <div class="col-sm-3">
        <span class="">Tài khoản</span>
        <div>
          <el-select
            v-model="search.account"
            filterable
            class="w-100"
            placeholder="Chọn tài khoản"
          > <!--@change="searchF"-->
            <el-option
              v-for="item in listAccount"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
      </div>

      <div class="col-sm-3">
        <span class="">Tên chiến dịch</span>
        <el-input
          v-model="search.campaign"
          placeholder="Nhập tên chiến dịch"
          @keyup.enter.native="searchF"
        />
      </div>

      <div class="col-sm-12 d-flex justify-content-center pt-4">
        <el-button
          icon="el-icon-search"
          type="primary"
          plain
          @click="searchF"
        >Tìm kiếm</el-button>

        <el-button
          v-if="hasPermission($options.name,'ReportFailoverByMonth.ExportExcel')"
          type="success"
          plain
          @click="exportData()"
        >
          Xuất Excel
        </el-button>

        <el-button
          icon="el-icon-refresh"
          type="warning"
          plain
          @click="removeFilter()"
        >Bỏ lọc</el-button>
      </div>

      <!--<div class="pt-4 pb-4 d-block float-right text-right">
        &lt;!&ndash;v-if="hasPermission($options.name,'reportFailover.export')"&ndash;&gt;
        <el-button
          v-if="hasPermission($options.name,'ReportFailoverByMonth.ExportExcel')"
          type="success"
          plain
          @click="exportData()"
        >
          Xuất excel
        </el-button>
      </div>-->

      <div class="pt-4">
        <el-table
          id="reportFailoverByMonth"
          :data="items"
          style="width: 100%"
          border
          :span-method="colSpanMethod"
          :header-cell-style="{textAlign:'center'}"
        >
          <el-table-column label="STT" header-align="center" align="center">
            <template v-slot="scope">
              {{ scope.$index === 0 ? 'Tổng' : scope.$index }}
            </template>
          </el-table-column>
          <el-table-column prop="accountName" label="Tài khoản" />
          <el-table-column prop="campaignCode" label="Chiến dịch" />
          <el-table-column label="Thông tin đơn hàng FailOver">
            <el-table-column prop="dataNumber" :formatter="formatNumber" label="Tổng SĐT" align="right" />
            <el-table-column prop="countZalo" :formatter="formatNumber" label="Tổng SĐT thành công Zalo" align="right" />
            <el-table-column prop="countViber" :formatter="formatNumber" label="Tổng SĐT thành công Viber" align="right" />
            <el-table-column prop="countSms" :formatter="formatNumber" label="Tổng SĐT thành công SMS" align="right" />
            <el-table-column prop="countError" :formatter="formatNumber" label="Tổng SĐT thất bại" align="right" />
            <el-table-column prop="countDuplicate" :formatter="formatNumber" label="Tổng SĐT lặp" align="right" />
          </el-table-column>
        </el-table>
        <div class="mt-4 d-block float-right text-right">
          <el-pagination
            :current-page.sync="pagination.pageNumber"
            :page-size="pagination.pageSize"
            :page-sizes="pageSizes"
            layout="sizes,prev, pager, next, total"
            :total="pagination.totalItems"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import baseCommon from '@/utils/base-common'
import reportCommon from '../mixin/report-mixin'
import title from '@/utils/mx-title'
import * as reportService from '../service/report-service'

export default {
  name: 'ReportFailoverByMonth', // Mã màn hình
  mixins: [baseCommon, reportCommon, title],
  data() {
    return {
      itemsTest: [
        {
          'accountName': 'Total',
          'campaignCode': 'Total',
          'dataNumber': 55000,
          'countZalo': 55000,
          'Countviber': 50500,
          'CountSms': 25000,
          'countError': 35000,
          'CountDuplicate': 25000
        },
        {
          'accountName': 'BiBo',
          'campaignCode': 'BiBo_C1',
          'dataNumber': 50000,
          'countZalo': 5000,
          'Countviber': 5000,
          'CountSms': 15000,
          'countError': 5000,
          'CountDuplicate': 20000
        },
        {
          'accountName': 'BiRain',
          'campaignCode': 'BiRain_C2',
          'dataNumber': 50000,
          'countZalo': 5000,
          'Countviber': 5000,
          'CountSms': 15000,
          'countError': 5000,
          'CountDuplicate': 20000
        },
        {
          'accountName': 'Văn Thanh',
          'campaignCode': 'LeeMinHo_C2',
          'dataNumber': 26000,
          'countZalo': 16000,
          'Countviber': 28000,
          'CountSms': 18000,
          'countError': 8000,
          'CountDuplicate': 29000
        }
      ],
      items: [],
      years: null,
      months: null,
      search: {
        year: null,
        month: null,
        campaign: null,
        account: null
      },
      pageTitle: null
    }
  },
  watch: {
    'pagination.pageSize': function(newValue, oldValue) {
      this.pagination.pageSize = newValue
      this.pagination.pageNumber = 1
      this.getData()
    }
  },
  created() {
    this.years = this.genYears()
    this.months = this.genMonths()
    this.search.month = this.currentMonth
    this.search.year = this.currentYear
    this.getData()
    this.getListAccount()
  },
  methods: {
    async getData() {
      // Show data Demo
      /* const self = this
      this.showLoading()
      await setTimeout(function() {
        self.items = self.itemsTest
        self.pagination = {
          pageNumber: 1,
          pageSize: 10,
          totalItems: self.itemsTest.length
        }
        self.hideLoading()
      }, 500)*/

      // Show data Product
      if (!this.validateSearchInput()) {
        return
      }

      const params = {
        'year': parseInt(this.search.year),
        'month': parseInt(this.search.month),
        'accountId': this.search.account,
        'campaignCode': this.isNullorEmpty(this.search.campaign) ? this.search.campaign : this.search.campaign.toString().trim(),
        'pageIndex': this.pagination.pageNumber,
        'pageSize': this.pagination.pageSize
      }

      this.showLoading()
      const response = await reportService.getReportFailoverByMonth(params)
      if (response.code === 200) {
        // debugger
        // eslint-disable-next-line no-undef
        if (_.isEmpty(response.data.result[0]) || response.data.result.length === 1) {
          this.items = []
        } else {
          this.items = response.data.result
        }
        // console.log(this.items)
        this.pagination.totalItems = response.data.totalItems
        // this.notifyInfo('Thông tin', response.message)
        this.hideLoading()
      }
    },

    async exportData() {
      if (!this.validateSearchInput()) {
        return
      }

      const params = {
        'year': parseInt(this.search.year),
        'month': parseInt(this.search.month),
        'accountId': this.search.account,
        'campaignCode': this.isNullorEmpty(this.search.campaign) ? this.search.campaign : this.search.campaign.toString().trim(),
        'pageIndex': 1,
        'pageSize': 999999
      }

      this.showLoading()
      const response = await reportService.exportExcelReportFailoverByMonth(params)
      this.downloadExcel(response, 'BaoCaoTongHopFailOverThang' + this.search.month + '.xlsx')
      this.hideLoading()

      /* response.then(res => {
        this.downloadExcel(res, 'BaoCaoTongHopFailOverThang' + this.search.month + '.xlsx')
        this.hideLoading()
      })*/
    },
    searchF() {
      this.pagination.pageNumber = 1
      this.getData()
    },
    removeFilter() {
      this.search = {
        year: this.currentYear,
        month: this.currentMonth,
        campaign: null,
        account: null
      }
      this.pagination = {
        pageNumber: 1,
        pageSize: 10,
        totalItems: 0,
        totalPages: 0
      }
      this.getData()
    },
    colSpanMethod({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        if (columnIndex === 0) {
          return [1, 3]
        } else if (columnIndex === 1 || columnIndex === 2) {
          return [0, 0]
        }
      }
    },
    validateSearchInput() {
      if (this.isEmpty(this.search.month)) {
        this.notifyWarning('Cảnh báo', 'Vui lòng chọn tháng')
        return false
      }
      if (this.isEmpty(this.search.year)) {
        this.notifyWarning('Cảnh báo', 'Vui lòng chọn năm')
        return false
      }
      return true
    }
  }
}
</script>

<style scoped>
  .float-right {
    float: right;
  }
  .text-right{
    text-align: right;
  }
</style>
