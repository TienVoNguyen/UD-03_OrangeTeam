<template>
  <el-main>
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in 6" :key="item">
        <h3 class="medium d-flex justify-content-center">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
    <!-- Products Start -->
    <div class="container-fluid pt-5">
      <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Thịnh hành</span></h2>
      </div>
      <div class="row px-xl-2 pb-3">
        <product-card v-for="(product, index) in productDetail" :key="index" :product="product" />
      </div>
    </div>
    <!-- Products End -->
    <!-- Subscribe Start -->
    <div class="container-fluid bg-info my-5">
      <div class="row justify-content-md-center py-5 px-xl-5">
        <div class="col-md-6 col-12 py-5">
          <div class="text-center mb-2 pb-2">
            <h2 class="section-title px-5 mb-3"><span class="bg-info px-2">Đăng ký</span></h2>
            <p>Amet lorem at rebum amet dolores. Elitr lorem dolor sed amet diam labore at justo ipsum eirmod duo labore labore.</p>
          </div>
          <ValidationProvider v-slot="{ errors, invalid, handleSubmit }" rules="email">
            <div class="input-group">
              <input v-model="registerEmail" type="text" class="form-control border-white p-4" placeholder="Email của bạn">
              <div class="input-group-append">
                <el-button class="btn btn-primary px-4" :disabled="invalid || !registerEmail" @click="handleSubmit(registerUpdate)">Gửi</el-button>
              </div>
            </div>
            <p class="text-danger">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>
      </div>
    </div>
    <!-- Subscribe End -->
    <div class="container-fluid pt-5">
      <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Mới cập nhật</span></h2>
      </div>
      <div class="row px-xl-2 pb-3">
        <product-card v-for="(product, index) in productDetail" :key="index" :product="product" />
      </div>
    </div>
  </el-main>
</template>

<script>
import userCommon from '@/views/user/Mixin/user-mixin'
import BaseValidate from '@/utils/BaseValidate'
import baseCommon from '@/utils/base-common'
import horizontalScroll from 'el-table-horizontal-scroll'
import ProductCard from '@/views/user/component/ProductCard'
import { ValidationProvider } from 'vee-validate'
export default {
  name: 'Home',
  directives: {
    horizontalScroll
  },
  components: { ValidationProvider, ProductCard },
  mixins: [baseCommon, userCommon, BaseValidate],
  data() {
    return {
      registerEmail: '',
      color: '',
      size: ''
    }
  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.cat-item .cat-img img,
.product-item .product-img img {
  transition: .5s;
}

.cat-item:hover .cat-img img,
.product-item:hover .product-img img {
  transform: scale(1.2);
}

.product-item .linkCard:hover {
  color: #D19C97 !important;
  text-decoration: none;
  background: none !important;
}

.img-fluid {
  max-width: 100%;
  height: auto;
}

.section-title {
  position: relative;
  display: inline-block;
  letter-spacing: 1px;
  font-weight: bold;
  color: #1C1C1C;
}

.section-title span {
  position: relative;
  background: #ffffff;
  z-index: 1;
}

.section-title::before {
  position: absolute;
  content: "";
  top: 50%;
  left: 0;
  width: 100%;
  height: 2px;
  margin-top: -1px;
  background: #1C1C1C;
}
</style>
