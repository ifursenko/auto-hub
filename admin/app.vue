<template>
  <div class="app">
    <header class="header">
      <h1>Админ · АвтоХаб</h1>
      <nav class="nav">
        <NuxtLink to="/admin/login">Вход</NuxtLink>
        <NuxtLink to="/admin/stations">СТО</NuxtLink>
        <NuxtLink to="/admin/services">Услуги</NuxtLink>
        <NuxtLink to="/admin/orders">Заказы</NuxtLink>
      </nav>
    </header>
    <main class="content">
      <NuxtPage />
    </main>
  </div>
</template>

<style scoped>
.app {
  font-family: Arial, sans-serif;
  padding: 16px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #eee;
  margin-bottom: 16px;
}
.nav {
  display: flex;
  gap: 12px;
}
.content {
  padding-top: 12px;
}
</style>
