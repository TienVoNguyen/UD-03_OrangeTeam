<template>
  <div class="app-container">

    <div class="row mt-3">
      <div class="btn head-btn col-6" :class="(searchObj.type===1)?'head-btn-access':'head-btn'" @click="activeRankUp">Quy tắc điều chỉnh tăng hạng</div>

      <div class="btn head-btn  col-6" :class="(searchObj.type===2)?'head-btn-access':'head-btn'" @click="activeRankDown">Quy tắc điều chỉnh giảm hạng</div>
    </div>

    <div class="row mt-3">
      <div class="col-1" />
      <div class="col-3">
        <label v-if="searchObj.type===1" for="content">Lý do tăng hạng</label>
        <label v-if="searchObj.type===2" for="content">Lý do giảm hạng</label>
        <el-input id="content" v-model="searchObj.content" value="" class="form-control non-border mt-2" :placeholder="(searchObj.type===1)?'Nhập lý do tăng hạng':'Nhập lý do giảm hạng'" />

      </div>
      <div class="col-2" />
      <div class="col-3">
        <label for="status">Trạng thái</label>
        <el-select id="status" v-model="searchObj.status" value="" filterable clearable class="form-control non-border mt-2">
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
      </div>
      <div v-show="isAdmin" class="col-12 text-center">
        <el-button type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
        <!-- <el-button type="success" plain @click="handleAcceptList">Phê duyệt</el-button> -->
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
              @change="selectAllRuleChangeRankNeedApprove"
            >
            <label> Thao tác </label>
          </template>
          <template slot-scope="{row, $index}">
            <!-- <el-checkbox v-if="checkBoxRow(row)" v-model="list[$index].checked" /> -->
            <span v-if="row.status === 2 || row.status === 5 || row.status === 6 || row.status === 7" title="Xem chi tiết" class="me-2" @click="handleViewDetail(row, $index)"><svg-icon icon-class="eye-open" style="color: #A045CB;" /></span>

            <input v-show="row.status === 1 || row.status === 4" :ref="'checkbox'+row.id" class="me-2" type="checkbox" @change="selectRuleChangeRank(row)">
            <span v-if="row.status === 1 || row.status === 4 || row.status === 3" class="me-2" @click="handleUpdate(row, $index)"><svg-icon icon-class="edit" style="color: #083863" /></span>
            <span v-if="row.status === 1 || row.status === 4 || row.status === 3" @click="handleDelete(row, $index)"><i class="el-icon-delete" style="color: #FF485A" /></span>

            <!-- for TESTTTTTTTTTTTTTTTTTTTTTTTTT -->

            <span v-if="row.status === 2" title="Trình duyệt" class="me-2" @click="handleApprove(row, $index)"><i class="el-icon-document-checked" style="color: #0FA958;" /></span>
            <span v-show="row.status === 6" title="Thay đổi trạng thái" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-close" style="color: #FF485A;" /></span>
            <span v-show="row.status === 5 ||row.status === 7" title="Thay đổi trạng thái" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-check" style="color: #0FA958;" /></span>

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
              @change="selectAllRuleChangeRankNeedApprove"
            >
            <label> Thao tác </label>
          </template>
          <template slot-scope="{row, $index}">
            <!-- <input v-show="checkRow(row) === 2" :ref="'checkbox'+row.id" class="me-2" type="checkbox" @change="selectRuleChangeRank(row)"> -->
            <span v-if="row.status !== 2" class="me-2" @click="handleViewDetail(row, $index)"><svg-icon icon-class="eye-open" style="color: #A045CB; font-size: 20px" /></span>

            <span v-if="row.status === 2" class="me-2" @click="handleApprove(row, $index)"><i class="el-icon-document-checked" style="color: #0FA958;font-size: 20px;" /></span>
            <span v-show="row.status === 6" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-close" style="color: #FF485A;font-size: 20px;" /></span>
            <span v-show="row.status === 5 ||row.status === 7" @click="handleHotChangeActive(row, $index)"><i class="el-icon-circle-check" style="color: #0FA958;font-size: 20px;" /></span>
          </template>

        </el-table-column>

        <el-table-column :label="(searchObj.type===1)?'Lý do tăng hạng':'Lý do giảm hạng'" align="center">
          <template slot-scope="{row}">
            {{ row.content }}
          </template>
        </el-table-column>
        <el-table-column label="Trạng thái" width="300px" align="center">
          <template slot-scope="{row}">
            {{ statusFilter ( row.status ) }}
          </template>
        </el-table-column>
      </el-table>

      <DeleteRuleChangeRank
        :current="currentObj"
        :dialog-delete-visible.sync="dialogDeleteVisible"
        :list="list"
        :title="title"
        :index="indexRow"
        :list-selected="ruleChangeRankSelected"
      />
      <ModifyRuleChangeRank
        :current="currentObj"
        :dialog-form-visible.sync="dialogFormVisible"
        :list="list"
        :title="title"
        :flag="flagReject"
        :status="status"
        :dialog-status="dialogStatus"
      />
      <ImportRuleChangeRank
        :dialog-import-visible.sync="dialogImportVisible"
        :type="searchObj.type"
      />
      <ReviewRuleChangeRank
        :dialog-review-visible.sync="dialogReviewVisible"
        :title="title"
        :type="searchObj.type"
        :list="ruleChangeRankSelected"
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
import DeleteRuleChangeRank from '@/views/parameter-management/rule-change-rank/components/delete-rule.vue' // secondary package based on el-pagination
import ModifyRuleChangeRank from '@/views/parameter-management/rule-change-rank/components/modify-rule.vue' // secondary package based on el-pagination
import ImportRuleChangeRank from '@/views/parameter-management/rule-change-rank/components/import-rule.vue' // secondary package based on el-pagination
import ReviewRuleChangeRank from '@/views/parameter-management/rule-change-rank/components/review-rule.vue' // secondary package based on el-pagination
import { getAllPage } from '@/api/rule-change-rank'

