<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      title="Điều chỉnh hạng"
      :visible.sync="dialogFormVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>Thông tin điều chỉnh hạng</strong></h3>
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px">
        <div class="row">
          <div class="col-6">
            <ValidationProvider
              v-slot="{ errors }"
              name="rule_change"
              rules="required"
            >
              <el-form-item
                prop="ruleChange"
                :error="messageError(rank_adjustFields.rule_change, errors[0])"
              >
                <label for="rule">Quy tắc điều chỉnh <span class="text-danger">*</span> :</label>
                <el-select id="ruleChange" v-model="tempRank.ruleChange" value="" class="form-control" placeholder="--Chọn--">
                  <el-option v-for="item in rule" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
          <div class="col-6">
            <ValidationProvider>
              <el-form-item
                prop="new_rule"
              >
                <label>Hạng điều chỉnh mới</label>
                <el-input id="new_rule" v-model="tempRank.new_rule" value="" class="form-control" placeholder="Hạng điều chỉnh mới" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="causeChange"
              rules="required"
            >
              <el-form-item
                prop="causeChange"
                :error="messageError(rank_adjustFields.cause_change, errors[0])"
              >
                <label for="cause">Nguyên nhân điều chỉnh <span class="text-danger">*</span> :</label>
                <el-select id="causeChange" v-model="tempRank.causeChange" value="" class="form-control" placeholder="--Chọn--">
                  <el-option v-for="item in cause" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="reason_change"
              rules="required"
            >
              <el-form-item
                prop="reason_change"
                :error="messageError(rank_adjustFields.reason_change, errors[0])"
              >
                <label>Lý do <span class="text-danger">*</span>:</label>
                <el-input id="reason_change" v-model="tempRank.reason_change" value="" class="form-control" placeholder="Nhập lý do điều chỉnh hạng" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button>
        <button class="btn btn-outline-warning" @click="handleSubmit">Đồng ý</button>
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'

export default {
  name: 'UpdateRank',
  components: { ValidationObserver, ValidationProvider },
  mixins: [BaseValidate],
  props: {
    temp: {
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
    list: {
      type: Array,
      default: null
    },
    rule: {
      type: Array,
      default: null
    },
    cause: {
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
      tempRank: {
        ruleChange: null,
        new_ruleChange: null,
        causeChange: null,
        reason_change: null
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
    handleSubmit() {
      if (this.dialogStatus === 'create') {
        this.createData()
      } else {
        this.updateData()
      }
    },
    createData() {

    },
    updateData() {

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
.form-control {
  border: 0;
  padding: 0;
}
.title {
  color: #5E2E86;
  text-align: center;
}
</style>
