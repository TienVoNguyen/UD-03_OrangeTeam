<template>
  <header id="collapsibleNavbar" class="head">
    <div class="fl">
      <div class="container">
        <div class="row">
          <div class="col-xl-6 col-lg-6 col-md-4 col-12 d-flex align-items-center sm-display-none">
            <!-- heading section start -->
            <div class="start-msg">
              <p class="no-margin-bottom text-white">Welcome to Libazz store!</p>
            </div>
            <!-- heading section end -->
          </div>
          <div class="col-xl-6 col-lg-6 col-md-8 col-12 d-flex align-items-center justify-content-end sm-justify-content-center">
            <!-- time section start -->
            <div class="select-arrow">
              <span class="currency">
                <select class="text-white">
                  <option class="text-black">$ Usd</option>
                  <option class="text-black">₹ Inr</option>
                  <option class="text-black">€ Eur</option>
                  <option class="text-black">£ Gbp</option>
                </select>
              </span>
              <span class="language">
                <select class="text-white">
                  <option class="text-black">English</option>
                  <option class="text-black">Hindi</option>
                  <option class="text-black">Eur</option>
                  <option class="text-black">Gbp</option>
                  <option class="text-black">Jpy</option>
                  <option class="text-black">Cad</option>
                  <option class="text-black">Aud</option>
                </select>
              </span>
            </div>
            <!-- time section end -->
            <!-- wishlist icon start -->
            <div class="wishlist">
              <div class="icon-margin">
                <a href="wishlist.html" class="middle">
                  <i class="far fa-heart" />
                  <span class="wish-count">3</span>
                  <span>Wishlist</span>
                </a>
              </div>
            </div>
            <!-- wishlist icon end -->
            <!-- user icon start -->
            <div class="users">
              <div class="icon-margin">
                <a class="middle" data-toggle="collapse" href="#useraccount">
                  <i class="far fa-user" />
                  <span>
                    <span class="account">My Account</span>
                  </span>
                </a>
                <!-- user icon attribute start -->
                <div id="useraccount" class="user-dropdown collapse">
                  <a href="signin.html" class="d-block">View Profile</a>
                  <a href="signin.html" class="d-block">Your Orders</a>
                  <a href="wishlist.html" class="d-block">Your Wishlist</a>
                  <a href="index.html" class="d-block">Logout</a>
                </div>
                <!-- user icon attribute end -->
              </div>
            </div>
            <!-- user icon end -->
          </div>
        </div>
      </div>
    </div>
    <div class="collapsibleNavbar">
      <div class="container">
        <div class="row">
          <div class="col-xl-3 col-lg-3 col-md-3 col-7 d-flex align-items-center xs-logo">
            <!-- header navbar start -->
            <nav id="navbar" class="navbar">
              <!-- navbar toggler start -->
              <button id="nav-icon" class="menu" type="button">
                <span class="menu-icon text-white" />
              </button>
              <!-- navbar toggler end -->
              <div id="navbar3" class="navbar-collapse">
                <!-- search icon start -->
                <div class="search d-none sm-display-block">
                  <input type="search" class="text-black" placeholder="Search your product">
                  <a href="search.html" class="text-black"><i class="ti-search" /></a>
                </div>
                <!-- search icon end -->
                <!-- header nav start -->
                <ul class="nav navbar-nav">
                  <!-- header-nav home start -->
                  <li class="active nav-home head-tab">
                    <a href="javaScript:void(0)">Home<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <div class="dropdown-tab">
                      <a href="home.html">Home layout 1</a>
                      <a href="home2.html">Home layout 2</a>
                      <a href="home3.html">Home layout 3</a>
                      <a href="home4.html">Home layout 4</a>
                      <a href="javaScript:void(0)">Home layout 5</a>
                      <a href="home6.html">Home layout 6</a>
                      <a href="home7.html">Home layout 7</a>
                    </div>
                  </li>
                  <!-- header-nav home end -->
                  <!-- header-nav blog start -->
                  <li class="nav-blog head-tab">
                    <a href="javascript:void(0)">Blogs<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <!-- all blog section start -->
                    <ul class="dropdown-tab">
                      <!-- blog start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog.html">Blog</a>
                          <a href="blog-full.html">Blog full</a>
                          <a href="blog-left-side.html">Blog left</a>
                          <a href="blog-right-side.html">Blog right</a>
                          <a href="blog-left-right-side.html">Blog mix</a>
                          <a href="blog-left-side-full.html">Blog left full</a>
                          <a href="blog-right-side-full.html">Blog right full</a>
                          <a href="blog-left-right-side-full.html">Blog mix full</a>
                        </div>
                      </li>
                      <!-- blog end -->
                      <!-- blog-grid start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog grid<i class="fas fa-angle-right" /></a>
                        <ul class="dropdown-submenutab">
                          <!-- blog 2 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 2 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-2-grid.html">2 grid</a>
                              <a href="blog-2-grid-left-side.html">2 grid left</a>
                              <a href="blog-2-grid-right-side.html">2 grid right</a>
                            </div>
                          </li>
                          <!-- blog 2 grid end -->
                          <!-- blog 3 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 3 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-3-grid.html">3 grid</a>
                              <a href="blog-3-grid-left-side.html">3 grid left</a>
                              <a href="blog-3-grid-right-side.html">3 grid right</a>
                              <a href="blog-3-grid-full.html">3 grid full</a>
                              <a href="blog-3-grid-left-side-full.html">3 grid left full</a>
                              <a href="blog-3-grid-right-side-full.html">3 grid right full</a>
                            </div>
                          </li>
                          <!-- blog 3 grid end -->
                          <!-- blog 4 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 4 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-4-grid.html">4 grid</a>
                              <a href="blog-4-grid-full.html">4 grid full</a>
                              <a href="blog-4-grid-left-side-full.html">4 grid left full</a>
                              <a href="blog-4-grid-right-side-full.html">4 grid right full</a>
                            </div>
                          </li>
                          <!-- blog 4 grid end -->
                        </ul>
                      </li>
                      <!-- blog-grid end -->
                      <!-- blog-simple start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog simple<i class="fas fa-angle-right" /></a>
                        <ul class="dropdown-submenutab">
                          <!-- blog 2 simple start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 2 simple<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-2-grid-simple.html">2 simple</a>
                              <a href="blog-2-grid-simple-left-side.html">2 simple left</a>
                              <a href="blog-2-grid-simple-right-side.html">2 simple right</a>
                            </div>
                          </li>
                          <!-- blog 2 simple end -->
                          <!-- blog 3 simple start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 3 simple<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-3-grid-simple.html">3 simple</a>
                              <a href="blog-3-grid-simple-left-side.html">3 simple left</a>
                              <a href="blog-3-grid-simple-right-side.html">3 simple right</a>
                              <a href="blog-3-grid-simple-full.html">3 simple full</a>
                              <a href="blog-3-grid-simple-left-side-full.html">3 simple left full</a>
                              <a href="blog-3-grid-simple-right-side-full.html">3 simple right full</a>
                            </div>
                          </li>
                          <!-- blog 3 simple end -->
                          <!-- blog 4 simple start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 4 simple<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-4-grid-simple.html">4 simple</a>
                              <a href="blog-4-grid-simple-full.html">4 simple full</a>
                              <a href="blog-4-grid-left-side-simple-full.html">4 simple left full</a>
                              <a href="blog-4-grid-right-side-simple-full.html">4 simple right full</a>
                            </div>
                          </li>
                          <!-- blog 4 simple end -->
                        </ul>
                      </li>
                      <!-- blog-simple end -->
                      <!-- blog-list start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog list<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog-list.html">List</a>
                          <a href="blog-list-full.html">List full</a>
                          <a href="blog-list-left-side.html">List left</a>
                          <a href="blog-list-right-side.html">List right</a>
                          <a href="blog-list-left-right-side.html">List mix</a>
                          <a href="blog-list-left-side-full.html">List left full</a>
                          <a href="blog-list-right-side-full.html">List right full</a>
                          <a href="blog-list-left-right-side-full.html">List mix full</a>
                        </div>
                      </li>
                      <!-- blog-list end -->
                      <!-- blog-classic start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog classic<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog-classic.html">Classic</a>
                          <a href="blog-classic-full.html">Classic full</a>
                          <a href="blog-left-side-classic.html">Classic left</a>
                          <a href="blog-right-side-classic.html">Classic right</a>
                          <a href="blog-left-side-classic-full.html">Classic left full</a>
                          <a href="blog-right-side-classic-full.html">Classic right full</a>
                        </div>
                      </li>
                      <!-- blog-classic end -->
                      <!-- blog-sticky start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog sticky<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog-left-side-sticky.html">Sticky left</a>
                          <a href="blog-right-side-sticky.html">Sticky right</a>
                          <a href="blog-left-right-side-sticky.html">Sticky mix</a>
                          <a href="blog-list-left-right-side-sticky.html">List sticky mix</a>
                          <a href="blog-left-side-sticky-full.html">Sticky left full</a>
                          <a href="blog-right-side-sticky-full.html">Sticky right full</a>
                          <a href="blog-left-right-side-sticky-full.html">Sticky mix full</a>
                          <a href="blog-list-left-right-side-sticky-full.html">List sticky mix full</a>
                        </div>
                      </li>
                      <!-- blog-sticky end -->
                    </ul>
                    <!-- all blog section end -->
                  </li>
                  <!-- header-nav blog end -->
                  <!-- header-nav product start -->
                  <li class="nav-product head-tab">
                    <a href="javascript:void(0)">Product<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <!-- all product start -->
                    <ul class="dropdown-tab">
                      <!-- product-grid start -->
                      <li class="dropdown-subtab">
                        <a href="javaScript:void(0)">Product grid<i class="fas fa-angle-right" /></a>
                        <ul class="dropdown-submenutab">
                          <!-- product 2 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Product 2 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="product-2-grid.html">2 grid</a>
                              <a href="product-2-grid-left-side.html">2 grid left</a>
                              <a href="product-2-grid-right-side.html">2 grid right</a>
                            </div>
                          </li>
                          <!-- product 2 grid end -->
                          <!-- product 3 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Product 3 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="product-3-grid.html">3 grid</a>
                              <a href="product-3-grid-left-side.html">3 grid left</a>
                              <a href="product-3-grid-right-side.html">3 grid right</a>
                              <a href="product-3-grid-full.html">3 grid full</a>
                              <a href="product-3-grid-left-side-full.html">3 grid left full</a>
                              <a href="product-3-grid-right-side-full.html">3 grid right full</a>
                            </div>
                          </li>
                          <!-- product 3 grid end -->
                          <!-- product 4 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Product 4 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="product-4-grid.html">4 grid</a>
                              <a href="product-4-grid-left-side.html">4 grid left</a>
                              <a href="product-4-grid-right-side.html">4 grid right</a>
                              <a href="product-4-grid-full.html">4 grid full</a>
                              <a href="product-4-grid-left-side-full.html">4 grid left full</a>
                              <a href="product-4-grid-right-side-full.html">4 grid right full</a>
                            </div>
                          </li>
                          <!-- product 4 grid end -->
                          <!-- product 6 grid start -->
                          <li class="dropdown-submenu">
                            <a href="product-6-grid-full.html">Product 6 grid full</a>
                          </li>
                          <!-- product 6 grid end -->
                          <!-- product-all-in-one start -->
                          <li class="dropdown-submenu">
                            <a href="allinonegrid.html">All in one grid</a>
                          </li>
                          <!-- product-all-in-one end -->
                        </ul>
                      </li>
                      <!-- product-grid end -->
                      <!-- product-list start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product list<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-list.html">List</a>
                          <a href="product-list-full.html">List full</a>
                          <a href="product-list-left-side.html">List left</a>
                          <a href="product-list-right-side.html">List right</a>
                          <a href="product-list-left-side-full.html">List left full</a>
                          <a href="product-list-right-side-full.html">List right full</a>
                        </div>
                      </li>
                      <!-- product-list end -->
                      <!-- product-list-advance start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product list advance<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-list-advance.html">List adv</a>
                          <a href="product-list-advance-full.html">List adv full</a>
                          <a href="product-list-left-side-advance.html">List adv left</a>
                          <a href="product-list-right-side-advance.html">List adv right</a>
                          <a href="product-list-left-side-advance-full.html">List adv left full</a>
                          <a href="product-list-right-side-advance-full.html">List adv right full</a>
                        </div>
                      </li>
                      <!-- product-list-advance end -->
                      <!-- product-detail start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product detail<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-detail.html">Detail</a>
                          <a href="product-detail-simple.html">Detail simple</a>
                          <a href="product-detail-slider.html">Detail slider</a>
                          <a href="product-detail-sticky.html">Detail sticky</a>
                          <a href="product-detail-with-zoom-effect.html">Detail zoom effect</a>
                        </div>
                      </li>
                      <!-- product-detail end -->
                      <!-- product-comparision start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product comparision<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-comparision.html">Comparision</a>
                          <a href="product-comparision-adv.html">Comparision adv</a>
                        </div>
                      </li>
                      <!-- product-comparision end -->
                    </ul>
                    <!-- all product end -->
                  </li>
                  <!-- header-nav product end -->
                  <!-- header-nav page start -->
                  <li class="nav-page head-tab">
                    <a href="javascript:void(0)">Pages<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <div class="dropdown-tab">
                      <a href="about-us.html">About</a>
                      <a href="billing-info.html">Billing info</a>
                      <a href="cancellation.html">Cancellation</a>
                      <a href="cart-page.html">Cart page</a>
                      <a href="checkout.html">Checkout</a>
                      <a href="coming-soon.html">Coming soon</a>
                      <a href="faqs.html">Faq's</a>
                      <a href="forgot-pwd.html">Forgot password</a>
                      <a href="order-complete.html">Order complete</a>
                      <a href="privacy-policy.html">Privacy policy</a>
                      <a href="signup.html">Signup</a>
                      <a href="signin.html">Signin</a>
                      <a href="site-map.html">Sitemap</a>
                      <a href="terms-condition.html">Terms & condition</a>
                      <a href="track-page.html">Track page</a>
                      <a href="wishlist.html">Wishlist</a>
                      <a href="fnf.html">4 not 4</a>
                    </div>
                  </li>
                  <!-- header-nav page end -->
                  <!-- header-nav store start -->
                  <li class="nav-store head-tab">
                    <a href="stores.html" class="">Stores</a>
                  </li>
                  <!-- header-nav store end -->
                  <!-- header-nav contact start -->
                  <li class="nav-contact head-tab">
                    <a href="contact-us.html" class="">Contact</a>
                  </li>
                  <!-- header-nav contact end -->
                </ul>
                <!-- header nav end -->
              </div>
            </nav>
            <!-- header navbar end -->
            <!-- logo start -->
            <div class="mainlogo">
              <a class="navbar-brand" href="home5.html">
                <img src="image/libazz-fashion-logo.png" alt="logo" class="img-fluid header-logo">
              </a>
            </div>
            <!-- logo end -->
          </div>
          <div class="col-xl-6 col-lg-6 col-md-6 col-12 sm-display-none">
            <!-- search icon start -->
            <div id="search-category">
              <form class="search-box" action="#" method="post">
                <div class="search-cat">
                  <select class="search-dropdown">
                    <option>All Categories</option>
                    <option>Men</option>
                    <option>Women</option>
                    <option>Jewellery</option>
                    <option>Electronics</option>
                    <option>Kid</option>
                    <option>Bootees Bags</option>
                    <option>Clothing</option>
                    <option>Footwear</option>
                    <option>T-Shirts</option>
                    <option>Polo-Shirts</option>
                  </select>
                </div>
                <div class="search">
                  <input type="search" placeholder="Search your product..." class="text-search" name="search">
                  <a id="search" href="search.html"><i class="fas fa-search" /><span>Search</span></a>
                </div>
              </form>
            </div>
            <!-- search icon end -->
          </div>
          <div class="col-xl-3 col-lg-3 col-md-3 col-5 cart">
            <!-- cart icon start -->
            <div class="show-cart padding-tb">
              <div class="icon-margin">
                <a href="javascript:void(0)" class="middle show-cart-click">
                  <i class="fas fa-luggage-cart" />
                  <span>My Cart:</span>
                  <span class="cart-count">5 items</span>
                </a>
                <!-- cart item start -->
                <div class="cart-drop-down">
                  <div class="item-limit">
                    <!-- cart item1 start -->
                    <div class="row padding-15px-tb">
                      <div class="col-xl-4 col-lg-4 col-md-4 col-4 padding-left-15px padding-right-0px">
                        <div class="padding-left-15px">
                          <a href="javascript:void(0)"><img src="image/libazz-fashion-cart-1.jpg" alt="cart-1" class="img-fluid"></a>
                        </div>
                      </div>
                      <div class="col-xl-8 col-lg-8 col-md-8 col-8 padding-left-15px">
                        <div class="padding-right-15px">
                          <div class="cart-pro-name font-weight-600 d-block">
                            <a href="javascript:void(0)" class="text-black">Silk Cotton Shirt</a>
                          </div>
                          <div class="quantity d-block">
                            <div class="plus-minus">
                              <span class="padding-5px border-light-gray sm-text-large">
                                <a href="javascript:void(0)" class="plus-btn text-black"><i class="fas fa-plus" /></a>
                                <input type="text" name="name" value="1" class="border-left-light-gray border-right-light-gray">
                                <a href="javascript:void(0)" class="minus-btn text-black"><i class="fas fa-minus" /></a>
                              </span>
                            </div>
                          </div>
                          <div class="cart-pro-price d-block">
                            <div class="price d-inline-block">
                              <div class="text-black">$413.00</div>
                            </div>
                            <div class="delete d-inline-block float-right">
                              <a href="javaScript:void(0)" class="trash text-black"><i class="ti-trash" /></a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- cart item1 end -->
                    <!-- cart item border start -->
                    <div class="border-top-light-gray margin-left-15px margin-right-15px" />
                    <!-- cart item border end -->
                    <!-- cart item2 start -->
                    <div class="row padding-15px-tb">
                      <div class="col-xl-4 col-lg-4 col-md-4 col-4 padding-left-15px padding-right-0px">
                        <div class="padding-left-15px">
                          <a href="javascript:void(0)"><img src="image/libazz-fashion-cart-2.jpg" alt="cart-2" class="img-fluid"></a>
                        </div>
                      </div>
                      <div class="col-xl-8 col-lg-8 col-md-8 col-8 padding-left-15px">
                        <div class="padding-right-15px">
                          <div class="cart-pro-name font-weight-600 d-block">
                            <a href="javascript:void(0)" class="text-black">Silk Cotton Shirt</a>
                          </div>
                          <div class="quantity d-block">
                            <div class="plus-minus">
                              <span class="padding-5px border-light-gray sm-text-large">
                                <a href="javascript:void(0)" class="plus-btn text-black"><i class="fas fa-plus" /></a>
                                <input type="text" name="name" value="1" class="border-left-light-gray border-right-light-gray">
                                <a href="javascript:void(0)" class="minus-btn text-black"><i class="fas fa-minus" /></a>
                              </span>
                            </div>
                          </div>
                          <div class="cart-pro-price d-block">
                            <div class="price d-inline-block">
                              <div class="text-black">$413.00</div>
                            </div>
                            <div class="delete d-inline-block float-right">
                              <a href="javaScript:void(0)" class="trash text-black"><i class="ti-trash" /></a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- cart item2 end -->
                    <!-- cart item border start -->
                    <div class="border-top-light-gray margin-left-15px margin-right-15px" />
                    <!-- cart item border end -->
                    <!-- cart item3 start -->
                    <div class="row padding-15px-tb">
                      <div class="col-xl-4 col-lg-4 col-md-4 col-4 padding-left-15px padding-right-0px">
                        <div class="padding-left-15px">
                          <a href="javascript:void(0)"><img src="image/libazz-fashion-cart-3.jpg" alt="cart-3" class="img-fluid"></a>
                        </div>
                      </div>
                      <div class="col-xl-8 col-lg-8 col-md-8 col-8 padding-left-15px">
                        <div class="padding-right-15px">
                          <div class="cart-pro-name font-weight-600 d-block">
                            <a href="javascript:void(0)" class="text-black">Silk Cotton Shirt</a>
                          </div>
                          <div class="quantity d-block">
                            <div class="plus-minus">
                              <span class="padding-5px border-light-gray sm-text-large">
                                <a href="javascript:void(0)" class="plus-btn text-black"><i class="fas fa-plus" /></a>
                                <input type="text" name="name" value="1" class="border-left-light-gray border-right-light-gray">
                                <a href="javascript:void(0)" class="minus-btn text-black"><i class="fas fa-minus" /></a>
                              </span>
                            </div>
                          </div>
                          <div class="cart-pro-price d-block">
                            <div class="price d-inline-block">
                              <div class="text-black">$413.00</div>
                            </div>
                            <div class="delete d-inline-block float-right">
                              <a href="javaScript:void(0)" class="trash text-black"><i class="ti-trash" /></a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- cart item3 end -->
                    <!-- cart item border start -->
                    <div class="border-top-light-gray margin-left-15px margin-right-15px" />
                    <!-- cart item border end -->
                    <!-- cart item4 start -->
                    <div class="row padding-15px-tb">
                      <div class="col-xl-4 col-lg-4 col-md-4 col-4 padding-left-15px padding-right-0px">
                        <div class="padding-left-15px">
                          <a href="javascript:void(0)"><img src="image/libazz-fashion-cart-4.jpg" alt="cart-4" class="img-fluid"></a>
                        </div>
                      </div>
                      <div class="col-xl-8 col-lg-8 col-md-8 col-8 padding-left-15px">
                        <div class="padding-right-15px">
                          <div class="cart-pro-name font-weight-600 d-block">
                            <a href="javascript:void(0)" class="text-black">Silk Cotton Shirt</a>
                          </div>
                          <div class="quantity d-block">
                            <div class="plus-minus">
                              <span class="padding-5px border-light-gray sm-text-large">
                                <a href="javascript:void(0)" class="plus-btn text-black"><i class="fas fa-plus" /></a>
                                <input type="text" name="name" value="1" class="border-left-light-gray border-right-light-gray">
                                <a href="javascript:void(0)" class="minus-btn text-black"><i class="fas fa-minus" /></a>
                              </span>
                            </div>
                          </div>
                          <div class="cart-pro-price d-block">
                            <div class="price d-inline-block">
                              <div class="text-black">$413.00</div>
                            </div>
                            <div class="delete d-inline-block float-right">
                              <a href="javaScript:void(0)" class="trash text-black"><i class="ti-trash" /></a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- cart item4 end -->
                    <!-- cart item border start -->
                    <div class="border-top-light-gray margin-left-15px margin-right-15px" />
                    <!-- cart item border end -->
                    <!-- cart item5 start -->
                    <div class="row padding-15px-tb">
                      <div class="col-xl-4 col-lg-4 col-md-4 col-4 padding-left-15px padding-right-0px">
                        <div class="padding-left-15px">
                          <a href="javascript:void(0)"><img src="image/libazz-fashion-cart-5.jpg" alt="cart-5" class="img-fluid"></a>
                        </div>
                      </div>
                      <div class="col-xl-8 col-lg-8 col-md-8 col-8 padding-left-15px">
                        <div class="padding-right-15px">
                          <div class="cart-pro-name font-weight-600 d-block">
                            <a href="javascript:void(0)" class="text-black">Silk Cotton Shirt</a>
                          </div>
                          <div class="quantity d-block">
                            <div class="plus-minus">
                              <span class="padding-5px border-light-gray sm-text-large">
                                <a href="javascript:void(0)" class="plus-btn text-black"><i class="fas fa-plus" /></a>
                                <input type="text" name="name" value="1" class="border-left-light-gray border-right-light-gray">
                                <a href="javascript:void(0)" class="minus-btn text-black"><i class="fas fa-minus" /></a>
                              </span>
                            </div>
                          </div>
                          <div class="cart-pro-price d-block">
                            <div class="price d-inline-block">
                              <div class="text-black">$413.00</div>
                            </div>
                            <div class="delete d-inline-block float-right">
                              <a href="javaScript:void(0)" class="trash text-black"><i class="ti-trash" /></a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- cart item5 end -->
                  </div>
                  <!-- cart item border start -->
                  <div class="border-top-light-gray margin-left-15px margin-right-15px" />
                  <!-- cart item border end -->
                  <!-- cart button start -->
                  <div class="row padding-15px-tb">
                    <div class="col-xl-6 col-lg-6 col-md-6 col-6">
                      <!-- cart view-cart button start -->
                      <div class="continue-shop padding-left-15px">
                        <a href="cart-page.html" class="btn btn-small-1">Viewcart</a>
                      </div>
                      <!-- cart view-cart button end -->
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-6 text-right">
                      <!-- cart checkout button start -->
                      <div class="check-out padding-right-15px">
                        <a href="checkout.html" class="btn btn-small-1">Checkout</a>
                      </div>
                      <!-- cart checkout button end -->
                    </div>
                  </div>
                  <!-- cart button end -->
                </div>
                <!-- cart item end -->
              </div>
            </div>
            <!-- cart icon end -->
          </div>
        </div>
        <div class="row md-display-none">
          <div class="col-xl-12 col-lg-12">
            <!-- header navbar start -->
            <nav class="navbar">
              <!-- navbar toggler start -->
              <button class="menu" type="button">
                <span class="menu-icon text-white" />
              </button>
              <!-- navbar toggler end -->
              <div class="navbar-collapse">
                <!-- search icon start -->
                <div class="search d-none sm-display-block">
                  <input type="search" class="text-black" placeholder="Search your product">
                  <a href="search.html" class="text-black"><i class="ti-search" /></a>
                </div>
                <!-- search icon end -->
                <!-- header nav start -->
                <ul class="nav navbar-nav">
                  <!-- header-nav home start -->
                  <li class="active nav-home head-tab">
                    <a href="javaScript:void(0)">Home<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <div class="dropdown-tab">
                      <a href="home.html">Home layout 1</a>
                      <a href="home2.html">Home layout 2</a>
                      <a href="home3.html">Home layout 3</a>
                      <a href="home4.html">Home layout 4</a>
                      <a href="javaScript:void(0)">Home layout 5</a>
                      <a href="home6.html">Home layout 6</a>
                      <a href="home7.html">Home layout 7</a>
                    </div>
                  </li>
                  <!-- header-nav home end -->
                  <!-- header-nav blog start -->
                  <li class="nav-blog head-tab">
                    <a href="javascript:void(0)">Blogs<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <!-- all blog section start -->
                    <ul class="dropdown-tab">
                      <!-- blog start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog.html">Blog</a>
                          <a href="blog-full.html">Blog full</a>
                          <a href="blog-left-side.html">Blog left</a>
                          <a href="blog-right-side.html">Blog right</a>
                          <a href="blog-left-right-side.html">Blog mix</a>
                          <a href="blog-left-side-full.html">Blog left full</a>
                          <a href="blog-right-side-full.html">Blog right full</a>
                          <a href="blog-left-right-side-full.html">Blog mix full</a>
                        </div>
                      </li>
                      <!-- blog end -->
                      <!-- blog-grid start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog grid<i class="fas fa-angle-right" /></a>
                        <ul class="dropdown-submenutab">
                          <!-- blog 2 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 2 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-2-grid.html">2 grid</a>
                              <a href="blog-2-grid-left-side.html">2 grid left</a>
                              <a href="blog-2-grid-right-side.html">2 grid right</a>
                            </div>
                          </li>
                          <!-- blog 2 grid end -->
                          <!-- blog 3 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 3 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-3-grid.html">3 grid</a>
                              <a href="blog-3-grid-left-side.html">3 grid left</a>
                              <a href="blog-3-grid-right-side.html">3 grid right</a>
                              <a href="blog-3-grid-full.html">3 grid full</a>
                              <a href="blog-3-grid-left-side-full.html">3 grid left full</a>
                              <a href="blog-3-grid-right-side-full.html">3 grid right full</a>
                            </div>
                          </li>
                          <!-- blog 3 grid end -->
                          <!-- blog 4 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 4 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-4-grid.html">4 grid</a>
                              <a href="blog-4-grid-full.html">4 grid full</a>
                              <a href="blog-4-grid-left-side-full.html">4 grid left full</a>
                              <a href="blog-4-grid-right-side-full.html">4 grid right full</a>
                            </div>
                          </li>
                          <!-- blog 4 grid end -->
                        </ul>
                      </li>
                      <!-- blog-grid end -->
                      <!-- blog-simple start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog simple<i class="fas fa-angle-right" /></a>
                        <ul class="dropdown-submenutab">
                          <!-- blog 2 simple start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 2 simple<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-2-grid-simple.html">2 simple</a>
                              <a href="blog-2-grid-simple-left-side.html">2 simple left</a>
                              <a href="blog-2-grid-simple-right-side.html">2 simple right</a>
                            </div>
                          </li>
                          <!-- blog 2 simple end -->
                          <!-- blog 3 simple start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 3 simple<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-3-grid-simple.html">3 simple</a>
                              <a href="blog-3-grid-simple-left-side.html">3 simple left</a>
                              <a href="blog-3-grid-simple-right-side.html">3 simple right</a>
                              <a href="blog-3-grid-simple-full.html">3 simple full</a>
                              <a href="blog-3-grid-simple-left-side-full.html">3 simple left full</a>
                              <a href="blog-3-grid-simple-right-side-full.html">3 simple right full</a>
                            </div>
                          </li>
                          <!-- blog 3 simple end -->
                          <!-- blog 4 simple start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Blog 4 simple<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="blog-4-grid-simple.html">4 simple</a>
                              <a href="blog-4-grid-simple-full.html">4 simple full</a>
                              <a href="blog-4-grid-left-side-simple-full.html">4 simple left full</a>
                              <a href="blog-4-grid-right-side-simple-full.html">4 simple right full</a>
                            </div>
                          </li>
                          <!-- blog 4 simple end -->
                        </ul>
                      </li>
                      <!-- blog-simple end -->
                      <!-- blog-list start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog list<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog-list.html">List</a>
                          <a href="blog-list-full.html">List full</a>
                          <a href="blog-list-left-side.html">List left</a>
                          <a href="blog-list-right-side.html">List right</a>
                          <a href="blog-list-left-right-side.html">List mix</a>
                          <a href="blog-list-left-side-full.html">List left full</a>
                          <a href="blog-list-right-side-full.html">List right full</a>
                          <a href="blog-list-left-right-side-full.html">List mix full</a>
                        </div>
                      </li>
                      <!-- blog-list end -->
                      <!-- blog-classic start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog classic<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog-classic.html">Classic</a>
                          <a href="blog-classic-full.html">Classic full</a>
                          <a href="blog-left-side-classic.html">Classic left</a>
                          <a href="blog-right-side-classic.html">Classic right</a>
                          <a href="blog-left-side-classic-full.html">Classic left full</a>
                          <a href="blog-right-side-classic-full.html">Classic right full</a>
                        </div>
                      </li>
                      <!-- blog-classic end -->
                      <!-- blog-sticky start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Blog sticky<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="blog-left-side-sticky.html">Sticky left</a>
                          <a href="blog-right-side-sticky.html">Sticky right</a>
                          <a href="blog-left-right-side-sticky.html">Sticky mix</a>
                          <a href="blog-list-left-right-side-sticky.html">List sticky mix</a>
                          <a href="blog-left-side-sticky-full.html">Sticky left full</a>
                          <a href="blog-right-side-sticky-full.html">Sticky right full</a>
                          <a href="blog-left-right-side-sticky-full.html">Sticky mix full</a>
                          <a href="blog-list-left-right-side-sticky-full.html">List sticky mix full</a>
                        </div>
                      </li>
                      <!-- blog-sticky end -->
                    </ul>
                    <!-- all blog section end -->
                  </li>
                  <!-- header-nav blog end -->
                  <!-- header-nav product start -->
                  <li class="nav-product head-tab">
                    <a href="javascript:void(0)">Product<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <!-- all product start -->
                    <ul class="dropdown-tab">
                      <!-- product-grid start -->
                      <li class="dropdown-subtab">
                        <a href="javaScript:void(0)">Product grid<i class="fas fa-angle-right" /></a>
                        <ul class="dropdown-submenutab">
                          <!-- product 2 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Product 2 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="product-2-grid.html">2 grid</a>
                              <a href="product-2-grid-left-side.html">2 grid left</a>
                              <a href="product-2-grid-right-side.html">2 grid right</a>
                            </div>
                          </li>
                          <!-- product 2 grid end -->
                          <!-- product 3 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Product 3 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="product-3-grid.html">3 grid</a>
                              <a href="product-3-grid-left-side.html">3 grid left</a>
                              <a href="product-3-grid-right-side.html">3 grid right</a>
                              <a href="product-3-grid-full.html">3 grid full</a>
                              <a href="product-3-grid-left-side-full.html">3 grid left full</a>
                              <a href="product-3-grid-right-side-full.html">3 grid right full</a>
                            </div>
                          </li>
                          <!-- product 3 grid end -->
                          <!-- product 4 grid start -->
                          <li class="dropdown-submenu">
                            <a href="javascript:void(0)">Product 4 grid<i class="fas fa-angle-right" /></a>
                            <div class="dropdown-submenu-tab">
                              <a href="product-4-grid.html">4 grid</a>
                              <a href="product-4-grid-left-side.html">4 grid left</a>
                              <a href="product-4-grid-right-side.html">4 grid right</a>
                              <a href="product-4-grid-full.html">4 grid full</a>
                              <a href="product-4-grid-left-side-full.html">4 grid left full</a>
                              <a href="product-4-grid-right-side-full.html">4 grid right full</a>
                            </div>
                          </li>
                          <!-- product 4 grid end -->
                          <!-- product 6 grid start -->
                          <li class="dropdown-submenu">
                            <a href="product-6-grid-full.html">Product 6 grid full</a>
                          </li>
                          <!-- product 6 grid end -->
                          <!-- product-all-in-one start -->
                          <li class="dropdown-submenu">
                            <a href="allinonegrid.html">All in one grid</a>
                          </li>
                          <!-- product-all-in-one end -->
                        </ul>
                      </li>
                      <!-- product-grid end -->
                      <!-- product-list start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product list<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-list.html">List</a>
                          <a href="product-list-full.html">List full</a>
                          <a href="product-list-left-side.html">List left</a>
                          <a href="product-list-right-side.html">List right</a>
                          <a href="product-list-left-side-full.html">List left full</a>
                          <a href="product-list-right-side-full.html">List right full</a>
                        </div>
                      </li>
                      <!-- product-list end -->
                      <!-- product-list-advance start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product list advance<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-list-advance.html">List adv</a>
                          <a href="product-list-advance-full.html">List adv full</a>
                          <a href="product-list-left-side-advance.html">List adv left</a>
                          <a href="product-list-right-side-advance.html">List adv right</a>
                          <a href="product-list-left-side-advance-full.html">List adv left full</a>
                          <a href="product-list-right-side-advance-full.html">List adv right full</a>
                        </div>
                      </li>
                      <!-- product-list-advance end -->
                      <!-- product-detail start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product detail<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-detail.html">Detail</a>
                          <a href="product-detail-simple.html">Detail simple</a>
                          <a href="product-detail-slider.html">Detail slider</a>
                          <a href="product-detail-sticky.html">Detail sticky</a>
                          <a href="product-detail-with-zoom-effect.html">Detail zoom effect</a>
                        </div>
                      </li>
                      <!-- product-detail end -->
                      <!-- product-comparision start -->
                      <li class="dropdown-subtab">
                        <a href="javascript:void(0)">Product comparision<i class="fas fa-angle-right" /></a>
                        <div class="dropdown-submenutab">
                          <a href="product-comparision.html">Comparision</a>
                          <a href="product-comparision-adv.html">Comparision adv</a>
                        </div>
                      </li>
                      <!-- product-comparision end -->
                    </ul>
                    <!-- all product end -->
                  </li>
                  <!-- header-nav product end -->
                  <!-- header-nav page start -->
                  <li class="nav-page head-tab">
                    <a href="javascript:void(0)">Pages<span><i class="fas fa-angle-down padding-left-5px" /></span></a>
                    <div class="dropdown-tab">
                      <a href="about-us.html">About</a>
                      <a href="billing-info.html">Billing info</a>
                      <a href="cancellation.html">Cancellation</a>
                      <a href="cart-page.html">Cart page</a>
                      <a href="checkout.html">Checkout</a>
                      <a href="coming-soon.html">Coming soon</a>
                      <a href="faqs.html">Faq's</a>
                      <a href="forgot-pwd.html">Forgot password</a>
                      <a href="order-complete.html">Order complete</a>
                      <a href="privacy-policy.html">Privacy policy</a>
                      <a href="signup.html">Signup</a>
                      <a href="signin.html">Signin</a>
                      <a href="site-map.html">Sitemap</a>
                      <a href="terms-condition.html">Terms & condition</a>
                      <a href="track-page.html">Track page</a>
                      <a href="wishlist.html">Wishlist</a>
                      <a href="fnf.html">4 not 4</a>
                    </div>
                  </li>
                  <!-- header-nav page end -->
                  <!-- header-nav store start -->
                  <li class="nav-store head-tab">
                    <a href="stores.html" class="">Stores</a>
                  </li>
                  <!-- header-nav store end -->
                  <!-- header-nav contact start -->
                  <li class="nav-contact head-tab">
                    <a href="contact-us.html" class="">Contact</a>
                  </li>
                  <!-- header-nav contact end -->
                </ul>
                <!-- header nav end -->
              </div>
            </nav>
            <!-- header navbar end -->
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'NavBar'
}
</script>

<style scoped>

</style>
