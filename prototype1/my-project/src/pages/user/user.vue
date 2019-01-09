<template>
  <view>
    <image :src="src"></image>
    <text>{{text}}</text>
  </view>
</template>

<script>
  export default {
    name: "list-item",
    props: ['text', 'src']
  }
</script>

<style scoped>
  view {
    display: flex;
    align-items: center;
  }
</style>
