<template>
  <div class="app-container">
    <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
      <el-form ref="ruleTPBankForm" label-position="left">
        <div class="row">
          <div class="col-4">
            <ValidationProvider
              v-slot="{ errors }"
              name="name"
              :rules="{required: true, max: 100}"
            >
              <el-form-item
                prop="name"
                :error="messageError(tpBankRuleFields.name, errors[0])"
              >
                <label for="name">Tên quy tắc <span class="text-danger">*</span></label>
                <el-input
                  id="name"
                  ref="name"
                  v-model="tpBankRule.name"
                  :disabled="isDisabled || !canEdit()"
                  class="form-control non-border"
                  placeholder="Nhập tên quy tắc"
                />
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-4" />
          <div class="col-4">
            <el-form-item
              prop="unit"
            >
              <label for="unit">Đơn vị BCTC <span class="text-danger">*</span></label>
              <el-input
                id="unit"
                ref="unit"
                class="form-control non-border"
                value="Triệu đồng"
                disabled
                placeholder="Nhập đơn vị BCTC"
              />
            </el-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col-4">
            <ValidationProvider
              v-slot="{ errors }"
              name="sales"
              :rules="{required: true}"
            >
              <el-form-item
                prop="sales"
                :error="messageError(tpBankRuleFields.sales, errors[0])"
              >
                <label for="sales">Doanh thu <span class="text-danger">*</span></label>
                <el-select
                  id="sales"
                  v-model="tpBankRule.sales"
                  :disabled="isDisabled || !canEdit()"
                  class="form-control non-border"
                  placeholder="Chọn nguồn liên kết dữ liệu"
                >
                  <el-option label="Thông tin chung" :value="1" />
                  <el-option label="Báo cáo kết quả kinh doanh" :value="2" />
                  <el-option label="Bảng cân đối kế toán" :value="3" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-4" />
          <div class="col-4">
            <ValidationProvider
              v-slot="{ errors }"
              name="sales_criteria"
              :rules="{required: true}"
            >
              <el-form-item
                prop="sales_criteria"
                :error="messageError(tpBankRuleFields.salesCriteria, errors[0])"
              >
                <label for="sales_criteria" />
                <el-select
                  id="sales_criteria"
                  v-model="tpBankRule.salesCriteria"
                  :disabled="isDisabled || !canEdit()"
                  multiple
                  class="form-control non-border"
                  placeholder="Chọn tiêu chí liên kết dữ liệu"
                >
                  <el-option label="PL_0001C - Doanh thu bán hàng và cung cấp dịch vụ" :value="1" />
                  <el-option label="PL_0002C - Doanh thu bán hàng và cung cấp dịch vụ" :value="2" />
                  <el-option label="PL_0003C - Doanh thu bán hàng và cung cấp dịch vụ" :value="3" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <div class="col-4">
            <ValidationProvider
              v-slot="{ errors }"
              name="equity"
              :rules="{required: true}"
            >
              <el-form-item
                prop="equity"
                :error="messageError(tpBankRuleFields.equity, errors[0])"
              >
                <label for="equity">Vốn chủ sở hữu <span class="text-danger">*</span></label>
                <el-select
                  id="equity"
                  v-model="tpBankRule.equity"
                  :disabled="isDisabled || !canEdit()"
                  class="form-control non-border"
                  placeholder="Chọn nguồn liên kết dữ liệu"
                >
                  <el-option label="Thông tin chung" :value="1" />
                  <el-option label="Báo cáo kết quả kinh doanh" :value="2" />
                  <el-option label="Bảng cân đối kế toán" :value="3" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-4" />
          <div class="col-4">
            <ValidationProvider
              v-slot="{ errors }"
              name="equity_criteria"
              :rules="{required: true}"
            >
              <el-form-item
                prop="equity_criteria"
                :error="messageError(tpBankRuleFields.equityCriteria, errors[0])"
              >
                <label for="equity_criteria" />
                <el-select
                  id="equity_criteria"
                  v-model="tpBankRule.equityCriteria"
                  :disabled="isDisabled || !canEdit()"
                  multiple
                  class="form-control non-border"
                  placeholder="Chọn tiêu chí liên kết dữ liệu"
                >
                  <el-option label="BS_0410C - Vốn chủ sở hữu" :value="1" />
                  <el-option label="BS_0411C - Vốn chủ sở hữu" :value="2" />
                  <el-option label="BS_0412C - Vốn chủ sở hữu" :value="3" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <list-config-editable
              v-if="!isDisabled"
              :config-scale-point-list.sync="tpBankRule.configScalePointList"
              :can-edit="canEdit()"
            />
            <list-config
              v-else
              :config-scale-point-list.sync="tpBankRule.configScalePointList"
            />
          </div>
        </div>
        <div v-if="tpBankRule.status === 3 || tpBankRule.status === 4" class="row">
          <div class="col-12">
            <el-form-item>
              <label for="reason">Lý do từ chối <span class="text-danger">*</span></label>
              <el-input
                id="reason"
                v-model="tpBankRule.reason"
                disabled
                :rows="2"
                type="textarea"
              />
            </el-form-item>
          </div>
        </div>
      </el-form>
      <div class="mt-5 text-center">
        <el-button class="me-3" type="primary" plain @click="closePage">ĐÓNG</el-button>
        <el-button v-if="!isDisabled && canEdit()" class="me-3" type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="tpBankRule.id && !isDisabled && canEdit()" type="success" plain @click="handleApply">TRÌNH DUYỆT</el-button>
      </div>
    </ValidationObserver>
    <apply-rule
      :dialog-apply-visible.sync="dialogApplyVisible"
      :rule.sync="tpBankRule"
    />
  </div>
