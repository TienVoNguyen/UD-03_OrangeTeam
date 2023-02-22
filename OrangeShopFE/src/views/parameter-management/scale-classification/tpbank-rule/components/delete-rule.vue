<template>
  <el-dialog
    :visible.sync="dialogDeleteVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'30%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Xóa bộ quy tắc </strong></h3>
    <div class="text-center">
      <p class="text-danger">Bạn có chắc chắn muốn xóa bộ quy tắc  này không?</p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button class="me-3" type="primary" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button type="warning" plain @click="deleteData">ĐỒNG Ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

import { deleteTPBankRule, getPageTPBankRule } from '@/api/tpbank-rule'
import baseCommon from '@/utils/base-common'

export default {
  name: 'DeleteRule',
  mixins: [baseCommon],
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
      this.temp.isDeleted = 1
      deleteTPBankRule(this.temp.id, this.temp).then(() => {
        const params = {
          isDeleted: 0,
          pageIndex: this.pagination.pageNumber,
          pageSize: this.pagination.pageSize
        }
        getPageTPBankRule(params).then(response => {
          const pagination = {
            pageNumber: response.data.pageNumber,
            totalItems: response.data.totalItems,
            totalPages: response.data.totalPages
          }
          this.$emit('update:list', response.data.result)
          this.$emit('update:pagination', pagination)
        })
        this.notifyInfo('Thông tin', 'Xóa quy tắc TPBank thành công')
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
