<template id="tpl">
  <div id="container">
    <!--地图容器
    longitude="poi.longitude"
         latitude="poi.latitude"-->
    <!--longitude及latitude为设置为调转到指定地址位置，默认不显示-->
    <map id="myMap"
         :markers="markers"

         scale='16' show-location>
    </map>
    <!--form表单-->
    <!--form表单-->
    <!--<form @submit="formSubmit($event)">-->
    <!--&lt;!&ndash;地址描述输入框,示例：北京市海淀区彩和坊路海淀西大街74号&ndash;&gt;-->
    <!--<input style="border:1px solid #000;" name="geocoder"></input>-->
    <!--&lt;!&ndash;提交表单数据&ndash;&gt;-->
    <!--<button form-type="submit" bindtap="geo">地址解析</button>-->
    <!--</form>-->
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
      return {}
    },
    methods: {
      handleTap() {
        console.log("Tapped!")
      }
    },
    onLoad(){
      console.log('Created')
      let that = this
      let myPoi = {}

      wx.getLocation({
        type: 'gcj02',
        success(res){
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
            }
          })
        }
      })


    },
    mounted() {
      console.log("Mounted")
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
