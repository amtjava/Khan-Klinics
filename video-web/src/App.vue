<script setup>
import { Search } from '@element-plus/icons-vue'
</script>

<template>
  <div>
    <div class="nav-bar">
      <div class="nav-project">Khanclinic</div>
      <el-input class="w-50 m-2 nav-input" placeholder="Please Input" :prefix-icon="Search" />
      <el-button class="nav-login" plain>Log in</el-button>
      <el-button class="nav-signup" color="#000000" style="color: white">Sign up</el-button>
    </div>
  </div>
  <div class="container">
    <router-view />
  </div>
</template>

<style lang="less" scoped>
.nav-bar {
  top: 0;
  left: 0;
  position: fixed;
  border-bottom: 1px solid #dcdfe6;
  line-height: 50px;
  width: 100%;
  background-color: white;
  z-index: 100;
  box-shadow: 0 5px 10px -5px grey;
  display: -webkit-flex;
  display: flex;
  align-items: center;

  .nav-project {
    margin-left: 32px;
    margin-right: 20px;
  }

  .nav-input {
    flex: 1;
  }

  .nav-login {
    margin-left: 20px;
  }

  .nav-signup {
    margin-right: 12px;
  }
}

.container {
  margin-top: 50px;
}
</style>
