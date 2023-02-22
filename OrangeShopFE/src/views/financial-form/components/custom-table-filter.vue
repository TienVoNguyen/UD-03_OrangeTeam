<template>
  <el-table :data="tableData" style="width: 100%" border>
    <el-table-column label="Thao tác" :render-header="customizeHeader" align="center">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="css-primary"
          icon="el-icon-delete"
          plain
          @click="handleEvent(scope.$index, scope.row)"
        />
      </template>
    </el-table-column>
    <el-table-column
      v-for="data in tableHeader"
      :key="data.prop"
      :prop="data.prop"
      :label="data.label"
      :render-header="customizeHeader"
      :align="data.align"
    />
  </el-table>
</template>

<script>
export default {
  name: 'CustomTable',
  props: {
    tableData: {
      type: Array,
      default: () => []
    },
    tableHeader: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      search: {}
    }
  },
  created() {
    this.tableHeader.forEach((h) => {
      this.$set(this.search, h.prop, '')
    })
  },
  methods: {
    handleInputChange(value) {
      console.log(value, 1)
    },
    customizeHeader(createElement, { column, $index }) {
      let col = ''
      if ($index === 0) {
        col = {
          label: 'Thao tác',
          hasSearch: false
        }
      } else {
        col = this.$props.tableHeader[$index - 1]
      }
      return (
        <div>
          <div class='d-block c-header text-center'>
            <span>{col.label}</span>
          </div>
          {col.hasSearch ? (
            <el-input
              suffix-icon='el-icon-search'
              v-model={this.search[col.prop]}
              placeholder='Tìm kiếm'
            />
          ) : (
            <div>
              {' '}
              <div style='height: 36px'></div>{' '}
            </div>
          )}
        </div>
      )
    },
    handleEvent(index, row) {}
  }
}
</script>

<style scoped>
>>> thead .cell {
  padding-left: 0px !important;
  padding-right: 0px !important;
}
.c-header {
  background-color: #f2f2f3;
  height: 40px;
  line-height: 40px;
}
>>> thead th {
  padding-bottom: 0px;
  padding-top: 0px;
}
>>> thead input {
  border: 0px;
}
</style>
