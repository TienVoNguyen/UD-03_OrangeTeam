<template>
  <div class="app-container">
    <div class="filter">
      <el-form ref="filterForm" label-position="left" class="mt-3">
        <div class="row">
          <div class="col-3">
            <el-form-item>
              <label for="nameMatrix">Tên ma trận</label>
              <el-input
                id="nameMatrix"
                ref="nameMatrix"
                v-model="filter.nameMatrix"
                class="form-control non-border"
                placeholder="Nhập tên ma trận"
              />
            </el-form-item>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <el-form-item>
              <label>Ngày tạo</label>
              <div>
                <el-date-picker
                  v-model="filter.createTimeFrom"
                  style="width: 40%"
                  placeholder="Từ ngày"
                  type="date"
                  value-format="yyyy-MM-dd"
                  format="dd/MM/yyyy"
                />
                <i
                  class="el-icon-minus font-weight-bold"
                  style="padding: 1% 2% 0 2%"
                />
                <el-date-picker
                  v-model="filter.createTimeTo"
                  style="width: 40%"
                  placeholder="Đến ngày"
                  type="date"
                  format="dd/MM/yyyy"
                  value-format="yyyy-MM-dd"
                />
              </div>
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
                <el-option label="Hoạt động" value="5" />
                <el-option label="Không hoạt động" value="6" />
              </el-select>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <div class="row mt-3 mb-5">
        <div class="col-12 text-center">
          <el-button type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
          <el-button type="warning" plain @click="$router.push('/parameter-management/scale/matrix-classify/add')">Thêm mới</el-button>
          <!-- <router-link class="btn btn-outline-warning me-5" to="/parameter-management/scale/matrix-classify/add">Thêm mới</router-link> -->
        </div>
      </div>
    </div>
    <div class="content">
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
            <div v-if="!isAdmin" class="row">
              <div v-if="row.status === 1 || row.status === 3 || row.status === 4" class="col-2">
                <span class="me-2">
                  <router-link :to="{name: 'EditMatrix', params: {id: row.id} }">
                    <svg-icon icon-class="edit" style="color: #083863" />
                  </router-link>
                </span>
              </div>
              <div v-if="row.status === 2 || row.status === 5 || row.status === 6 || row.status === 7" class="col-2">
                <span class="me-2">
                  <!-- <svg-icon v-if="row.status === 2 || row.status === 5 || row.status === 6 || row.status === 7" icon-class="eye-open" style="color: #A045CB" /> -->
                  <router-link :to="{name: 'ViewDetailMatrix', params: {id: row.id} }">
                    <svg-icon icon-class="eye-open" style="color: #A045CB" />
                  </router-link>
                </span>
              </div>
              <div v-if="row.status === 1 || row.status === 4 " class="col-1">
                <span @click="handleApprove(row)"><i class="el-icon-document-checked" style="color: #0FA958;" /></span>
              </div>
              <div class="col-2">
                <span>
                  <i v-if="row.status === 1 || row.status === 3 || row.status === 4" class="el-icon-delete" style="color: #FF485A" @click="handleDelete(row)" />
                  <!-- <i v-if="row.status === 5 || row.status === 6 || row.status === 7" class="el-icon-document-copy" style="color: #0FA958" @click="handleCopy(row)" /> -->
                  <router-link v-if="row.status === 5 || row.status === 6 || row.status === 7" :to="{name: 'AddMatrix', query: row }">
                    <span class="el-icon-document-copy" style="color: #0FA958" />
                  </router-link>
                </span>
              </div>
            </div>
            <div v-if="isAdmin" class="row">
              <div v-if=" row.status !== 2" class="col-2">
                <span class="me-2">
                  <router-link :to="{name: 'ViewDetailMatrix', params: {id: row.id} }">
                    <svg-icon icon-class="eye-open" style="color: #A045CB; font-size: 20px" />
                  </router-link>
                </span>
              </div>

              <div v-if="row.status === 2" class="col-2">
                <router-link :to="{name: 'ApproveMatrix', params: {id: row.id} }">
                  <span class="el-icon-document-checked" style="color: #699BF7; font-size: 20px" />
                </router-link>
              </div>

              <div class="col-1">
                <span v-if="row.status === 6" @click="handleHotChangeActive(row)"><i class="el-icon-circle-close" style="color: #FF485A;;font-size: 20px;" /></span>
                <span v-if="row.status === 5 || row.status === 7" @click="handleHotChangeActive(row)"><i class="el-icon-circle-check" style="color: #0FA958;;font-size: 20px;" /></span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="Tên quy tắc" header-align="center">
          <template slot-scope="{row}">
            {{ row.nameMatrix }}
          </template>
        </el-table-column>
        <el-table-column label="Bộ quy tắc" header-align="center">
          <template slot-scope="{row}">
            {{ renderRules(row.rules) }}
          </template>
        </el-table-column>
        <el-table-column label="Ngày tạo" width="200px" header-align="center" align="center">
          <template slot-scope="{row}">
            {{ convertDateToLocal(row.createTime, 'DD/MM/YYYY') }}
          </template>
        </el-table-column>
        <el-table-column label="Ngày duyệt" width="200px" header-align="center" align="center">
          <template slot-scope="{row}">
            {{ convertDateToLocal(row.approveTime, 'DD/MM/YYYY') }}
          </template>
        </el-table-column>
        <el-table-column label="Trạng thái" width="200px" header-align="center" align="center">
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
      <delete-matrix
        :dialog-delete-visible.sync="dialogDeleteVisible"
        :temp="temp"
        :title="title"
        :list.sync="list"
        :pagination.sync="pagination"
      />
    </div>
  </div>
</template>

<script>

import waves from '@/directive/waves'
import baseCommon from '@/utils/base-common'
import DeleteMatrix from '@/views/parameter-management/scale-classification/matrix-classify/components/delete-matrix'
import { getAllPage } from '@/api/matrix-classify'
export default {
  name: 'ListMatrix',
  components: { DeleteMatrix },
  directives: { waves },
  mixins: [baseCommon],
  data() {
    return {
      list: null,
      dialogDeleteVisible: false,
      filter: {
        nameMatrix: null,
        createTimeFrom: null,
        createTimeTo: null,
        status: null,
        pageSize: 10,
        pageIndex: 0
      },
      temp: {},
      title: null,
      isAdmin: false
    }
  },
  created() {
    this.getListMatrix()
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
      this.getListMatrix()
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
    renderRules(rule) {
      if (!rule) {
        return ''
      }
      let str = ''
      switch (rule) {
        case 1: {
          str = 'Quy tắc TPBank'
          break
        }
        case 2: {
          str = 'Quy tắc theo điểm chỉ tiêu'
          break
        }
        case 3: {
          str = 'Quy tắc TPBank, Quy tắc theo điểm chỉ tiêu'
          break
        }
      }
      return str
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
          str = 'Đã phê duyệt'
          break
        }
        case 6: {
          str = 'Hoạt động'
          break
        }
        case 7: {
          str = 'Không hoạt động'
          break
        }
      }
      return str
    },
    getListMatrix() {
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
</style>
