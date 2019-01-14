<template>
  <div class="indexContainer">
    <div class="mask">
      <div class="img-container">
        <image src="../../static/images/spinner.svg" class="loading"></image>
        <p class="info">登录中</p>
      </div>
    </div>
    <image v-if="isShow" class="index_img" :src="userInfo.avatarUrl" alt=""></image>
    <Button class="btn" v-else
            open-type="getUserInfo"
            @getuserinfo="getUserInfo"
    >
      点击获取用户信息
    </Button>
    <p class="userName">Hello {{ userInfo.nickName }}</p>
    <div class="goStudy" @tap="handleTap">
      <p>进入投票助手</p>
    </div>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        userInfo: {}, // 初始数据
        isShow: false, // 没有授权
        notReady: this.globalData.id == undefined
      }
    },
    beforeMount() {
      // 获取用户登录信息
      this.handleGetUserInfo();
    },
    methods: {
      // 获取用户登录信息
      handleGetUserInfo() {
        let that = this
        wx.getUserInfo({
          success: (data)=>{
            // console.log('data:' +JSON.stringify(data));
            that.globalData.userInfo = data.userInfo;
            console.log('id: '+that.globalData.id)
            let data2send = {
              userIdMd5 : that.globalData.id,
              userName: data.userInfo.nickName,
              userIcon: "this",
              userPhonenumber: ''
            }
            console.log(JSON.stringify(data2send))
            that.$fly.interceptors.request.use((request) => {
              request.headers = {
                'Content-Type': 'application/json'
              };
            })
            that.$fly.get({
              method: 'POST',
              url: 'http://activity103.mynatapp.cc/wx/wxuserinfo',/*contentType: 'application/json;charset=utf-8',*/
              body: JSON.stringify(data2send)
            }).then(function(res){
              console.log(res.data)
            })
            this.isShow = true;
          },
          fail: () => {
            console.log('*****获取失败*****');
          }
        });
      },

      getUserInfo(data){
        console.log('46' + JSON.stringify(data));
        // 判断用户是否授权
        if (data.mp.detail.rawData) {
          // 用户授权
          this.handleGetUserInfo();
        }
      },

      handleTap(){
        wx.switchTab({
          url: '../home/main'
        })
      }
    }
  }
</script>