export default {
  name: 'RuleChangeRankList',
  components: { DeleteRuleChangeRank, ModifyRuleChangeRank, ImportRuleChangeRank, ReviewRuleChangeRank },
  directives: { waves },
  mixins: [baseCommon],
  data() {
    return {
      tableKey: 0,
      list: null,
      listLoading: true,
      status: null,
      isAdmin: false,
      dialogFormVisible: false,
      dialogStatus: '',
      dialogFormUpdateVisible: false,
      dialogImportVisible: false,
      dialogDeleteVisible: false,
      dialogReviewVisible: false,
      searchObj: {
        type: 1,
        content: null,
        status: 0,
        pageSize: 10,
        pageIndex: 0
      },
      isCheckAll: false,
      title: null,
      flagReject: false,
      currentObj: {
        id: null,
        content: null,
        status: null,
        type: null,
        createdBy: null,
        requestChange: null
      },
      indexRow: null,
      downloadLoading: false,
      ruleChangeRankSelected: []
    }
  },
  created() {
    this.getStatus()
    this.getList()
  },
  methods: {
    selectRuleChangeRank(element) {
      if (this.$refs[`checkbox${element.id}`].checked) {
        // console.log(element)
        const row = {
          id: element.id,
          content: element.content,
          status: element.status,
          type: element.type,
          requestChange: element.requestChange,
          createdBy: element.createdBy
        }
        this.ruleChangeRankSelected.push(row)
        if (this.isSelectAllRuleChangeRankNeedApprove()) {
          this.isCheckAll = true
        }
      } else {
        const indexRemove = this.ruleChangeRankSelected.findIndex(ruleChangeRank => ruleChangeRank.id === element.id)
        this.ruleChangeRankSelected.splice(indexRemove, 1)
        this.isCheckAll = false
      }
    },
    isSelectAllRuleChangeRankNeedApprove() {
      if (!this.isAdmin) {
        const listRuleChangeRankNeedApprove = this.list.filter(element => element.status === 1 || element.status === 2)
        let count = 0
        listRuleChangeRankNeedApprove.map(element => {
          if (this.$refs[`checkbox${element.id}`].checked) {
            count++
          }
        })
        return listRuleChangeRankNeedApprove.length === count
      } else {
        const listRuleChangeRankNeedApprove = this.list.filter(element => element.status === 3)
        let count = 0
        listRuleChangeRankNeedApprove.map(element => {
          if (this.$refs[`checkbox${element.id}`].checked) {
            count++
          }
        })
        return listRuleChangeRankNeedApprove.length === count
      }
    },
    selectAllRuleChangeRankNeedApprove() {
      if (!this.isCheckAll) {
        this.ruleChangeRankSelected = []
      }
      if (!this.isAdmin) {
        this.list.filter(element => element.status === 1 || element.status === 2).map(element => {
          this.$refs[`checkbox${element.id}`].checked = this.isCheckAll
          if (this.isCheckAll) {
            const row = {
              id: element.id,
              content: element.content,
              status: element.status,
              type: element.type,
              requestChange: element.requestChange,
              createdBy: element.createdBy
            }
            this.ruleChangeRankSelected.push(row)
          }
        })
      } else {
        this.list.filter(element => element.status === 3).map(element => {
          this.$refs[`checkbox${element.id}`].checked = this.isCheckAll
          if (this.isCheckAll) {
            const row = {
              id: element.id,
              content: element.content,
              status: element.status,
              type: element.type,
              requestChange: element.requestChange,
              createdBy: element.createdBy
            }
            this.ruleChangeRankSelected.push(row)
          }
        })
      }
    },

    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      if (this.searchObj.type === 1) {
        this.activeRankUp()
      } else if (this.searchObj.type === 2) {
        this.activeRankDown()
      }
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      if (this.searchObj.type === 1) {
        this.activeRankUp()
      } else if (this.searchObj.type === 2) {
        this.activeRankDown()
      }
    },
    statusFilter(status) {
      const statusMap = {
        1: 'Tạo mới',
        2: 'Chờ duyệt',
        3: 'Từ chối',
        4: 'Chỉnh sửa',
        5: 'Phê duyệt',
        6: 'Hoạt động',
        7: 'Không hoạt động'
      }
      return statusMap[status]
    },
    resetSearchObj() {
      this.searchObj = {
        content: null,
        type: this.type,
        status: 0,
        pageSize: 10,
        pageIndex: 0
      }
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.currentObj = {
        id: null,
        content: null,
        status: null,
        type: null,
        createdBy: null,
        requestChange: null
      }
      if (this.searchObj.type === 1) {
        this.title = 'rankUp'
        this.currentObj.type = 1
      } else {
        this.title = 'rankDown'
        this.currentObj.type = 2
      }
      this.dialogFormVisible = true
    },
    handleUpdate(row, $index) {
      this.flagReject = false
      this.dialogStatus = 'update'
      this.currentObj = row
      if (this.searchObj.type === 1) {
        this.title = 'rankUp'
        this.currentObj.type = 1
      } else {
        this.title = 'rankDown'
        this.currentObj.type = 2
      }
      if (this.currentObj.status === 3) {
        this.flagReject = true
      } else {
        this.flagReject = false
      }
      // this.resetTemp()
      this.dialogFormVisible = true
    },
    handleViewDetail(row, $index) {
      this.flagReject = false
      this.dialogStatus = 'view'
      this.currentObj = row
      if (this.searchObj.type === 1) {
        this.title = 'rankUp'
        this.currentObj.type = 1
      } else {
        this.title = 'rankDown'
        this.currentObj.type = 2
      }
      if (this.currentObj.status === 3) {
        this.flagReject = true
      } else {
        this.flagReject = false
      }
      // this.resetTemp()
      this.dialogFormVisible = true
    },
    handleApprove(row, $index) {
      this.flagReject = false
      this.dialogStatus = 'approve'
      this.currentObj = row
      if (this.searchObj.type === 1) {
        this.title = 'rankUp'
        this.currentObj.type = 1
      } else {
        this.title = 'rankDown'
        this.currentObj.type = 2
      }
      // this.resetTemp()
      this.dialogFormVisible = true
    },
    handleHotChangeActive(row, $index) {
      if (this.searchObj.type === 1) {
        this.currentObj.type = 1
      } else {
        this.currentObj.type = 2
      }
      this.currentObj = row

      this.title = 'hotchange'
      this.dialogDeleteVisible = true
    },
    handleDelete(row, $index) {
      this.indexRow = $index
      this.currentObj = row
      // this.temp = Object.assign({}, row) // copy obj
      if (this.searchObj.type === 1) {
        this.currentObj.type = 1
      } else {
        this.currentObj.type = 2
      }
      this.title = 'delete'

      this.dialogDeleteVisible = true
    },
    handleImport() {
      this.dialogImportVisible = true
    },
    handleExport() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        var headerType = ''
        if (this.searchObj.type === 1) {
          headerType = 'Lý do tăng hạng'
        } else if (this.searchObj.type === 2) {
          headerType = 'Lý do giảm hạng'
        }
        const tHeader = ['STT', headerType]
        const filterVal = ['index', 'content']

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
          name: 'Chờ duyệt'
        },
        {
          id: 3,
          name: 'Từ chối'
        },
        {
          id: 4,
          name: 'Chỉnh sửa'
        },
        {
          id: 5,
          name: 'Phê duyệt'
        },
        {
          id: 6,
          name: 'Hoạt động'
        },
        {
          id: 7,
          name: 'Không hoạt động'
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
    },
    activeRankDown() {
      this.searchObj.type = 2
      this.getList()
    },
    activeRankUp() {
      this.searchObj.type = 1
      this.getList()
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
.head-btn {
  background: #F5F5F5;
  border: 0.1px solid #9A10DB;
  color: #9A10DB;
}
.head-btn-access {
  transition-duration: 0.4s;
  background: #9A10DB !important;
  color: white !important;
}
</style>
