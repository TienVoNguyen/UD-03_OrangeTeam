<template>
  <div>
    <hr>
    <div class="mt-2 mb-2">
      <h5 class="text-primary mb-3">
        2. Khai báo thao tác
        <el-button
          class="button-custom"
          icon="el-icon-plus"
          size="mini"
          style="float: right"
          type="primary"
          @click="addNewAction()"
        >Thêm mới</el-button>
      </h5>
    </div>
    <el-table :data="actions" style="width: 100%" border max-height="300">
      <el-table-column label="STT" width="50" :align="'center'">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="Mã thao tác">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.code"
            class="w-full"
            maxlength="50"
            show-word-limit
          />
          <p
            v-if="validateActionCode(scope.row, scope.$index) !== null"
            style="font-size: 12px"
            class="text-danger"
          >
            {{ validateActionCode(scope.row, scope.$index) }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="Tên thao tác">
        <template
          slot-scope="scope"
        ><el-input
          v-model="scope.row.name"
          class="w-full"
          maxlength="100"
          show-word-limit
        /></template>
      </el-table-column>
      <el-table-column label="mô tả">
        <template
          slot-scope="scope"
        ><el-input
          v-model="scope.row.description"
          class="w-full"
          maxlength="500"
          show-word-limit
        /> </template></el-table-column>
      <el-table-column label="Thao tác" width="120" :align="'center'">
        <template slot-scope="scope">
          <el-tooltip
            class="item"
            effect="dark"
            content="Thêm mới chi tiết thao tác"
            placement="bottom"
          >
            <el-button
              v-if="validateActionCode(scope.row, scope.$index) === null"
              size="mini"
              type="primary"
              plain
              @click="handleAddActionApis(scope.$index, scope.row)"
            >
              <i class="el-icon-plus" />
            </el-button>
          </el-tooltip>
          <el-tooltip
            class="item"
            effect="dark"
            content="Xoá thao tác"
            placement="bottom"
          >
            <el-button
              size="mini"
              type="danger"
              plain
              @click="handleDelete(scope.$index, scope.row)"
            ><i class="el-icon-delete" />
            </el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-dialog
        title="Thao tác chi tiết"
        :visible.sync="showDialogApis"
        :before-close="handleClose"
        append-to-body
      >
        <el-button
          class="button-custom mb-3"
          icon="el-icon-plus"
          size="mini"
          style="float: left"
          type="primary"
          @click="addNewActionApis()"
        >Thêm mới</el-button>
        <el-table :data="listApi" style="width: 100%" border max-height="500">
          <el-table-column
            label="STT"
            width="50"
            :align="'center'"
            header-align="center"
          >
            <template slot-scope="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column label="Mã thao tác chi tiết">
            <template slot-scope="scope">
              <el-input
                v-model="scope.row.apiCode"
                class="w-full"
                maxlength="50"
                show-word-limit
              />
              <p
                v-if="validateActionCodeApis(scope.row, scope.$index) !== null"
                style="font-size: 12px"
                class="text-danger"
              >
                {{ validateActionCodeApis(scope.row, scope.$index) }}
              </p>
            </template>
          </el-table-column>
          <el-table-column label="Phương thức api">
            <template slot-scope="scope">
              <el-select v-model="scope.row.apiMethod" class="w-full" placeholder="Select">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="Thao tác" width="80" :align="'center'">
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="Xoá thao tác chi tiết"
                placement="bottom"
              >
                <el-button
                  size="mini"
                  type="danger"
                  plain
                  @click="handleDeleteApis(scope.$index, scope.row)"
                ><i class="el-icon-delete" />
                </el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
          <el-button
            plain
            type="danger"
            @click="hideDialogApis()"
          >Đóng</el-button>
          <el-button
            plain
            type="primary"
            @click="handleSubmitActionApis()"
          >Lưu</el-button>
        </div>
      </el-dialog>
    </el-table>
  </div>
</template>
<script>
import BaseCommon from '@/utils/base-common'
import * as functionService from '../service/function-service'
export default {
  name: 'ActionApis',
  mixins: [BaseCommon],
  props: {
    isGetActions: {
      type: Boolean,
      default: false
    },
    actions: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      action: {
        id: null,
        code: null,
        description: null,
        functionId: null,
        listApi: []
      },
      listApi: [],
      api: {
        apiCode: null,
        apiMethod: null
      },
      showDialogApis: false,
      actionCode: null,
      isDoneApis: true,
      isDoneAction: true,
      options: [{
        value: 'GET',
        label: 'GET'
      }, {
        value: 'POST',
        label: 'POST'
      }, {
        value: 'PUT',
        label: 'PUT'
      }, {
        value: 'DELETE',
        label: 'DELETE'
      }
      ]
    }
  },
  watch: {},
  created() {},
  methods: {
    handleClose() {},
    handleDelete(index, item) {
      this.$confirm(
        'Bạn có chắc chắn muốn xoá bản ghi này không?',
        'Cảnh báo',
        {
          confirmButtonText: 'Đồng ý',
          cancelButtonText: 'Huỷ',
          type: 'warning',
          center: true
        }
      ).then(() => {
        this.deleteAction(index, item)
      }).catch(action => {
      })
    },
    handleAddActionApis(index, item) {
      this.listApi =
        item.listApi === null || item.listApi === undefined ? [] : item.listApi
      this.showDialogApis = true
      this.actionCode = item.code
    },
    async deleteAction(index, item) {
      if (item.id === null) {
        this.actions.splice(index, 1)
      } else {
        const response = await functionService.deleteFunctionAction(item.id)
        if (response.code === 200) {
          this.actions.splice(index, 1)
          this.hideDialog()
        }
      }
    },
    handleDeleteApis(index, item) {
      this.$confirm(
        'Bạn có chắc chắn muốn xoá bản ghi này không?',
        'Cảnh báo',
        {
          confirmButtonText: 'Đồng ý',
          cancelButtonText: 'Huỷ',
          type: 'warning',
          center: true
        }
      ).then(() => {
        this.listApi.splice(index, 1)
        this.notifySuccess('Thông báo', 'Xoá thành công')
      }).catch(action => {
      })
    },
    hideDialogApis() {
      this.showDialogApis = false
    },
    addNewAction() {
      this.action = {
        id: null,
        code: null,
        description: null,
        functionId: null
      }
      this.actions.push(this.action)
    },
    addNewActionApis() {
      this.api = {
        apiCode: null,
        apiMethod: null
      }
      this.listApi.push(this.api)
    },
    validateActionCode(item, index) {
      this.isDoneAction = true
      if (item.code === null || item.code === '') {
        this.isDoneAction = false
        return 'Mã thao tác không được bỏ trống'
      } else {
        if (this.actions.length > 1) {
          const tempActions = this.actions
          for (let i = 0; i < tempActions.length; i++) {
            if (item.code === tempActions[i].code && i !== index) {
              this.isDoneAction = false
              return 'Mã thao tác bị trùng'
            }
          }
        }
        return null
      }
    },
    validateActionCodeApis(item, index) {
      this.isDoneApis = true
      if (item.apiCode === null || item.apiCode === '') {
        this.isDoneApis = false
        return 'Mã thao tác chi tiết không được bỏ trống'
      } else {
        if (this.listApi.length > 1) {
          const templistApi = this.listApi
          for (let i = 0; i < templistApi.length; i++) {
            if (item.apiCode === templistApi[i].apiCode && i !== index) {
              this.isDoneApis = false
              return 'Mã thao tác chi tiết bị trùng'
            }
          }
        }
        return null
      }
    },
    handleSubmitActionApis() {
      if (this.isDoneApis) {
        const objectIndex = this.actions.findIndex(
          (obj) => obj.code === this.actionCode
        )
        this.actions[objectIndex].listApi = this.listApi
        this.$emit('genAction', this.actions)
        this.notifySuccess('Thông báo', 'Thêm thành công api')
        this.hideDialogApis()
      }
    }
  }
}
</script>

