<template>
  <div class="app-container">
    <el-form ref="scorePeriodForm" label-position="left" class="mt-3">
      <div class="row">
        <div class="col-12">
          <label class="label">I. Tần suất</label>
          <div class="row">
            <div class="col-4">
              <div class="row">
                <div class="col-8">
                  <ValidationProvider
                    v-slot="{ errors }"
                    name="name"
                    rules="required"
                  >
                    <el-form-item
                      prop="name"
                      :error="messageError(scorePeriodFields.name, errors[0])"
                    >
                      <label for="name">Loại BCTC <span class="text-danger">*</span></label>
                      <el-input id="name" disabled value="Báo cáo thuế" class="form-control non-border" />
                    </el-form-item>
                  </ValidationProvider>
                </div>
                <div class="col-4">
                  <el-form-item
                    prop="name"
                  >
                    <label for="frequencyTaxReport">Tần suất</label>
                    <el-input id="frequencyTaxReport" ref="frequencyTaxReport" v-model="config.frequencyTaxReport" class="form-control non-border" placeholder="Nhập tần suất" />
                  </el-form-item>
                </div>
              </div>
            </div>
            <div class="col-4">
              <div class="row">
                <div class="col-8">
                  <ValidationProvider
                    v-slot="{ errors }"
                    name="name"
                    rules="required"
                  >
                    <el-form-item
                      prop="name"
                      :error="messageError(scorePeriodFields.name, errors[0])"
                    >
                      <label for="name">Loại BCTC <span class="text-danger">*</span></label>
                      <el-input id="name" disabled value="Báo cáo kiểm toán" class="form-control non-border" />
                    </el-form-item>
                  </ValidationProvider>
                </div>
                <div class="col-4">
                  <el-form-item
                    prop="frequencyAuditReport"
                  >
                    <label for="frequencyAuditReport">Tần suất</label>
                    <el-input id="frequencyAuditReport" ref="frequencyAuditReport" v-model="config.frequencyAuditReport" class="form-control non-border" placeholder="Nhập tần suất" />
                  </el-form-item>
                </div>
              </div>
            </div>
            <div class="col-4">
              <div class="row">
                <div class="col-8">
                  <ValidationProvider
                    v-slot="{ errors }"
                    name="name"
                    rules="required"
                  >
                    <el-form-item
                      prop="name"
                      :error="messageError(scorePeriodFields.name, errors[0])"
                    >
                      <label for="name">Loại BCTC <span class="text-danger">*</span></label>
                      <el-input id="name" disabled value="Báo cáo kiểm toán nội bộ" class="form-control non-border" />
                    </el-form-item>
                  </ValidationProvider>
                </div>
                <div class="col-4">
                  <el-form-item
                    prop="name"
                  >
                    <label for="frequencyInternalAuditReport">Tần suất</label>
                    <el-input id="frequencyInternalAuditReport" ref="frequencyInternalAuditReport" v-model="config.frequencyInternalAuditReport" class="form-control non-border" placeholder="Nhập tần suất" />
                  </el-form-item>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-4">
              <div class="row">
                <div class="col-8">
                  <ValidationProvider
                    v-slot="{ errors }"
                    name="name"
                    rules="required"
                  >
                    <el-form-item
                      prop="name"
                      :error="messageError(scorePeriodFields.name, errors[0])"
                    >
                      <label for="name">Loại BCTC <span class="text-danger">*</span></label>
                      <el-input id="name" ref="name" disabled value="Không có BCTC" class="form-control non-border" />
                    </el-form-item>
                  </ValidationProvider>
                </div>
                <div class="col-4">
                  <el-form-item
                    prop="frequencyNoneReport"
                  >
                    <label for="frequencyNoneReport">Tần suất</label>
                    <el-input id="frequencyNoneReport" ref="frequencyNoneReport" v-model="config.frequencyNoneReport" class="form-control non-border" placeholder="Nhập tần suất" />
                  </el-form-item>
                </div>
              </div>
            </div>
            <div class="col-4">
              <el-form-item
                prop="name"
              >
                <label for="timeAutoRemoveDraft">Thời gian tự động xóa hồ sơ nháp (đ/vị: Ngày)</label>
                <el-input id="timeAutoRemoveDraft" ref="timeAutoRemoveDraft" v-model="config.timeAutoRemoveDraft" placeholder="Nhập thời gian tự động xóa hồ sơ nháp" class="form-control non-border" />
              </el-form-item>
            </div>
          </div>
        </div>
        <div class="col-12 mt-3">
          <label class="label">II. Hồ sơ xếp hạng</label>
          <div class="row">
            <div class="col-4">
              <el-form-item>
                <label>Ngày/Tháng hết hiệu lực</label>
                <div class="row">
                  <div class="col-8">
                    <el-input ref="day" v-model="config.day" class="form-control non-border" placeholder="Nhập ngày" />
                  </div>
                  <div class="col-4">
                    <el-input ref="month" v-model="config.month" class="form-control non-border" placeholder="Nhập tháng" />
                  </div>
                </div>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item
                prop="yearExpired"
              >
                <label for="yearExpired">Số năm hiệu lực (năm)</label>
                <el-input id="yearExpired" ref="yearExpired" v-model="config.yearExpired" placeholder="Nhập số năm hiệu lực" class="form-control non-border" />
              </el-form-item>
            </div>
          </div>
        </div>
        <div class="col-12 mt-3">
          <label class="label">III. Hồ sơ điều chỉnh</label>
          <div class="row">
            <div class="col-4">
              <el-form-item
                prop="dayCanEdit"
              >
                <label for="dayCanEdit">Số ngày được phép điều chỉnh (ngày)</label>
                <el-input id="dayCanEdit" ref="dayCanEdit" v-model="config.dayCanEdit" placeholder="Nhập số ngày được phép điều chỉnh" class="form-control non-border" />
              </el-form-item>
            </div>
          </div>
        </div>
      </div>
      <div class="row mt-5">
        <div class="col-12">
          <button class="btn btn-outline-warning float-end me-3 mb-3">Cập nhật</button>
        </div>
      </div>
    </el-form>
  </div>
</template>

<script>
import { ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'

export default {
  name: 'ProcedureConfig',
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
      config: {
        timeAutoRemoveDraft: 1,
        day: '31',
        month: '03',
        yearExpired: 2,
        dayCanEdit: 2,
        frequencyTaxReport: 1,
        frequencyAuditReport: 1,
        frequencyInternalAuditReport: 1,
        frequencyNoneReport: 1
      }
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
