<template>
  <div class="app">
    <header class="header">
      <h1>Мастер · АвтоХаб</h1>
      <nav class="nav">
        <NuxtLink to="/master/login">Вход</NuxtLink>
        <NuxtLink to="/master/dashboard">Дашборд</NuxtLink>
        <NuxtLink to="/master/schedule">Расписание</NuxtLink>
        <NuxtLink to="/master/orders">Заказы</NuxtLink>
        <NuxtLink to="/master/profile">Профиль</NuxtLink>
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
