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
  image {
    height: 150rpx;
    width: 150rpx;
    border: 1rpx solid #000;
  }
  text {
    height: 150rpx;
    width: 550rpx;
    line-height: 150rpx;
    padding-left: 50rpx;
  }
</style>
