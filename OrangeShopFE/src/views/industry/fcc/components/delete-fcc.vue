<template>
  <el-dialog
    :visible.sync="dialogDeleteVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'30%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Xóa ngành kinh tế FCC</strong></h3>
    <div class="text-center">
      <p class="text-danger">Bạn có chắc chắn muốn xóa ngành kinh tế FCC này không?</p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button type="warning" plain @click="deleteData">ĐỒNG Ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

import { deleteEconomicFCC, getPageEconomicFCC } from '@/api/economic-fcc'
import BaseCommon from '@/utils/base-common'

export default {
  name: 'DeleteFcc',
  mixins: [BaseCommon],
  props: {
    dialogDeleteVisible: {
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
    },
    pagination: {
      type: Object,
      default: null
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogDeleteVisible', false)
    },
    deleteData() {
      const params = {
        economicFCCCode: this.temp.economicFCCCode
      }
      deleteEconomicFCC(this.temp.id, params).then(() => {
        getPageEconomicFCC({}).then(response => {
          const pagination = {
            pageNumber: response.data.pageNumber,
            pageSize: 10,
            totalItems: response.data.totalItems,
            totalPages: response.data.totalPages
          }
          this.$emit('update:list', response.data.result)
          this.$emit('update:pagination', pagination)
        })
        this.notifyInfo('Thông tin', 'Xóa ngành kinh tế FCC thành công')
      })
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
