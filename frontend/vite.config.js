import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/api/v1': {
        target: 'http://spring:8080',
        changeOrigin: true,
        secure: false
      }
    }
  }
})
