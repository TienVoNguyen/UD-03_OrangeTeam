<template>
  <el-dialog
    :visible.sync="dialogApproveVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'40%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Phê duyệt ngành kinh tế FCC</strong></h3>
    <div class="text-center">
      <p class="text-danger">Bạn có chắc chắn muốn phê duyệt (những) ngành kinh tế FCC này không?</p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button type="warning" plain @click="approveFcc()">ĐỒNG Ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

export default {
  name: 'ApproveFcc',
  props: {
    dialogApproveVisible: {
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
    approveFcc() {
      // applyEconomicFCC(this.economicFccSelected).then(() => {
      //   if (this.isCheckAll) {
      //     this.$emit('update:is-check-all', false)
      //   }
      //   getAllEconomicFCC({}).then(response => {
      //     this.$emit('update:list', response.data.result)
      //   })
      //   this.$notify.success({
      //     title: 'Success',
      //     message: 'Phê duyệt ngành kinh tế FCC thành công',
      //     showClose: false
      //   })
      //   this.closeDialog()
      // })
    },
    closeDialog() {
      this.$emit('update:dialogApproveVisible', false)
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
