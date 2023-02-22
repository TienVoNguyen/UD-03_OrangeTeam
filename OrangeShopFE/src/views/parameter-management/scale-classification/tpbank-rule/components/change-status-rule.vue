<template>
  <el-dialog
    :visible.sync="dialogChangeStatusVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'40%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Thay đổi trạng thái</strong></h3>
    <div class="text-center">
      <p class="text-danger">Bạn có chắc chắn muốn thay đổi trạng thái hoạt động của Bộ quy tắc này không?</p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button type="warning" plain @click="changeStatus()">ĐỒNG Ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

import { getPageTPBankRule, updateTPBankRuleStatus } from '@/api/tpbank-rule'
import baseCommon from '@/utils/base-common'

export default {
  name: 'ChangeStatusRule',
  mixins: [baseCommon],
  props: {
    dialogChangeStatusVisible: {
      type: Boolean,
      default: false
    },
    rule: {
      type: Object,
      default: null
    },
    list: {
      type: Array,
      default: null
    }
  },
  methods: {
    changeStatus() {
      let status = 5
      if (this.rule.status === 5) {
        status = 6
      }
      const params = {
        id: this.rule.id,
        status: status
      }
      updateTPBankRuleStatus(params).then(() => {
        this.notifyInfo('Thông tin', 'Thay đổi trạng thái quy tắc TPBank thành công')
        getPageTPBankRule({}).then(response => {
          this.$emit('update:list', response.data.result)
        })
        this.closeDialog()
      })
    },
    closeDialog() {
      this.$emit('update:dialogChangeStatusVisible', false)
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
