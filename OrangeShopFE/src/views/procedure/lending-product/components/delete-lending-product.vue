<template>
  <el-dialog
    :visible.sync="dialogDeleteVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'30%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>Xóa loại hình sản phẩm</strong></h3>
    <div class="text-center">
      <h5 class="text-danger">Bạn có chắc muốn xóa loại hình sản phẩm?</h5>
    </div>
    <div slot="footer" class="dialog-footer">
      <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button>
      <button class="btn btn-outline-warning" @click="deleteData">Đồng ý</button>
    </div>
  </el-dialog>
</template>

<script>

export default {
  name: 'DeleteLendingProduct',
  props: {
    dialogDeleteVisible: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogDeleteVisible', false)
    },
    deleteData() {
      //
    }
  }
}
</script>
<style scoped>
.btn-outline-secondary {
  border-color: #C9D1D7;
  color: #C9D1D7;
}
.btn-outline-secondary:hover {
  background-color: #C9D1D7;
  border-color: #C9D1D7;
  color: #fff;
}
.btn-outline-secondary:active,
.btn-outline-secondary:focus {
  color: #fff;
  background-color: #C9D1D7;
  border-color: #C9D1D7;
  box-shadow: 0 0 0 0.2rem #C9D1D7 !important;
}
.btn-outline-warning {
  border-color: #F7B432;
  color: #F7B432;
}
.btn-outline-warning:hover {
  background-color: #F7B432;
  border-color: #F7B432;
  color: #fff;
}
.btn-outline-warning:active,
.btn-outline-warning:focus {
  color: #fff;
  background-color: #F7B432;
  border-color: #F7B432;
  box-shadow: 0 0 0 0.2rem #F7B432 !important;
}
.title {
  color: #5E2E86;
  text-align: center;
}
</style>
