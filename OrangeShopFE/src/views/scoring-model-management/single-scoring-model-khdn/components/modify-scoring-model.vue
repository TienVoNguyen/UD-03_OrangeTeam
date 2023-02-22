<template>
  <div class="app-container">
    <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
      <h6 style="color: #EF8C1A" class="title mt-3"><strong>I. Thông tin chung</strong></h6>
      <el-form ref="scoringModelForm" label-position="left">
        <div class="row">
          <div class="col-3">
            <ValidationProvider
              v-slot="{ errors }"
              name="codeModel"
              :rules="{required: true, max: 100}"
            >
              <el-form-item
                prop="codeModel"
                :error="messageError(singleScoringModelKHDNFields.codeModel, errors[0])"
              >
                <label for="name">Mã mô hình đơn<span class="text-danger">*</span></label>
                <el-input
                  id="codeModel"
                  ref="codeModel"
                  v-model="scoringModel.codeModel"
                  :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'"
                  class="form-control non-border"
                  placeholder="Nhập mã mô hình đơn"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              v-slot="{ errors }"
              name="nameModel"
              :rules="{required: true, max: 100}"
            >
              <el-form-item
                prop="nameModel"
                :error="messageError(singleScoringModelKHDNFields.nameModel, errors[0])"
              >
                <label for="name">Tên mô hình đơn<span class="text-danger">*</span></label>
                <el-input
                  id="nameModel"
                  ref="nameModel"
                  v-model="scoringModel.nameModel"
                  :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'"
                  class="form-control non-border"
                  placeholder="Nhập mã mô hình đơn"
                />
              </el-form-item>
            </ValidationProvider>
          </div>

          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              v-slot="{ errors }"
              name="typeRank"
              :rules="{required: true}"
            >
              <el-form-item
                prop="typeRank"
                :error="messageError(singleScoringModelKHDNFields.typeRank, errors[0])"
              >
                <label for="typeRank">Loại xếp hạng <span class="text-danger">*</span></label>
                <el-select id="typeRank" v-model="scoringModel.typeRank" class="form-control non-border" placeholder="---Chọn loại xếp hạng---" @change="changeTypeRankCSS">
                  <el-option v-for="item in typeRanks" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <div class="col-3">
            <ValidationProvider
              v-slot="{ errors }"
              name="scaleClassify"
              :rules="{required: true}"
            >
              <el-form-item
                prop="scaleClassify"
                :error="messageError(singleScoringModelKHDNFields.scaleClassify, errors[0])"
              >
                <label>Phân loại quy mô<span class="text-danger">*</span></label>
                <el-select id="scaleClassify" v-model="scoringModel.scaleClassify" multiple class="form-control non-border" placeholder="Chọn loại quy mô" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix' || $route.name === 'ApproveMatrix'">
                  <el-option v-for="item in scaleClassifies" :key="item.id" :label="item.nameProperty" :value="item.id" />
                </el-select>

              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              v-slot="{ errors }"
              name="industryGroup"
              :rules="{required: true}"
            >
              <el-form-item
                prop="industryGroup"
                :error="messageError(singleScoringModelKHDNFields.industryGroup, errors[0])"
              >
                <label>Nhóm ngành<span class="text-danger">*</span></label>
                <el-select id="industryGroup" v-model="scoringModel.industryGroup" multiple class="form-control non-border" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'">
                  <el-option v-for="item in industryGroups" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>

              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <h6 style="color: #EF8C1A" class="title mt-3"><strong>II. Triển khai phương pháp chấm điểm</strong></h6>

        <div class="row">
          <div class="col-3">
            <ValidationProvider
              v-slot="{ errors }"
              name="inputModel"
              :rules="{required: true}"
            >
              <el-form-item
                prop="inputModel"
                :error="messageError(singleScoringModelKHDNFields.inputModel, errors[0])"
              >
                <label>Tên mô hình nhập liệu<span class="text-danger">*</span></label>
                <el-select id="inputModel" v-model="scoringModel.inputModel" class="form-control non-border" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'" @change="getListOutputCSS()">
                  <el-option v-for="item in inputModels" :key="item.id" :label="item.nameInputModel" :value="item.id" />
                </el-select>

              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              v-slot="{ errors }"
              name="typeCode"
              :rules="{required: true}"
            >
              <el-form-item
                prop="typeCode"
                :error="messageError(singleScoringModelKHDNFields.typeCode, errors[0])"
              >
                <label>Loại code<span class="text-danger">*</span></label>
                <el-select id="typeCode" v-model="scoringModel.typeCode" class="form-control non-border" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'">
                  <el-option v-for="item in typeCodes" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>

              </el-form-item>
            </ValidationProvider>
          </div>

          <div class="col-1" />
          <div class="col-3">
            <ValidationProvider
              ref="file"
              v-slot="{ errors }"
              name="file"
            >
              <el-form-item
                prop="file"
                :error="messageError(singleScoringModelKHDNFields.fileCodeModel, errors[0])"
              >
                <div>
                  <label for="excel" style="">File code model<span class="text-danger">*</span></label>
                  <input
                    ref="excel-upload-input"
                    class="form-control custom-file-input"
                    type="file"
                    accept=".xlsx, .xls"
                    multiple
                    @change="handleFileUpload( $event )"
                  >
                  <span>Tải về file đã upload</span> <a role="button" style="margin-top: -4px; color:blue" @click="handleDownloadFile">tại đây</a>
                </div>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <div class="row">
          <label>1. Biến từ CSS</label>

          <el-table
            :data="dataOutputCSS"
            :header-cell-style="{ background: '#F2F2F3', color: '#000000' }"
            border
            fit
            highlight-current-row
            style="width: 100%;"
          >
            <el-table-column label="Mã tiêu chí" header-align="center" width="200px" align="center">
              <template slot="header">
                <div class="text-center">
                  <label>Mã tiêu chí</label>
                  <div class="el-input el-input--medium el-input--suffix">
                    <input
                      v-model="filterCSS.codeCriteria"
                      placeholder="Tìm kiếm"
                      type="text"
                      class="el-input__inner"
                      @input="handleFilterCSSChange"
                    >
                  </div>
                </div>
              </template>
              <template slot-scope="{row, $index}">
                <el-input
                  :id="'codeCriteria'+$index"
                  v-model="row.codeCriteria"
                  class="form-control non-border"
                  placeholder="Nhập"
                />
              </template>
            </el-table-column>
            <el-table-column label="Tên tiêu chí" header-align="center" width="800px" align="center">
              <template slot="header">
                <div class="text-center">
                  <label>Tên tiêu chí</label>
                  <div class="el-input el-input--medium el-input--suffix">
                    <input
                      v-model="filterCSS.nameCriteria"
                      placeholder="Tìm kiếm"
                      type="text"
                      class="el-input__inner"
                      @input="handleFilterCSSChange"
                    >
                  </div>
                </div>
              </template>
              <template slot-scope="{row, $index}">
                <el-input
                  :id="'nameCriteria'+$index"
                  v-model="row.nameCriteria"
                  class="form-control non-border"
                  placeholder="Nhập"
                />
                <!-- {{ row.nameCriteria }} -->
              </template>
            </el-table-column>
            <el-table-column label="Loại tiêu chí" header-align="center" width="350px" align="center">
              <template slot="header">
                <div class="text-center">
                  <label>Loại tiêu chí</label>
                  <div class="el-input el-input--medium el-input--suffix">
                    <el-select v-model="filterCSS.typeCriteria" :disabled="scoringModel.typeRank === 1 || scoringModel.typeRank === 2" class="form-control non-border" placeholder="Tất cả" @change="handleFilterCSSChange">
                      <el-option
                        v-for="item in typeCriterias"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id"
                      />
                    </el-select>
                  </div>
                </div>
              </template>
              <template slot-scope="{row}">
                <!-- {{ renderTypeCriteria(row.typeCriteria) }} -->
                <el-radio-group v-model="row.typeCriteria">
                  <el-radio :disabled="scoringModel.typeRank === 1 || scoringModel.typeRank === 2" class="me-2" :label="1">Tài chính</el-radio>
                  <el-radio :disabled="scoringModel.typeRank === 2 || scoringModel.typeRank === 1" :label="2">Phi tài chính</el-radio>
                </el-radio-group>
              </template>
            </el-table-column>
            <el-table-column label="Chọn đầu ra" header-align="center" width="300px" align="center">
              <template slot="header">
                <div class="text-center">
                  <label>Chọn đầu ra</label>
                  <div class="el-input el-input--medium el-input--suffix">
                    <el-select v-model="filterCSS.isOutput" class="form-control non-border" placeholder="Tất cả" @change="handleFilterCSSChange">
                      <el-option label="Tất cả" value="" />
                      <el-option label="Có" value="1" />
                      <el-option label="Không" value="2" />
                    </el-select>
                  </div>
                </div>
              </template>
              <template slot-scope="{row, $index}">
                <ValidationProvider
                  v-slot="{ errors }"
                  :name="'isOutput'+$index"
                  :rules="{required: true}"
                >
                  <el-form-item
                    :prop="'isOutput'+$index"
                    :error="messageError(matrixClassifyFields.establishMatrix, errors[0])"
                  >
                    <!-- <el-checkbox :id="'isOutput'+$index" v-model="row.isOutput" :checked="row.isOutput===1" class="form-control non-border" :disabled="$route.name === 'ViewDetailMatrix' || $route.name === 'ApproveMatrix'" /> -->
                    <input
                      :ref="'checkbox' + $index"
                      v-model="row.isOutput"
                      :checked="row.isOutput===1"
                      type="checkbox"
                    >
                  </el-form-item>

                </ValidationProvider>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="row mt-3">
          <label>2. Biến từ Code Model
            <span class="el-icon-circle-plus-outline" style="color: #5E2E86" @click="addRowCodeModel()" />
            <span class="el-icon-upload2" style="color: #2E7EB7" @click="importListCodeModelCreateria()" />
          </label>
          <el-table
            :data="dataOutputCodeModel"
            :header-cell-style="{ background: '#F2F2F3', color: '#000000' }"
            border
            fit
            highlight-current-row
            style="width: 100%;"
          >
            <el-table-column label="Mã tiêu chí" header-align="center" width="200px" align="center">
              <template slot-scope="{row, $index}">
                <el-input
                  :id="'codeCriteria'+$index"
                  v-model="row.codeCriteria"
                  class="form-control non-border"
                  placeholder="Nhập"
                />
              </template>
            </el-table-column>
            <el-table-column label="Tên tiêu chí" header-align="center" width="900px" align="center">
              <template slot-scope="{row, $index}">
                <el-input
                  :id="'nameCriteria'+$index"
                  v-model="row.nameCriteria"
                  class="form-control non-border"
                  placeholder="Nhập"
                />
                <!-- {{ row.nameCriteria }} -->
              </template>
            </el-table-column>
            <el-table-column label="Loại tiêu chí" header-align="center" align="center">
              <template slot-scope="{row}">
                <!-- {{ renderTypeCriteria(row.typeCriteria) }} -->
                <el-radio-group v-model="row.typeCriteria">
                  <el-radio :disabled="scoringModel.typeRank === 1 || scoringModel.typeRank === 2" class="me-2" :label="1">Tài chính</el-radio>
                  <el-radio :disabled="scoringModel.typeRank === 1 || scoringModel.typeRank === 2" :label="2">Phi tài chính</el-radio>
                </el-radio-group>
              </template>
            </el-table-column>
            <el-table-column label="Nhóm tiêu chí" header-align="center" width="350px" align="center">
              <template slot-scope="{row, $index}">

                <el-select :id="'groupCriteria'+$index" v-model="row.groupCriteria" style="width:300px; text-align: center" class="form-control non-border" placeholder="Chọn nhóm tiêu chí">
                  <el-option
                    v-for="item in groupCriterias"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>

              </template>
            </el-table-column>
          </el-table>
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
                :error="messageError(singleScoringModelKHDNFields.requestChangeMatrix, errors[0])"
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
        <el-button v-if="$route.name !== 'ViewDetailSingleScoringModel' && $route.name !== 'ApproveSingleScoringModel'" type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="$route.name === 'ApproveSingleScoringModel' && !flagReject" type="success" plain @click="handleSubmit(submit)">PHÊ DUYỆT</el-button>
        <el-button v-if="$route.name === 'ApproveSingleScoringModel' && !flagReject" type="danger" plain @click="handleRejectFrontEnd(submit)">TỪ CHỐI</el-button>
        <el-button v-if="$route.name === 'ApproveSingleScoringModel' && flagReject" type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="$route.name !== 'ApproveSingleScoringModel'" type="success" plain>TRÌNH DUYỆT</el-button>
      </div>
    </ValidationObserver>

    <ImportOutputCodeModel
      :dialog-import-visible.sync="dialogImportVisible"
    />

  </div>
