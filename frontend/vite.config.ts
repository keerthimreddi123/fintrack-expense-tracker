/// <reference types="vitest/config" />
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// PROVIDED. The dev server runs on :4200 and forwards /api to the Spring Boot backend on :8080.
// Do not change the port or the proxy target —
// the E2E suite depends on them.
export default defineConfig({
  plugins: [react()],
  server: {
    port: 4200,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
      },
    },
  },
  test: {
    environment: 'jsdom',
    globals: false,
    include: ['src/**/*.spec.{ts,tsx}'],
  },
});
