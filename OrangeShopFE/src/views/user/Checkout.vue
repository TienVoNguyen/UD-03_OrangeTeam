<template>
  <el-main>
    <!-- Page Header Start -->
    <page-header />
    <!-- Page Header End -->
    <!-- Checkout Start -->
    <div class="container-fluid pt-5">
      <div class="row px-xl-5">
        <div class="col-lg-6">
          <h4 class="font-weight-semi-bold mb-4">Billing Address</h4>
          <div class="row">
            <div class="col-md-6 form-group">
              <label>Họ tên:</label>
              <input class="form-control" type="text" value="Nguyen Tien Vo" readonly>
            </div>
            <div class="col-md-6 form-group">
              <label>Số điện thoại:</label>
              <input class="form-control" type="text" readonly>
            </div>
            <div class="col-md-12 form-group">
              <label>E-mail:</label>
              <input class="form-control" type="text" readonly>
            </div>
            <div class="col-md-12 form-group">
              <label>Địa chỉ:</label>
              <input class="form-control" type="text" readonly>
            </div>
            <div class="col-md-12 form-group">
              <label>Ghi chú:</label>
              <textarea class="form-control" />
            </div>
            <div class="col-md-12 form-group">
              <div class="card border-0 mb-5">
                <div class="card-header bg-secondary border-0">
                  <h4 class="font-weight-semi-bold m-0">Payment</h4>
                </div>
                <div class="card-body">
                  <div class="form-group">
                    <div class="custom-control custom-radio">
                      <input id="paypal" type="radio" class="custom-control-input" name="payment">
                      <label class="custom-control-label" for="paypal">Paypal</label>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="custom-control custom-radio">
                      <input id="directcheck" type="radio" class="custom-control-input" name="payment">
                      <label class="custom-control-label" for="directcheck">Direct Check</label>
                    </div>
                  </div>
                  <div class="">
                    <div class="custom-control custom-radio">
                      <input id="banktransfer" type="radio" class="custom-control-input" name="payment">
                      <label class="custom-control-label" for="banktransfer">Bank Transfer</label>
                    </div>
                  </div>
                </div>
                <div class="card-footer border-secondary bg-transparent">
                  <button class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Place Order</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="card border-0 mb-3">
            <div class="card-header bg-secondary border-0">
              <h4 class="font-weight-semi-bold m-0">Order Total</h4>
            </div>
            <div class="card-body">
              <h5 class="font-weight-medium mb-3">Products</h5>
              <div v-for="(p, index) in productCart" :key="index" class="d-flex justify-content-between">
                <p>{{ p.name }}</p>
                <p>{{ p.size.name }}</p>
                <p>{{ p.color.name }}</p>
                <p>{{ p.quantity }}</p>
                <p>{{ p.price2 * p.quantity | currency('VND', 0, 'đ', '.', ',') }}</p>
              </div>
              <hr class="mt-0">
              <div class="d-flex justify-content-between mb-3 pt-1">
                <h6 class="font-weight-medium">Subtotal</h6>
                <h6 class="font-weight-medium">{{ subTotal | currency('VND', 0, 'đ', '.', ',') }}</h6>
              </div>
              <div class="d-flex justify-content-between">
                <h6 class="font-weight-medium">Giảm giá</h6>
                <h6 class="font-weight-medium">{{ discount | currency('VND', 0, 'đ', '.', ',') }}</h6>
              </div>
              <div class="d-flex justify-content-between">
                <h6 class="font-weight-medium">Shipping</h6>
                <h6 class="font-weight-medium">{{ shipping | currency('VND', 0, 'đ', '.', ',') }}</h6>
              </div>
            </div>
            <div class="card-footer border-secondary bg-transparent">
              <div class="d-flex justify-content-between mt-2">
                <h5 class="font-weight-bold">Total</h5>
                <h5 class="font-weight-bold">{{ subTotal - discount + shipping | currency('VND', 0, 'đ', '.', ',') }}</h5>
              </div>
            </div>
          </div>
          <div class="card border-0 mb-5">
            <div class="card-body">
              <form action="">
                <div class="input-group">
                  <input type="text" class="form-control p-4" placeholder="Coupon Code">
                  <div class="input-group-append">
                    <button class="btn btn-primary">Apply Coupon</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Checkout End -->
  </el-main>
</template>

