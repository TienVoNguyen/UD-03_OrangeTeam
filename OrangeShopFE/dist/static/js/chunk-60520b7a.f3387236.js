(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-60520b7a"],{"09f4":function(e,t,i){"use strict";i.d(t,"a",(function(){return a})),Math.easeInOutQuad=function(e,t,i,o){return e/=o/2,e<1?i/2*e*e+t:(e--,-i/2*(e*(e-2)-1)+t)};var o=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function l(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function n(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function a(e,t,i){var a=n(),s=e-a,c=20,d=0;t="undefined"===typeof t?500:t;var r=function e(){d+=c;var n=Math.easeInOutQuad(d,a,s,t);l(n),d<t?o(e):i&&"function"===typeof i&&i()};r()}},"15cb":function(e,t,i){"use strict";i.r(t);var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"app-container"},[t("div",{staticClass:"row mt-3"},[t("div",{staticClass:"col-4"},[t("label",[e._v("Mã số thuế")]),t("el-input",{staticClass:"form-control non-border",attrs:{id:"code_tax",value:"",placeholder:"Nhập mã đơn vị"},model:{value:e.temp.code_tax,callback:function(t){e.$set(e.temp,"code_tax",t)},expression:"temp.code_tax"}})],1),t("div",{staticClass:"col-4"},[t("label",[e._v("Mã số đăng ký kinh doanh")]),t("el-input",{staticClass:"form-control non-border",attrs:{id:"code_reg",value:"",placeholder:"Nhập mã số đăng ký kinh doanh"},model:{value:e.temp.code_reg,callback:function(t){e.$set(e.temp,"code_reg",t)},expression:"temp.code_reg"}})],1),t("div",{staticClass:"col-4",staticStyle:{display:"flex"}},[t("div",{staticClass:"col-5"},[t("label",[e._v("Mã CIF (FCC)")]),t("el-input",{staticClass:"form-control non-border",attrs:{id:"code_cif",value:"",placeholder:"Nhập mã CIF (FCC)"},model:{value:e.temp.code_cif,callback:function(t){e.$set(e.temp,"code_cif",t)},expression:"temp.code_cif"}})],1),t("div",{staticClass:"col-1"}),t("div",{staticClass:"col-5"},[t("label",[e._v("Mã CIF (Tạm)")]),t("el-input",{staticClass:"form-control non-border",attrs:{id:"code_cif_temp",value:"",placeholder:"Nhập mã CIF (Tạm)"},model:{value:e.temp.code_cif_temp,callback:function(t){e.$set(e.temp,"code_cif_temp",t)},expression:"temp.code_cif_temp"}})],1)])]),t("div",{staticClass:"row mt-5"},[t("div",{staticClass:"col-6"},[t("button",{staticClass:"btn btn-outline-primary float-end",on:{click:function(t){return e.searchModel()}}},[e._v("Tìm kiếm")])]),t("div",{staticClass:"col-6"},[t("router-link",{staticClass:"btn btn-outline-warning",staticStyle:{color:"#EF8C1A"},attrs:{to:{path:"/creditRatingsRouter/ctck_customers/add_customer_identification"}}},[e._v("Thêm mới")])],1)]),-1!=e.errorSearch.type?t("div",{staticClass:"row mt-3"},[t("span",{staticClass:"el-icon-warning",staticStyle:{"font-size":"40px",color:"#FF0000","text-align":"center"}}),t("span",{staticStyle:{"font-size":"20px",color:"#FF0000","text-align":"center"}},[e._v(" "+e._s(e.errorSearch.message))])]):e._e(),t("div",{staticClass:"row mt-5"},[t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],key:e.tableKey,staticStyle:{width:"100%"},attrs:{data:e.list,"header-cell-style":{background:"#5E2E864D",color:"#000000",borderRightStyle:"dashed"},"cell-style":{borderRightStyle:"dashed"},border:"",fit:"","highlight-current-row":""}},[t("el-table-column",{attrs:{label:"Thao tác",width:"100px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(i){var o=i.row,l=i.$index;return[t("router-link",{staticClass:"el-icon-edit",staticStyle:{color:"#083863"},attrs:{to:{path:"/creditRatingsRouter/ctck_customers/update_customer_identification/"+o.id}}}),t("span",{on:{click:function(t){return e.handleDelete(o,l)}}},[t("i",{staticClass:"el-icon-delete",staticStyle:{color:"#FF485A"}})])]}}])}),t("el-table-column",{attrs:{label:"Mã số thuế",width:"130px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[e._v(" "+e._s(i.code_tax)+" ")]}}])}),t("el-table-column",{attrs:{label:"Mã số ĐKKD",width:"130px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(i){var o=i.row;return[t("span",[e._v(e._s(o.code_reg))])]}}])}),t("el-table-column",{attrs:{label:"Mã CIF (FCC)",width:"130px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[e._v(" "+e._s(i.code_cif)+" ")]}}])}),t("el-table-column",{attrs:{label:"Mã CIF (Tạm)",width:"130px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[e._v(" "+e._s(i.code_cif_temp)+" ")]}}])}),t("el-table-column",{attrs:{label:"Tên doanh nghiệp"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[e._v(" "+e._s(i.name_dn)+" ")]}}])}),t("el-table-column",{attrs:{label:"Tên ĐVQL"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[e._v(" "+e._s(i.name_dept)+" ")]}}])}),t("el-table-column",{attrs:{label:"Mã ĐVQL",width:"160px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[e._v(" "+e._s(i.code_dept)+" ")]}}])})],1),t("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.page,limit:e.listQuery.limit},on:{"update:page":function(t){return e.$set(e.listQuery,"page",t)},"update:limit":function(t){return e.$set(e.listQuery,"limit",t)},pagination:e.getList}}),t("DeleteCustomer",{attrs:{"dialog-delete-visible":e.dialogDeleteVisible},on:{"update:dialogDeleteVisible":function(t){e.dialogDeleteVisible=t},"update:dialog-delete-visible":function(t){e.dialogDeleteVisible=t}}})],1)])},l=[],n=i("6724"),a=i("333d"),s=function(){var e=this,t=e._self._c;return t("el-dialog",{attrs:{title:"Xóa người Khách hàng",visible:e.dialogDeleteVisible,"before-close":e.closeDialog,center:!0,width:"30%","show-close":!1},on:{"update:visible":function(t){e.dialogDeleteVisible=t},"update:beforeClose":function(t){e.closeDialog=t},"update:before-close":function(t){e.closeDialog=t}}},[t("h3",{staticClass:"title mt-3",attrs:{slot:"title"},slot:"title"},[t("strong",[e._v("Thông Báo!")])]),t("div",{staticClass:"text-center"},[t("h5",{staticClass:"text-danger"},[e._v("Bạn có chắc muốn xóa Khách hàng?")])]),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("button",{staticClass:"btn btn-outline-secondary me-3",on:{click:e.closeDialog}},[e._v("Đóng")]),t("button",{staticClass:"btn btn-outline-warning",on:{click:e.deleteData}},[e._v("Đồng ý")])])])},c=[],d={name:"DeleteCustomer",props:{dialogDeleteVisible:{type:Boolean,default:!1}},methods:{closeDialog:function(){this.$emit("update:dialogDeleteVisible",!1)},deleteData:function(){}}},r=d,u=(i("9b4c"),i("2877")),p=Object(u["a"])(r,s,c,!1,null,"744f3729",null),m=p.exports,f={name:"CustomerIdentificationList",components:{Pagination:a["a"],DeleteCustomer:m},directives:{waves:n["a"]},data:function(){return{tableKey:0,list:null,total:0,listLoading:!0,listQuery:{page:1,limit:20,importance:void 0,title:void 0,sort:"+id"},status:null,type:null,dialogFormVisible:!1,dialogStatus:"",dialogFormUpdateVisible:!1,dialogImportVisible:!1,dialogDeleteVisible:!1,temp:{code_tax:null,code_reg:null,code_cif:null,code_cif_temp:null},errorSearch:{type:-1,message:null}}},created:function(){this.getList(),this.getStatus(),this.getType(),this.getSide(),this.clearValidateSearch()},methods:{resetTemp:function(){this.temp={code_tax:null,code_reg:null,code_cif:null,code_cif_temp:null}},handleCreate:function(){this.dialogStatus="create",this.resetTemp(),this.dialogFormVisible=!0},handleUpdate:function(e,t){this.dialogStatus="update",this.temp=Object.assign({},e),this.dialogFormUpdateVisible=!0},handleDelete:function(e,t){this.dialogDeleteVisible=!0,this.temp=Object.assign({},e)},handleImport:function(){this.dialogImportVisible=!0},getStatus:function(){this.status=[{id:0,name:"Không hoạt động"},{id:1,name:"Hoạt động"}]},getSide:function(){this.side=[{id:1,name:"Miền bắc"},{id:2,name:"Miền nam"}]},getType:function(){this.type=[{id:1,name:"Chi nhánh"},{id:2,name:"Hội sở"}]},getList:function(){var e=this;this.listLoading=!0,this.list=[{id:1,code_tax:"TAX011",code_reg:"REG011",code_cif:"CIF011",code_cif_temp:"CIF_TEMP011",name_dn:"DN011",name_dept:"DEPT011",code_dept:"CODE_DEPT011"},{id:2,code_tax:"TAX012",code_reg:"REG012",code_cif:"CIF012",code_cif_temp:"CIF_TEMP012",name_dn:"DN012",name_dept:"DEPT012",code_dept:"CODE_DEPT012"},{id:3,code_tax:"TAX013",code_reg:"REG013",code_cif:"CIF013",code_cif_temp:"CIF_TEMP013",name_dn:"DN013",name_dept:"DEPT013",code_dept:"CODE_DEPT013"}],this.total=this.list.length,setTimeout((function(){e.listLoading=!1}),1500)},searchModel:function(){this.checkValidateSearch()},checkValidateSearch:function(){null!=this.temp.code_tax&&""!==this.temp.code_tax&&void 0!==this.temp.code_tax||null!=this.temp.code_reg&&""!==this.temp.code_reg&&void 0!==this.temp.code_reg||null!=this.temp.code_cif&&""!==this.temp.code_cif&&void 0!==this.temp.code_cif||null!=this.temp.code_cif_temp&&""!==this.temp.code_cif_temp&&void 0!==this.temp.code_cif_temp||(this.errorSearch={type:1,message:"Vui lòng nhập ít nhất 1 tham số trước khi tìm kiếm thông tin"})},clearValidateSearch:function(){this.errorSearch={type:-1,message:null}}}},h=f,_=(i("c715"),Object(u["a"])(h,o,l,!1,null,null,null));t["default"]=_.exports},"1fd5":function(e,t,i){},6724:function(e,t,i){"use strict";i("8d41");var o="@@wavesContext";function l(e,t){function i(i){var o=Object.assign({},t.value),l=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},o),n=l.ele;if(n){n.style.position="relative",n.style.overflow="hidden";var a=n.getBoundingClientRect(),s=n.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":(s=document.createElement("span"),s.className="waves-ripple",s.style.height=s.style.width=Math.max(a.width,a.height)+"px",n.appendChild(s)),l.type){case"center":s.style.top=a.height/2-s.offsetHeight/2+"px",s.style.left=a.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(i.pageY-a.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(i.pageX-a.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=l.color,s.className="waves-ripple z-active",!1}}return e[o]?e[o].removeHandle=i:e[o]={removeHandle:i},i}var n={bind:function(e,t){e.addEventListener("click",l(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[o].removeHandle,!1),e.addEventListener("click",l(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[o].removeHandle,!1),e[o]=null,delete e[o]}},a=function(e){e.directive("waves",n)};window.Vue&&(window.waves=n,Vue.use(a)),n.install=a;t["a"]=n},"8d41":function(e,t,i){},"9b4c":function(e,t,i){"use strict";i("1fd5")},b761:function(e,t,i){},c715:function(e,t,i){"use strict";i("b761")}}]);