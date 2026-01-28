const apiProxyTarget = process.env.NUXT_API_PROXY_TARGET || 'http://backend:8080'

export default defineNuxtConfig({
  app: {
    head: {
      title: 'АвтоХаб · Мастер'
    }
  },
  runtimeConfig: {
    public: {
      apiBase: process.env.NUXT_PUBLIC_API_BASE || '/api'
    }
  },
  nitro: {
    routeRules: {
      '/api/**': { proxy: `${apiProxyTarget}/**` }
    }
  }
})
