<template>
  <ValidationProvider
    v-slot="{ errors }"
    name="configScalePointList"
    :rules="{required: true}"
  >
    <el-form-item
      prop="configScalePointList"
      :error="messageError(tpBankRuleFields.configScalePointList, errors[0])"
    >
      <label for="configScalePointList">Thiết lập điểm quy mô <span class="text-danger">*</span> <i
        class="ms-3 el-icon-circle-plus"
        style="color: #A045CB; font-size: 20px"
        @click="addNewScalePoint"
      /></label>
      <el-table
        v-model="configScalePointList"
        :data="configScalePointList"
        :header-cell-style="{ background: '#F2F2F3', color: '#000000' }"
        border
        fit
        highlight-current-row
        style="width: 100%;"
      >
        <el-table-column
          label="Thao tác"
          align="center"
        >
          <template v-if="canEdit" slot-scope="{$index}">
            <i class="el-icon-delete" style="color: #FF485A" @click="removeScalePoint($index)" />
          </template>
        </el-table-column>
        <el-table-column label="Doanh thu" header-align="center">
          <el-table-column label="Dấu" header-align="center">
            <template slot-scope="{row}">
              <el-radio-group v-model="row.sales.fromSign" :disabled="!canEdit">
                <el-radio class="me-2" :label="1">>=</el-radio>
                <el-radio :label="0">></el-radio>
              </el-radio-group>
            </template>
          </el-table-column>
          <el-table-column label="Chặn dưới" header-align="center">
            <template slot-scope="{row}">
              <ValidationProvider v-slot="{ errors }" name="salesFromValue" :rules="{ regex:/^[0-9]*$/, isLower: '@salesToValue', max: 20}">
                <el-form-item
                  prop="salesFromValue"
                  :error="messageError(salesScalePointFields.fromValue, errors[0])"
                >
                  <el-input
                    id="salesFromValue"
                    ref="salesFromValue"
                    v-model="row.sales.fromValue"
                    :disabled="!canEdit"
                    class="form-control non-border"
                    placeholder="Nhập"
                  />
                  <div slot="error" class="el-form-item__error" style="position: relative">{{ messageError(salesScalePointFields.fromValue, errors[0]) }}</div>
                </el-form-item>
              </ValidationProvider>
            </template>
          </el-table-column>
          <el-table-column label="Chặn trên" header-align="center">
            <template slot-scope="{row}">
              <ValidationProvider v-slot="{ errors }" name="salesToValue" :rules="{ regex:/^[0-9]*$/, max: 20}">
                <el-form-item
                  prop="salesToValue"
                  :error="messageError(salesScalePointFields.toValue, errors[0])"
                >
                  <el-input
                    id="salesToValue"
                    ref="salesToValue"
                    v-model="row.sales.toValue"
                    :disabled="!canEdit"
                    class="form-control non-border"
                    placeholder="Nhập"
                  />
                </el-form-item>
                <div slot="error" class="el-form-item__error" style="position: relative">{{ messageError(salesScalePointFields.toValue, errors[0]) }}</div>
              </ValidationProvider>
            </template>
          </el-table-column>
          <el-table-column label="Dấu" header-align="center">
            <template slot-scope="{row}">
              <el-radio-group v-model="row.sales.toSign" :disabled="!canEdit">
                <el-radio class="me-2" :label="0">&lt;</el-radio>
                <el-radio :label="1">&lt;=</el-radio>
              </el-radio-group>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column label="Vốn chủ sở hữu" header-align="center">
          <el-table-column label="Dấu" header-align="center">
            <template slot-scope="{row}">
              <el-radio-group v-model="row.equity.fromSign" :disabled="!canEdit">
                <el-radio class="me-2" :label="1">>=</el-radio>
                <el-radio :label="0">></el-radio>
              </el-radio-group>
            </template>
          </el-table-column>
          <el-table-column label="Chặn dưới" header-align="center">
            <template slot-scope="{row}">
              <ValidationProvider v-slot="{ errors }" name="equityFromValue" :rules="{ regex:/^[0-9]*$/, isLower: '@equityToValue', max: 20}">
                <el-form-item
                  prop="equityFromValue"
                  :error="messageError(equityScalePointFields.fromValue, errors[0])"
                >
                  <el-input
                    id="equityFromValue"
                    ref="equityFromValue"
                    v-model="row.equity.fromValue"
                    :disabled="!canEdit"
                    class="form-control non-border"
                    placeholder="Nhập"
                  />
                  <div slot="error" class="el-form-item__error" style="position: relative">{{ messageError(equityScalePointFields.fromValue, errors[0]) }}</div>
                </el-form-item>
              </ValidationProvider>
            </template>
          </el-table-column>
          <el-table-column label="Chặn trên" header-align="center">
            <template slot-scope="{row}">
              <ValidationProvider v-slot="{ errors }" name="equityToValue" :rules="{ regex:/^[0-9]*$/, max: 20}">
                <el-form-item
                  prop="equityToValue"
                  :error="messageError(equityScalePointFields.toValue, errors[0])"
                >
                  <el-input
                    id="equityToValue"
                    ref="equityToValue"
                    v-model="row.equity.toValue"
                    :disabled="!canEdit"
                    class="form-control non-border"
                    placeholder="Nhập"
                  />
                </el-form-item>
                <div slot="error" class="el-form-item__error" style="position: relative">{{ messageError(equityScalePointFields.toValue, errors[0]) }}</div>
              </ValidationProvider>
            </template>
          </el-table-column>
          <el-table-column label="Dấu" header-align="center">
            <template slot-scope="{row}">
              <el-radio-group v-model="row.equity.toSign" :disabled="!canEdit">
                <el-radio class="me-2" :label="0">&lt;</el-radio>
                <el-radio :label="1">&lt;=</el-radio>
              </el-radio-group>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column label="Size" header-align="center" with="100px">
          <template slot-scope="{row}">
            <ValidationProvider v-slot="{ errors }" name="size" rules="required">
              <el-form-item
                prop="size"
                :error="messageError(configScalePointFields.size, errors[0])"
              >
                <el-input
                  id="size"
                  ref="size"
                  v-model="row.size"
                  :disabled="!canEdit"
                  class="form-control non-border"
                  placeholder="Nhập"
                />
                <div slot="error" class="el-form-item__error" style="position: relative">{{ messageError(configScalePointFields.size, errors[0]) }}</div>
              </el-form-item>
            </ValidationProvider>
          </template>
        </el-table-column>
        <el-table-column label="Quy mô" header-align="center" with="100px">
          <template slot-scope="{row}">
            <ValidationProvider v-slot="{ errors }" name="scale" rules="required">
              <el-form-item
                prop="scale"
                :error="messageError(configScalePointFields.scale, errors[0])"
              >
                <el-select id="scale" v-model="row.scale" class="form-control non-border" placeholder="Chọn" :disabled="!canEdit">
                  <el-option label="Nhỏ" :value="1" />
                  <el-option label="Lớn" :value="2" />
                  <el-option label="Vừa" :value="3" />
                </el-select>
                <div slot="error" class="el-form-item__error" style="position: relative">{{ messageError(configScalePointFields.scale, errors[0]) }}</div>
              </el-form-item>
            </ValidationProvider>
          </template>
        </el-table-column>
      </el-table>
    </el-form-item>
  </ValidationProvider>
</template>

<script>

import baseCommon from '@/utils/base-common'
import { ValidationProvider } from 'vee-validate'
import BaseValidate from '@/utils/BaseValidate'

export default {
  name: 'ListConfigEditable',
  components: { ValidationProvider },
  mixins: [BaseValidate, baseCommon],
  props: {
    configScalePointList: {
      type: Array,
      default: null
    },
    canEdit: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    addNewScalePoint() {
      this.configScalePointList.push(this.createNewScalePoint())
    },
    removeScalePoint(index) {
      this.configScalePointList.splice(index, 1)
      this.$emit('update:configScalePointList', this.configScalePointList)
    },
    createNewScalePoint() {
      return {
        sales: {
          fromSign: 1,
          fromValue: null,
          toValue: null,
          toSign: 0
        },
        equity: {
          fromSign: 1,
          fromValue: null,
          toValue: null,
          toSign: 0
        },
        size: '',
        scale: null
      }
    }
  }
}
</script>
<style>
.non-border {
  border: 0;
  padding: 0;
}
</style>
