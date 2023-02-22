<template>
  <el-dialog
    :visible.sync="dialogChangeStatusVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'30%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Thay đổi trạng thái</strong></h3>
    <div class="text-center">
      <p class="text-danger">Bạn có chắc chắn muốn thay đổi trạng thái ngành kinh tế FCC này không ?</p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button type="warning" plain @click="changeStatus">ĐỒNG Ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

import { changeStatusEconomicFCC, getPageEconomicFCC } from '@/api/economic-fcc'
import BaseCommon from '@/utils/base-common'

export default {
  name: 'ChangeStatusFcc',
  mixins: [BaseCommon],
  props: {
    dialogChangeStatusVisible: {
      type: Boolean,
      default: false
    },
    temp: {
      type: Object,
      default: null
    },
    list: {
      type: Array,
      default: null
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogChangeStatusVisible', false)
    },
    changeStatus() {
      changeStatusEconomicFCC(this.temp.id, this.temp).then(() => {
        getPageEconomicFCC({}).then(response => {
          this.$emit('update:list', response.data.result)
        })
      })
      this.notifyInfo('Thông tin', 'Thay đổi trạng thái ngành kinh tế FCC thành công')
      this.closeDialog()
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
