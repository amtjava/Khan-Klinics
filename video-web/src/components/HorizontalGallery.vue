<template>
  <div :v-show="isShow">
    <div class="title">{{ category.name }}</div>
    <el-tabs v-model="tabActiveName" class="tab-tabs">
      <el-tab-pane
        v-for="(category, index) in categoryList"
        :key="index"
        :label="category.name"
        :name="index"
        class="tab-item"
      >
        <div class="index-group-box">
          <div class="index-group-boxIn" ref="groupBoxRef">
            <router-link
              v-for="item in category.list"
              :key="item.id"
              :to="{ path: '/detail/' + item.id }"
            >
              <div class="group-card">
                <el-image :src="item.posterUrl" class="index-group-boxIn-img" />
                <div class="index-group-boxIn-title">{{ item.title }}</div>
                <div class="index-group-boxIn-author">{{ item.author }}</div>
              </div>
            </router-link>
          </div>
          <el-button
            class="index-group-box-btn-left"
            type="primary"
            :icon="ArrowLeft"
            circle
            @click="groupScroll('left')"
            color="#000000"
          ></el-button>
          <el-button
            class="index-group-box-btn-right"
            type="primary"
            :icon="ArrowRight"
            circle
            @click="groupScroll('right')"
            color="#000000"
          ></el-button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import axios from '../utils/axios'
import { List } from 'lodash';

const props = defineProps({
  category: Object
})

const isShow = ref(false);
const groupBoxRef = ref(null);

// ⿏标点击滚动
const groupScroll = (direction) => {
  if (direction === 'right') {
    groupBoxRef.value.scrollLeft += 200;
  } else {
    groupBoxRef.value.scrollLeft -= 200;
  }
}

onMounted(() => {
  getSubCategory()
})

const categoryList = ref([])

const getSubCategory = () => {
  axios.get('system/category/list-parent?parentId=' + props.category.id).then(res => {
    const list = res.data
    list.push({ id: props.category.id, name: 'OTHER' })
    categoryList.value = list
    for (let i = 0; i < list.length; i++) {
      getListSameCate(list[i].id, (listVideo) => {
        if (!isShow.value && listVideo.length > 0) {
          isShow.value = true
          console.log('===' + listVideo.length)
        }
        const list2 = categoryList.value
        list2[i].list = listVideo
        categoryList.value = list2
      })
    }
  })
}

const getListSameCate = (categoryId, callback: (list: []) => void) => {
  axios.get(`system/video/listByCate?categoryId=${categoryId}`).then(res => {
    callback(res.data)
  })
}

const tabActiveName = ref(0)
</script>

<style lang="less" scoped>
.title {
  font-size: 35px;
  font-weight: 500;
  margin-top: 30px;
}

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

.tab-tabs {
  margin-top: 10px;
  .tab-item {
    border: 1px solid grey;
    padding: 20px;

    .index-group-box {
      position: relative;
      box-sizing: border-box;
      width: 100%;
      margin-top: 8px;
      margin-bottom: 10px;

      .index-group-boxIn {
        display: flex;
        scroll-behavior: smooth;
        white-space: nowrap;
        overflow-x: auto;
        flex: none;
        scrollbar-width: none; /* Firefox */
        -ms-overflow-style: none; /* IE 10+ */

        .group-card {
          box-sizing: border-box;
          width: 200px;
          //height: 150px;
          padding-right: 16px;
          flex: none;
          text-overflow: ellipsis;

          .index-group-boxIn-img {
            width: 100%;
            height: 120px;
            background-color: grey;
          }

          .index-group-boxIn-title {
            color: black;
            width: 100%;
            overflow: hidden;
            text-overflow: ellipsis;
          }

          .index-group-boxIn-author {
            color: grey;
            font-size: 12px;
            margin-top: 5px;
            width: 100%;
            overflow: hidden;
            height: 20px;
            text-overflow: ellipsis;
          }
        }

        .group-card:hover {
        }

        .group-card:nth-last-of-type(1) {
          margin-right: 0px;
        }
      }

      .index-group-boxIn::-webkit-scrollbar {
        display: none; /* Chrome Safari */
      }

      .index-group-box-btn-left {
        position: absolute;
        left: 0;
        top: 60px;
        transform: translate(0, -50%);
        color: white;
      }

      .index-group-box-btn-right {
        position: absolute;
        right: 0;
        top: 60px;
        transform: translate(0, -50%);
        color: white;
      }
    }
  }
}
</style>
