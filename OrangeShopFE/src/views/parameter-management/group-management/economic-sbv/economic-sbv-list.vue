<template>
  <div class="app-container">
    <div class="row mt-3">
      <div class="col-3">
        <label>Mã ngành kinh tế SBV</label>
        <el-input id="search_code" v-model="searchObj.codeSBV" value="" class="form-control non-border" placeholder="Nhập mã đơn vị" />
      </div>
      <div class="col-1" />
      <div class="col-3">
        <label>Tên ngành kinh tế SBV</label>
        <el-input id="search_name" v-model="searchObj.nameSBV" value="" class="form-control non-border" placeholder="Nhập mã số đăng ký kinh doanh" />
      </div>
      <div class="col-1" />
      <div class="col-3">
        <label for="status">Trạng thái</label>
        <el-select id="status" v-model="searchObj.status" value="" filterable clearable class="form-control non-border">
          <el-option v-for="item in status" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </div>
    </div>
    <div class="row mt-5">
      <div v-show="!isAdmin" class="col-12 text-center">
        <el-button type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
        <el-button type="warning" plain @click="handleCreate">Thêm mới</el-button>
        <el-button type="danger" plain @click="handleImport">Import</el-button>
        <el-button type="info" plain @click="handleExport">Export</el-button>
        <el-button type="success" plain @click="handleReview">Trình duyệt</el-button>
        <!-- FOR TESTTTTTTTTTTTTTTTTTTTTTTTTT -->
        <el-button type="success" plain @click="handleAcceptList">Phê duyệt</el-button>
      </div>
      <div v-show="isAdmin" class="col-12 text-center">
        <el-button type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
        <el-button type="success" plain @click="handleAcceptList">Phê duyệt</el-button>
        <el-button type="info" plain @click="handleExport">Export</el-button>
      </div>
    </div>
    <!-- <div class="filter-container mt-5 row">
      <div class="col-8" />
      <div class="col-4">
        <button class="btn btn-sm btn-outline-primary float-end">Export</button>
        <button class="btn btn-sm btn-outline-warning float-end me-3" @click="handleImport">Import</button>
      </div>
    </div> -->

    <div class="row mt-5">
      <el-table
        :key="tableKey"
        v-loading="listLoading"
        :data="list"
        :header-cell-style="{ background: '#F2F2F3', color: '#000000' }"
        border
        fit
        highlight-current-row
        style="width: 100%;"
      >
        <el-table-column
          v-if="!isAdmin"
          width="100px"
          label="Thao tác"
        >
          <template slot="header">
            <input
              ref="checkAll"
              v-model="isCheckAll"
              type="checkbox"
              class="me-2"
              @change="selectAllEconomicSBVNeedApprove"
            >
            <label> Thao tác </label>
          </template>
          <template slot-scope="{row, $index}">
            <!-- <el-checkbox v-if="checkBoxRow(row)" v-model="list[$index].checked" /> -->
            <input v-show="checkRow(row) === 1" :ref="'checkbox'+row.id" class="me-2" type="checkbox" @change="selectEconomicSBV(row)">
            <span title="Chỉnh sửa" class="me-2" @click="handleUpdate(row, $index)"><svg-icon icon-class="edit" style="color: #083863;" /></span>
            <span title="Xóa" @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A;margin-right:7px" /></span>
            <!-- forr TESTTTTTTTTTTTTTTTTTTTTTTTTT -->
            <span v-show="checkRow(row) === 3" title="Phê duyệt" class="me-2" @click="handleAccept(row, $index)"><i class="el-icon-document-checked" style="color: #0FA958" /></span>
            <span v-show="checkRow(row) === 3" title="Từ chối" @click="handleReject(row, $index)"><i class="el-icon-document-delete" style="color: #FF485A;" /></span>
            <span v-show="checkRow(row) === 6" title="Thay đổi trạng thái" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-close" style="color: #FF485A;" /></span>
            <span v-show="checkRow(row) === 5" title="Thay đổi trạng thái" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-check" style="color: #0FA958;" /></span>

          </template>

        </el-table-column>
        <el-table-column
          v-if="isAdmin"
          width="100px"
          label="Thao tác"
        >
          <template slot="header">
            <input
              ref="checkAll"
              v-model="isCheckAll"
              type="checkbox"
              class="me-2"
              @change="selectAllEconomicSBVNeedApprove"
            >
            <label> Thao tác </label>
          </template>
          <template slot-scope="{row, $index}">
            <input v-show="checkRow(row) === 3" :ref="'checkbox'+row.id" class="me-2" type="checkbox" @change="selectEconomicSBV(row)">
            <span v-show="checkRow(row) === 3" class="me-2" @click="handleAccept(row, $index)"><i class="el-icon-document-checked" style="color: #0FA958;font-size: 20px;" /></span>
            <span v-show="checkRow(row) === 3" @click="handleReject(row, $index)"><i class="el-icon-document-delete" style="color: #FF485A;font-size: 20px;" /></span>
            <span v-show="checkRow(row) === 6" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-close" style="color: #FF485A;font-size: 20px;" /></span>
            <span v-show="checkRow(row) === 5" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-check" style="color: #0FA958;font-size: 20px;" /></span>
          </template>

        </el-table-column>

        <el-table-column label="Mã ngành kinh tế SBV" align="center">
          <template slot-scope="{row}">
            {{ row.codeSBV }}
          </template>
        </el-table-column>
        <el-table-column label="Tên ngành kinh tế SBV" width="330px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.nameSBV }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Yêu cầu điều chỉnh" width="330px" align="center">
          <template slot-scope="{row}">
            {{ row.requestChange }}
          </template>
        </el-table-column>
        <el-table-column label="Trạng thái" width="200px" align="center">
          <template slot-scope="{row}">
            {{ statusFilter ( row.status ) }}
          </template>
        </el-table-column>
      <!-- <el-table-column label="Hiển thị mặc định (HO)" width="150" align="center">
        <template slot-scope="{row}">
          <el-checkbox v-model="row.showDefault" />
        </template>
      </el-table-column> -->
      </el-table>

      <DeleteEconomicSBV
        :current="currentObj"
        :dialog-delete-visible.sync="dialogDeleteVisible"
        :list="list"
        :title="title"
        :index="indexRow"
        :list-selected="economicSBVSelected"
      />
      <AddEconomicSBV
        :current="currentObj"
        :dialog-form-visible.sync="dialogFormVisible"
        :list="list"
        :title="title"
        :status="status"
        :dialog-status="dialogStatus"
      />
      <ImportEconomicSBV
        :dialog-import-visible.sync="dialogImportVisible"
      />
      <ReviewEconomicSBV
        :dialog-review-visible.sync="dialogReviewVisible"
        :title="title"
        :list="economicSBVSelected"
      />
    </div>

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
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import baseCommon from '@/utils/base-common'
import DeleteEconomicSBV from '@/views/parameter-management/group-management/economic-sbv/delete-economic-sbv' // secondary package based on el-pagination
import AddEconomicSBV from '@/views/parameter-management/group-management/economic-sbv/add-economic-sbv' // secondary package based on el-pagination
import ImportEconomicSBV from '@/views/parameter-management/group-management/economic-sbv/import-economic-sbv' // secondary package based on el-pagination
import ReviewEconomicSBV from '@/views/parameter-management/group-management/economic-sbv/review-economic-sbv' // secondary package based on el-pagination
import { getAllPage, convertCur } from '@/api/economic-sbv'

