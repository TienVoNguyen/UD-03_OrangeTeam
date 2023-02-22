<template>
  <div class="app-container">
    <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
      <el-form ref="matrixClassifyForm" label-position="left">
        <div class="row">
          <div class="col-5">
            <ValidationProvider
              v-slot="{ errors }"
              name="nameMatrix"
              :rules="{required: true, max: 100}"
            >
              <el-form-item
                prop="nameMatrix"
                :error="messageError(matrixClassifyFields.nameMatrix, errors[0])"
              >
                <label for="name">Tên ma trận <span class="text-danger">*</span></label>
                <el-input
                  id="nameMatrix"
                  ref="nameMatrix"
                  v-model="matrixClassify.nameMatrix"
                  :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'"
                  class="form-control non-border"
                  placeholder="Nhập tên ma trận"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-2" />
          <div class="col-5">
            <ValidationProvider
              v-slot="{ errors }"
              name="rule"
              :rules="{required: true}"
            >
              <el-form-item
                prop="rule"
                :error="messageError(matrixClassifyFields.rules, errors[0])"
              >
                <label for="unit">Loại quy tắc phân loại quy mô <span class="text-danger">*</span></label>
                <el-select id="rules" v-model="dataChangeMultiSelect" multiple class="form-control non-border" placeholder="Chọn loại quy tắc phân loại quy mô" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix' || $route.name === 'ApproveMatrix'" @change="changeMultiSelect">
                  <el-option label="Quy tắc phân loại TPBank" value="1" />
                  <el-option label="Quy tắc phân loại theo điểm chỉ tiêu" value="2" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <div v-if="matrixClassify.rules === 1 || matrixClassify.rules === 3" class="col-5">
            <ValidationProvider
              v-slot="{ errors }"
              name="tpBankRules"
              :rules="{required: true}"
            >
              <el-form-item
                prop="tpBankRule"
                :error="messageError(matrixClassifyFields.tpBankRule, errors[0])"
              >
                <label for="lstActiveTPBankRules">Quy tắc phân loại TPBank <span class="text-danger">*</span></label>
                <el-select id="lstActiveTPBankRule" v-model="matrixClassify.tpBankRuleId" value="" filterable clearable class="form-control non-border" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'">
                  <el-option v-for="item in lstActiveTPBankRules" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>

              </el-form-item>
            </ValidationProvider>
          </div>
          <div v-if="matrixClassify.rules === 1 || matrixClassify.rules === 3" class="col-2" />
          <div v-if="matrixClassify.rules === 2 || matrixClassify.rules === 3" class="col-5">
            <ValidationProvider
              v-slot="{ errors }"
              name="pointRules"
              :rules="{required: true}"
            >
              <el-form-item
                prop="pointRule"
                :error="messageError(matrixClassifyFields.pointRule, errors[0])"
              >
                <label for="lstActivePointRules">Quy tắc phân loại theo điểm chỉ tiêu <span class="text-danger">*</span></label>
                <el-select id="lstActivePointRule" v-model="matrixClassify.pointRuleId" value="" filterable clearable class="form-control non-border" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'">
                  <el-option v-for="item in lstActivePointRules" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div v-if=" matrixClassify.rules === 3" class="row">
          <div class="col-12">

            <label>Thiết lập ma trận <span class="text-danger">*</span></label>
            <el-table
              :data="dataMatrix"
              :header-cell-style="{ background: '#F2F2F3', color: '#000000' }"
              border
              fit
              highlight-current-row
              style="width: 100%;"
            >

              <el-table-column label="STT" header-align="center" width="150px" align="center">
                <template slot-scope="{$index}">
                  {{ $index + 1 }}
                </template>
              </el-table-column>
              <el-table-column label="Quy mô - Quy tắc phân loại TPBank" header-align="center" align="center">
                <template slot-scope="{row}">
                  {{ row.scaleTPBankRule }}
                </template>
              </el-table-column>
              <el-table-column label="Quy mô - Quy tắc phân loại theo điểm chỉ tiêu" header-align="center" align="center">
                <template slot-scope="{row}">
                  {{ row.scalePointRule }}
                </template>
              </el-table-column>
              <el-table-column label="Quy mô ma trận" header-align="center" align="center">
                <template slot-scope="{row, $index}">

                  <ValidationProvider
                    v-slot="{ errors }"
                    :name="'scaleMatrix'+$index"
                    :rules="{required: true}"
                  >
                    <el-form-item
                      :prop="'scaleMatrix'+$index"
                      :error="messageError(matrixClassifyFields.establishMatrix, errors[0])"
                    >
                      <el-select id="scaleMatrix" v-model="row.scaleMatrix" class="form-control non-border" placeholder="Chọn" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'">
                        <el-option label="Nhỏ" value="1" />
                        <el-option label="Vừa" value="2" />
                        <el-option label="Lớn" value="3" />
                      </el-select>
                    </el-form-item>

                  </ValidationProvider>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>

        <div v-if="flagReject" class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="requestChangeMatrix"
              :rules="{required: true}"
            >
              <el-form-item
                prop="requestChangeMatrix"
                :error="messageError(matrixClassifyFields.requestChangeMatrix, errors[0])"
              >
                <label for="name">Lý do từ chối <span class="text-danger">*</span></label>
                <el-input
                  id="requestChangeMatrix"
                  ref="requestChangeMatrix"
                  v-model="matrixClassify.requestChange"
                  :disabled="matrixClassify.status === 3 || $route.name === 'ViewDetailMatrix'"
                  class="form-control non-border"
                  placeholder="Nhập lý do từ chối"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
      </el-form>
      <div class="mt-5 text-center">
        <el-button type="primary" plain @click="closePage">ĐÓNG</el-button>
        <el-button v-if="$route.name !== 'ViewDetailMatrix' && $route.name !== 'ApproveMatrix'" type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="$route.name === 'ApproveMatrix' && !flagReject" type="success" plain @click="handleSubmit(submit)">PHÊ DUYỆT</el-button>
        <el-button v-if="$route.name === 'ApproveMatrix' && !flagReject" type="danger" plain @click="handleRejectFrontEnd(submit)">TỪ CHỐI</el-button>
        <el-button v-if="$route.name === 'ApproveMatrix' && flagReject" type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="$route.name !== 'ApproveMatrix'" type="success" plain>TRÌNH DUYỆT</el-button>
      </div>
    </ValidationObserver>
  </div>
