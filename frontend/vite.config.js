import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import svgr from '@svgr/rollup';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), svgr()],
  server:{
    port: 3000,
    esbuild: {
      loader: 'jsx', 
      include: /\.jsx?$/, 
    },
    // Get rid (remove) of the CORS error
    proxy: {
      "/v1" : {
        target: "http://localhost:8080", 
        changeOrigin: true,
        // because not use https
        secure: false,
      }
    }
  },
})