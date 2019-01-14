<template>
    <div class="detailContainer">

    </div>
</template>

<script>
    export default {
        name: "index",
        onLoad(options) {
          console.log(options)
        }
    }
</script>

<style scoped>
    .detailContainer {
      width:600rpx;
      height:700rpx;
      /*border:4rpx solid #dcdee2;*/
      margin:20rpx auto;
      border-radius:20rpx;
      box-shadow:#dedee2 0 0 10rpx 5rpx;
    }
</style>
