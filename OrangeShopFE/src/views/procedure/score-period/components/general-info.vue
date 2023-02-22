<template>
  <el-form ref="scorePeriodForm" label-position="left" class="mt-3">
    <div class="row">
      <div class="col-12">
        <label class="label">I. Thông tin chung</label>
      </div>
    </div>
    <div class="row">
      <div class="col-4">
        <ValidationProvider
          v-slot="{ errors }"
          name="name"
          rules="required"
        >
          <el-form-item
            prop="name"
            :error="messageError(scorePeriodFields.name, errors[0])"
          >
            <label for="name">Kỳ chấm điểm <span class="text-danger">*</span></label>
            <el-input id="name" ref="name" v-model="scorePeriod.name" class="form-control non-border" placeholder="Nhập kỳ chấm điểm" :disabled="disabled" />
          </el-form-item>
        </ValidationProvider>
      </div>
      <div class="col-4">
        <el-form-item>
          <label>Thời gian tổ chức địa điểm</label>
          <div class="row">
            <div class="col-6">
              <el-input ref="startDate" v-model="scorePeriod.startDate" class="form-control non-border" placeholder="Từ ngày" onfocus="this.type='date'" onblur="if(this.value===''){this.type='text'}" :disabled="disabled" />
            </div>
            <div class="col-6">
              <el-input ref="endDate" v-model="scorePeriod.endDate" class="form-control non-border" placeholder="Đến ngày" onfocus="this.type='date'" onblur="if(this.value===''){this.type='text'}" :disabled="disabled" />
            </div>
          </div>
        </el-form-item>
      </div>
      <div class="col-4">
        <el-form-item>
          <label for="status">Trạng thái</label>
          <el-select id="status" v-model="status" value-key="status" class="form-control non-border" placeholder="---Tất cả---" :disabled="disabled">
            <el-option v-for="item in listStatus" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
      </div>
    </div>
  </el-form>
</template>

<script>
import { ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'

export default {
  name: 'GeneralInfo',
  components: { ValidationProvider },
  mixins: [BaseValidate],
  props: {
    disabled: {
      type: Boolean,
      default: false
    },
    scorePeriod: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      listStatus: [{
        label: 'Khóa',
        value: 2
      }, {
        label: 'Đang thực hiện',
        value: 1
      }],
      status: null
    }
  },
  created() {
    if (!this.scorePeriod) {
      this.scorePeriod = {
        name: '',
        startDate: '',
        endDate: '',
        status: null
      }
    } else {
      this.status = this.scorePeriod.status
    }
  },
  methods: {
    messageError(fieldName, error) {
      if (error === undefined) {
        return undefined
      }
      return fieldName + error
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
.label {
  color: #EF8C1A;
}
.non-border {
  border: 0;
  padding: 0;
}
</style>
