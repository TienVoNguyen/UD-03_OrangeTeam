<template>
  <div class="app-container">
    <el-form ref="filterForm" label-position="left" class="mt-3">
      <div class="row">
        <div class="col-3">
          <el-form-item>
            <label for="code">Mã ngành kinh tế FCC</label>
            <el-input
              id="code"
              ref="code"
              v-model="filter.economicFCCCode"
              class="form-control non-border"
              placeholder="Nhập mã ngành kinh tế FCC"
            />
          </el-form-item>
        </div>
        <div class="col-1" />
        <div class="col-3">
          <el-form-item>
            <label for="name">Tên ngành kinh tế FCC</label>
            <el-input
              id="name"
              ref="name"
              v-model="filter.economicFCCName"
              class="form-control non-border"
              placeholder="Nhập tên ngành kinh tế FCC"
            />
          </el-form-item>
        </div>
        <div class="col-1" />
        <div class="col-3">
          <el-form-item>
            <label for="status">Trạng thái</label>
            <el-select id="status" v-model="filter.status" class="form-control non-border" placeholder="---Tất cả---">
              <el-option label="Tất cả" value="" />
              <el-option label="Tạo mới" value="1" />
              <el-option label="Chờ duyệt" value="2" />
              <el-option label="Từ chối" value="3" />
              <el-option label="Chỉnh sửa" value="4" />
              <el-option label="Không hoạt động" value="5" />
              <el-option label="Hoạt động" value="6" />
            </el-select>
          </el-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col-3">
          <el-form-item>
            <label for="css">Tên nhóm ngành</label>
            <el-select
              id="css"
              v-model="filter.economicCSSId"
              class="form-control non-border"
              placeholder="Chọn nhóm ngành"
            />
          </el-form-item>
        </div>
        <div class="col-1" />
        <div class="col-3">
          <el-form-item>
            <label for="sbv">Tên ngành kinh tế SBV</label>
            <el-select
              id="sbv"
              v-model="filter.economicSBVId"
              class="form-control non-border"
              placeholder="Chọn tên ngành kinh tế SBV"
            />
          </el-form-item>
        </div>
      </div>
    </el-form>
    <div class="row mt-3 mb-5">
      <div class="col-12 text-center">
        <el-button type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
        <el-button v-if="hasPermission($options.name,'fcc.insert')" type="warning" plain @click="handleCreate">Thêm mới</el-button>
        <el-button v-if="hasPermission($options.name,'fcc.import')" type="danger" plain @click="handleImport">Import</el-button>
        <el-button type="info" plain @click="handleExport">Export</el-button>
        <el-button v-if="hasPermission($options.name,'fcc.apply')" type="success" plain @click="handleApply">Trình duyệt</el-button>
        <!--        <button class="btn btn-outline-success" @click="handleApprove">Phê duyệt</button>-->
      </div>
    </div>

    <el-table
      :key="tableKey"
      :data="list"
      :header-cell-style="{ background: '#F2F2F3', color: '#000000' }"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        width="100px"
        label="Thao tác"
      >
        <template slot="header">
          <input
            ref="checkAll"
            v-model="isCheckAll"
            type="checkbox"
            class="me-2"
            @change="selectAllEconomicFCCNeedApprove"
          >
          <label> Thao tác </label>
        </template>
        <template v-if="row.status !== 2 && row.status !== 6" slot-scope="{row}">
          <div class="row">
            <div class="col-2">
              <input
                v-if="row.status === 1 || row.status === 4"
                :ref="'checkbox' + row.id"
                type="checkbox"
                @change="selectEconomicFCC(row)"
              >
            </div>
            <div class="col-2">
              <span v-if="hasPermission($options.name,'fcc.edit')" class="me-2" @click="handleUpdate(row)"><svg-icon icon-class="edit" style="color: #083863" /></span>
            </div>
            <div class="col-2">
              <span v-if="hasPermission($options.name,'fcc.delete') && row.status !== 5" @click="handleDelete(row)"><i
                class="el-icon-delete"
                style="color: #FF485A"
              /></span>
            </div>
          </div>
        </template>
        <!--        <template v-if="row.status === 2" slot-scope="{row}">-->
        <!--          <div class="row">-->
        <!--            <div class="col-2">-->
        <!--              <input-->
        <!--                :ref="'checkbox' + row.id"-->
        <!--                type="checkbox"-->
        <!--                @change="selectEconomicFCC(row)"-->
        <!--              >-->
        <!--            </div>-->
        <!--            <div v-if="hasPermission($options.name,'fcc.updatestatus')" class="col-2">-->
        <!--              <span class="me-2" @click="handleApprove()"><i class="el-icon-success text-success"/></span>-->
        <!--            </div>-->
        <!--            <div v-if="hasPermission($options.name,'fcc.reject')" class="col-2">-->
        <!--              <i class="el-icon-error text-danger" @click="handleReject(row)"/>-->
        <!--            </div>-->
        <!--          </div>-->
        <!--        </template>-->
        <!--        <template v-if="row.status === 6" slot-scope="{row}">-->
        <!--          <div v-if="hasPermission($options.name,'fcc.updatestatus')" class="row">-->
        <!--            <div class="col-2">-->
        <!--              <i class="el-icon-check text-success" @click="handleChangeStatus(row)"/>-->
        <!--            </div>-->
        <!--            <div class="col-2"/>-->
        <!--            <div class="col-2"/>-->
        <!--          </div>-->
        <!--        </template>-->
      </el-table-column>
      <el-table-column label="Mã ngành  FCC" width="150px">
        <template slot-scope="{row}">
          {{ row.economicFCCCode }}
        </template>
      </el-table-column>
      <el-table-column label="Tên ngành kinh tế FCC">
        <template slot-scope="{row}">
          {{ row.economicFCCName }}
        </template>
      </el-table-column>
      <el-table-column label="Ngành  kinh tế SBV">
        <template slot-scope="{row}">
          {{ row.economicSBVName }}
        </template>
      </el-table-column>
      <el-table-column label="Nhóm ngành">
        <template slot-scope="{row}">
          {{ row.economicCSSName }}
        </template>
      </el-table-column>
      <el-table-column label="Yêu cầu điều chỉnh" width="250px">
        <template slot-scope="{row}">
          {{ row.requestChange }}
        </template>
      </el-table-column>
      <el-table-column label="Trạng thái" width="200px">
        <template slot-scope="{row}">
          {{ renderStatus(row.status) }}
        </template>
      </el-table-column>
    </el-table>
    <div class="float-end mt-3">
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
    <dialog-fcc
      :temp="temp"
      :dialog-form-visible.sync="dialogFormVisible"
      :list.sync="list"
      :dialog-status="dialogStatus"
      :title="textMap[dialogStatus]"
      :sbv-groups="sbvGroups"
      :css-groups="cssGroups"
      :pagination.sync="pagination"
    />
    <delete-fcc
      :dialog-delete-visible.sync="dialogDeleteVisible"
      :temp="temp"
      :list.sync="list"
      :pagination.sync="pagination"
    />
    <import-fcc
      :dialog-import-visible.sync="dialogImportVisible"
      :list.sync="list"
      :pagination.sync="pagination"
    />
    <apply-fcc
      :dialog-apply-visible.sync="dialogApplyVisible"
      :economic-fcc-selected="economicFCCSelected"
      :list.sync="list"
      :is-check-all.sync="isCheckAll"
    />
    <approve-fcc
      :dialog-approve-visible.sync="dialogApproveVisible"
      :economic-fcc-selected="economicFCCSelected"
      :list.sync="list"
      :is-check-all.sync="isCheckAll"
    />
    <change-status-fcc
      :dialog-change-status-visible.sync="dialogChangeStatusVisible"
      :temp="temp"
      :list.sync="list"
    />
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import DialogFcc from '@/views/industry/fcc/components/dialog-fcc'
import DeleteFcc from '@/views/industry/fcc/components/delete-fcc'
import ImportFcc from '@/views/industry/fcc/components/import-fcc'
import ApplyFcc from '@/views/industry/fcc/components/apply-fcc'
import { getAllEconomicCSS, getAllEconomicSBV, getPageEconomicFCC } from '@/api/economic-fcc'
import baseCommon from '@/utils/base-common'
import ApproveFcc from '@/views/industry/fcc/components/approve-fcc'
import ChangeStatusFcc from '@/views/industry/fcc/components/change-status-fcc'