</template>

<script>

import baseCommon from '@/utils/base-common'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'
import { getOneById, updateSingleScoringModelKHDN, createSingleScoringModelKHDN, uploadFileSingleScoringModelKHDN } from '@/api/single-scoring-model-khdn'
import { getListDataField, getListFinancialVariable, getInputModel } from '@/api/single-scoring-model-khdn'
import { getAllOutputCSS, updateOutputCSS, createOutputCSS } from '@/api/out-put-scoring-model-from-css'
import { getAllOutputCodeModel, createOutputCodeModel, updateOutputCodeModel } from '@/api/out-put-scoring-model-from-code-model'
import ImportOutputCodeModel from '@/views/scoring-model-management/single-scoring-model-khdn/components/import-output-code-model.vue'
import { getAllPage } from '@/api/config-parameter-property'

export default {
  name: 'ModifySingleScoringModel',
  components: { ValidationObserver, ValidationProvider, ImportOutputCodeModel },
  mixins: [BaseValidate, baseCommon],
  data() {
    return {
      dialogImportVisible: false,
      scoringModel: {
        id: null,
        codeModel: null,
        nameModel: null,
        typeRank: null,
        scaleClassify: [],
        industryGroup: [],
        inputModel: null,
        typeCode: null,
        fileCodeModel: null,
        commentReport: null,
        commentApprove: null,
        status: null
      },
      filterCSS: {
        id: null,
        idSingleScoringModelKHDN: null,
        codeCriteria: null,
        nameCriteria: null,
        typeCriteria: null,
        isOutput: null
      },
      filterScaleClassify: {
        idTypeConfig: null,
        codeProperty: null,
        nameProperty: null,
        status: 1
      },
      filterCodeModel: {
        id: null,
        idSingleScoringModelKHDN: null,
        codeCriteria: null,
        nameCriteria: null,
        typeCriteria: null,
        groupCriteria: null
      },
      filterListDataField: {
        modelId: null
      },
      filterListFinancialVariable: {
        type: null
      },
      filterListIndustryGroup: {
        status: null
      },
      filterInputModel: {
        status: null,
        id: null
      },
      files: null,
      inputModels: [],
      typeCriterias: null,
      groupCriterias: null,
      dataOutputCSS: [],
      dataOutputCodeModel: [],
      dataChangeMultiSelect: [],
      flagChangeMultiSelect: false,
      lstActiveTPBankRules: {},
      lstActivePointRules: {},
      industryGroups: {},
      scaleClassifies: {},
      typeRanks: {},
      typeCodes: {},
      flagReject: false
    }
  },
  created() {
    this.getIndustryGroups()
    this.getScaleClassifies()
    this.getTypeRanks()
    this.getTypeCodes()
    this.getTypeCriterias()
    this.getInputModels()
    this.getObjectFromPath()

    this.getGroupCriterias()
  },
  methods: {
    closePage() {
      this.$router.back()
    },
    handleDownloadFile() {
      const lstFile = this.scoringModel.fileCodeModel.split(',')
      // console.log(lstFile)
      const link = document.createElement('a')
      link.href = `D:/CSS/TOPICFILE/uploads/${lstFile[1]}`
      link.setAttribute('download', 'file.doc')
      link.click()
    },
    getListOutputCSSFromFinancialVariable() {
      this.filterListFinancialVariable.type = this.scoringModel.typeRank
      getListFinancialVariable(this.filterListFinancialVariable).then(response => {
        this.appendFinancialVariableToCSS(response.data)
      }).catch((error) => {
        if (error.response) {
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })
    },
    getListOutputCSS() {
      this.filterInputModel.id = this.scoringModel.inputModel
      getInputModel(this.filterInputModel).then(response => {
        this.filterListDataField.modelId = response.data[0].dataNonfinancialModel

        if (this.filterListDataField.modelId !== null && this.filterListDataField.modelId !== undefined) {
          getListDataField(this.filterListDataField).then(response => {
            console.log(response)
          }).catch((error) => {
            if (error.response) {
              console.log(error.response)
              this.notifyError('Thông tin', error.response.data.message)
            }
          })
        }

        this.getListOutputCSSFromFinancialVariable()
      }).catch((error) => {
        if (error.response) {
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })

      // if (this.filterListDataField.modelId !== null && this.filterListDataField.modelId !== undefined) {
      //   getListDataField(this.filterListDataField).then(response => {
      //   }).catch((error) => {
      //     if (error.response) {
      //       console.log(error.response)
      //       this.notifyError('Thông tin', error.response.data.message)
      //     }
      //   })
      // }

      this.filterCSS.idSingleScoringModelKHDN = this.$route.params.id
      this.getListOutputCSSFromFinancialVariable()

      // getAllOutputCSS(this.filterCSS).then(response => {
      //   this.dataOutputCSS = response.data
      // })
    },
    getListOutputCodeModel() {
      this.filterCodeModel.idSingleScoringModelKHDN = this.$route.params.id
      getAllOutputCodeModel(this.filterCodeModel).then(response => {
        this.dataOutputCodeModel = response.data
      })
    },
    // getListOutputCodeModel() {
    //   getAllOutputCodeModel(this.filterCodeModel).then(response => {
    //     this.dataOutputCodeModel = response.data
    //   })
    // },
    submit() {
      if (this.$route.name === 'AddSingleScoringModel') {
        this.createData()
      } else if (this.$route.name === 'EditSingleScoringModel') {
        this.updateData()
      } else if (this.$route.name === 'ApproveSingleScoringModel' && this.flagReject) {
        this.rejectData()
      } else if (this.$route.name === 'ApproveSingleScoringModel' && !this.flagReject) {
        this.acceptData()
      }
    },
    createData() {
      this.scoringModel.status = 1

      this.scoringModel.scaleClassify = this.scoringModel.scaleClassify.toString()
      this.scoringModel.industryGroup = this.scoringModel.industryGroup.toString()

      if (this.files !== null && this.files.length > 0) {
        const formData = new FormData()
        for (var i = 0; i < this.files.length; i++) {
          formData.append('files', this.files[i])
        }
        uploadFileSingleScoringModelKHDN(formData).then(response => {
          this.scoringModel.fileCodeModel = response.data
          createSingleScoringModelKHDN(this.scoringModel).then(response => {
            if (this.dataOutputCodeModel.length > 0) {
              for (let i = 0; i < this.dataOutputCodeModel.length; i++) {
                if (this.dataOutputCodeModel[i].id !== null && this.dataOutputCodeModel[i].id !== undefined) {
                  updateOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                } else {
                  createOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                }
              }
            }
            if (this.dataOutputCSS.length > 0) {
              for (let i = 0; i < this.dataOutputCSS.length; i++) {
                if (this.dataOutputCSS[i].id !== null && this.dataOutputCSS[i].id !== undefined) {
                  if (this.dataOutputCSS[i].isOutput) {
                    this.dataOutputCSS[i].isOutput = 1
                  } else {
                    this.dataOutputCSS[i].isOutput = 0
                  }
                  updateOutputCSS((this.dataOutputCSS[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                } else {
                  createOutputCSS((this.dataOutputCSS[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                }
              }
            }
            this.$router.back()
            this.notifySuccess('Thông tin', response.message)
          })
        })
      } else {
        createSingleScoringModelKHDN(this.scoringModel).then(response => {
          if (this.dataOutputCodeModel.length > 0) {
            for (let i = 0; i < this.dataOutputCodeModel.length; i++) {
              if (this.dataOutputCodeModel[i].id !== null && this.dataOutputCodeModel[i].id !== undefined) {
                updateOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              } else {
                createOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              }
            }
          }
          if (this.dataOutputCSS.length > 0) {
            for (let i = 0; i < this.dataOutputCSS.length; i++) {
              if (this.dataOutputCSS[i].id !== null && this.dataOutputCSS[i].id !== undefined) {
                if (this.dataOutputCSS[i].isOutput) {
                  this.dataOutputCSS[i].isOutput = 1
                } else {
                  this.dataOutputCSS[i].isOutput = 0
                }
                updateOutputCSS((this.dataOutputCSS[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              } else {
                createOutputCSS((this.dataOutputCSS[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              }
            }
          }
          this.$router.back()
          this.notifySuccess('Thông tin', response.message)
        })
      }
    },
    addRowCodeModel() {
      var newRow = {
        idSingleScoringModelKHDN: this.scoringModel.id,
        codeCriteria: null,
        nameCriteria: null,
        typeCriteria: null,
        groupCriteria: null
      }
      this.dataOutputCodeModel.push(newRow)
    },
    updateData() {
      this.scoringModel.scaleClassify = this.scoringModel.scaleClassify.toString()
      this.scoringModel.industryGroup = this.scoringModel.industryGroup.toString()
      if (this.files !== null && this.files.length > 0) {
        const formData = new FormData()
        for (var i = 0; i < this.files.length; i++) {
          formData.append('files', this.files[i])
        }
        uploadFileSingleScoringModelKHDN(formData).then(response => {
          this.scoringModel.fileCodeModel = response.data
          updateSingleScoringModelKHDN(this.scoringModel).then(response => {
            if (this.dataOutputCodeModel.length > 0) {
              for (let i = 0; i < this.dataOutputCodeModel.length; i++) {
                if (this.dataOutputCodeModel[i].id !== null && this.dataOutputCodeModel[i].id !== undefined) {
                  updateOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                } else {
                  createOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                }
              }
            }
            if (this.dataOutputCSS.length > 0) {
              for (let i = 0; i < this.dataOutputCSS.length; i++) {
                if (this.dataOutputCSS[i].id !== null && this.dataOutputCSS[i].id !== undefined) {
                  if (this.dataOutputCSS[i].isOutput) {
                    this.dataOutputCSS[i].isOutput = 1
                  } else {
                    this.dataOutputCSS[i].isOutput = 0
                  }
                  updateOutputCSS((this.dataOutputCSS[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                } else {
                  createOutputCSS((this.dataOutputCSS[i])).then(response => {
                  }).catch((error) => {
                    if (error.response) {
                      console.log(error.response)
                    }
                  })
                }
              }
            }
            this.$router.back()
            this.notifySuccess('Thông tin', response.message)
          })
        })
      } else {
        updateSingleScoringModelKHDN(this.scoringModel).then(response => {
          if (this.dataOutputCodeModel.length > 0) {
            for (let i = 0; i < this.dataOutputCodeModel.length; i++) {
              if (this.dataOutputCodeModel[i].id !== null && this.dataOutputCodeModel[i].id !== undefined) {
                updateOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              } else {
                createOutputCodeModel((this.dataOutputCodeModel[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              }
            }
          }
          if (this.dataOutputCSS.length > 0) {
            for (let i = 0; i < this.dataOutputCSS.length; i++) {
              if (this.dataOutputCSS[i].id !== null && this.dataOutputCSS[i].id !== undefined) {
                if (this.dataOutputCSS[i].isOutput) {
                  this.dataOutputCSS[i].isOutput = 1
                } else {
                  this.dataOutputCSS[i].isOutput = 0
                }
                updateOutputCSS((this.dataOutputCSS[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              } else {
                createOutputCSS((this.dataOutputCSS[i])).then(response => {
                }).catch((error) => {
                  if (error.response) {
                    console.log(error.response)
                  }
                })
              }
            }
          }
          this.$router.back()
          this.notifySuccess('Thông tin', response.message)
        })
      }
    },
    getObjectFromPath() {
      const id = this.$route.params.id
      if (id) {
        getOneById(id).then(response => {
          this.scoringModel = response.data
          this.scoringModel.scaleClassify = this.scoringModel.scaleClassify.split(',').map(Number)
          this.scoringModel.industryGroup = this.scoringModel.industryGroup.split(',').map(Number)
          if (this.scoringModel.typeRank === 3) {
            this.filterCSS.typeCriteria = null
            this.filterCodeModel.typeCriteria = null
          } else {
            this.filterCSS.typeCriteria = this.scoringModel.typeRank
            this.filterCodeModel.typeCriteria = this.scoringModel.typeRank
          }
          this.filterListFinancialVariable.type = this.scoringModel.typeRank

          this.matrixClassify.tpBankRuleId = response.data.tpBankRuleId
          this.matrixClassify.pointRuleId = response.data.pointRuleId
          this.matrixClassify.contentMatrix = response.data.contentMatrix

          if (this.matrixClassify.contentMatrix !== null && this.matrixClassify.contentMatrix !== undefined) {
            this.dataMatrix = JSON.parse(this.matrixClassify.contentMatrix)
          }

          this.getListOutputCSSFromFinancialVariable()
          this.getListOutputCSS()
          this.getListOutputCodeModel()
          // this.getListCSS()
          this.notifySuccess('Thông tin', response.message)
        }).catch((error) => {
          if (error.response) {
            console.log(error.response)
            this.notifyError('Thông tin', error.response.data.message)
          }
        })
      }
    },
    getGroupCriterias() {
      this.groupCriterias = [
        {
          id: 1,
          name: 'Tài chính'
        },
        {
          id: 2,
          name: 'Phi tài chính'
        }
      ]
    },
    getInputModels() { // API mo hinh nhap lieu (anh quan do): dang hoat dong
      getInputModel(this.filterInputModel).then(response => {
        this.inputModels = response.data
      }).catch((error) => {
        if (error.response) {
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })
    },
    getIndustryGroups() { // API quan ly nhom nganh : dang hoat dong (anh hoang anh)
      this.industryGroups = [
        {
          id: 1,
          name: 'Bán buôn và bán lẻ'
        },
        {
          id: 2,
          name: 'Dịch vụ'
        },
        {
          id: 3,
          name: 'Ngành công nghiệp nặng'
        }
      ]
    },
    getTypeCodes() {
      this.typeCodes = [
        {
          id: 1,
          name: 'R'
        },
        {
          id: 2,
          name: 'Python'
        }
      ]
    },
    getTypeCriterias() { // API quan ly nhom nganh : dang hoat dong (anh hoang anh)
      this.typeCriterias = [
        {
          id: null,
          name: 'Tất cả'
        },
        {
          id: 1,
          name: 'Tài chính'
        },
        {
          id: 2,
          name: 'Phi tài chính'
        }
      ]
    },
    getTypeRanks() { // API quan ly nhom nganh : dang hoat dong (anh hoang anh)
      this.typeRanks = [
        {
          id: 1,
          name: 'Xếp hạng tài chính'
        },
        {
          id: 2,
          name: 'Xếp hạng phi tài chính'
        },
        {
          id: 3,
          name: 'Xếp hạng kết hợp'
        }
      ]
    },
    getScaleClassifies() {
      this.scaleClassifies
      getAllPage(this.filterScaleClassify).then(response => {
        this.scaleClassifies = response.data.result
      }).catch((error) => {
        if (error.response) {
          console.log(error.response)
        }
      })
    },
    renderTypeCriteria(type) {
      if (!type) {
        return ''
      }
      let str = ''
      switch (type) {
        case 1: {
          str = 'Tài chính'
          break
        }
        case 2: {
          str = 'Phi tài chính'
          break
        }
      }
      return str
    },
    // getListCSS() {
    //   if (this.scoringModel.typeRank === 1) {
    //     this.getFinanceListOutputCSS()
    //   } else if (this.scoringModel.typeRank === 2) {
    //     this.getNonFinanceListOutputCSS()
    //   } else if (this.scoringModel.typeRank === 3) {
    //     this.getListOutputCSSFromFinancialVariable()
    //   }
    // },
    handleFilterCSSChange() {
      this.filterCSS.idSingleScoringModelKHDN = this.$route.params.id
      getAllOutputCSS(this.filterCSS).then(response => {
        this.dataOutputCSS = response.data
      })
    },
    changeTypeRankCSS() {
      this.filterCSS.idSingleScoringModelKHDN = this.$route.params.id
      this.filterCSS.typeCriteria = this.scoringModel.typeRank

      this.filterCodeModel.typeCriteria = this.scoringModel.typeRank

      if (this.filterCodeModel.typeCriteria === 3) {
        this.filterCodeModel.typeCriteria = null
      }
      getAllOutputCodeModel(this.filterCodeModel).then(response => {
        this.dataOutputCodeModel = response.data
      })

      if (this.filterCSS.typeCriteria === 3) {
        this.filterCSS.typeCriteria = null
      }
      getAllOutputCSS(this.filterCSS).then(response => {
        this.dataOutputCSS = response.data
      })
    },
    customizeHeader(createElement, { column, $index }) {
      let col = ''
      if ($index === 0) {
        col = {
          label: 'Thao tác',
          hasSearch: false
        }
      } else {
        col = this.$props.tableHeader[$index - 1]
      }
      return (
        <div>
          <div class='d-block c-header text-center'>
            <span>{col.label}</span>
          </div>
          {col.hasSearch ? (
            <el-input
              suffix-icon='el-icon-search'
              v-model={this.search[col.prop]}
              placeholder='Tìm kiếm'
              input={this.handleInputChange}
            />
          ) : (
            <div>
              {' '}
              <div style='height: 36px'></div>{' '}
            </div>
          )}
        </div>
      )
    },
    handleFileUpload(e) {
      this.files = e.target.files
    },
    importListCodeModelCreateria() {
      this.dialogImportVisible = true
    },
    appendFinancialVariableToCSS(lstFinanc) {
      if (lstFinanc.length > 0) {
        for (let index = 0; index < lstFinanc.length; index++) {
          var element = lstFinanc[index]
          if (element !== null && element !== undefined) {
            var obj = {
              'id': null,
              'codeCriteria': element.varCode,
              'nameCriteria': element.variable,
              'idSingleScoringModelKHDN': null,
              'isOutput': 0,
              'typeCriteria': (element.type === 4) ? 1 : 2
            }
            // console.log(obj.typeCriteria)
            this.dataOutputCSS.push(obj)
          }
        }
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
