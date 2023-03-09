import { mapGetters } from 'vuex'
import { getListProduct } from '@/api/product'
export default {
  data() {
    return {
      product: {
        id: 1,
        name: 'Colorful Stylish Shirt',
        sizes: [
          { id: 1, name: 'XS' },
          { id: 2, name: 'S' },
          { id: 3, name: 'M' },
          { id: 4, name: 'L' },
          { id: 5, name: 'XL' }
        ],
        colors: [
          { id: 1, name: 'Black' },
          { id: 2, name: 'Blue' },
          { id: 3, name: 'Yellow' },
          { id: 4, name: 'Sky' },
          { id: 5, name: 'Green' }
        ],
        price1: '232312',
        price2: '203233',
        quantity: 30
      },
      productCart: [
        {
          id: 1,
          name: 'Colorful Stylish Shirt',
          size: { id: 1, name: 'XL' },
          color: { id: 1, name: 'Black' },
          price1: '232312',
          price2: '203233',
          quantity: 30
        },
        {
          id: 2,
          name: 'Colorful Stylish Shirt 2',
          size: { id: 2, name: 'L' },
          color: { id: 2, name: 'Blue' },
          price1: '232312',
          price2: '203233',
          quantity: 23
        },
        {
          id: 3,
          name: 'Colorful Stylish Shirt 3',
          size: { id: 3, name: 'XXL' },
          color: { id: 3, name: 'Gray' },
          price1: '232312',
          price2: '203233',
          quantity: 23
        }
      ],
      productDetail: [],
      price1: '232312',
      price2: '203233'
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device',
      'roles',
      'token'
    ]),
    subTotal() {
      return this.productCart
        // eslint-disable-next-line no-return-assign
        .map(item => item.quantity * item.price2).reduce((total, qty) => total += qty, 0)
    }
  },
  created() {
    const params = {
      page: 1,
      size: 8
    }
    getListProduct(params).then(res => {
      this.productDetail = res.data.result
    })
  },
  methods: {
    addToCart(product, size, color, quantity) {
      if (!this.token) {
        this.$router.push(`/login?redirect=${this.$route.fullPath}`)
      } else {
        if (size && color) {
          if (!quantity) {
            quantity = 1
          }
          console.log(product, size, color, quantity)
        } else {
          this.notifyWarning('Cảnh báo', 'Hãy chọn Size và Color trước khi thêm vào giỏ hàng!')
        }
      }
    },
    registerUpdate() {
      console.log('dang ky thanh cong')
    },
    viewDetail(data) {
      this.$router.push(`/product`)
    }
  }
}
