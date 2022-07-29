import {createApp} from 'vue'
import App from './App.vue'
import router from './router/index'

import './assets/css/reset.css'
import 'element-plus/theme-chalk/el-message.css';
import 'element-plus/theme-chalk/el-message-box.css';

const app = createApp(App)

app.use(router)

app.mount('#app')
