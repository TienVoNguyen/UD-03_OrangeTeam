<template>
  <div class="app-container">
    <div class="page-title">{{ pageTitle }}</div>
    <div class="col-sm-12 row">
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

      <div class="col-sm-6">
        <span class="" style="padding-left: 7%">
          Ngày gửi từ <i class="required">*</i>
        </span>
        <div class="col-sm-12 d-flex justify-content-center position-relative">
          <el-date-picker
            v-model="search.fromDate"
            style="width: 40%"
            placeholder="Từ ngày"
            type="date"
            value-format="yyyy-MM-dd"
            format="dd/MM/yyyy"
            @focus="setDefaultFromDate()"
          />
          <i
            class="el-icon-minus font-weight-bold"
            style="padding: 1% 2% 0 2%"
          />
          <span class="to-date">đến <i class="required">*</i></span>
          <el-date-picker
            v-model="search.toDate"
            style="width: 40%"
            placeholder="Đến ngày"
            type="date"
            format="dd/MM/yyyy"
            value-format="yyyy-MM-dd"
            @focus="setDefaultToDate()"
          />
        </div>
      </div>

      <div class="col-sm-3 ">
        <span class="">Chiến dịch</span>
        <el-input
          v-model="search.campaign"
          placeholder="Nhập tên chiến dịch"
          style="width: 86%"
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
          v-if="hasPermission($options.name,'ReportFailoverDetail.ExportExcel')"
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
          v-if="hasPermission($options.name,'ReportFailoverDetail.ExportExcel')"
          type="success"
          plain
          @click="exportData()"
        >
          Xuất Excel
        </el-button>
      </div>-->

      <div class="pt-4">
        <el-table
          v-horizontal-scroll="'always'"
          :data="items"
          style="width: 100%"
          border
        >
          <el-table-column
            v-for="item in headers"
            :key="item.prop"
            :label="item.label"
            :prop="item.prop"
            :formatter="item.formatter"
            :min-width="item.width"
            :align="item.align"
            header-align="center"
          />
        </el-table>
      </div>

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
</template>

<script>
import baseCommon from '@/utils/base-common'
import reportCommon from '@/views/reports/mixin/report-mixin'
import title from '@/utils/mx-title'
import * as reportService from '@/views/reports/service/report-service'
import horizontalScroll from 'el-table-horizontal-scroll'
import { alertCommon } from '@/utils/alert-common'