export default {
  name: 'EconomicSBVList',
  components: { DeleteEconomicSBV, AddEconomicSBV, ImportEconomicSBV, ReviewEconomicSBV },
  directives: { waves },
  mixins: [baseCommon],
  data() {
    return {
      tableKey: 0,
      list: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        sort: '+id'
      },
      status: null,
      isAdmin: false,
      dialogFormVisible: false,
      dialogStatus: '',
      dialogFormUpdateVisible: false,
      dialogImportVisible: false,
      dialogDeleteVisible: false,
      dialogReviewVisible: false,
      searchObj: {
        codeSBV: null,
        nameSBV: null,
        status: 0,
        pageSize: 10,
        pageIndex: 0
      },
      isCheckAll: false,
      title: null,
      currentObj: {
        id: null,
        codeSBV: null,
        nameSBV: null,
        status: null,
        requestChange: null
      },
      indexRow: null,
      downloadLoading: false,
      economicSBVSelected: []

    }
  },
  created() {
    this.getStatus()
    this.getList()
  },
  methods: {
    selectEconomicSBV(element) {
      if (this.$refs[`checkbox${element.id}`].checked) {
        // console.log(element)
        const row = {
          id: element.id,
          codeSBV: element.codeSBV,
          nameSBV: element.nameSBV,
          status: element.status,
          requestChange: element.requestChange
        }
        this.economicSBVSelected.push(row)
        if (this.isSelectAllEconomicSBVNeedApprove()) {
          this.isCheckAll = true
        }
      } else {
        const indexRemove = this.economicSBVSelected.findIndex(economicSBV => economicSBV.id === element.id)
        this.economicSBVSelected.splice(indexRemove, 1)
        this.isCheckAll = false
      }
    },
    isSelectAllEconomicSBVNeedApprove() {
      if (!this.isAdmin) {
        const listEconomicSBVNeedApprove = this.list.filter(element => element.status === 1 || element.status === 2)
        let count = 0
        listEconomicSBVNeedApprove.map(element => {
          if (this.$refs[`checkbox${element.id}`].checked) {
            count++
          }
        })
        return listEconomicSBVNeedApprove.length === count
      } else {
        const listEconomicSBVNeedApprove = this.list.filter(element => element.status === 3)
        let count = 0
        listEconomicSBVNeedApprove.map(element => {
          if (this.$refs[`checkbox${element.id}`].checked) {
            count++
          }
        })
        return listEconomicSBVNeedApprove.length === count
      }
    },
    selectAllEconomicSBVNeedApprove() {
      if (!this.isCheckAll) {
        this.economicSBVSelected = []
      }
      if (!this.isAdmin) {
        this.list.filter(element => element.status === 1 || element.status === 2).map(element => {
          this.$refs[`checkbox${element.id}`].checked = this.isCheckAll
          if (this.isCheckAll) {
            const row = {
              id: element.id,
              codeSBV: element.codeSBV,
              nameSBV: element.nameSBV,
              status: element.status,
              requestChange: element.requestChange
            }
            this.economicSBVSelected.push(row)
          }
        })
      } else {
        this.list.filter(element => element.status === 3).map(element => {
          this.$refs[`checkbox${element.id}`].checked = this.isCheckAll
          if (this.isCheckAll) {
            const row = {
              id: element.id,
              codeSBV: element.codeSBV,
              nameSBV: element.nameSBV,
              status: element.status,
              requestChange: element.requestChange
            }
            this.economicSBVSelected.push(row)
          }
        })
      }

      console.log(this.economicSBVSelected)
    },

    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getList()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getList()
    },
    statusFilter(status) {
      const statusMap = {
        1: 'Tạo mới',
        2: 'Chỉnh sửa',
        3: 'Chờ duyệt',
        4: 'Từ chối',
        5: 'Không hoạt động',
        6: 'Hoạt động'
      }
      return statusMap[status]
    },
    renderingHeader(h, { column }) {
      return h('div', { 'class': 'phase-header' }, [
        // h('el-checkbox', { 'class': 'phase-header__title me-2 checkAll' }),
        h('el-checkbox', {
          props: {
            class: 'phase-header__title me-2'
          },
          on: {
            '!change': this.renderCheckAll
          }}),
        h('span', column.label)
      ])
    },
    resetSearchObj() {
      this.searchObj = {
        codeSBV: null,
        nameSBV: null,
        status: 0,
        pageSize: 10,
        pageIndex: 0
      }
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.currentObj = {
        id: null,
        codeSBV: null,
        nameSBV: null,
        status: null,
        requestChange: null
      }
      this.title = 'Thêm mới ngành kinh tế SBV'
      this.resetSearchObj()
      this.dialogFormVisible = true
    },
    handleUpdate(row, $index) {
      this.dialogStatus = 'update'
      this.title = 'Sửa ngành kinh tế SBV'

      this.currentObj = convertCur(this.currentObj, row)
      console.log(row)
      // this.resetTemp()
      this.dialogFormVisible = true
    },
    handleAccept(row, $index) {
      // var obj = convertCur(this.currentObj, row)
      // obj.status = 6
      this.currentObj = convertCur(this.currentObj, row)
      this.currentObj.status = 6
      this.title = 'accept'

      this.dialogDeleteVisible = true
    },
    handleReject(row, $index) {
      this.currentObj = convertCur(this.currentObj, row)
      this.currentObj.status = 4

      this.title = 'Từ chối ngành kinh tế SBV'
      this.dialogFormVisible = true
    },
    handleHotChangeActive(row, $index) {
      this.currentObj = convertCur(this.currentObj, row)
      if (this.currentObj.status === 6) {
        this.currentObj.status = 5
      } else if (this.currentObj.status === 5) {
        this.currentObj.status = 6
      }

      this.title = 'hotchange'
      this.dialogDeleteVisible = true
    },
    handleDelete(row, $index) {
      this.indexRow = $index
      // this.temp = Object.assign({}, row) // copy obj
      this.currentObj = convertCur(this.currentObj, row)
      this.title = 'delete'

      this.dialogDeleteVisible = true
    },
    handleImport() {
      this.dialogImportVisible = true
    },
    handleExport() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['STT', 'Mã ngành kinh tế SBV', 'Tên ngành kinh tế SBV']
        const filterVal = ['index', 'codeSBV', 'nameSBV']

        const data = this.formatJson(filterVal, this.list)
        const filename = this.formatFilenameExport()
        const bookType = 'xlsx'
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: filename,
          autoWidth: 'true',
          bookType: bookType
        })
        this.$notify.success({
          title: 'Success',
          message: `${filename}.${bookType} tải xuống thành công`,
          showClose: false
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v, index) => filterVal.map(j => {
        if (j === 'index') {
          return index + 1
        } else if (j === 'status_import') {
          return this.renderResult(v[j])
        } else {
          return v[j]
        }
      }))
    },
    formatFilenameExport() {
      const currentDate = new Date()
      return `${currentDate.getFullYear()}${currentDate.getMonth() + 1}${currentDate.getDate()}${currentDate.getHours()}${currentDate.getMinutes()}${currentDate.getSeconds()}_nganhKinhTeSBV`
    },
    handleReview() {
      this.title = 'reviewlist'
      this.dialogReviewVisible = true
    },
    handleAcceptList() {
      this.title = 'acceptlist'
      this.dialogReviewVisible = true
    },
    handleSearch() {
      this.getList()
    },
    resetListChecked() {
      this.listChecked = {}
    },
    renderCheckAll() {
      // this.$refs[`checkbox6`].checked = !this.$refs[`checkbox6`].checked
      this.isCheckAll = !this.isCheckAll
      this.list.forEach((element, index) => {
        this.$refs[`checkbox${index}`].checked = this.isCheckAll
      })
    },
    checkRow(row) {
      if (row.status === 1 || row.status === 2) { // them moi, chinh sua
        return 1
      } else if (row.status === 3) { // cho duyet + hoat dong
        return 3
      } else if (row.status === 4) { // tu choi
        return 4
      } else if (row.status === 5) { // khong hoat dong
        return 5
      } else if (row.status === 6) { // khong hoat dong
        return 6
      }
    },
    getStatus() {
      this.status = [
        {
          id: 0,
          name: 'Tất cả'
        },
        {
          id: 1,
          name: 'Tạo mới'
        },
        {
          id: 2,
          name: 'Chỉnh sửa'
        },
        {
          id: 3,
          name: 'Chờ duyệt'
        },
        {
          id: 4,
          name: 'Từ chối'
        },
        {
          id: 5,
          name: 'Không hoạt động'
        },
        {
          id: 6,
          name: 'Hoạt động'
        }
      ]
    },
    getList() {
      this.searchObj.pageIndex = this.pagination.pageNumber
      this.searchObj.pageSize = this.pagination.pageSize
      this.listLoading = true
      getAllPage(this.searchObj).then(response => {
        this.list = response.data.result
        // this.total = response.data.totalItems
        this.pagination = {
          pageNumber: response.data.pageNumber,
          pageSize: response.data.pageSize,
          totalItems: response.data.totalItems,
          totalPages: response.data.totalPages
        }
      })
      setTimeout(() => {
        this.listLoading = false
      }, 1 * 1000)
    }
  }
}
</script>
<style>
.non-border {
  border: 0;
  padding: 0;
}
.float-right {
  float: right;
}
</style>
