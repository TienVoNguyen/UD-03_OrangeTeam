<template>
  <el-main>
    <!-- Page Header Start -->
    <el-page-header class="m-auto" @back="goBack">
      <template v-slot:content>
        <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />
      </template>
    </el-page-header>
    <!-- Page Header End -->
    <!-- Shop Detail Start -->
    <div class="container-fluid py-5">
      <div class="row px-xl-5">
        <div class="col-lg-5 pb-5 border">
          <el-carousel>
            <el-carousel-item>
              <img class="w-100 h-100" src="@/assets/img/product-1.jpg" alt="Image">
            </el-carousel-item>
            <el-carousel-item class="w-100 h-100">
              <img class="w-100 h-100" src="@/assets/img/product-2.jpg" alt="Image">
            </el-carousel-item>
            <el-carousel-item class="w-100 h-100">
              <img class="w-100 h-100" src="@/assets/img/product-3.jpg" alt="Image">
            </el-carousel-item>
            <el-carousel-item class="w-100 h-100">
              <img class="w-100 h-100" src="@/assets/img/product-4.jpg" alt="Image">
            </el-carousel-item>
          </el-carousel>
        </div>

        <div class="col-lg-7 pb-5">
          <h3 class="font-weight-semi-bold">Colorful Stylish Shirt</h3>
          <div class="d-flex mb-3">
            <div class="text-primary mr-2">
              <small class="fas fa-star" />
              <small class="fas fa-star" />
              <small class="fas fa-star" />
              <small class="fas fa-star-half-alt" />
              <small class="far fa-star" />
            </div>
            <small class="pt-1">(50 Reviews)</small>
          </div>
          <h3 class="font-weight-semi-bold mb-4">$150.00</h3>
          <p class="mb-4 text-justify">Volup erat ipsum diam elitr rebum et dolor. Est nonumy elitr erat diam stet sit clita ea.<br>
            Sanc invidunt ipsum et, labore clita lorem magna lorem ut. Erat lorem duo dolor no sea nonumy.
            Accus labore stet, est lorem sit diam sea et justo, amet at lorem et eirmod ipsum diam et rebum kasd rebum.</p>
          <div class="d-flex mb-3">
            <p class="text-dark font-weight-medium mb-0 mr-3">Sizes:</p>
            <el-radio-group v-model="size" style="padding-top: .4rem">
              <el-radio v-for="(s, index) in product.sizes" :key="index" :label="s.id" class="mb-0 font-weight-bold">{{ s.name }}</el-radio>
            </el-radio-group>
          </div>
          <div class="d-flex mb-4">
            <p class="text-dark font-weight-medium mb-0 mr-3">Colors:</p>
            <el-radio-group v-model="color" style="padding-top: .4rem">
              <el-radio v-for="(c, index) in product.colors" :key="index" :label="c.id" class="mb-0 font-weight-bold">{{ c.name }}</el-radio>
            </el-radio-group>
          </div>
          <div class="d-flex align-items-center mb-4 pt-2">
            <div class="input-group quantity mr-3" style="width: 130px;">
              <div class="input-group-btn">
                <button class="btn btn-primary btn-minus h-100">
                  <i class="fa fa-minus" />
                </button>
              </div>
              <input type="text" class="form-control bg-secondary text-center" value="1">
              <div class="input-group-btn">
                <button class="btn btn-primary btn-plus h-100">
                  <i class="fa fa-plus" />
                </button>
              </div>
            </div>
            <button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1" /> Add To Cart</button>
          </div>
          <div class="d-flex pt-2">
            <p class="text-dark font-weight-medium mb-0 mr-2">Share on:</p>
            <div class="d-inline-flex">
              <a class="text-dark px-2" href="">
                <i class="fab fa-facebook-f" />
              </a>
              <a class="text-dark px-2" href="">
                <i class="fab fa-twitter" />
              </a>
              <a class="text-dark px-2" href="">
                <i class="fab fa-linkedin-in" />
              </a>
              <a class="text-dark px-2" href="">
                <i class="fab fa-pinterest" />
              </a>
            </div>
          </div>
        </div>
      </div>
      <div class="row px-xl-5">
        <div class="col">
          <div class="nav nav-tabs justify-content-center border-secondary mb-4">
            <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Description</a>
            <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-2">Information</a>
            <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Reviews (0)</a>
          </div>
          <div class="tab-content">
            <div class="tab-pane fade show active" id="tab-pane-1">
              <h4 class="mb-3">Product Description</h4>
              <p>Eos no lorem eirmod diam diam, eos elitr et gubergren diam sea. Consetetur vero aliquyam invidunt duo dolores et duo sit. Vero diam ea vero et dolore rebum, dolor rebum eirmod consetetur invidunt sed sed et, lorem duo et eos elitr, sadipscing kasd ipsum rebum diam. Dolore diam stet rebum sed tempor kasd eirmod. Takimata kasd ipsum accusam sadipscing, eos dolores sit no ut diam consetetur duo justo est, sit sanctus diam tempor aliquyam eirmod nonumy rebum dolor accusam, ipsum kasd eos consetetur at sit rebum, diam kasd invidunt tempor lorem, ipsum lorem elitr sanctus eirmod takimata dolor ea invidunt.</p>
              <p>Dolore magna est eirmod sanctus dolor, amet diam et eirmod et ipsum. Amet dolore tempor consetetur sed lorem dolor sit lorem tempor. Gubergren amet amet labore sadipscing clita clita diam clita. Sea amet et sed ipsum lorem elitr et, amet et labore voluptua sit rebum. Ea erat sed et diam takimata sed justo. Magna takimata justo et amet magna et.</p>
            </div>
            <div class="tab-pane fade" id="tab-pane-2">
              <h4 class="mb-3">Additional Information</h4>
              <p>Eos no lorem eirmod diam diam, eos elitr et gubergren diam sea. Consetetur vero aliquyam invidunt duo dolores et duo sit. Vero diam ea vero et dolore rebum, dolor rebum eirmod consetetur invidunt sed sed et, lorem duo et eos elitr, sadipscing kasd ipsum rebum diam. Dolore diam stet rebum sed tempor kasd eirmod. Takimata kasd ipsum accusam sadipscing, eos dolores sit no ut diam consetetur duo justo est, sit sanctus diam tempor aliquyam eirmod nonumy rebum dolor accusam, ipsum kasd eos consetetur at sit rebum, diam kasd invidunt tempor lorem, ipsum lorem elitr sanctus eirmod takimata dolor ea invidunt.</p>
              <div class="row">
                <div class="col-md-6">
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item px-0">
                      Sit erat duo lorem duo ea consetetur, et eirmod takimata.
                    </li>
                    <li class="list-group-item px-0">
                      Amet kasd gubergren sit sanctus et lorem eos sadipscing at.
                    </li>
                    <li class="list-group-item px-0">
                      Duo amet accusam eirmod nonumy stet et et stet eirmod.
                    </li>
                    <li class="list-group-item px-0">
                      Takimata ea clita labore amet ipsum erat justo voluptua. Nonumy.
                    </li>
                  </ul>
                </div>
                <div class="col-md-6">
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item px-0">
                      Sit erat duo lorem duo ea consetetur, et eirmod takimata.
                    </li>
                    <li class="list-group-item px-0">
                      Amet kasd gubergren sit sanctus et lorem eos sadipscing at.
                    </li>
                    <li class="list-group-item px-0">
                      Duo amet accusam eirmod nonumy stet et et stet eirmod.
                    </li>
                    <li class="list-group-item px-0">
                      Takimata ea clita labore amet ipsum erat justo voluptua. Nonumy.
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div id="tab-pane-3" class="tab-pane fade">
              <div class="row">
                <div class="col-md-6">
                  <h4 class="mb-4">1 review for "Colorful Stylish Shirt"</h4>
                  <div class="media mb-4">
                    <img src="@/assets/img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                    <div class="media-body">
                      <h6>John Doe<small> - <i>01 Jan 2045</i></small></h6>
                      <div class="text-primary mb-2">
                        <i class="fas fa-star" />
                        <i class="fas fa-star" />
                        <i class="fas fa-star" />
                        <i class="fas fa-star-half-alt" />
                        <i class="far fa-star" />
                      </div>
                      <p>Diam amet duo labore stet elitr ea clita ipsum, tempor labore accusam ipsum et no at. Kasd diam tempor rebum magna dolores sed sed eirmod ipsum.</p>
                    </div>
                  </div>
                </div>
                <div class="col-md-6">
                  <h4 class="mb-4">Leave a review</h4>
                  <small>Your email address will not be published. Required fields are marked *</small>
                  <div class="d-flex my-3">
                    <p class="mb-0 mr-2">Your Rating * :</p>
                    <div class="text-primary">
                      <i class="far fa-star" />
                      <i class="far fa-star" />
                      <i class="far fa-star" />
                      <i class="far fa-star" />
                      <i class="far fa-star" />
                    </div>
                  </div>
                  <form>
                    <div class="form-group">
                      <label for="message">Your Review *</label>
                      <textarea id="message" cols="30" rows="5" class="form-control"></textarea>
                    </div>
                    <div class="form-group">
                      <label for="name">Your Name *</label>
                      <input type="text" class="form-control" id="name">
                    </div>
                    <div class="form-group">
                      <label for="email">Your Email *</label>
                      <input type="email" class="form-control" id="email">
                    </div>
                    <div class="form-group mb-0">
                      <input type="submit" value="Leave Your Review" class="btn btn-primary px-3">
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Shop Detail End -->
  </el-main>
