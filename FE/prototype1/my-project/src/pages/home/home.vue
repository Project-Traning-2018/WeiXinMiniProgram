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
         @markertap="handleSelectSpot($event)"
         scale='16' show-location>
    </map>
    <cover-view>
      <button @tap="handleNew">
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
        },
        nearbyActId: 0,
        nearbyActList: []
      }
    },
    methods: {
      handleNew() {
        wx.navigateTo({
          url: '../newActivity/main'
        })
      },
      // 地图上marker的点击事件
      handleSelectSpot(e){
        /*e.mp.markerId*/
        this.nearbyActId = e.mp.markerId
        wx.navigateTo({
          url: '../randomActivityDetail/main?nearbyActId='+this.nearbyActId
        })
      }
    },
    onShow() {
      let that = this
      let myPoi = {}
      wx.getLocation({
        type: 'gcj02',
        success(res) {
          console.log("位置信息：")
          console.log(res)
          myPoi = res
          console.log(that.globalData.id)
          /* 获取附件2km内的活动 */
          let data2send = {
            'userIdMd5': that.globalData.id,
            'activityAddress': 'myAddress',
            'activityAddressname': 'myAddressName',
            'activityLatitude': myPoi.latitude,
            'activityLongitude': myPoi.longitude
          }
          that.$fly.interceptors.request.use((request) => {
            request.headers = {
              'Content-Type': 'application/json'
            };
          })
          that.$fly.get({
            method: 'POST',
            timeout: 500000,
            url: 'http://activity103.mynatapp.cc/miniapp/activityinfo/getactivitynearby',/*contentType: 'application/json;charset=utf-8',*/
            body: JSON.stringify(data2send)
          }).then(function(res){
            console.log(res.data)
            that.nearbyActList = res.data.data
            console.log(res.data.data)
            console.log(typeof res.data.data)
            that.globalData.nearbyActList = that.nearbyActList
            that.markers = []
            for (let i = 0;i < that.nearbyActList.length;i++) {
              that.markers[i] = {}
              that.markers[i].id = that.nearbyActList[i].activityId
              that.markers[i].latitude = that.nearbyActList[i].activityLatitude
              that.markers[i].longitude = that.nearbyActList[i].activityLongitude
              that.markers[i].callout = {
                content: that.callout.content,
                bgColor: '#ff0000',
                color: that.callout.color,
                padding: that.callout.padding,
                display: that.callout.display,
                borderRadius: that.callout.borderRadius
              }
              console.log(that.markers[i])
            }
          }).catch(function () {
            wx.showToast({
              icon: 'none',
              title: '数据请求失败，请检查网络连接并重启小程序'
            })
          })
          that.qqmapsdk.reverseGeocoder({
            location: {
              latitude: myPoi.latitude,
              longitude: myPoi.longitude
            },
            success(res) {
              console.log(res)
              that.latitude = myPoi.latitude
              that.longitude = myPoi.longitude
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
                      console.log(`获得了用户的userinfo`)
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
      })
      this.mapCtx = wx.createMapContext('myMap');
    }
  }
</script>

<style scoped>
  #container,
  #myMap {
    height: 100%;
    width: 100%;
  }

  button {
    width: 550rpx;
    height: 90rpx;
    margin-left: -275rpx;
    position: fixed;
    left: 50%;
    bottom: 50rpx;
    background-color: #2C8DF6;
    line-height: 90rpx;
    color: #ffffff;
  }

  cover-view {
    position: fixed;
    width: 750rpx;
    height: 200rpx;
    bottom: 0;
  }
</style>
