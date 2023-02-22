<template>
  <el-dialog
    :visible.sync="dialogAcceptVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'40%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Trình duyệt ngành kinh tế FCC</strong></h3>
    <div class="text-center">
      <p class="text-danger">Bạn có chắc chắn muốn trình duyệt (những) ngành kinh tế FCC này không?</p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button type="warning" plain @click="approve()">ĐỒNG Ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

import { approveEconomicFCC, getAllEconomicFCC } from '@/api/economic-fcc'
import BaseCommon from '@/utils/base-common'

export default {
  name: 'AcceptFcc',
  mixins: [BaseCommon],
  props: {
    dialogAcceptVisible: {
      type: Boolean,
      default: false
    },
    isCheckAll: {
      type: Boolean,
      default: false
    },
    economicFccSelected: {
      type: Array,
      default: null
    },
    list: {
      type: Array,
      default: null
    }
  },
  methods: {
    approve() {
      approveEconomicFCC(this.economicFccSelected).then(() => {
        if (this.isCheckAll) {
          this.$emit('update:is-check-all', false)
        }
        getAllEconomicFCC({}).then(response => {
          this.$emit('update:list', response.data.result)
        })
        this.notifyInfo('Thông tin', 'Phê duyệt ngành kinh tế FCC thành công')
        this.closeDialog()
      })
    },
    closeDialog() {
      this.$emit('update:dialogAcceptVisible', false)
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
