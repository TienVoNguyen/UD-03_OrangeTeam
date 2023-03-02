<template>
  <div>
    <navbar />
    <app-main />
    <Footer />
    <el-tooltip placement="top" content="Back to top">
      <back-to-top :custom-style="myBackToTopStyle" :visibility-height="100" :back-position="10" transition-name="fade" />
    </el-tooltip>
  </div>
</template>

<script>
import { AppMain, Navbar, Footer } from './components'
import BackToTop from '@/components/BackToTop'
export default {
  name: 'UserLayout',
  components: {
    Navbar,
    AppMain,
    Footer,
    BackToTop
  },
  data() {
    return {
      // customizable button style, show/hide critical point, return position
      myBackToTopStyle: {
        right: '50px',
        bottom: '50px',
        width: '40px',
        height: '40px',
        'border-radius': '4px',
        'line-height': '45px', // 请保持与高度一致以垂直居中 Please keep consistent with height to center vertically
        background: '#e7eaf1'// 按钮的背景颜色 The background color of the button
      }
    }
  }
}
</script>

<style scoped>
.placeholder-container div {
  margin: 10px;
}
</style>
