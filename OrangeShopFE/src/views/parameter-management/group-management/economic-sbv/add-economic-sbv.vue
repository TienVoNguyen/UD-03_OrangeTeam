<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      title="Thêm mới ngành kinh tế SBV"
      :visible.sync="dialogFormVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>{{ title }}</strong></h3>
      <el-form ref="dataForm" :rules="rules" :model="current" label-position="left" label-width="70px">
        <div class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="codeSBV"
              :rules="{required: true, max: 20}"
            >
              <el-form-item
                prop="codeSBV"
                :error="messageError(economic_sbvFields.codeSBV, errors[0])"
              >
                <label>Mã ngành kinh tế SBV <span class="text-danger">*</span></label>
                <el-input id="codeSBV" v-model="current.codeSBV" :disabled="dialogStatus !== 'create'" value="" class="form-control" placeholder="Nhập mã ngành kinh tế SBV " />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <div class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="nameSBV"
              :rules="{required: true, max: 100}"
            >
              <el-form-item
                prop="nameSBV"
                :error="messageError(economic_sbvFields.nameSBV, errors[0])"
              >
                <label>Tên ngành kinh tế SBV<span class="text-danger">*</span></label>
                <el-input id="nameSBV" v-model="current.nameSBV" value="" :disabled="current.status === 4" class="form-control" placeholder="Nhập tên ngành kinh tế SBV " />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div v-if="current.status === 4" class="row">
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
                <el-input id="requestChange" v-model="current.requestChange" value="" class="form-control" placeholder="Yêu cầu điều chỉnh" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="info" plain @click="closeDialog">Đóng</el-button>
        <el-button type="warning" plain @click="handleSubmit(submit)">Đồng ý</el-button>
        <el-button v-if="dialogStatus !== 'create'" type="success" plain @click="handleReviewDialog">Trình duyệt</el-button>
        <!-- <button v-if="dialogStatus !== 'create'" class="btn btn-outline-success" @click="handleReviewDialog">Trình duyệt</button> -->
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import baseCommon from '@/utils/base-common'
import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import { createSBV, updateSBV, approveSBV } from '@/api/economic-sbv'
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
  name: 'AddEconomicSBV',
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
    }
  },
  data() {
    return {
      rules: {
        // name: [{ required: true, message: 'Name is required', trigger: 'change' }],
        // slug: [{ required: true, message: 'Slug is required', trigger: 'change' }]
      },
      objAdd: {
        codeSBV: null,
        nameSBV: null
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
      } else {
        this.updateData()
      }
    },
    handleReviewDialog() {
      this.current.status = 3
      approveSBV(this.current).then(response => {
        this.$emit('update:dialogFormVisible', false)
        this.$parent.getList()

        this.notifySuccess('Thông tin', response.message)
      })
    },
    createData() {
      this.current.status = 1
      this.current.createdBy = 'Tung_NT'
      createSBV(this.current).then(response => {
        this.$emit('update:dialogFormVisible', false)
        this.$parent.getList()
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
      if (this.current.status === 4) {
        approveSBV(this.current).then(response => {
          this.$emit('update:dialogFormVisible', false)
          this.$parent.getList()
          this.notifySuccess('Thông tin', response.message)
        })
      } else {
        updateSBV(this.current).then(response => {
          this.$emit('update:dialogFormVisible', false)
          this.$parent.getList()
          this.notifySuccess('Thông tin', response.message)
        })
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
</style>
