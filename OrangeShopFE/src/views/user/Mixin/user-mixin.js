import { mapGetters } from 'vuex'
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
        price2: '203233'
      },
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
          price2: '203233'
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
          price2: '203233'
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
          price2: '203233'
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
          price2: '203233'
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
          price2: '203233'
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
          price2: '203233'
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
          price2: '203233'
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
          price2: '203233'
        }
      ],
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
    ])
  },
  created() {
  },
  methods: {
    addToCart(data) {
      if (!this.token) {
        this.$router.push(`/login?redirect=${this.$route.fullPath}`)
      } else {
        console.log(data)
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
