(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-174fd762"],{"2f0e":function(t,e,a){"use strict";e["a"]={data:function(){return{typeFReports:[{label:"A",value:1}],statusFStatuses:[{label:"A",value:1}],tableData:[{name:"Bảng cân đối kế toán_2022",code:"TT200",createdDate:"02/08/2022",approvedDate:"",status:"Tạo mới"}],tableHeader:[{label:"Tên biểu mẫu nhập liệu",prop:"name",width:"100"},{label:"Mẫu biểu BCTC",prop:"code",width:"100"},{label:"Ngày tạo",prop:"createdDate",width:"100"},{label:"Ngày duyệt",prop:"approvedDate",width:"100"},{label:"Trạng thái",prop:"status",width:"100"}],tableData1:[{stt:1,ttc:"TT200",mnt:"STR",mnt1:"TTRY"}],tableHeader1:[{label:"STT",prop:"stt",width:"100",align:"center"},{label:"Tên tiêu chí",prop:"ttc",width:"100",hasSearch:!0},{label:"Mã năm T",prop:"mnt",width:"100",hasSearch:!0},{label:"Mã năm T-1",prop:"mnt1",width:"100",hasSearch:!0}]}}}},"51a2":function(t,e,a){},9759:function(t,e,a){},d7b2:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t._self._c;return e("div",{staticClass:"fluid-container"},[e("filter-table-vue",{staticClass:"mt-5"}),e("div",{staticClass:"d-flex justify-content-center mt-5"},[e("el-button",{attrs:{type:"css-secondary"}},[t._v("Tìm kiếm")]),e("el-button",{attrs:{type:"css-primary "}},[t._v("Thêm mới")])],1),e("div",{staticClass:"mx-5 mt-5"},[e("custom-table-vue",{attrs:{"table-data":t.tableData,"table-header":t.tableHeader}}),e("div",{staticClass:"d-block float-right"},[e("el-pagination",{attrs:{"current-page":t.pagination.currentPage,"page-size":t.pagination.pageSize,layout:"prev, pager, next,total",total:t.pagination.total},on:{"update:currentPage":function(e){return t.$set(t.pagination,"currentPage",e)},"update:current-page":function(e){return t.$set(t.pagination,"currentPage",e)},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)],1)},l=[],i=(a("4de4"),a("d3b7"),function(){var t=this,e=t._self._c;return e("div",{staticClass:"d-block"},[e("div",{staticClass:"container"},[e("div",{staticClass:"row justify-content-around"},[e("div",{staticClass:"col-4"},[e("span",{staticStyle:{"font-weight":"bold"}},[t._v("Mẫu biểu BCTC")]),e("el-select",{staticClass:"mt-3 d-block",attrs:{clearable:"",placeholder:"---Tất cả---"},model:{value:t.filter.typeFReport,callback:function(e){t.$set(t.filter,"typeFReport",e)},expression:"filter.typeFReport"}},t._l(t.typeFReports,(function(t){return e("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),e("div",{staticClass:"col-4"},[e("span",{staticStyle:{"font-weight":"bold"}},[t._v("Tên biểu mẫu nhập liệu")]),e("el-input",{staticClass:"mt-3",attrs:{placeholder:"Nhập tên biểu mẫu","prefix-icon":"el-icon-search"},model:{value:t.filter.nameFReport,callback:function(e){t.$set(t.filter,"nameFReport",e)},expression:"filter.nameFReport"}})],1)]),e("div",{staticClass:"row justify-content-around mt-3"},[e("div",{staticClass:"col-4"},[e("span",{staticStyle:{"font-weight":"bold"}},[t._v("Ngày tạo")]),e("div",{staticClass:"container mt-3"},[e("div",{staticClass:"row"},[e("div",{staticClass:"col-5 px-0"},[e("el-date-picker",{attrs:{placeholder:"Từ ngày",type:"date"},model:{value:t.filter.fromDateFReport,callback:function(e){t.$set(t.filter,"fromDateFReport",e)},expression:"filter.fromDateFReport"}})],1),e("div",{staticClass:"col-2 text-center align-middle"},[t._v("đến")]),e("div",{staticClass:"col-5 px-0"},[e("el-date-picker",{attrs:{placeholder:"Đến ngày",type:"date"},model:{value:t.filter.toDateFReport,callback:function(e){t.$set(t.filter,"toDateFReport",e)},expression:"filter.toDateFReport"}})],1)])])]),e("div",{staticClass:"col-4"},[e("span",{staticStyle:{"font-weight":"bold"}},[t._v("Trạng thái")]),e("el-select",{staticClass:"mt-3 d-block",attrs:{clearable:"",placeholder:"---Tất cả---"},model:{value:t.filter.statusFReport,callback:function(e){t.$set(t.filter,"statusFReport",e)},expression:"filter.statusFReport"}},t._l(t.statusFStatuses,(function(t){return e("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1)])])])}),r=[],o=a("2f0e"),s={name:"FilterTable",mixins:[o["a"]],data:function(){return{filter:{}}}},c=s,u=(a("ff5a"),a("2877")),p=Object(u["a"])(c,i,r,!1,null,"41a319d8",null),d=p.exports,f=function(){var t=this,e=t._self._c;return e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[e("el-table-column",{attrs:{label:"Thao tác"},scopedSlots:t._u([{key:"default",fn:function(a){return[e("el-button",{attrs:{size:"mini",icon:"el-icon-edit",plain:""},on:{click:function(e){return t.handleEvent(a.$index,a.row)}}}),e("el-button",{attrs:{size:"mini",type:"danger",icon:"el-icon-delete",plain:""},on:{click:function(e){return t.handleEvent(a.$index,a.row)}}}),e("el-button",{attrs:{size:"mini",type:"css-secondary",icon:"el-icon-view",plain:""},on:{click:function(e){return t.handleEvent(a.$index,a.row)}}}),e("el-button",{attrs:{size:"mini",type:"css-primary",icon:"el-icon-copy-document",plain:""},on:{click:function(e){return t.handleEvent(a.$index,a.row)}}})]}}])}),t._l(t.tableHeader,(function(t){return e("el-table-column",{key:t.prop,attrs:{prop:t.prop,label:t.label}})}))],2)},b=[],h={name:"CustomTable",props:{tableData:{type:Array,default:function(){return[]}},tableHeader:{type:Array,default:function(){return[]}}},data:function(){return{search:{}}},created:function(){},methods:{handleEvent:function(t,e){console.log(t,e)}}},m=h,v=Object(u["a"])(m,f,b,!1,null,null,null),g=v.exports,y={name:"FinancialReport",components:{filterTableVue:d,customTableVue:g},mixins:[o["a"]],data:function(){return{pagination:{currentPage:1,pageSize:100,total:1e3}}},methods:{handleSizeChange:function(t){console.log("".concat(t," items per page"))},handleCurrentChange:function(t){console.log("current page: ".concat(t))}}},C=y,T=(a("f956"),Object(u["a"])(C,n,l,!1,null,"203d338c",null));e["default"]=T.exports},f956:function(t,e,a){"use strict";a("51a2")},ff5a:function(t,e,a){"use strict";a("9759")}}]);