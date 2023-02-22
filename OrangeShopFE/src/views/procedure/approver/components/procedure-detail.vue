<template>
  <div class="app-container">
    <div class="filter-container">
      <h4>Danh sách quyền phê duyệt luồng nhanh</h4>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      :header-cell-style="{ background: '#F2F2F3', color: '#000000', border: 'none' }"
      :cell-style="{borderRight: 'none'}"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="Thao tác" width="100px" align="center">
        <template slot-scope="{row}">
          <router-link :to="{ path: '/procedures/'+ $route.params.id +'/enterprises/'+row.id+'/users'}"><span><i class="el-icon-s-tools" style="color: #EF8C1A" /></span></router-link>
        </template>
      </el-table-column>
      <el-table-column label="Quyền phê duyệt" width="150px">
        <template slot-scope="{row}">
          {{ row.group?.name }}
        </template>
      </el-table-column>
      <el-table-column label="Người thực hiện">
        <template slot-scope="{row}">
          <span>{{ getUsers(row.users) }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>listQuery.limit" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" layout="prev, pager, next, sizes" @pagination="getList" />
    <div class="row fixed-bottom">
      <div class="col-12">
        <button class="btn btn-outline-secondary float-end me-3 mb-3" @click="closePage">Đóng</button>
      </div>
    </div>
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'ProcedureDetail',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    closePage() {
      this.$router.back()
    },
    getList() {
      this.listLoading = true
      this.list = [
        {
          id: 1,
          group: {
            id: 1,
            name: 'Checker 1'
          },
          users: [
            {
              name: 'Nguyễn Văn A'
            },
            {
              name: 'Nguyễn Văn B'
            }
          ]
        },
        {
          id: 2,
          group: {
            id: 2,
            name: 'Checker 2'
          },
          users: [
            {
              name: 'Nguyễn Văn C'
            },
            {
              name: 'Nguyễn Văn D'
            }
          ]
        },
        {
          id: 3,
          group: {
            id: 3,
            name: 'Approver 1'
          },
          users: [
            {
              name: 'Nguyễn Văn E'
            },
            {
              name: 'Nguyễn Văn F'
            }
          ]
        },
        {
          id: 4,
          group: {
            id: 4,
            name: 'Approver 2'
          },
          users: [
            {
              name: 'Nguyễn Văn G'
            },
            {
              name: 'Nguyễn Văn H'
            }
          ]
        }
      ]
      this.total = this.list.length
      setTimeout(() => {
        this.listLoading = false
      }, 1.5 * 1000)
    },
    getUsers(users) {
      let userString = ''
      for (let i = 0; i < users.length; i++) {
        userString += users[i].name
        if (i < users.length - 1) {
          userString += ', '
        }
      }
      return userString
    }
  }
}
</script>
