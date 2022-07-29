<template>
  <div v-show="listVideo.length">
    <div class="title" v-if="categoryName">{{ categoryName }}</div>
    <div class="index-group-box">
      <div class="index-group-boxIn" ref="groupBoxRef">
        <div class="group-card" v-for="item in listVideo" :key="item.id">
          <el-image :src="item.url" class="index-group-boxIn-img" />
          <div class="index-group-boxIn-title">{{ item.title }}</div>
          <div class="index-group-boxIn-author">{{ item.author }}</div>
          <el-rate
            v-model="rateValue"
            disabled
            show-score
            text-color="#b4690e"
            score-template="{value}"
          ></el-rate>
        </div>
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
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'

const props = defineProps({
  categoryName: String
})

const groupBoxRef = ref(null);

// ⿏标点击滚动
const groupScroll = (direction) => {
  if (direction === 'right') {
    groupBoxRef.value.scrollLeft += 200;
  } else {
    groupBoxRef.value.scrollLeft -= 200;
  }
}

const listVideo = ref([])

onMounted(() => {
  const list = []
  for (var i = 0; i < 20; i++) {
    list.push({ id: i, title: 'This content needs to be added', author: 'author', url: 'https://img-c.udemycdn.com/course/240x135/394676_ce3d_5.jpg' })
  }
  listVideo.value = list
})

const rateValue = ref(4.1)

</script>

<style lang="less" scoped>
.title {
  font-size: 20px;
  font-weight: 400;
}
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
</style>