export default {
  name: 'Fcc',
  components: { ChangeStatusFcc, ApproveFcc, ApplyFcc, ImportFcc, DeleteFcc, DialogFcc },
  directives: { waves },
  mixins: [baseCommon],
  data() {
    return {
      tableKey: 0,
      list: null,
      dialogFormVisible: false,
      dialogImportVisible: false,
      dialogApplyVisible: false,
      dialogDeleteVisible: false,
      dialogChangeStatusVisible: false,
      dialogApproveVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Sửa ngành kinh tế FCC',
        create: 'Thêm mới ngành kinh tế FCC',
        reject: 'Từ chối ngành kinh tế FCC'
      },
      temp: {
        id: null,
        economicFCCCode: null,
        economicFCCName: null,
        economicSBVId: null,
        economicCSSId: null,
        status: null,
        requestChange: null
      },
      filter: {
        economicFCCCode: null,
        economicFCCName: null,
        status: null,
        economicCSSId: null,
        economicSBVId: null
      },
      isCheckAll: false,
      downloadLoading: false,
      sbvGroups: null,
      cssGroups: null,
      economicFCCSelected: []
    }
  },
  created() {
    this.getListEconomicFCC()
    this.getAllSBVGroup()
    this.getAllCSSGroup()
  },
  methods: {
    selectEconomicFCC(element) {
      if (this.$refs[`checkbox${element.id}`].checked) {
        const row = {
          id: element.id,
          economicFCCCode: element.economicFCCCode,
          economicFCCName: element.economicFCCName,
          economicCSSId: element.economicCSSId,
          economicSBVId: element.economicSBVId,
          status: element.status,
          requestChange: element.requestChange
        }
        this.economicFCCSelected.push(row)
        if (this.isSelectAllEconomicFCCNeedApprove()) {
          this.isCheckAll = true
        }
      } else {
        const indexRemove = this.economicFCCSelected.findIndex(economicFCC => economicFCC.id === element.id)
        this.economicFCCSelected.splice(indexRemove, 1)
        this.isCheckAll = false
      }
    },
    isSelectAllEconomicFCCNeedApprove() {
      const listEconomicFCCNeedApprove = this.list.filter(element => element.status === 1 || element.status === 4)
      let count = 0
      listEconomicFCCNeedApprove.map(element => {
        if (this.$refs[`checkbox${element.id}`].checked) {
          count++
        }
      })
      return listEconomicFCCNeedApprove.length === count
    },
    selectAllEconomicFCCNeedApprove() {
      if (!this.isCheckAll) {
        this.economicFCCSelected = []
      }
      this.list.filter(element => element.status === 1 || element.status === 4).map(element => {
        this.$refs[`checkbox${element.id}`].checked = this.isCheckAll
        if (this.isCheckAll) {
          const row = {
            id: element.id,
            economicFCCCode: element.economicFCCCode,
            economicFCCName: element.economicFCCName,
            economicCSSId: element.economicCSSId,
            economicSBVId: element.economicSBVId,
            status: element.status,
            requestChange: element.requestChange
          }
          this.economicFCCSelected.push(row)
        }
      })
    },
    resetTemp() {
      this.temp = {
        id: null,
        economicFCCCode: null,
        economicFCCName: null,
        economicSBVId: null,
        economicCSSId: null,
        status: null,
        requestChange: null
      }
    },
    handleChangeStatus(row) {
      this.temp = {
        id: row.id,
        economicFCCCode: row.economicFCCCode,
        economicFCCName: row.economicFCCName,
        economicCSSId: row.economicCSSId,
        economicSBVId: row.economicSBVId,
        status: row.status,
        requestChange: row.requestChange
      }
      this.dialogChangeStatusVisible = true
    },
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getListEconomicFCC()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getListEconomicFCC()
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.resetTemp()
      this.dialogFormVisible = true
    },
    handleUpdate(row) {
      this.temp = {
        id: row.id,
        economicFCCCode: row.economicFCCCode,
        economicFCCName: row.economicFCCName,
        economicCSSId: row.economicCSSId,
        economicSBVId: row.economicSBVId,
        status: row.status,
        requestChange: row.requestChange
      }
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    handleDelete(row) {
      this.temp = {
        id: row.id,
        economicFCCCode: row.economicFCCCode,
        economicFCCName: row.economicFCCName,
        economicCSSId: row.economicCSSId,
        economicSBVId: row.economicSBVId,
        status: row.status,
        requestChange: row.requestChange
      }
      this.dialogDeleteVisible = true
    },
    handleImport() {
      this.dialogImportVisible = true
    },
    handleApply() {
      this.dialogApplyVisible = true
    },
    handleApprove() {
      this.dialogApproveVisible = true
    },
    handleSearch() {
      this.getListEconomicFCC()
    },
    handleReject(row) {
      this.temp = {
        id: row.id,
        economicFCCCode: row.economicFCCCode,
        economicFCCName: row.economicFCCName,
        economicCSSId: row.economicCSSId,
        economicSBVId: row.economicSBVId,
        status: row.status,
        requestChange: row.requestChange
      }
      this.dialogStatus = 'reject'
      this.dialogFormVisible = true
    },
    handleExport() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(async excel => {
        const tHeader = ['STT', 'Mã ngành kinh tế FCC', 'Tên ngành kinh tế FCC', 'Ngành kinh tế SBV', 'Nhóm ngành CSS']
        const filterVal = ['index', 'economicFCCCode', 'economicFCCName', 'economicSBVName', 'economicCSSName']
        const list = this.list
        const data = this.formatJson(filterVal, list)
        const filename = this.formatFilenameExport()
        const bookType = 'xlsx'
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: filename,
          autoWidth: 'true',
          bookType: bookType
        })
        this.notifyInfo('Thông tin', `${filename}.${bookType} tải xuống thành công`)
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v, index) => filterVal.map(j => {
        if (j === 'sbv' || j === 'css') {
          return v[j].name
        } else if (j === 'index') {
          return index + 1
        } else {
          return v[j]
        }
      }))
    },
    formatFilenameExport() {
      const currentDate = new Date()
      return `${currentDate.getFullYear()}${currentDate.getMonth() + 1}${currentDate.getDate()}${currentDate.getHours()}${currentDate.getMinutes()}${currentDate.getSeconds()}_nganhKinhTeFCC`
    },
    renderStatus(status) {
      if (!status) {
        return ''
      }
      let str = ''
      switch (status) {
        case 1: {
          str = 'Tạo mới'
          break
        }
        case 2: {
          str = 'Chờ duyệt'
          break
        }
        case 3: {
          str = 'Từ chối'
          break
        }
        case 4: {
          str = 'Chỉnh sửa'
          break
        }
        case 5: {
          str = 'Không hoạt động'
          break
        }
        case 6: {
          str = 'Hoạt động'
          break
        }
      }
      return str
    },
    getListEconomicFCC() {
      this.showLoading()
      const params = {
        economicFCCCode: this.filter?.economicFCCCode,
        economicFCCName: this.filter?.economicFCCName,
        status: this.filter?.status,
        economicCSSId: this.filter?.economicCSSId,
        economicSBVId: this.filter?.economicSBVId,
        pageIndex: this.pagination.pageNumber,
        pageSize: this.pagination.pageSize
      }
      getPageEconomicFCC(params).then(response => {
        this.list = response.data.result
        this.pagination = {
          pageNumber: response.data.pageNumber,
          pageSize: response.data.pageSize,
          totalItems: response.data.totalItems,
          totalPages: response.data.totalPages
        }
        this.notifyInfo('Thông tin', response.message)
        this.hideLoading()
      })
    },
    getAllSBVGroup() {
      getAllEconomicSBV({ status: 6 }).then(response => {
        this.sbvGroups = response.data
      })
    },
    getAllCSSGroup() {
      getAllEconomicCSS({ status: 6 }).then(response => {
        this.cssGroups = response.data
      })
    }
  }
}
</script>
<style>

.non-border {
  border: 0;
  padding: 0;
}
</style>
