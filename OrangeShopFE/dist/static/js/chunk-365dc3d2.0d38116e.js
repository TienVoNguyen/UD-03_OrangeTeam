(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-365dc3d2"],{"1d3d":function(t,e,s){},7066:function(t,e,s){"use strict";s("1d3d")},"8a81":function(t,e,s){},ecac:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t._self._c;return e("div",{staticClass:"app-container"},[t.user?e("div",[e("el-row",{attrs:{gutter:20}},[e("el-col",{attrs:{span:6,xs:24}},[e("user-card",{attrs:{user:t.user}})],1),e("el-col",{attrs:{span:18,xs:24}},[e("el-card",[e("el-tabs",{model:{value:t.activeTab,callback:function(e){t.activeTab=e},expression:"activeTab"}},[e("el-tab-pane",{attrs:{label:"Activity",name:"activity"}},[e("activity")],1),e("el-tab-pane",{attrs:{label:"Timeline",name:"timeline"}},[e("timeline")],1),e("el-tab-pane",{attrs:{label:"Account",name:"account"}},[e("account",{attrs:{user:t.user}})],1)],1)],1)],1)],1)],1):t._e()])},i=[],l=s("5530"),r=(s("b0c0"),s("a15b"),s("2f62")),c=function(){var t=this,e=t._self._c;return e("el-card",{staticStyle:{"margin-bottom":"20px"}},[e("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e("span",[t._v("About me")])]),e("div",{staticClass:"user-profile"},[e("div",{staticClass:"box-center"},[e("pan-thumb",{attrs:{image:t.user.avatar,height:"100px",width:"100px",hoverable:!1}},[e("div",[t._v("Hello")]),t._v(" "+t._s(t.user.role)+" ")])],1),e("div",{staticClass:"box-center"},[e("div",{staticClass:"user-name text-center"},[t._v(t._s(t.user.name))]),e("div",{staticClass:"user-role text-center text-muted"},[t._v(t._s(t._f("uppercaseFirst")(t.user.role)))])])]),e("div",{staticClass:"user-bio"},[e("div",{staticClass:"user-education user-bio-section"},[e("div",{staticClass:"user-bio-section-header"},[e("svg-icon",{attrs:{"icon-class":"education"}}),e("span",[t._v("Education")])],1),e("div",{staticClass:"user-bio-section-body"},[e("div",{staticClass:"text-muted"},[t._v(" JS in Computer Science from the University of Technology ")])])]),e("div",{staticClass:"user-skills user-bio-section"},[e("div",{staticClass:"user-bio-section-header"},[e("svg-icon",{attrs:{"icon-class":"skill"}}),e("span",[t._v("Skills")])],1),e("div",{staticClass:"user-bio-section-body"},[e("div",{staticClass:"progress-item"},[e("span",[t._v("Vue")]),e("el-progress",{attrs:{percentage:70}})],1),e("div",{staticClass:"progress-item"},[e("span",[t._v("JavaScript")]),e("el-progress",{attrs:{percentage:18}})],1),e("div",{staticClass:"progress-item"},[e("span",[t._v("Css")]),e("el-progress",{attrs:{percentage:12}})],1),e("div",{staticClass:"progress-item"},[e("span",[t._v("ESLint")]),e("el-progress",{attrs:{percentage:100,status:"success"}})],1)])])])])},n=[],o=s("3cbc"),u={components:{PanThumb:o["a"]},props:{user:{type:Object,default:function(){return{name:"",email:"",avatar:"",role:""}}}}},m=u,p=(s("7066"),s("2877")),d=Object(p["a"])(m,c,n,!1,null,"14daffcb",null),v=d.exports,f=function(){var t=this,e=t._self._c;return e("div",{staticClass:"user-activity"},[e("div",{staticClass:"post"},[e("div",{staticClass:"user-block"},[e("img",{staticClass:"img-circle",attrs:{src:"https://wpimg.wallstcn.com/57ed425a-c71e-4201-9428-68760c0537c4.jpg"+t.avatarPrefix}}),e("span",{staticClass:"username text-muted"},[t._v("Iron Man")]),e("span",{staticClass:"description"},[t._v("Shared publicly - 7:30 PM today")])]),e("p",[t._v(" Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans. ")]),e("ul",{staticClass:"list-inline"},[t._m(0),e("li",[e("span",{staticClass:"link-black text-sm"},[e("svg-icon",{attrs:{"icon-class":"like"}}),t._v(" Like ")],1)])])]),e("div",{staticClass:"post"},[e("div",{staticClass:"user-block"},[e("img",{staticClass:"img-circle",attrs:{src:"https://wpimg.wallstcn.com/9e2a5d0a-bd5b-457f-ac8e-86554616c87b.jpg"+t.avatarPrefix}}),e("span",{staticClass:"username text-muted"},[t._v("Captain American")]),e("span",{staticClass:"description"},[t._v("Sent you a message - yesterday")])]),e("p",[t._v(" Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans. ")]),e("ul",{staticClass:"list-inline"},[t._m(1),e("li",[e("span",{staticClass:"link-black text-sm"},[e("svg-icon",{attrs:{"icon-class":"like"}}),t._v(" Like ")],1)])])]),e("div",{staticClass:"post"},[e("div",{staticClass:"user-block"},[e("img",{staticClass:"img-circle",attrs:{src:"https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg"+t.avatarPrefix}}),e("span",{staticClass:"username"},[t._v("Spider Man")]),e("span",{staticClass:"description"},[t._v("Posted 4 photos - 2 days ago")])]),e("div",{staticClass:"user-images"},[e("el-carousel",{attrs:{interval:6e3,type:"card",height:"220px"}},t._l(t.carouselImages,(function(s){return e("el-carousel-item",{key:s},[e("img",{staticClass:"image",attrs:{src:s+t.carouselPrefix}})])})),1)],1),e("ul",{staticClass:"list-inline"},[t._m(2),e("li",[e("span",{staticClass:"link-black text-sm"},[e("svg-icon",{attrs:{"icon-class":"like"}}),t._v(" Like")],1)])])])])},b=[function(){var t=this,e=t._self._c;return e("li",[e("span",{staticClass:"link-black text-sm"},[e("i",{staticClass:"el-icon-share"}),t._v(" Share ")])])},function(){var t=this,e=t._self._c;return e("li",[e("span",{staticClass:"link-black text-sm"},[e("i",{staticClass:"el-icon-share"}),t._v(" Share ")])])},function(){var t=this,e=t._self._c;return e("li",[e("span",{staticClass:"link-black text-sm"},[e("i",{staticClass:"el-icon-share"}),t._v(" Share")])])}],h="?imageView2/1/w/80/h/80",g="?imageView2/2/h/440",C={data:function(){return{carouselImages:["https://wpimg.wallstcn.com/9679ffb0-9e0b-4451-9916-e21992218054.jpg","https://wpimg.wallstcn.com/bcce3734-0837-4b9f-9261-351ef384f75a.jpg","https://wpimg.wallstcn.com/d1d7b033-d75e-4cd6-ae39-fcd5f1c0a7c5.jpg","https://wpimg.wallstcn.com/50530061-851b-4ca5-9dc5-2fead928a939.jpg"],avatarPrefix:h,carouselPrefix:g}}},_=C,x=(s("fe22"),Object(p["a"])(_,f,b,!1,null,"1066d76c",null)),k=x.exports,y=function(){var t=this,e=t._self._c;return e("div",{staticClass:"block"},[e("el-timeline",t._l(t.timeline,(function(s,a){return e("el-timeline-item",{key:a,attrs:{timestamp:s.timestamp,placement:"top"}},[e("el-card",[e("h4",[t._v(t._s(s.title))]),e("p",[t._v(t._s(s.content))])])],1)})),1)],1)},w=[],j={data:function(){return{timeline:[{timestamp:"2019/4/20",title:"Update Github template",content:"PanJiaChen committed 2019/4/20 20:46"},{timestamp:"2019/4/21",title:"Update Github template",content:"PanJiaChen committed 2019/4/21 20:46"},{timestamp:"2019/4/22",title:"Build Template",content:"PanJiaChen committed 2019/4/22 20:46"},{timestamp:"2019/4/23",title:"Release New Version",content:"PanJiaChen committed 2019/4/23 20:46"}]}}},S=j,P=Object(p["a"])(S,y,w,!1,null,null,null),O=P.exports,T=(s("498a"),function(){var t=this,e=t._self._c;return e("el-form",[e("el-form-item",{attrs:{label:"Name"}},[e("el-input",{model:{value:t.user.name,callback:function(e){t.$set(t.user,"name","string"===typeof e?e.trim():e)},expression:"user.name"}})],1),e("el-form-item",{attrs:{label:"Email"}},[e("el-input",{model:{value:t.user.email,callback:function(e){t.$set(t.user,"email","string"===typeof e?e.trim():e)},expression:"user.email"}})],1),e("el-form-item",[e("el-button",{attrs:{type:"primary"},on:{click:t.submit}},[t._v("Update")])],1)],1)}),J=[],U={props:{user:{type:Object,default:function(){return{name:"",email:""}}}},methods:{submit:function(){this.$message({message:"User information has been updated successfully",type:"success",duration:5e3})}}},A=U,L=Object(p["a"])(A,T,J,!1,null,null,null),V=L.exports,E={name:"Profile",components:{UserCard:v,Activity:k,Timeline:O,Account:V},data:function(){return{user:{},activeTab:"activity"}},computed:Object(l["a"])({},Object(r["b"])(["name","avatar","roles"])),created:function(){this.getUser()},methods:{getUser:function(){this.user={name:this.name,role:this.roles.join(" | "),email:"admin@test.com",avatar:this.avatar}}}},I=E,M=Object(p["a"])(I,a,i,!1,null,null,null);e["default"]=M.exports},fe22:function(t,e,s){"use strict";s("8a81")}}]);