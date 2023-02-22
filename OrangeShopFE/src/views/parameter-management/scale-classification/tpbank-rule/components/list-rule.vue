<template>
  <div class="app-container">
    <div class="filter">
      <el-form ref="filterForm" label-position="left" class="mt-3">
        <div class="row">
          <div class="col-3">
            <el-form-item>
              <label for="name">Tên quy tắc</label>
              <el-input
                id="name"
                ref="name"
                v-model="filter.name"
                class="form-control non-border"
                placeholder="Nhập tên quy tắc"
              />
            </el-form-item>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <el-form-item>
              <label>Ngày tạo</label>
              <div>
                <el-date-picker
                  v-model="filter.createdFrom"
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
                  v-model="filter.createdTo"
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
      <div class="row mt-3 mb-5 me-5">
        <div class="col-12 text-center">
          <el-button class="me-5" type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
          <router-link to="/parameter-management/scale/tpbank-rule/add">
            <el-button type="warning" plain>Thêm mới</el-button>
          </router-link>
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
            <div class="row">
              <div class="col-2" />
              <div class="col-2">
                <span class="me-2">
                  <router-link v-if="row.status === 1 || row.status === 3 || row.status === 4" :to="{name: 'EditRule', params: {id: row.id}}">
                    <svg-icon icon-class="edit" style="color: #083863" />
                  </router-link>
                  <router-link v-if="row.status === 2 || row.status === 5 || row.status === 6" :to="{name: 'ViewRule', params: {id: row.id, disabled: true}}">
                    <svg-icon icon-class="eye-open" style="color: #A045CB" />
                  </router-link>
                </span>
              </div>
              <div class="col-2">
                <span>
                  <i v-if="row.status === 1 || row.status === 3 || row.status === 4" class="el-icon-delete" style="color: #FF485A" @click="handleDelete(row)" />
                  <router-link v-if="row.status === 5 || row.status === 6" :to="{name: 'AddRule', params: {isCopy: true, id: row.id}}">
                    <i class="el-icon-document-copy" style="color: #0FA958" />
                  </router-link>
                </span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="Tên quy tắc" header-align="center">
          <template slot-scope="{row}">
            {{ row.name }}
          </template>
        </el-table-column>
        <el-table-column label="Ngày tạo" width="200px" header-align="center">
          <template slot-scope="{row}">
            {{ convertDate(row.createdAt) }}
          </template>
        </el-table-column>
        <el-table-column label="Ngày duyệt" width="200px" header-align="center">
          <template slot-scope="{row}">
            {{ convertDate(row.approvedAt) }}
          </template>
        </el-table-column>
        <el-table-column label="Trạng thái" width="200px" header-align="center">
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
      <delete-rule
        :dialog-delete-visible.sync="dialogDeleteVisible"
        :temp="temp"
        :list.sync="list"
        :pagination.sync="pagination"
      />
      <change-status-rule
        :dialog-change-status-visible.sync="dialogChangeStatusVisible"
        :temp="temp"
        :list.sync="list"
      />
    </div>
  </div>
</template>

<script>

import waves from '@/directive/waves'
import baseCommon from '@/utils/base-common'
import DeleteRule from '@/views/parameter-management/scale-classification/tpbank-rule/components/delete-rule'
import { getPageTPBankRule, getTPBankRule } from '@/api/tpbank-rule'
import ChangeStatusRule from '@/views/parameter-management/scale-classification/tpbank-rule/components/change-status-rule'

export default {
  name: 'ListRule',
  components: { DeleteRule, ChangeStatusRule },
  directives: { waves },
  mixins: [baseCommon],
  data() {
    return {
      list: null,
      dialogDeleteVisible: false,
      dialogChangeStatusVisible: false,
      filter: {
        name: null,
        createdFrom: null,
        createdTo: null,
        status: null
      },
      temp: {
        name: '',
        unit: 'Triệu đồng',
        sales: null,
        sales_criteria: null,
        equity: null,
        equity_criteria: null,
        configScalePointList: []
      }
    }
  },
  created() {
    this.getListRule()
  },
  methods: {
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
    },
    getListRule() {
      this.showLoading()
      const params = {
        name: this.filter?.name,
        createdFrom: this.filter?.createdFrom,
        createdTo: this.filter?.createdTo,
        status: this.filter?.status,
        isDeleted: 0,
        pageIndex: this.pagination.pageNumber,
        pageSize: this.pagination.pageSize
      }
      getPageTPBankRule(params).then(response => {
        this.list = response.data.result
        this.pagination = {
          pageNumber: response.data.pageNumber,
          pageSize: response.data.pageSize,
          totalItems: response.data.totalItems,
          totalPages: response.data.totalPages
        }
        this.notifyInfo('Thông tin', response.message)
        this.hideLoading()
      }).catch(() => {
        this.hideLoading()
      })
    },
    handleSearch() {
      this.getListRule()
    },
    handleDelete(row) {
      getTPBankRule(row.id, {}).then(response => {
        const data = response.data
        this.temp = data
        this.dialogDeleteVisible = true
      })
    },
    handleChangeStatus(row) {
      getTPBankRule(row.id, {}).then(response => {
        const data = response.data
        this.temp = data
        this.dialogChangeStatusVisible = true
      })
    },
    convertDate(date) {
      return this.convertDateToLocal(date, 'DD/MM/YYYY')
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
          str = 'Hoạt động'
          break
        }
        case 6: {
          str = 'Không hoạt động'
          break
        }
      }
      return str
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