export default {
  name: 'ReportFailoverDetail', // Mã màn hình
  directives: {
    horizontalScroll
  },
  mixins: [baseCommon, reportCommon, title],
  data() {
    return {
      itemsTest: [
        {
          'accountName': 'BiBo',
          'campaignName': 'BIBO_C',
          'trafficSource': 'API',
          'brandname': '123456',
          'from': '266548996578556',
          'to': '8498576624456',
          'messageId': '554998-agigresd-12224',
          'sendDate': '01/10/2021 18:00:00',
          'telco': 'Viettel',
          'timeSetup': 50,
          'statusFailover': 'Success',
          'statusTelco': 'Success',
          'errorCode': '00',
          'errorName': 'Success',
          'responseDate': '01/10/2021 19:00:00',
          'mt': 2,
          'method': 'Zalo'
        },
        {
          'accountName': 'VanThanh',
          'campaignName': 'VanThanh_C',
          'trafficSource': 'API',
          'brandname': '5697746',
          'from': '266548996578556',
          'to': '8498576624456',
          'messageId': '554998-oeiirir-12224',
          'sendDate': '01/10/2021 18:00:00',
          'telco': 'Mobifone',
          'timeSetup': 50,
          'statusFailover': 'Failed',
          'statusTelco': 'Failed',
          'errorCode': '06',
          'errorName': 'Expired time',
          'responseDate': '01/10/2021 19:00:00',
          'mt': 2,
          'method': 'Zalo'
        },
        {
          'accountName': 'TranBinh',
          'campaignName': 'TranBinh_C',
          'trafficSource': 'API',
          'brandname': '5669876',
          'from': '266548996578556',
          'to': '8498576622524456',
          'messageId': '554998-ahdhrty-12224',
          'sendDate': '01/10/2021 18:00:00',
          'telco': 'Vinaphone',
          'timeSetup': 15,
          'statusFailover': 'Success',
          'statusTelco': 'Failed',
          'errorCode': '07',
          'errorName': 'System Error',
          'responseDate': '01/10/2021 19:00:00',
          'mt': 2,
          'method': 'Viber'
        },
        {
          'accountName': 'CongKhiem',
          'campaignName': 'CongKhiem_C',
          'trafficSource': 'API',
          'brandname': '123456',
          'from': '266548996578556',
          'to': '849857669988',
          'messageId': '554998-Xyz-12224',
          'sendDate': '01/10/2021 18:00:00',
          'telco': 'Viettel',
          'timeSetup': 50,
          'statusFailover': 'Success',
          'statusTelco': 'Success',
          'errorCode': '00',
          'errorName': 'Success',
          'responseDate': '01/10/2021 19:00:00',
          'mt': 2,
          'method': 'SMS'
        }
      ],
      items: [],
      search: {
        fromDate: null,
        toDate: null,
        campaign: null,
        account: null
      },
      headers: [
        {
          label: 'STT',
          prop: 'stt',
          formatter: null,
          width: 50
        },
        {
          label: 'Account Name',
          prop: 'accountName',
          formatter: null,
          width: 120
        },
        {
          label: 'Campaign Name',
          prop: 'campaignName',
          formatter: null,
          width: 100
        },
        {
          label: 'Traffic Source',
          prop: 'trafficSource',
          formatter: null
        },
        {
          label: 'From',
          prop: 'from',
          formatter: null
        },
        {
          label: 'To',
          prop: 'to',
          formatter: null,
          width: 150
        },
        {
          label: 'Message Id',
          prop: 'messageId',
          formatter: null
        },
        {
          label: 'Send Date',
          prop: 'sendDate',
          formatter: row => { return this.convertDate(row.sendDate) },
          width: 150,
          align: 'center'
        },
        {
          label: 'Telco',
          prop: 'telco',
          formatter: null
        },
        {
          label: 'Time Setup (s)',
          prop: 'timeSetup',
          formatter: null
        },
        {
          label: 'Status Failover',
          prop: 'statusFailover',
          formatter: null
        },
        {
          label: 'Status Telco',
          prop: 'statusTelco',
          formatter: null
        },
        {
          label: 'Error Code',
          prop: 'errorCode',
          formatter: null,
          width: 300
        },
        {
          label: 'Error Name',
          prop: 'errorName',
          formatter: null
        },
        {
          label: 'Response Date',
          prop: 'responseDate',
          formatter: row => { return this.convertDate(row.sendDate) },
          width: 150,
          align: 'center'
        },
        {
          label: 'MT',
          prop: 'mt',
          formatter: null
        },
        {
          label: 'Method',
          prop: 'method',
          formatter: null
        }
      ]
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
    const currentDate = this.nowDate
    this.nowDate.setDate(this.nowDate.getDate() - 7)
    this.search.fromDate = currentDate
    this.search.toDate = new Date()
    this.getData()
    this.getListAccount()
  },
  methods: {
    async getData() {
      // Data demo fixed
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
      }, 500) */

      // Get data from api
      if (!this.validateSearchInput()) {
        return
      }

      const params = {
        'fromDate': this.search.fromDate,
        'toDate': this.search.toDate,
        'accountId': this.search.account,
        'campaignCode': this.isNullorEmpty(this.search.campaign) ? this.search.campaign : this.search.campaign.toString().trim(),
        'pageIndex': this.pagination.pageNumber,
        'pageSize': this.pagination.pageSize
      }

      this.showLoading()
      const response = await reportService.getReportFailoverDetail(params)
      if (response.code === 200) {
        this.items = response.data.result
        const self = this
        this.items.forEach((v, i) => { v.stt = (self.pagination.pageNumber - 1) * self.pagination.pageSize + i + 1 })
        this.pagination.totalItems = response.data.totalRecord
        // this.notifyInfo('Thông tin', this.convertMessage(response.message))
        this.hideLoading()
      }
    },
    async exportData() {
      if (!this.validateSearchInput()) {
        return
      }

      const params = {
        'fromDate': this.search.fromDate,
        'toDate': this.search.toDate,
        'accountId': this.search.account,
        'campaignCode': this.isNullorEmpty(this.search.campaign) ? this.search.campaign : this.search.campaign.toString().trim(),
        'pageIndex': 1,
        'pageSize': 999999
      }

      this.showLoading()
      const response = await reportService.exportExcelReportFailoverDetail(params)
      this.downloadExcel(response, 'BaoCaoLogDetailFailOver.xlsx')
      this.hideLoading()

      /* response.then(res => {
        this.downloadExcel(res, 'BaoCaoLogDetailFailOver.xlsx')
        this.hideLoading()
      })*/
    },
    searchF() {
      this.pagination.pageNumber = 1
      this.getData()
    },
    removeFilter() {
      this.pagination = {
        pageNumber: 1,
        pageSize: 10,
        totalItems: 0,
        totalPages: 0
      }
      this.search = {
        'fromDate': this.nowDate,
        'toDate': new Date(),
        'accountId': null,
        'campaignCode': null
      }
      this.getData()
    },
    validateSearchInput() {
      // debugger
      if (this.isEmpty(this.search.fromDate)) {
        this.notifyWarning('Cảnh báo', alertCommon.from_date_empty)
        return false
      }

      if (this.isEmpty(this.search.toDate)) {
        this.notifyWarning('Cảnh báo', alertCommon.to_date_empty)
        return false
      }

      if (this.search.fromDate != null && this.search.toDate != null) {
        if (this.convertDateObjectToString(this.search.fromDate) > this.convertDateObjectToString(this.search.toDate)) {
          this.notifyWarning('Cảnh báo', alertCommon.from_date_bigger_to_date)
          return false
        }
      }

      return true
    },
    setDefaultFromDate() {
      const fromDateDefault = new Date()
      fromDateDefault.setDate(fromDateDefault.getDate() - 7)
      const temp = fromDateDefault
      if (this.convertDateObjectToString(this.search.fromDate) === this.convertDateObjectToString(temp)) {
        this.search.fromDate = fromDateDefault
        // eslint-disable-next-line no-empty
      } else {
        const temp1 = this.search.fromDate
        const temp1ToTimeStamp = Date.parse(temp1)
        const temp1ToDate = new Date(temp1ToTimeStamp)
        if (this.convertDateObjectToString(this.search.fromDate) === this.convertDateObjectToString(temp1)) {
          this.search.fromDate = temp1ToDate
        }
      }
    },
    setDefaultToDate() {
      const toDateDefault = new Date()
      const temp = toDateDefault
      if (this.convertDateObjectToString(this.search.toDate) === this.convertDateObjectToString(temp)) {
        this.search.toDate = toDateDefault
        // eslint-disable-next-line no-empty
      } else {
        const temp1 = this.search.toDate
        const temp1ToTimeStamp = Date.parse(temp1)
        const temp1ToDate = new Date(temp1ToTimeStamp)
        if (this.convertDateObjectToString(this.search.toDate) === this.convertDateObjectToString(temp1)) {
          this.search.toDate = temp1ToDate
        }
      }
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
  .to-date{
    position: absolute;
    top: -22px;
    right: 42.5%;
    z-index: 1;
  }
</style>
