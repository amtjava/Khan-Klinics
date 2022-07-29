import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/index',
            name: 'index',
            component: () => import('../views/Index.vue')
        },
        {
            path: '/detail/:id',
            name: 'detail',
            component: () => import('../views/Detail.vue')
        },
        {
            path: '/videoManager',
            name: 'videoManager',
            component: () => import('../views/VideoManager.vue')
        },
        {
            path: '/categoryControl',
            name: 'categoryControl',
            component: () => import('../views/CategoryControl.vue')
        },
        {
            path: '/project',
            name: 'project',
            component: () => import('../views/Project.vue')
        },
        {
            path: '/submission-policy',
            name: 'submission-policy',
            component: () => import('../views/policy/SubmissionPolicy.vue')
        },
        {
            path: '/privacy-policy',
            name: 'privacy-policy',
            component: () => import('../views/policy/PrivacyPolicy.vue')
        },
        {
            path: '/policies-standards',
            name: 'policies-standards',
            component: () => import('../views/policy/PoliciesStandards.vue')
        }
    ],
    // scrollBehavior(to, from, savedPosition) {
    //     if (savedPosition) {
    //         return savedPosition
    //     } else {
    //         // chrome
    //         document.body.scrollTop = 0
    //         // firefox
    //         document.documentElement.scrollTop = 0
    //         // safari
    //         window.pageYOffset = 0
    //         return { x: 0, y: 0 }
    //     }
    // }
})

export default router


// router.beforeEach((to, from, next) => {
//     // chrome
//     document.body.scrollTop = 0
//     // firefox
//     document.documentElement.scrollTop = 0
//     // safari
//     window.pageYOffset = 0
//     console.log('=========')
//     debugger
//     next()
// })