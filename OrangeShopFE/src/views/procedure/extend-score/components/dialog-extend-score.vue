<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }">
    <el-dialog
      :visible.sync="dialogFormVisible"
      :before-close.sync="closeDialog"
    >
      <h3 slot="title" class="title mt-3"><strong>Gia hạn chấm điểm & xếp hạng tín dụng</strong></h3>
      <el-form ref="extendScore" label-position="left">
        <div class="row">
          <div class="col-4">
            <el-form-item
              prop="taxCode"
            >
              <label for="tax-code">Mã số thuế</label>
              <el-input id="tax-code" ref="taxCode" v-model="enterpriseFilter.taxCode" class="form-control non-border" placeholder="Nhập mã số thuế" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="businessCode"
            >
              <label for="business-code">Mã số đăng ký kinh doanh</label>
              <el-input id="business-code" ref="businessCode" v-model="enterpriseFilter.businessCode" class="form-control non-border" placeholder="Nhập mã số thuế đăng ký kinh doanh" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item prop="cifCode">
              <div class="row">
                <div class="col-6">
                  <label>Mã CIF (FCC)</label>
                  <el-input v-model="enterpriseFilter.fccCode" class="form-control non-border" placeholder="Nhập CIF" />
                </div>
                <div class="col-6">
                  <label>Mã CIF (Tạm)</label>
                  <el-input v-model="enterpriseFilter.cifDraftCode" class="form-control non-border" placeholder="Nhập CIF" />
                </div>
              </div>
            </el-form-item>
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12 text-center">
            <button class="btn btn-outline-primary">Tìm Kiếm</button>
          </div>
        </div>
      </el-form>
      <el-form ref="lendingProductForm" label-position="left" class="mt-5">
        <div class="row">
          <div class="col-4">
            <el-form-item
              prop="taxCode"
            >
              <label for="tax-code">Mã số thuế <span class="text-danger">*</span></label>
              <el-input id="tax-code" ref="taxCode" v-model="extendScore.taxCode" disabled class="form-control non-border" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="businessCode"
            >
              <label for="business-code">Mã số đăng ký kinh doanh <span class="text-danger">*</span></label>
              <el-input id="business-code" ref="businessCode" v-model="extendScore.businessCode" disabled class="form-control non-border" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item prop="cifCode">
              <div class="row">
                <div class="col-6">
                  <label>Mã CIF (FCC)</label>
                  <el-input v-model="extendScore.fccCode" disabled class="form-control non-border" />
                </div>
                <div class="col-6">
                  <label>Mã CIF (Tạm)</label>
                  <el-input v-model="extendScore.cifDraftCode" disabled class="form-control non-border" />
                </div>
              </div>
            </el-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col-4">
            <el-form-item
              prop="taxCode"
            >
              <label for="tax-code">Tên doanh nghiệp <span class="text-danger">*</span></label>
              <el-input id="tax-code" ref="taxCode" :value="extendScore.company?.name" disabled class="form-control non-border" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="businessCode"
            >
              <label for="business-code">Tên đơn vị quản lý <span class="text-danger">*</span></label>
              <el-input id="business-code" ref="businessCode" :value="extendScore.unit?.name" disabled class="form-control non-border" />
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="businessCode"
            >
              <label for="business-code">Mã đơn vị quản lý <span class="text-danger">*</span></label>
              <el-input id="business-code" ref="businessCode" :value="extendScore.unit?.code" disabled class="form-control non-border" />
            </el-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col-4">
            <el-form-item
              prop="taxCode"
            >
              <label for="tax-code">Loại chấm điểm</label>
              <el-select id="type" v-model="extendScore.scoreType" disabled class="form-control non-border" placeholder="---Tất cả---">
                <el-option label="Thông thường" value="1" />
                <el-option label="Định kỳ" value="2" />
              </el-select>
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="taxCode"
            >
              <label for="tax-code">Loại BCTC</label>
              <el-select id="type" v-model="extendScore.report" class="form-control non-border" placeholder="---Tất cả---">
                <el-option v-for="item in reports" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item
              prop="businessCode"
            >
              <label for="business-code">Năm BCTC <span class="text-danger">*</span></label>
              <el-input id="business-code" ref="businessCode" :value="extendScore.year" class="form-control non-border" />
            </el-form-item>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <button class="btn btn-outline-secondary me-3" @click="closeDialog">Đóng</button>
        <button class="btn btn-outline-warning" @click="handleSubmit(submit)">Đồng ý</button>
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver } from 'vee-validate'

export default {
  name: 'DialogExtendScore',
  components: { ValidationObserver },
  mixins: [BaseValidate],
  props: {
    dialogFormVisible: {
      type: Boolean,
      default: false
    },
    list: {
      type: Array,
      default: null
    }
  },
  data() {
    return {
      enterpriseFilter: {
        taxCode: '',
        businessCode: '',
        fccCode: '',
        cifDraftCode: ''
      },
      extendScore: {
        taxCode: '',
        businessCode: '',
        fccCode: '',
        cifDraftCode: '',
        company: null,
        unit: null,
        scoreType: null,
        report: null,
        year: null
      },
      reports: null
    }
  },
  created() {
    this.getReports()
  },
  methods: {
    closeDialog() {
      this.$emit('update:dialogFormVisible', false)
      this.$nextTick(() => {
        this.$refs.observer.reset()
      })
    },
    submit() {
      //
    },
    messageError(fieldName, error) {
      if (error === undefined) {
        return undefined
      }
      return fieldName + error
    },
    getReports() {
      this.reports = [
        {
          id: 1,
          name: 'Báo cáo thuế'
        },
        {
          id: 2,
          name: 'Báo cáo kiểm toán'
        },
        {
          id: 3,
          name: 'Báo cáo kiểm toán nội bộ'
        },
        {
          id: 4,
          name: 'Không có BCTC'
        }
      ]
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
