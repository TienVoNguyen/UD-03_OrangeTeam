<template>
  <el-dialog
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
import { updateRule } from '@/api/rule-change-rank'
import baseCommon from '@/utils/base-common'
export default {
  name: 'ReviewRuleChangeRank',
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
    },
    type: {
      type: Number,
      default: null
    }
  },
  methods: {
    renderTitle() {
      if (this.title === 'reviewlist' && this.type === 1) {
        return 'Trình duyệt quy tắc tăng hạng'
      } else if (this.title === 'reviewlist' && this.type === 2) {
        return 'Trình duyệt quy tắc giảm hạng'
      } else if (this.title === 'acceptlist' && this.type === 1) {
        return 'Phê duyệt quy tắc tăng hạng'
      } else if (this.title === 'acceptlist' && this.type === 2) {
        return 'Phê duyệt quy tắc giảm hạng'
      }
    },
    renderContent() {
      if (this.title === 'reviewlist' && this.type === 1) {
        return 'Bạn có chắc chắn muốn Trình duyệt (những) quy tắc tăng hạng này không ?'
      } else if (this.title === 'reviewlist' && this.type === 2) {
        return 'Bạn có chắc chắn muốn Trình duyệt (những) quy tắc giảm hạng này không ?'
      } else if (this.title === 'acceptlist' && this.type === 1) {
        return 'Bạn có chắc chắn muốn Phê duyệt (những) quy tắc tăng hạng này không ?'
      } else if (this.title === 'acceptlist' && this.type === 2) {
        return 'Bạn có chắc chắn muốn Phê duyệt (những) quy tắc giảm hạng này không ?'
      }
    },
    closeDialog() {
      this.$emit('update:dialogReviewVisible', false)
    },
    reviewData() {
      if (this.list.length > 0) {
        this.list.forEach(element => {
          element.status = 2
          // console.log(element)
          updateRule(element).then(response => {
            this.$emit('update:dialogReviewVisible', false)
            this.$parent.economicSBVSelected = []
            if (this.type === 1) {
              this.$parent.activeRankUp()
              this.notifySuccess('Thông tin', 'Trình duyệt quy tắc tăng hạng thành công')
            } else if (this.type === 2) {
              this.$parent.activeRankDown()
              this.notifySuccess('Thông tin', 'Trình duyệt quy tắc giảm hạng thành công')
            }
          })
        })
      } else {
        this.notifyError('Thông tin', 'Vui lòng chọn quy tắc cần trình duyệt')
      }
      //
    },
    acceptData() {
      this.list.forEach(element => {
        element.status = 6
        console.log(element)
        updateRule(element).then(response => {
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
