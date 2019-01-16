<template>
  <div>
    <view class="index_left">
    <!-- <navigator url='../suggestion/main'>  --> 
    <image class="index_img" :src="img"></image>
     <p class="p1">{{name}}</p>
   <!-- </navigator> -->
  </view>
  
    <view class="index_view">
    <image :src="src0"></image>
    <text @tap="handleTap0">{{text0}}</text>
    <image  class="icon" src="../../static/images/user_5.png" mode="aspectFill" />
  </view>
  
   <view class="index_view">
    <image :src="src1"></image>
    <text @tap="handleTap1">{{text1}}</text>
    <image  class="icon" src="../../static/images/user_5.png" mode="aspectFill" />
   </view>
   <view class="index_view">
    <image :src="src2"></image>
    <text @tap="handleTap2">{{text2}}</text>
    <image  class="icon" src="../../static/images/user_5.png" mode="aspectFill" />
    </view>
  </div>
</template>

<script>
  import item from '@/components/list-item'
  export default {
    components: {
      item
    },
    data() {
      return {
        src0: '../../static/images/user_2.png',
        src1: '../../static/images/user_3.png',
        src2: '../../static/images/user_4.png',
        text0: '我的信息',
        text1: '意见反馈',
        text2: '系统版本',
        img:'',
        name:''
      }
    },
    name: "user",
    methods: {
      handleTap0(){
        wx.navigateTo({
          url: '../suggestion/main'
        })
      },
      handleTap1(){
        wx.navigateTo({
          url: '../suggestion/main'
        })
      },
      handleTap2(){
        wx.navigateTo({
          url: '../version/main'
        })
      }
    },
     onLoad() {
      console.log(this.globalData.userInfo)
      this.img = this.globalData.userInfo.avatarUrl
      this.name = this.globalData.userInfo.nickName
    }
  }
</script>

<style scoped>
 div {
  display: flex;
  flex-direction: column;
 }
  .index_view {
    display: flex;
    align-items: center;
    margin:0 0 30rpx 0rpx;
    border:1rpx solid #eee;
    padding-left: 30rpx;
  }
  image { 
    height: 80rpx;
    width: 80rpx;
    /*border: 1rpx solid #000;*/
  }
  text {
    height: 150rpx;
    width: 550rpx;
    line-height: 150rpx;
    padding-left: 50rpx;
  }
   .index_img{

    /*width: 200rpx;*/
    /*height: 200rpx;*/
    border-radius: 100rpx;
    /*margin: 100rpx 0;*/
    height: 150rpx;
    width: 150rpx;
  }
  .index_left{
    display: flex;
    align-items: flex-end;
    margin:60rpx 0 80rpx 0rpx;
    border-bottom: 1rpx solid #eee;
    padding-left:40rpx;
    padding-bottom:35rpx;

  }
  .icon{
     height: 40rpx;
    width: 40rpx;
    margin-right: 40rpx;
  }
  .p1{
    font-size: 40rpx;
    padding-left: 40rpx;
    padding-bottom: 30rpx;
  }
</style>