<script>
import BaseValidate from '@/utils/BaseValidate'
import baseCommon from '@/utils/base-common'
import horizontalScroll from 'el-table-horizontal-scroll'
import userCommon from '@/views/user/Mixin/user-mixin'
import PageHeader from '@/views/user/component/PageHeader'

import { searchAddress } from '@/api/goog-remote-search'
export default {
  name: 'DetailProduct',
  directives: {
    horizontalScroll
  },
  components: { PageHeader },
  mixins: [baseCommon, userCommon, BaseValidate],
  data() {
    return {
      size: '',
      color: '',
      quantity: 1,
      differenceAddress: false,
      state: '',
      timeout: null,
      discount: 100000,
      shipping: 10000
    }
  },
  watch: {
    quantity: function(newVal, oldVal) {
      this.quantity = String(this.quantity).replace(/\D/g, '')
      if (newVal > this.product.quantity) {
        this.quantity = this.product.quantity
      }
      if (newVal <= 1) {
        this.quantity = 1
      }
    }
  },
  methods: {
    querySearchAsync(queryString, cb) {
      searchAddress(queryString).then(res => {
        const link = res.data.predictions
        if (link) {
          console.log(link)
          const results = link.map(e => {
            return {
              value: e.description,
              label: e.description
            }
          })
          cb(results)
        }
      })
    },
    handleSelect(item) {
      console.log(2, item)
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    quantityReduce() {
      if (this.quantity <= 1) {
        this.quantity = 1
      } else {
        this.quantity--
      }
    },
    quantityIncrease() {
      if (this.quantity >= this.product.quantity) {
        this.quantity = this.product.quantity
      } else {
        this.quantity++
      }
    }
  }
}
</script>

<style>
.el-page-header__title {
  margin: auto !important;
}
.breadcrumb-container {
  padding-top: .5rem;
}
.el-carousel, .el-carousel__container {
  width: 100% !important;
  height: 100% !important;
}
.font-weight-medium {
  font-weight: 500;
}
.font-weight-semi-bold {
  font-weight: 600;
}
.text-primary {
  color: #D19C97 !important;
}
.btn-primary {
  color: #212529;
  background-color: #D19C97;
  border-color: #D19C97;
}

.btn-primary:hover {
  color: #fff;
  background-color: #c5837c;
  border-color: #c17a74;
}

.btn-primary:focus, .btn-primary.focus {
  color: #fff;
  background-color: #c5837c;
  border-color: #c17a74;
  box-shadow: 0 0 0 0.2rem rgba(183, 138, 135, 0.5);
}

.btn-primary.disabled, .btn-primary:disabled {
  color: #212529;
  background-color: #D19C97;
  border-color: #D19C97;
}

.btn-primary:not(:disabled):not(.disabled):active, .btn-primary:not(:disabled):not(.disabled).active,
.show > .btn-primary.dropdown-toggle {
  color: #fff;
  background-color: #c17a74;
  border-color: #bd726b;
}

.btn-primary:not(:disabled):not(.disabled):active:focus, .btn-primary:not(:disabled):not(.disabled).active:focus,
.show > .btn-primary.dropdown-toggle:focus {
  box-shadow: 0 0 0 0.2rem rgba(183, 138, 135, 0.5);
}
.bg-secondary {
  background-color: #EDF1FF !important;
}

a.bg-secondary:hover, a.bg-secondary:focus,
button.bg-secondary:hover,
button.bg-secondary:focus {
  background-color: #bac9ff !important;
}

@media (prefers-reduced-motion: reduce) {
  .form-control {
    transition: none;
  }
}

.form-control::-ms-expand {
  background-color: transparent;
  border: 0;
}

.form-control:-moz-focusring {
  color: transparent;
  text-shadow: 0 0 0 #495057;
}

.form-control:focus {
  color: #495057;
  background-color: #fff;
  border-color: #f8f0ef;
  outline: 0;
  box-shadow: none;
}

.form-control::placeholder {
  color: #999999;
  opacity: 1;
}

.form-control:disabled, .form-control[readonly] {
  background-color: #e9ecef;
  opacity: 1;
}

input[type="date"].form-control,
input[type="time"].form-control,
input[type="datetime-local"].form-control,
input[type="month"].form-control {
  appearance: none;
}
.el-radio__input.is-checked + .el-radio__label, .el-tabs__item.is-active {
  color: #bd726b;
}
.el-radio__input.is-checked .el-radio__inner {
  border-color: #bd726b;
  background: #bd726b;
}
.el-checkbox__label {
  font-size: 1.5em;
}
</style>
