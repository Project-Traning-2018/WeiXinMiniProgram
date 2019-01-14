<template>
  <view>
    <image :src="src"></image>
    <text @tap="handleTap">{{text}}</text>
    <image :src="img"></image>
    <p>名字是 {{name}}</p>
  </view>
</template>

<script>
  import item from '@/components/list-item'
  export default {
    components: {
      item
    },
    data() {
      return {
        src: '../../static/images/profile.png',
        text: '反馈',
        img: '',
        name: ''
      }
    },
    name: "user",
    methods: {
      handleTap(){
        console.log('Tapped')
        wx.navigateTo({
          url: '../suggestion/main'
        })
      }
    },
    onLoad() {
      this.img = this.globalData.userInfo.avatarUrl
      this.name = this.globalData.userInfo.nickName
    }
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
