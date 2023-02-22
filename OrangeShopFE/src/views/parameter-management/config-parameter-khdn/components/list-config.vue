<template>
  <div class="app-container">

    <el-form ref="filterForm" label-position="left" class="mt-3">
      <div class="row">
        <div class="col-3">
          <el-form-item>
            <label for="nameMatrix">Mã cấu hình tham số</label>
            <el-input
              id="codeConfig"
              ref="codeConfig"
              v-model="filter.codeConfig"
              class="form-control non-border"
              placeholder="Nhập mã cấu hình tham số"
            />
          </el-form-item>
        </div>
        <div class="col-1" />
        <div class="col-3">
          <el-form-item>
            <label for="nameMatrix">Tên cấu hình tham số</label>
            <el-input
              id="nameConfig"
              ref="nameConfig"
              v-model="filter.nameConfig"
              class="form-control non-border"
              placeholder="Nhập tên cấu hình tham số"
            />
          </el-form-item>
        </div>
        <div class="col-1" />
        <div class="col-3">
          <el-form-item>
            <label for="status">Trạng thái</label>
            <el-select id="status" v-model="filter.status" class="form-control non-border" placeholder="---Tất cả---">
              <el-option label="Tất cả" value="" />
              <el-option label="Chưa tạo thuộc tính" value="1" />\
              <el-option label="Hoạt động" value="2" />
              <el-option label="Không hoạt động" value="3" />
            </el-select>
          </el-form-item>
        </div>
      </div>
    </el-form>

    <div class="row mt-5">
      <div class="col-12 text-center">
        <el-button type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
      </div>
    </div>

    <div class="row mt-5">
      <el-table
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
          header-align="center"
        >
          <template slot-scope="{row}">
            <div class="row ">
              <div v-if="row.status !== 1" class="col-12 text-center">
                <span class="me-2">
                  <router-link :to="{name: 'EditConfig', params: {id: row.id} }">
                    <svg-icon icon-class="edit" style="color: #083863" />
                  </router-link>
                </span>
              </div>
              <div v-if="row.status === 1" class="col-12 text-center">
                <span class="me-2">
                  <router-link :to="{name: 'AddConfig', params: {id: row.id} }">
                    <i class="el-icon-circle-plus" style="color: #fe0000" />
                  </router-link>
                </span>
              </div>
            </div>

          </template>
        </el-table-column>
        <el-table-column label="Mã cấu hình tham số" header-align="center">
          <template slot-scope="{row}">
            {{ row.codeConfig }}
          </template>
        </el-table-column>
        <el-table-column label="Tên cấu hình tham số" header-align="center">
          <template slot-scope="{row}">
            {{ row.nameConfig }}
          </template>
        </el-table-column>
        <el-table-column label="Trạng thái" width="200px" header-align="center" align="center">
          <template slot-scope="{row}">
            {{ renderStatus(row.status) }}
          </template>
        </el-table-column>
      </el-table>

      <!-- <DeleteConfigParameterKHDN
        :dialog-delete-visible.sync="dialogDeleteVisible"
        :list="list"
        :title="title"
      /> -->
      <ImportConfigParameterKHDN
        :dialog-import-visible.sync="dialogImportVisible"
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
// import DeleteConfigParameterKHDN from '@/views/parameter-management/config-parameter-khdn/components/delete-config.vue' // secondary package based on el-pagination
import ImportConfigParameterKHDN from '@/views/parameter-management/config-parameter-khdn/components/import-property.vue' // secondary package based on el-pagination
import { getAllPage } from '@/api/type-config-parameter'

export default {
  name: 'ListMatrix',
  components: { ImportConfigParameterKHDN },
  directives: { waves },
  mixins: [baseCommon],
  data() {
    return {
      list: null,
      dialogDeleteVisible: false,
      dialogImportVisible: false,
      filter: {
        codeConfig: null,
        nameConfig: null,
        status: null,
        pageSize: 10,
        pageIndex: 0
      },
      temp: {},
      title: null
    }
  },
  created() {
    this.getListTypeConfig()
  },
  methods: {
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getListMatrix()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getListMatrix()
    },
    handleSearch() {
      this.getListTypeConfig()
    },
    handleDelete(row) {
      this.title = 'delete'
      this.dialogDeleteVisible = true
      this.temp = row
    },
    handleApprove(row) {
      this.title = 'approve'
      this.dialogDeleteVisible = true
      this.temp = row
    },
    handleHotChangeActive(row) {
      this.temp = row
      this.title = 'hotchange'
      this.dialogDeleteVisible = true
    },
    renderStatus(status) {
      if (!status) {
        return ''
      }
      let str = ''
      switch (status) {
        case 1: {
          str = 'Chưa tạo thuộc tính'
          break
        }
        case 2: {
          str = 'Hoạt dộng'
          break
        }
        case 3: {
          str = 'Không hoạt động'
          break
        }
      }
      return str
    },
    getListTypeConfig() {
      this.filter.pageIndex = this.pagination.pageNumber
      this.filter.pageSize = this.pagination.pageSize
      this.listLoading = true
      getAllPage(this.filter).then(response => {
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