</template>

<script>
import Breadcrumb from '@/views/user/component/Breadcrumb/index.vue'
import BaseValidate from '@/utils/BaseValidate'
import baseCommon from '@/utils/base-common'
import horizontalScroll from 'el-table-horizontal-scroll'
import { ValidationObserver, ValidationProvider } from 'vee-validate'
import userCommon from '@/views/user/Mixin/user-mixin'
export default {
  name: 'DetailProduct',
  directives: {
    horizontalScroll
  },
  components: { ValidationObserver, ValidationProvider, Breadcrumb },
  mixins: [baseCommon, userCommon, BaseValidate],
  data() {
    return {
      size: '',
      color: ''
    }
  },
  methods: {
    goBack() {
      console.log('back')
    }
  }
}
</script>

<style>
.el-page-header__title {
  margin: auto !important;
}
.breadcrumb-container {
  padding-top: .5rem;
}
.el-carousel, .el-carousel__container {
  width: 100% !important;
  height: 100% !important;
}
.font-weight-medium {
  font-weight: 500;
}
.font-weight-semi-bold {
  font-weight: 600;
}
.text-primary {
  color: #D19C97 !important;
}
.btn-primary {
  color: #212529;
  background-color: #D19C97;
  border-color: #D19C97;
}

.btn-primary:hover {
  color: #fff;
  background-color: #c5837c;
  border-color: #c17a74;
}