</template>

<script>

import { ValidationObserver, ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'
import { createTPBankRule, getTPBankRule, updateTPBankRule } from '@/api/tpbank-rule'
import baseCommon from '@/utils/base-common'
import ApplyRule from '@/views/parameter-management/scale-classification/tpbank-rule/components/apply-rule'
import ListConfigEditable from '@/views/parameter-management/scale-classification/tpbank-rule/components/list-config-editable'
import ListConfig from '@/views/parameter-management/scale-classification/tpbank-rule/components/list-config'

export default {
  name: 'ModifyRule',
  components: { ListConfigEditable, ApplyRule, ValidationObserver, ValidationProvider, ListConfig },
  mixins: [BaseValidate, baseCommon],
  data() {
    return {
      tpBankRule: {
        name: '',
        sales: null,
        salesCriteria: [],
        equity: null,
        equityCriteria: [],
        configScalePointList: [],
        status: 1,
        reason: ''
      },
      dialogApplyVisible: false,
      isDisabled: false
    }
  },
  created() {
    const id = this.$route.params.id
    this.isDisabled = this.$route.params.disabled
    if (id) {
      getTPBankRule(id, null).then(response => {
        const data = response.data
        this.tpBankRule = data
        this.tpBankRule.salesCriteria = JSON.parse(data.salesCriteria)
        this.tpBankRule.equityCriteria = JSON.parse(data.equityCriteria)
        const isCopy = this.$route.params.isCopy
        if (isCopy) {
          this.tpBankRule.id = null
          this.tpBankRule.name += ' - Copy'
          this.tpBankRule.status = 1
        }
        this.notifyInfo('Thông tin', response.message)
      })
    }
  },
  methods: {
    closePage() {
      this.$router.back()
    },
    canEdit() {
      return this.tpBankRule.status === 1 || this.tpBankRule.status === 3 || this.tpBankRule.status === 4
    },
    submit() {
      if (this.tpBankRule.id) {
        this.updateData()
      } else {
        this.createData()
      }
    },
    updateData() {
      this.showLoading()
      const equityCriteria = this.tpBankRule.equityCriteria
      const salesCriteria = this.tpBankRule.salesCriteria
      const data = Object.assign({}, this.tpBankRule)
      data.equityCriteria = JSON.stringify(equityCriteria)
      data.salesCriteria = JSON.stringify(salesCriteria)
      if (data.status === 3) {
        data.status = 4
      }
      updateTPBankRule(data.id, data).then(response => {
        this.notifyInfo('Thông tin', response.message)
        this.hideLoading()
      }).catch(() => {
        this.hideLoading()
      })
    },
    createData() {
      this.showLoading()
      const equityCriteria = this.tpBankRule.equityCriteria
      const salesCriteria = this.tpBankRule.salesCriteria
      const data = Object.assign({}, this.tpBankRule)
      data.equityCriteria = JSON.stringify(equityCriteria)
      data.salesCriteria = JSON.stringify(salesCriteria)
      createTPBankRule(data).then(response => {
        this.notifyInfo('Thông tin', response.message)
        this.hideLoading()
      }).catch(() => {
        this.hideLoading()
      })
    },
    handleApply() {
      this.dialogApplyVisible = true
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
