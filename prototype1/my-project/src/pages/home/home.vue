<template id="tpl">
  <div id="container">
    <!--地图容器
    longitude="poi.longitude"
         latitude="poi.latitude"-->
    <!--longitude及latitude为设置为调转到指定地址位置，默认不显示-->
    <map id="myMap"
         :markers="markers"
         :latitude="latitude"
         :longitude="longitude"
         scale='16' show-location>
    </map>
    <cover-view>
      <button @tap="handleTap">
        创建报名
      </button>
    </cover-view>
    <!--地址描述经纬度展示-->
    <!--<view>地址纬度："{{poi.latitude}}"</view>-->
    <!--<view>地址经度："{{poi.longitude}}"</view>-->
  </div>
</template>

<script>
  export default {
    name: "home",
    data() {
      return {
        borderRadius: 5,
        latitude: 0,
        longitude: 0,
        markers: [],
        callout: {
          content: '预计还有10分钟到达',
          bgColor: "#736F6E",
          color: "#ffffff",
          padding: 10,
          display: "ALWAYS",
          borderRadius: 5
        },
        mobileLocation: {//移动选择位置数据
          longitude: 0,
          latitude: 0,
          address: '',
        }
      }
    },
    methods: {
      handleTap() {
        console.log("Tapped!")
      }
    },
    beforeCreate() {
      console.log('Created')
      let that = this
      let myPoi = {}
      wx.getLocation({
        type: 'gcj02',
        success(res) {
          console.log(res)
          myPoi = res
          console.log(this)
          that.qqmapsdk.reverseGeocoder({
            location: {
              latitude: myPoi.latitude,
              longitude: myPoi.longitude
            },
            success(res) {
              console.log(res)
              that.latitude = myPoi.latitude
              that.longitude = myPoi.longitude
              console.log(that)
              that.markers = [
                {
                  id: 0,
                  latitude: res.latitude,
                  longitude: res.longitude,
                  callout: {//窗体
                    content: that.callout.content,
                    bgColor: that.callout.bgColor,
                    color: that.callout.color,
                    padding: that.callout.padding,
                    display: that.callout.display,
                    borderRadius: that.callout.borderRadius
                  }
                },
                {
                  id: 1,
                  latitude: 30.51428,
                  longitude: 114.435616,
                  callout: {//窗体
                    content: that.callout.content,
                    bgColor: that.callout.bgColor,
                    color: that.callout.color,
                    padding: that.callout.padding,
                    display: that.callout.display,
                    borderRadius: that.callout.borderRadius
                  }
                },
                {
                  id: 2,
                  latitude: 30.51308,
                  longitude: 114.43435,
                  callout: {//窗体
                    content: that.callout.content,
                    bgColor: that.callout.bgColor,
                    color: that.callout.color,
                    padding: that.callout.padding,
                    display: that.callout.display,
                    borderRadius: that.callout.borderRadius
                  }
                },
                {
                  id: 3,
                  latitude: 30.505688,
                  longitude: 114.432465,
                  callout: {//窗体
                    content: that.callout.content,
                    bgColor: that.callout.bgColor,
                    color: that.callout.color,
                    padding: that.callout.padding,
                    display: that.callout.display,
                    borderRadius: that.callout.borderRadius
                  }
                }
              ]
            }
          })
        },
        fail() {
          console.log('获取地址信息失败')
          wx.showModal({
            title: '警告',
            content: '您点击了拒绝授权,将无法正常显示位置信息,点击确定重新获取授权。',
            success: function (res) {
              if (res.confirm) {
                wx.openSetting({
                  success(res) {
                    if (res.authSetting["scope.userInfo"]) {////如果用户重新同意了授权登录
                      wx.getUserInfo({
                        success: function (res) {
                          var userInfo = res.userInfo;
                          that.setData({
                            nickName: userInfo.nickName,
                            avatarUrl: userInfo.avatarUrl,
                          })
                        }
                      })
                    }
                  }, fail: function (res) {
                  }
                })
              }
            }
          })
        },
        mounted() {
          console.log("Mounted")
        }
      })
      this.mapCtx = wx.createMapContext('qqMap');
    }
  }
</script>

<style scoped>
  page {
    height: 100%;
    width: 100%;
  }

  #container,
  #myMap {
    height: 100%;
    width: 100%;
  }

  button {
    width: 550 rpx;
    height: 90 rpx;
    margin-left: -275 rpx;
    position: fixed;
    left: 50%;
    bottom: 50 rpx;
    background-color: #2C8DF6;
    line-height: 90 rpx;
    color: #ffffff;
  }

  cover-view {
    position: fixed;
    width: 750 rpx;
    height: 200 rpx;
    bottom: 0;
  }
</style>