.btn-primary:focus, .btn-primary.focus {
  color: #fff;
  background-color: #c5837c;
  border-color: #c17a74;
  box-shadow: 0 0 0 0.2rem rgba(183, 138, 135, 0.5);
}

.btn-primary.disabled, .btn-primary:disabled {
  color: #212529;
  background-color: #D19C97;
  border-color: #D19C97;
}

.btn-primary:not(:disabled):not(.disabled):active, .btn-primary:not(:disabled):not(.disabled).active,
.show > .btn-primary.dropdown-toggle {
  color: #fff;
  background-color: #c17a74;
  border-color: #bd726b;
}

.btn-primary:not(:disabled):not(.disabled):active:focus, .btn-primary:not(:disabled):not(.disabled).active:focus,
.show > .btn-primary.dropdown-toggle:focus {
  box-shadow: 0 0 0 0.2rem rgba(183, 138, 135, 0.5);
}
.bg-secondary {
  background-color: #EDF1FF !important;
}

a.bg-secondary:hover, a.bg-secondary:focus,
button.bg-secondary:hover,
button.bg-secondary:focus {
  background-color: #bac9ff !important;
}

@media (prefers-reduced-motion: reduce) {
  .form-control {
    transition: none;
  }
}

.form-control::-ms-expand {
  background-color: transparent;
  border: 0;
}

.form-control:-moz-focusring {
  color: transparent;
  text-shadow: 0 0 0 #495057;
}

.form-control:focus {
  color: #495057;
  background-color: #fff;
  border-color: #f8f0ef;
  outline: 0;
  box-shadow: none;
}

.form-control::placeholder {
  color: #999999;
  opacity: 1;
}

.form-control:disabled, .form-control[readonly] {
  background-color: #e9ecef;
  opacity: 1;
}

input[type="date"].form-control,
input[type="time"].form-control,
input[type="datetime-local"].form-control,
input[type="month"].form-control {
  appearance: none;
}

</style>
