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
      <h5 class="text-danger">{{ renderContent() }}</h5>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="info" plain @click="closeDialog">Đóng</el-button>
      <el-button v-if="title==='delete'" type="warning" plain @click="deleteData">Đồng ý</el-button>
      <el-button v-if="title==='accept'" type="warning" plain @click="approveData">Đồng ý</el-button>
      <el-button v-if="title==='acceptlist'" type="warning" plain @click="approveDataList">Đồng ý</el-button>
      <el-button v-if="title==='hotchange'" type="warning" plain @click="hotChangeData">Đồng ý</el-button>

    </div>
  </el-dialog>
</template>

<script>
import baseCommon from '@/utils/base-common'
import { deleteRule, updateRule } from '@/api/rule-change-rank'
export default {
  name: 'DeleteConfigParameterKHDN',
  mixins: [baseCommon],
  props: {
    current: {
      type: Object,
      default: null
    },
    dialogDeleteVisible: {
      type: Boolean,
      default: false
    },
    list: {
      type: Array,
      default: null
    },
    index: {
      type: Number,
      default: null
    },
    title: {
      type: String,
      default: null
    }
  },
  methods: {
    renderTitle() {
      if (this.current.type === 1 && this.title === 'delete') {
        return 'Xóa quy tắc thăng hạng'
      } else if (this.current.type === 2 && this.title === 'delete') {
        return 'Xóa quy tắc giảm hạng'
      } else if (this.current.type === 1 && this.title === 'hotchange') {
        return 'Thay đổi trạng thái'
      } else if (this.current.type === 2 && this.title === 'hotchange') {
        return 'Thay đổi trạng thái'
      }
    },
    renderContent() {
      if (this.current.type === 1 && this.title === 'delete') {
        return 'Bạn có chắc chắn muốn xóa quy tắc thăng hạng này không ?'
      } else if (this.current.type === 2 && this.title === 'delete') {
        return 'Bạn có chắc chắn muốn xóa quy tắc giảm hạng này không ?'
      } else if (this.current.type === 1 && this.title === 'hotchange') {
        return 'Bạn có chắc chắn muốn thay đổi trạng thái của lý do tăng hạng này không ?'
      } else if (this.current.type === 2 && this.title === 'hotchange') {
        return 'Bạn có chắc chắn muốn thay đổi trạng thái của lý do giảm hạng này không ?'
      }
    },
    closeDialog() {
      this.$emit('update:dialogDeleteVisible', false)
    },
    approveDataList() {
      var data_export = []
      this.list.forEach((element, index) => {
        if (this.$refs[`checkbox${index}`].checked === true) {
          console.log(element)
          data_export.push(element)
        }
      })
    },
    approveData() {
      updateRule(this.current).then(response => {
        this.$emit('update:dialogDeleteVisible', false)
        this.$parent.getList()
        this.notifySuccess('Thông tin', 'Phê duyệt thành công')
      })
      var data_export = []
      this.list.forEach((element, index) => {
        // this.$refs[`checkbox${index}`].checked = this.isCheckAll
        if (this.$refs[`checkbox${index}`].checked === true) {
          console.log(element)
          data_export.push(element)
        }
      })
    },
    hotChangeData() {
      if (this.current.status === 5 || this.current.status === 7) {
        this.current.status = 6
      } else if (this.current.status === 6) {
        this.current.status = 7
      }
      // console.log(this.current)
      updateRule(this.current).then(response => {
        this.$emit('update:dialogDeleteVisible', false)
        if (this.current.type === 1) {
          this.notifySuccess('Thông tin', 'Phê duyệt quy tắc thăng hạng thành công')
          this.$parent.activeRankUp()
        } else if (this.current.type === 2) {
          this.notifySuccess('Thông tin', 'Phê duyệt quy tắc giảm hạng thành công')
          this.$parent.activeRankDown()
        }
      })
    },
    deleteData() {
      //
      deleteRule(this.current.id).then(response => {
        this.$emit('update:dialogDeleteVisible', false)

        if (response.data.type === 1) {
          this.notifySuccess('Thông tin', 'Xóa quy tắc thăng hạng thành công')
          this.$parent.activeRankUp()
        } else if (response.data.type === 2) {
          this.notifySuccess('Thông tin', 'Xóa quy tắc giảm hạng thành công')
          this.$parent.activeRankDown()
        }
      })
      setTimeout(() => {
        this.listLoading = false
      }, 1 * 1000)
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
