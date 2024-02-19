<template>
  <el-container class="layout-container-demo" style="min-height:100vh;">
    <el-aside :width="sideWith + 'px'">
      <el-scrollbar>
        <el-menu :default-openeds="['1', '3']" style="min-height:100vh; overflow-x: hidden"
                  background-color="rgb(48,65,86)"
                  text-color="#fff"
                  active-text-color="#ffd04b" 
                  :collapse-transition="false"
                  :collapse="isCollapse"
        >
        <div style="height:60px; line-height: 60px; text-align: center;">
          <img src="../assets/logo.svg" alt="" style="width: 20px; position: relative; top: 3px; margin-right: 5px;" >
          <b style="color: white;" v-show="logoTextShow">车间生产采集</b>
        </div>
          <el-sub-menu index="1">
            <template #title> 
              <el-icon><icon-menu /></el-icon>
              <span>首页</span>
            </template>
              <el-menu-item index="1-1">账号管理</el-menu-item>
              <el-menu-item index="1-2">员工管理</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="2">  
            <template #title>
              <el-icon><Tools /></el-icon>
              <span>生产流程</span>
            </template>
              <el-menu-item index="2-1">生产规划</el-menu-item>
              <el-menu-item index="2-2">物料调拨</el-menu-item>
              <el-menu-item index="2-3">加工管理</el-menu-item>
              <el-menu-item index="2-4">质量检测</el-menu-item>
              <el-menu-item index="2-5">入库管理</el-menu-item>
              <el-menu-item index="2-6">发货管理</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><Box /></el-icon>
              <span>物料管理</span>
            </template>
              <el-menu-item index="3-1">物料入库</el-menu-item>
              <el-menu-item index="3-2">物料库存</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>   

    <el-container>
      <el-header style=" font-size: 16px; border-bottom:1px solid #ccc;line-height: 60px;display:flex ">
        <div style="flex: 1; font-size: 20px; right: 2px;">
          <el-icon :class="collapseBtnClass" style="cursor: pointer" @click="collapse">
          <Expand /> 
        </el-icon> 
        </div> 
        <div class="toolbar" style="width: 30px">
          <el-dropdown>
            <el-icon style="margin-right: 5px; margin-top: 1px"><setting/>
            </el-icon>         
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>退出</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span>Tom</span>
        </div>
      </el-header>

      <el-main>
        <div style="padding:5px 0">
          <el-input v-model="input1" style="width: 200px" :suffix-icon="Document" placeholder="请输入订单"></el-input>
          <el-input v-model="input2" style="width: 200px" :suffix-icon="User" placeholder="请输入名称" class="ml-5"></el-input>
          <el-input v-model="input3" style="width: 200px" :suffix-icon="Clock" placeholder="请输入计划完成时间" class="ml-5"></el-input>
          <el-button class="ml-5" type="primary" >搜索</el-button>
        </div> 
        <div style="padding:5px 0">
          <el-button type="primary" @click="add">新增<el-icon ><CirclePlus /></el-icon></el-button>
          <el-button type="danger">批量删除<el-icon><Delete /></el-icon></el-button>
          <el-button type="primary">导入<el-icon><Bottom /></el-icon></el-button>
          <el-button type="primary">导出<el-icon><Top /></el-icon></el-button>
        </div>
       
        <el-scrollbar> 
          <el-table :data="tableData">
            <el-table-column prop="PlannedOrder_number" label="计划订单" width="150" /> 
            <el-table-column prop="Product_name" label="产品名称" width="120" />
            <el-table-column prop="Product_number" label="产品数量" width="80"/>
            <el-table-column prop="Material_name" label="物料名称" width="80"/>
            <el-table-column prop="Material_number" label="物料数量" width="80"/>
            <el-table-column prop="Work_number " label="工作人数" width="80"/>
            <el-table-column prop="Device_number" label="所需设备名称" width="120"/>
            <el-table-column prop="Planned_time" label="计划完成时间" width="120"/>
            <el-table-column>
              <template #default="{ item }">
                <el-button type="success">编辑<el-icon><EditPen /></el-icon></el-button>
                <el-button type="danger">删除<el-icon><Delete /></el-icon></el-button>
              </template>
            
            </el-table-column>
          </el-table> 
          <div style="padding: 5px 0">
            <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="10"
             layout="total, sizes, prev, pager, next, jumper"
            :total="400">
            </el-pagination>

          <el-dialog
            v-model="dialogVisible" title="Tips" width="30%">
          <el-form :model="form" label-width="120px">
            <el-form-item label="计划订单">
            <el-input v-model="form.plannedOrder_number" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="产品名称">
            <el-input v-model="form.Product_name" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="产品数量">
            <el-input v-model="form.Product_number" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="物料名称">
            <el-input v-model="form.Material_name" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="物料数量">
            <el-input v-model="form.Material_number" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="工作人数">
            <el-input v-model="form.Work_number" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="所需设备名称">
            <el-input v-model="form.Device_number" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="划完成时间">
            <el-input v-model="form.Planned_time" style="width: 80%"/>
            </el-form-item>
          </el-form>
          <template #footer>
          <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save"> 确定</el-button>
          </span>
          </template>
          </el-dialog>

          </div>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template> 


<script setup>
import { ref } from 'vue'
import { Menu as IconMenu,  Setting, Tools ,Box, Expand,Search ,Clock,Document,User,CirclePlus,Delete,Bottom,Top,EditPen} from '@element-plus/icons-vue'
import { sliderButtonEmits } from 'element-plus/es/components/slider/src/button';
import request from '../../utils/request';
const tableData = ref(Array.from({ length: 10 }))
const isCollapse = ref(false)
const sideWith =ref(200)
const logoTextShow =ref(true)
const dialogVisible =ref(false)
const form =ref({
  plannedOrder_number:"",
  Product_name:"",
  Product_number:"",
  Material_name:"",
  Material_number:"",
  Work_number:"",
  Device_number:"",
  Planned_time:""
})
const input1 = ref('')  
const input2 = ref('')
const input3 = ref('')
const collapse = () => {
      isCollapse.value = !isCollapse.value;
      if (isCollapse.value){
        sideWith.value = 60
        logoTextShow.value=false
      }else{
        sideWith.value = 200
        logoTextShow.value=true
      }  
    }
const add = () => {
    dialogVisible.value = true
    form.value={}
  }
const save =() =>{
    request.post("http://localhost:9090/user", form.value).then(res => {
      console.log(res);
    }) 
  }
</script> 
<style>
.layout-container-demo .el-header {
  position: relative;
  background-color:white;
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background:rgb(48,65,86);
}
.layout-container-demo .el-menu {   
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 10px
 }
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>