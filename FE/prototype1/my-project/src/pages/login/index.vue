<template>
  <div class="indexContainer">
    <img v-if="isShow" class="index_img" :src="userInfo.avatarUrl" alt="">
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
        isShow: false // 没有授权
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
            this.userInfo = data.userInfo;
            console.log('id: '+that.globalData.id)
            let data2send = {
              userIdMd5 : that.globalData.id,
              userName: this.userInfo.nickName,
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
</style>
