<template>
  <el-dialog
    title="Xóa ngành kinh tế SBV"
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
      <el-button v-if="title==='hotchange'" type="warning" plain @click="approveData">Đồng ý</el-button>

    </div>
  </el-dialog>
</template>

<script>
import baseCommon from '@/utils/base-common'
import { deleteSBV, approveSBV } from '@/api/economic-sbv'
export default {
  name: 'DeleteEconomicSBV',
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
      if (this.title === 'delete') {
        return 'Xóa ngành kinh tế SBV'
      } else if (this.title === 'accept' || this.title === 'acceptlist') {
        return 'Phê duyệt ngành kinh tế SBV'
      } else if (this.title === 'hotchange') {
        return 'Thay đổi trạng thái'
      }
    },
    renderContent() {
      if (this.title === 'delete') {
        return 'Bạn có chắc chắn muốn xóa ngành kinh tế SBV này không ?'
      } else if (this.title === 'accept' || this.title === 'acceptlist') {
        return 'Bạn có chắc chắn muốn Phê duyệt (những) ngành kinh tế SBV này không ?'
      } else if (this.title === 'hotchange') {
        return 'Bạn có chắc chắn muốn thay đổi trạng thái ngành kinh tế SBV này không ?'
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
      approveSBV(this.current).then(response => {
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
    deleteData() {
      //
      deleteSBV(this.current.id).then(response => {
        var nameSBV = response.data.nameSBV
        this.$emit('update:dialogDeleteVisible', false)

        this.notifySuccess('Thông tin', 'Xóa ngành kinh tế ' + nameSBV + ' thành công')
        this.$parent.getList()
      })
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
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
