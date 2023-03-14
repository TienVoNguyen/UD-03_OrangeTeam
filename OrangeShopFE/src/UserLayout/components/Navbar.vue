<template>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <!-- Container wrapper -->
    <div class="container-fluid">
      <!-- Toggle button -->
      <button
        class="navbar-toggler"
        type="button"
        data-mdb-toggle="collapse"
        data-mdb-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <i class="fas fa-bars" />
      </button>

      <!-- Collapsible wrapper -->
      <div id="navbarSupportedContent" class="collapse navbar-collapse">
        <!-- Navbar brand -->
        <router-link class="navbar-brand mt-2 mt-lg-0" to="/user/home">
          <img
            src="@/assets/img/sanvadio.png"
            class="w-25"
            alt="MDB Logo"
            loading="lazy"
          >
        </router-link>
        <!-- Left links -->
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <router-link class="nav-item" to="/home">
            <span class="nav-link">Trang chủ</span>
          </router-link>
          <li class="nav-item">
            <span class="nav-link">Danh mục</span>
          </li>
          <li class="nav-item">
            <span class="nav-link">Liên hệ</span>
          </li>
          <router-link v-if="dashboardVisible" class="nav-item" to="/dashboard">
            <span class="nav-link">Dashboard</span>
          </router-link>
        </ul>
        <!-- Left links -->
      </div>
      <!-- Collapsible wrapper -->
      <!-- Right elements -->
      <div class="d-flex align-items-center">
        <form class="input-group w-auto">
          <input
            type="search"
            class="form-control rounded"
            placeholder="Search your product"
          >
        </form>
        <template v-if="token">
          <!-- Icon -->
          <router-link class="text-reset me-3 ml-1" to="/user/shopping-cart">
            <i class="fas fa-shopping-cart" />
            <span class="badge rounded-pill badge-notification bg-danger">{{ getCount }}</span>
          </router-link>
          <!-- Notifications -->
          <div class="dropdown">
            <a
              id="navbarDropdownMenuLink"
              class="text-reset me-3 dropdown-toggle hidden-arrow"
              href="#"
              role="button"
              data-mdb-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="fas fa-bell" />
              <span class="badge rounded-pill badge-notification bg-danger">1</span>
            </a>
            <ul
              class="dropdown-menu dropdown-menu-end"
              aria-labelledby="navbarDropdownMenuLink"
            >
              <li>
                <a class="dropdown-item" href="#">Some news</a>
              </li>
              <li>
                <a class="dropdown-item" href="#">Another news</a>
              </li>
              <li>
                <a class="dropdown-item" href="#">Something else here</a>
              </li>
            </ul>
          </div>
          <!-- Avatar -->
          <div class="dropdown">
            <a
              id="navbarDropdownMenuAvatar"
              class="dropdown-toggle d-flex align-items-center hidden-arrow"
              href="#"
              role="button"
              data-mdb-toggle="dropdown"
              aria-expanded="false"
            >
              <img
                :src="avatar+'?imageView2/1/w/80/h/80'"
                class="rounded-circle"
                height="25"
                alt="Black and White Portrait of a Man"
                loading="lazy"
              >
            </a>
            <ul
              class="dropdown-menu dropdown-menu-end"
              aria-labelledby="navbarDropdownMenuAvatar"
            >
              <router-link to="/user/profile">
                <a class="dropdown-item" href="#">My profile</a>
              </router-link>
              <li>
                <a class="dropdown-item" href="#">Settings</a>
              </li>
              <li @click="logout">
                <span class="dropdown-item">Logout</span>
              </li>
            </ul>
          </div>
        </template>
        <template v-if="!token">
          <button type="button" class="btn btn-link px-3 me-2" @click="login">
            Login
          </button>
          <button type="button" class="btn btn-primary me-3">
            Sign up for free
          </button>
          <a
            class="btn btn-dark px-3"
            href="https://github.com/mdbootstrap/mdb-ui-kit"
            role="button"
          ><i class="fab fa-github" /></a>
        </template>
      </div>
      <!-- Right elements -->
    </div>
    <!-- Container wrapper -->
  </nav>
  <!-- Navbar -->
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  components: {
  },
  data() {
    return {
      items: []
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device',
      'roles',
      'token',
      'cart'
    ]),
    dashboardVisible() {
      return !this.roles.length < 1 && !this.roles.includes('user')
    },
    getCount() {
      // console.log(this.cart)
      return this.cart.map(item => item.quantity)
        // eslint-disable-next-line no-return-assign
        .reduce((total, quantity) => total += quantity, 0)
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      const path = this.$route.fullPath
      if (!path.startsWith('/home') && !path.startsWith('/product')) {
        this.$router.push('/home')
      }
    },
    login() {
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.right-menu {
  float: right;
  height: 100%;
  line-height: 30px;

  &:focus {
    outline: none;
  }

  .right-menu-item {
    display: inline-block;
    padding: 0 8px;
    height: 100%;
    font-size: 18px;
    color: #5a5e66;
    vertical-align: text-bottom;

    &.hover-effect {
      cursor: pointer;
      transition: background .3s;

      &:hover {
        background: rgba(0, 0, 0, .025)
      }
    }
  }

  .avatar-container {
    margin-right: 30px;

    .avatar-wrapper {
      //margin-top: 5px;
      position: relative;

      .user-avatar {
        cursor: pointer;
        width: 30px;
        height: 30px;
        border-radius: 10px;
      }

      .el-icon-caret-bottom {
        cursor: pointer;
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }
}

</style>