</template>

<script>

import baseCommon from '@/utils/base-common'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'
import { createMatrix, getOneById, updateMatrix } from '@/api/matrix-classify'

export default {
  name: 'ModifyMatrix',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate, baseCommon],
  data() {
    return {
      matrixClassify: {
        id: null,
        nameMatrix: null,
        rules: null,
        tpBankRuleId: null,
        pointRuleId: null,
        contentMatrix: null,
        requestChange: null,
        status: null,
        createdBy: null,
        approveBy: null
      },
      dataMatrix: null,
      dataChangeMultiSelect: [],
      flagChangeMultiSelect: false,
      lstActiveTPBankRules: {},
      lstActivePointRules: {},
      flagReject: false
    }
  },
  created() {
    this.getLstActiveTPBankRule()
    this.getLstActivePointRule()
    this.renderContentMatrix()
    this.getObjectFromPath()
    this.getObjectFromBody()
  },
  methods: {
    closePage() {
      this.$router.back()
    },
    submit() {
      // console.log(this.$route)
      if (this.$route.name === 'AddMatrix') {
        this.createData()
      } else if (this.$route.name === 'EditMatrix') {
        this.updateData()
      } else if (this.$route.name === 'ApproveMatrix' && this.flagReject) {
        this.rejectData()
      } else if (this.$route.name === 'ApproveMatrix' && !this.flagReject) {
        this.acceptData()
      }
    },
    createData() {
      this.matrixClassify.status = 1
      this.matrixClassify.createdBy = 'Tung_NT'
      this.matrixClassify.contentMatrix = JSON.stringify(this.dataMatrix)
      createMatrix(this.matrixClassify).then(response => {
        this.$router.back()
        this.notifySuccess('Thông tin', response.message)
      }).catch((error) => {
        if (error.response) {
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })
    },
    handleRejectFrontEnd() {
      this.matrixClassify.contentMatrix = JSON.stringify(this.dataMatrix)
      this.flagReject = true
    },
    rejectData() {
      this.matrixClassify.contentMatrix = JSON.stringify(this.dataMatrix)
      this.matrixClassify.status = 3
      updateMatrix(this.matrixClassify).then(response => {
        this.$router.back()
        this.notifySuccess('Thông tin', 'Phê duyệt thành công')
      })
    },
    acceptData() {
      this.matrixClassify.contentMatrix = JSON.stringify(this.dataMatrix)
      this.matrixClassify.status = 5
      this.matrixClassify.approveBy = 'approveBy TUNG'
      updateMatrix(this.matrixClassify).then(response => {
        this.$router.back()
        this.notifySuccess('Thông tin', 'Phê duyệt thành công')
      })
    },
    updateData() {
      this.matrixClassify.contentMatrix = JSON.stringify(this.dataMatrix)
      if (this.matrixClassify.status === 3) {
        this.matrixClassify.status = 4
      }
      updateMatrix(this.matrixClassify).then(response => {
        console.log(response)
        this.$router.back()
        this.notifySuccess('Thông tin', response.message)
      })
    },
    getObjectFromBody() {
      const data = this.$route.query
      if (data.nameMatrix !== null && data.nameMatrix !== undefined) {
        data.nameMatrix = data.nameMatrix + ' - Copy'
        this.matrixClassify = data
        if (this.matrixClassify.rules === 3) {
          this.dataChangeMultiSelect = ['1', '2']
        } else if (data.rules !== null && data.rules !== undefined) {
          this.dataChangeMultiSelect = [data.rules.toString()]
        }
        if (this.matrixClassify.contentMatrix !== null && this.matrixClassify.contentMatrix !== undefined) {
          this.dataMatrix = JSON.parse(this.matrixClassify.contentMatrix)
        }
      }
    },
    getObjectFromPath() {
      const id = this.$route.params.id
      if (id) {
        getOneById(id).then(response => {
          this.matrixClassify.id = id
          this.matrixClassify.nameMatrix = response.data.nameMatrix
          this.matrixClassify.rules = response.data.rules
          if (this.matrixClassify.rules === 3) {
            this.dataChangeMultiSelect = ['1', '2']
          } else if (response.data.rules !== null && response.data.rules !== undefined) {
            this.dataChangeMultiSelect = [response.data.rules.toString()]
          }

          this.matrixClassify.tpBankRuleId = response.data.tpBankRuleId
          this.matrixClassify.pointRuleId = response.data.pointRuleId
          this.matrixClassify.contentMatrix = response.data.contentMatrix

          if (this.matrixClassify.contentMatrix !== null && this.matrixClassify.contentMatrix !== undefined) {
            this.dataMatrix = JSON.parse(this.matrixClassify.contentMatrix)
          }

          this.matrixClassify.requestChange = response.data.requestChange
          this.matrixClassify.status = response.data.status
          if (this.matrixClassify.status === 3) {
            this.flagReject = true
          }
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
    changeMultiSelect(e) {
      this.flagChangeMultiSelect = true
      // console.log(e)
      if (e.length === 2) {
        this.matrixClassify.rules = 3
      } else if (e.length === 1) {
        this.matrixClassify.rules = parseInt(e[0])
      }
    },
    getLstActiveTPBankRule() {
      this.lstActiveTPBankRules = [
        {
          id: 1,
          name: 'TPBankRule 1'
        },
        {
          id: 2,
          name: 'TPBankRule 2'
        },
        {
          id: 3,
          name: 'TPBankRule 3'
        }
      ]
    },
    getLstActivePointRule() {
      this.lstActivePointRules = [
        {
          id: 1,
          name: 'PointRule 1'
        },
        {
          id: 2,
          name: 'PointRule 2'
        },
        {
          id: 3,
          name: 'PointRule 3'
        }
      ]
    },
    renderContentMatrix() {
      this.dataMatrix = [
        {
          id: 1,
          scaleTPBankRule: 'Nhỏ',
          scalePointRule: 'Nhỏ',
          scaleMatrix: null
        },
        {
          id: 2,
          scaleTPBankRule: 'Nhỏ',
          scalePointRule: 'Vừa',
          scaleMatrix: null
        },
        {
          id: 3,
          scaleTPBankRule: 'Nhỏ',
          scalePointRule: 'Lớn',
          scaleMatrix: null
        },
        {
          id: 4,
          scaleTPBankRule: 'Vừa',
          scalePointRule: 'Nhỏ',
          scaleMatrix: null
        },
        {
          id: 5,
          scaleTPBankRule: 'Vừa',
          scalePointRule: 'Vừa',
          scaleMatrix: null
        },
        {
          id: 6,
          scaleTPBankRule: 'Vừa',
          scalePointRule: 'Lớn',
          scaleMatrix: null
        },
        {
          id: 7,
          scaleTPBankRule: 'Lớn',
          scalePointRule: 'Nhỏ',
          scaleMatrix: null
        },
        {
          id: 8,
          scaleTPBankRule: 'Lớn',
          scalePointRule: 'Vừa',
          scaleMatrix: null
        },
        {
          id: 9,
          scaleTPBankRule: 'Lớn',
          scalePointRule: 'Lớn',
          scaleMatrix: null
        }
      ]
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
