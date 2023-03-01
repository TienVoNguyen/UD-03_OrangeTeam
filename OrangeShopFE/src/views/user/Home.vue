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
      <div class="row px-xl-5 pb-3">
        <div v-for="(o, index) in 8" :key="index" class="col-lg-3 col-md-6 col-sm-12 pb-1">
          <div class="card product-item border-0 mb-4">
            <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
              <img class="img-fluid w-100" src="@/assets/img/product-6.jpg" alt="">
            </div>
            <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
              <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>
              <div class="d-flex justify-content-center">
                <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>
              </div>
            </div>
            <div class="card-footer d-flex justify-content-between bg-light border">
              <router-link to="" class="linkCard text-dark p-0"><i class="fas fa-eye mr-1" style="color: #D19C97" />View Detail</router-link>
              <router-link to="" class="linkCard text-dark p-0"><i class="fas fa-shopping-cart mr-1" style="color: #D19C97" />Add To Cart</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Products End -->
  </el-main>
</template>

<script>
export default {
  name: 'Home'
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
