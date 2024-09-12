"use strict";(self["webpackChunkvue2"]=self["webpackChunkvue2"]||[]).push([[186],{2186:function(e,o,t){t.r(o),t.d(o,{default:function(){return c}});var r=function(){var e=this,o=e._self._c;return o("div",[o("div",[o("el-button",{attrs:{type:"primary",round:""},on:{click:function(o){e.dialogFormVisible=!0}}},[e._v("添加宿舍信息")]),o("el-button",{staticStyle:{float:"right"},attrs:{type:"primary",round:""},on:{click:function(o){return e.quit()}}},[e._v("退出登录")]),o("el-dialog",{attrs:{title:"填写信息",visible:e.dialogFormVisible},on:{"update:visible":function(o){e.dialogFormVisible=o}}},[o("el-form",{ref:"form",attrs:{model:e.form}},[o("el-form-item",{attrs:{label:"楼栋","label-width":e.formLabelWidth,prop:"unit",rules:[{required:!0,message:"不能为空"}]}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.unit,callback:function(o){e.$set(e.form,"unit",o)},expression:"form.unit"}})],1),o("el-form-item",{attrs:{label:"宿舍号","label-width":e.formLabelWidth,prop:"roomid",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.roomid,callback:function(o){e.$set(e.form,"roomid",e._n(o))},expression:"form.roomid"}})],1),o("el-form-item",{attrs:{label:"楼层","label-width":e.formLabelWidth,prop:"floor",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.floor,callback:function(o){e.$set(e.form,"floor",e._n(o))},expression:"form.floor"}})],1),o("el-form-item",{attrs:{label:"床位数","label-width":e.formLabelWidth,prop:"bedcount",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.bedcount,callback:function(o){e.$set(e.form,"bedcount",e._n(o))},expression:"form.bedcount"}})],1),o("el-form-item",{attrs:{label:"住宿费","label-width":e.formLabelWidth,prop:"money",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.money,callback:function(o){e.$set(e.form,"money",e._n(o))},expression:"form.money"}})],1)],1),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{attrs:{round:""},on:{click:function(o){return e.resetForm("form")}}},[e._v("重置")]),o("el-button",{attrs:{round:""},on:{click:function(o){e.dialogFormVisible=!1}}},[e._v("取消")]),o("el-button",{attrs:{type:"primary",round:""},on:{click:e.form_Submit}},[e._v("确定")])],1)],1)],1),o("br"),o("el-form",{ref:"searchForm",staticClass:"demo-form-inline",attrs:{inline:!0,model:e.searchForm}},[o("el-form-item",{attrs:{label:"楼栋",prop:"unit"}},[o("el-input",{attrs:{placeholder:"请输入楼栋"},model:{value:e.searchForm.unit,callback:function(o){e.$set(e.searchForm,"unit",o)},expression:"searchForm.unit"}})],1),o("el-form-item",{attrs:{label:"宿舍号",prop:"roomid"}},[o("el-input",{attrs:{placeholder:"请输入宿舍号"},model:{value:e.searchForm.roomid,callback:function(o){e.$set(e.searchForm,"roomid",o)},expression:"searchForm.roomid"}})],1),o("el-form-item",{attrs:{label:"楼层",prop:"floor"}},[o("el-input",{attrs:{placeholder:"请输入楼层"},model:{value:e.searchForm.floor,callback:function(o){e.$set(e.searchForm,"floor",o)},expression:"searchForm.floor"}})],1),o("el-form-item",{attrs:{label:"床位数",prop:"bedcount"}},[o("el-input",{attrs:{placeholder:"请输入床位数"},model:{value:e.searchForm.bedcount,callback:function(o){e.$set(e.searchForm,"bedcount",o)},expression:"searchForm.bedcount"}})],1),o("el-form-item",{attrs:{label:"住宿费",prop:"money"}},[o("el-input",{attrs:{placeholder:"请输入住宿费"},model:{value:e.searchForm.money,callback:function(o){e.$set(e.searchForm,"money",o)},expression:"searchForm.money"}})],1),o("el-form-item",[o("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.search_Submit}},[e._v("根据筛选条件查询")]),o("el-button",{on:{click:function(o){e.resetForm("searchForm"),e.search_Submit()}}},[e._v("重置")])],1)],1),o("el-button",{attrs:{type:"danger",icon:"el-icon-delete"},on:{click:function(o){return e.delete_choose()}}},[e._v("批量删除已勾选信息")]),o("el-button",{on:{click:function(o){return e.cancel_choose()}}},[e._v("取消")]),o("br"),o("br"),o("div",[o("el-table",{ref:"multiple_Table",staticStyle:{width:"100%"},attrs:{data:e.roomData,border:""},on:{"selection-change":e.handleSelectionChange}},[o("el-table-column",{attrs:{type:"selection",width:"40"}}),o("el-table-column",{attrs:{prop:"roommsg",label:"楼栋-宿舍号",sortable:"",width:"130"}}),o("el-table-column",{attrs:{prop:"unit",label:"楼栋",sortable:"",width:"100"}}),o("el-table-column",{attrs:{prop:"roomid",label:"宿舍号",sortable:"",width:"100"}}),o("el-table-column",{attrs:{prop:"floor",label:"楼层",sortable:"",width:"80"}}),o("el-table-column",{attrs:{prop:"bedcount",label:"床位数",width:"100"}}),o("el-table-column",{attrs:{prop:"money",label:"住宿费",sortable:"",width:"100"}}),o("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(o){e.EditFormVisible=!0,e.editform.unit=t.row.unit,e.editform.roomid=t.row.roomid,e.editform.floor=t.row.floor,e.editform.bedcount=t.row.bedcount,e.editform.money=t.row.money,e.editform.roommsg=t.row.roommsg}}},[e._v("编辑")]),o("el-dialog",{attrs:{title:"填写信息",visible:e.EditFormVisible},on:{"update:visible":function(o){e.EditFormVisible=o}}},[o("el-form",{ref:"editform",attrs:{model:e.editform}},[o("el-form-item",{attrs:{label:"楼栋","label-width":e.formLabelWidth,prop:"unit",rules:[{required:!0,message:"不能为空"}]}},[o("el-input",{attrs:{autocomplete:"off",disabled:!0},model:{value:e.editform.unit,callback:function(o){e.$set(e.editform,"unit",o)},expression:"editform.unit"}})],1),o("el-form-item",{attrs:{label:"宿舍号","label-width":e.formLabelWidth,prop:"roomid",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off",disabled:!0},model:{value:e.editform.roomid,callback:function(o){e.$set(e.editform,"roomid",e._n(o))},expression:"editform.roomid"}})],1),o("el-form-item",{attrs:{label:"楼层","label-width":e.formLabelWidth,prop:"floor",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off",disabled:!0},model:{value:e.editform.floor,callback:function(o){e.$set(e.editform,"floor",e._n(o))},expression:"editform.floor"}})],1),o("el-form-item",{attrs:{label:"床位数","label-width":e.formLabelWidth,prop:"bedcount",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:e.editform.bedcount,callback:function(o){e.$set(e.editform,"bedcount",e._n(o))},expression:"editform.bedcount"}})],1),o("el-form-item",{attrs:{label:"住宿费","label-width":e.formLabelWidth,prop:"money",rules:[{required:!0,message:"不能为空"},{type:"number",message:"必须为数字"}]}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:e.editform.money,callback:function(o){e.$set(e.editform,"money",e._n(o))},expression:"editform.money"}})],1)],1),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{attrs:{round:""},on:{click:function(o){return e.resetForm("editform")}}},[e._v("重置")]),o("el-button",{attrs:{round:""},on:{click:function(o){e.EditFormVisible=!1}}},[e._v("取消")]),o("el-button",{attrs:{type:"primary",round:""},on:{click:function(o){return e.edit_Submit()}}},[e._v("确定")])],1)],1),e._v("   "),o("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(o){return e.delete_ids(t.row.roommsg)}}},[e._v("删除")])]}}])})],1)],1)],1)},l=[],s=(t(7658),t(9555)),i={data(){return{roomData:[],chooseData:[],form:{unit:"",roomid:"",floor:"",bedcount:"",money:"",roommsg:""},editform:{unit:"",roomid:"",floor:"",bedcount:"",money:"",roommsg:""},searchForm:{unit:"",roomid:"",floor:"",bedcount:"",money:"",roommsg:""},dialogFormVisible:!1,EditFormVisible:!1,formLabelWidth:"120px"}},methods:{form_Submit(){""==this.form.unit||"unll"==this.form.unit||""==this.form.roomid||""==this.form.floor||""==this.form.bedcount||""==this.form.money||null==this.form.roomid||null==this.form.floor||null==this.form.bedcount||null==this.form.money?this.$message.error("提交失败！信息不可留空！"):s.Z.post("/room",this.form).then((e=>{"room repeat"==e.data.msg?this.$message.error("提交失败！该宿舍已存在！"):(this.$message({message:"提交成功！请刷新网页查看最新数据",type:"success"}),console.log(e.data))})).catch((e=>{this.$message.error("提交失败！该宿舍已存在！"),console.error(e)}))},search_Submit(){s.Z.post("/room/search",this.searchForm).then((e=>{console.log(e.data.data),this.roomData=e.data.data,this.$message({message:"查询成功！共"+this.roomData.length+"条数据",type:"success"})})).catch((e=>{console.error(e)}))},edit_Submit(){""==this.editform.floor||""==this.editform.bedcount||""==this.editform.money||null==this.editform.floor||null==this.editform.bedcount||null==this.editform.money?this.$message.error("提交失败！信息不可留空！"):s.Z.post("/room/update",this.editform).then((e=>{this.$message({message:"修改成功！请刷新网页查看最新数据",type:"success"}),console.log(e.data.data)})).catch((e=>{this.$message.error("提交失败！请检查信息后重试！"),console.error(e)}))},delete_ids(e){this.$confirm("此操作将永久删除指定宿舍所有信息并清空该宿舍内学生的住宿信息 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{s.Z.delete("/room/ids/"+e).then((e=>{console.log(e.data)})).catch((e=>{console.error(e)})),this.$message({type:"success",message:"删除成功!请刷新网页查看最新数据"})})).catch((()=>{this.$message({type:"info",message:"已取消删除"})}))},resetForm(e){this.$refs[e].resetFields()},delete_choose(){for(var e=new Array,o=0;o<this.chooseData.length;o++)e[o]=this.chooseData[o].roommsg;this.delete_ids(e)},cancel_choose(e){e?e.forEach((e=>{this.$refs.multiple_Table.toggleRowSelection(e)})):this.$refs.multiple_Table.clearSelection()},handleSelectionChange(e){this.chooseData=e},quit(){localStorage.clear(),this.$router.push("/login"),this.$message.success("已退出登录")}},mounted(){s.Z.post("/room/search",this.searchForm).then((e=>{console.log(e.data.data),"NOT_LOGIN"==e.data.msg?(this.$router.push("/login"),this.$message.error("登录信息过期，请重新登录")):this.roomData=e.data.data})).catch((e=>{console.error(e)}))}},a=i,m=t(1001),n=(0,m.Z)(a,r,l,!1,null,null,null),c=n.exports}}]);
//# sourceMappingURL=186.a0a998bd.js.map