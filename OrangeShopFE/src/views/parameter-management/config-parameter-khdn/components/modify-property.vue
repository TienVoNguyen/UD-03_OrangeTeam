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
              name="codeProperty"
              :rules="{required: true, regex: /^[a-zA-Z0-9_]*$/, max: 20}"
            >
              <el-form-item
                prop="codeProperty"
                :error="messageError(configParameterPropertyFields.codeProperty, errors[0])"
              >
                <label>Mã thuộc tính<span class="text-danger">*</span></label>
                <el-input id="codeProperty" v-model="current.codeProperty" :disabled="dialogStatus === 'update'" placeholder="Nhập" value="" class="form-control" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <div class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="nameProperty"
              :rules="{required: true, max: 100}"
            >
              <el-form-item
                prop="nameProperty"
                :error="messageError(configParameterPropertyFields.nameProperty, errors[0])"
              >
                <label>Tên thuộc tính<span class="text-danger">*</span></label>
                <el-input id="nameProperty" v-model="current.nameProperty" placeholder="Nhập" value="" class="form-control" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <div class="row">
          <div class="col-12">
            <ValidationProvider
              v-slot="{ errors }"
              name="description"
              :rules="{ max: 500}"
            >
              <el-form-item
                prop="description"
                :error="messageError(configParameterPropertyFields.description, errors[0])"
              >
                <label>Mô tả<span class="text-danger">*</span></label>
                <el-input id="description" v-model="current.description" type="textarea" placeholder="Nhập" value="" class="form-control" />
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>

        <div class="row">
          <div class="col-12">
            <ValidationProvider
              name="status"
            >
              <el-form-item
                prop="status"
              >
                <label for="name">Trạng thái</label>
                <el-select id="status" v-model="current.status" class="form-control non-border" placeholder="---Chọn trạng thái---">
                  <el-option v-for="item in lstStatusProperty" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </ValidationProvider>
          </div>
        </div>
      </el-form>

      <div slot="footer" class="dialog-footer  text-center">
        <el-button type="info" plain @click="closeDialog">ĐÓNG</el-button>
        <el-button type="warning" plain @click="handleSubmit(submit)">LƯU</el-button>
      </div>
    </el-dialog>
  </ValidationObserver>
</template>

<script>

import baseCommon from '@/utils/base-common'
import BaseValidate from '@/utils/BaseValidate'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import { createConfigParamProperty, updateConfigParamProperty } from '@/api/config-parameter-property'
import { updateTypeConfig } from '@/api/type-config-parameter'
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
  name: 'ModifyProperty',
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
    parent: {
      type: Object,
      default: null
    },
    dialogStatus: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      rules: {
        // name: [{ required: true, message: 'Name is required', trigger: 'change' }],
        // slug: [{ required: true, message: 'Slug is required', trigger: 'change' }]
      },
      lstStatusProperty: null
    }
  },
  created() {
    this.getLstStatusProperty()
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
      }
    },
    createData() {
      this.current.idTypeConfig = this.parent.id
      createConfigParamProperty(this.current).then(response => {
        if (this.parent.status === 1) {
          this.parent.status = 2
          updateTypeConfig(this.parent).then(response => {
          })
        }
        this.$emit('update:dialogFormVisible', false)
        this.$emit('reload')
        this.notifySuccess('Thông tin', response.message)
      }).catch((error) => {
        if (error.response) {
          this.$emit('reload')
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })
    },
    updateData() {
      updateConfigParamProperty(this.current).then(response => {
        if (this.parent.status === 1) {
          this.parent.status = 2
          updateTypeConfig(this.parent).then(response => {
          })
        }
        this.$emit('update:dialogFormVisible', false)
        this.$emit('reload')
        this.notifySuccess('Thông tin', response.message)
      }).catch((error) => {
        if (error.response) {
          this.$emit('reload')
          console.log(error.response)
          this.notifyError('Thông tin', error.response.data.message)
        }
      })
    },
    handleRejectFrontEnd() {
      this.flag = true
    },
    renderTitle() {
      if (this.dialogStatus === 'create') {
        return 'Thêm mới thuộc tính'
      } else if (this.dialogStatus === 'update') {
        return 'Chỉnh sửa thuộc tính'
      }
    },
    getLstStatusProperty() {
      this.lstStatusProperty = [
        {
          id: 1,
          name: 'Hoạt động'
        },
        {
          id: 2,
          name: 'Không hoạt động'
        }
      ]
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
