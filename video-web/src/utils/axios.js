import axios from 'axios'
import {ElMessage} from 'element-plus'

axios.defaults.baseURL = import.meta.env.VITE_API_DOMAIN
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/json'

axios.interceptors.response.use(res => {
    if (res.status !== 200) {
        if (res.data.msg) ElMessage.error(res.data.msg)
        // if (res.data.resultCode == 419) {
        //     router.push({ path: '/login' })
        // }
        return Promise.reject(res.data)
    }
    if (typeof res.data !== 'object') {
        ElMessage.error('Server Error！')
        return Promise.reject(res)
    }
    if (res.data.code !== 0) {
        ElMessage.error(res.data.msg)
        return Promise.reject(res.data)
    }

    return res.data
})

export default axios