<template>
  <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
    <div class="card product-item border-0 mb-4">
      <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
        <img class="img-fluid w-100" src="@/assets/img/product-6.jpg" alt="">
        <h6 class="text-truncate mb-3 d-flex justify-content-center">{{ product.name }}</h6>
        <div class="d-flex justify-content-center">
          <h6>{{ product.price2 | currency('VND', 0, 'đ', '.', ',') }}</h6><h6 class="text-muted ml-2"><del>{{ product.price1 | currency('VND', 0, 'đ', '.', ',') }}</del></h6>
        </div>
      </div>
      <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
        <div class="d-flex justify-content-center">
          <el-radio-group v-model="selectedSize" size="mini" fill="#D19C97" @change="chooseSize">
            <el-radio-button v-for="(s) in product.sizes" :key="s.id" :label="s.id">{{ s.name }}</el-radio-button>
          </el-radio-group>
        </div>
        <div class="d-flex justify-content-center w-100">
          <el-radio-group v-model="selectedColor" size="mini" fill="#D19C97" @change="chooseColor">
            <el-radio-button v-for="(c) in product.colors" :key="c.id" :label="c.id">{{ c.name }}</el-radio-button>
          </el-radio-group>
        </div>
      </div>
      <div class="card-footer d-flex justify-content-between bg-light border">
        <el-button type="button" class="linkCard text-dark p-0 border-0 bg-light" @click="viewDetail(product)">
          <i class="fas fa-eye mr-1" style="color: #D19C97" />View Detail
        </el-button>
        <el-button type="button" class="linkCard text-dark p-0 border-0 bg-light" @click="addToCart(product, selectedSize, selectedColor)">
          <i class="fas fa-shopping-cart mr-1" style="color: #D19C97" />Add To Cart
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import baseCommon from '@/utils/base-common'
import userCommon from '@/views/user/Mixin/user-mixin'

export default {
  mixins: [baseCommon, userCommon],
  props: {
    product: {
      type: Object,
      require: true,
      default(rawProps) {
        return { message: 'Truyền vào product' }
      }
    }
  },
  data() {
    return {
      selectedSize: null,
      selectedColor: null
    }
  },
  computed: {
    ...mapGetters([
      'avatar',
      'token'
    ])
  },
  methods: {
    chooseSize() {
      console.log(this.selectedSize)
    },
    chooseColor() {
      console.log(this.selectedColor)
    },
    viewDetail(data) {
      this.$router.push(`/product`)
    }
  }
}
</script>

<style>
.el-radio-button--mini .el-radio-button__inner {
  padding: 5px 12px;
}
</style>
