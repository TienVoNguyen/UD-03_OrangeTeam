<template>
  <el-dialog
    :visible.sync="dialogApplyVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'40%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Trình duyệt quy tắc TPBank</strong></h3>
    <div class="text-center">
      <p class="text-danger">Bạn có chắc chắn muốn trình duyệt quy tắc này không?</p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button type="warning" plain @click="apply()">ĐỒNG Ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

import { getTPBankRule, updateTPBankRuleStatus } from '@/api/tpbank-rule'
import baseCommon from '@/utils/base-common'

export default {
  name: 'ApplyRule',
  mixins: [baseCommon],
  props: {
    dialogApplyVisible: {
      type: Boolean,
      default: false
    },
    rule: {
      type: Object,
      default: null
    }
  },
  methods: {
    apply() {
      const params = {
        id: this.rule.id,
        status: 2
      }
      updateTPBankRuleStatus(params).then(() => {
        this.notifyInfo('Thông tin', 'Trình duyệt quy tắc TPBank thành công')
        getTPBankRule(params.id, {}).then(response => {
          const data = response.data
          data.salesCriteria = JSON.parse(data.salesCriteria)
          data.equityCriteria = JSON.parse(data.equityCriteria)
          this.$emit('update:rule', data)
        })
        this.closeDialog()
      })
    },
    closeDialog() {
      this.$emit('update:dialogApplyVisible', false)
    }
  }
}
</script>
<style scoped>
.title {
  color: #5E2E86;
  text-align: center;
}
</style>
