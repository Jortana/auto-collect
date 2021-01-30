<template>
<div class="content">
  <div class="status">
    <h1>我的收集</h1>
    <input type="text" />
  </div>
</div>
</template>

<script>
export default {
  name: 'MyTask'
}
</script>

<style scoped src="../../css/glass-common.css"></style>
<style scoped>
.status input {
  background: linear-gradient(
    to right bottom,
    rgba(255, 255, 255, 0.7),
    rgba(255, 255, 255, 0.3)
  );
  border: none;
  outline: none;
  width: 50%;
  padding: 0.5rem 1rem;
  border-radius: 2rem;
}
</style>
