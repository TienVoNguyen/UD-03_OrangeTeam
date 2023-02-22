<template>
  <ValidationObserver ref="observer">
    <div class="app-container">
      <div class="filter">
        <el-form ref="filterForm" label-position="left" class="mt-3">
          <div class="row">
            <div class="col-3">
              <el-form-item>
                <label for="nameModel">Tên mô hình đơn</label>
                <el-input
                  id="nameModel"
                  ref="nameModel"
                  v-model="filter.nameModel"
                  class="form-control non-border"
                  placeholder="Nhập tên mô hình"
                />
              </el-form-item>
            </div>
            <div class="col-1" />
            <div class="col-3">
              <el-form-item>
                <label for="typeRank">Loại xếp hạng</label>
                <el-select id="typeRank" v-model="filter.typeRank" class="form-control non-border" placeholder="---Chọn---">
                  <el-option label="Xếp hạng tài chính" value="1" />
                  <el-option label="Xếp hạng phi tài chính" value="2" />
                  <el-option label="Xếp hạng kết hợp" value="3" />
                </el-select>
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
                  <el-option label="Ngừng hoạt động" value="6" />
                </el-select>
              </el-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col-3">
              <el-form-item>
                <label for="scaleClassify">Phân loại quy mô</label>
                <el-select id="scaleClassify" v-model="filter.scaleClassify" placeholder="---Chọn---" filterable clearable class="form-control non-border">
                  <el-option v-for="item in scaleClassifies" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </div>
            <div class="col-1" />
            <div class="col-3">
              <el-form-item>
                <label for="industryGroup">Nhóm ngành</label>
                <el-select id="industryGroup" v-model="filter.industryGroup" placeholder="---Chọn---" filterable clearable class="form-control non-border">
                  <el-option v-for="item in industryGroups" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </div>
            <div class="col-1" />
            <div class="col-3">
              <ValidationProvider
                v-slot="{ errors }"
                name="userCreated"
                :rules="{ max: 100}"
              >
                <el-form-item
                  prop="userCreated"
                  :error="messageError(singleScoringModelKHDNFields.userCreated, errors[0])"
                >
                  <label for="userCreated">Người tạo</label>
                  <el-input
                    id="userCreated"
                    ref="userCreated"
                    v-model="filter.userCreated"
                    class="form-control non-border"
                    placeholder="Nhập"
                  />
                </el-form-item>

              </ValidationProvider>
            </div>

          </div>
          <div class="row">
            <div class="col-3">
              <ValidationProvider
                v-slot="{ errors }"
                name="userApproved"
                :rules="{ max: 100}"
              >
                <el-form-item
                  prop="userApproved"
                  :error="messageError(singleScoringModelKHDNFields.userApproved, errors[0])"
                >
                  <label for="userApproved">Người duyệt</label>
                  <el-input
                    id="userApproved"
                    ref="userApproved"
                    v-model="filter.userApproved"
                    class="form-control non-border"
                    placeholder="Nhập"
                  />
                </el-form-item>
              </ValidationProvider>
            </div>
            <div class="col-1" />
            <div class="col-3">
              <el-form-item>
                <label>Thời gian tạo</label>
                <div>
                  <el-date-picker
                    v-model="filter.createdTimeFrom"
                    style="width: 46%"
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
                    v-model="filter.createdTimeTo"
                    style="width: 46%"
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
                <label>Thời gian duyệt</label>
                <div>
                  <el-date-picker
                    v-model="filter.approvedTimeFrom"
                    style="width: 46%"
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
                    v-model="filter.approvedTimeTo"
                    style="width: 46%"
                    placeholder="Đến ngày"
                    type="date"
                    format="dd/MM/yyyy"
                    value-format="yyyy-MM-dd"
                  />
                </div>
              </el-form-item>
            </div>
          </div>
        </el-form>
        <div class="row mt-3 mb-5">
          <div class="col-12 text-center">
            <el-button type="primary" plain @click="handleSearch">Tìm kiếm</el-button>
            <el-button type="warning" plain @click="$router.push('/scoring-model-management/single-scoring-model-khdn/add')">Thêm mới</el-button>
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
                    <router-link :to="{name: 'EditSingleScoringModel', params: {id: row.id} }">
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
          <el-table-column label="Mã mô hình đơn" width="200px" header-align="center">
            <template slot-scope="{row}">
              {{ row.codeModel }}
            </template>
          </el-table-column>
          <el-table-column label="Tên mô hình đơn" width="200px" header-align="center">
            <template slot-scope="{row}">
              {{ row.nameModel }}
            </template>
          </el-table-column>
          <el-table-column label="Loại xếp hạng" header-align="center">
            <template slot-scope="{row}">
              {{ renderTypeRank(row.typeRank) }}
            </template>
          </el-table-column>
          <el-table-column label="Quy mô" header-align="center">
            <template slot-scope="{}">
              ?????????
            </template>
          </el-table-column>
          <el-table-column label="Nhóm ngành" header-align="center">
            <template slot-scope="{row}">
              {{ row.industryGroup }}
            </template>
          </el-table-column>
          <el-table-column label="Người tạo" header-align="center">
            <template slot-scope="{row}">
              {{ (row.userCreated) }}
            </template>
          </el-table-column>
          <el-table-column label="Người duyệt" header-align="center">
            <template slot-scope="{row}">
              {{ (row.userApproved) }}
            </template>
          </el-table-column>
          <el-table-column label="Ngày tạo" header-align="center" align="center">
            <template slot-scope="{row}">
              {{ convertDateToLocal(row.createdTime, 'DD/MM/YYYY') }}
            </template>
          </el-table-column>
          <el-table-column label="Ngày duyệt" header-align="center" align="center">
            <template slot-scope="{row}">
              {{ convertDateToLocal(row.approvedTime, 'DD/MM/YYYY') }}
            </template>
          </el-table-column>
          <el-table-column label="Trạng thái" header-align="center" align="center">
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
        <delete-scoring-model
          :dialog-delete-visible.sync="dialogDeleteVisible"
          :temp="temp"
          :title="title"
          :list.sync="list"
          :pagination.sync="pagination"
        />
      </div>
    </div>

  </ValidationObserver>
