<template>
    <div class="show">
     <p>当前版本：v0.1.0</p>
    
    </div>
</template>

<script>
   
</script>

<style>
  page{
    height: 100%;
  }
  .show {
    position:  relative; /* 1 */
    width: 750rpx;
    height: 100%;
    border: 1rpx solid #eee;
    font-size: 50rpx;
    line-height: 80rpx;
    text-align: center;
    border-radius: 10rpx;
    
  }
  p {
    display: block;
    position: absolute; /* 1 */
    top: 50%;  /* 2 */
    left: 50%;  /* 2 */
    width: 600rpx;
    height: 80rpx;
    line-height: 80rpx;
    margin-left: -300rpx;  /* 3 */
    margin-top: -40rpx;  /* 3 */
  }
</style>