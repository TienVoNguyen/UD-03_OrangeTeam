<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column
      label="Thao tác"
    ><template slot-scope="scope">
      <el-button
        size="mini"
        icon="el-icon-edit"
        plain
        @click="handleEvent(scope.$index, scope.row)"
      />
      <el-button
        size="mini"
        type="danger"
        icon="el-icon-delete"
        plain
        @click="handleEvent(scope.$index, scope.row)"
      />
      <el-button
        size="mini"
        type="css-secondary"
        icon="el-icon-view"
        plain
        @click="handleEvent(scope.$index, scope.row)"
      />
      <el-button
        size="mini"
        type="css-primary"
        icon="el-icon-copy-document"
        plain
        @click="handleEvent(scope.$index, scope.row)"
      />
    </template></el-table-column>
    <el-table-column
      v-for="data in tableHeader"
      :key="data.prop"
      :prop="data.prop"
      :label="data.label"
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
  created() {},
  methods: {
    handleEvent(index, row) {
      console.log(index, row)
    }
  }
}
</script>

<style>
</style>
