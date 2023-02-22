<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      :visible.sync="dialogFormVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>{{ renderTitle() }}</strong></h3>
      <el-form ref="dataForm" :rules="rules" :model="current" label-position="left" label-width="70px">
        <div class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="content"
              :rules="{required: true, max: 500}"
            >
              <el-form-item
                prop="content"
                :error="messageError(ruleChangeRankFields.content, errors[0])"
              >
                <label v-if="current.type===1">Lý do tăng hạng<span class="text-danger">*</span></label>
                <label v-if="current.type===2">Lý do giảm hạng<span class="text-danger">*</span></label>
                <el-input id="content" v-model="current.content" :disabled="dialogStatus === 'approve' || dialogStatus==='view'" type="textarea" value="" class="form-control" :placeholder="(current.type===1)?'Nhập lý do tăng hạng':'Nhập lý do giảm hạng'" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <div v-if="flag" class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="requestChange"
              rules="required"
            >
              <el-form-item
                prop="requestChange"
                :error="messageError(economic_sbvFields.requestChange, errors[0])"
              >
                <label>Yêu cầu điều chỉnh <span class="text-danger">*</span></label>
                <el-input id="requestChange" v-model="current.requestChange" :disabled="((dialogStatus === 'approve' || dialogStatus==='view') && !flag) || current.status === 3" value="" class="form-control" placeholder="Yêu cầu điều chỉnh" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
      </el-form>

      <div slot="footer" class="dialog-footer  text-center">
        <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
        <el-button v-if="(dialogStatus === 'create' || dialogStatus === 'update')&& !flag" type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="(dialogStatus === 'create' || dialogStatus === 'update')&& !flag" :disabled="dialogStatus === 'create'" type="success" plain @click="handleReviewDialog">TRÌNH DUYỆT</el-button>

        <el-button v-if="flag" type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
        <el-button v-if="(dialogStatus === 'approve')&& !flag" type="success" plain @click="handleSubmit(submit)">PHÊ DUYỆT</el-button>
        <el-button v-if="(dialogStatus === 'approve')&& !flag" type="danger" plain @click="handleRejectFrontEnd(submit)">TỪ CHỐI</el-button>
        <!-- <button v-if="dialogStatus !== 'create'" class="btn btn-outline-success" @click="handleReviewDialog">Trình duyệt</button> -->
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import baseCommon from '@/utils/base-common'
import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import { createRule, updateRule } from '@/api/rule-change-rank'
import { required } from 'vee-validate/dist/rules'
import { extend } from 'vee-validate'
extend('required', required)
extend('max', {
  validate: (value, args) => {
    return value.length <= args.length
  },
  params: ['length'],
  message: ' không được vượt quá {length} kí tự'
})

