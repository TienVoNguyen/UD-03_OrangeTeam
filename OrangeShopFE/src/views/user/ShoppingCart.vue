<template>
  <el-main>
    <!-- Page Header Start -->
    <page-header />
    <!-- Page Header End -->
    <!-- Cart Start -->
    <div class="container-fluid pt-5">
      <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
          <table class="table table-bordered text-center mb-0">
            <thead class="bg-secondary text-dark">
              <tr>
                <th>Products</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Remove</th>
              </tr>
            </thead>
            <tbody class="align-middle">
              <cart-row
                v-for="(product, index) in productCart"
                :key="index"
                :index="index"
                :cart-row="product"
                @increase="quantityIncrease"
                @reduce="quantityReduce"
                @watchCartRow="changeValCartRow"
              />
            </tbody>
          </table>
        </div>
        <div class="col-lg-4">
          <div class="card border-secondary mb-5">
            <div class="card-header bg-secondary border-0">
              <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
            </div>
            <div class="card-body">
              <div class="d-flex justify-content-between mb-3 pt-1">
                <h6 class="font-weight-medium">Subtotal</h6>
                <h6 class="font-weight-medium">{{ subTotal | currency('VND', 0, 'đ', '.', ',') }}</h6>
              </div>
            </div>
            <div class="card-footer border-secondary bg-transparent">
              <div class="d-flex justify-content-between mt-2">
                <h5 class="font-weight-bold">Total</h5>
                <h5 class="font-weight-bold">{{ subTotal - discount | currency('VND', 0, 'đ', '.', ',') }}</h5>
              </div>
              <router-link to="/user/checkout" class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Cart End -->
  </el-main>
</template>

<script>
import BaseValidate from '@/utils/BaseValidate'
import baseCommon from '@/utils/base-common'
import horizontalScroll from 'el-table-horizontal-scroll'
import userCommon from '@/views/user/Mixin/user-mixin'
import PageHeader from '@/views/user/component/PageHeader'
import CartRow from '@/views/user/component/CartRow'

export default {
  name: 'DetailProduct',
  directives: {
    horizontalScroll
  },
  components: { PageHeader, CartRow },
  mixins: [baseCommon, userCommon, BaseValidate],
  data() {
    return {
      size: '',
      color: '',
      quantity: 1
    }
  },
  methods: {
    quantityReduce(index) {
      if (this.productCart[index].quantity <= 1) {
        this.productCart[index].quantity = 1
      } else {
        this.productCart[index].quantity--
      }
    },
    quantityIncrease(index) {
      if (this.productCart[index].quantity >= this.product.quantity) {
        this.productCart[index].quantity = this.product.quantity
      } else {
        this.productCart[index].quantity++
      }
    },
    changeValCartRow(quantity, index) {
      quantity = String(quantity).replace(/\D/g, '')
      if (quantity > this.product.quantity) {
        quantity = this.product.quantity
      }
      if (quantity <= 1) {
        quantity = 1
      }
      this.productCart[index].quantity = quantity
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
</style>
