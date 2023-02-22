<template>
  <div class="app-container">
    <div class="page-title">{{ pageTitle }}</div>
    <div class="page-button-top">
      <el-button
        icon="el-icon-check"
        type="success"
        plain
        style="text-transform: uppercase;"
      >Kiểm tra</el-button>

      <el-button
        icon="el-icon-position"
        type="primary"
        plain
        style="text-transform: uppercase;"
      >Gửi tin</el-button>
    </div>
    <div class="col-sm-12 row">
      <div id="priority" class="panel panel-default">
        <div class="panel-body">
          <h6>Cấu hình thứ tự ưu tiên</h6><br>
          <div class="row">
            <div class="col-sm-5">
              <div class="d-inline-block label-custom"><span class="">Tài khoản</span> <i class="required">*</i></div>
              <div class="d-inline-block w-75 ml-15">
                <el-select
                  v-model="formInput.priorityInfo.account"
                  class="w-110"
                  placeholder="Chọn tài khoản"
                  filterable
                >
                  <el-option
                    v-for="item in listAccount"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </div>
            </div>
            <div class="col-sm-1" />

            <div class="col-sm-6 text-end-custom">
              <div class="d-inline-block label-custom"><span class="">Nhóm KH</span> <i class="required">*</i></div>
              <div class="d-inline-block w-75 ml-15">
                <el-select
                  v-model="formInput.priorityInfo.groupAccount"
                  class="w-100"
                  filterable
                  disabled
                >
                  <el-option
                    v-for="item in listAccount"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </div>
            </div>
          </div>

          <div class="row mt-4">
            <div class="col-sm-5">
              <div class="d-inline-block label-custom"><span class="">Thứ tự ưu tiên</span> <i class="required">*</i></div>
              <div class="d-inline-block w-75 ml-15">
                <select id="priorityList" v-model="formInput.priorityInfo.priorities" size="6" class="form-control w-110">
                  <option
                    v-for="(item,index) in priorityList"
                    :key="index"
                    :value="item"
                  >
                    {{ item.label }}
                  </option>
                </select>
              </div>
            </div>

            <div class="col-sm-2">
              <div>
                <p class="float-start arrow-start">
                  <el-button
                    icon="el-icon-arrow-right"
                    type="primary"
                    plain
                    size="small"
                    style="padding:6px 8px;"
                  />
                </p>
                <p class="float-end arrow-end">
                  <el-button
                    icon="el-icon-arrow-up"
                    type="primary"
                    plain
                    size="small"
                    style="padding:6px 8px;"
                  />
                </p>
              </div>
              <div style="clear: both;" />

              <div class="arrow-row">
                <p class="float-start arrow-start">
                  <el-button
                    icon="el-icon-arrow-left"
                    type="primary"
                    plain
                    size="small"
                    style="padding:6px 8px;"
                  />
                </p>
                <p class="float-end arrow-end">
                  <el-button
                    icon="el-icon-arrow-down"
                    type="primary"
                    plain
                    size="small"
                    style="padding:6px 8px;"
                  />
                </p>
              </div>
              <div style="clear: both;" />

              <div class="arrow-row">
                <p class="float-start arrow-start">
                  <el-button
                    icon="el-icon-d-arrow-right"
                    type="primary"
                    plain
                    size="small"
                    style="padding:6px 8px;"
                  />
                </p>
                <p class="float-end arrow-end">
                  <el-button
                    icon="el-icon-d-arrow-right"
                    type="primary"
                    plain
                    size="small"
                    style="padding:8px 6px;transform:rotate(270deg);position: relative;left: -1px;"
                  />
                </p>
              </div>
              <div style="clear: both;" />

              <div class="arrow-row">
                <p class="float-start arrow-start">
                  <el-button
                    icon="el-icon-d-arrow-left"
                    type="primary"
                    plain
                    size="small"
                    style="padding:6px 8px;"
                  />
                </p>
                <p class="float-end arrow-end">
                  <el-button
                    icon="el-icon-d-arrow-right"
                    type="primary"
                    plain
                    size="small"
                    style="padding:8px 6px; transform:rotate(90deg);position: relative;left: -1px;"
                  />
                </p>
              </div>
            </div>

            <div class="col-sm-5 text-end-custom">
              <div class="d-inline-block w-90 ml-15">
                <select id="priorityListSelected" v-model="formInput.priorityInfo.prioritiesSelected" size="6" class="form-control w-100">
                  <option v-for="(item, index) in prioritySelectedList" :key="index" :value="item">
                    {{ item.name }}
                  </option>
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import baseCommon from '@/utils/base-common'
import failoverCommon from '@/views/failover/mixin/failover-mixin'
import title from '@/utils/mx-title'
// import * as reportService from '@/views/reports/service/report-service'
import horizontalScroll from 'el-table-horizontal-scroll'
// import { alertCommon } from '@/utils/alert-common'
// import reportCommon from '@/views/reports/mixin/report-mixin'

export default {
  name: 'CreateFailover', // mã màn hình
  directives: {
    horizontalScroll
  },
  mixins: [baseCommon, failoverCommon, title],
  data() {
    return {
      formInput: {
        priorityInfo: {
          account: null,
          groupAccount: null,
          priorities: null,
          prioritiesSelected: null
        },
        contentInfo: {

        },
        configSendMsg: {

        }
      }
    }
  },
  methods: {
  }
}
</script>

<style scoped>
  .panel{
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid #eee;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgb(239 239 243);
    box-shadow: 0 1px 1px rgb(239 239 243);
    margin-top: 20px;
  }
  .panel-body {
    padding: 15px 15px;
  }
  .arrow-start{
    position: relative;
    left: 50px;
  }
  .arrow-end{
    position: relative;
    right: -20px;
  }
  .arrow-row:nth-of-type(1){
    position: relative;
    top: -10px
  }
</style>