export default {
  name: 'ModifyRuleChangeRank',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate, baseCommon],
  props: {
    current: {
      type: Object,
      default: null
    },
    dialogFormVisible: {
      type: Boolean,
      default: false
    },
    dialogStatus: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      default: ''
    },
    list: {
      type: Array,
      default: null
    },
    flag: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      rules: {
        // name: [{ required: true, message: 'Name is required', trigger: 'change' }],
        // slug: [{ required: true, message: 'Slug is required', trigger: 'change' }]
      }
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogFormVisible', false)
      this.$nextTick(() => {
        this.$refs.observer.reset()
      })
    },
    submit() {
      if (this.dialogStatus === 'create') {
        this.createData()
      } else if (this.dialogStatus === 'update') {
        this.updateData()
      } else if ((this.dialogStatus === 'approve') && !this.flag) {
        this.approveData()
      } else if (this.flag) {
        this.rejectData()
      }
    },
    handleReviewDialog() {
      this.current.status = 2
      updateRule(this.current).then(response => {
        this.$emit('update:dialogFormVisible', false)
        if (response.data.type === 1) {
          this.$parent.activeRankUp()
          this.notifySuccess('Thông tin', 'Trình duyệt quy tắc tăng hạng thành công')
        } else if (response.data.type === 2) {
          this.$parent.activeRankDown()
          this.notifySuccess('Thông tin', 'Trình duyệt quy tắc giảm hạng thành công')
        }
      })
    },
    createData() {
      this.current.status = 1
      this.current.createdBy = 'Tung_NT'
      console.log(this.current)
      createRule(this.current).then(response => {
        this.$emit('update:dialogFormVisible', false)
        if (response.data.type === 1) {
          this.$parent.activeRankUp()
        } else if (response.data.type === 2) {
          this.$parent.activeRankDown()
        }
        this.notifySuccess('Thông tin', response.message)
      }).catch((error) => {
        if (error.response) {
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })
    },
    updateData() {
      //
      if (this.current.status === 3) {
        this.current.status = 4
        updateRule(this.current).then(response => {
          this.$emit('update:dialogFormVisible', false)
          if (response.data.type === 1) {
            this.$parent.activeRankUp()
          } else if (response.data.type === 2) {
            this.$parent.activeRankDown()
          }
          this.notifySuccess('Thông tin', response.message)
        })
      } else {
        updateRule(this.current).then(response => {
          this.$emit('update:dialogFormVisible', false)
          if (response.data.type === 1) {
            this.$parent.activeRankUp()
          } else if (response.data.type === 2) {
            this.$parent.activeRankDown()
          }
          this.notifySuccess('Thông tin', response.message)
        })
      }
    },
    approveData() {
      this.current.status = 5
      updateRule(this.current).then(response => {
        this.$emit('update:dialogFormVisible', false)
        if (response.data.type === 1) {
          this.$parent.activeRankUp()
          this.notifySuccess('Thông tin', 'Phê duyệt quy tắc tăng hạng thành công')
        } else if (response.data.type === 2) {
          this.$parent.activeRankDown()
          this.notifySuccess('Thông tin', 'Phê duyệt quy tắc giảm hạng thành công')
        }
      })
    },
    handleRejectFrontEnd() {
      this.flag = true
    },
    rejectData() {
      this.current.status = 3
      updateRule(this.current).then(response => {
        this.$emit('update:dialogFormVisible', false)
        if (response.data.type === 1) {
          this.$parent.activeRankUp()
          this.notifySuccess('Thông tin', 'Phê duyệt quy tắc tăng hạng thành công')
        } else if (response.data.type === 2) {
          this.$parent.activeRankDown()
          this.notifySuccess('Thông tin', 'Phê duyệt quy tắc giảm hạng thành công')
        }
      })
    },
    renderTitle() {
      if (this.title === 'rankUp' && this.dialogStatus === 'create') {
        return 'Thêm mới quy tắc tăng hạng'
      } else if (this.title === 'rankDown' && this.dialogStatus === 'create') {
        return 'Thêm mới quy tắc giảm hạng'
      } else if (this.title === 'rankUp' && this.dialogStatus === 'update') {
        return 'Chỉnh sửa quy tắc tăng hạng'
      } else if (this.title === 'rankDown' && this.dialogStatus === 'update') {
        return 'Chỉnh sửa quy tắc giảm hạng'
      } else if (this.title === 'rankUp' && this.dialogStatus === 'approve') {
        return 'Phê duyệt quy tắc tăng hạng'
      } else if (this.title === 'rankDown' && this.dialogStatus === 'approve') {
        return 'Phê duyệt quy tắc giảm hạng'
      } else if (this.title === 'rankUp' && this.dialogStatus === 'view') {
        return 'Xem thông tin quy tắc tăng hạng'
      } else if (this.title === 'rankDown' && this.dialogStatus === 'view') {
        return 'Xem thông tin quy tắc giảm hạng'
      }
    }
  }
}
</script>
<style scoped>
.form-control {
  border: 0;
  padding: 0;
}
.title {
  color: #5E2E86;
  text-align: center;
}
.custom-notification {
  background-color: greenyellow;
}
#content {
  height: 100px !important;
}
</style>
