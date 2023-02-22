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
            class="w-100"
            placeholder="Chọn tài khoản"
            filterable
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
          v-if="hasPermission($options.name,'ReportFailoverByCampaign.ExportExcel')"
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
          v-if="hasPermission($options.name,'ReportFailoverByCampaign.ExportExcel')"
          type="success"
          plain
          @click="exportData()"
        >
          Xuất Excel
        </el-button>
      </div>-->

      <div class="pt-4">
        <el-table
          id="reportFailoverByCampaign"
          :data="items"
          style="width: 100%"
          border
          :span-method="rowSpanMethod"
          :header-cell-style="{textAlign:'center'}"
        >
          <el-table-column prop="campaignCode" label="Mã chiến dịch" />
          <el-table-column prop="chanelName" label="Kênh gửi tin" />
          <el-table-column prop="brandName" label="Brandname" />
          <el-table-column prop="countSmsSent" :formatter="formatNumber" label="Số lượng truyền đi" align="right" />
          <el-table-column prop="countSmsSentOk" :formatter="formatNumber" label="Số lượng thành công thực tế" align="right" />
          <el-table-column prop="countSmsSentOnTime" :formatter="formatNumber" label="SL thành công trong thời gian cho phép" align="right" />
          <el-table-column prop="countMtSms" :formatter="formatNumber" label="SMS FailOver MT" align="right" />
          <el-table-column prop="countPayOnChanel" :formatter="formatNumber" label="Số lượng thanh toán theo kênh" align="right" />
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
import reportCommon from '@/views/reports/mixin/report-mixin'
import title from '@/utils/mx-title'
import * as reportService from '@/views/reports/service/report-service'

export default {
  name: 'ReportFailoverByCampaign', // Mã màn hinh
  mixins: [baseCommon, reportCommon, title],
  data() {
    return {
      itemsTest: [
        {
          'campaignCode': 'Tổng',
          'chanelName': '',
          'brandname': '',
          'countSmsSent': '',
          'countSmsSentOk': 155000,
          'countSmsSentOnTime': 55000,
          'countMtSms': '',
          'countPayOnChanel': 15500
        },
        {
          'campaignCode': 'BiBo_C1',
          'chanelName': 'SMS',
          'brandname': '',
          'countSmsSent': 15000,
          'countSmsSentOk': 5000,
          'countSmsSentOnTime': 3000,
          'countMtSms': 1500,
          'countPayOnChanel': 1500
        },
        {
          'campaignCode': 'BiBo_C1',
          'chanelName': 'Viber',
          'brandname': '',
          'countSmsSent': 15000,
          'countSmsSentOk': 5000,
          'countSmsSentOnTime': 3000,
          'countMtSms': 1500,
          'countPayOnChanel': 1500
        },
        {
          'campaignCode': 'BiBo_C1',
          'chanelName': 'Zalo',
          'brandname': '',
          'countSmsSent': 16000,
          'countSmsSentOk': 6000,
          'countSmsSentOnTime': 5000,
          'countMtSms': 1700,
          'countPayOnChanel': 1700
        },
        {
          'campaignCode': 'VanThanh_C2',
          'chanelName': 'Zalo',
          'brandname': '',
          'countSmsSent': 25000,
          'countSmsSentOk': 8200,
          'countSmsSentOnTime': 3300,
          'countMtSms': 1800,
          'countPayOnChanel': 1900
        },
        {
          'campaignCode': 'VanThanh_C2',
          'chanelName': 'Viber',
          'brandname': '',
          'countSmsSent': 45000,
          'countSmsSentOk': 15000,
          'countSmsSentOnTime': 5600,
          'countMtSms': 1500,
          'countPayOnChanel': 1500
        }
      ],
      items: null,
      years: null,
      months: null,
      nowDate: new Date(),
      search: {
        year: null,
        month: null,
        campaign: null,
        account: null
      },
      spanArr: []
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
  updated() {
    // this.onMergeRows()
  },
  methods: {
    async getData() {
      // Demo data local
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
      const response = await reportService.getReportFailoverByCamp(params)
      if (response.code === 200) {
        // eslint-disable-next-line no-undef
        if (_.isEmpty(response.data.result[0]) || response.data.result.length === 1) {
          this.items = []
        } else {
          this.items = response.data.result
          this.items[0].campaignCode = 'Tổng'
          this.onMergeRows()
        }
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
      const response = await reportService.exportExcelReportFailoverByCamp(params)
      this.downloadExcel(response, 'BaoCaoTongHopFailOverCamp.xlsx')
      this.hideLoading()

      /* response.then(res => {
        this.downloadExcel(res, 'BaoCaoTongHopFailOverCamp.xlsx')
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
    onMergeRows() {
      this.spanArr = []
      this.items.forEach((item, index) => {
        if (index === 0) {
          this.spanArr.push(1)
          this.position = 0
        } else {
          if (
            this.items[index].campaignCode ===
            this.items[index - 1].campaignCode
          ) {
            this.spanArr[this.position] += 1
            this.spanArr.push(0)
          } else {
            this.spanArr.push(1)
            this.position = index
          }
        }
        // console.log(this.spanArr)
        // console.log(this.position)
        // console.log(this.spanArr[this.position])
      })
    },
    rowSpanMethod({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        if (columnIndex === 0) {
          return [1, 3]
        } else if (columnIndex === 2 || columnIndex === 1) {
          return [0, 0]
        }
      }
      if (columnIndex === 0) {
        const _row = this.spanArr[rowIndex]
        const _col = _row > 0 ? 1 : 0
        return {
          rowspan: _row,
          colspan: _col
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
