<template>
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
    />
    <el-table-column label="Doanh thu" header-align="center">
      <el-table-column label="Dấu" header-align="center">
        <template slot-scope="{row}">
          <el-radio-group v-model="row.sales.fromSign" disabled>
            <el-radio class="me-2" :label="1">>=</el-radio>
            <el-radio :label="0">></el-radio>
          </el-radio-group>
        </template>
      </el-table-column>
      <el-table-column label="Chặn dưới" align="center">
        <template slot-scope="{row}">
          <span v-if="row.sales.fromValue">{{ row.sales.fromValue }}</span>
          <span v-else>-&#8734;</span>
        </template>
      </el-table-column>
      <el-table-column label="Chặn trên" align="center">
        <template slot-scope="{row}">
          <span v-if="row.sales.toValue">{{ row.sales.toValue }}</span>
          <span v-else>+&#8734;</span>
        </template>
      </el-table-column>
      <el-table-column label="Dấu" header-align="center">
        <template slot-scope="{row}">
          <el-radio-group v-model="row.sales.toSign" disabled>
            <el-radio class="me-2" :label="0">&lt;</el-radio>
            <el-radio :label="1">&lt;=</el-radio>
          </el-radio-group>
        </template>
      </el-table-column>
    </el-table-column>
    <el-table-column label="Vốn chủ sở hữu" header-align="center">
      <el-table-column label="Dấu" header-align="center">
        <template slot-scope="{row}">
          <el-radio-group v-model="row.equity.fromSign" disabled>
            <el-radio class="me-2" :label="1">>=</el-radio>
            <el-radio :label="0">></el-radio>
          </el-radio-group>
        </template>
      </el-table-column>
      <el-table-column label="Chặn dưới" align="center">
        <template slot-scope="{row}">
          <span v-if="row.equity.fromValue">{{ row.equity.fromValue }}</span>
          <span v-else>-&#8734;</span>
        </template>
      </el-table-column>
      <el-table-column label="Chặn trên" align="center">
        <template slot-scope="{row}">
          <span v-if="row.equity.toValue">{{ row.equity.toValue }}</span>
          <span v-else>+&#8734;</span>
        </template>
      </el-table-column>
      <el-table-column label="Dấu" header-align="center">
        <template slot-scope="{row}">
          <el-radio-group v-model="row.equity.toSign" disabled>
            <el-radio class="me-2" :label="0">&lt;</el-radio>
            <el-radio :label="1">&lt;=</el-radio>
          </el-radio-group>
        </template>
      </el-table-column>
    </el-table-column>
    <el-table-column label="Size" align="center" with="100px">
      <template slot-scope="{row}">
        {{ row.size }}
      </template>
    </el-table-column>
    <el-table-column label="Quy mô" align="center" with="100px">
      <template slot-scope="{row}">
        {{ renderScale(row.scale) }}
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

export default {
  name: 'ListConfig',
  props: {
    configScalePointList: {
      type: Array,
      default: null
    }
  },
  methods: {
    renderScale(scale) {
      return scale === 1 ? 'Nhỏ' : (scale === 2 ? 'Lớn' : 'Vừa')
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
