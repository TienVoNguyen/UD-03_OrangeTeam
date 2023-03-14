<template>
  <tr>
    <td>
      <el-row>
        <el-col :span="6" class="align-middle">
          <img :src="imgProduct" alt="product img" style="width: 50px">
        </el-col>
        <el-col :span="18">
          <p class="font-weight-semi-bold">{{ cartRow.productName }}</p>
          <span><strong>Size:</strong> {{ cartRow.size }} </span> <span><strong>Color:</strong> {{ cartRow.color }}</span>
        </el-col>
      </el-row>
    </td>
    <td class="align-middle">{{ cartRow.price | currency('VND', 0, 'đ', '.', ',') }}</td>
    <td class="align-middle">
      <div class="input-group quantity mx-auto" style="width: 100px;">
        <div class="input-group-btn">
          <button class="btn btn-sm btn-primary btn-minus h-100" @click="reduce">
            <i class="fa fa-minus" />
          </button>
        </div>
        <input v-model="quantity" type="text" class="form-control form-control-sm bg-secondary text-center">
        <div class="input-group-btn">
          <button class="btn btn-sm btn-primary btn-plus h-100" @click="increase">
            <i class="fa fa-plus" />
          </button>
        </div>
      </div>
    </td>
    <td class="align-middle">{{ cartRow.price * cartRow.quantity | currency('VND', 0, 'đ', '.', ',') }}</td>
    <td class="align-middle"><button class="btn btn-sm btn-primary" @click="removeCartRow"><i class="fa fa-times" /></button></td>
  </tr>
</template>

<script>
import userCommon from '@/views/user/Mixin/user-mixin'
import { removeItem, updateQuantity } from '@/api/cart'
export default {
  mixins: [userCommon],
  props: {
    cartRow: {
      type: Object,
      require: true,
      default(rawProps) {
        return { message: 'Truyền vào cart row' }
      }
    },
    index: {
      type: Number,
      require: true,
      default(rawProps) {
        return { message: 'Truyền vào index' }
      }
    }
  },
  data() {
    return {
      quantity: 1
    }
  },
  computed: {
    imgProduct() {
      return require(`@/assets/pictures/${this.cartRow.image}`)
    }
  },
  watch: {
    quantity: function(newVal, oldVal) {
      this.quantity = String(this.quantity).replace(/\D/g, '')
      if (newVal > this.cartRow.quantity) {
        this.quantity = this.cartRow.quantity
      }
      if (newVal <= 1) {
        this.quantity = 1
      }
    }
  },
  created() {
    this.quantity = this.cartRow.quantity
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    async reduce() {
      this.quantity--
      if (this.quantity < 1) this.quantity = 1
      const params = {
        id: this.cartRow.productDetailId,
        qty: this.quantity
      }
      updateQuantity(params).then(
        await this.$store.dispatch('cart/getCart')
      )
    },
    increase() {
      this.$emit('increase', this.index)
      this.quantity = this.cartRow.quantity
    },
    async removeCartRow() {
      removeItem(this.cartRow.productDetailId).then(
        await this.$store.dispatch('cart/getCart')
      )
    }
  }
}
</script>

<style scoped>

</style>