<style scoped>
  page {
    background-color: #8ed145;
  }
  .indexContainer {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100%;
  }
  .index_img{
    width: 200rpx;
    height: 200rpx;
    border-radius: 100rpx;
    margin: 100rpx 0;
  }
  .userName {
    font-size: 40rpx;
    font-weight: bold;
    margin: 100rpx 0;
  }.goStudy {
     width: 220rpx;
     height: 80rpx;
     border: 1rpx solid #eee;
     font-size: 24rpx;
     line-height: 80rpx;
     text-align: center;
     border-radius: 10rpx;
   }
  .btn {
    width: 300rpx;
    height: 300rpx;
    border-radius: 150rpx;
    margin: 50rpx 0;
    line-height: 300rpx;
    text-align: center;
    font-size: 26rpx;
  }
  .mask {
    position: absolute;
    height: 750rpx;
    width: 750rpx;
    z-index: 999;
    margin: 200rpx auto;
  }
  .img-container {
    position: absolute;
    height: 200rpx;
    width: 200rpx;
    top: 50%;
    left: 50%;
    margin-top: -100rpx;
    margin-left: -100rpx;
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 20rpx;
    /*background-image: url('data:image/svg+xml;base64,PHN2ZyB3aWR0aD0nNDhweCcgaGVpZ2h0PSc0OHB4JyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMDAgMTAwIiBwcmVzZXJ2ZUFzcGVjdFJhdGlvPSJ4TWlkWU1pZCIKICAgICBjbGFzcz0idWlsLWRlZmF1bHQiPgogICAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEwMCIgaGVpZ2h0PSIxMDAiIGZpbGw9Im5vbmUiIGNsYXNzPSJiayI+PC9yZWN0PgogICAgPHJlY3QgeD0nNDYuNScgeT0nNDAnIHdpZHRoPSc3JyBoZWlnaHQ9JzIwJyByeD0nNScgcnk9JzUnIGZpbGw9JyNkY2RlZTInCiAgICAgICAgICB0cmFuc2Zvcm09J3JvdGF0ZSgwIDUwIDUwKSB0cmFuc2xhdGUoMCAtMzApJz4KICAgICAgICA8YW5pbWF0ZSBhdHRyaWJ1dGVOYW1lPSdvcGFjaXR5JyBmcm9tPScxJyB0bz0nMCcgZHVyPScxcycgYmVnaW49JzBzJyByZXBlYXRDb3VudD0naW5kZWZpbml0ZScvPgogICAgPC9yZWN0PgogICAgPHJlY3QgeD0nNDYuNScgeT0nNDAnIHdpZHRoPSc3JyBoZWlnaHQ9JzIwJyByeD0nNScgcnk9JzUnIGZpbGw9JyNkY2RlZTInCiAgICAgICAgICB0cmFuc2Zvcm09J3JvdGF0ZSgzMCA1MCA1MCkgdHJhbnNsYXRlKDAgLTMwKSc+CiAgICAgICAgPGFuaW1hdGUgYXR0cmlidXRlTmFtZT0nb3BhY2l0eScgZnJvbT0nMScgdG89JzAnIGR1cj0nMXMnIGJlZ2luPScwLjA4MzMzMzMzMzMzMzMzMzMzcycKICAgICAgICAgICAgICAgICByZXBlYXRDb3VudD0naW5kZWZpbml0ZScvPgogICAgPC9yZWN0PgogICAgPHJlY3QgeD0nNDYuNScgeT0nNDAnIHdpZHRoPSc3JyBoZWlnaHQ9JzIwJyByeD0nNScgcnk9JzUnIGZpbGw9JyNkY2RlZTInCiAgICAgICAgICB0cmFuc2Zvcm09J3JvdGF0ZSg2MCA1MCA1MCkgdHJhbnNsYXRlKDAgLTMwKSc+CiAgICAgICAgPGFuaW1hdGUgYXR0cmlidXRlTmFtZT0nb3BhY2l0eScgZnJvbT0nMScgdG89JzAnIGR1cj0nMXMnIGJlZ2luPScwLjE2NjY2NjY2NjY2NjY2NjY2cycKICAgICAgICAgICAgICAgICByZXBlYXRDb3VudD0naW5kZWZpbml0ZScvPgogICAgPC9yZWN0PgogICAgPHJlY3QgeD0nNDYuNScgeT0nNDAnIHdpZHRoPSc3JyBoZWlnaHQ9JzIwJyByeD0nNScgcnk9JzUnIGZpbGw9JyNkY2RlZTInCiAgICAgICAgICB0cmFuc2Zvcm09J3JvdGF0ZSg5MCA1MCA1MCkgdHJhbnNsYXRlKDAgLTMwKSc+CiAgICAgICAgPGFuaW1hdGUgYXR0cmlidXRlTmFtZT0nb3BhY2l0eScgZnJvbT0nMScgdG89JzAnIGR1cj0nMXMnIGJlZ2luPScwLjI1cycgcmVwZWF0Q291bnQ9J2luZGVmaW5pdGUnLz4KICAgIDwvcmVjdD4KICAgIDxyZWN0IHg9JzQ2LjUnIHk9JzQwJyB3aWR0aD0nNycgaGVpZ2h0PScyMCcgcng9JzUnIHJ5PSc1JyBmaWxsPScjZGNkZWUyJwogICAgICAgICAgdHJhbnNmb3JtPSdyb3RhdGUoMTIwIDUwIDUwKSB0cmFuc2xhdGUoMCAtMzApJz4KICAgICAgICA8YW5pbWF0ZSBhdHRyaWJ1dGVOYW1lPSdvcGFjaXR5JyBmcm9tPScxJyB0bz0nMCcgZHVyPScxcycgYmVnaW49JzAuMzMzMzMzMzMzMzMzMzMzM3MnIHJlcGVhdENvdW50PSdpbmRlZmluaXRlJy8+CiAgICA8L3JlY3Q+CiAgICA8cmVjdCB4PSc0Ni41JyB5PSc0MCcgd2lkdGg9JzcnIGhlaWdodD0nMjAnIHJ4PSc1JyByeT0nNScgZmlsbD0nI2RjZGVlMicKICAgICAgICAgIHRyYW5zZm9ybT0ncm90YXRlKDE1MCA1MCA1MCkgdHJhbnNsYXRlKDAgLTMwKSc+CiAgICAgICAgPGFuaW1hdGUgYXR0cmlidXRlTmFtZT0nb3BhY2l0eScgZnJvbT0nMScgdG89JzAnIGR1cj0nMXMnIGJlZ2luPScwLjQxNjY2NjY2NjY2NjY2NjdzJyByZXBlYXRDb3VudD0naW5kZWZpbml0ZScvPgogICAgPC9yZWN0PgogICAgPHJlY3QgeD0nNDYuNScgeT0nNDAnIHdpZHRoPSc3JyBoZWlnaHQ9JzIwJyByeD0nNScgcnk9JzUnIGZpbGw9JyNkY2RlZTInCiAgICAgICAgICB0cmFuc2Zvcm09J3JvdGF0ZSgxODAgNTAgNTApIHRyYW5zbGF0ZSgwIC0zMCknPgogICAgICAgIDxhbmltYXRlIGF0dHJpYnV0ZU5hbWU9J29wYWNpdHknIGZyb209JzEnIHRvPScwJyBkdXI9JzFzJyBiZWdpbj0nMC41cycgcmVwZWF0Q291bnQ9J2luZGVmaW5pdGUnLz4KICAgIDwvcmVjdD4KICAgIDxyZWN0IHg9JzQ2LjUnIHk9JzQwJyB3aWR0aD0nNycgaGVpZ2h0PScyMCcgcng9JzUnIHJ5PSc1JyBmaWxsPScjZGNkZWUyJwogICAgICAgICAgdHJhbnNmb3JtPSdyb3RhdGUoMjEwIDUwIDUwKSB0cmFuc2xhdGUoMCAtMzApJz4KICAgICAgICA8YW5pbWF0ZSBhdHRyaWJ1dGVOYW1lPSdvcGFjaXR5JyBmcm9tPScxJyB0bz0nMCcgZHVyPScxcycgYmVnaW49JzAuNTgzMzMzMzMzMzMzMzMzNHMnIHJlcGVhdENvdW50PSdpbmRlZmluaXRlJy8+CiAgICA8L3JlY3Q+CiAgICA8cmVjdCB4PSc0Ni41JyB5PSc0MCcgd2lkdGg9JzcnIGhlaWdodD0nMjAnIHJ4PSc1JyByeT0nNScgZmlsbD0nI2RjZGVlMicKICAgICAgICAgIHRyYW5zZm9ybT0ncm90YXRlKDI0MCA1MCA1MCkgdHJhbnNsYXRlKDAgLTMwKSc+CiAgICAgICAgPGFuaW1hdGUgYXR0cmlidXRlTmFtZT0nb3BhY2l0eScgZnJvbT0nMScgdG89JzAnIGR1cj0nMXMnIGJlZ2luPScwLjY2NjY2NjY2NjY2NjY2NjZzJyByZXBlYXRDb3VudD0naW5kZWZpbml0ZScvPgogICAgPC9yZWN0PgogICAgPHJlY3QgeD0nNDYuNScgeT0nNDAnIHdpZHRoPSc3JyBoZWlnaHQ9JzIwJyByeD0nNScgcnk9JzUnIGZpbGw9JyNkY2RlZTInCiAgICAgICAgICB0cmFuc2Zvcm09J3JvdGF0ZSgyNzAgNTAgNTApIHRyYW5zbGF0ZSgwIC0zMCknPgogICAgICAgIDxhbmltYXRlIGF0dHJpYnV0ZU5hbWU9J29wYWNpdHknIGZyb209JzEnIHRvPScwJyBkdXI9JzFzJyBiZWdpbj0nMC43NXMnIHJlcGVhdENvdW50PSdpbmRlZmluaXRlJy8+CiAgICA8L3JlY3Q+CiAgICA8cmVjdCB4PSc0Ni41JyB5PSc0MCcgd2lkdGg9JzcnIGhlaWdodD0nMjAnIHJ4PSc1JyByeT0nNScgZmlsbD0nI2RjZGVlMicKICAgICAgICAgIHRyYW5zZm9ybT0ncm90YXRlKDMwMCA1MCA1MCkgdHJhbnNsYXRlKDAgLTMwKSc+CiAgICAgICAgPGFuaW1hdGUgYXR0cmlidXRlTmFtZT0nb3BhY2l0eScgZnJvbT0nMScgdG89JzAnIGR1cj0nMXMnIGJlZ2luPScwLjgzMzMzMzMzMzMzMzMzMzRzJyByZXBlYXRDb3VudD0naW5kZWZpbml0ZScvPgogICAgPC9yZWN0PgogICAgPHJlY3QgeD0nNDYuNScgeT0nNDAnIHdpZHRoPSc3JyBoZWlnaHQ9JzIwJyByeD0nNScgcnk9JzUnIGZpbGw9JyNkY2RlZTInCiAgICAgICAgICB0cmFuc2Zvcm09J3JvdGF0ZSgzMzAgNTAgNTApIHRyYW5zbGF0ZSgwIC0zMCknPgogICAgICAgIDxhbmltYXRlIGF0dHJpYnV0ZU5hbWU9J29wYWNpdHknIGZyb209JzEnIHRvPScwJyBkdXI9JzFzJyBiZWdpbj0nMC45MTY2NjY2NjY2NjY2NjY2cycgcmVwZWF0Q291bnQ9J2luZGVmaW5pdGUnLz4KICAgIDwvcmVjdD4KPC9zdmc+Cg==');*/
    background-position: center;
    background-repeat: no-repeat;
  }
  .loading {
    position: absolute;
    top: 50%;
    left: 50%;
    height: 100rpx;
    width: 100rpx;
    margin-left: -50rpx;
    margin-top: -50rpx;
  }
  .info {
    color: #eeeeee;
    font-size: 24rpx;
    text-align: center;
    position: absolute;
    bottom: 20rpx;
    margin: 0 auto;
    width: 100%;
    word-spacing: 4rpx;
  }
</style>
