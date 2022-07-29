import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import path from 'path'
import fs from 'fs'
import dotenv from 'dotenv'

export default ({command, mode}) => {
    let NODE_ENV = process.env.NODE_ENV || 'development';
    let envFiles = [];
    if (command === 'serve') {
        envFiles = [
            /** default file */
            `.env`
        ]
    } else {
        envFiles = [
            /** default file */
            `.env`,
            /** mode file */
            `.env.${NODE_ENV}`
        ]
    }
    for (const file of envFiles) {
        const envConfig = dotenv.parse(fs.readFileSync(file))
        for (const k in envConfig) {
            process.env[k] = envConfig[k]
        }
    }
    return defineConfig({
        build: {
            outDir: 'video-web'
        },
        base: process.env.VITE_APP_BASE,
        plugins: [
            vue(),
            AutoImport({
                resolvers: [ElementPlusResolver()],
            }),
            Components({
                resolvers: [ElementPlusResolver()],
            })
        ],
        resolve: {
            alias: {
                '~': path.resolve(__dirname, './'),
                '@': path.resolve(__dirname, 'src')
            }
        }
    })
}