</template>

<script>

import waves from '@/directive/waves'
import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import baseCommon from '@/utils/base-common'
import DeleteScoringModel from '@/views/scoring-model-management/single-scoring-model-khdn/components/delete-scoring-model'
import { getAllPage } from '@/api/single-scoring-model-khdn'
export default {
  name: 'ListSingleScoringModel',
  components: { ValidationObserver, ValidationProvider, DeleteScoringModel },
  directives: { waves },
  mixins: [baseCommon, BaseValidate],
  data() {
    return {
      list: null,
      dialogDeleteVisible: false,
      filter: {
        nameModel: null,
        typeRank: null,
        status: null,
        scaleClassify: null,
        industryGroup: null,
        userCreated: null,
        userApproved: null,
        createdTimeFrom: null,
        createdTimeTo: null,
        approvedTimeFrom: null,
        approvedTimeTo: null,
        pageSize: 10,
        pageIndex: 0
      },
      temp: {},
      industryGroups: null,
      scaleClassifies: null,
      title: null,
      isAdmin: false
    }
  },
  created() {
    this.getListSingleScoringModelKHDN()
  },
  methods: {
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getListSingleScoringModelKHDN()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getListSingleScoringModelKHDN()
    },
    handleSearch() {
      this.getListSingleScoringModelKHDN()
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
    renderTypeRank(type) {
      if (!type) {
        return ''
      }
      let str = ''
      switch (type) {
        case 1: {
          str = 'Xếp hạng tài chính'
          break
        }
        case 2: {
          str = 'Xếp hạng phi tài chính'
          break
        }
        case 3: {
          str = 'Xếp hạng kết hợp'
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
          str = 'Hoạt động'
          break
        }
        case 6: {
          str = 'Ngừng hoạt động'
          break
        }
      }
      return str
    },
    getListSingleScoringModelKHDN() {
      this.filter.pageIndex = this.pagination.pageNumber
      this.filter.pageSize = this.pagination.pageSize
      this.listLoading = true
      getAllPage(this.filter).then(response => {
        this.list = response.data.result
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
