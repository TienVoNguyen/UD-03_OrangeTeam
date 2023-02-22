<template>
  <div class="d-block">
    <div class="container">
      <div class="row justify-content-around">
        <div class="col-4">
          <span style="font-weight: bold">Mẫu biểu BCTC</span>
          <el-select
            v-model="filter.typeFReport"
            clearable
            placeholder="---Tất cả---"
            class="mt-3 d-block"
          >
            <el-option
              v-for="item in typeFReports"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
        <div class="col-4">
          <span style="font-weight: bold">Tên biểu mẫu nhập liệu</span>
          <el-input
            v-model="filter.nameFReport"
            placeholder="Nhập tên biểu mẫu"
            prefix-icon="el-icon-search"
            class="mt-3"
          />
        </div>
      </div>
      <div class="row justify-content-around mt-3">
        <div class="col-4">
          <span style="font-weight: bold">Ngày tạo</span>
          <div class="container mt-3">
            <div class="row">
              <div class="col-5 px-0">
                <el-date-picker
                  v-model="filter.fromDateFReport"
                  placeholder="Từ ngày"
                  type="date"
                />
              </div>
              <div class="col-2 text-center align-middle">đến</div>
              <div class="col-5 px-0">
                <el-date-picker
                  v-model="filter.toDateFReport"
                  placeholder="Đến ngày"
                  type="date"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="col-4">
          <span style="font-weight: bold">Trạng thái</span>
          <el-select
            v-model="filter.statusFReport"
            clearable
            placeholder="---Tất cả---"
            class="mt-3 d-block"
          >
            <el-option
              v-for="item in statusFStatuses"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FakeData from '../utils/FakeData'

export default {
  name: 'FilterTable',
  mixins: [FakeData],
  data() {
    return {
      filter: {}
    }
  }
}
</script>

<style scoped>
.el-date-editor {
  width: unset;
}
</style>
