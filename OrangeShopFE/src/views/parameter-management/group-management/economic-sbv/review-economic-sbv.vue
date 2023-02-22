<template>
  <el-dialog
    title="Trình duyệt ngành kinh tế SBV"
    :visible.sync="dialogReviewVisible"
    :before-close.sync="closeDialog"
    :center="true"
    :width="'50%'"
    :show-close="false"
  >
    <h3 slot="title" class="title mt-3"><strong>{{ renderTitle() }}</strong></h3>
    <div class="text-center">
      <h5 class="text-danger">{{ renderContent() }}</h5>
    </div>
    <div slot="footer" class="dialog-footer mt-5">
      <el-button type="info" plain @click="closeDialog">Đóng</el-button>
      <el-button v-if="title==='reviewlist'" type="warning" plain @click="reviewData">Đồng ý</el-button>
      <el-button v-if="title==='acceptlist'" type="warning" plain @click="acceptData">Đồng ý</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { approveSBV } from '@/api/economic-sbv'
import baseCommon from '@/utils/base-common'
export default {
  name: 'ReviewEconomicSBV',
  mixins: [baseCommon],
  props: {
    dialogReviewVisible: {
      type: Boolean,
      default: false
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
      if (this.title === 'reviewlist') {
        return 'Trình duyệt ngành kinh tế SBV'
      } else if (this.title === 'acceptlist') {
        return 'Phê duyệt ngành kinh tế SBV'
      }
    },
    renderContent() {
      if (this.title === 'reviewlist') {
        return 'Bạn có trình duyệt (những) ngành kinh tế SBV này không ?'
      } else if (this.title === 'acceptlist') {
        return 'Bạn có chắc chắn muốn Phê duyệt (những) ngành kinh tế SBV này không ?'
      }
    },
    closeDialog() {
      this.$emit('update:dialogReviewVisible', false)
    },
    reviewData() {
      this.list.forEach(element => {
        element.status = 3
        // console.log(element)
        approveSBV(element).then(response => {
          this.$emit('update:dialogReviewVisible', false)
          this.$parent.economicSBVSelected = []
          this.$parent.getList()

          this.notifySuccess('Thông tin', response.message)
        })
      })
      //
    },
    acceptData() {
      this.list.forEach(element => {
        element.status = 6
        console.log(element)
        approveSBV(element).then(response => {
          this.$emit('update:dialogReviewVisible', false)
          this.$parent.economicSBVSelected = []
          this.$parent.getList()

          this.notifySuccess('Thông tin', response.message)
        })
      })
      //
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
