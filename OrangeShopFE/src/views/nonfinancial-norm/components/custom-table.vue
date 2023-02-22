<template>
  <el-table
    :data="tableData"
    border
    stripe
    :height="height - 30"
  >
    <el-table-column
      v-for="data in tableHeader"
      :key="data.prop"
      :prop="data.prop"
      :label="data.label"
      :render-header="customizeHeader"
      :min-width="data.width"
      :show-overflow-tooltip="true"
    />
  </el-table>
</template>

<script>
import * as _ from 'lodash'

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
    },
    height: {
      type: Number,
      default: 0
    },
    loading: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      search: {},
      count: 0
    }
  },
  watch: {
    search: {
      handler(value) {
        this.handleInputChange(value)
      },
      deep: true
    }
  },
  created() {
    this.tableHeader.forEach((h) => {
      this.$set(this.search, h.prop, '')
    })
  },
  methods: {
    handleInputChange: _.debounce(function(data) {
      this.$emit('filter', data)
    }, 600),
    customizeHeader(createElement, { column, $index }) {
      const col = this.$props.tableHeader[$index]
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
              input={this.handleInputChange}
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

>>> tr td .cell { white-space:pre }

</style>
