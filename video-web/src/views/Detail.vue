<template>
  <div class="container">
    <div class="video-content">
      <div class="video-content-player" ref="player"/>
      <div class="video-content-title">{{ videoDetail.title }}</div>
      <div class="video-content-info">
        <div class="video-content-author">{{ videoDetail.author }}</div>
        <div>{{ videoDetail.category.name }}</div>
        <div>{{ parseTime(videoDetail.createTime) }}</div>
      </div>
    </div>
    <div class="container-right">
      <router-link v-for="video in listSame" :to="{ path: '/detail/' + video.id }" :key="video.id">
        <div class="video-item-box">
          <el-image class="video-item-box-image" :src="video.posterUrl" fit="cover"></el-image>
          <div class="video-item-box-title">{{ video.title }}</div>
          <div class="video-item-box-author">{{ video.author }}</div>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script setup>
import {nextTick, onMounted, reactive, ref, watch} from 'vue';
import Artplayer from "artplayer";
import {useRoute} from 'vue-router'
import axios from '@/utils/axios'
import parseTime from '@/utils/dateTime'
import {ElMessage} from "element-plus";

const route = useRoute()

watch(
    () => route.params,
    (val) => {
      increasePlay.value = false
      getDetail()
    }
)

const player = ref(null)

const options = reactive({
  url: 'https://cdn.jsdelivr.net/gh/xdlumia/files/video-play/IronMan2.mp4',
  poster: '',
  title: 'test',
  theme: '#409EFF',
  fullscreen: true,
  fullscreenWeb: true,
  flip: true,
  playbackRate: true,
  aspectRatio: true,
  setting: true,
  screenshot: true,
  pip: true,
})

onMounted(() => {
  getDetail()
})

defineExpose({
  player,
  options
})

const videoDetail = ref({
  id: -1,
  title: '',
  author: '',
  posterUrl: '',
  videoUrl: '',
  createTime: '',
  category: {
    id: -1,
    name: ''
  }
})

const increasePlay = ref(false)

const getDetail = () => {
  if (!route.params.id) {
    return
  }
  if (options.poster !== '') {
    window.location.reload()
    return
  }
  axios.get('system/video/get?id=' + route.params.id).then(res => {
    videoDetail.value = res.data

    options.poster = videoDetail.value.posterUrl
    options.url = videoDetail.value.videoUrl
    nextTick(() => {
      const art = new Artplayer({
        ...options,
        container: player.value
      });
      art.on('play', (...args) => {
        if (!increasePlay.value) {
          increasePlay.value = true
          axios.put("system/video/increasePlay", videoDetail.value).then((res) => {
          });
        }
      });
    })

    getListSameCate(videoDetail.value.id, videoDetail.value.category.id)
  })
}

const listSame = ref([])

const getListSameCate = (id, categoryId) => {
  axios.get(`system/video/listSameCate?id=${id}&categoryId=${categoryId}`).then(res => {
    listSame.value = res.data
  })
}
</script>

<style lang="less" scoped>
.container {
  padding: 20px 30px;

  .video-content {
    display: inline-block;
    width: 60%;

    .video-content-player {
      height: 500px;
    }

    .video-content-title {
      color: black;
      margin: 10px 0;
      font-size: 20px;
    }

    .video-content-info {
      display: flex;
      justify-content: space-between;
    }
  }

  .container-right {
    width: 35%;
    height: 100vh;
    float: right;
    overflow: auto;

    .video-item-box {
      width: 100%;
      height: 150px;
      margin-bottom: 10px;

      .video-item-box-image {
        width: 100%;
        max-width: 200px;
        height: 120px;
      }

      .video-item-box-title {
        color: black;
      }

      .video-item-box-author {
        color: grey;
        font-size: 12px;
      }
    }
  }
}
</style>
