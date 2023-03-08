<template>
  <tr>
    <td>
      <el-row>
        <el-col :span="6" class="align-middle">
          <img src="@/assets/img/product-1.jpg" alt="product img" style="width: 50px">
        </el-col>
        <el-col :span="18">
          <p class="font-weight-semi-bold">{{ cartRow.name }}</p>
          <span><strong>Size:</strong> {{ cartRow.size.name }} </span> <span><strong>Color:</strong> {{ cartRow.color.name }}</span>
        </el-col>
      </el-row>
    </td>
    <td class="align-middle">{{ cartRow.price2 | currency('VND', 0, 'đ', '.', ',') }}</td>
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
    <td class="align-middle">{{ cartRow.price2 * quantity | currency('VND', 0, 'đ', '.', ',') }}</td>
    <td class="align-middle"><button class="btn btn-sm btn-primary" @click="removeCartRow"><i class="fa fa-times" /></button></td>
  </tr>
</template>

<script>
import userCommon from '@/views/user/Mixin/user-mixin'
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
  watch: {
    quantity: function(newVal, oldVal) {
      this.$emit('watchCartRow', newVal, this.index)
      this.quantity = this.cartRow.quantity
    }
  },
  created() {
    this.quantity = this.cartRow.quantity
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    reduce() {
      this.$emit('reduce', this.index)
      this.quantity = this.cartRow.quantity
    },
    increase() {
      this.$emit('increase', this.index)
      this.quantity = this.cartRow.quantity
    },
    removeCartRow() {
      console.log('remove')
    }
  }
}
</script>

<style scoped>

</style>
