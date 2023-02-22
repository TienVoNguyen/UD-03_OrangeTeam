<template>
  <div class="app-container">
    <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
      <el-form ref="matrixClassifyForm" label-position="left">
        <h6 style="color: #EF8C1A" class="title mt-3"><strong>1. Thông cấu hình tham số KHDN</strong></h6>
        <div class="row">
          <div class="col-3">
            <ValidationProvider
              name="codeConfig"
            >
              <el-form-item
                prop="codeConfig"
              >
                <label for="name">Mã cấu hình tham số</label>
                <el-input
                  id="codeConfig"
                  ref="codeConfig"
                  v-model="dataTypeConfig.codeConfig"
                  :disabled="true"
                  class="form-control non-border"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              name="nameConfig"
            >
              <el-form-item
                prop="nameConfig"
              >
                <label for="name">Tên cấu hình tham số</label>
                <el-input
                  id="nameConfig"
                  ref="nameConfig"
                  v-model="dataTypeConfig.nameConfig"
                  :disabled="true"
                  class="form-control non-border"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              name="statusTypeConfig"
            >
              <el-form-item
                prop="statusTypeConfig"
              >
                <label for="name">Trạng thái</label>
                <el-select id="statusTypeConfig" v-model="dataTypeConfig.status" :disabled="$route.name === 'AddConfig'" class="form-control non-border" placeholder="---Chọn trạng thái---">
                  <el-option v-for="item in lstStatusTypeConfig" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <h6 style="color: #EF8C1A" class="title mt-3"><strong>2. Chi tiết thuộc tính</strong></h6>
        <div class="row">
          <div class="col-3">
            <ValidationProvider
              name="codeProperty"
            >
              <el-form-item
                prop="codeProperty"
              >
                <label for="name">Mã thuộc tính</label>
                <el-input
                  id="codeProperty"
                  ref="codeProperty"
                  v-model="filter.codeProperty"
                  class="form-control non-border"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              name="nameProperty"
            >
              <el-form-item
                prop="nameProperty"
              >
                <label for="name">Tên thuộc tính</label>
                <el-input
                  id="nameProperty"
                  ref="nameProperty"
                  v-model="filter.nameProperty"
                  class="form-control non-border"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              name="statusProperty"
            >
              <el-form-item
                prop="statusProperty"
              >
                <label for="name">Trạng thái</label>
                <el-select id="statusProperty" v-model="filter.status" class="form-control non-border" placeholder="--- Tất cả ---">
                  <el-option label="Tất cả" value="" />
                  <el-option label="Hoạt động" value="1" />
                  <el-option label="Không hoạt động" value="2" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

      </el-form>
      <div class="mt-5 text-center">
        <el-button type="primary" plain @click="handleSearch">TÌM KIẾM</el-button>
        <el-button type="success" plain @click="handleCreate">THÊM MỚI</el-button>
        <el-button type="warning" plain @click="handleImport">IMPORT</el-button>
      </div>

      <div class="row mt-5">
        <el-table
          :data="dataConfigProperty"
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
                <div class="col-12 text-center">
                  <span class="me-2">
                    <svg-icon icon-class="edit" style="color: #083863" @click="handleUpdate(row)" />
                  </span>
                </div>
              </div>

            </template>
          </el-table-column>
          <el-table-column label="STT" width="100px" header-align="center" align="center">
            <template slot-scope="{$index}">
              {{ $index + 1 }}
            </template>
          </el-table-column>
          <el-table-column label="Mã thuộc tính" width="300px" header-align="center">
            <template slot-scope="{row}">
              {{ row.codeProperty }}
            </template>
          </el-table-column>
          <el-table-column label="Tên thuộc tính" header-align="center">
            <template slot-scope="{row}">
              {{ row.nameProperty }}
            </template>
          </el-table-column>
          <el-table-column label="Mô tả" header-align="center">
            <template slot-scope="{row}">
              {{ row.description }}
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
        <ImportProperty
          :dialog-import-visible.sync="dialogImportVisible"
          :parent="dataTypeConfig"
          @reload="getDataConfigProperty"
        />
      </div>
      <ModifyProperty
        :current="currentObj"
        :dialog-form-visible.sync="dialogFormVisible"
        :dialog-status="dialogStatus"
        :parent="dataTypeConfig"
        @reload="getDataConfigProperty"
      />

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

      <div class="mt-5 text-center">
        <el-button type="primary" plain @click="closePage">ĐÓNG</el-button>
        <el-button type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
      </div>
    </ValidationObserver>
  </div>
