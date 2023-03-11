<template>
  <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
    <div class="card product-item border-0 mb-4 d-flex align-items-stretch">
      <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0" style="max-height: 26em">
        <img class="img-fluid w-100" :src="imgProduct" alt="">
      </div>
      <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
        <div class="d-flex justify-content-center" style="min-height: 5em">
          <router-link :to="'/product/view-detail/' + productCard.id"><h6 class="text-truncate mb-3">{{ productCard.name }}</h6></router-link>
        </div>
        <div class="d-flex justify-content-center mb-3">
          <h6>{{ productPriceSale | currency('VND', 0, 'đ', '.', ',') }}</h6><h6 class="text-muted ml-2"><del>{{ productPriceDefault | currency('VND', 0, 'đ', '.', ',') }}</del></h6>
        </div>
        <div class="d-flex justify-content-center mb-2">
          <el-radio-group v-model="selectedSize" size="mini" fill="#D19C97">
            <el-radio-button v-for="(s) in sizes.value" :key="s" :label="s" />
          </el-radio-group>
        </div>
        <div class="d-flex justify-content-center align-items-end w-100">
          <el-radio-group v-model="selectedColor" size="mini" fill="#D19C97">
            <el-radio-button v-for="(c) in colors.value" :key="c" :label="c" />
          </el-radio-group>
        </div>
      </div>
      <div class="card-footer d-flex justify-content-between bg-light border">
        <el-button type="button" class="linkCard text-dark p-0 border-0 bg-light" @click="viewDetail(product)">
          <i class="fas fa-eye mr-1" style="color: #D19C97" />View Detail
        </el-button>
        <el-button type="button" class="linkCard text-dark p-0 border-0 bg-light" @click="addToCart(productAddToCard, selectedSize, selectedColor)">
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
    productCard: {
      type: Object,
      require: true,
      default(rawProps) {
        return { message: 'Truyền vào product' }
      }
    }
  },
  data() {
    return {
      selectedSize: '',
      selectedColor: '',
      sizes: [],
      colors: [],
      variationOptions: [],
      productAddToCard: {
        id: '',
        name: '',
        priceSale: 0,
        priceDefault: 0,
        quantity: 1
      }
    }
  },
  computed: {
    ...mapGetters([
      'avatar',
      'token'
    ]),
    imgProduct() {
      return require(`@/assets/pictures/${this.productCard.defaultImage}`)
    },
    productPriceSale() {
      return this.productAddToCard.priceSale === 0 ? this.productCard.productDetails[0].priceSale : this.productAddToCard.priceSale
    },
    productPriceDefault() {
      return this.productAddToCard.priceDefault === 0 ? this.productCard.productDetails[0].priceDefault : this.productAddToCard.priceDefault
    }
  },
  watch: {
    selectedSize: function() {
      this.selectProductDetail()
    },
    selectedColor: function() {
      this.selectProductDetail()
    }
  },
  created() {
    console.log(this.productCard)
    this.getProductVariations()
    this.productAddToCard.name = this.productCard.name
    this.productAddToCard.priceSale = this.productCard.productDetails[0].priceSale
    this.productAddToCard.priceDefault = this.productCard.productDetails[0].priceDefault
    console.log(1, this.productAddToCard)
    console.log(2, this.productCard.productDetails[0])
  },
  methods: {
    viewDetail(data) {
      this.$router.push(`/product`)
    },
    getProductVariations() {
      const options = {}
      let productVariations = []
      for (let i = 0; i < this.productCard.productDetails.length; i++) {
        const variationOptions = this.productCard.productDetails[i].variationOptions

        for (let j = 0; j < variationOptions.length; j++) {
          const variation = variationOptions[j].variation
          const value = variationOptions[j].value
          if (!options[variation.name]) {
            options[variation.name] = []
          }
          options[variation.name].push(value)
        }
      }
      productVariations = options

      for (const property in productVariations) {
        const obj = {}
        obj.name = property
        obj.value = productVariations[property].filter((item, index) => {
          return productVariations[property].indexOf(item) === index
        })
        this.variationOptions.push(obj)
      }
      this.sizes = this.variationOptions[0]
      this.colors = this.variationOptions[1]
      // console.log(this.variationOptions)
    },
    selectProductDetail() {
      if (this.selectedSize && this.selectedColor) {
        // Duyệt qua các phần tử trong mảng "productDetails"
        for (let i = 0; i < this.productCard.productDetails.length; i++) {
          const detail = this.productCard.productDetails[i]
          let hasSizeM = false
          let hasColorBlack = false

          // Kiểm tra xem phần tử có tuỳ chọn size 'M' hay không
          for (let j = 0; j < detail.variationOptions.length; j++) {
            if (detail.variationOptions[j].value === this.selectedSize && detail.variationOptions[j].variation.name === this.sizes.name) {
              hasSizeM = true
              break
            }
          }

          // Kiểm tra xem phần tử có tuỳ chọn color 'Black' hay không
          for (let j = 0; j < detail.variationOptions.length; j++) {
            if (detail.variationOptions[j].value === this.selectedColor && detail.variationOptions[j].variation.name === this.colors.name) {
              hasColorBlack = true
              break
            }
          }

          // Nếu phần tử có cả hai tuỳ chọn này, lấy thông tin chi tiết của phần tử đó
          if (hasSizeM && hasColorBlack) {
            this.productAddToCard.priceSale = detail.priceSale
            this.productAddToCard.priceDefault = detail.priceDefault
            this.productAddToCard.id = detail.id
            break
          }
        }
      }
    }
  }
}
</script>

<style>
.el-radio-button--mini .el-radio-button__inner {
  padding: 5px 12px;
}
.product-img img {
  transition: .5s;
}

.product-img img:hover {
  transform: scale(1.2);
}

.product-item .linkCard:hover {
  color: #D19C97 !important;
  text-decoration: none;
  background: none !important;
}

.text-truncate {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
  text-align: center;
}

</style>
