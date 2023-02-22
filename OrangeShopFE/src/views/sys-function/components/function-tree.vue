<template>
  <div class="custom-tree-container app-container">
    <el-card class="box-card app-container">
      <div slot="header" class="clearfix">
        <h5>
          Quản lý chức năng hệ thống
          <el-button
            v-if="hasPermission($options.name,'function.insert')"
            class="button-custom"
            icon="el-icon-plus"
            size="small"
            style="float: right"
            type="primary"
            @click="showDialog(null, 'create')"
          >Thêm mới</el-button>
          <modify-dialog
            :id="id"
            :parent-id="parentId"
            :show-dialog="isShowDialog"
            @hideDialog="hideDialog"
            @modifySuccess="getFunctions"
          />
        </h5>
      </div>
      <el-tree
        :data="functions"
        node-key="id"
        default-expand-all
        :expand-on-click-node="false"
      >
        <span slot-scope="{ node, data }" class="custom-tree-node">

          <span
            :class="data.status == 0 ? 'text-red' : 'text-primary'"
          ><svg-icon :icon-class="data.icon ? data.icon : ''" class-name="disabled" />&nbsp;&nbsp; {{ data.title }} {{ data.name }}</span>
          <span>
            <el-button
              v-if="data.isMenuGroup === 1 && hasPermission($options.name,'function.insert')"
              size="small"
              type="primary"
              class="button-custom"
              plain
              @click="showDialog(data, 'create')"
            >
              Thêm
            </el-button>
            <el-button
              v-if="data.priority != null && hasPermission($options.name,'function.update')"
              size="small"
              type="success"
              class="button-custom"
              plain
              @click="showDialog(data, 'update')"
            >
              Sửa
            </el-button>
            <el-button
              v-if="data.priority != null && hasPermission($options.name,'function.delete') && hasPermission($options.name,'function.action.delete')"
              type="danger"
              size="small"
              class="button-custom"
              plain
              @click="() => remove(node, data)"
            >
              Xoá
            </el-button>
          </span>
        </span>
      </el-tree>
    </el-card>
  </div>
</template>
<script>
import * as functionService from '../service/function-service'
import baseCommon from '@/utils/base-common'
import ModifyDialog from '../components/modify.vue'
export default {
  name: 'Function',
  components: { ModifyDialog },
  mixins: [baseCommon],
  data() {
    return {
      functions: [],
      isShowDialog: false,
      id: null,
      parentId: null
    }
  },
  created() {
    this.getFunctions()
  },
  methods: {
    showDialog(data, action) {
      if (action === 'create') {
        this.parentId = data == null ? null : data.id
      } else if (action === 'update') {
        this.id = data.id
      } else {
        this.notifyError('Lỗi', 'Không xác định được chức năng')
      }
      this.isShowDialog = true
    },
    hideDialog(obj) {
      this.id = null
      this.parentId = null
      this.isShowDialog = obj
    },
    async getFunctions() {
      this.showLoading()
      const params = {}
      const response = await functionService.getFunctions(params)
      if (response.code === 200) {
        this.functions = this.generateTreeMenu(response.data)
        this.notifyInfo('Thông tin', response.message)
        console.log(this.functions)
      }
      this.hideLoading()
    },
    remove(node, data) {
      this.$confirm(
        'Bạn có chắc chắn muốn xoá bản ghi này không?',
        'Cảnh báo',
        {
          confirmButtonText: 'Đồng ý',
          cancelButtonText: 'Huỷ',
          type: 'warning',
          center: true
        }
      ).then(async() => {
        const response = await functionService.deleteFunction(data.id)
        if (response.code === 200) {
          this.notifyInfo('Thông tin', response.message)
          this.getFunctions()
        }
      }).catch(action => {
      })
    },

    generateTreeMenu(data) {
      const map = {}
      const root = []
      for (const item of data) {
        item.children = []
        map[item?.id] = item
        if (item.actions && item.actions.length !== 0) {
          item.children = item.children.concat(item.actions)
        }
      }
      for (const item of data) {
        if (item.parentId) {
          map[item.parentId].children.push(item)
          // if (item.actions && item.actions.length !== 0) {
          //   item.children = item.children.concat(item.actions)
          // }
        } else {
          root.push(item)
        }
      }
      return root
    }
  }
}
</script>

<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 18px;
  padding-right: 8px;
}
.el-tree-node__content {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  height: 2.5rem;
  cursor: pointer;
}
</style>