</template>

<script>

import baseCommon from '@/utils/base-common'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'
import { getOneById } from '@/api/type-config-parameter'
import { getAllPage } from '@/api/config-parameter-property'
import { updateTypeConfig } from '@/api/type-config-parameter'
import ModifyProperty from '@/views/parameter-management/config-parameter-khdn/components/modify-property.vue' // secondary package based on el-pagination
import ImportProperty from '@/views/parameter-management/config-parameter-khdn/components/import-property.vue' // secondary package based on el-pagination

export default {
  name: 'ModifyConfig',
  components: { ValidationObserver, ValidationProvider, ModifyProperty, ImportProperty },
  mixins: [BaseValidate, baseCommon],
  data() {
    return {
      filter: {
        idTypeConfig: null,
        codeProperty: null,
        nameProperty: null,
        status: null
      },
      configProperty: {
        id: null,
        idTypeConfig: null,
        codeProperty: null,
        nameProperty: null,
        description: null,
        status: null
      },
      dataTypeConfig: null,
      dataConfigProperty: null,
      lstStatusTypeConfig: null,
      dialogFormVisible: false,
      dialogImportVisible: false,
      dialogStatus: '',
      currentObj: {
        id: null,
        idTypeConfig: null,
        codeProperty: null,
        nameProperty: null,
        description: null,
        status: null
      }
    }
  },
  created() {
    this.getObjectFromPath()
    this.getLstStatusTypeConfig()
    this.getDataConfigProperty()
  },
  methods: {
    submit() {
      if (this.$route.name === 'AddConfig') {
        this.createData()
      } else if (this.$route.name === 'EditConfig') {
        this.updateData()
      }
    },
    updateData() {
      updateTypeConfig(this.dataTypeConfig).then(response => {
        this.closePage()
        // this.getDataConfigProperty()
        this.notifySuccess('Thông tin', response.message)
      }).catch((error) => {
        if (error.response) {
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })
    },
    handleCurrentChange(val) {
      this.pagination.pageNumber = val
      this.getListMatrix()
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getListMatrix()
    },
    handleSearch() {
      this.getDataConfigProperty()
    },
    closePage() {
      this.$router.back()
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.currentObj = {
        id: null,
        idTypeConfig: null,
        codeProperty: null,
        nameProperty: null,
        description: null,
        status: 1
      }
      this.resetSearchObj()
      this.dialogFormVisible = true
    },
    handleUpdate(row, $index) {
      this.dialogStatus = 'update'
      this.currentObj = row
      this.dialogFormVisible = true
    },
    handleImport() {
      this.dialogImportVisible = true
    },
    resetSearchObj() {
      this.filter = {
        idTypeConfig: null,
        codeProperty: null,
        nameProperty: null,
        status: null
      }
    },
    renderStatus(status) {
      if (!status) {
        return ''
      }
      let str = ''
      switch (status) {
        case 1: {
          str = 'Hoạt động'
          break
        }
        case 2: {
          str = 'Không hoạt động'
          break
        }
      }
      return str
    },
    getLstStatusTypeConfig() {
      this.lstStatusTypeConfig = [
        {
          id: 1,
          name: 'Chưa tạo thuộc tính'
        },
        {
          id: 2,
          name: 'Hoạt động'
        },
        {
          id: 3,
          name: 'Không hoạt động'
        }
      ]
    },
    getObjectFromPath() {
      const id = this.$route.params.id
      if (id) {
        getOneById(id).then(response => {
          this.dataTypeConfig = response.data
          // this.$router.back()
          this.notifySuccess('Thông tin', response.message)
        }).catch((error) => {
          if (error.response) {
            console.log(error.response)
            this.notifyError('Thông tin', error.response.data.message)
          }
        })
      }
    },
    getDataConfigProperty() {
      const id = this.$route.params.id
      this.filter.idTypeConfig = id
      if (id) {
        getAllPage(this.filter).then(response => {
          this.dataConfigProperty = response.data.result
          // this.$router.back()
          this.pagination = {
            pageNumber: response.data.pageNumber,
            pageSize: response.data.pageSize,
            totalItems: response.data.totalItems,
            totalPages: response.data.totalPages
          }
          this.notifySuccess('Thông tin', response.message)
        }).catch((error) => {
          if (error.response) {
            console.log(error.response)
            this.notifyError('Thông tin', error.response.data.message)
          }
        })
      }
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
