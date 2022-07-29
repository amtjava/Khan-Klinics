<template>
    <div class="nav-layout">
        <div class="nav-bar">
            <div class="nav-project">Project</div>
            <el-input class="w-50 m-2 nav-input" placeholder="Please Input" :prefix-icon="Search" />
            <el-button class="nav-login" plain>Log in</el-button>
            <el-button color="#000000" style="color: white">Sign up</el-button>
        </div>
    </div>
    <div class="container">
        <div class="top">
            <img class="top-img" src="@/assets/image/project-top-bg.jpeg" />
            <el-card class="top-card" shadow="always">This content needs to be added</el-card>
        </div>
        <div class="tab">
            <div class="tab-title">This content needs to be added</div>
            <el-tabs v-model="tabActiveName" class="tab-tabs">
                <el-tab-pane
                    v-for="(item, index) in tabOptions"
                    :key="index"
                    :label="item.title"
                    :name="item.name"
                    class="tab-item"
                >
                    <div class="tab-item-title">{{ item.title }}</div>
                    <HorizontalGallery2 />
                </el-tab-pane>
            </el-tabs>
        </div>
        <div class="category">
            <HorizontalGallery2 categoryName="PYTHON" />
        </div>
    </div>
</template>

<script lang="ts" setup>
import { Search } from '@element-plus/icons-vue'
import { ref } from '@vue/reactivity'
import HorizontalGallery2 from '@/components/HorizontalGallery2.vue'

const tabActiveName = ref('first')

const tabOptions = ref([
    { id: 0, name: 'first', title: 'TAb1' },
    { id: 1, name: 'second', title: 'TAb2' },
    { id: 2, name: 'third', title: 'TAb3' }
])

</script>

<style lang='less' scoped>
.nav-layout {
    width: 100%;
    line-height: 51px;
    z-index: 200;
    position: fixed;
    background-color: white;
    margin-top: -50px;
    box-shadow: 0 5px 10px -5px grey;

    .nav-bar {
        display: -webkit-flex;
        display: flex;
        align-items: center;
        padding: 0 12px 0 32px;

        .nav-project {
            margin-right: 20px;
        }
        .nav-input {
            flex: 1;
        }
        .nav-login {
            margin-left: 20px;
        }
    }
}

.container {
    width: 100%;
    background-color: white;
    padding-bottom: 30px;

    .top {
        margin: 0 30px;
        overflow: hidden;
        position: relative;
        .top-img {
            width: 100%;
            float: right;
        }
        .top-card {
            width: 40%;
            font-size: 50px;
            top: 30px;
            left: 30px;
            position: absolute;
        }
    }
    .tab {
        margin: 30px 40px 0 40px;
        ::v-deep .el-tabs__header {
            margin: 0 0 1px 0;
        }
        ::v-deep .el-tabs__nav-wrap::after {
            background-color: transparent;
        }
        ::v-deep .el-tabs__active-bar {
            background-color: transparent;
        }
        ::v-deep .el-tabs__item {
            color: #6a6f73;
            font-weight: 700;
            font-size: 17px;
        }
        ::v-deep .el-tabs__item.is-active {
            color: black;
            font-weight: 700;
            font-size: 17px;
        }
        .tab-title {
            font-size: 35px;
            font-weight: 500;
        }

        .tab-tabs {
            margin-top: 10px;
            .tab-item {
                border: 1px solid grey;
                padding: 20px;
            }
        }
    }
    .category {
        margin: 30px 40px 0 40px;
    }
}
</style>