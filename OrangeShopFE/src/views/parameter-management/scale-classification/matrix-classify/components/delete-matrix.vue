<template>
  <el-dialog
    :visible.sync="dialogDeleteVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'40%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>{{ renderTitle() }}</strong></h3>
    <div class="text-center">
      <p class="text-danger"> {{ renderContent() }} </p>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
      <el-button v-if="title==='delete'" type="warning" plain @click="deleteData">Đồng ý</el-button>
      <el-button v-if="title==='approve'" type="warning" plain @click="approveData">Đồng ý</el-button>
      <el-button v-if="title==='hotchange'" type="warning" plain @click="hotChangeData">Đồng ý</el-button>
    </div>
  </el-dialog>
</template>

<script>

import baseCommon from '@/utils/base-common'
import { deleteMatrix, updateMatrix } from '@/api/matrix-classify'

export default {
  name: 'DeleteMatrix',
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
    title: {
      type: String,
      default: null
    }
  },
  methods: {
    renderTitle() {
      if (this.title === 'delete') {
        return 'Xóa ma trận'
      } else if (this.title === 'approve') {
        return 'Trình duyệt ma trận'
      } else if (this.title === 'hotchange') {
        return 'Thay đổi trạng thái'
      }
    },
    renderContent() {
      if (this.title === 'delete') {
        return 'Bạn có chắc chắn muốn xóa ma trận này không ?'
      } else if (this.title === 'approve') {
        return 'Bạn có chắc chắn muốn trình duyệt ma trận này không ?'
      } else if (this.title === 'hotchange') {
        return 'Bạn có chắc chắn muốn thay đổi trạng thái hoạt động của ma trận này không ?'
      }
    },
    closeDialog() {
      this.$emit('update:dialogDeleteVisible', false)
    },
    deleteData() {
      console.log(this.temp)
      deleteMatrix(this.temp.id).then(response => {
        var name = response.data.nameMatrix
        this.$emit('update:dialogDeleteVisible', false)

        this.notifySuccess('Thông tin', 'Xóa ma trận ' + name + ' thành công')
        this.$parent.getListMatrix()
      })
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    },
    approveData() {
      // console.log(this.temp)
      this.temp.status = 2
      updateMatrix(this.temp).then(response => {
        this.$emit('update:dialogDeleteVisible', false)
        this.notifySuccess('Thông tin', 'Trình duyệt ma trận ' + response.data.nameMatrix + ' thành công')
        this.$parent.getListMatrix()
      })
    },
    hotChangeData() {
      if (this.temp.status === 5) {
        this.temp.status = 6
      } else if (this.temp.status === 6) {
        this.temp.status = 7
      } else if (this.temp.status === 7) {
        this.temp.status = 6
      }
      console.log(this.temp)
      updateMatrix(this.temp).then(response => {
        this.$emit('update:dialogDeleteVisible', false)
        this.notifySuccess('Thông tin', 'Phê duyệt ma trận ' + response.data.nameMatrix + ' thành công')
        this.$parent.getListMatrix()
      })
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
