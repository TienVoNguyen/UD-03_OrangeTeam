(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1c62e7aa"],{"0365":function(e,t,n){},"09f4":function(e,t,n){"use strict";n.d(t,"a",(function(){return a})),Math.easeInOutQuad=function(e,t,n,i){return e/=i/2,e<1?n/2*e*e+t:(e--,-n/2*(e*(e-2)-1)+t)};var i=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function o(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function a(e,t,n){var a=s(),l=e-a,r=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=r;var s=Math.easeInOutQuad(c,a,l,t);o(s),c<t?i(e):n&&"function"===typeof n&&n()};u()}},1:function(e,t){},2:function(e,t){},"262b":function(e,t,n){},3:function(e,t){},3081:function(e,t,n){"use strict";var i=n("5530"),o=(n("d3b7"),n("25f0"),n("4c93")),s=n("7bb1");Object(s["c"])("required",Object(i["a"])(Object(i["a"])({},o["c"]),{},{message:" bắt buộc nhập"})),Object(s["c"])("requiredSelect",Object(i["a"])(Object(i["a"])({},o["c"]),{},{message:" bắt buộc chọn"})),Object(s["c"])("ext",Object(i["a"])(Object(i["a"])({},o["a"]),{},{message:" không đúng định dạng"})),Object(s["c"])("regex",Object(i["a"])(Object(i["a"])({},o["b"]),{},{message:" không đúng định dạng"})),Object(s["c"])("max",{validate:function(e,t){return e.toString().length<=t.length},params:["length"],message:" không vượt quá {length} ký tự"}),Object(s["c"])("isLower",{params:["target"],validate:function(e,t){var n=t.target;return!n||+e<+n},message:" phải nhỏ hơn chặn trên"}),t["a"]={data:function(){return{loginFields:{username:"Tên đăng nhập",password:"Mật khẩu"},approverFields:{unit:"Đơn vị",position:"Chức vụ",approver:"Họ và tên"},scorePeriodFields:{name:"Kỳ chấm điểm"},lendingFields:{type:"Loại chấm điểm",name:"Loại hình chấm điểm"},systemFields:{dept1:"Đơn vị",dept1_type:"Loại đơn vị",dept2:"Phòng ban",side:"Miền",name:"Tên",status:"Trạng thái",code:"Mã",position:"Chức vụ",file:"File"},customer_identificationFields:{code_tax:"Mã số thuế",code_reg:"Mã số đăng ký kinh doanh",name_dn:"Tên doanh nghiệp",name_dept:"Tên đơn vị quản lý",code_dept:"Mã đơn vị quản lý"},rank_adjustFields:{rule_change:"Quy tắc điều chỉnh",cause_change:"Nguyên nhân điều chỉnh",reason_change:"Lý do điều chỉnh"},functionsName:{title:"Tiêu đề",path:"Path",icon:"Icon",component:"Mã màn hình",priority:"Thứ tự"},economic_sbvFields:{codeSBV:"Mã ngành kinh tế SBV",nameSBV:"Tên ngành kinh tế SBV",requestChange:"Yêu cầu điều chỉnh",file:"File"},groupFCCFields:{code:"Mã ngành kinh tế FCC",name:"Tên ngành kinh tế FCC",sbv:"Ngành kinh tế SBV",css:"Nhóm ngành"},groupFCCImportFields:{file:"File"},matrixClassifyFields:{nameMatrix:"Tên ma trận",rules:"Loại quy tắc phân loại quy mô",tpBankRule:"Quy tắc phân loại TPBank",pointRule:"Quy tắc phân loại theo điểm chỉ tiêu",establishMatrix:"Thiết lập ma trận",requestChangeMatrix:"Lý do từ chối"},tpBankRuleFields:{name:"Tên quy tắc",unit:"Đơn vị báo cáo tài chính",sales:"Doanh thu",salesCriteria:"Doanh thu - Chọn tiêu chí",equity:"Vốn chủ sở hữu",equityCriteria:"Vốn chủ sở hữu - Chọn tiêu chí",configScalePointList:"Thiết lập điểm quy mô"},salesScalePointFields:{fromSign:"Dấu",fromValue:"Chặn dưới",toValue:"Chặn trên",toSign:"Dấu"},equityScalePointFields:{fromSign:"Dấu",fromValue:"Chặn dưới",toValue:"Chặn trên",toSign:"Dấu"},configScalePointFields:{size:"Size",scale:"Quy mô"},branches:{branchCode:"Mã đơn vị",branchCodeNumber:"Mã số đơn vị",branchTypeName:"Loại đơn vị",branchName:"Tên đơn vị",districtName:"Miền"},departments:{branchName:"Tên đơn vị",name:"Tên phòng ban"},positions:{branchName:"Tên đơn vị",name:"Tên chức vụ",departmentName:"Tên phòng ban"},groups:{name:"Tên nhóm",code:"Mã nhóm"},users:{groupName:"Tên nhóm",username:"Tên đăng nhập",name:"Tên nhân viên"},ruleChangeRankFields:{content:"Lý do"},singleScoringModelKHDNFields:{codeModel:"Mã mô hình đơn",nameModel:"Tên mô hình đơn",typeRank:"Loại xếp hạng",scaleClassify:"Phân loại quy mô",industryGroup:"Nhóm ngành",inputModel:"Tên mô hình nhập liệu",typeCode:"Loại code",fileCodeModel:"File code model",fileImport:"File",userCreated:"Người tạo",userApproved:"Người duyệt"},configParameterPropertyFields:{codeProperty:"Mã thuộc tính",nameProperty:"Tên thuộc tính",description:"Mô tả",file:"File"}}},methods:{messageError:function(e,t){if(void 0!==t)return e+t}}}},"59ec":function(e,t,n){"use strict";n("b0c0");var i=function(){var e=this,t=e._self._c;return t("el-form",{ref:"scorePeriodForm",staticClass:"mt-3",attrs:{"label-position":"left"}},[t("div",{staticClass:"row"},[t("div",{staticClass:"col-12"},[t("label",{staticClass:"label"},[e._v("I. Thông tin chung")])])]),t("div",{staticClass:"row"},[t("div",{staticClass:"col-4"},[t("ValidationProvider",{attrs:{name:"name",rules:"required"},scopedSlots:e._u([{key:"default",fn:function(n){var i=n.errors;return[t("el-form-item",{attrs:{prop:"name",error:e.messageError(e.scorePeriodFields.name,i[0])}},[t("label",{attrs:{for:"name"}},[e._v("Kỳ chấm điểm "),t("span",{staticClass:"text-danger"},[e._v("*")])]),t("el-input",{ref:"name",staticClass:"form-control non-border",attrs:{id:"name",placeholder:"Nhập kỳ chấm điểm",disabled:e.disabled},model:{value:e.scorePeriod.name,callback:function(t){e.$set(e.scorePeriod,"name",t)},expression:"scorePeriod.name"}})],1)]}}])})],1),t("div",{staticClass:"col-4"},[t("el-form-item",[t("label",[e._v("Thời gian tổ chức địa điểm")]),t("div",{staticClass:"row"},[t("div",{staticClass:"col-6"},[t("el-input",{ref:"startDate",staticClass:"form-control non-border",attrs:{placeholder:"Từ ngày",onfocus:"this.type='date'",onblur:"if(this.value===''){this.type='text'}",disabled:e.disabled},model:{value:e.scorePeriod.startDate,callback:function(t){e.$set(e.scorePeriod,"startDate",t)},expression:"scorePeriod.startDate"}})],1),t("div",{staticClass:"col-6"},[t("el-input",{ref:"endDate",staticClass:"form-control non-border",attrs:{placeholder:"Đến ngày",onfocus:"this.type='date'",onblur:"if(this.value===''){this.type='text'}",disabled:e.disabled},model:{value:e.scorePeriod.endDate,callback:function(t){e.$set(e.scorePeriod,"endDate",t)},expression:"scorePeriod.endDate"}})],1)])])],1),t("div",{staticClass:"col-4"},[t("el-form-item",[t("label",{attrs:{for:"status"}},[e._v("Trạng thái")]),t("el-select",{staticClass:"form-control non-border",attrs:{id:"status","value-key":"status",placeholder:"---Tất cả---",disabled:e.disabled},model:{value:e.status,callback:function(t){e.status=t},expression:"status"}},e._l(e.listStatus,(function(e){return t("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1)],1)])])},o=[],s=n("7bb1"),a=n("3081"),l={name:"GeneralInfo",components:{ValidationProvider:s["b"]},mixins:[a["a"]],props:{disabled:{type:Boolean,default:!1},scorePeriod:{type:Object,default:null}},data:function(){return{listStatus:[{label:"Khóa",value:2},{label:"Đang thực hiện",value:1}],status:null}},created:function(){this.scorePeriod?this.status=this.scorePeriod.status:this.scorePeriod={name:"",startDate:"",endDate:"",status:null}},methods:{messageError:function(e,t){if(void 0!==t)return e+t}}},r=l,c=(n("688bf"),n("2877")),u=Object(c["a"])(r,i,o,!1,null,"2192038c",null);t["a"]=u.exports},6724:function(e,t,n){"use strict";n("8d41");var i="@@wavesContext";function o(e,t){function n(n){var i=Object.assign({},t.value),o=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),s=o.ele;if(s){s.style.position="relative",s.style.overflow="hidden";var a=s.getBoundingClientRect(),l=s.querySelector(".waves-ripple");switch(l?l.className="waves-ripple":(l=document.createElement("span"),l.className="waves-ripple",l.style.height=l.style.width=Math.max(a.width,a.height)+"px",s.appendChild(l)),o.type){case"center":l.style.top=a.height/2-l.offsetHeight/2+"px",l.style.left=a.width/2-l.offsetWidth/2+"px";break;default:l.style.top=(n.pageY-a.top-l.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",l.style.left=(n.pageX-a.left-l.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return l.style.backgroundColor=o.color,l.className="waves-ripple z-active",!1}}return e[i]?e[i].removeHandle=n:e[i]={removeHandle:n},n}var s={bind:function(e,t){e.addEventListener("click",o(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[i].removeHandle,!1),e.addEventListener("click",o(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[i].removeHandle,!1),e[i]=null,delete e[i]}},a=function(e){e.directive("waves",s)};window.Vue&&(window.waves=s,Vue.use(a)),s.install=a;t["a"]=s},"688bf":function(e,t,n){"use strict";n("fe3b")},"759c":function(e,t,n){"use strict";n("262b")},8479:function(e,t,n){"use strict";n("b0c0");var i=function(){var e=this,t=e._self._c;return t("div",[t("div",[t("el-form",{ref:"customerFilterForm",staticClass:"mt-3",attrs:{"label-position":"left"}},[t("div",{staticClass:"row"},[t("div",{staticClass:"col-12"},[t("label",{staticClass:"label"},[e._v("II. Danh sách khách hàng chấm điểm định kỳ")])])]),t("div",{staticClass:"row mt-3"},[t("div",{staticClass:"col-4"},[t("el-form-item",{attrs:{prop:"taxCode"}},[t("label",{attrs:{for:"tax-code"}},[e._v("Mã số thuế")]),t("el-input",{ref:"taxCode",staticClass:"form-control non-border",attrs:{id:"tax-code",placeholder:"Nhập mã số thuế"},model:{value:e.customerFilter.taxCode,callback:function(t){e.$set(e.customerFilter,"taxCode",t)},expression:"customerFilter.taxCode"}})],1)],1),t("div",{staticClass:"col-4"},[t("el-form-item",{attrs:{prop:"businessCode"}},[t("label",{attrs:{for:"business-code"}},[e._v("Mã số đăng ký kinh doanh")]),t("el-input",{ref:"businessCode",staticClass:"form-control non-border",attrs:{id:"business-code",placeholder:"Nhập mã số thuế đăng ký kinh doanh"},model:{value:e.customerFilter.businessCode,callback:function(t){e.$set(e.customerFilter,"businessCode",t)},expression:"customerFilter.businessCode"}})],1)],1),t("div",{staticClass:"col-4"},[t("el-form-item",{attrs:{prop:"cifCode"}},[t("div",{staticClass:"row"},[t("div",{staticClass:"col-6"},[t("label",[e._v("Mã CIF (FCC)")]),t("el-input",{staticClass:"form-control non-border",attrs:{placeholder:"Nhập CIF"},model:{value:e.customerFilter.fccCode,callback:function(t){e.$set(e.customerFilter,"fccCode",t)},expression:"customerFilter.fccCode"}})],1),t("div",{staticClass:"col-6"},[t("label",[e._v("Mã CIF (Tạm)")]),t("el-input",{staticClass:"form-control non-border",attrs:{placeholder:"Nhập CIF"},model:{value:e.customerFilter.cifDraftCode,callback:function(t){e.$set(e.customerFilter,"cifDraftCode",t)},expression:"customerFilter.cifDraftCode"}})],1)])])],1)]),t("div",{staticClass:"row"},[t("div",{staticClass:"col-4"},[t("el-form-item",{attrs:{prop:"unit"}},[t("label",{attrs:{for:"unit"}},[e._v("Tên ĐVQL")]),t("el-select",{staticClass:"form-control non-border",attrs:{id:"unit",placeholder:"---Chọn---"},model:{value:e.customerFilter.unit,callback:function(t){e.$set(e.customerFilter,"unit",t)},expression:"customerFilter.unit"}},e._l(e.units,(function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1)],1),t("div",{staticClass:"col-4"},[t("el-form-item",{attrs:{prop:"businessCode"}},[t("label",{attrs:{for:"unit-code"}},[e._v("Mã ĐVQL")]),t("el-input",{ref:"unitCode",staticClass:"form-control non-border",attrs:{id:"unit-code",placeholder:"Nhập mã ĐVQL"},model:{value:e.customerFilter.unitCode,callback:function(t){e.$set(e.customerFilter,"unitCode",t)},expression:"customerFilter.unitCode"}})],1)],1),t("div",{staticClass:"col-4"},[t("el-form-item",[t("label",{attrs:{for:"status"}},[e._v("Trạng thái")]),t("el-select",{staticClass:"form-control non-border",attrs:{id:"status",placeholder:"---Tất cả---"},model:{value:e.customerFilter.status,callback:function(t){e.$set(e.customerFilter,"status",t)},expression:"customerFilter.status"}},[t("el-option",{attrs:{label:"Đang thực hiện",value:"1"}}),t("el-option",{attrs:{label:"Khóa",value:"2"}})],1)],1)],1)]),t("div",{staticClass:"row"},[t("div",{staticClass:"col-6"},[t("button",{staticClass:"btn btn-outline-primary float-end"},[e._v("Tìm kiếm")])]),t("div",{staticClass:"col-6"})])])],1),t("div",{staticClass:"mt-5"},[t("div",{staticClass:"row mb-2"},[t("div",{staticClass:"col-8"}),t("div",{staticClass:"col-4"},[t("button",{staticClass:"btn btn-sm btn-outline-primary float-end"},[e._v("Export")]),t("button",{staticClass:"btn btn-sm btn-outline-warning float-end me-3",on:{click:e.handleImport}},[e._v("Import")])])]),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],key:e.tableKey,staticStyle:{width:"100%"},attrs:{data:e.list,"header-cell-style":{background:"#5E2E864D",color:"#000000",borderRightStyle:"none"},"cell-style":{borderRightStyle:"none"},border:"",fit:"","highlight-current-row":""}},[t("el-table-column",{attrs:{label:"Thao tác",width:"100px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(n){var i=n.row,o=n.$index;return[e.enabled?t("span",{on:{click:function(t){return e.handleDelete(i,o)}}},[t("i",{staticClass:"el-icon-delete",staticStyle:{color:"#FF485A"}})]):e._e()]}}])}),t("el-table-column",{attrs:{label:"Mã số thuế"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[e._v(" "+e._s(n.taxCode)+" ")]}}])}),t("el-table-column",{attrs:{label:"Mã số ĐKKD"},scopedSlots:e._u([{key:"default",fn:function(n){var i=n.row;return[t("span",[e._v(e._s(i.businessCode))])]}}])}),t("el-table-column",{attrs:{label:"Mã CIF (FCC)"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[e._v(" "+e._s(n.fccCode)+" ")]}}])}),t("el-table-column",{attrs:{label:"Mã CIF (Tạm)"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[e._v(" "+e._s(n.cifDraftCode)+" ")]}}])}),t("el-table-column",{attrs:{label:"Tên ĐVQL"},scopedSlots:e._u([{key:"default",fn:function(t){var n,i=t.row;return[e._v(" "+e._s(null===(n=i.unit)||void 0===n?void 0:n.name)+" ")]}}])}),t("el-table-column",{attrs:{label:"Mã ĐVQL"},scopedSlots:e._u([{key:"default",fn:function(t){var n,i=t.row;return[e._v(" "+e._s(null===(n=i.unit)||void 0===n?void 0:n.code)+" ")]}}])}),t("el-table-column",{attrs:{label:"Trạng thái"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[e._v(" "+e._s(0===n.status?"Chưa xếp hạng":"Đã xếp hạng")+" ")]}}])})],1),t("div",{staticClass:"row"},[t("div",{staticClass:"col-12"},[t("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],staticClass:"float-end mt-0",attrs:{total:e.total,page:e.listQuery.page,limit:e.listQuery.limit,layout:"prev, pager, next, sizes"},on:{"update:page":function(t){return e.$set(e.listQuery,"page",t)},"update:limit":function(t){return e.$set(e.listQuery,"limit",t)},pagination:e.getList}})],1)]),t("div",{staticClass:"row mt-3 float-end"},[t("div",{staticClass:"col-12"},[t("button",{staticClass:"btn btn-outline-secondary me-3",on:{click:e.closePage}},[e._v("Đóng")]),e.enabled?t("button",{staticClass:"btn btn-outline-warning"},[e._v(e._s(e.edited?"Cập nhật":"Đồng ý"))]):e._e()])])],1),t("delete-customer",{attrs:{"dialog-delete-visible":e.dialogDeleteVisible},on:{"update:dialogDeleteVisible":function(t){e.dialogDeleteVisible=t},"update:dialog-delete-visible":function(t){e.dialogDeleteVisible=t}}}),t("import-customer",{attrs:{"dialog-import-visible":e.dialogImportVisible},on:{"update:dialogImportVisible":function(t){e.dialogImportVisible=t},"update:dialog-import-visible":function(t){e.dialogImportVisible=t}}})],1)},o=[],s=n("6724"),a=n("333d"),l=function(){var e=this,t=e._self._c;return t("el-dialog",{attrs:{title:"Xóa người phê duyệt",visible:e.dialogDeleteVisible,"before-close":e.closeDialog,center:!0,width:"30%","show-close":!1},on:{"update:visible":function(t){e.dialogDeleteVisible=t},"update:beforeClose":function(t){e.closeDialog=t},"update:before-close":function(t){e.closeDialog=t}}},[t("h3",{staticClass:"title mt-3",attrs:{slot:"title"},slot:"title"},[t("strong",[e._v("Xóa khách hàng")])]),t("div",{staticClass:"text-center"},[t("h5",{staticClass:"text-danger"},[e._v("Bạn có chắc muốn xóa khách hàng?")])]),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("button",{staticClass:"btn btn-outline-secondary me-3",on:{click:e.closeDialog}},[e._v("Đóng")]),t("button",{staticClass:"btn btn-outline-warning",on:{click:e.deleteData}},[e._v("Đồng ý")])])])},r=[],c={name:"DeleteCustomer",props:{dialogDeleteVisible:{type:Boolean,default:!1}},methods:{closeDialog:function(){this.$emit("update:dialogDeleteVisible",!1)},deleteData:function(){}}},u=c,d=(n("c264"),n("2877")),m=Object(d["a"])(u,l,r,!1,null,"26487478",null),h=m.exports,f=function(){var e=this,t=e._self._c;return t("el-dialog",{attrs:{title:"Tải file danh sách người phê duyệt",visible:e.dialogImportVisible,width:"30%","before-close":e.closeDialog},on:{"update:visible":function(t){e.dialogImportVisible=t},"update:beforeClose":function(t){e.closeDialog=t},"update:before-close":function(t){e.closeDialog=t}}},[t("h3",{staticClass:"title mt-3",attrs:{slot:"title"},slot:"title"},[t("strong",[e._v("Tải file danh sách khách hàng ")])]),t("el-form",{ref:"dataForm",attrs:{"label-position":"left","label-width":"70px"}},[t("label",{attrs:{for:"excel"}},[e._v("Chọn file danh sách khách hàng chấm điểm "),t("span",{staticClass:"text-danger"},[e._v("*")])]),t("input",{ref:"excel-upload-input",staticClass:"form-control custom-file-input",attrs:{type:"file",accept:".xlsx, .xls"}})]),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("button",{staticClass:"btn btn-outline-secondary me-3",on:{click:e.closeDialog}},[e._v("Đóng")]),t("button",{staticClass:"btn btn-outline-warning",on:{click:e.importData}},[e._v("Đồng ý")])])],1)},p=[],b=(n("d3b7"),n("ac1f"),n("00b4"),n("1146")),v=n.n(b),g={name:"ImportCustomer",props:{dialogImportVisible:{type:Boolean,default:!1}},data:function(){return{loading:!1,excelData:{header:null,results:null}}},methods:{closeDialog:function(){this.$emit("update:dialogImportVisible",!1)},importData:function(){this.$refs["dataForm"].validate((function(e){}))},beforeUpload:function(e){var t=e.size/1024/1024<1;return!!t||(this.$message({message:"Please do not upload files larger than 1m in size.",type:"warning"}),!1)},handleClick:function(e){var t=e.target.files,n=t[0];n&&this.upload(n)},upload:function(e){this.$refs["excel-upload-input"].value=null;var t=this.beforeUpload(e);t&&this.readerData(e)},readerData:function(e){var t=this;return this.loading=!0,new Promise((function(n,i){var o=new FileReader;o.onload=function(e){var i=e.target.result,o=v.a.read(i,{type:"array"}),s=o.SheetNames[0],a=o.Sheets[s],l=v.a.utils.sheet_to_json(a);console.log(l),t.loading=!1,n()},o.readAsArrayBuffer(e)}))},isExcel:function(e){return/\.(xlsx|xls|csv)$/.test(e.name)}}},C=g,y=(n("759c"),Object(d["a"])(C,f,p,!1,null,"4949f176",null)),_=y.exports,k={name:"ListCustomer",components:{ImportCustomer:_,DeleteCustomer:h,Pagination:a["a"]},directives:{waves:s["a"]},props:{enabled:{type:Boolean,default:!0},edited:{type:Boolean,default:!1}},data:function(){return{tableKey:0,units:null,list:null,total:0,listLoading:!0,listQuery:{page:1,limit:20,importance:void 0,title:void 0,type:void 0,sort:"+id"},dialogImportVisible:!1,dialogDeleteVisible:!1,customerFilter:{taxCode:"",businessCode:"",fccCode:"",cifDraftCode:"",unit:null,unitCode:"",status:null}}},created:function(){this.getList(),this.getUnits()},methods:{handleDelete:function(e,t){this.dialogDeleteVisible=!0,this.temp=Object.assign({},e)},handleImport:function(){this.dialogImportVisible=!0},getUnits:function(){this.units=[{id:1,name:"Đông Đô"},{id:2,name:"Đông Đô 2"}]},closePage:function(){this.$router.back()},getList:function(){var e=this;this.listLoading=!0,this.list=[{taxCode:"0123456789",businessCode:"0289456123",fccCode:"A1234561",cifDraftCode:"A1234561",unit:{id:1,name:"Chi nhánh Đông Đô",code:"DD001"},status:1},{taxCode:"0223456789",businessCode:"0389456123",fccCode:"A1234562",cifDraftCode:"A1234562",unit:{id:1,name:"Chi nhánh Đông Đô",code:"DD001"},status:0},{taxCode:"0323456789",businessCode:"0489456123",fccCode:"A1234563",cifDraftCode:"A1234563",unit:{id:1,name:"Chi nhánh Đông Đô",code:"DD001"},status:0},{taxCode:"0423456789",businessCode:"0589456123",fccCode:"A1234564",cifDraftCode:"A1234564",unit:{id:1,name:"Chi nhánh Đông Đô",code:"DD001"},status:0}],this.total=this.list.length,setTimeout((function(){e.listLoading=!1}),1500)}}},x=k,w=(n("f538"),Object(d["a"])(x,i,o,!1,null,"39567326",null));t["a"]=w.exports},"8d41":function(e,t,n){},c264:function(e,t,n){"use strict";n("fb22")},cad7:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e._self._c;return t("div",{staticClass:"app-container"},[t("ValidationObserver",{ref:"observer"},[t("general-info",{attrs:{"score-period":e.scorePeriod}}),t("list-customer")],1)],1)},o=[],s=n("5530"),a=n("7bb1"),l=n("8479"),r=n("59ec"),c={name:"EditScorePeriod",components:{GeneralInfo:r["a"],ListCustomer:l["a"],ValidationObserver:a["a"]},data:function(){return{scorePeriod:{name:"",startDate:"",endDate:"",status:null}}},created:function(){this.scorePeriod=Object(s["a"])({},this.$route.params.scorePeriod)}},u=c,d=n("2877"),m=Object(d["a"])(u,i,o,!1,null,"ec31098c",null);t["default"]=m.exports},f538:function(e,t,n){"use strict";n("0365")},fb22:function(e,t,n){},fe3b:function(e,t,n){}}]);