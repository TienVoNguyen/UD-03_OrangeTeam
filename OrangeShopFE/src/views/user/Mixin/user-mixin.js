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
      productDetail: [
        {
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
          img: 'product-6.jpg'
        },
        {
          id: 2,
          name: 'Colorful Stylish Shirt',
          sizes: [
            { id: 5, name: 'XL' }
          ],
          colors: [
            { id: 1, name: 'Black' },
            { id: 2, name: 'Blue' },
            { id: 4, name: 'Sky' },
            { id: 5, name: 'Green' }
          ],
          price1: '232312',
          price2: '203233',
          img: 'product-6.jpg'
        },
        {
          id: 3,
          name: 'Colorful Stylish Shirt 3',
          sizes: [
            { id: 1, name: 'XS' },
            { id: 2, name: 'S' },
            { id: 3, name: 'M' },
            { id: 4, name: 'L' },
            { id: 5, name: 'XL' }
          ],
          colors: [
            { id: 4, name: 'Sky' },
            { id: 5, name: 'Green' }
          ],
          price1: '232312',
          price2: '203233',
          img: 'product-6.jpg'
        },
        {
          id: 4,
          name: 'Colorful Stylish Shirt 4',
          sizes: [
            { id: 1, name: 'XS' },
            { id: 2, name: 'S' }
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
          img: 'product-6.jpg'
        },
        {
          id: 5,
          name: 'Colorful Stylish Shirt 5',
          sizes: [
            { id: 1, name: 'XS' },
            { id: 2, name: 'S' },
            { id: 5, name: 'XL' }
          ],
          colors: [
            { id: 4, name: 'Sky' },
            { id: 5, name: 'Green' }
          ],
          price1: '232312',
          price2: '203233',
          img: 'product-6.jpg'
        },
        {
          id: 6,
          name: 'Colorful Stylish Shirt 6',
          sizes: [
            { id: 1, name: 'XS' },
            { id: 2, name: 'S' },
            { id: 4, name: 'L' },
            { id: 5, name: 'XL' }
          ],
          colors: [
            { id: 1, name: 'Black' },
            { id: 2, name: 'Blue' },
            { id: 3, name: 'Yellow' },
            { id: 4, name: 'Sky' }
          ],
          price1: '232312',
          price2: '203233',
          img: 'product-6.jpg'
        },
        {
          id: 7,
          name: 'Colorful Stylish Shirt 7',
          sizes: [
            { id: 1, name: 'XS' },
            { id: 2, name: 'S' },
            { id: 3, name: 'M' },
            { id: 5, name: 'XL' }
          ],
          colors: [
            { id: 1, name: 'Black' },
            { id: 5, name: 'Green' }
          ],
          price1: '232312',
          price2: '203233',
          img: 'product-6.jpg'
        },
        {
          id: 8,
          name: 'Colorful Stylish Shirt 8',
          sizes: [
            { id: 1, name: 'XS' },
            { id: 4, name: 'L' },
            { id: 5, name: 'XL' }
          ],
          colors: [
            { id: 1, name: 'Black' },
            { id: 2, name: 'Blue' },
            { id: 3, name: 'Yellow' }
          ],
          price1: '232312',
          price2: '203233',
          img: 'product-6.jpg'
        }
      ],
      sizes: [],
      colors: [],
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
      res.data.result.forEach(product => {
        product.productDetails.forEach(options => {
          options.variationOptions.forEach(option => {
            if (option.variation.name === 'Color') {
              this.colors.push(option.value)
            } else if (option.variation.name === 'Size') {
              this.sizes.push(option.value)
            }
          })
        })
      })
    })
    console.log(1, this.colors)
    console.log(2, this.sizes)
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
