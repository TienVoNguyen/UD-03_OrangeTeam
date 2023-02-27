$(document).ready(function(){
    "use strict";
    /*==============================================================
      Home Slider
    ==============================================================*/

    var swiper = new Swiper('#home-slider',{
      grabCursor : true,
      navigation: {
          nextEl: '.slider-next',
          prevEl: '.slider-prev',
        },
      loop:true
    });

    /*==============================================================
      Home Slider Style2
    ==============================================================*/

    $('.slider').owlCarousel({
        loop:true,
        nav:false,
        items:1,
        autoplay:true,
        autoplayTimeout:7000,
        autoplayHoverPause:true
    });


    /*==============================================================
      Home Style2 testimonial slider
    ==============================================================*/

    $('.category-owl,.testi-monial-masonary,.deal-day1').owlCarousel({
        loop:true,
        nav:false,
        items:1,
        margin:15,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:false
    });

    /*==============================================================
      Home Style3 testimonial slider
    ==============================================================*/

    $('.test-monial').owlCarousel({
      loop:true,
      margin:30,
      nav:false,
      autoplay:true,
      autoplayTimeout:5000,
      autoplayHoverPause:false,
      responsive:{
        0:{
            items:1
        },
        600:{  
            items:2
        },
        1000:{
            items:2
        }
      }
    });

    /*==============================================================
      Home Style4 testimonial slider
    ==============================================================*/

    $('.test-monial2').owlCarousel({
      items:1,
      loop:true,
      margin:30,
      nav:false,
      autoplay:true,
      autoplayTimeout:5000,
      autoplayHoverPause:false
    });


    /*==============================================================
      Home Style2 brand-icon slider
    ==============================================================*/

    $('.brand-icon').owlCarousel({
        loop:true,
        margin:30,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:false,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:3
            },
            1000:{
                items:5
            }
        }
    });

    /*==============================================================
      Product Slider
    ==============================================================*/
    
    var swiper = new Swiper('#product-slider', {
      slidesPerView: 4,
      spaceBetween: 30,

      navigation: {
        nextEl: '#next',
        prevEl: '#prev',
      },
      
      breakpoints: {
        1024: {
          slidesPerView: 4,
          spaceBetween: 30,
        },
        768: {
          slidesPerView: 3,
          spaceBetween: 30,
        },
        640: {
          slidesPerView: 2,
          spaceBetween: 15,
        },
        320: {
          slidesPerView: 2,
          spaceBetween: 15,
        }
      }
    });

    /*==============================================================
      Home Style2 Product Slider
    ==============================================================*/

    $('.feature-product-slider').owlCarousel({
        loop:true,
        margin:30,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:2
            },
            600:{
                items:3
            },
            1000:{
                items:4
            }
        }
    });

    /*==============================================================
      Home Style3 Product Slider
    ==============================================================*/

    $('.new-arrival').owlCarousel({
        loop:true,
        margin:30,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:2
            },
            1000:{
                items:2
            }
        }
    });

    $('.feature-trend-slider').owlCarousel({
        loop:true,
        margin:30,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:3
            },
            1000:{
                items:4
            }
        }
    });

    $('.blog-slider').owlCarousel({
        loop:true,
        margin:30,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:2
            },
            1000:{
                items:3
            }
        }
    });

    $('.blog-slider2').owlCarousel({
        loop:true,
        items:1,
        margin:0,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true
    });

    $('.category-icon').owlCarousel({
        loop:true,
        margin:0,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:3
            },
            600:{
                items:5
            },
            1000:{
                items:6
            }
        }
    });

    $('.categoryicon').owlCarousel({
        loop:true,
        margin:0,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:2
            },
            600:{
                items:1
            },
            1000:{
                items:2
            }
        }
    });

    $('.cat-icon').owlCarousel({
        loop:true,
        nav:true,
        navText : ['<i class="ti-angle-left"></i>','<i class="ti-angle-right"></i>'],
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:2
            },
            600:{
                items:2
            },
            750:{
                items:4
            },
            1024:{
                items:5
            },
            1199:{
                items:6
            }
        }
    });

    $('.mega-menu-slider').owlCarousel({
        loop:true,
        margin:15,
        nav:false,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:2
            },
            1000:{
                items:2
            }
        }
    });

    /*==============================================================
      Product Details Page Similar Product Slider
    ==============================================================*/

    $('.similar-product-silder').owlCarousel({
        loop:false,
        margin:30,
        nav:true,
        responsive:{
            0:{
                items:2
            },
            600:{
                items:3
            },
            1000:{
                items:4
            }
        }
    });

    $('.detail-slider').owlCarousel({
        loop:false,
        margin:15,
        nav:true,
        navText : ['<i class="ti-angle-left"></i>','<i class="ti-angle-right"></i>'],
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        responsive:{
            0:{
                items:3
            },
            600:{
                items:3
            },
            1000:{
                items:4
            }
        }
    });

    /*==============================================================
      Deal of day and Testimonial and Blog-owl and recommed Slider 
    ==============================================================*/

    $('.deal-day,.testimonial-silder,.blog-owl-slider').owlCarousel({
      loop:false,
      margin:0,
      nav:true,
      navText : ['<i class="ti-angle-left"></i>','<i class="ti-angle-right"></i>'],
      responsive:{
        0:{
            items:1
        },
        600:{  
            items:1
        },
        1000:{
            items:1
        }
      }
    });

    $('.recom-owl-slider').owlCarousel({
      loop:true,
      margin:0,
      nav:false,
      items:1,
      autoplay:true,
      autoplayTimeout:1000,
      autoplayHoverPause:true,
    });

    /*==============================================================
      Toggle Icon Plus and Minus
    ==============================================================*/

    function toggleIcon(e){
      $(e.target)
      .prev('.panel-title')
      .find(".fas")
      .toggleClass('fa-plus-circle fa-minus-circle');
    }
    $('.panel-group').on('hidden.bs.collapse', toggleIcon);
    $('.panel-group').on('shown.bs.collapse', toggleIcon);

    $('#rtl-support').on("click",function(){
      var rtl = getCookie('rtl');
      if(rtl == ''){
        setCookie('rtl','1','1');
      }else{
        eraseCookie('rtl');
      }
      $('html').toggleClass('rtl');
    }); 

    $('#box-support').on("click",function(){
      var box = getCookie('box');
      if(box == ''){
        //alert('dsf');
        setCookie('box','1','1');
      }else{
        eraseCookie('box');
      }
      $('html').toggleClass('box-layout');
    }); 

    $('#dark-support').on("click",function(){
      var dark = getCookie('dark');
      if(dark == ''){
        setCookie('dark','1','1');
      }else{
        eraseCookie('dark');
      }
       $('html').toggleClass('dark-layout');
    });


    function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

    function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

    function eraseCookie(key) {
        var keyValue = getCookie(key);
        setCookie(key, keyValue, '-1');
    }


    /*==============================================================
      Full Screen Height Of Page
    ==============================================================*/

    resizefullscreen();  

    /*==============================================================
      Track your page Events
    ==============================================================*/

    $(".track-your-order button").on("click", function () {
      $(this).fadeOut();
      $(".track-your-order").fadeOut();
    });

    /*==============================================================
      Product Sidebar Click Events
    ==============================================================*/

    $('.grid-list').on('click',function(){
      $('.grid-list').removeClass("active-2");
      $(this).addClass("active-2");
    });

    /*==============================================================
      Product Details  Click Events
    ==============================================================*/

    $('.product-detail ul li, .index-tab ul li, .landing-page ul li').on("click",function(){
      $('li').removeClass("active-3");
      $(this).addClass("active-3");
    });

    /*==============================================================
      Homestyle3 Click Events
    ==============================================================*/

    $('.new-tab ul li').on("click",function(){
      $('.new-tab li').removeClass("active");
      $(this).addClass("active");
    });

    $('.our-tab ul li').on("click",function(){
      $('.our-tab li').removeClass("active");
      $(this).addClass("active");
    });

    $('.feature-trend ul li').on("click",function(){
      $('.feature-trend li').removeClass("active");
      $(this).addClass("active");
    });

    /*==============================================================
      Boostrao Tooltip
    ==============================================================*/

    $('[data-toggle="tooltip"]').tooltip(); 

    /*==============================================================
      Bottom To Top Scroll
    ==============================================================*/

    $(window).scroll(function(){ 
      if ($(this).scrollTop() > 200) { 
          $('#scroll').fadeIn(); 
      } else { 
          $('#scroll').fadeOut(); 
      } 
    });

    $('#scroll').on('click',function(){ 
        $("html, body").animate({ scrollTop: 0 }, 600); 
        return false; 
    }); 

    /*==============================================================
      Newsletter Popup
    ==============================================================*/

    $('#myModal1').modal('show');

    /*==============================================================
      Toggle plus minus icon on show hide of collapse element
    ==============================================================*/

    $(".collapse.show").each(function(){
      $(this).prev(".faqs").find(".fas").addClass("fa-minus").removeClass("fa-plus");
    });

    $(".collapse").on('show.bs.collapse', function(){
      $(this).prev(".faqs").find(".fas").removeClass("fa-plus").addClass("fa-minus");
    }).on('hide.bs.collapse', function(){
      $(this).prev(".faqs").find(".fas").removeClass("fa-minus").addClass("fa-plus");
    });

    /* ========================================== 
      Product-zoom events on hover
    ========================================== */

    $('.zoom').zoom();

    $('.red-tooltip').tooltip();
});

  /*==============================================================
    Fullscreen Height
  ==============================================================*/

  function resizefullscreen() {
    var minheight = $(window).height();
    $(".fullscreen").css('min-height', minheight);
  }

  $(window).resize(function () {
      resizefullscreen();
  });

  /*==============================================================
    Page Sticky For Sidebar
  ==============================================================*/

  var mainNavLinks = document.querySelectorAll(".preview");
  var mainSections = document.querySelectorAll(".details");

  var lastId;
  var cur = [];

  window.addEventListener("scroll", event => {
    var fromTop = window.scrollY;
    mainNavLinks.forEach(link => {
      var section = document.querySelector(link.hash);
    });
  });

  $(function () {
    $('input,textarea').on( "focus",function () {
        $(this).data('placeholder', $(this).attr('placeholder'))
               .attr('placeholder', '');
    }).blur(function () {
        $(this).attr('placeholder', $(this).data('placeholder'));
    });
  });

  $(window).scroll(function(){
    var nav = $('.make-me-sticky');
    if(nav.length){
      var distance = nav.offset().top - $(window).scrollTop();
      if (distance <= 0) {
         $('.sidebar-item').addClass('is-sticky')
       } else {
         $('.sidebar-item').removeClass('is-sticky')
       }
    }
  });

  /*==============================================================
    Masonary Grid For Product
  ==============================================================*/

  var $grid = $('.grid').masonry({
    itemSelector: '.grid-item',
    columnWidth: '.grid-sizer',
    percentPosition: true
  });
  
  /* ========================================== 
  Layout Masonry after each image loads
  ========================================== */

  $grid.imagesLoaded().progress(function() {
    $grid.masonry('layout');
  });
  
  /* ========================================== 
  Timer For Deal and Week Of Days
  ========================================== */

  function getTimeRemaining(endtime) {
    var t = Date.parse(endtime) - Date.parse(new Date());
    var seconds = Math.floor((t / 1000) % 60);
    var minutes = Math.floor((t / 1000 / 60) % 60);
    var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
    var days = Math.floor(t / (1000 * 60 * 60 * 24));
    return {
      'total': t,
      'days': days,
      'hours': hours,
      'minutes': minutes,
      'seconds': seconds
    };
  }

  function initializeClock(id, endtime) {
    var clock = document.getElementById(id);
    if(clock != null){
      var daysSpan = clock.querySelector('.days');
      var hoursSpan = clock.querySelector('.hours');
      var minutesSpan = clock.querySelector('.minutes');
      var secondsSpan = clock.querySelector('.seconds');

      function updateClock() {
        var t = getTimeRemaining(endtime);
        daysSpan.innerHTML = t.days;
        hoursSpan.innerHTML = ('0' + t.hours).slice(-2);
        minutesSpan.innerHTML = ('0' + t.minutes).slice(-2);
        secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);

        if (t.total <= 0) {
          clearInterval(timeinterval);
        }
      }

      updateClock();
      var timeinterval = setInterval(updateClock, 1000);
    }
  }

  var deadline = new Date(Date.parse(new Date()) + 15 * 24 * 60 * 60 * 1000);
  initializeClock('clockdiv', deadline);

  var deadline = new Date(Date.parse(new Date()) + 15 * 24 * 60 * 60 * 1000);
  initializeClock('clockdeal', deadline);

  /* ========================================== 
  Instagram feed
  ========================================== */

  if($('#instaFeed-style1').length != 0){

    var instaFeedStyle1 = new Instafeed({
        target: 'instaFeed-style1',
        "edge_liked_by": {
          "count": 821544
        },
        get: 'user',
        userId: 16918021242,
        limit: 12,
        accessToken: '16918021242.1677ed0.4afc702b03f64021a37acaaa836c4efc',
        resolution: 'standard_resolution',
        error: {
            template:'<div class="col-12"><span class=text-center>No Images Found</span></div>'
        },
        template: '<div class="col-xl-2 col-lg-2 col-md-2 col-4 padding-left-0px padding-right-0px instafeed-style1"><a class="insta-link" href="{{link}}" target="_blank"><img src="{{image}}" class="insta-image img-fluid w-100 h-100"/><div class="insta-counts"><span class="margin-right-20px"><i class="far fa-heart"></i><span class="count-number">{{likes}}</span></span><span><i class="far fa-comment"></i><span class="count-number">{{comments}}</span></span></div></a></div>'
    });
    instaFeedStyle1.run();
  }

  if($('#instaFeed-style2').length != 0){

    var instaFeedStyle1 = new Instafeed({
        target: 'instaFeed-style2',
        "edge_liked_by": {
          "count": 821544
        },
        get: 'user',
        userId: 16918021242,
        limit: 6,
        accessToken: '16918021242.1677ed0.4afc702b03f64021a37acaaa836c4efc',
        resolution: 'standard_resolution',
        error: {
            template:'<div class="col-12"><span class=text-center>No Images Found</span></div>'
        },
        template: '<div class="col-xl-2 col-lg-2 col-md-2 col-4 padding-left-0px padding-right-0px instafeed-style1"><a class="insta-link" href="{{link}}" target="_blank"><img src="{{image}}" class="insta-image img-fluid"/><div class="insta-icon"><span><i class="fab fa-instagram"></i></div></a></div>'
    });
    instaFeedStyle1.run();
  }

  if($('#instaFeed-style3').length != 0){

    var instaFeedStyle1 = new Instafeed({
        target: 'instaFeed-style3',
        "edge_liked_by": {
          "count": 821544
        },
        get: 'user',
        userId: 16918021242,
        limit: 6,
        accessToken: '16918021242.1677ed0.4afc702b03f64021a37acaaa836c4efc',
        resolution: 'standard_resolution',
        error: {
            template:'<div class="col-12"><span class=text-center>No Images Found</span></div>'
        },
        template: '<div class="col-xl-2 col-lg-2 col-md-2 col-4 padding-left-0px padding-right-0px instafeed-style"><a class="insta-link" href="{{link}}" target="_blank"><img src="{{image}}" class="insta-image img-fluid"/></a><div class="insta-caption"><div class="insta-icon"><span><i class="fab fa-instagram"></i></span></div><div class="insta-counts"><span><i class="far fa-heart"></i><span class="count-number">{{likes}}</span></span><span><i class="far fa-comment"></i><span class="count-number">{{comments}}</span></span></div></div></div>'
    });
    instaFeedStyle1.run();
  }

  if($('#instaFeed-style4').length != 0){

    var instaFeedStyle1 = new Instafeed({
        target: 'instaFeed-style4',
        "edge_liked_by": {
          "count": 821544
        },
        get: 'user',
        userId: 16918021242,
        limit: 6,
        accessToken: '16918021242.1677ed0.4afc702b03f64021a37acaaa836c4efc',
        resolution: 'standard_resolution',
        error: {
            template:'<div class="col-12"><span class=text-center>No Images Found</span></div>'
        },
        template: '<div class="col-xl-4 col-lg-4 col-md-4 col-4 padding-left-0px padding-right-0px instafeed-style"><a class="insta-link" href="{{link}}" target="_blank"><img src="{{image}}" class="insta-image img-fluid"/></a><div class="insta-caption"><div class="insta-icon"><span><i class="fab fa-instagram"></i></span></div><div class="insta-counts"><span><i class="far fa-heart"></i><span class="count-number">{{likes}}</span></span><span><i class="far fa-comment"></i><span class="count-number">{{comments}}</span></span></div></div></div>'
    });
    instaFeedStyle1.run();
  }

  if($('#instaFeed-style5').length != 0){

    var instaFeedStyle1 = new Instafeed({
        target: 'instaFeed-style5',
        "edge_liked_by": {
          "count": 821544
        },
        get: 'user',
        userId: 16918021242,
        limit: 6,
        accessToken: '16918021242.1677ed0.4afc702b03f64021a37acaaa836c4efc',
        resolution: 'standard_resolution',
        error: {
            template:'<div class="col-12"><span class=text-center>No Images Found</span></div>'
        },
        template: '<li class="instafeed-style"><a class="insta-link" href="{{link}}" target="_blank"><img src="{{image}}" class="insta-image img-fluid"/></a><div class="insta-caption"><div class="insta-icon"><span><i class="fab fa-instagram"></i></span></div><div class="insta-counts"><span><i class="far fa-heart"></i><span class="count-number">{{likes}}</span></span><span><i class="far fa-comment"></i><span class="count-number">{{comments}}</span></span></div></li>'
    });
    instaFeedStyle1.run();
  }

  /* ========================================== 
  Should be equal the the height of the header
  ========================================== */

  window.onscroll = function() {stickyheader()};

  var header = document.getElementById("collapsibleNavbar");
  if(header != undefined){
    var sticky = header.offsetTop;

    function stickyheader() {
      if (window.pageYOffset > sticky) {
        header.classList.add("sticky");
      } else {
        header.classList.remove("sticky");
      }
    }
  }

  /* ========================================== 
  Minus and Plus Btn Height
  ========================================== */

  $('.minus-btn,.minus-btn-1').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    if (value > 1) {
      value = value - 1;
    } else {
      value = 0;
    }
    $input.val(value);
  });

  $('.plus-btn,.plus-btn-1').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    if (value < 100) {
      value = value + 1;
    } else {
      value =100;
    }
    $input.val(value);
  });

  /* ========================================== 
  Billing page card check
  ========================================== */

  var conditionalContent = $('#card'),
  group = $('input[type=radio][name=radio]');
  group.change(function(){
    conditionalContent.toggle(group.filter(':checked').val() === 'yes');
  }).change();
  
  /* ========================================== 
  Effect Of Elements
  ========================================== */

  AOS.init({
    duration: 1200,
  });

  /* ========================================== 
  Mobile Menu Click Events
  ========================================== */

  var win_width = $( window ).width();
  if(win_width <= 991){
    $('.nav li').on("click", function(){
      if($(this).hasClass('activate')){
        $(this).children(".dropdown-content").removeClass('menu-open-class');
        $(this).removeClass('activate');
      }else{
        $(".dropdown-content").removeClass('menu-open-class');
        $('.nav li').removeClass('activate');
        $(".dropdown-content,.dropdown-blog").css('display','none');
        $(this).children(".dropdown-content").addClass('menu-open-class'); 
        $(this).children(".dropdown-content").css('display','block');
        $(this).addClass('activate'); 
      }
    });
  }

  var win_width = $( window ).width();
  if(win_width <= 991){
    $('li.dropdown-submenu a').on("click", function(){
      if($(this).hasClass('activate')){
        $(this).next("div").removeClass('menu-open-class');
        $(this).next("div").css('display','none');
        $(this).next("ul").removeClass('menu-open-class'); 
        $(this).next("ul").css('display','none');
        $(this).removeClass('activate'); 
      }else{
        $(this).next("div").addClass('menu-open-class'); 
        $(this).next("div").css('display','block');
        $(this).next("ul").addClass('menu-open-class'); 
        $(this).next("ul").css('display','block');
        $(this).addClass('activate'); 
      }
    });
  }


  /* ========================================== 
  Mini cart
  ========================================== */

  if(win_width <= 1024){
    $('.show-cart').on('click', function (e) {
          if($('.cart-drop-down').hasClass('active')){
              $('.cart-drop-down').removeClass('active');
          }else{
              $('.cart-drop-down').addClass('active');
          }
      });
      $('.setting').on('click', function (e) {
          if($('.setting-drop-down').hasClass('active')){
              $('.setting-drop-down').removeClass('active');
          }else{
              $('.setting-drop-down').addClass('active');
          }
      });
      $('.user').on('click', function (e) {
          if($('.user-drop-down').hasClass('active')){
              $('.user-drop-down').removeClass('active');
          }else{
              $('.user-drop-down').addClass('active');
          }
      });
  }

  /* ========================================== 
  Coming Soon Page Background Image Slide
  ========================================== */

  $('.coming-slide,.footer').each(function(){
    var delay = 7000;
    var speed = 1000;
    var itemSlide = $(this).find('.coming-item,.footer-image');
    var nowSlide = 0;

    $(itemSlide).hide();
    $(itemSlide[nowSlide]).show();
    nowSlide++;
    if(nowSlide >= itemSlide.length) {nowSlide = 0;}

    setInterval(function(){
        $(itemSlide).fadeOut(speed);
        $(itemSlide[nowSlide]).fadeIn(speed);
        nowSlide++;
        if(nowSlide >= itemSlide.length) {nowSlide = 0;}
    },delay);
  });

  /* ========================================== 
  Dropdown Click Events
  ========================================== */

  $('.dropdown-el').on("click",function(e) {
      e.preventDefault();
      e.stopPropagation();
      $(this).toggleClass('expanded');
      $('#'+$(e.target).attr('for')).prop('checked',true);
  });

  $(document).on("click",function() {
      $('.dropdown-el').removeClass('expanded');
  });


  $(document).ready(function(){
    $('#nav-icon,#nav-plus-icon').click(function(){
      $(this).toggleClass('is-active');
    });
  });

  $("#navbar > button.menu").on("click", function() {
    $('.head').toggleClass('open');
    $(this).toggleClass('open').next('.navbar-collapse').toggleClass('open');
    $(this).parents().siblings().find('.navbar-collapse').removeClass('open');
  });

  $("#all-header-icon > button.plus-opt").on("click", function() {
    $('.head').toggleClass('side-open');
    $(this).toggleClass('side-open').next('.input-search-1').toggleClass('side-open');
    $(this).parents().siblings().find('.input-search-1').removeClass('side-open');
  });


  /* ========================================== 
   portfolio
  ========================================== */
  
  $('#portfolio').imagesLoaded(function () {
    // filter items on button click
    $('.filtering').on('click', 'span', function () {
      var filterValue = $(this).attr('data-filter');
      $grid.isotope({ filter: filterValue });
      $(this)
      .addClass("active")
      .siblings()
      .removeClass("active");
    });

    var $grid = $('.gallery').isotope({
      // options options
      itemSelector: '.items',
      layoutMode: 'fitRows'
    });
    // images have loaded
  });

  /* ========================================== 
   index theme slider
  ========================================== */

  $('.other-theme').owlCarousel({
    loop:true,
    margin:30,
    nav:false,
    autoplay:true,
    autoplayTimeout:5000,
    autoplayHoverPause:true,
    responsive:{
        0:{
            items:1
        },
        600:{
            items:2
        },
        1000:{
            items:3
        },
        1199:{
            items:4
        }
    }
  });